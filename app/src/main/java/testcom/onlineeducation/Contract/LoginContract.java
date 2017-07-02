package testcom.onlineeducation.Contract;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Login.LoginPresentImpl;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface LoginContract {
    interface LoginView extends BaseView<LoginPresentImpl>{
        void initData();
    }

    interface LoginPresent{

    }
}
