package testcom.onlineeducation.presenter;

import java.util.ArrayList;
import java.util.List;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.MainContract;
import testcom.onlineeducation.DAO.UserDao;
import testcom.onlineeducation.bean.BannerListBean;
import testcom.onlineeducation.bean.HomeHeaderBean;
import testcom.onlineeducation.bean.HomeHotLessonBean;
import testcom.onlineeducation.bean.HomeHotPlayBean;
import testcom.onlineeducation.bean.HomeListBean;
import testcom.onlineeducation.bean.MessageListBean;
import testcom.onlineeducation.network.BaseCallBack;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/16.
 */
public class MainPresentImpl extends BasePresenter<MainContract.MainView> implements MainContract.MainPresenter {

    private MainContract.MainView mView;
    private List<Visitable> list = new ArrayList<>();

    //构造函数设置present
    public MainPresentImpl(MainContract.MainView mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }


    public void start(){
        loadData();
    }

    //初始化数据
    public void loadData(){
        HomeHeaderBean headerBean = new HomeHeaderBean();
        ArrayList<Visitable> messageList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            messageList.add(new MessageListBean());
        }
        headerBean.setMessageList(messageList);
        ArrayList<BannerListBean> bannerList = new ArrayList<>();
        bannerList.add(new BannerListBean("http://pic39.nipic.com/20140326/17525920_145247224339_2.jpg"));
        bannerList.add(new BannerListBean("http://pic8.nipic.com/20100809/668573_144908068462_2.jpg"));
        bannerList.add(new BannerListBean("http://pic.58pic.com/58pic/13/92/94/65w58PICPUD_1024.jpg"));
        headerBean.setBannerList(bannerList);
        list.add(headerBean);

        HomeListBean hotPlay = new HomeListBean();
        hotPlay.setType("正在热播");
        hotPlay.setMaxShowCount(4);
        ArrayList<Visitable> hotList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            HomeHotPlayBean bean = new HomeHotPlayBean();
            bean.setAuthor("老周");
            bean.setMoney(i*10+"");
            bean.setName("《猫和老鼠》");
            hotList.add(bean);
        }
        hotPlay.setList(hotList);
        list.add(hotPlay);

        HomeListBean hotLesson = new HomeListBean();
        hotLesson.setType("热门课程");
        hotLesson.setMaxShowCount(4);
        ArrayList<Visitable> LessonList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            HomeHotLessonBean bean = new HomeHotLessonBean();
            bean.setAuthor("老周");
            bean.setMoney(i*10+"");
            bean.setName("《猫和老鼠》");
            LessonList.add(bean);
        }
        hotLesson.setList(LessonList);
        list.add(hotLesson);
        

        mView.initData(list);
        UserDao.FirstRequest("46", "869515024751809", new BaseCallBack() {
            @Override
            public void success(Object data) {
                mView.loadingData();
            }

            @Override
            public void failed(int errorCode, Object data) {

            }

            @Override
            public void failed(Object data) {

            }
        });

    }
}
