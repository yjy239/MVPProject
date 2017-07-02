package testcom.onlineeducation.bean;

import android.os.Parcel;
import android.os.Parcelable;

import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.TypeFactory;
import testcom.onlineeducation.view.Adapter.MuliTypeAdapter.Visitable;

/**
 * Created by Administrator on 2017/6/17 0017.
 */
public class StudyPicContentBean implements Visitable, Parcelable {
    private String studyLan;
    private String translateLan;
    private String pic;
    private int time;
    private boolean isSingle;

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.studyLan);
        dest.writeString(this.translateLan);
        dest.writeString(this.pic);
        dest.writeInt(this.time);
    }

    public StudyPicContentBean() {
    }

    protected StudyPicContentBean(Parcel in) {
        this.studyLan = in.readString();
        this.translateLan = in.readString();
        this.pic = in.readString();
        this.time = in.readInt();
    }

    public static final Parcelable.Creator<StudyPicContentBean> CREATOR = new Parcelable.Creator<StudyPicContentBean>() {
        @Override
        public StudyPicContentBean createFromParcel(Parcel source) {
            return new StudyPicContentBean(source);
        }

        @Override
        public StudyPicContentBean[] newArray(int size) {
            return new StudyPicContentBean[size];
        }
    };
}
