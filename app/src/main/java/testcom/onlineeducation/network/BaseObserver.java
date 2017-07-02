package testcom.onlineeducation.network;


import android.util.Log;

import org.json.JSONObject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import testcom.onlineeducation.utils.TextUtils;

/**
 * Created by Administrator on 2017/6/16.
 */
public abstract class BaseObserver implements Observer<Response<ResponseBody>> {

    public static CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        Log.e("Disposable","add");
        compositeDisposable.add(d);
    }

    @Override
    public void onNext(@NonNull Response<ResponseBody> responseBodyResponse){
        try{
            if(responseBodyResponse.body() != null){
                String responseStr = responseBodyResponse.body().string();
                JSONObject jsonObject = new JSONObject(responseStr);
                Log.e("responseBody: ",responseStr);
                int code = 0;
                code = jsonObject.getInt("code");
                String message = jsonObject.getString("message");
                if(jsonObject.has("result")&&jsonObject.getString("result")!=null){
                    String content = jsonObject.getString("result");
                    if(code == 0){
                        if(content != null){
                            onNext(content);
                        }else {
                            Log.e("Http","没有content");
                        }

                    }else {
                        onError(code,content);
                    }
                    Log.e("message",message+"");
                }else {
                  if(code != 0){
                      onError(code,message);
                  }else {
                      onNext(message);
                  }
                }
            }else if(responseBodyResponse.errorBody() != null){
                String error = responseBodyResponse.errorBody().string();
                Log.e("error",responseBodyResponse.errorBody().string());
                if(error!=null){
                    JSONObject jsonObject = new JSONObject(error);
                    int code = jsonObject.getInt("code");
                    String message = jsonObject.getString("message");
                    onError(code,TextUtils.toURLDecoded(message));
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public abstract void onNext(String content);

    @Override
    public void onError(@NonNull Throwable e) {
        e.printStackTrace();
    }

    public abstract void onError(int code,String error);

    @Override
    public abstract void onComplete();

    public static CompositeDisposable getCompositeDisposable(){
        return compositeDisposable;
    }
}
