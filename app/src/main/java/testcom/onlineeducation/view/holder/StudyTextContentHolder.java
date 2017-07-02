package testcom.onlineeducation.view.holder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.StudyTextContentBean;
import testcom.onlineeducation.ui.Activity.Study.ExerciseActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class StudyTextContentHolder extends BaseContextViewHolder<StudyTextContentBean> {

    public StudyTextContentHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setUpView(final StudyTextContentBean model, final int position, final MultiTypeAdapter adapter) {
        final Context context = getHolderContext();
        TextView tv_study_lan = (TextView) getView(R.id.tv_study_lan);
        tv_study_lan.setText(model.getStudyLan());
        TextView tv_translate_lan = (TextView) getView(R.id.tv_translate_lan);
        tv_translate_lan.setText(model.getTranslateLan());
        LinearLayout ll_item = (LinearLayout) getView(R.id.ll_item);
        tv_study_lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExerciseActivity.class);
                intent.putExtra("studyLan", model.getStudyLan());
                intent.putExtra("translateLan", model.getTranslateLan());
                if (model.isSingle()) {
                    intent.putExtra("isSingle", true);
                }
                context.startActivity(intent);

            }
        });
        tv_translate_lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExerciseActivity.class);
                intent.putExtra("studyLan", model.getStudyLan());
                intent.putExtra("translateLan", model.getTranslateLan());
                if (model.isSingle()) {
                    intent.putExtra("isSingle", true);
                }
                context.startActivity(intent);

            }
        });
    }
}
