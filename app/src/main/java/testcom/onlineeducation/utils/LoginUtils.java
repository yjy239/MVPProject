package testcom.onlineeducation.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.se7en.utils.SystemUtil;
//import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Administrator on 2016/8/12.
 */
public class LoginUtils {

    public final static String PlatformPhone = "phone";
    public final static String PlatformQQ = "qq";
    public final static String PlatformWechat = "Wechat";
    public final static String PlatformSina = "weibo";

    private static String user_id = "";
    private static String token = "";
    private static String mobile = "";
    public static String getMobile() {
        return SystemUtil.getSharedString("mobile","");
    }
    public static String scoreString = "";
    public static String createTeam = "";
    public static String technologyrule = "";
    public static String register = "";
    public static String about = "";
    public static int size = 0;
    public static boolean open = false;

    public static String getDeviceid() {
        return deviceid;
    }

    public static void setDeviceid(String deviceid) {
        LoginUtils.deviceid = deviceid;
    }

    public static String deviceid = "";


    public static void setSwitch(boolean open){
        LoginUtils.open = open;
    }

    public static boolean getSwitch(){
        return LoginUtils.open;
    }


    public static void setSize(int size){
        SystemUtil.setSharedString("size",String.valueOf(size));
    }

    public static int getSize(){
        if(SystemUtil.getSharedString("size") != null){
            return Integer.parseInt(SystemUtil.getSharedString("size"));
        }else {
            setSize(0);
            return Integer.parseInt(SystemUtil.getSharedString("size"));
        }

    }

    public static void saveuserlist(String mobile,int size){
        SystemUtil.setSharedString("user"+size,mobile);
    }

    public static void setVersion(String version){
        SystemUtil.setSharedString("version",version);
    }

    public static String getVersion(){
        return SystemUtil.getSharedString("version","");
    }
    public static String getSaveuserlist(int i){
        return SystemUtil.getSharedString("user"+i);
    }

    public static String getCreateTeam() {
        return createTeam;
    }

    public static void setCreateTeam(String createTeam) {
        LoginUtils.createTeam = createTeam;
    }

    public static String getAbout() {
        return about;
    }

    public static void setAbout(String about) {
        LoginUtils.about = about;
    }

    public static String getRegister() {
        return register;
    }

    public static void setRegister(String register) {
        LoginUtils.register = register;
    }

    public static String getTechnologyrule() {
        return technologyrule;
    }

    public static void setTechnologyrule(String technologyrule) {
        LoginUtils.technologyrule = technologyrule;
    }

    public static String getScoreString() {
        return scoreString;
    }

    public static void setScoreString(String scoreString) {
        LoginUtils.scoreString = scoreString;
    }


    public static void setMobile(String mobile) {
        SystemUtil.setSharedString("mobile",mobile);
    }



    public static String getRegistration_id() {
        return registration_id;
    }

    public static void setRegistration_id(String registration_id) {
        LoginUtils.registration_id = registration_id;
    }

    private static String registration_id;


    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        LoginUtils.username = username;
    }

    /*环信账号和密码*/
    private static String username = "";
    private static String password = "";


    /*是否登录了*/
    public static boolean isLogin(){
        return SystemUtil.getSharedBoolean("isLogin", false);
    }
    /*设置是否登录*/
    public static void setIsLogin(final boolean isLogin){
        SystemUtil.setSharedBoolean("isLogin", isLogin);
    }
    /*设置自动登陆所需数据*/
    public static void setAutoLoginParams(final String platform, final String openid, final String access_token){
        SystemUtil.setSharedString("openid", openid);
        SystemUtil.setSharedString("access_token", access_token);
        SystemUtil.setSharedString("loginPlatform", platform);
    }
    /*登录成功设置*/
    public static void setLoginSuccessInfo(final String user_id, final String token, final String platform, final String openid,final String username, final String access_token){
        setUserId(user_id);
        setToken(token);
        setUsername(username);
        setIsLogin(true);
        setAutoLoginParams(platform, openid, access_token);
    }
    /*获取登录平台信息*/
    public static String getLoginPlatform(){
        return SystemUtil.getSharedString("loginPlatform", "");
    }

    public static void setPlatform(String platform){
        SystemUtil.setSharedString("loginPlatform",platform);
    }

    public static String getLoginOpenid(){
        return SystemUtil.getSharedString("openid", "");
    }

    public static void setLoginOpenid(String u){
        SystemUtil.setSharedString("openid", "");
    }
    public static String getLoginAccessToken(){
        return SystemUtil.getSharedString("access_token", "");
    }

    public static String getLoginRefresh(){
        return SystemUtil.getSharedString("refresh_token","");
    }

    public static String getLoginEx(){
        return SystemUtil.getSharedString("expires_in","");
    }
    /*设置登录后用户信息*/

    public static String getUserId() {
        return SystemUtil.getSharedString("user_id","");
    }

    public static void setUserId(String user_id) {
        LoginUtils.user_id = user_id;
        SystemUtil.setSharedString("user_id",user_id);
    }

    public static String getToken() {
        return SystemUtil.getSharedString("token","");
    }

    public static void setToken(String token) {
        LoginUtils.token = token;
        SystemUtil.setSharedString("token",token);
    }


    /**
     * 第三方登录信息*/
    public static void setPlatformMessage(String platform,String openid,String accesstoken,String expiresin,
                                          String refreshtoken){
        SystemUtil.setSharedString("loginPlatform",platform);
        SystemUtil.setSharedString("openid",openid);
        SystemUtil.setSharedString("access_token",accesstoken);
        SystemUtil.setSharedString("expires_in",expiresin);
        SystemUtil.setSharedString("refresh_token",refreshtoken);
    }


//    /*平台转换*/
//    public static String platformExchange(final SHARE_MEDIA platform){
//        String platformStr = "";
//        switch (platform){
//            case QQ:
//                platformStr = LoginUtils.PlatformQQ;
//                break;
//            case WEIXIN:
//                platformStr = LoginUtils.PlatformWechat;
//                break;
//            case SINA:
//                platformStr = LoginUtils.PlatformSina;
//                break;
//        }
//        return platformStr;
//    }
//
//    public static SHARE_MEDIA EXchange(String platform){
//        SHARE_MEDIA p = null;
//        switch(platform){
//            case "qq":
//                p = SHARE_MEDIA.QQ;
//                break;
//            case "weixin":
//                p = SHARE_MEDIA.WEIXIN;
//                break;
//            case "weibo":
//                p= SHARE_MEDIA.SINA;
//                break;
//        }
//        return p;
//    }

    /**获取环信账号和密码*/
    public static void setPassword(String password){
       SystemUtil.setSharedString("password",password);
    }

    public static String getPassword(){
       return SystemUtil.getSharedString("password");
    }

    /**退出时清空，个人配置*/
    public static void clear(){
        SystemUtil.setSharedBoolean("isLogin", false);
        SystemUtil.setSharedString("loginPlatform","");
        SystemUtil.setSharedString("openid","");
        SystemUtil.setSharedString("access_token","");
        SystemUtil.setSharedString("expires_in","");
        SystemUtil.setSharedString("refresh_token","");
        LoginUtils.user_id = "";
        LoginUtils.token = "";
    }


    //检测网络状态
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

}
