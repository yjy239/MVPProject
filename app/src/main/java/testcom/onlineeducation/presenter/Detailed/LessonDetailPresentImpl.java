package testcom.onlineeducation.presenter.Detailed;

import testcom.onlineeducation.BasePresenter;
import testcom.onlineeducation.Contract.LessonDetailContract;

/**
 * Created by Administrator on 2017/6/23.
 */
public class LessonDetailPresentImpl extends BasePresenter<LessonDetailContract.View>
        implements LessonDetailContract.Presenter {

    public LessonDetailContract.View mView;

    public LessonDetailPresentImpl(LessonDetailContract.View mView){
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
