package testcom.onlineeducation.DAO;

import org.json.JSONObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import testcom.onlineeducation.bean.UserInfoBean;
import testcom.onlineeducation.network.ApiService;
import testcom.onlineeducation.network.BaseCallBack;
import testcom.onlineeducation.network.BaseObserver;
import testcom.onlineeducation.network.HttpApi;
import testcom.onlineeducation.network.RetroFitManager;
import testcom.onlineeducation.utils.LoginUtils;

/**
 * Created by Administrator on 2017/6/16.
 */
public class UserDao {

    //测试接口
    public static void FirstRequest(final String id, final String unique_id, final BaseCallBack baseCallBack){
        RetroFitManager.getInstance()
                .setUrl("http://sevenshop.lht9.com/")
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
    }

    //注册接口
    public static void Register(final String username,final String password,final String code,
                                final String type,final String email_id,final BaseCallBack baseCallBack){
        RetroFitManager.getInstance().setUrl(HttpApi.mainURL)
                .addLog(false)
                .setCallBackListener(new RetroFitManager.ResponseCallBack() {
                    @Override
                    public void setResponseCallBack(ApiService handlercenter) {
                        handlercenter.Register(username, password, code, type, email_id)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseObserver() {
                                    @Override
                                    public void onNext(String content) {
                                        baseCallBack.success(content);
                                    }

                                    @Override
                                    public void onError(int code, String error) {
                                        baseCallBack.failed(code,error);
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }).create();

    }

    //获取邮箱码
    public static void getEmailCode(final String email,final BaseCallBack baseCallBack){
        RetroFitManager.getInstance().setUrl(HttpApi.mainURL)
                .addLog(false)
                .setCallBackListener(new RetroFitManager.ResponseCallBack() {
                    @Override
                    public void setResponseCallBack(ApiService handlercenter) {
                        handlercenter.getEmailCode(email)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseObserver() {
                                    @Override
                                    public void onNext(String content) {
                                        try{
                                            JSONObject obj = new JSONObject(content);
                                            baseCallBack.success(obj.getString("email_id"));
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }

                                    }

                                    @Override
                                    public void onError(int code, String error) {
                                        baseCallBack.failed(code,error);
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }).create();
    }

    //获取登录
    public static void Login(final String username,final String password,final BaseCallBack baseCallBack){
        RetroFitManager.getInstance()
                .setUrl(HttpApi.mainURL)
                .addLog(false)
                .setCallBackListener(new RetroFitManager.ResponseCallBack() {
                    @Override
                    public void setResponseCallBack(ApiService handlercenter) {
                        handlercenter.Login(username,password)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseObserver() {
                                    @Override
                                    public void onNext(String content) {
                                        try{
                                            UserInfoBean bean = new UserInfoBean();
                                            JSONObject obj = new JSONObject(content);
                                            bean.setId(obj.getString("id"));
                                            bean.setName(obj.getString("name"));
                                            bean.setNickname(obj.getString("nickname"));
                                            bean.setSex(obj.getString("sex"));
                                            bean.setEmail(obj.getString("email"));
                                            bean.setLanguage(obj.getString("language"));
                                            bean.setAvatar(obj.getString("avatar"));
                                            LoginUtils.setToken(obj.getString("token"));
                                            baseCallBack.success(bean);
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }

                                    }

                                    @Override
                                    public void onError(int code, String error) {
                                        baseCallBack.failed(code,error);
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }).create();
    }

    //忘记密码
    public static void ForgetPassword(final String username,final String password,final String code,
                                      final String type,final String email_id,final BaseCallBack baseCallBack){
        RetroFitManager.getInstance()
                .setUrl(HttpApi.mainURL)
                .addLog(false)
                .setCallBackListener(new RetroFitManager.ResponseCallBack() {
                    @Override
                    public void setResponseCallBack(ApiService handlercenter) {
                        handlercenter.ForgetPassword(username, password, code, type, email_id)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseObserver(){

                                    @Override
                                    public void onNext(String content) {
                                        baseCallBack.success(content);
                                    }

                                    @Override
                                    public void onError(int code, String error) {
                                        baseCallBack.failed(code,error);
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }).create();

    }

    //更新用户信息
    public static void Update(final String avatar,final String sex,final String language,final String nickname,
                              final BaseCallBack baseCallBack){
        RetroFitManager.getInstance()
                .setUrl(HttpApi.mainURL)
                .addLog(true)
                .setCallBackListener(new RetroFitManager.ResponseCallBack() {
                    @Override
                    public void setResponseCallBack(ApiService handlercenter) {
                        handlercenter.Update(avatar, sex, language, nickname)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseObserver() {
                                    @Override
                                    public void onNext(String content) {
                                        baseCallBack.success(content);
                                    }

                                    @Override
                                    public void onError(int code, String error) {
                                        baseCallBack.failed(code,error);
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }).create();

    }

    //选择语种
    public static void SelectLanguage(final String language,final BaseCallBack baseCallBack){
        RetroFitManager.getInstance()
                .setUrl(HttpApi.mainURL)
                .addLog(true)
                .setCallBackListener(new RetroFitManager.ResponseCallBack() {
                    @Override
                    public void setResponseCallBack(ApiService handlercenter) {
                        handlercenter.SelectLanguage(language)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseObserver() {
                                    @Override
                                    public void onNext(String content) {
                                        baseCallBack.success(content);
                                    }

                                    @Override
                                    public void onError(int code, String error) {
                                        baseCallBack.failed(code,error);
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }).create();

    }

    //退出登录
    public static void Loginout(final BaseCallBack baseCallBack){
        RetroFitManager.getInstance()
                .setUrl(HttpApi.mainURL)
                .addLog(true)
                .setCallBackListener(new RetroFitManager.ResponseCallBack() {
                    @Override
                    public void setResponseCallBack(ApiService handlercenter) {
                        handlercenter.LoginOut(LoginUtils.getToken())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new BaseObserver() {
                                    @Override
                                    public void onNext(String content) {
                                        baseCallBack.success(content);
                                    }

                                    @Override
                                    public void onError(int code, String error) {
                                        baseCallBack.failed(code,error);
                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }).create();
    }
}
