package testcom.onlineeducation.network;


import okhttp3.FormBody;
import testcom.onlineeducation.utils.MD5;

/**
 * Created by Administrator on 2016/7/5.
 */
public class HttpApi {


//    "http://api.global.football.gamenew100.com"
    //主服务器地址
    public static final String mainURL = "http://babi.lht9.com/";
    /**图片地址*/
    private static final String imageURL = "http://sevenshop.lht9.com";
    //签名用到的randomKey
    private static final String randomKey = "123456";

    public static final String imageLoadURL = "http://v0.api.upyun.com/football9";

    //返回值为{code:0,content:响应数据,message:错误详情}，code为0时表示成功，其它均为失败

    //region 注册登录接口
    /***注册**/
    public static final String register = "Api/User/register";

    /***获取邮箱码**/
    public static final String getEmailCode = "Api/User/sendEmailCode";

    /***登录**/
    public static final String Login = "Api/User/login";

    /****忘记密码**/
    public static final String Forget = "Api/User/forgetpwd";

    /***更新用户信息**/
    public static final String Update = "Api/User/updateuser";

    /**选择语种***/
    public static final String SelectLanguage = "Api/User/selectLanguage";

    /***退出登录**/
    public static final String LoginOut = "Api/User/logout";
    //endregion





    //获取post请求URL
    public static String getRouterURL(String router) {
        return mainURL + router;
    }


    //获取post form
    public static FormBody getFormBody(String requestStr) {
        String signData = HttpApi.getSignString(requestStr);
//        Log.e("FormBody", requestStr);
//        Log.e("FormBody", signData);
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("code", requestStr)
                .add("sign", signData);
        return builder.build();
    }

    //签名
    public static String getSignString(String sign) {
        String signData = MD5.getMD5(sign) + randomKey;
//        signData = signData.toUpperCase();
        signData = MD5.getMD5(signData);

        return signData;
    }

    //获取图片完整url
    public static String getFullImageUrl(String imageUrl) {
        if (imageUrl == null)
            return "";
        if (imageUrl.contains("http://") || imageUrl.contains("https://")) {
            return imageUrl;
        } else {
            return imageURL + "/" + imageUrl;
        }
    }

    public static String getFullImageUrl(String imageURL, boolean small) {
        if (imageURL == null) return "";
        String fullImageUrl = getFullImageUrl(imageURL);
        if (small) {
            fullImageUrl = fullImageUrl + "!/fwfh/300x300";
        }
        return fullImageUrl;
    }

}
