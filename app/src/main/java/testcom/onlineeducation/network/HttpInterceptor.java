package testcom.onlineeducation.network;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import testcom.onlineeducation.utils.LoginUtils;

/**
 * Created by Administrator on 2017/6/15.
 */
public class HttpInterceptor implements Interceptor {

    private boolean hasToken;

    public HttpInterceptor(boolean hasToken){
        this.hasToken = hasToken;
    }

    private static final Charset UTF8 = Charset.forName("UTF-8");
    @Override
    public Response intercept(Chain chain) throws IOException {
        long nanotime  = System.nanoTime();
        Request request = chain.request();
        Request newrequest= null;
        RequestBody requestBody = request.body();

        Request.Builder builder = request.newBuilder();
        boolean hasBody = requestBody!=null;
        String url="";
        if(hasBody){
            //添加token
            //新建一个body在全部添加进去
            if(hasToken){
                if(requestBody instanceof FormBody){
                    FormBody.Builder newbody = new FormBody.Builder();
                    FormBody oldbody = (FormBody) requestBody;
                    for (int i = 0;i<oldbody.size();i++){
                        newbody.addEncoded(oldbody.encodedName(i),oldbody.encodedValue(i));
                    }
                    if(LoginUtils.getToken()!=null){
                        newbody.add("token",LoginUtils.getToken());
                    }
                    builder.method(request.method(),newbody.build());
                    newrequest = builder.build();
                }
            }else {
                newrequest = request;
            }

            Buffer buffer = new Buffer();
            newrequest.body().writeTo(buffer);

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                contentType.charset(UTF8);
            }
            url = buffer.readString(charset);
            Log.e("url",buffer.readString(charset));
        }

        Log.e("Http ","  sending Request :"+request.url()+"/"+url);
        Response response = chain.proceed(newrequest);
        long nanotime2 = System.nanoTime();
        Log.e("Http ","  receiver Request :"+request.url()+"  Time:" + (nanotime2-nanotime)/1e6d + response.headers()+response.toString());

        return response;
    }
}
