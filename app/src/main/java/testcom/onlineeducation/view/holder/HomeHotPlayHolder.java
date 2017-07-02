package testcom.onlineeducation.view.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.HomeHotPlayBean;
import testcom.onlineeducation.network.HttpApi;
import testcom.onlineeducation.ui.Activity.Study.VideoStudyActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class HomeHotPlayHolder  extends BaseContextViewHolder<HomeHotPlayBean> {
    public HomeHotPlayHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setUpView(final HomeHotPlayBean model, final int position, final MultiTypeAdapter adapter) {
        final Context context = getHolderContext();
        TextView in_text = (TextView)getView(R.id.in_text);
        in_text.setText(model.getName());
        TextView name = (TextView)getView(R.id.name);
        name.setText(model.getAuthor());
        TextView price = (TextView)getView(R.id.price);
        String money = model.getMoney();
        if ("0".equals(money)){
            money = "免费";
        }else {
            money = "¥"+model.getMoney();
        }
        price.setText(money);
        ImageView iv = (ImageView)getView(R.id.goods_pic);
        Glide.with(context)
                .load(HttpApi.getFullImageUrl(model.getImg()))
                .placeholder(R.drawable.default_loading_pic)
//                .transform(new GlideCircleTransform(this.context))
                .into(iv);
        getView(R.id.rl_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoStudyActivity.class);
                context.startActivity(intent);
            }
        });

    }
}