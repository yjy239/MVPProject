package testcom.onlineeducation.view.Adapter.MuliTypeAdapter;

import android.content.Context;
import android.view.View;

import testcom.onlineeducation.R;
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
import testcom.onlineeducation.view.holder.HomeHeaderHolder;
import testcom.onlineeducation.view.holder.HomeHotLessonHolder;
import testcom.onlineeducation.view.holder.HomeHotPlayHolder;
import testcom.onlineeducation.view.holder.HomeListHolder;
import testcom.onlineeducation.view.holder.MessageListHolder;
import testcom.onlineeducation.view.holder.MoneyDetailHolder;
import testcom.onlineeducation.view.holder.MyStudyListHolder;
import testcom.onlineeducation.view.holder.PayTypeHolder;
import testcom.onlineeducation.view.holder.StudyMovieListHolder;
import testcom.onlineeducation.view.holder.StudyPicContentHolder;
import testcom.onlineeducation.view.holder.StudyPicListHolder;
import testcom.onlineeducation.view.holder.StudySigleTitleHolder;
import testcom.onlineeducation.view.holder.StudyTextContentHolder;
import testcom.onlineeducation.view.holder.StudyTextListHolder;
import testcom.onlineeducation.view.holder.StudyVideoListHolder;

//import testcom.onlineeducation.bean.EduBean;


/**
 * Created by Administrator on 2017/3/29 0029.
 */
public class TypeFactoryForList implements TypeFactory {
    private final int TYPE_HOME_HEADER = R.layout.item_home_header;
    private final int TYPE_HOME_LIST = R.layout.item_home_list;
    private final int TYPE_HOME_HOT_PLAY = R.layout.item_hot_layout;
    private final int TYPE_HOME_HOT_LESSON = R.layout.openlesson_layout;

    private final int TYPE_STUDY_TEXT = R.layout.item_study_text;
    private final int TYPE_STUDY_PIC = R.layout.item_study_pic;
    private final int TYPE_STUDY_VIDEO = R.layout.item_study_video;
    private final int TYPE_STUDY_MOVIE = R.layout.item_study_movie;
    private final int TYPE_STUDY_SINGLE_TITLE = R.layout.item_study_single_title;
    private final int TYPE_STUDY_TEXT_CONTENT = R.layout.item_study_text_content;
    private final int TYPE_STUDY_PIC_CONTENT = R.layout.item_study_pic_content;
    private final int TYPE_PAY_TYPE_POP = R.layout.item_pay_type;
    private final int TYPE_MESSAGE_LIST_BEAN = R.layout.item_message_list_bean;
    private final int TYPE_MYSTUDY_LIST = R.layout.item_mystudy;
    private final int TYPE_MONEY_DETAIL = R.layout.item_money_detail;

    private Context context;

    public TypeFactoryForList() {

    }

    public TypeFactoryForList(Context context) {
        this.context = context;
    }

    @Override
    public int type(HomeHeaderBean bean) {
        return TYPE_HOME_HEADER;
    }

    @Override
    public int type(HomeListBean bean) {
        return TYPE_HOME_LIST;
    }

    @Override
    public int type(HomeHotPlayBean bean) {
        return TYPE_HOME_HOT_PLAY;
    }

    @Override
    public int type(HomeHotLessonBean bean) {
        return TYPE_HOME_HOT_LESSON;
    }

    @Override
    public int type(StudyTextListBean one) {
        return TYPE_STUDY_TEXT;
    }

    @Override
    public int type(StudyPicListBean bean) {
        return TYPE_STUDY_PIC;
    }

    @Override
    public int type(StudyVideoListBean bean) {
        return TYPE_STUDY_VIDEO;
    }

    @Override
    public int type(StudyMovieListBean bean) {
        return TYPE_STUDY_MOVIE;
    }

    @Override
    public int type(StudySingleTitleBean bean) {
        return TYPE_STUDY_SINGLE_TITLE;
    }

    @Override
    public int type(StudyTextContentBean bean) {
        return TYPE_STUDY_TEXT_CONTENT;
    }

    @Override
    public int type(StudyPicContentBean bean) {
        return TYPE_STUDY_PIC_CONTENT;
    }

    @Override
    public int type(MessageListBean bean) {
        return TYPE_MESSAGE_LIST_BEAN;
    }

    @Override
    public int type(MoneyDetailBean bean) {
        return TYPE_MONEY_DETAIL;
    }

    @Override
    public int type(PayTypeBean bean) {
        return TYPE_PAY_TYPE_POP;
    }

    @Override
    public int type(MyStudyBean bean) {
        return TYPE_MYSTUDY_LIST;
    }



    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {
        if (TYPE_STUDY_TEXT == type) {
            return new StudyTextListHolder(itemView, context);
        } else if (TYPE_STUDY_PIC == type) {
            return new StudyPicListHolder(itemView, context);
        } else if (TYPE_STUDY_VIDEO == type) {
            return new StudyVideoListHolder(itemView, context);
        } else if (TYPE_STUDY_MOVIE == type) {
            return new StudyMovieListHolder(itemView, context);
        } else if (TYPE_STUDY_SINGLE_TITLE == type) {
            return new StudySigleTitleHolder(itemView);
        } else if (TYPE_STUDY_TEXT_CONTENT == type) {
            return new StudyTextContentHolder(itemView, context);
        } else if (TYPE_STUDY_PIC_CONTENT == type) {
            return new StudyPicContentHolder(itemView, context);
        } else if (TYPE_PAY_TYPE_POP == type) {
            return new PayTypeHolder(itemView, context);
        } else if (TYPE_MESSAGE_LIST_BEAN == type) {
            return new MessageListHolder(itemView, context);
        } else if (TYPE_MYSTUDY_LIST == type) {
            return new MyStudyListHolder(itemView, context);
        } else if (TYPE_HOME_HEADER == type) {
            return new HomeHeaderHolder(itemView, context);
        } else if (TYPE_HOME_LIST == type) {
            return new HomeListHolder(itemView, context);
        } else if (TYPE_HOME_HOT_PLAY == type) {
            return new HomeHotPlayHolder(itemView, context);
        } else if (TYPE_HOME_HOT_LESSON == type) {
            return new HomeHotLessonHolder(itemView, context);
        }else if (TYPE_MONEY_DETAIL == type){
            return new MoneyDetailHolder(itemView);
        }
        return null;
    }
}