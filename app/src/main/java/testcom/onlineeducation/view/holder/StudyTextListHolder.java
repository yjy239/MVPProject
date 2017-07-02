package testcom.onlineeducation.view.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.StudyTextListBean;
import testcom.onlineeducation.ui.Activity.Study.TextStudyActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class StudyTextListHolder extends BaseContextViewHolder<StudyTextListBean> {

    public StudyTextListHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setUpView(final StudyTextListBean model, final int position, final MultiTypeAdapter adapter) {
        final Context context = getHolderContext();
        TextView tv_name = (TextView) getView(R.id.tv_name);
        tv_name.setText(model.getName());
        TextView tv_author = (TextView)getView(R.id.tv_author);
        tv_author.setText(model.getAuthor());
        TextView tv_amount = (TextView)getView(R.id.tv_amount);
        tv_amount.setText(model.getMoney());
        RelativeLayout rl_item = (RelativeLayout)getView(R.id.rl_item);
        rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TextStudyActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
