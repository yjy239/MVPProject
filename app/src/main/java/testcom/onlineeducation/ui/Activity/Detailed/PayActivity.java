package testcom.onlineeducation.ui.Activity.Detailed;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhouwei.library.CustomPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import testcom.onlineeducation.Contract.PayContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.PayTypeBean;
import testcom.onlineeducation.presenter.Detailed.PayPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/21.
 */
public class PayActivity extends BaseActivity implements PayContract.PayView {

    PayPresentImpl mPresenter;
    CustomPopWindow popupWindow;
    CustomPopWindow popupWindow2;
    @BindView(R.id.container)
    RelativeLayout container;
    private boolean locked = false;
    private int type = 0;

    @Override
    protected int setViewId() {
        return R.layout.payactivity_layout;
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
        Intent i = getIntent();
        if(i.hasExtra("type")){
            type = i.getIntExtra("type",0);
        }
        Log.e("type",type+"");
    }

    @Override
    public void initData() {

    }

    @Override
    public void setPresenter(PayPresentImpl presenter) {
        this.mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        mPresenter = new PayPresentImpl(this);
        mPresenter.attchView(this);
        mPresenter.start();
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if(popupWindow!=null){
            popupWindow.dissmiss();
        }
        if(popupWindow2!=null){
            popupWindow2.dissmiss();
        }
        super.onDestroy();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(hasFocus&&!locked){
            Log.e("window","create");
            showPopupWindow();
        }

    }

    RelativeLayout rlCoupon;
    Button pay;
    TextView delete;

    private void showPopupWindow() {
        if (popupWindow2 != null) popupWindow2.dissmiss();

        View contentView = LayoutInflater.from(PayActivity.this).inflate(R.layout.coupon_popupwindow, null);
        //处理popWindow 显示内容
        handleLogic(contentView);
        popupWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)//显示的布局，还可以通过设置一个View
                .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小,默认包裹内容
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOutsideTouchable(false)//是否PopupWindow 以外触摸dissmiss
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.5f) // 控制亮度
                .create()//创建PopupWindow
                .showAtLocation(container,Gravity.CENTER, 0, 0);
        locked = false;
        //显示PopupWindow
    }

    private void handleLogic(View contentView) {
        rlCoupon = (RelativeLayout) contentView.findViewById(R.id.select_coupon);
        pay = (Button)contentView.findViewById(R.id.pay);
        delete = (TextView)contentView.findViewById(R.id.delete);
        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.select_coupon:

                        break;
                    case R.id.pay:
                        showTypePopWindow();
                        break;
                    case R.id.delete:
                        finish();
                        break;
                }
            }
        };
        rlCoupon.setOnClickListener(listener);
        pay.setOnClickListener(listener);
        delete.setOnClickListener(listener);
    }

    private void showTypePopWindow(){
        if (popupWindow != null) popupWindow.dissmiss();
        View contentView = LayoutInflater.from(PayActivity.this).inflate(R.layout.pay_popwindow, null);
        //处理popWindow 显示内容
        handlePayType(contentView);
        popupWindow2 = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(contentView)//显示的布局，还可以通过设置一个View
                .size(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小,默认包裹内容
                .setFocusable(true)//是否获取焦点，默认为ture
                .setOutsideTouchable(false)//是否PopupWindow 以外触摸dissmiss
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.5f) // 控制亮度
                .create()//创建PopupWindow
                .showAtLocation(container, Gravity.CENTER, 0, 0);//显示PopupWindow
        locked = true;
    }
    private List<Visitable> models = new ArrayList<>();
    private static final String PAY_TYPE_BANK_CARD = "pay_type_bank_card";
    private static final String PAY_TYPE_ALI_PAY = "pay_type_ali_pay";
    private static final String PAY_TYPE_WE_CHAT = "pay_type_we_chat";
    private static final String PAY_TYPE_USER_MONEY = "pay_type_user_money";
    private static final String PAY_TYPE_USER_APPLE = "pay_type_apple_pay";

    private void handlePayType(View contentView) {
        models = new ArrayList<>();
        PayTypeBean bean0 = new PayTypeBean();
        bean0.setName("余额支付");
        bean0.setType(PAY_TYPE_USER_MONEY);
        models.add(bean0);

        PayTypeBean bean1 = new PayTypeBean();
        bean1.setName("支付宝支付");
        bean1.setType(PAY_TYPE_ALI_PAY);
        models.add(bean1);

        PayTypeBean bean2 = new PayTypeBean();
        bean2.setName("微信支付");
        bean2.setType(PAY_TYPE_WE_CHAT);
        models.add(bean2);

        PayTypeBean bean3 = new PayTypeBean();
        bean3.setName("银联支付");
        bean3.setType(PAY_TYPE_BANK_CARD);
        models.add(bean3);

        PayTypeBean bean4 = new PayTypeBean();
        bean4.setName("applepay");
        bean4.setType(PAY_TYPE_USER_APPLE);
        models.add(bean4);


        RecyclerView rv = (RecyclerView) contentView.findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        MultiTypeAdapter adapter = new MultiTypeAdapter(models, this);
        rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiTypeAdapter.OnItemClick() {
            @Override
            public void onItemClick(View v, int position) {
                Log.e("item",position+"");
                if(type == 0){
                    Toast.makeText(getApplicationContext(),"已付款",Toast.LENGTH_SHORT).show();
                }else if(type == 1){
                    //公开课进入预约界面
                    Intent todetail = new Intent(PayActivity.this,LessonDetailActivity.class);
                    startActivity(todetail);
                }

                finish();
            }
        });
//        View.OnClickListener listener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.if_tv_back:
//                        if (popupWindow2 != null) {
//                            popupWindow2.dissmiss();
//                        }
//                        showPopupWindow();
//                        break;
//                }
//            }
//        };
//        contentView.findViewById(R.id.if_tv_back).setOnClickListener(listener);
    }

}
