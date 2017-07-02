package testcom.onlineeducation.presenter;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.MainContract;
import testcom.onlineeducation.Contract.StudyContract;
import testcom.onlineeducation.DAO.UserDao;
import testcom.onlineeducation.bean.StudyMovieListBean;
import testcom.onlineeducation.bean.StudyPicListBean;
import testcom.onlineeducation.bean.StudyTextListBean;
import testcom.onlineeducation.bean.StudyVideoListBean;
import testcom.onlineeducation.model.HotBean;
import testcom.onlineeducation.network.BaseCallBack;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/16.
 */
public class StudyPresentImpl extends BasePresenter<StudyContract.StudyView> implements StudyContract.StudyPresent {

    private StudyContract.StudyView mView;
    private List<Visitable> textList = new ArrayList<>();
    private List<Visitable> picList = new ArrayList<>();
    private List<Visitable> videoList = new ArrayList<>();
    private List<Visitable> movieList = new ArrayList<>();

    //构造函数设置present
    public StudyPresentImpl(StudyContract.StudyView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }


    public void start(){
        loadData();
    }

    //初始化数据
    public void loadData(){
        for (int i = 0; i < 12; i++) {
            StudyTextListBean bean = new StudyTextListBean();
            String name = "托福必考300词"+i+1;
            bean.setName(name);
            String author = "新华书店";
            bean.setAuthor(author);
            String money = "¥"+ i ;
            bean.setMoney(money);
            textList.add(bean);
        }
        for (int i = 0; i < 12; i++) {
            StudyPicListBean bean = new StudyPicListBean();
            String name = "托福必考300词"+i+1;
            bean.setName(name);
            String author = "新华书店";
            bean.setAuthor(author);
            String money = "¥"+ i ;
            bean.setMoney(money);
            picList.add(bean);
        }
        for (int i = 0; i < 12; i++) {
            StudyVideoListBean bean = new StudyVideoListBean();
            String name = "托福必考300词"+i+1;
            bean.setName(name);
            String author = "新华书店";
            bean.setAuthor(author);
            String money = "¥"+ i ;
            bean.setMoney(money);
            videoList.add(bean);
        }
        for (int i = 0; i < 12; i++) {
            StudyMovieListBean bean = new StudyMovieListBean();
            String name = "托福必考300词"+i+1;
            bean.setName(name);
            String author = "新华书店";
            bean.setAuthor(author);
            String money = "¥"+ i ;
            bean.setMoney(money);
            movieList.add(bean);
        }
        mView.initData(textList, 0);
        mView.initData(picList, 1);
        mView.initData(videoList, 2);
        mView.initData(movieList, 3);
    }
}
