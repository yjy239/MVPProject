package testcom.onlineeducation.Contract;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Login.PhoneRegisterPresentImpl;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface PhoneRegisterContract {
    interface View extends BaseView<PhoneRegisterPresentImpl>{
        void initData();
    }

    interface Presenter{

    }
}
