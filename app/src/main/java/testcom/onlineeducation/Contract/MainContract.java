package testcom.onlineeducation.Contract;

import java.util.List;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.model.HotBean;
import testcom.onlineeducation.presenter.MainPresentImpl;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/14.
 * creator : yjy
 */
public interface MainContract {

    //此处写处理view的事件
    interface MainView extends BaseView<MainPresentImpl>{
        void initData(List<Visitable> list);
        void loadingData();
    }


    //这里可以写presenter处理事件
   interface  MainPresenter  {

    }

}
