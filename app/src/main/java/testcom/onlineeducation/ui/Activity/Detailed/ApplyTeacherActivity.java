package testcom.onlineeducation.ui.Activity.Detailed;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhouwei.library.CustomPopWindow;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.carbswang.android.numberpickerview.library.NumberPickerView;
import me.nereo.multi_image_selector.MultiImageSelector;
import testcom.onlineeducation.Contract.ApplyContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.Detailed.ApplyPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.utils.Bimp;
import testcom.onlineeducation.view.Adapter.EduAdapter;


/**
 * Created by Administrator on 2017/6/22.
 */
public class ApplyTeacherActivity extends BaseActivity implements ApplyContract.View{


    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.lanuage)
    TextView lanuage;
    @BindView(R.id.education)
    TextView education;
    @BindView(R.id.experience)
    TextView experience;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.rl_sex)
    RelativeLayout rlSex;
    @BindView(R.id.rl_lanuage)
    RelativeLayout rlLanuage;
    @BindView(R.id.rl_edu)
    RelativeLayout rlEdu;
    @BindView(R.id.rl_ex)
    RelativeLayout rlEx;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.root)
    LinearLayout root;
    private ApplyPresentImpl mPresenter;
    private int type = 0;
    private CustomPopWindow popupWindow;
    private CustomPopWindow popupWindow2;
    private String[] mSex;
    private String[] mLan;
    private String[] mEdu;
    private String[] mExp;
    private static final int REQUEST_IMAGE = 2;
    private ArrayList<String> piclist = new ArrayList<>();
    private RecyclerView edulist;
    private EduAdapter adapter;
    List<String> list = new ArrayList<>();

    @Override
    protected int setViewId() {
        return R.layout.applyactivity_layout;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
        titleName.setText(R.string.applytoteacher);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {

        mPresenter = new ApplyPresentImpl(this);
        mPresenter.attchView(this);
        mPresenter.start();
    }





    @Override
    public void setPresenter(ApplyPresentImpl presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    private NumberPickerView picker;
    private TextView cancel;
    private TextView sure;
    private boolean unlocked = false;

    private void showPopupWindow() {
        if(popupWindow!=null)
            popupWindow.dissmiss();
        if(popupWindow2 != null)
            popupWindow2.dissmiss();
        View contentView = LayoutInflater.from(ApplyTeacherActivity.this).inflate(R.layout.poppicker_layout, null);
        //处理popWindow 显示内容
        switch(type){
            case 0:
                handleData(contentView,mSex);
                break;
            case 1:
                handleData(contentView,mLan);
                break;
            case 2:
                handleData(contentView,mEdu);
                break;
            case 3:
                handleData(contentView,mExp);
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

    private void handleData(View contentView,String[] values) {
        picker = (NumberPickerView) contentView.findViewById(R.id.picker);
        picker.setDisplayedValues(values);
        picker.setMinValue(0);
        picker.setMaxValue(values.length-1);
        cancel = (TextView)contentView.findViewById(R.id.cancel);
        sure = (TextView)contentView.findViewById(R.id.sure);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.sure:
                        if(type == 0){
                            sex.setText(picker.getContentByCurrValue());
                        }else if(type == 1){
                            lanuage.setText(picker.getContentByCurrValue());
                        }else if(type == 2){
                            education.setText(picker.getContentByCurrValue());
                        }else if(type == 3){
                            experience.setText(picker.getContentByCurrValue());
                        }
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

//        picker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }


    public void showWindow2(){
        if(popupWindow!=null)
            popupWindow.dissmiss();
        if(popupWindow2 != null)
            popupWindow2.dissmiss();
        View contentView = LayoutInflater.from(ApplyTeacherActivity.this).inflate(R.layout.edulist_layout, null);
        initListData(contentView,mEdu);
        popupWindow2 = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)//显示的布局，还可以通过设置一个View
                .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小,默认包裹内容
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOutsideTouchable(false)//是否PopupWindow 以外触摸dissmiss
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.5f) // 控制亮度
                .create()//创建PopupWindow
                .showAtLocation(root, Gravity.CENTER, 0, 0);
    }

    private void initListData(View contentView, final String[] mLan) {
        RecyclerView edulist = (RecyclerView)contentView.findViewById(R.id.edulist);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        edulist.setLayoutManager(manager);
        list.clear();
        for(int i = 0;i<mLan.length;i++){
            list.add(mLan[i]);
        }
        adapter = new EduAdapter(ApplyTeacherActivity.this,list);
        edulist.setAdapter(adapter);
        adapter.setOnItemClickLisenter(new EduAdapter.ItemOnClick() {
            @Override
            public void onItemClick(View v, int position) {
                education.setText(mLan[position]);
                popupWindow2.dissmiss();
            }
        });

    }


    @OnClick({R.id.rl_name, R.id.rl_sex, R.id.rl_lanuage, R.id.rl_edu, R.id.rl_ex, R.id.back, R.id.submit,R.id.head})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                }
                MultiImageSelector.create(ApplyTeacherActivity.this)
                        .showCamera(true)
                        .count(1)
                        .single()
                        .multi()
                        .origin(piclist)
                        .start(ApplyTeacherActivity.this,REQUEST_IMAGE);
                break;
            case R.id.rl_name:

                break;
            case R.id.rl_sex:
                setType(0);
                showPopupWindow();
                break;
            case R.id.rl_lanuage:
                setType(1);
                showPopupWindow();
                break;
            case R.id.rl_edu:
                showWindow2();
                break;
            case R.id.rl_ex:
                setType(3);
                showPopupWindow();
                break;
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                break;
        }
    }

//    @Override
//    public String format(int value) {
//        if(type == 0){
//            return mSex[value];
//        }else if(type == 1){
//            return mLan[value];
//        }else if(type == 2){
//            return mEdu[value];
//        }else if(type == 3){
//            return  mExp[value];
//        }else {
//            return mSex[value];
//        }
//    }
//
//    @Override
//    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//        if(type == 0){
//            sex.setText(mSex[newVal]);
//        }else if(type == 1){
//            lanuage.setText(mLan[newVal]);
//        }else if(type == 2){
//            education.setText(mEdu[newVal]);
//        }else if(type == 3){
//            experience.setText(mExp[newVal]+"年");
//        }else {
//            sex.setText(mSex[newVal]);
//        }
//
//    }

    public void setType(int value){
        type = value;
    }

    @Override
    public void initData(String[] mData1, String[] mData2, String[] mData3, String[] mData4) {
        mLan = mData1;
        mSex = mData2;
        mEdu = mData3;
        mExp = mData4;
    }

    @Override
    public Context getMyContext() {
        return ApplyTeacherActivity.this;
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
