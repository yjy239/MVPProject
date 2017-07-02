package testcom.onlineeducation.presenter.Login;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.LoginContract;

/**
 * Created by Administrator on 2017/6/20.
 */
public class LoginPresentImpl extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresent {

    private LoginContract.LoginView mView;

    public LoginPresentImpl(LoginContract.LoginView mView){
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
