package testcom.onlineeducation.ui.Activity.Study;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import testcom.onlineeducation.R;
import testcom.onlineeducation.ui.Activity.BaseActivity;
import testcom.onlineeducation.utils.voice.VoiceManager;

/**
 * Created by Administrator on 2017/6/17 0017.
 */
public class ExerciseActivity extends BaseActivity {
    private static final String TAG = "ExerciseActivity";
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

    private boolean firstRecord = true;
    private boolean firstPlay = true;

    private VoiceManager voiceManager;
    private String testPath;

    @Override
    protected int setViewId() {
        return R.layout.activity_exercise;
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
        deleteCash();
        voiceManager = VoiceManager.getInstance(this);
        boolean isSingle = getIntent().getBooleanExtra("isSingle", false);
        String studyLan = getIntent().getStringExtra("studyLan");
        String translateLan = getIntent().getStringExtra("translateLan");
        if (!isSingle) {
            rlWord.setVisibility(View.GONE);
            rlSentence.setVisibility(View.VISIBLE);
            tvSentence.setText(studyLan);
            tvSentenceEx.setText(translateLan);
        }else {
            tvWord.setText(studyLan);
            tvWordEx.setText(translateLan);
        }
    }


    @OnClick({R.id.back, R.id.play_voice, R.id.record_voice, R.id.share_voice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
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
