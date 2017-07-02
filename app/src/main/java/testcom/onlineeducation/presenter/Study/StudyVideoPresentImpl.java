package testcom.onlineeducation.presenter.Study;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.StudyVideoContract;
import testcom.onlineeducation.bean.StudySingleTitleBean;
import testcom.onlineeducation.bean.StudyTextContentBean;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/16.
 */
public class StudyVideoPresentImpl extends BasePresenter<StudyVideoContract.StudyVideoView> implements StudyVideoContract.StudyVideoPresent {

    private StudyVideoContract.StudyVideoView mView;
    private List<Visitable> list = new ArrayList<>();

    //构造函数设置present
    public StudyVideoPresentImpl(StudyVideoContract.StudyVideoView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }


    public void start() {
        loadData();
    }

    //初始化数据
    public void loadData() {
        StudySingleTitleBean singleTitleBean = new StudySingleTitleBean();
        singleTitleBean.setTitle("置身《奇幻森林》的学习语句中……");
        list.add(singleTitleBean);
        for (int i = 0; i < 22; i++) {
            StudyTextContentBean bean = new StudyTextContentBean();
            if (i % 2 == 0) {
                String studyLan;
                String translateLan;
                if (i == 0) {
                    studyLan = "Did you go to class today?Did you go to class today?Did you go to class today?Did you go to class today?";
                    translateLan = "你今天去上课了吗？你今天去上课了吗？你今天去上课了吗？你今天去上课了吗？";
                } else {
                    studyLan = "Did you go to class today?";
                    translateLan = "你今天去上课了吗？";
                }
                bean.setStudyLan(studyLan);
                bean.setTranslateLan(translateLan);
                bean.setSingle(false);
            } else {
                String studyLan = "apple";
                bean.setStudyLan(studyLan);
                String translateLan = "苹果";
                bean.setTranslateLan(translateLan);
                bean.setSingle(true);
            }
            list.add(bean);
        }
        mView.initData(list);

    }
}
