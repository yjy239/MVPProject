package testcom.onlineeducation;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/6/14.
 * creator : yjy
 */
public abstract class BasePresenter<V extends BaseView> {
    protected Reference<V> mViewRef;

    /**mvp:
     * 绑定view**/
    public void attchView(V view){
        if(mViewRef ==null){
            mViewRef = new WeakReference<V>(view);
        }
    }


    /***获取view**/
    public V getView(){
        return mViewRef.get();
    }

    /***是否建立联系**/
    public boolean isAttched(){
        return mViewRef!=null && mViewRef.get()!=null;
    }

    /***接触view的绑定**/
    public void detachView(){
        if(mViewRef !=null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public abstract void start();

}
