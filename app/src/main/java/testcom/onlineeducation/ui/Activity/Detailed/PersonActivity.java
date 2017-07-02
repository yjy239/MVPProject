package testcom.onlineeducation.ui.Activity.Detailed;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhouwei.library.CustomPopWindow;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import testcom.onlineeducation.Contract.PersonContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.Detailed.PersonPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.utils.Bimp;

/**
 * Created by Administrator on 2017/6/26.
 */
public class PersonActivity extends BaseActivity implements PersonContract.View, NumberPicker.Formatter,
        NumberPicker.OnValueChangeListener {

    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.rl_username)
    RelativeLayout rlUsername;
    @BindView(R.id.language)
    TextView language;
    @BindView(R.id.rl_language)
    RelativeLayout rlLanguage;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.root)
    LinearLayout root;
    private PersonPresentImpl mPresenter;
    private CustomPopWindow popupWindow;
    private int type = 0;
    private NumberPicker picker;
    private String[] mSex;
    private String[] mLan;
    private ArrayList<String> piclist = new ArrayList<>();
    private static final int REQUEST_IMAGE = 2;

    @Override
    protected int setViewId() {
        return R.layout.personactivity_layout;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
        titleName.setText(R.string.minfo);
    }

    @Override
    protected void initEvent() {
        mPresenter = new PersonPresentImpl(this);
        mPresenter.attchView(this);
        mPresenter.start();
    }

    @Override
    protected void init() {

    }

    @Override
    public void setPresenter(PersonPresentImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initData(String[] mSex, String[] mLan) {
        this.mSex = mSex;
        this.mLan = mLan;
    }

    @Override
    public Context getmContext() {
        return PersonActivity.this;
    }

    private void showPopupWindow() {
        if (popupWindow != null)
            popupWindow.dissmiss();
        View contentView = LayoutInflater.from(PersonActivity.this).inflate(R.layout.poppicker_layout, null);
        //处理popWindow 显示内容
        switch (type) {
            case 0:
                handleData(contentView, mSex);
                break;
            case 1:
                handleData(contentView, mLan);
                break;

        }

        popupWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)//显示的布局，还可以通过设置一个View
                .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小,默认包裹内容
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOutsideTouchable(false)//是否PopupWindow 以外触摸dissmiss
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.5f) // 控制亮度
                .create()//创建PopupWindow
                .showAtLocation(root, Gravity.CENTER, 0, 0);
        //显示PopupWindow
    }

    private void handleData(View contentView, String[] values) {
        picker = (NumberPicker) contentView.findViewById(R.id.picker);
        picker.setDisplayedValues(values);
        picker.setMinValue(0);
        picker.setMaxValue(values.length - 1);
        picker.setOnValueChangedListener(this);
        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }


    @OnClick({R.id.back, R.id.rl_head, R.id.rl_sex, R.id.rl_username, R.id.rl_language})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.rl_head:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                }
                MultiImageSelector.create(PersonActivity.this)
                        .showCamera(true)
                        .count(1)
                        .single()
                        .multi()
                        .origin(piclist)
                        .start(PersonActivity.this,REQUEST_IMAGE);
                break;
            case R.id.rl_sex:
                type = 0;
                showPopupWindow();
                break;
            case R.id.rl_username:

                break;
            case R.id.rl_language:
                type = 1;
                showPopupWindow();
                break;
        }
    }

    @Override
    public String format(int value) {
        if(type == 0){
            return mSex[value];
        }else if(type == 1){
            return mLan[value];
        }else {
            return mSex[value];
        }
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        if(type == 0){
            sex.setText(mSex[newVal]);
        }else if(type == 1){
            language.setText(mLan[newVal]);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                piclist = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for(String p: piclist){
                    sb.append(p);
                    sb.append("\n");
                }
                Log.e("piclist",sb.toString());
                try{
                    Bitmap bitmap = Bimp.revitionImageSize(piclist.get(0));
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] bytes=baos.toByteArray();
                    Glide.with(this)
                            .load(bytes)
                            .into(head);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

}
