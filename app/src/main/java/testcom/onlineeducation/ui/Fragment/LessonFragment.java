package testcom.onlineeducation.ui.Fragment;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.LessonContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.LessonPresenterImpl;
import testcom.onlineeducation.view.Adapter.AppointAdapter;
import testcom.onlineeducation.view.Adapter.LessonPagerAdapter;
import testcom.onlineeducation.view.Adapter.LiveAdapter;
import testcom.onlineeducation.view.Adapter.MyLessonAdapter;
import testcom.onlineeducation.view.Adapter.MyLiveAdapter;
import testcom.onlineeducation.view.Adapter.OpenAdapter;

/**
 * Created by Administrator on 2017/6/16.
 */

public class LessonFragment extends BaseFragment implements LessonContract.LessonView, ViewPager.OnPageChangeListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_icon1)
    TextView tvIcon1;
    @BindView(R.id.rl_icon1)
    RelativeLayout rlIcon1;
    @BindView(R.id.tv_icon2)
    TextView tvIcon2;
    @BindView(R.id.rl_icon2)
    RelativeLayout rlIcon2;
    @BindView(R.id.tv_icon3)
    TextView tvIcon3;
    @BindView(R.id.rl_icon3)
    RelativeLayout rlIcon3;
    @BindView(R.id.tv_icon4)
    TextView tvIcon4;
    @BindView(R.id.rl_icon4)
    RelativeLayout rlIcon4;
    @BindView(R.id.tv_icon5)
    TextView tvIcon5;
    @BindView(R.id.rl_icon5)
    RelativeLayout rlIcon5;
    @BindView(R.id.bottom_tab)
    LinearLayout bottomTab;
    @BindView(R.id.container)
    ViewPager container;
    @BindView(R.id.navview)
    NavigationView navview;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    private List<View> viewlist = new ArrayList<>();
    private LayoutInflater inflater;

    private LessonPresenterImpl mLessonPresenterImpl;
    private View mLiveView;
    private View LiveView;
    private View appiontmentView;
    private View openLessonView;
    private View mLessonView;
    private LessonPagerAdapter pagerAdapter;
    private LiveAdapter liveAdapter;
    private MyLiveAdapter myLiveAdapter;
    private AppointAdapter appointAdapter;
    private OpenAdapter openAdapter;
    private MyLessonAdapter mpreLessonAdapter;
    private MyLessonAdapter mappLessonAdapter;
    private RecyclerView mlivelist;
    private RecyclerView livelist;
    private RecyclerView appointlist;
    private RecyclerView openlist;
    private RecyclerView prelist;
    private RecyclerView myappointlist;
    private GridLayoutManager livemanager;
    private LinearLayoutManager appointmanager;
    private GridLayoutManager openmanager;
    private LinearLayoutManager premanager;
    private GridLayoutManager myappointmanager;
    private int orderBmpw = 0; // 游标宽度
    private int orderOffset = 0;// // 动画图片偏移量
    private int orderCurrIndex = 0;// 当前页卡编号
    private List<TextView> btnlist = new ArrayList<TextView>();//按键数组
    private int currentPage;
    private int animationTime;
    private int clickPage;


    @Override
    protected int setViewId() {
        return R.layout.lessonfragment_layout;
    }

    @Override
    protected void findViews(View view) {
        ButterKnife.bind(getActivity());
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {

        //view的初始化
        btnlist.add(tvIcon1);
        btnlist.add(tvIcon2);
        btnlist.add(tvIcon3);
        btnlist.add(tvIcon4);
        btnlist.add(tvIcon5);

        inflater = LayoutInflater.from(getActivity());
        mLiveView = inflater.inflate(R.layout.pager_item_layout, null);
        LiveView = inflater.inflate(R.layout.pager_item_layout, null);
        appiontmentView = inflater.inflate(R.layout.pager_item_layout, null);
        openLessonView = inflater.inflate(R.layout.pager_item_layout, null);

        mLessonView = inflater.inflate(R.layout.my_lesson_page, null);
        viewlist.add(mLiveView);
        viewlist.add(LiveView);
        viewlist.add(appiontmentView);
        viewlist.add(openLessonView);
        viewlist.add(mLessonView);
        pagerAdapter = new LessonPagerAdapter(viewlist);
        container.setAdapter(pagerAdapter);
        container.addOnPageChangeListener(this);

        //RecycleView 初始化
        mlivelist = (RecyclerView) mLiveView.findViewById(R.id.page_item);
        livelist = (RecyclerView) LiveView.findViewById(R.id.page_item);
        appointlist = (RecyclerView) appiontmentView.findViewById(R.id.page_item);
        openlist = (RecyclerView) openLessonView.findViewById(R.id.page_item);
        prelist = (RecyclerView) mLessonView.findViewById(R.id.prelive_list);
        myappointlist = (RecyclerView) mLessonView.findViewById(R.id.appoint_list);


        livemanager = new GridLayoutManager(getActivity(), 2);
        appointmanager = new LinearLayoutManager(getActivity());
        openmanager = new GridLayoutManager(getActivity(), 2);
        premanager = new LinearLayoutManager(getActivity());
        myappointmanager = new GridLayoutManager(getActivity(), 2);

        changePage(clickPage);
    }


    @Override
    public void setPresenter(LessonPresenterImpl presenter) {
        //view绑定和启动presenter的处理方法
        this.mLessonPresenterImpl = presenter;
    }

    @Override
    public void onResume() {
        //初始化view
        mLessonPresenterImpl = new LessonPresenterImpl(this);
        mLessonPresenterImpl.attchView(this);
        mLessonPresenterImpl.start();
        super.onResume();
    }


    @Override
    public void loadingdata() {

    }

    @Override


    public void initData(List<String> list) {
        //传数据
        liveAdapter = new LiveAdapter(getActivity(), list);
        livelist.setLayoutManager(livemanager);
        livelist.setAdapter(liveAdapter);

        appointAdapter = new AppointAdapter(getActivity(), list);
        appointlist.setLayoutManager(appointmanager);
        appointlist.setAdapter(appointAdapter);

        openAdapter = new OpenAdapter(getActivity(), list);
        openlist.setLayoutManager(openmanager);
        openlist.setAdapter(openAdapter);

        mpreLessonAdapter = new MyLessonAdapter(getActivity(), list, 0);
        prelist.setLayoutManager(premanager);
        prelist.setAdapter(mpreLessonAdapter);

        mappLessonAdapter = new MyLessonAdapter(getActivity(), list, 1);
        myappointlist.setLayoutManager(myappointmanager);
        myappointlist.setAdapter(mappLessonAdapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        int one = orderOffset * 2 + orderBmpw;// 页卡1 -> 页卡2 偏移量
//        int two = one * 2;// 页卡1 -> 页卡3 偏移量
//        int three = one * 3;//1->4
//        int four = one * 4;//1->5
//        int five = one * 5;//1->6

//
        orderCurrIndex = position;
//        if (animation != null) {
//            animation.setFillAfter(true);// True:图片停在动画结束位置
//            animation.setDuration(animationTime);
//            cursor.startAnimation(animation);
//        }

        if (position != currentPage) {
            for (int i = 0; i < btnlist.size(); i++) {
                btnlist.get(i).setTextColor(getResources().getColor(R.color.colorBottomBlack));
            }
            btnlist.get(position).setTextColor(getResources().getColor(R.color.colorBlue));
        }
        currentPage = container.getCurrentItem();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick({R.id.rl_icon1, R.id.rl_icon2, R.id.rl_icon3, R.id.rl_icon4, R.id.rl_icon5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_icon1:
                clickPage = 0;
                break;
            case R.id.rl_icon2:
                clickPage = 1;
                break;
            case R.id.rl_icon3:
                clickPage = 2;
                break;
            case R.id.rl_icon4:
                clickPage = 3;
                break;
            case R.id.rl_icon5:
                clickPage = 4;
                break;
        }

        changePage(clickPage);
    }



    private void changePage(int clickPage){
        if (clickPage != currentPage) {
            container.setCurrentItem(clickPage);
//            btnlist.get(clickPage).setTextColor(getResources().getColor(R.color.colorBottomRed));
        }
    }

    public void setClickPage(int clickPage) {
        if (container !=  null){
            changePage(clickPage);
        }else {
            this.clickPage = clickPage;
        }
    }
}


