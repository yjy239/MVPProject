package testcom.onlineeducation.Contract;

import java.util.List;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Detailed.MyLessonPresentImpl;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/23.
 */
public interface MyLessonContract {
    interface View extends BaseView<MyLessonPresentImpl>{
        void initData(List<Visitable> list);
    }

    interface Presenter{

    }
}
