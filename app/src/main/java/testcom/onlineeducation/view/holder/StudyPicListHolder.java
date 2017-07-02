package testcom.onlineeducation.view.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.StudyPicListBean;
import testcom.onlineeducation.bean.StudyTextListBean;
import testcom.onlineeducation.network.HttpApi;
import testcom.onlineeducation.ui.Activity.Study.PicStudyActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class StudyPicListHolder extends BaseContextViewHolder<StudyPicListBean> {

    public StudyPicListHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setUpView(final StudyPicListBean model, final int position, final MultiTypeAdapter adapter) {
        final Context context = getHolderContext();
        TextView tv_name = (TextView) getView(R.id.tv_name);
        tv_name.setText(model.getName());
        TextView tv_author = (TextView)getView(R.id.tv_author);
        tv_author.setText(model.getAuthor());
        TextView tv_amount = (TextView)getView(R.id.tv_amount);
        tv_amount.setText(model.getMoney());
        ImageView iv = (ImageView)getView(R.id.iv);
        Glide.with(context)
                .load(HttpApi.getFullImageUrl(model.getPic()))
                .placeholder(R.drawable.default_loading_pic)
//                .transform(new GlideCircleTransform(this.context))
                .into(iv);
        RelativeLayout rl_item = (RelativeLayout)getView(R.id.rl_item);
        rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PicStudyActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
