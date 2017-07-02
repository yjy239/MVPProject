package testcom.onlineeducation.bean;

import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.TypeFactory;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/17 0017.
 */
public class StudyTextContentBean implements Visitable{
    private String studyLan;
    private String translateLan;
    private int time;
    private boolean isSingle;

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean sigle) {
        isSingle = sigle;
    }

    public String getStudyLan() {
        return studyLan;
    }

    public void setStudyLan(String studyLan) {
        this.studyLan = studyLan;
    }

    public String getTranslateLan() {
        return translateLan;
    }

    public void setTranslateLan(String translateLan) {
        this.translateLan = translateLan;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
