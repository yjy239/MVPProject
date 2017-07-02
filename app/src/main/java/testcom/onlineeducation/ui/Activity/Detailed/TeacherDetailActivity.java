package testcom.onlineeducation.ui.Activity.Detailed;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.techery.properratingbar.ProperRatingBar;
import testcom.onlineeducation.Contract.TeacherDetailContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.Detailed.TeacherDetailPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.view.Adapter.AppointAdapter;

/**
 * Created by Administrator on 2017/6/23.
 */
public class TeacherDetailActivity extends BaseActivity implements TeacherDetailContract.View {

    TeacherDetailPresentImpl mPresenter;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.rating_bar)
    ProperRatingBar ratingBar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.edu)
    TextView edu;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.lesson_list)
    RecyclerView lessonList;

    private AppointAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected int setViewId() {
        return R.layout.teacherdetail_layout;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
        titleName.setText(R.string.teacherdetail);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mPresenter = new TeacherDetailPresentImpl(this);
        mPresenter.attchView(this);
        mPresenter.start();
    }

    @Override
    public void setPresenter(TeacherDetailPresentImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    public void initData(List<String> list) {
        adapter = new AppointAdapter(this, list);
        lessonList.setLayoutManager(manager);
        lessonList.setAdapter(adapter);
    }

    @OnClick({R.id.back})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}
