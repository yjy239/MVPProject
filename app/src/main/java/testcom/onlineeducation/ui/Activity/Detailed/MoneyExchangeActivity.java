package testcom.onlineeducation.ui.Activity.Detailed;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.R;
import testcom.onlineeducation.ui.Activity.BaseActivity;

/**
 * Created by Administrator on 2017/6/28 0028.
 */
public class MoneyExchangeActivity extends BaseActivity {

    @BindView(R.id.v)
    View v;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_tip)
    TextView tvTip;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_next)
    TextView tvNext;

    public static final int TYPE_RECHARGE = 0;
    public static final int TYPE_WITHDRAW = 1;

    private int type;

    @Override
    protected int setViewId() {
        return R.layout.activity_money_exchange;
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
        type = getIntent().getIntExtra("type", 0);
        if (type == TYPE_RECHARGE){

        }else if (type == TYPE_WITHDRAW){

        }
    }

    @OnClick({R.id.back, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_next:

                break;
        }
    }
}
