package testcom.onlineeducation.view.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.StudyPicContentBean;
import testcom.onlineeducation.network.HttpApi;
import testcom.onlineeducation.ui.Activity.Study.PicExerciseActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class StudyPicContentHolder extends BaseContextViewHolder<StudyPicContentBean> {
    public StudyPicContentHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setUpView(final StudyPicContentBean model, final int position, final MultiTypeAdapter adapter) {
        final Context context = getHolderContext();
        List<Visitable> models = adapter.getModels();
        final ArrayList<StudyPicContentBean> list = new ArrayList<>();
        for (int i = 0; i < models.size(); i++) {
            list.add((StudyPicContentBean)models.get(i));
        }
        TextView tv_study_lan = (TextView) getView(R.id.tv_study_lan);
        tv_study_lan.setText(model.getStudyLan());
        TextView tv_translate_lan = (TextView) getView(R.id.tv_translate_lan);
        tv_translate_lan.setText(model.getTranslateLan());
        ImageView iv = (ImageView)getView(R.id.iv);
        Glide.with(context)
                .load(HttpApi.getFullImageUrl(model.getPic()))
                .placeholder(R.drawable.default_loading_pic)
//                .transform(new GlideCircleTransform(this.context))
                .into(iv);
        RelativeLayout rl_item = (RelativeLayout) getView(R.id.rl_item);
        rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PicExerciseActivity.class);
//                intent.putExtra("position", );
                intent.putExtra("model", list);
                context.startActivity(intent);
            }
        });

    }
}
