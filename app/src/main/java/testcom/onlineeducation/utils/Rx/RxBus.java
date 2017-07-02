package testcom.onlineeducation.utils.Rx;


import android.util.Log;

import org.reactivestreams.Publisher;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import testcom.onlineeducation.i.RxPost;

/**
 * Created by Administrator on 2017/6/22.
 */
public class RxBus {

    private static RxBus mInstance;
    private final Subject bus = PublishSubject.create().toSerialized();
    //存放订阅者信息
    protected static Map<Object, CompositeDisposable> subscriptions = new HashMap<>();

    private RxBus(){

    }

    public static RxBus getDefault(){
        if(mInstance == null){
            synchronized (RxBus.class){
                if(mInstance == null){
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    public void post(Object o){
        bus.onNext(o);
    }

    public void register(@Nullable final Object subscrber){
        Flowable.just(subscrber)
                .filter(new Predicate<Object>() {
                    @Override
                    public boolean test(@NonNull Object o) throws Exception {
                        //判断有没有存在订阅者里面
                        return subscriptions.get(subscrber) == null;
                    }
                })
                .flatMap(new Function<Object, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(@NonNull Object o) throws Exception {
                        //获取该类中所有的的方法
                        return Flowable.fromArray(o.getClass().getDeclaredMethods());
                    }
                })
                .map(new Function<Object, Method>() {
                    @Override
                    public Method apply(@NonNull Object o) throws Exception {
                        //所有方法全部设置可以访问
                        Method m = (Method)o;
                        m.setAccessible(true);
                        return m;
                    }
                })
                .filter(new Predicate<Method>() {
                    @Override
                    public boolean test(@NonNull Method method) throws Exception {
                        //检查方法有没有注释
                        return method.isAnnotationPresent(Subcuribe.class);
                    }
                })
                .subscribe(new Consumer<Method>() {
                    @Override
                    public void accept(@NonNull Method method) throws Exception {
                        //加入到订阅者map里面
                        addMap(method,subscrber);
                    }
                });
    }

    public void addMap(final Method m, final Object subscrber){
        //解析m事件里面的参数
        Subcuribe sub = m.getAnnotation(Subcuribe.class);
        int[] type = sub.value();
        //第一个是subscribeOn() 的线程,第二个事observeOn线程
        int subtype = 0;
        int obtype = 0;
        if(type.length == 1){
            obtype = type[0];
        }

        if(type.length == 2){
            subtype = type[0];
            obtype = type[1];
        }

        Class<?>[] paramvalue = m.getParameterTypes();
        Class typeClass = null;
        if(paramvalue.length > 0){
            typeClass = paramvalue[0];
        }

        if(typeClass == null){
            typeClass = RxPost.class;
        }

        //第二个是ObserveOn() 的线程
        //订阅事件
        Disposable disposable = tObservable(typeClass)
                .observeOn(getThread(obtype))
                .subscribe(new Consumer() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        try {
                            //注入处理事件
                            boolean test =o instanceof RxPost;
                            Log.e("class",test + "");
                            m.invoke(subscrber,o);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
        //添加到map里面
        CompositeDisposable item = subscriptions.get(subscrber);
        if(item == null){
            item = new CompositeDisposable();
        }
        item.add(disposable);

        subscriptions.put(subscrber,item);
    }

    public Scheduler getThread(int type){
        if(type == 1){
            return AndroidSchedulers.mainThread();
        }else if(type == 2){
            return Schedulers.io();
        }else {
            return AndroidSchedulers.mainThread();
        }
    }


    //传入特定的类型
    public  <T>Observable tObservable(Class<T> eventType){
        return bus.ofType(eventType);
    }

    //注销
    public void unregister(Object subscrber){
        CompositeDisposable disposable;
        if(subscrber !=null){
            disposable = subscriptions.get(subscrber);
            if(disposable !=null){
                disposable.dispose();
                subscriptions.remove(subscrber);
            }
        }
    }


}
