package testcom.onlineeducation.ui.Activity.Detailed;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhouwei.library.CustomPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.carbswang.android.numberpickerview.library.NumberPickerView;
import testcom.onlineeducation.Contract.SettingContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.i.IEvent;
import testcom.onlineeducation.i.RxPost;
import testcom.onlineeducation.presenter.Detailed.SettingPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.utils.DataDeleteUtils;
import testcom.onlineeducation.utils.Rx.RxBus;
import testcom.onlineeducation.utils.TextUtils;
import testcom.onlineeducation.view.Adapter.EduAdapter;

/**
 * Created by Administrator on 2017/6/22.
 */
public class SettingActivity extends BaseActivity implements SettingContract.View, NumberPicker.Formatter,
        NumberPicker.OnValueChangeListener {

    SettingPresentImpl mPresenter;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.rl_lang)
    RelativeLayout rlLang;
    @BindView(R.id.size)
    TextView size;
    @BindView(R.id.rl_clear)
    RelativeLayout rlClear;
    @BindView(R.id.rl_update)
    RelativeLayout rlUpdate;
    @BindView(R.id.rl_about)
    RelativeLayout rlAbout;
    @BindView(R.id.rl_advice)
    RelativeLayout rlAdvice;
    @BindView(R.id.rl_contract)
    RelativeLayout rlContract;
    @BindView(R.id.rl_out)
    RelativeLayout rlOut;
    @BindView(R.id.root)
    LinearLayout root;
    @BindView(R.id.lang)
    TextView lang;

    private CustomPopWindow popupWindow;
    private CustomPopWindow popupWindow2;
    private CustomPopWindow popupWindow3;

    private NumberPickerView picker;
    private TextView sure;
    private TextView cancel;

    private String[] mLanuages;
    private CustomPopWindow popWindow;
    private List<String> list = new ArrayList<>();
    private int type = 0;


    @Override
    protected int setViewId() {
        return R.layout.activity_setting_layout;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
        titleName.setText(R.string.setting);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        mPresenter = new SettingPresentImpl(this);
        mPresenter.attchView(this);
        mPresenter.start();
    }


    @Override
    public void setPresenter(SettingPresentImpl presenter) {
        mPresenter = presenter;
    }

    private void showPopupWindow() {
        if (popupWindow != null)
            popupWindow.dissmiss();
        View contentView = LayoutInflater.from(SettingActivity.this).inflate(R.layout.poppicker_layout, null);
        //处理popWindow 显示内容
        handleData(contentView);

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

    private void handleData(View contentView) {
        picker = (NumberPickerView) contentView.findViewById(R.id.picker);
        picker.setDisplayedValues(mLanuages);
        picker.setMinValue(0);
        picker.setMaxValue(mLanuages.length - 1);
        cancel = (TextView)contentView.findViewById(R.id.cancel);
        sure = (TextView)contentView.findViewById(R.id.sure);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.sure:
                        lang.setText(picker.getContentByCurrValue());
                        popupWindow.dissmiss();
                        break;
                    case R.id.cancel:
                        popupWindow.dissmiss();
                        break;
                }
            }
        };
        sure.setOnClickListener(listener);
        cancel.setOnClickListener(listener);
    }




    @OnClick({R.id.rl_lang, R.id.rl_clear, R.id.rl_update, R.id.rl_about, R.id.rl_advice, R.id.rl_contract, R.id.rl_out, R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_lang:
                showPopupWindow();
                break;
            case R.id.rl_clear:
                if(popupWindow!=null){
                    popupWindow.dissmiss();
                }
                if(popupWindow3!=null){
                    popupWindow3.dissmiss();
                }
                type = 0;
                View contentView = LayoutInflater.from(SettingActivity.this).inflate(R.layout.edulist_layout, null);
                initListData(contentView);
               popupWindow2 =  new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(contentView)//显示的布局，还可以通过设置一个View
                        .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小,默认包裹内容
                        .setFocusable(true)//是否获取焦点，默认为ture
                        .setOutsideTouchable(false)//是否PopupWindow 以外触摸dissmiss
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.5f) // 控制亮度
                        .create()//创建PopupWindow
                        .showAtLocation(root, Gravity.CENTER, 0, 0);
                break;
            case R.id.rl_update:
                break;
            case R.id.rl_about:
                break;
            case R.id.rl_advice:
                break;
            case R.id.rl_contract:
                break;
            case R.id.rl_out:
                if(popupWindow!=null){
                    popupWindow.dissmiss();
                }
                if(popupWindow3!=null){
                    popupWindow2.dissmiss();
                }
                type = 1;
                View contentView2 = LayoutInflater.from(SettingActivity.this).inflate(R.layout.edulist_layout, null);
                initListData(contentView2);
                popupWindow3 = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(contentView2)//显示的布局，还可以通过设置一个View
                        .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小,默认包裹内容
                        .setFocusable(true)//是否获取焦点，默认为ture
                        .setOutsideTouchable(false)//是否PopupWindow 以外触摸dissmiss
                        .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                        .setBgDarkAlpha(0.5f) // 控制亮度
                        .create()//创建PopupWindow
                        .showAtLocation(root, Gravity.CENTER, 0, 0);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void initListData(View contentView) {
        RecyclerView edulist = (RecyclerView)contentView.findViewById(R.id.edulist);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        edulist.setLayoutManager(manager);
        list.clear();
        list.add(TextUtils.getRString(R.string.sure,SettingActivity.this));
        list.add(TextUtils.getRString(R.string.cancel,SettingActivity.this));
        EduAdapter adapter = new EduAdapter(SettingActivity.this,list);
        edulist.setAdapter(adapter);
        adapter.setOnItemClickLisenter(new EduAdapter.ItemOnClick() {
            @Override
            public void onItemClick(View v, int position) {
                if(position == 0){
                    if(type == 1){
                        popupWindow3.dissmiss();
                        mPresenter.loginout();
                    }else if(type == 0){
                        DataDeleteUtils.cleanInternalCache(SettingActivity.this);
                    }
                }else if(position ==1){
                    popupWindow3.dissmiss();
                }
            }
        });
    }

    @Override
    public String format(int value) {
        return mLanuages[value];
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        lang.setText(mLanuages[newVal]);
    }

    @Override
    public void initData(String[] mData) {
        mLanuages = mData;
    }

    @Override
    public Context getMyContext() {
        return SettingActivity.this;
    }

    @Override
    public void actionout() {
        RxBus.getDefault().post(new RxPost(IEvent.LOGINOUT));
        finish();
    }

}
