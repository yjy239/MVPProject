package testcom.onlineeducation.ui.Activity.Login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.MailForgetContract;
import testcom.onlineeducation.DAO.UserDao;
import testcom.onlineeducation.R;
import testcom.onlineeducation.network.BaseCallBack;
import testcom.onlineeducation.presenter.Login.MailForgetPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.utils.TextUtils;
import testcom.onlineeducation.utils.ToastUtil;

/**
 * Created by Administrator on 2017/6/20.
 */
public class MailForgetActivity extends BaseActivity implements MailForgetContract.View {


    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.telnum)
    EditText telnum;
    @BindView(R.id.code)
    TextView code;
    @BindView(R.id.checkcode)
    EditText checkcode;
    @BindView(R.id.getcode)
    TextView getcode;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.phonefind)
    TextView phonefind;
    @BindView(R.id.sure)
    Button sure;
    private MailForgetPresentImpl mPresenter;
    private String emailid = "";

    @Override
    protected int setViewId() {
        return R.layout.mailforget_activity;
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
        titleName.setText(R.string.forgetpass);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        mPresenter = new MailForgetPresentImpl(this);
        mPresenter.attchView(this);
        mPresenter.start();
        super.onResume();
    }

    @Override
    public void setPresenter(MailForgetPresentImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }


    @OnClick({R.id.back, R.id.phonefind})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.phonefind:
                Intent i = new Intent(MailForgetActivity.this, PhoneForgetActivity.class);
                startActivity(i);
                break;
            case R.id.getcode:
                if (telnum.getText().toString().equals("")) {
                    ToastUtil.showToast(MailForgetActivity.this, TextUtils.getRString(R.string.tip3, MailForgetActivity.this));
                    return;
                }
                UserDao.getEmailCode(telnum.getText().toString(), new BaseCallBack() {
                    @Override
                    public void success(Object data) {
                        emailid = data.toString();
                        Toast.makeText(MailForgetActivity.this,
                                TextUtils.getRString(R.string.success, MailForgetActivity.this), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(int errorCode, Object data) {
                        ToastUtil.showToast(MailForgetActivity.this, data.toString());
                    }

                    @Override
                    public void failed(Object data) {

                    }
                });
                break;
            case R.id.sure:
                if(telnum.getText().toString().equals("")){
                    Toast.makeText(MailForgetActivity.this, TextUtils.getRString(R.string.tip3,MailForgetActivity.this), Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.getText().toString().equals("")){
                    Toast.makeText(MailForgetActivity.this, TextUtils.getRString(R.string.ptip2,MailForgetActivity.this), Toast.LENGTH_SHORT).show();
                    return;
                }
                UserDao.ForgetPassword(telnum.getText().toString(), password.getText().toString(), checkcode.getText().toString(),
                        "email", emailid, new BaseCallBack() {
                            @Override
                            public void success(Object data) {
                                Toast.makeText(MailForgetActivity.this,
                                        TextUtils.getRString(R.string.success,MailForgetActivity.this),Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void failed(int errorCode, Object data) {
                                Toast.makeText(MailForgetActivity.this,data.toString(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void failed(Object data) {

                            }
                        });
                break;
        }
    }

}
