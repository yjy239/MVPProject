package testcom.onlineeducation.ui.Activity.Detailed;

import android.content.Intent;
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
public class MyWalletActivity extends BaseActivity {

    @BindView(R.id.v)
    View v;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_money_detail)
    TextView tvMoneyDetail;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_recharge)
    TextView tvRecharge;
    @BindView(R.id.tv_withdraw)
    TextView tvWithdraw;
    @BindView(R.id.tv_bank_card)
    TextView tvBankCard;
    @BindView(R.id.tv_change_pay_password)
    TextView tvChangePayPassword;
    @BindView(R.id.tv_find_pay_password)
    TextView tvFindPayPassword;

    @Override
    protected int setViewId() {
        return R.layout.activity_my_wallet;
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

    @OnClick({R.id.back, R.id.tv_money_detail, R.id.tv_recharge, R.id.tv_withdraw, R.id.tv_bank_card, R.id.tv_change_pay_password, R.id.tv_find_pay_password})
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_money_detail:
                intent = new Intent(this, MoneyDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_recharge:

                break;
            case R.id.tv_withdraw:

                break;
            case R.id.tv_bank_card:

                break;
            case R.id.tv_change_pay_password:

                break;
            case R.id.tv_find_pay_password:

                break;
        }
    }
}
