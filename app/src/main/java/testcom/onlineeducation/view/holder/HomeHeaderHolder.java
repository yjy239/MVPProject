package testcom.onlineeducation.view.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.bingoogolapple.bgabanner.BGABanner;
import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.BannerListBean;
import testcom.onlineeducation.bean.HomeHeaderBean;
import testcom.onlineeducation.ui.Activity.Home.MessageActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class HomeHeaderHolder extends BaseContextViewHolder<HomeHeaderBean> {
    public HomeHeaderHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setUpView(final HomeHeaderBean model, final int position, final MultiTypeAdapter adapter) {
        final Context context = getHolderContext();

        BGABanner banner_main_flip = (BGABanner)getView(R.id.banner_main_flip);
        banner_main_flip.setData(model.getBannerList(), null);
        BGABanner.Adapter banneradpter = new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                Glide.with(context)
                        .load(((BannerListBean)model).getImg())
                        .into((ImageView) view);
            }
        };
        banner_main_flip.setAdapter(banneradpter);

        TextView tv_message_count = (TextView)getView(R.id.tv_message_count);
        tv_message_count.setText(model.getMessageList().size() + "");
        getView(R.id.message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                context.startActivity(intent);
            }
        });

//        Glide.with(context)
//                .load(HttpApi.getFullImageUrl(model.getPic()))
//                .placeholder(R.drawable.default_loading_pic)
////                .transform(new GlideCircleTransform(this.context))
//                .into(iv);
    }
}
