package testcom.onlineeducation.ui.Activity.Home;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.MoreHotLessonContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.Home.MoreHotLessonPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.CustomRecyclerView;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class MoreHotLessonActivity extends BaseActivity implements MoreHotLessonContract.MoreHotLessonView{

    @BindView(R.id.v)
    View v;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.custom_rv)
    CustomRecyclerView customRv;

    private MoreHotLessonPresentImpl mPresenter;
    @Override
    protected int setViewId() {
        return R.layout.activity_more_hot_lesson;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        mPresenter = new MoreHotLessonPresentImpl(this);
        mPresenter.start();
        mPresenter.attchView(this);
    }


    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
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
    public void setPresenter(MoreHotLessonPresentImpl presenter) {
        this.mPresenter = presenter;
    }
}
