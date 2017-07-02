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
import testcom.onlineeducation.ui.Activity.Home.MoreHotLessonActivity;
import testcom.onlineeducation.ui.Activity.Home.MoreHotPlayActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.BaseContextViewHolder;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.CustomRecyclerView;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/23 0023.
 */
public class HomeListHolder extends BaseContextViewHolder<HomeListBean> {
    public HomeListHolder(View itemView, Context context) {
        super(itemView, context);
    }

    @Override
    public void setUpView(final HomeListBean model, final int position, final MultiTypeAdapter adapter) {
        final Context context = getHolderContext();

        TextView tv_type = (TextView) getView(R.id.tv_type);
        tv_type.setText(model.getType());
        ArrayList<Visitable> list = model.getList();
        ArrayList<Visitable> showList = new ArrayList<>();
        int maxShowCount = model.getMaxShowCount();
        int size = list.size();
        if (maxShowCount < size) {
            for (int i = 0; i < maxShowCount; i++) {
                showList.add(list.get(i));
            }
        } else {
            showList.addAll(list);
        }


//        RecyclerView rv = (RecyclerView) getView(R.id.rv);
//        GridLayoutManager mGridLayoutManager = new GridLayoutManager(context, 2);
//        mGridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
//        rv.setLayoutManager(mGridLayoutManager);
//        rv.setItemAnimator(new DefaultItemAnimator());
//        MultiTypeAdapter childAdapter = new MultiTypeAdapter(showList, context);
//        rv.setAdapter(childAdapter);

        CustomRecyclerView customRv = (CustomRecyclerView) getView(R.id.rv);
        setData(customRv, showList);

        if ("热门课程".equals(model.getType())) {
            getView(R.id.bottom).setVisibility(View.VISIBLE);
        }

        getView(R.id.rl_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                String type = model.getType();
                if (type!=null) {
                    switch (type){
                        case "正在热播":
                            intent = new Intent(context, MoreHotPlayActivity.class);
                            context.startActivity(intent);
                            break;
                        case "热门课程":
                            intent = new Intent(context, MoreHotLessonActivity.class);
                            context.startActivity(intent);
                            break;
                    }
                }
            }
        });

    }

    private void setData(CustomRecyclerView customRv, ArrayList<Visitable> list){
        MultiTypeAdapter adapter = customRv.getAdapter();
        if (list.size() == 0) {
            customRv.showEmptyView();
        } else {
            customRv.hideEmptyView();
        }
        adapter.refreshData(list);

        customRv.stopSwipeRefresh();
    }

}