package testcom.onlineeducation.Contract;

import android.content.Context;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Detailed.ApplyPresentImpl;

/**
 * Created by Administrator on 2017/6/22.
 */
public interface ApplyContract {
    interface View extends BaseView<ApplyPresentImpl>{
        void initData(String[] mData1,String[] mData2,String[] mData3,String[]mData4);
        Context getMyContext();
    }

    interface Presenter{
    }
}
