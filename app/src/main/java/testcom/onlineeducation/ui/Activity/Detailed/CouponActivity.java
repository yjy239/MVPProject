package testcom.onlineeducation.ui.Activity.Detailed;

import testcom.onlineeducation.Contract.CouponContract;
import testcom.onlineeducation.presenter.Detailed.CouponPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;

/**
 * Created by Administrator on 2017/6/21.
 */
public class CouponActivity extends BaseActivity implements CouponContract.CouponView{

    private CouponPresentImpl mPresenter;

    @Override
    protected int setViewId() {
        return 0;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setPresenter(CouponPresentImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        mPresenter = new CouponPresentImpl(this);
        mPresenter.attchView(this);
        mPresenter.start();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if(mPresenter!=null){
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}
