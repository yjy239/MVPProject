package testcom.onlineeducation.presenter.Login;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.MailRegisterContract;

/**
 * Created by Administrator on 2017/6/20.
 */
public class MailRegisterPresentImpl extends BasePresenter<MailRegisterContract.View> implements MailRegisterContract.Presenter{

    private MailRegisterContract.View mView;

    public MailRegisterPresentImpl(MailRegisterContract.View mView){
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
