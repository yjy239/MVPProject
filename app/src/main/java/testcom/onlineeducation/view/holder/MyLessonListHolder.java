package testcom.onlineeducation.view.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.MyLessonBean;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/6/23.
 */
public class MyLessonListHolder extends BaseContextViewHolder<MyLessonBean> {

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.rl_item)
    RelativeLayout rlItem;

    public MyLessonListHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setUpView(MyLessonBean model, int position, MultiTypeAdapter adapter) {
        final Context context = getHolderContext();
        iv = (ImageView) getView(R.id.iv);
        tvName = (TextView)getView(R.id.tv_name);
        tvAmount = (TextView)getView(R.id.tv_amount);
        tvAuthor = (TextView)getView(R.id.tv_author);
        status = (TextView)getView(R.id.status);
        Glide.with(context)
                .load(R.drawable.default_loading_pic)
                .asBitmap()
                .centerCrop()
                .into(iv);

        tvName.setText(model.getName());
        tvAmount.setText(model.getPrice());
        status.setText(model.getStatus());
        tvAuthor.setText(model.getAuthor());
    }
}
