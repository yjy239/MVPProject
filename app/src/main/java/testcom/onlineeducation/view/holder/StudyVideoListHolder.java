package testcom.onlineeducation.view.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.StudyPicListBean;
import testcom.onlineeducation.bean.StudyVideoListBean;
import testcom.onlineeducation.network.HttpApi;
import testcom.onlineeducation.ui.Activity.Study.VideoStudyActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class StudyVideoListHolder extends BaseContextViewHolder<StudyVideoListBean> {

    public StudyVideoListHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setUpView(final StudyVideoListBean model, final int position, final MultiTypeAdapter adapter) {
        final Context context = getHolderContext();
        TextView tv_name = (TextView) getView(R.id.tv_name);
        tv_name.setText(model.getName());
        TextView tv_author = (TextView) getView(R.id.tv_author);
        tv_author.setText(model.getAuthor());
        TextView tv_amount = (TextView) getView(R.id.tv_amount);
        tv_amount.setText(model.getMoney());
        ImageView iv = (ImageView) getView(R.id.iv);
        Glide.with(context)
                .load(HttpApi.getFullImageUrl(model.getPic()))
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
