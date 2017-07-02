package testcom.onlineeducation.Contract;

import java.util.List;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Study.StudyTextPresentImpl;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface StudyTextContract {
    interface StudyTextView extends BaseView<StudyTextPresentImpl> {
        void initData(List<Visitable> list);
        void showMsg();
    }

    interface StudyTextPresent {

    }
}
