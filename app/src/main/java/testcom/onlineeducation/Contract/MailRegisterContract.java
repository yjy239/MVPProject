package testcom.onlineeducation.Contract;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Login.MailRegisterPresentImpl;

/**
 * Created by Administrator on 2017/6/20.
 */
public interface MailRegisterContract {
    interface View extends BaseView<MailRegisterPresentImpl>{
        void initData();
    }

    interface Presenter{

    }
}
