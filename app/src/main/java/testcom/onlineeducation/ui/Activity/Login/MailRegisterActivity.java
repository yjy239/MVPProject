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
import testcom.onlineeducation.Contract.MailRegisterContract;
import testcom.onlineeducation.DAO.UserDao;
import testcom.onlineeducation.R;
import testcom.onlineeducation.network.BaseCallBack;
import testcom.onlineeducation.presenter.Login.MailRegisterPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.utils.TextUtils;
import testcom.onlineeducation.utils.ToastUtil;

/**
 * Created by Administrator on 2017/6/20.
 */
public class MailRegisterActivity extends BaseActivity implements MailRegisterContract.View {
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
    @BindView(R.id.phoneregister)
    TextView phoneregister;
    @BindView(R.id.register)
    Button register;

    private MailRegisterPresentImpl mPresenter;
    private String emailid = "";

    @Override
    protected int setViewId() {
        return R.layout.mailregister_layout;
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
        titleName.setText(R.string.mailsign);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        mPresenter = new MailRegisterPresentImpl(this);
        mPresenter.attchView(this);
        mPresenter.start();
        super.onResume();
    }

    @Override
    public void setPresenter(MailRegisterPresentImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @OnClick({R.id.back, R.id.phoneregister,R.id.register,R.id.getcode})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.phoneregister:
                Intent i = new Intent(MailRegisterActivity.this, PhoneRegisterActivity.class);
                startActivity(i);
                break;
            case R.id.getcode:
                if(telnum.getText().toString().equals("")){
                    ToastUtil.showToast(MailRegisterActivity.this,TextUtils.getRString(R.string.tip3,MailRegisterActivity.this));
                    return;
                }
                UserDao.getEmailCode(telnum.getText().toString(), new BaseCallBack() {
                    @Override
                    public void success(Object data) {
                        emailid = data.toString();
                        Toast.makeText(MailRegisterActivity.this,
                                TextUtils.getRString(R.string.success,MailRegisterActivity.this),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(int errorCode, Object data) {
                        ToastUtil.showToast(MailRegisterActivity.this,data.toString());
                    }

                    @Override
                    public void failed(Object data) {

                    }
                });
                break;
            case R.id.register:
                if(telnum.getText().toString().equals("")){
                    Toast.makeText(MailRegisterActivity.this, TextUtils.getRString(R.string.tip3,MailRegisterActivity.this), Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.getText().toString().equals("")){
                    Toast.makeText(MailRegisterActivity.this, TextUtils.getRString(R.string.ptip2,MailRegisterActivity.this), Toast.LENGTH_SHORT).show();
                    return;
                }
                UserDao.Register(telnum.getText().toString(), password.getText().toString(), checkcode.getText().toString(),
                        "email", emailid, new BaseCallBack() {
                            @Override
                            public void success(Object data) {
                                Toast.makeText(MailRegisterActivity.this,
                                        TextUtils.getRString(R.string.rsucc,MailRegisterActivity.this),Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void failed(int errorCode, Object data) {
                                Toast.makeText(MailRegisterActivity.this,data.toString(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void failed(Object data) {

                            }
                        });
                break;
        }
    }

}
