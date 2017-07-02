package testcom.onlineeducation.presenter.Detailed;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.MyLessonContract;
import testcom.onlineeducation.bean.MyStudyBean;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/23.
 */
public class MyLessonPresentImpl extends BasePresenter<MyLessonContract.View> implements MyLessonContract.Presenter{

    private MyLessonContract.View mView;
    private List<Visitable> list = new ArrayList<>();

    public MyLessonPresentImpl(MyLessonContract.View mView){
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        for(int i = 0;i<5;i++){
            MyStudyBean bean = new MyStudyBean();
            bean.setAuthor("小李");
            bean.setImg("213");
            bean.setName("英语教学");
            bean.setPrice("￥35");
            bean.setStatus("已完成");
            list.add(bean);
        }
        mView.initData(list);
    }
}
