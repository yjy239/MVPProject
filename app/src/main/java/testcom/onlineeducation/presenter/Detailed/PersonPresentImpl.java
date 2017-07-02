package testcom.onlineeducation.presenter.Detailed;

import android.content.Context;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.PersonContract;
import testcom.onlineeducation.R;

/**
 * Created by Administrator on 2017/6/26.
 */
public class PersonPresentImpl extends BasePresenter<PersonContract.View> implements PersonContract.Presenter {

    private PersonContract.View mView;
    private String[] mSex ;
    private String[] mLanuages;
    private Context mContext;

    public PersonPresentImpl(PersonContract.View mView){
        this.mView = mView;
        mView.setPresenter(this);
        mContext = mView.getmContext();
    }

    @Override
    public void start() {
        mLanuages = new String[]{getRString(R.string.chinese),getRString(R.string.english)};
        mSex = new String[]{getRString(R.string.man),getRString(R.string.woman)};
        mView.initData(mSex,mLanuages);
    }

    public String getRString(int value){
        return mContext.getResources().getString(value);
    }
}
