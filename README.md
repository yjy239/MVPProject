注：该工程由MVP+Retrofit+rxjava框架，经过测试无太大问题。但是由于是自己在搭公司项目的前期框架
之后不会进一步更新工程内容，只会更新自己封装框架的bug，欢迎看官指出我的错漏

阅读帮助
===
该工程作为启动项目的前期框架，里面只有几个接口用来测试网络引擎的正确性<br/>

主要工程模块：
---
该项目以MVP框架作为思想基础搭建起来，为了避免内存泄露，稍微从几个固定的搭建模式中做了点修改。<br/>
<br/>
1.ui文件夹:用于存放activity以及fragment等视图文件 相当于MVP中view的角色<br/>
<br/>
2.model文件夹:用于存放数据，相当于MVP中Model角色<br/>
<br/>
3.Contract文件夹:用于存放mvp用来相互关联的接口管理，（用法详见代码，和参照google的mvp文档）<br/>
<br/>
4.Preseneter文件夹:用于存放Mvp角色中Presenter角色。里面的类全部继承了BasePresnter(BasePresenter对持有长时间的引用用了弱引用处理)<br/>
<br/>
5.utils文件夹:用于工程中的工具类,里面有rxBus的工具类,以及sharesdk的接法LoginApi。<br/>
<br/>
6.network文件夹:用于工程的网络引擎（由Rxjava+Retrofit封装）<br/>
<br/>
7.Dao文件夹:用于封装每个模块的网络请求的具体方法<br/>
<br/>

## 网络引擎用法
---

该网络引擎RetrofitManager其实就是对Retrofit.Builder进一步封装，完成了链式调用，想办法简化其中流程，用法如下：<br/>
<br/>
现在ApiService接口创建一个Retrofit的方法<br/>
```Java
 @POST("api/goods/goodsInfo")
    @FormUrlEncoded
    Observable<Response<ResponseBody>> firstRequest(@Field("id")String id, @Field("unique_id")String unique_id);
```
在对应的Dao模块创建请求方法
```Java
	public static void FirstRequest(final String id, final String unique_id, final BaseCallBack baseCallBack){
        RetroFitManager.getInstance()
                .setUrl("")
                .addLog(true)
                .setCallBackListener(new RetroFitManager.ResponseCallBack() {
                    @Override
                    public void setResponseCallBack(ApiService handlercenter) {
                        handlercenter.firstRequest(id,unique_id)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseObserver() {
                                    @Override
                                    public void onNext(String content) {
                                        //此处可以做解析json数据，content已经去掉了code和message
                                        baseCallBack.success(content);
                                    }

                                    @Override
                                    public void onError(int code, String error) {
                                        //此处可以获取错误代码和错误
                                        baseCallBack.failed(code,error);
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                })
                .create();
```

方法说明:<br/>
setUrl():是用来设置url的服务器地址<br/>
<br/>
addLog(boolean token)：用来设置自己的做的网络拦截器,添加打印完整url+参数,token是指是否添加存放在LoginUtils的token（注：retrofit中不可以没有请求参数，当只有token的时候最好添加上）<br/>
<br/>
setCallBackListener(ResponseCallBack CallBack):用来处理网络回应的Response体回调<br/>
<br/>
BaseObserver():抽象类用来处理一般的Response的结构，将成功失败的数据分别传出。自己想要处理自己的相应体结构不可不必使用这个BaseObserver<br/>
<br/>
create():用来创建Rtrofit+rxjava链式方法<br/>
<br/>

最后的调用:
```java
 UserDao.FirstRequest("46", "869515024751809", new BaseCallBack() {
            @Override
            public void success(Object data) {
                mView.loadingData();
            }

            @Override
            public void failed(int errorCode, Object data) {

            }

            @Override
            public void failed(Object data) {

            }
        });
```
<br/>
在对应的位置进行处理:success:是回调成功，onerror是回调失败或者服务器返回错误等处理<br/>
<br/>

## RxBus的调用

## 1.先注册，将对应的类注册到了订阅者的队列中：<br/>
```Java

RxBus.getDefault().register(this);

```

## 2.记住在onDestroy记得注销，避免内存泄露:
```Java

  RxBus.getDefault().unregister(this);
  
```
<br/>

## 3.建立一个带 @Subcuribe({HandlerThread.MAIN})的方法<br/>

```java
  HandlerThread.MAIN,HandlerThread.IO
```
里面的参数时用来处理方法进行的动作是UI线程工作还是io线程工作,例子:
```java
@Subcuribe({HandlerThread.MAIN})
    public void handler(RxPost ev){
        switch (ev.getTag()){
            case IEvent.LOGIN:
                nickname.setText("登录");
                LoginUtils.setIsLogin(true);
                break;
            case IEvent.LOGINOUT:
                nickname.setText("未登录");
                LoginUtils.setIsLogin(false);
                break;
        }
    }
```

# 结束语
<br/>
本人只是一个月薪3k的菜鸟，虽然搭了几个工程的框架，以后觉得有的思路还算的上可以，给大家看看我错漏，毕竟一个人摸黑走，确实成长太慢了。
希望大家不吝啬指教，这个工程虽然为了这个工程专门适配了不少地方，以后我想办法，同一个通用框架出来，便于自己的工程。<br/>







 

