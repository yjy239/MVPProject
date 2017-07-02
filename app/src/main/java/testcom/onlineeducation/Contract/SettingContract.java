package testcom.onlineeducation.Contract;

import android.content.Context;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Detailed.SettingPresentImpl;

/**
 * Created by Administrator on 2017/6/22.
 */
public interface SettingContract {
    interface View extends BaseView<SettingPresentImpl>{
        void initData(String[] mData);
        Context getMyContext();
        void actionout();
    }

    interface Presenter{
        void loginout();
    }
}
