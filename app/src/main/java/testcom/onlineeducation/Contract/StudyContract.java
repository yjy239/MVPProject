package testcom.onlineeducation.Contract;

import java.util.List;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.StudyPresentImpl;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface StudyContract {
    interface StudyView extends BaseView<StudyPresentImpl> {
        void initData(List<Visitable> list, int index);
        void showMsg();
    }

    interface StudyPresent {

    }
}
