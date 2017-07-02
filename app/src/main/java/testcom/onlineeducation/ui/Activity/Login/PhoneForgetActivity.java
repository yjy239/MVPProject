package testcom.onlineeducation.ui.Activity.Login;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import testcom.onlineeducation.Contract.PhoneForgetContract;
import testcom.onlineeducation.DAO.UserDao;
import testcom.onlineeducation.R;
import testcom.onlineeducation.network.BaseCallBack;
import testcom.onlineeducation.presenter.Login.PhoneForgetPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.utils.TextUtils;
import testcom.onlineeducation.utils.ToastUtil;

/**
 * Created by Administrator on 2017/6/20.
 */
public class PhoneForgetActivity extends BaseActivity implements PhoneForgetContract.View {
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.countryid)
    TextView countryid;
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
    @BindView(R.id.mailfind)
    TextView mailfind;
    @BindView(R.id.sure)
    Button sure;

    private PhoneForgetPresentImpl mPresenter;
    public Handler mHandler;
    public static int TIMESTATUS = 0;
    public static int TIMESTOP = 1;
    public static int TIMEREADY = 0;
    public static int TIMERUNNING = 2;
    public static int SECONDS = 59;
    public boolean send = false;

    // 默认使用中国区号
    private final String DEFAULT_COUNTRY_ID = "42";
    private String countryCode = "86";
    private final static int RESULT_COMPLETE = 0;
    private final static int SUBMIT_VERIFICATION_CODE = 1;
    private final static int GET_VERIFICATION_CODE = 2;
    private final static int EVENT_GET_SUPPORTED_COUNTRIES = 4;

    public EventHandler eventhandler = new EventHandler(){
        @Override
        public void afterEvent(int event, int result, Object data) {

            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                handler.sendEmptyMessage(RESULT_COMPLETE);
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    handler.sendEmptyMessage(SUBMIT_VERIFICATION_CODE);
                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                    //获取验证码成功
                    handler.sendEmptyMessage(GET_VERIFICATION_CODE);
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
                    //返回支持发送验证码的国家列表
                    Log.e("mobile",data.toString());
                    handler.sendEmptyMessage(EVENT_GET_SUPPORTED_COUNTRIES);
                }
            }else{
                ((Throwable)data).printStackTrace();
            }
        }
    };

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case RESULT_COMPLETE:
                    Log.e("RESULT_COMPLETE","RESULT_COMPLETE");
                    Toast.makeText(PhoneForgetActivity.this,"验证码回调成功",Toast.LENGTH_SHORT).show();
                    break;
                case SUBMIT_VERIFICATION_CODE:
                    Log.e("SUBMIT","SUBMIT_VERIFICATION_CODE");
                    Toast.makeText(PhoneForgetActivity.this,"提交验证码",Toast.LENGTH_SHORT).show();
                    break;
                case GET_VERIFICATION_CODE:
                    Log.e("GET","GET_VERIFICATION_CODE");
                    Toast.makeText(PhoneForgetActivity.this,"获取验证码",Toast.LENGTH_SHORT).show();
                    break;
                case  EVENT_GET_SUPPORTED_COUNTRIES:
                    Log.e("COUNTRIES","EVENT_GET_SUPPORTED_COUNTRIES");
                    break;
            }
        }
    };

    @Override
    protected int setViewId() {
        return R.layout.phonefoget_activity;
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
        mHandler = new Handler();
        SMSSDK.registerEventHandler(eventhandler);
        SMSSDK.getSupportedCountries();
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        mPresenter = new PhoneForgetPresentImpl(this);
        mPresenter.attchView(this);
        mPresenter.start();
        super.onResume();
    }

    @Override
    public void setPresenter(PhoneForgetPresentImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterEventHandler(eventhandler);
        if (mPresenter != null) {
            mPresenter.detachView();
        }

        if (mHandler != null) {
            mHandler.removeCallbacks(timerun);
        }
        super.onDestroy();
    }

    @OnClick({R.id.back, R.id.mailfind, R.id.getcode,R.id.sure})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.mailfind:
                Intent i = new Intent(PhoneForgetActivity.this, MailForgetActivity.class);
                startActivity(i);
                break;
            case R.id.getcode:
                if (TIMESTATUS == TIMEREADY || TIMESTATUS == TIMESTOP) {
                    mHandler.postDelayed(timerun, 1000);
                    if(!send){
                        SMSSDK.getVerificationCode(countryCode,telnum.getText().toString());
                        send = true;
                    }
                } else {
                    Toast.makeText(PhoneForgetActivity.this, "请等待短信", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sure:
                if(telnum.getText().toString().equals("")){
                    Toast.makeText(PhoneForgetActivity.this, TextUtils.getRString(R.string.prtip,PhoneForgetActivity.this), Toast.LENGTH_SHORT).show();
                    return;
                }
                if(telnum.getText().toString().length() < 11){
                    ToastUtil.showToast(PhoneForgetActivity.this, TextUtils.getRString(R.string.tip,PhoneForgetActivity.this));
                    return;
                }

                if(password.getText().toString().equals("")){
                    Toast.makeText(PhoneForgetActivity.this, TextUtils.getRString(R.string.ptip2,PhoneForgetActivity.this), Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.getText().toString().length() <6 || password.getText().toString().length() > 20){
                    ToastUtil.showToast(PhoneForgetActivity.this, TextUtils.getRString(R.string.tip2,PhoneForgetActivity.this));
                    return;
                }

                UserDao.ForgetPassword(telnum.getText().toString(), password.getText().toString(), checkcode.getText().toString(),
                        "mobile", "", new BaseCallBack() {
                            @Override
                            public void success(Object data) {
                                Toast.makeText(PhoneForgetActivity.this,
                                        TextUtils.getRString(R.string.success,PhoneForgetActivity.this),Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void failed(int errorCode, Object data) {
                                Toast.makeText(PhoneForgetActivity.this,data.toString(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void failed(Object data) {

                            }
                        });

                break;
        }
    }

    public Runnable timerun = new Runnable() {
        @Override
        public void run() {
            //计时停止，倒数开始
            if (TIMESTATUS != TIMESTOP) {
                TIMESTATUS = TIMERUNNING;
                if (SECONDS >= 0) {
                    getcode.setText("倒计时" + SECONDS);
                    SECONDS--;
                    mHandler.postDelayed(this, 1000);
                } else {
                    getcode.setText("重新获取");
                    TIMESTATUS = TIMEREADY;
                    mHandler.removeCallbacks(this);
                    SECONDS = 59;
                    send = false;
                }


            }
        }
    };

}
