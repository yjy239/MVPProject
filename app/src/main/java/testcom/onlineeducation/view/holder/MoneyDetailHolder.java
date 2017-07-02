package testcom.onlineeducation.view.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.HomeListBean;
import testcom.onlineeducation.bean.MoneyDetailBean;
import testcom.onlineeducation.ui.Activity.Home.MoreHotLessonActivity;
import testcom.onlineeducation.ui.Activity.Home.MoreHotPlayActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class MoneyDetailHolder extends BaseViewHolder<MoneyDetailBean> {
    public MoneyDetailHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(final MoneyDetailBean model, final int position, final MultiTypeAdapter adapter) {
        TextView tv_name = (TextView)getView(R.id.tv_name);
        tv_name.setText(model.getName());
        TextView tv_time = (TextView)getView(R.id.tv_time);
        tv_time.setText(model.getTime());
        TextView tv_value = (TextView)getView(R.id.tv_value);
        tv_value.setText(model.getValue());
    }

}