package testcom.onlineeducation.ui.Activity.Detailed;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.HotPlayContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.model.HotBean;
import testcom.onlineeducation.presenter.Detailed.HotPlayPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.view.Adapter.MainHotAdapter;
import testcom.onlineeducation.view.Adapter.OpenAdapter;

/**
 * Created by Administrator on 2017/6/19.
 */
public class HotPlayingActivity extends BaseActivity implements HotPlayContract.HotView {


    @BindView(R.id.hotplay_list)
    RecyclerView hotplayList;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title_name)
    TextView titleName;

    private HotPlayPresentImpl mPresenter;
    private MainHotAdapter adapter;
    private GridLayoutManager gridmanager;
    private GridLayoutManager gridmanager2;
    private OpenAdapter openadapter;
    private int type = 0;

    @Override
    protected int setViewId() {
        return R.layout.activity_hotplaying;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
        Intent i = getIntent();
        if(i.hasExtra("type")){
            type = i.getIntExtra("type",0);
        }
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        if(type == 0){
            titleName.setText(R.string.hot_playing);
        }else {
            titleName.setText(R.string.hot_lesson);
        }

    }

    @Override
    public void setPresenter(HotPlayPresentImpl presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onResume() {
        mPresenter = new HotPlayPresentImpl(this);
        mPresenter.attchView(this);
        mPresenter.start();
        super.onResume();
    }

    @Override
    public void initData(List<HotBean> list) {
        gridmanager = new GridLayoutManager(HotPlayingActivity.this, 2);
        adapter = new MainHotAdapter(HotPlayingActivity.this, list);
        hotplayList.setLayoutManager(gridmanager);
        hotplayList.setAdapter(adapter);
    }

    @Override
    public void initLessonData(List<String> list) {
        gridmanager2 = new GridLayoutManager(HotPlayingActivity.this, 2);
        openadapter = new OpenAdapter(HotPlayingActivity.this, list);
        hotplayList.setLayoutManager(gridmanager2);
        hotplayList.setAdapter(openadapter);
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
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
