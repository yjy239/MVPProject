package testcom.onlineeducation.presenter.Login;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.MailForgetContract;

/**
 * Created by Administrator on 2017/6/20.
 */
public class MailForgetPresentImpl extends BasePresenter<MailForgetContract.View> implements MailForgetContract.Presenter{

    private MailForgetContract.View mView;

    public MailForgetPresentImpl(MailForgetContract.View mView){
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
