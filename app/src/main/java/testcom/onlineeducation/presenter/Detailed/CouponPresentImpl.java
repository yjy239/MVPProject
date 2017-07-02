package testcom.onlineeducation.presenter.Detailed;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.CouponContract;

/**
 * Created by Administrator on 2017/6/21.
 */
public class CouponPresentImpl extends BasePresenter<CouponContract.CouponView> implements CouponContract.CouponPresent{

    private CouponContract.CouponView mView;

    public CouponPresentImpl(CouponContract.CouponView mView){
        this.mView = mView;
    }

    @Override
    public void start() {

    }
}
