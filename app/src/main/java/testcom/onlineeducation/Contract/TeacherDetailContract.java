package testcom.onlineeducation.Contract;

import java.util.List;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Detailed.TeacherDetailPresentImpl;

/**
 * Created by Administrator on 2017/6/23.
 */
public interface TeacherDetailContract {
    interface View extends BaseView<TeacherDetailPresentImpl>{
        void initData(List<String> list);
    }

    interface Presenter{

    }
}
