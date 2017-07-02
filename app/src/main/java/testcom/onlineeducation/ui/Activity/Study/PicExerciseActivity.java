package testcom.onlineeducation.ui.Activity.Study;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.R;
import testcom.onlineeducation.bean.StudyPicContentBean;
import testcom.onlineeducation.network.HttpApi;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.utils.voice.VoiceManager;
import testcom.onlineeducation.view.Adapter.MyPagerAdapter;

/**
 * Created by Administrator on 2017/6/19 0019.
 */
public class PicExerciseActivity extends BaseActivity {
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
    @BindView(R.id.vp)
    ViewPager vp;

    private VoiceManager voiceManager;
    private String testPath;
    private ArrayList<StudyPicContentBean> list = new ArrayList<>();
    private ArrayList<PageCard> pageList = new ArrayList<>();
    @Override
    protected int setViewId() {
        return R.layout.activity_pic_exercise;
    }

    @Override
    protected void findViews() {
        ButterKnife.bind(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        list = getIntent().getParcelableArrayListExtra("model");
        List<View> viewList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            View view = inflater.inflate(R.layout.page_pic_exercise, null);
            PageCard pageCard = new PageCard(view, list.get(i));
            viewList.add(view);
            pageList.add(pageCard);
        }
        vp.setAdapter(new MyPagerAdapter(viewList));
    }

    @Override
    protected void initEvent() {
        voiceManager = VoiceManager.getInstance(this);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                deleteCash();
                pageList.get(position).reSet();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void init() {

    }

    @OnClick(R.id.back)
    public void onClick() {
        finish();
    }

    public class PageCard {
        private static final String TAG = "PageCard";
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_word)
        TextView tvWord;
        @BindView(R.id.tv_word_ex)
        TextView tvWordEx;
        @BindView(R.id.rl_word)
        RelativeLayout rlWord;
        @BindView(R.id.tv_sentence)
        TextView tvSentence;
        @BindView(R.id.tv_sentence_ex)
        TextView tvSentenceEx;
        @BindView(R.id.rl_sentence)
        RelativeLayout rlSentence;
        @BindView(R.id.iv_count)
        ImageView ivCount;
        @BindView(R.id.play_voice)
        View playVoice;
        @BindView(R.id.record_voice)
        ImageView recordVoice;
        @BindView(R.id.share_voice)
        View shareVoice;
        @BindView(R.id.tv_length)
        TextView tvLength;

        StudyPicContentBean model;
        private boolean firstRecord = true;
        private boolean firstPlay = true;



        public PageCard(View view, StudyPicContentBean model) {
            ButterKnife.bind(this, view);
            this.model = model;

            setPageData();
        }

        @OnClick({R.id.play_voice, R.id.record_voice, R.id.share_voice})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.play_voice:
                    voiceManager.stopVoiceRecord();
                    firstRecord = true;
                    if (firstPlay) {
                        try {
                            if (testPath != null) {
                                voiceManager.startPlay(testPath);
                                firstPlay = false;
                            }else {
                                showShortToast("没有录音");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showShortToast("没有录音");
                        }
                    } else {
                        voiceManager.stopPlay();
                        firstPlay = true;
                    }
                    break;
                case R.id.record_voice:
                    voiceManager.stopPlay();
                    firstPlay = true;

                    if (firstRecord) {
                        voiceManager.pauseOrStartVoiceRecord();
                        voiceManager.setVoiceRecordListener(new VoiceManager.VoiceRecordCallBack() {
                            @Override
                            public void recDoing(long time, String strTime) {
                                tvLength.setText(strTime);
                            }

                            @Override
                            public void recVoiceGrade(int grade) {
//                            voicLine.setVolume(grade);
                            }

                            @Override
                            public void recStart(boolean init) {
//                            mIvPauseContinue.setImageResource(R.drawable.icon_pause);
//                            voicLine.setContinue();
                            }

                            @Override
                            public void recPause(String str) {
//                            mIvPauseContinue.setImageResource(R.drawable.icon_continue);
//                            voicLine.setPause();
                            }


                            @Override
                            public void recFinish(long length, String strLength, String path) {
                                recordVoice.setImageResource(R.drawable.icon_record_finish);
                                testPath = path;
                            }
                        });
                        voiceManager.startVoiceRecord(Environment.getExternalStorageDirectory().getPath() + "/OnlineEducation/audio");
                        firstRecord = false;
                    } else {
                        voiceManager.stopVoiceRecord();
                        firstRecord = true;
                    }
                    break;
                case R.id.share_voice:
                    if (testPath != null){
                        showShortToast("调用分享sdk");
                    }else {
                        showShortToast("请先录音再分享");
                    }
                    break;
            }
        }

        private void setPageData() {
            Glide.with(PicExerciseActivity.this)
                    .load(HttpApi.getFullImageUrl(model.getPic()))
                    .placeholder(R.drawable.default_loading_pic)
//                .transform(new GlideCircleTransform(this.context))
                    .into(iv);
            if (!model.isSingle()) {
                rlWord.setVisibility(View.GONE);
                rlSentence.setVisibility(View.VISIBLE);
                tvSentence.setText(model.getStudyLan());
                tvSentenceEx.setText(model.getTranslateLan());
            } else {
                tvWord.setText(model.getStudyLan());
                tvWordEx.setText(model.getTranslateLan());
            }
        }

        public void reSet(){
            tvLength.setText("00:00:00");
            recordVoice.setImageResource(R.drawable.icon_record_default);
        }

    }

    private void deleteCash() {
        try {
            String s = Environment.getExternalStorageDirectory().getPath() + "/OnlineEducation/audio";
            File file = new File(s);
            String[] childFilePaths = file.list();
            if (childFilePaths != null) {
                for (int i = 0; i < childFilePaths.length; i++) {
                    File childFile = new File(file.getAbsolutePath() + "/" + childFilePaths[i]);
                    childFile.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
