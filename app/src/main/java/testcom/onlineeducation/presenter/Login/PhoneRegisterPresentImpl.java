package testcom.onlineeducation.presenter.Login;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.PhoneRegisterContract;

/**
 * Created by Administrator on 2017/6/20.
 */
public class PhoneRegisterPresentImpl extends BasePresenter<PhoneRegisterContract.View> implements PhoneRegisterContract.Presenter{

    private PhoneRegisterContract.View mView;

    public PhoneRegisterPresentImpl(PhoneRegisterContract.View mView){
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.initData();
    }
}
