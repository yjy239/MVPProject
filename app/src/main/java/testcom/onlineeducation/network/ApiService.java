package testcom.onlineeducation.network;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface ApiService {
    @POST("api/goods/goodsInfo")
    @FormUrlEncoded
    Observable<Response<ResponseBody>> firstRequest(@Field("id")String id, @Field("unique_id")String unique_id);

    /***手机或者邮箱注册***/
    @POST(HttpApi.register)
    @FormUrlEncoded
    Observable<Response<ResponseBody>> Register(@Field("username")String username,@Field("password")String password,
                                                     @Field("code")String code,@Field("type")String type,@Field("email_id")String email_id);

    /***获取邮箱码***/
    @POST(HttpApi.getEmailCode)
    @FormUrlEncoded
    Observable<Response<ResponseBody>> getEmailCode(@Field("email")String email);

    /***用户登录**/
    @POST(HttpApi.Login)
    @FormUrlEncoded
    Observable<Response<ResponseBody>> Login(@Field("username")String username,@Field("password")String password);

    /***忘记密码***/
    @POST(HttpApi.Forget)
    @FormUrlEncoded
    Observable<Response<ResponseBody>> ForgetPassword(@Field("username")String username,@Field("password")String password,@Field("code")String code,@Field("type")String type,@Field("email_id")String email_id);

    /**更新用户信息**/
    @POST(HttpApi.Update)
    @FormUrlEncoded
    Observable<Response<ResponseBody>> Update(@Field("avatar")String avatar,@Field("sex")String sex,@Field("language")String language,
                                             @Field("nickname")String nickname);

    /***选择语种***/
    @POST(HttpApi.SelectLanguage)
    @FormUrlEncoded
    Observable<Response<ResponseBody>> SelectLanguage(@Field("language")String language);

    /***退出登录**/
    @POST(HttpApi.LoginOut)
    @FormUrlEncoded
    Observable<Response<ResponseBody>> LoginOut(@Field("token")String token);



}
