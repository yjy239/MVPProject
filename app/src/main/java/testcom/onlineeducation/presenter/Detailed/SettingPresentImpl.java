package testcom.onlineeducation.presenter.Detailed;

import android.content.Context;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.SettingContract;
import testcom.onlineeducation.DAO.UserDao;
import testcom.onlineeducation.R;
import testcom.onlineeducation.network.BaseCallBack;
import testcom.onlineeducation.utils.ToastUtil;

/**
 * Created by Administrator on 2017/6/22.
 */
public class SettingPresentImpl extends BasePresenter<SettingContract.View> implements SettingContract.Presenter {

    SettingContract.View mView;
    private String[] mLanuages;
    private Context mContext;

    public SettingPresentImpl(SettingContract.View mView){
        this.mView = mView;
        mView.setPresenter(this);
        mContext = mView.getMyContext();
    }

    @Override
    public void start() {
        mLanuages = new String[]{getRString(R.string.chinese),getRString(R.string.english)};
        mView.initData(mLanuages);
    }

    public String getRString(int value){
        return mContext.getResources().getString(value);
    }

    @Override
    public void loginout() {
        UserDao.Loginout(new BaseCallBack() {
            @Override
            public void success(Object data) {
                mView.actionout();
            }

            @Override
            public void failed(int errorCode, Object data) {
                if(errorCode == 1028){
                    mView.actionout();
                }else {
                    ToastUtil.showToast(mContext,data.toString());
                }

            }

            @Override
            public void failed(Object data) {

            }
        });
    }
}
