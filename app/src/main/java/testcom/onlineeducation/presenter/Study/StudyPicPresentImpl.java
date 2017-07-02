package testcom.onlineeducation.presenter.Study;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.StudyPicContract;
import testcom.onlineeducation.Contract.StudyTextContract;
import testcom.onlineeducation.bean.StudyPicContentBean;
import testcom.onlineeducation.bean.StudySingleTitleBean;
import testcom.onlineeducation.bean.StudyTextContentBean;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/16.
 */
public class StudyPicPresentImpl extends BasePresenter<StudyPicContract.StudyPicView> implements StudyPicContract.StudyPicPresent {

    private StudyPicContract.StudyPicView mView;
    private List<Visitable> list = new ArrayList<>();

    //构造函数设置present
    public StudyPicPresentImpl(StudyPicContract.StudyPicView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }


    public void start() {
        loadData();
    }

    //初始化数据
    public void loadData() {
        for (int i = 0; i < 12; i++) {
            StudyPicContentBean bean = new StudyPicContentBean();
            if (i % 2 == 0) {
                String studyLan;
                String translateLan;

                studyLan = "The little Dinosaur tells a story";
                translateLan = "小恐龙讲故事";

                bean.setStudyLan(studyLan);
                bean.setTranslateLan(translateLan);
            } else {
                String studyLan = "apple";
                bean.setStudyLan(studyLan);
                String translateLan = "苹果";
                bean.setTranslateLan(translateLan);
            }
            list.add(bean);
        }
        mView.initData(list);

    }
}
