package testcom.onlineeducation.ui.Activity.Study;

import android.content.pm.ActivityInfo;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.StudyVideoContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.Study.StudyVideoPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.CustomRecyclerView;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/17 0017.
 */
public class VideoStudyActivity extends BaseActivity implements StudyVideoContract.StudyVideoView {

    @BindView(R.id.v)
    View v;
    @BindView(R.id.back)
    View back;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.custom_rv)
    CustomRecyclerView customRv;
    @BindView(R.id.screen_control)
    ImageView screen_control;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.ll_control)
    LinearLayout ll_control;

    private StudyVideoPresentImpl mPresenter;
    private boolean isFirst = true;

    @Override
    protected int setViewId() {
        return R.layout.activity_study_video;
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
        mPresenter = new StudyVideoPresentImpl(this);
        mPresenter.start();
        mPresenter.attchView(this);
        customRv.setShowHasNotMoreData(false);
        customRv.getSwipeRefresh().setEnabled(false);
    }


    @OnClick({R.id.back, R.id.play, R.id.bottom_play, R.id.screen_control})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.play:

                break;
            case R.id.bottom_play:

                break;
            case R.id.screen_control:
                if (isFirst){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    isFirst = false;
                    screen_control.setImageResource(R.drawable.icon_back_screen);
                    reSetHeight(iv, ViewGroup.LayoutParams.MATCH_PARENT);
                    reSetHeight(ll_control, ViewGroup.LayoutParams.MATCH_PARENT);
                }else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    isFirst = true;
                    screen_control.setImageResource(R.drawable.icon_full_screen);
                    float v = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, VideoStudyActivity.this.getResources().getDisplayMetrics());
                    reSetHeight(iv, (int)v);
                    reSetHeight(ll_control, (int)v);
                }
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
    public void setPresenter(StudyVideoPresentImpl presenter) {
        this.mPresenter = presenter;
    }

    private void reSetHeight(View view, int height){
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = height;
        view.setLayoutParams(layoutParams);
    }
}
