package testcom.onlineeducation.Contract;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Login.MailForgetPresentImpl;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface MailForgetContract {
    interface View extends BaseView<MailForgetPresentImpl>{
        void initData();
    }

    interface Presenter{

    }
}
