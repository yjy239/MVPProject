package testcom.onlineeducation.presenter;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.MyContract;

/**
 * Created by Administrator on 2017/6/19.
 */
public class MyPresenterImpl extends BasePresenter<MyContract.MyView> implements MyContract.MyPresenter{

    private MyContract.MyView mView;

    public MyPresenterImpl(MyContract.MyView mView){
        this.mView = mView;
        mView.setPresenter(this);
    }


    @Override
    public void start() {

    }
}
