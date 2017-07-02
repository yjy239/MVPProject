package testcom.onlineeducation.ui.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;

import testcom.onlineeducation.utils.ToastUtil;

/**
 * Created by Administrator on 2017/6/14.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static final String MESSAGE_RECEIVED_ACTION = "com.yidu.sevensecondmall.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    private MessagesReceiver mMessageReceiver;

    private AlertDialog show;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setViewId());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        registerMessageReceiver();
        findViews();
        handlerSaveInstanceState(savedInstanceState);
        initEvent();
        init();
//        loadData();
        hyalinize();
    }


    /***
     * 处理saveInstanceState
     ***/
    protected void handlerSaveInstanceState(Bundle savedInstanceState) {

    }


    /**
     * 设置布局id
     *
     * @return
     */
    protected abstract int setViewId();

    /**
     * 初始化控件
     */
    protected abstract void findViews();

    /**
     * 初始化控件的点击事件
     */
    protected abstract void initEvent();

    /**
     * 初始化数据
     */
    protected abstract void init();

//    /**
//     * 加载数据
//     */
//    protected abstract void loadData();


    /**
     * 沉浸式
     **/
    private void hyalinize() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    //隐藏listview
    public boolean isHideListView(View v) {
        if (v != null && v instanceof ListView) {
            if (v.getVisibility() == View.VISIBLE) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();

        //config.setToDefaults();
        config.fontScale = 1.0f;
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    public void registerMessageReceiver() {
        mMessageReceiver = new MessagesReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessagesReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                String msgtitle = "";

                if (intent.hasExtra("title")) {
                    msgtitle = intent.getStringExtra("title");
                }

                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!TextUtils.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
//                if (msgtitle.equals("异地登陆")) {
//                    if (LoginUtils.isLogin()) {
//                        if (!SystemUtil.getSharedBoolean("ReceiveLoginError", false)) {
//                            show = new AlertDialog.Builder(BaseActivity.this).setTitle("异地登录提醒")
//                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            dialog.dismiss();
//                                            LoginUtils.setUserId("");
//                                            LoginUtils.setToken("");
//                                            LoginUtils.setIsLogin(false);
//                                            EventBus.getDefault().post(new LoginEvent(IEventTag.LOGINOUT));
//                                            SystemUtil.setSharedBoolean("ReceiveLoginError", true);
//                                        }
//                                    }).setCancelable(false).show();
//                        }
//                    }
//                } else {
//                }

//                if ("Xiaomi".equals(Build.MANUFACTURER)) {//小米手机
//                    Log.e("机型", "小米手机");
//                    requestPermission();
//                } else if ("Meizu".equals(Build.MANUFACTURER)) {//魅族手机
//                    Log.e("机型", "魅族手机");
//                    requestPermission();
//                } else {//其他手机
//                    Log.e("机型", "其他手机");
//                    if (Build.VERSION.SDK_INT >= 23) {
//                        if (!Settings.canDrawOverlays(MainsActivity.this)) {
//                            Intent intent3 = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                            startActivityForResult(intent3, 12);
//                        } else {
//                            createFloatView(msgtitle,userid,teamid,matchid);
//                        }
//                    } else {
//                        createFloatView(msgtitle,userid,teamid,matchid);
//                    }
//                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    protected void onResume() {
//        if (SystemUtil.getSharedBoolean("ReceiveLoginError", false)) {
//            if (show != null) {
//                show.dismiss();
//            }
//        }
        super.onResume();
    }

    /**show a short toast*/
    public void showShortToast(String str){
        ToastUtil.showToast(BaseActivity.this, str);
    }

}
