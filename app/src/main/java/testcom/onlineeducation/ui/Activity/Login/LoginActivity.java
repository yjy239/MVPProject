package testcom.onlineeducation.ui.Activity.Login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.LoginContract;
import testcom.onlineeducation.DAO.UserDao;
import testcom.onlineeducation.R;
import testcom.onlineeducation.i.IEvent;
import testcom.onlineeducation.i.RxPost;
import testcom.onlineeducation.network.BaseCallBack;
import testcom.onlineeducation.presenter.Login.LoginPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.utils.Rx.RxBus;
import testcom.onlineeducation.utils.TextUtils;
import testcom.onlineeducation.utils.ToastUtil;

/**
 * Created by Administrator on 2017/6/20.
 */
public class LoginActivity extends BaseActivity implements LoginContract.LoginView {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.forgetpass)
    TextView forgetpass;
    @BindView(R.id.signup)
    TextView signup;
    @BindView(R.id.or)
    TextView or;
    @BindView(R.id.select_title)
    RelativeLayout selectTitle;

    private LoginPresentImpl loginPresent;

    @Override
    protected int setViewId() {
        return R.layout.activity_login_layout;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        loginPresent = new LoginPresentImpl(this);
        loginPresent.attchView(this);

        loginPresent.start();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if(loginPresent !=null){
            loginPresent.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void setPresenter(LoginPresentImpl presenter) {
        this.loginPresent = presenter;
    }

    @OnClick({R.id.signup,R.id.forgetpass,R.id.login})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.signup:
                Intent signup = new Intent(LoginActivity.this,PhoneRegisterActivity.class);
                startActivity(signup);
                break;
            case R.id.forgetpass:
                Intent forget = new Intent(LoginActivity.this,PhoneForgetActivity.class);
                startActivity(forget);
                break;
            case R.id.login:
                UserDao.Login(name.getText().toString(), password.getText().toString(), new BaseCallBack() {
                    @Override
                    public void success(Object data) {
                        ToastUtil.showToast(LoginActivity.this, TextUtils.getRString(R.string.success,LoginActivity.this));
                        RxBus.getDefault().post(new RxPost(IEvent.LOGIN));
                        finish();
                    }

                    @Override
                    public void failed(int errorCode, Object data) {
                        ToastUtil.showToast(LoginActivity.this,data.toString());
                    }

                    @Override
                    public void failed(Object data) {

                    }
                });

                break;
        }
    }

}
