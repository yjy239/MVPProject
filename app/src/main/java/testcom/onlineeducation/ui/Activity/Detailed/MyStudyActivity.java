package testcom.onlineeducation.ui.Activity.Detailed;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.MyStudyContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.Detailed.MyStudyPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.CustomRecyclerView;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/23.
 */
public class MyStudyActivity extends BaseActivity implements MyStudyContract.View {

    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.study_list)
    CustomRecyclerView studyList;

    private MyStudyPresentImpl mPresenter;

    @Override
    protected int setViewId() {
        return R.layout.mystudyactivity_layout;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
        titleName.setText(R.string.mystudy);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        mPresenter = new MyStudyPresentImpl(this);
        mPresenter.attchView(this);
        mPresenter.start();
    }

    @Override
    public void setPresenter(MyStudyPresentImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }


    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    @Override
    public void initData(List<Visitable> list) {
        MultiTypeAdapter adapter = studyList.getAdapter();
        if (list.size() == 0) {
            studyList.showEmptyView();
        } else {
            studyList.hideEmptyView();
        }
        adapter.refreshData(list);
        if (studyList.isRefreshing()) {
            showShortToast("刷新成功");
        }
        studyList.stopSwipeRefresh();
    }

    @OnClick({R.id.back})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
