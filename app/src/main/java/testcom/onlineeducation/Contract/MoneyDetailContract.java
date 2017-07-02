package testcom.onlineeducation.Contract;

import java.util.List;


import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Detailed.MoneyDetailPresentImpl;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/14.
 * creator : yjy
 */
public interface MoneyDetailContract {

    //此处写处理view的事件
    interface MoneyDetailView extends BaseView<MoneyDetailPresentImpl> {
        void initData(List<Visitable> list);
    }


    //这里可以写presenter处理事件
   interface  MoneyDetailPresenter  {

    }

}
