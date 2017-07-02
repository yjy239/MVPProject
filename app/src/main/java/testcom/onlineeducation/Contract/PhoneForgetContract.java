package testcom.onlineeducation.Contract;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Login.PhoneForgetPresentImpl;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface PhoneForgetContract {
    interface View extends BaseView<PhoneForgetPresentImpl>{
        void initData();
    }

    interface  Presenter{

    }
}
