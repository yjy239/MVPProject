package testcom.onlineeducation.ui.Fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.StudyContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.StudyPresentImpl;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.CustomRecyclerView;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.MultiTypeAdapter;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;
import testcom.onlineeducation.view.Adapter.MyPagerAdapter;

/**
 * Created by Administrator on 2017/6/16.
 */
public class StudyFragment extends BaseFragment implements StudyContract.StudyView, ViewPager.OnPageChangeListener {
    @BindView(R.id.ll_study_text)
    LinearLayout llStudyText;
    @BindView(R.id.ll_study_pic)
    LinearLayout llStudyPic;
    @BindView(R.id.ll_study_video)
    LinearLayout llStudyVideo;
    @BindView(R.id.ll_study_movie)
    LinearLayout llStudyMovie;
    @BindView(R.id.bottom_tab)
    LinearLayout bottomTab;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.tv_pic)
    TextView tvPic;
    @BindView(R.id.tv_video)
    TextView tvVideo;
    @BindView(R.id.tv_movie)
    TextView tvMovie;
    @BindView(R.id.iv_text)
    ImageView ivText;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.iv_video)
    ImageView ivVideo;
    @BindView(R.id.iv_movie)
    ImageView ivMovie;

    private View textStudyListView;
    private View picStudyListView;
    private View videoStudyListView;
    private View movieStudyListView;
    private List<View> viewList;//view数组
    private ArrayList<TextView> tvList = new ArrayList<>();
    private ArrayList<ImageView> ivList = new ArrayList<>();

    private int[] iconClicks = new int[]{R.drawable.icon_study_text_click, R.drawable.icon_study_pic_click, R.drawable.icon_study_video_click, R.drawable.icon_study_movie_click};
    private int[] iconDefaults = new int[]{R.drawable.icon_study_text_default, R.drawable.icon_study_pic_default, R.drawable.icon_study_video_default, R.drawable.icon_study_movie_default};

    private int currentPage;
    private int clickPage;

    private StudyPresentImpl mPresenter;
    private ArrayList<Integer> pageList = new ArrayList<>();

    @Override
    protected int setViewId() {
        return R.layout.fragment_study;
    }

    @Override
    protected void findViews(View view) {
        ButterKnife.bind(this, view);
        tvList.add(tvText);
        tvList.add(tvPic);
        tvList.add(tvVideo);
        tvList.add(tvMovie);
        ivList.add(ivText);
        ivList.add(ivPic);
        ivList.add(ivVideo);
        ivList.add(ivMovie);
    }

    @Override
    protected void initEvent() {
        viewpager.addOnPageChangeListener(this);
    }

    @Override
    protected void init() {
        for (int i = 0; i < 4; i++) {
            pageList.add(1);
        }
        initViewPager();
        mPresenter = new StudyPresentImpl(this);
        mPresenter.start();
        mPresenter.attchView(this);
    }

    @Override
    public void setPresenter(StudyPresentImpl presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void initData(List<Visitable> list, int index) {
        initPageData(list, viewList.get(index), pageList.get(index));
    }

    private void initPageData(List<Visitable> list, View view, int currentPage) {
        CustomRecyclerView custom_rv = (CustomRecyclerView) view.findViewById(R.id.custom_rv);
        MultiTypeAdapter adapter = custom_rv.getAdapter();
        if (currentPage == 1) {
            if (list.size() == 0) {
                custom_rv.showEmptyView();
            } else {
                custom_rv.hideEmptyView();
            }
            adapter.refreshData(list);
            if (custom_rv.isRefreshing()) {
                showShortToast("刷新成功");
            }
            custom_rv.stopSwipeRefresh();
        } else {
            adapter.addMoreData(list);
        }
    }

    @Override
    public void showMsg() {

    }

    public void initViewPager() {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        textStudyListView = inflater.inflate(R.layout.layout_default_list, null);
        picStudyListView = inflater.inflate(R.layout.layout_default_list, null);
        videoStudyListView = inflater.inflate(R.layout.layout_grid_two_ver_list, null);
        movieStudyListView = inflater.inflate(R.layout.layout_grid_two_ver_list, null);

        viewList = new ArrayList<>();// 将要分页显示的View装入数组中
        viewList.add(textStudyListView);
        viewList.add(picStudyListView);
        viewList.add(videoStudyListView);
        viewList.add(movieStudyListView);

        viewpager.setAdapter(new MyPagerAdapter(viewList));

    }


    @OnClick({R.id.ll_study_text, R.id.ll_study_pic, R.id.ll_study_video, R.id.ll_study_movie})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_study_text:
                clickPage = 0;
                break;
            case R.id.ll_study_pic:
                clickPage = 1;
                break;
            case R.id.ll_study_video:
                clickPage = 2;
                break;
            case R.id.ll_study_movie:
                clickPage = 3;
                break;
        }
        if (clickPage != currentPage) viewpager.setCurrentItem(clickPage);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position != currentPage) {
            for (int i = 0; i < tvList.size(); i++) {
                tvList.get(i).setTextColor(ContextCompat.getColor(getActivity(), R.color.colorBottomBlack));
                ivList.get(i).setImageResource(iconDefaults[i]);
            }
            tvList.get(position).setTextColor(ContextCompat.getColor(getActivity(), R.color.colorBlue));
            ivList.get(position).setImageResource(iconClicks[position]);
        }
        currentPage = viewpager.getCurrentItem();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
