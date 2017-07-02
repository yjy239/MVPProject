package testcom.onlineeducation;

import android.app.Application;

import com.mob.MobSDK;
import com.se7en.utils.DeviceUtils;
import com.se7en.utils.SystemUtil;

import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2017/6/23.
 */
public class OnlineEducationApplication extends Application{

    String TAG = "com.yidu.kingoffootball.SevenSecondAppliaction";

    @Override
    public void onCreate() {
        super.onCreate();
        DeviceUtils.setContext(this);
        SystemUtil.setContext(this);
        MobSDK.init(this,"1dc7c72f41ab1");
        SMSSDK.initSDK(this,"1dc7c72f41ab1","cce730f7b06fd9944a09add6325ed4a8");

    }
}
