package testcom.onlineeducation.presenter.Home;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.MoreHotLessonContract;
import testcom.onlineeducation.bean.HomeHotLessonBean;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/22 0022.
 */
public class MoreHotLessonPresentImpl extends BasePresenter<MoreHotLessonContract.MoreHotLessonView> implements MoreHotLessonContract.MoreHotLessonPresenter {
    private MoreHotLessonContract.MoreHotLessonView mView;
    private List<Visitable> list = new ArrayList<>();

    //构造函数设置present
    public MoreHotLessonPresentImpl(MoreHotLessonContract.MoreHotLessonView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }


    public void start() {
        loadData();
    }

    //初始化数据
    public void loadData() {
        for (int i = 0; i < 23; i++) {
            HomeHotLessonBean bean = new HomeHotLessonBean();
            bean.setAuthor("老周");
            bean.setMoney(i*10+"");
            bean.setName("《猫和老鼠》");
            list.add(bean);
        }
        mView.initData(list);

    }
}
