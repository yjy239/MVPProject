package testcom.onlineeducation.ui.Activity.Detailed;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.Contract.LessonDetailContract;
import testcom.onlineeducation.R;
import testcom.onlineeducation.presenter.Detailed.LessonDetailPresentImpl;
import testcom.onlineeducation.ui.Activity.BaseActivity;

/**
 * Created by Administrator on 2017/6/23.
 */
public class LessonDetailActivity extends BaseActivity implements LessonDetailContract.View {
    private static final String TAG = "LessonDetailActivity";


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.v)
    View v;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_head)
    RelativeLayout rlHead;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.bottom_play)
    ImageView bottomPlay;
    @BindView(R.id.screen_control)
    ImageView screenControl;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rl_video)
    RelativeLayout rlVideo;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.rl_author)
    RelativeLayout rlAuthor;
    @BindView(R.id.tv_all_content)
    TextView tvAllContent;
    @BindView(R.id.tv_hide_more)
    TextView tvHideMore;
    @BindView(R.id.v_line)
    View vLine;
    @BindView(R.id.tv_book_teacher)
    TextView tvBookTeacher;
    @BindView(R.id.tv_show_more)
    TextView tvShowMore;
    @BindView(R.id.line_bottom)
    View lineBottom;
    @BindView(R.id.tv_book_teacher_bottom)
    TextView tvBookTeacherBottom;

    private LessonDetailPresentImpl mPresenter;

    @Override
    protected int setViewId() {
        return R.layout.activity_lesson_detail;
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
        mPresenter = new LessonDetailPresentImpl(this);
        mPresenter.attchView(this);
        mPresenter.start();
        tvAllContent.setText(getString(R.string.long_text));
        String s = tvAllContent.getText().toString();
        if (s.length()>200){
            tvAllContent.setText(s.substring(0,201)+"...");
        }
    }

    @Override
    public void setPresenter(LessonDetailPresentImpl presenter) {
        this.mPresenter = presenter;
    }

    @OnClick({R.id.back, R.id.play, R.id.bottom_play, R.id.screen_control, R.id.tv_hide_more, R.id.tv_book_teacher, R.id.tv_show_more, R.id.tv_book_teacher_bottom})
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

                break;
            case R.id.tv_hide_more:
                tvHideMore.setVisibility(View.GONE);
                vLine.setVisibility(View.GONE);
                tvBookTeacher.setVisibility(View.GONE);
                tvShowMore.setVisibility(View.VISIBLE);
                lineBottom.setVisibility(View.VISIBLE);
                tvBookTeacherBottom.setVisibility(View.VISIBLE);
                tvAllContent.setText(getString(R.string.long_text));
                String s = tvAllContent.getText().toString();
                if (s.length()>200){
                    tvAllContent.setText(s.substring(0,201)+"...");
                }
                Log.e(TAG, "onClick: "+ tvAllContent.getText().length() );
                break;
            case R.id.tv_show_more:
                tvHideMore.setVisibility(View.VISIBLE);
                vLine.setVisibility(View.VISIBLE);
                tvBookTeacher.setVisibility(View.VISIBLE);
                tvShowMore.setVisibility(View.GONE);
                lineBottom.setVisibility(View.GONE);
                tvBookTeacherBottom.setVisibility(View.GONE);
                tvAllContent.setText(getString(R.string.long_text));
                Log.e(TAG, "onClick: "+ tvAllContent.getText().length() );
                break;
            case R.id.tv_book_teacher:
            case R.id.tv_book_teacher_bottom:
                Intent appoint  = new Intent(LessonDetailActivity.this,TeacherDetailActivity.class);
                startActivity(appoint);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }


}
