package testcom.onlineeducation.Contract;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Detailed.CouponPresentImpl;

/**
 * Created by Administrator on 2017/6/21.
 */
public interface CouponContract {
    interface CouponView extends BaseView<CouponPresentImpl>{
        void initData();
    }

    interface CouponPresent{

    }
}
