package testcom.onlineeducation.ui.Activity.Study;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.StudyPicContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.Study.StudyPicPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.CustomRecyclerView;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/17 0017.
 */
public class PicStudyActivity extends BaseActivity implements StudyPicContract.StudyPicView {


    @BindView(R.id.v)
    View v;
    @BindView(R.id.back)
    View back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.custom_rv)
    CustomRecyclerView customRv;
    private StudyPicPresentImpl mPresenter;

    @Override
    protected int setViewId() {
        return R.layout.activity_study_pic;
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
        mPresenter = new StudyPicPresentImpl(this);
        mPresenter.start();
        mPresenter.attchView(this);
        customRv.setShowHasNotMoreData(false);
        customRv.getSwipeRefresh().setEnabled(false);
    }


    @OnClick({R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
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
    public void showMsg() {

    }

    @Override
    public void setPresenter(StudyPicPresentImpl presenter) {
        this.mPresenter = presenter;
    }

}
