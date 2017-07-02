package testcom.onlineeducation.Contract;

import android.content.Context;

import testcom.onlineeducation.BaseView;
import testcom.onlineeducation.presenter.Detailed.PersonPresentImpl;

/**
 * Created by Administrator on 2017/6/26.
 */
public interface PersonContract {
    interface View extends BaseView<PersonPresentImpl>{
        void initData(String[] mSex,String[] mLan);
        Context getmContext();
    }

    interface Presenter{

    }
}
