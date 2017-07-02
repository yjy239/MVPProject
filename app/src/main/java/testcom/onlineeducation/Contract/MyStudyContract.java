package testcom.onlineeducation.Contract;

import java.util.List;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Detailed.MyStudyPresentImpl;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/22.
 */
public interface MyStudyContract {
    interface View extends BaseView<MyStudyPresentImpl>{
        void initData(List<Visitable> list);
    }

    interface Presenter{

    }
}
