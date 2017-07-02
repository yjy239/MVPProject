package testcom.onlineeducation.view.holder;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.PayTypeBean;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class PayTypeHolder extends BaseContextViewHolder<PayTypeBean> {

    public PayTypeHolder(View itemView , Context context) {
        super(itemView, context);
    }

    @Override
    public void setUpView(final PayTypeBean model, final int position, final MultiTypeAdapter adapter) {
        Context context = getHolderContext();
        TextView tvName = (TextView) getView(R.id.tv_name);
//        IconFontTextView ifTvIsChoose = (IconFontTextView) getView(R.id.if_tv_choose);
        RelativeLayout rl = (RelativeLayout) getView(R.id.rl);
//        TextView if_tv_icon = (TextView)getView(R.id.if_tv_icon);


        String name;
        int icon;
        int color;
        switch (position){
            case 0:
                name = "余额支付";
//                icon = R.string.icon_bank;
//                color = ContextCompat.getColor(context, R.color.light_red);
                break;
            case 1:
                name = "支付宝支付";
//                color = ContextCompat.getColor(context, R.color.btn_blue_normal);

                break;
            case 2:
                name = "微信支付";
//                icon = R.string.icon_we_chat;
//                color = ContextCompat.getColor(context, R.color.green);

                break;
            case  3:
                name = "银联支付";
//                icon = R.string.icon_bank;
//                color = ContextCompat.getColor(context, R.color.colorIconYellow);
                break;
            default:
                name = "applepay";
//                icon = R.string.icon_ali_pay;
//                color = ContextCompat.getColor(context, R.color.gray_normal);
                break;
        }

        tvName.setText(name);
//        if_tv_icon.setText(icon);
//        if_tv_icon.setTextColor(color);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < adapter.getModels().size(); i++) {
                    PayTypeBean bean = (PayTypeBean) adapter.getModels().get(i);
                    bean.setChoose(false);
                }
                model.setChoose(true);
//                EventBus.getDefault().post(new SelectEvent(IEventOrderTag.SHOW_PAY_TYPE_DETAIL, position));
            }
        });
        tvName.setText(model.getName());
//        ifTvIsChoose.setVisibility(model.isChoose() ? View.VISIBLE : View.INVISIBLE);
    }
}
