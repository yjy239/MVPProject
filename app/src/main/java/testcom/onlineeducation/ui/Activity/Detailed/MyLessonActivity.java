package testcom.onlineeducation.ui.Activity.Detailed;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.MyLessonContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.Detailed.MyLessonPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.CustomRecyclerView;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/23.
 */
public class MyLessonActivity extends BaseActivity implements MyLessonContract.View {

    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.lessonlist)
    CustomRecyclerView lessonlist;
    private MyLessonPresentImpl myLessonPresent;

    @Override
    protected int setViewId() {
        return R.layout.mylessonactivity_layout;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
        titleName.setText(R.string.mylesson);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        myLessonPresent = new MyLessonPresentImpl(this);
        myLessonPresent.attchView(this);
        myLessonPresent.start();
    }

    @Override
    public void initData(List<Visitable> list) {
        MultiTypeAdapter adapter = lessonlist.getAdapter();
        if (list.size() == 0) {
            lessonlist.showEmptyView();
        } else {
            lessonlist.hideEmptyView();
        }
        adapter.refreshData(list);
        if (lessonlist.isRefreshing()) {
            showShortToast("刷新成功");
        }
        lessonlist.stopSwipeRefresh();
    }

    @Override
    public void setPresenter(MyLessonPresentImpl presenter) {
        myLessonPresent = presenter;
    }

    @Override
    protected void onDestroy() {
        if (myLessonPresent != null) {
            myLessonPresent.detachView();
        }
        super.onDestroy();
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
