package testcom.onlineeducation.presenter.Detailed;

import android.content.Context;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.ApplyContract;
import testcom.onlineeducation.R;

/**
 * Created by Administrator on 2017/6/22.
 */
public class ApplyPresentImpl extends BasePresenter<ApplyContract.View> implements ApplyContract.Presenter{

    ApplyContract.View mView;
    private String[] mSex ;
    private String[] mLanuages;
    private String[] mEducation;
    private String[] mExp;
    private Context mContext;

    public ApplyPresentImpl(ApplyContract.View mView){
        this.mView = mView;
        mView.setPresenter(this);
        mContext = mView.getMyContext();
    }

    @Override
    public void start() {
        mLanuages = new String[]{getRString(R.string.chinese),getRString(R.string.english)};
        mSex = new String[]{getRString(R.string.man),getRString(R.string.woman)};
        mEducation = new String[]{getRString(R.string.collage),getRString(R.string.university),getRString(R.string.Master),
                getRString(R.string.Doctor)};
        mExp = new String[]{"1","2","3","4","5","6","7","8","9","10","11"};
        mView.initData(mLanuages,mSex,mEducation,mExp);
    }

    public String getRString(int value){
        return mContext.getResources().getString(value);
    }
}
