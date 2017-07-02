package testcom.onlineeducation.utils;

import android.content.Context;
import android.util.Log;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/6/27.
 */
public class TextUtils {
    public static String getRString(int value, Context mContext){
        return mContext.getResources().getString(value);
    }

    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            Log.e("toURLEncoded error:" , paramString);
            return "";
        }

        try
        {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        }
        catch (Exception localException)
        {
            Log.e("toURLEncoded error:"+paramString, localException+"");
        }

        return "";
    }

    public static String toURLDecoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            Log.e("toURLDecoded error:",paramString);
            return "";
        }

        try
        {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLDecoder.decode(str, "UTF-8");
            return str;
        }
        catch (Exception localException)
        {
            Log.e("toURLDecoded error:"+paramString, localException+"");
        }

        return "";
    }
}
