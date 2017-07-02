package testcom.onlineeducation.Contract;

import java.util.List;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Study.StudyPicPresentImpl;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface StudyPicContract {
    interface StudyPicView extends BaseView<StudyPicPresentImpl> {
        void initData(List<Visitable> list);
        void showMsg();
    }

    interface StudyPicPresent {

    }
}
