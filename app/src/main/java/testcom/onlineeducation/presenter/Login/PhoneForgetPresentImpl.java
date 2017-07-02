package testcom.onlineeducation.presenter.Login;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.PhoneForgetContract;

/**
 * Created by Administrator on 2017/6/20.
 */
public class PhoneForgetPresentImpl extends BasePresenter<PhoneForgetContract.View> implements PhoneForgetContract.Presenter{

    private PhoneForgetContract.View mView;

    public PhoneForgetPresentImpl(PhoneForgetContract.View mView){
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
