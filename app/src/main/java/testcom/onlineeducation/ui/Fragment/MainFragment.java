package testcom.onlineeducation.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.MainContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.i.IEvent;
import testcom.onlineeducation.i.RxPost;
import testcom.onlineeducation.model.HotBean;
import testcom.onlineeducation.presenter.MainPresentImpl;
import testcom.onlineeducation.ui.Activity.Detailed.HotPlayingActivity;
import testcom.onlineeducation.ui.Activity.Home.MessageActivity;
import testcom.onlineeducation.utils.LoginUtils;
import testcom.onlineeducation.utils.Rx.HandlerThread;
import testcom.onlineeducation.utils.Rx.RxBus;
import testcom.onlineeducation.utils.Rx.Subcuribe;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.CustomRecyclerView;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/14.
 */
public class MainFragment extends BaseFragment implements MainContract.MainView {
    @BindView(R.id.v)
    View v;
    @BindView(R.id.custom_rv)
    CustomRecyclerView customRv;

    private MainPresentImpl mPresenter;

    @Override
    protected int setViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void findViews(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initEvent() {
        RxBus.getDefault().register(this);
    }

    @Override
    protected void init() {
        customRv.setShowHasNotMoreData(false);
        mPresenter = new MainPresentImpl(this);
        mPresenter.start();
        mPresenter.attchView(this);
    }


    public void initData(List<Visitable> list) {
        MultiTypeAdapter adapter = customRv.getAdapter();
        if (list.size() == 0) {
            customRv.showEmptyView();
        } else {
            customRv.hideEmptyView();
        }
        adapter.refreshData(list);
        if (customRv.isRefreshing()) {
            showShortToast("刷新成功");
        }
        customRv.stopSwipeRefresh();
    }

    @Override
    public void loadingData() {
        Toast.makeText(getActivity(), "已经读取了数据", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        RxBus.getDefault().unregister(this);
    }


    @Override
    public void setPresenter(MainPresentImpl presenter) {
        this.mPresenter = presenter;
    }

    @Subcuribe({HandlerThread.MAIN})
    public void handler(RxPost ev){
        switch (ev.getTag()){
//            case IEvent.LOGIN:
//
//
//                break;
        }
    }
}
