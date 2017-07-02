package testcom.onlineeducation.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.R;
import testcom.onlineeducation.i.IEvent;
import testcom.onlineeducation.i.RxPost;
import testcom.onlineeducation.ui.Fragment.LessonFragment;
import testcom.onlineeducation.ui.Fragment.MainFragment;
import testcom.onlineeducation.ui.Fragment.MyFragment;
import testcom.onlineeducation.ui.Fragment.StudyFragment;
import testcom.onlineeducation.utils.LoginUtils;
import testcom.onlineeducation.utils.Rx.RxBus;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.main)
    LinearLayout main;
    @BindView(R.id.study)
    LinearLayout study;
    @BindView(R.id.lesson)
    LinearLayout lesson;
    @BindView(R.id.my)
    LinearLayout my;
    @BindView(R.id.bottom_tab)
    LinearLayout bottomTab;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tv_main)
    TextView tvMain;
    @BindView(R.id.tv_study)
    TextView tvStudy;
    @BindView(R.id.tv_lesson)
    TextView tvLesson;
    @BindView(R.id.tv_my)
    TextView tvMy;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.iv_study)
    ImageView ivStudy;
    @BindView(R.id.iv_lesson)
    ImageView ivLesson;
    @BindView(R.id.iv_my)
    ImageView ivMy;

    private long exitTime = 0;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private MainFragment mainFragment;
    private LessonFragment lessonFragment;
    private StudyFragment studyFragment;
    private MyFragment myFragment;

    private static final String HOME_TAG = "home_tag";
    private static final String LESSON_TAG = "lesson_tag";
    private static final String STUDY_TAG = "study_tag";
    private static final String MY_TAG = "my_tag";

    private Fragment[] fragments;
    private Fragment showFragment;
    /* 当前显示的是第几个fragment */
    int currentFragmentIndex = 0;
    private int clickIndex = 0;
    private String[] tags = new String[]{HOME_TAG, STUDY_TAG, LESSON_TAG, MY_TAG};
    private int[] iconClicks = new int[]{R.drawable.icon_main_home_click, R.drawable.icon_main_study_click, R.drawable.icon_main_lesson_click, R.drawable.icon_main_my_click};
    private int[] iconDefaults = new int[]{R.drawable.icon_main_home_default, R.drawable.icon_main_study_default, R.drawable.icon_main_lesson_default, R.drawable.icon_main_my_default};
    private ArrayList<TextView> tvList = new ArrayList<>();
    private ArrayList<ImageView> ivList = new ArrayList<>();

    private String action;

    @Override
    protected int setViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
        tvList.add(tvMain);
        tvList.add(tvStudy);
        tvList.add(tvLesson);
        tvList.add(tvMy);
        ivList.add(ivHome);
        ivList.add(ivStudy);
        ivList.add(ivLesson);
        ivList.add(ivMy);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        if(LoginUtils.isLogin()){
            RxBus.getDefault().post(new RxPost(IEvent.LOGIN));
        }
    }


    @Override
    protected void handlerSaveInstanceState(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        if (savedInstanceState != null) {
            mainFragment = (MainFragment) manager.findFragmentByTag(HOME_TAG);
            studyFragment = (StudyFragment) manager.findFragmentByTag(STUDY_TAG);
            lessonFragment = (LessonFragment) manager.findFragmentByTag(LESSON_TAG);
            myFragment = (MyFragment) manager.findFragmentByTag(MY_TAG);

            //初始化，主页界面
            if (mainFragment == null)
                mainFragment = new MainFragment();
            if (studyFragment == null)
                studyFragment = new StudyFragment();
            if (lessonFragment == null)
                lessonFragment = new LessonFragment();
            if (myFragment == null)
                myFragment = new MyFragment();

            fragments = new Fragment[]{
                    mainFragment,
                    studyFragment,
                    lessonFragment,
                    myFragment
            };
            for (int i = 0; i < fragments.length; i++) {
                if (!fragments[i].isAdded()) {
                    transaction.add(R.id.content, fragments[i], tags[i]);
                }
            }
            transaction.show(mainFragment).hide(studyFragment).hide(lessonFragment).hide(myFragment).commit();
        } else {
            initFragment();
        }

        super.handlerSaveInstanceState(savedInstanceState);
    }

    public void initFragment() {
        try {
            //初始化，主页界面
            mainFragment = new MainFragment();
            studyFragment = new StudyFragment();
            lessonFragment = new LessonFragment();
            myFragment = new MyFragment();

            fragments = new Fragment[]{
                    mainFragment,
                    studyFragment,
                    lessonFragment,
                    myFragment
            };
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content, mainFragment, HOME_TAG)
                    .add(R.id.content, studyFragment, STUDY_TAG)
                    .show(mainFragment)
                    .hide(studyFragment)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.main, R.id.study, R.id.lesson, R.id.my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main:
                clickIndex = 0;
                break;
            case R.id.study:
                clickIndex = 1;
                break;
            case R.id.lesson:
                clickIndex = 2;
                break;
            case R.id.my:
                clickIndex = 3;
                break;
        }

        changeFragment();
    }

    public void changeFragment() {
        if (clickIndex != currentFragmentIndex) {
            // 单击别的按钮，显示另外一个fragment
            showFragment = fragments[clickIndex];
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!showFragment.isAdded()) {
                // 以前没有显示过
                transaction.add(R.id.content, showFragment, tags[clickIndex]);
            }
            transaction.hide(fragments[currentFragmentIndex])
                    .show(showFragment)
                    .commit();

            for (int i = 0; i < tvList.size(); i++) {
                tvList.get(i).setTextColor(ContextCompat.getColor(this, R.color.colorBottomBlack));
                ivList.get(i).setImageResource(iconDefaults[i]);
            }
            tvList.get(clickIndex).setTextColor(ContextCompat.getColor(this, R.color.colorBlue));
            ivList.get(clickIndex).setImageResource(iconClicks[clickIndex]);
            currentFragmentIndex = clickIndex;
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        String action = intent.getStringExtra("action");
        if (action == null) {
            Log.e(TAG, "onNewIntent: action == null");
        } else {
            this.action = action;
        }
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        if (action != null) {
            switch (action) {
                case "intoMyLesson":
                    clickIndex = 2;
                    changeFragment();
                    lessonFragment.setClickPage(4);
                    break;
            }
            action = null;
        }
        super.onResume();
    }

}
