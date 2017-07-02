package testcom.onlineeducation.presenter.Detailed;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.MoneyDetailContract;
import testcom.onlineeducation.bean.MoneyDetailBean;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/28 0028.
 */
public class MoneyDetailPresentImpl extends BasePresenter<MoneyDetailContract.MoneyDetailView> implements MoneyDetailContract.MoneyDetailPresenter {
    private MoneyDetailContract.MoneyDetailView mView;
    private List<Visitable> list = new ArrayList<>();

    //构造函数设置present
    public MoneyDetailPresentImpl(MoneyDetailContract.MoneyDetailView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }


    public void start() {
        loadData();
    }

    //初始化数据
    public void loadData() {
        for (int i = 0; i < 23; i++) {
            MoneyDetailBean bean = new MoneyDetailBean();
            bean.setName("陈小杰-课程支付");
            bean.setTime("2017.06.28  14:00");
            bean.setValue("50");
            list.add(bean);
        }
        mView.initData(list);

    }
}
