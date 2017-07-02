package testcom.onlineeducation.Contract;

import java.util.List;


import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Home.MessagePresentImpl;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/16.
 */
public interface MessageContract {
    interface MessageView extends BaseView<MessagePresentImpl> {
        void initData(List<Visitable> list);
    }

    interface MessagePresent {

    }
}
