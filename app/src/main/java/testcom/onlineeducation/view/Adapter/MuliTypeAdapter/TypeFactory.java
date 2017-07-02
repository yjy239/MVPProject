package testcom.onlineeducation.view.Adapter.MuliTypeAdapter;

import android.view.View;

//import testcom.onlineeducation.bean.EduBean;
import testcom.onlineeducation.bean.HomeHeaderBean;
import testcom.onlineeducation.bean.HomeHotLessonBean;
import testcom.onlineeducation.bean.HomeHotPlayBean;
import testcom.onlineeducation.bean.HomeListBean;
import testcom.onlineeducation.bean.MessageListBean;
import testcom.onlineeducation.bean.MoneyDetailBean;
import testcom.onlineeducation.bean.MyStudyBean;
import testcom.onlineeducation.bean.PayTypeBean;
import testcom.onlineeducation.bean.StudyMovieListBean;
import testcom.onlineeducation.bean.StudyPicContentBean;
import testcom.onlineeducation.bean.StudyPicListBean;
import testcom.onlineeducation.bean.StudySingleTitleBean;
import testcom.onlineeducation.bean.StudyTextContentBean;
import testcom.onlineeducation.bean.StudyTextListBean;
import testcom.onlineeducation.bean.StudyVideoListBean;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public interface TypeFactory {
    //home-start
    int type(HomeHeaderBean bean);
    int type(HomeListBean bean);
    int type(HomeHotPlayBean bean);
    int type(HomeHotLessonBean bean);
    //home-end

    //study-start
    int type(StudyTextListBean bean);
    int type(StudyPicListBean bean);
    int type(StudyVideoListBean bean);
    int type(StudyMovieListBean bean);
    int type(StudySingleTitleBean bean);
    int type(StudyTextContentBean bean);
    int type(StudyPicContentBean bean);
    //study-end

    //my-start
    int type(MessageListBean bean);
    int type(MoneyDetailBean bean);
    //my-end

    //deatil-start
    int type(PayTypeBean bean);
    int type(MyStudyBean bean);
//    int type(EduBean bean);
    //deatil-end

    BaseViewHolder createViewHolder(int type, View itemView);
}
