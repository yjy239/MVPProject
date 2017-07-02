package testcom.onlineeducation.presenter.Detailed;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.HotPlayContract;
import testcom.onlineeducation.model.HotBean;

/**
 * Created by Administrator on 2017/6/19.
 */
public class HotPlayPresentImpl extends BasePresenter<HotPlayContract.HotView> implements HotPlayContract.HotPresent{

    private HotPlayContract.HotView mView;
    private List<HotBean> list = new ArrayList<>();

    public HotPlayPresentImpl(HotPlayContract.HotView mView){
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if(mView.getType() == 0){
            HotBean bean = new HotBean();
            bean.setName("haha");
            bean.setPrice("￥ 35");
            bean.setTitle("12313213");
            list.add(bean);
            HotBean bean2 = new HotBean();
            bean2.setName("haha");
            bean2.setPrice("￥ 35");
            bean2.setTitle("12313213");
            list.add(bean2);
            HotBean bean3 = new HotBean();
            bean3.setName("546");
            bean3.setPrice("￥ 235");
            bean3.setTitle("12313213");
            list.add(bean3);
            HotBean bean4 = new HotBean();
            bean4.setName("yjy");
            bean4.setPrice("￥ 135");
            bean4.setTitle("12313213");
            list.add(bean4);

            mView.initData(list);
        }else {
            List<String> list = new ArrayList<String>();
            mView.initLessonData(list);
        }

    }
}
