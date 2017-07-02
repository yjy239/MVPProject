package testcom.onlineeducation.presenter.Detailed;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.PayContract;

/**
 * Created by Administrator on 2017/6/21.
 */
public class PayPresentImpl extends BasePresenter<PayContract.PayView> implements PayContract.PayPresenter{

    PayContract.PayView mView;

    public PayPresentImpl(PayContract.PayView mView){
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
