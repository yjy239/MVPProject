package testcom.onlineeducation.Contract;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Detailed.PayPresentImpl;

/**
 * Created by Administrator on 2017/6/21.
 */
public interface PayContract {
    interface PayView extends BaseView<PayPresentImpl>{
        void initData();
    }

    interface PayPresenter{

    }
}
