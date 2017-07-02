package testcom.onlineeducation.Contract;

import java.util.List;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.model.HotBean;
import testcom.onlineeducation.presenter.Detailed.HotPlayPresentImpl;

/**
 * Created by Administrator on 2017/6/19.
 */
public interface HotPlayContract {
    interface HotView extends BaseView<HotPlayPresentImpl>{
        void initData(List<HotBean> list);
        int getType();
        void initLessonData(List<String>list);
    }

    interface HotPresent{

    }
}
