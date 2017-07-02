package testcom.onlineeducation.view.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.EduBean;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;

/**
 * Created by Administrator on 2017/6/28.
 */
public class EduHolder extends BaseContextViewHolder<EduBean> {


    public EduHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setUpView(EduBean model, int position, MultiTypeAdapter adapter) {
        final Context context = getHolderContext();
        TextView txt = (TextView)getView(R.id.name);
        txt.setText(model.getName()+"");
    }
}
