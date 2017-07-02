package testcom.onlineeducation.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Administrator on 2017/6/15.
 */
public class RetroFitManager {


    private static RetroFitManager mInstance;
    private static Retrofit.Builder builder;
    private static Retrofit retrofit;
    private static ApiService handlercenter;


    private RetroFitManager(){
        builder = new Retrofit.Builder();
    }

    public static RetroFitManager getInstance(){
        if(builder == null){
            synchronized (RetroFitManager.class){
                if(builder == null){
                    mInstance = new RetroFitManager();
                }
            }
        }
        return mInstance;
    }

    public  RetroFitManager setUrl(String url){
        builder.baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return mInstance;
    }


    public RetroFitManager addLog(boolean hasToken){
         builder.client(getOkHttpClient(hasToken));
        return mInstance;
    }

    public  RetroFitManager create(){
        retrofit = builder.build();
        handlercenter = retrofit.create(ApiService.class);
        if(CallBack != null){
            CallBack.setResponseCallBack(handlercenter);
        }
        return mInstance;
    }


    private static OkHttpClient getOkHttpClient(boolean hasToken) {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(new HttpInterceptor(hasToken));
        httpClientBuilder.readTimeout(10, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(10,TimeUnit.SECONDS);
        return httpClientBuilder.build();
    }


    public interface ResponseCallBack{
        void setResponseCallBack(ApiService handlercenter);
    }
    public ResponseCallBack CallBack;

    public RetroFitManager setCallBackListener(ResponseCallBack CallBack){
        this.CallBack = CallBack;
        return mInstance;
    }







}
