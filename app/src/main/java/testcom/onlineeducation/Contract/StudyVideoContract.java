package testcom.onlineeducation.Contract;

import java.util.List;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Study.StudyVideoPresentImpl;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface StudyVideoContract {
    interface StudyVideoView extends BaseView<StudyVideoPresentImpl> {
        void initData(List<Visitable> list);
    }

    interface StudyVideoPresent {

    }
}
