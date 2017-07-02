package testcom.onlineeducation.Contract;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.MyPresenterImpl;

/**
 * Created by Administrator on 2017/6/19.
 */
public interface MyContract {

    interface MyView extends BaseView<MyPresenterImpl>{

    }

    interface MyPresenter{

    }
}
