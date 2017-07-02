package testcom.onlineeducation.Contract;

import java.util.List;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.LessonPresenterImpl;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface LessonContract {
   
    interface LessonView extends BaseView<LessonPresenterImpl> {
        void loadingdata();
        void initData(List<String> list);
    }

    interface LessonPresent{

    }
}
