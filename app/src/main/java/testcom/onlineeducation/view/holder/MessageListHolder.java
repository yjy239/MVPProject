package testcom.onlineeducation.view.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.MessageListBean;
import testcom.onlineeducation.bean.StudyTextListBean;
import testcom.onlineeducation.network.HttpApi;
import testcom.onlineeducation.ui.Activity.MainActivity;
import testcom.onlineeducation.ui.Activity.Study.TextStudyActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class MessageListHolder extends BaseContextViewHolder<MessageListBean> {

    public MessageListHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setUpView(final MessageListBean model, final int position, final MultiTypeAdapter adapter) {
        final Context context = getHolderContext();
        TextView tv_title = (TextView)getView(R.id.tv_title);
        tv_title.setText(model.getTitle());
        TextView tv_content = (TextView)getView(R.id.tv_content);
        tv_content.setText(model.getContent());
        TextView tv_time = (TextView)getView(R.id.tv_time);
        ImageView iv = (ImageView)getView(R.id.iv);
        Glide.with(context)
                .load(HttpApi.getFullImageUrl(model.getPic()))
                .placeholder(R.drawable.default_loading_pic)
//                .transform(new GlideCircleTransform(this.context))
                .into(iv);
        tv_time.setText(model.getTime());
        getView(R.id.rl_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("action", "intoMyLesson");
                context.startActivity(intent);
            }
        });
        getView(R.id.rl_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
