package testcom.onlineeducation.ui.Fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.MyContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.i.IEvent;
import testcom.onlineeducation.i.RxPost;
import testcom.onlineeducation.presenter.MyPresenterImpl;
import testcom.onlineeducation.ui.Activity.Detailed.ApplyTeacherActivity;
import testcom.onlineeducation.ui.Activity.Detailed.MyLessonActivity;
import testcom.onlineeducation.ui.Activity.Detailed.MyStudyActivity;
import testcom.onlineeducation.ui.Activity.Detailed.MyWalletActivity;
import testcom.onlineeducation.ui.Activity.Detailed.PersonActivity;
import testcom.onlineeducation.ui.Activity.Detailed.SettingActivity;
import testcom.onlineeducation.ui.Activity.Login.LoginActivity;
import testcom.onlineeducation.utils.LoginUtils;
import testcom.onlineeducation.utils.Rx.HandlerThread;
import testcom.onlineeducation.utils.Rx.RxBus;
import testcom.onlineeducation.utils.Rx.Subcuribe;

/**
 * Created by Administrator on 2017/6/16.
 * Created by Administrator on 2017/6/19.
 */
public class MyFragment extends BaseFragment implements MyContract.MyView {

    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.lag)
    TextView lag;
    @BindView(R.id.rl_apply)
    LinearLayout rlApply;
    @BindView(R.id.rl_study)
    LinearLayout rlStudy;
    @BindView(R.id.rl_lesson)
    LinearLayout rlLesson;
    @BindView(R.id.rl_share)
    LinearLayout rlShare;
    @BindView(R.id.rl_setting)
    LinearLayout rlSetting;
    @BindView(R.id.personinfo)
    ImageView personinfo;
    private MyPresenterImpl mPresenterImpl;

    public MyFragment(){

    }

    @Override
    protected int setViewId() {
        RxBus.getDefault().register(this);
        return R.layout.fragment_my;
    }

    @Override
    protected void findViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initEvent() {
    }



    @Override
    protected void init() {
        if(LoginUtils.isLogin()){
            nickname.setText("登录");
            LoginUtils.setIsLogin(true);
        }else {
            nickname.setText("未登录");
            LoginUtils.setIsLogin(false);
        }
    }


    @Override
    public void setPresenter(MyPresenterImpl presenter) {
        this.mPresenterImpl = presenter;
    }


    public void onResume() {
        mPresenterImpl = new MyPresenterImpl(this);
        mPresenterImpl.attchView(this);
        mPresenterImpl.start();
        super.onResume();
    }

    public void onDestroy() {
        if (mPresenterImpl != null) {
            mPresenterImpl.detachView();
        }
        RxBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subcuribe({HandlerThread.MAIN})
    public void handler(RxPost ev){
        switch (ev.getTag()){
            case IEvent.LOGIN:
                nickname.setText("登录");
                LoginUtils.setIsLogin(true);
                break;
            case IEvent.LOGINOUT:
                nickname.setText("未登录");
                LoginUtils.setIsLogin(false);
                break;
        }
    }



    @OnClick({R.id.head, R.id.rl_apply, R.id.rl_study, R.id.rl_lesson, R.id.rl_share, R.id.rl_setting,R.id.personinfo, R.id.ll_my_wallet})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.head:
                if(!LoginUtils.isLogin()){
                    Intent login = new Intent(getActivity(), LoginActivity.class);
                    startActivity(login);
                }
                break;
            case R.id.rl_apply:
                if(LoginUtils.isLogin()){
                    Intent apply = new Intent(getActivity(), ApplyTeacherActivity.class);
                    startActivity(apply);
                }
                break;
            case R.id.rl_study:
                if(LoginUtils.isLogin()){
                    Intent study = new Intent(getActivity(), MyStudyActivity.class);
                    startActivity(study);
                }
                break;
            case R.id.rl_lesson:
                if(LoginUtils.isLogin()){
                    Intent lesson = new Intent(getActivity(), MyLessonActivity.class);
                    startActivity(lesson);
                }

                break;
            case R.id.rl_share:
                break;
            case R.id.rl_setting:
                if (LoginUtils.isLogin()) {
                    Intent i = new Intent(getActivity(), SettingActivity.class);
                    startActivity(i);
                }

                break;
            case R.id.personinfo:
                if(LoginUtils.isLogin()){
                    Intent info = new Intent(getActivity(), PersonActivity.class);
                    startActivity(info);
                }
                break;
            case R.id.ll_my_wallet:
                intent = new Intent(getActivity(), MyWalletActivity.class);
                startActivity(intent);
                break;
        }
    }

}
