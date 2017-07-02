package testcom.onlineeducation.view.holder;

import android.view.View;
import android.widget.TextView;

import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.StudySingleTitleBean;
import testcom.onlineeducation.bean.StudyTextListBean;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class StudySigleTitleHolder extends BaseViewHolder<StudySingleTitleBean> {

    public StudySigleTitleHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final StudySingleTitleBean model, final int position, final MultiTypeAdapter adapter) {
        TextView tv_text_title = (TextView) getView(R.id.tv_text_title);
        tv_text_title.setText(model.getTitle());
    }
}
