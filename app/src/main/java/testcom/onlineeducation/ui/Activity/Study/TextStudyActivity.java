package testcom.onlineeducation.ui.Activity.Study;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.StudyTextContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.Study.StudyTextPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.CustomRecyclerView;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/17 0017.
 */
public class TextStudyActivity extends BaseActivity implements StudyTextContract.StudyTextView {

    @BindView(R.id.v)
    View v;
    @BindView(R.id.back)
    View back;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_play)
    TextView tvPlay;
    @BindView(R.id.custom_rv)
    CustomRecyclerView customRv;

    private StudyTextPresentImpl mPresenter;

    @Override
    protected int setViewId() {
        return R.layout.activity_study_text;
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
        mPresenter = new StudyTextPresentImpl(this);
        mPresenter.start();
        mPresenter.attchView(this);
        customRv.setShowHasNotMoreData(false);
        customRv.getSwipeRefresh().setEnabled(false);
    }


    @OnClick({R.id.back, R.id.tv_play})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_play:

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
    public void setPresenter(StudyTextPresentImpl presenter) {
        this.mPresenter = presenter;
    }
}
