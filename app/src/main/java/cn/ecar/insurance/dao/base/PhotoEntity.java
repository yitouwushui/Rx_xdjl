package cn.ecar.insurance.dao.base;

/**
 * Created by lq on 2017/12/4.
 */

public class PhotoEntity extends BaseEntity {
    private String Position;
    private String PositionPhoto;
    private String PositionBigPhoto;
    private String TrueName;
    private String TrueNamePhoto;
    private String TrueNameBigPhoto;

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getPositionPhoto() {
        return PositionPhoto;
    }

    public void setPositionPhoto(String positionPhoto) {
        PositionPhoto = positionPhoto;
    }

    public String getTrueName() {
        return TrueName;
    }

    public void setTrueName(String trueName) {
        TrueName = trueName;
    }

    public String getTrueNamePhoto() {
        return TrueNamePhoto;
    }

    public void setTrueNamePhoto(String trueNamePhoto) {
        TrueNamePhoto = trueNamePhoto;
    }

    @Override
    public String toString() {
        return "PhotoEntity{" +
                "result='" + result + '\'' +
                ", msg='" + msg + '\'' +
                ", Position='" + Position + '\'' +
                ", PositionPhoto='" + PositionPhoto + '\'' +
                ", TrueName='" + TrueName + '\'' +
                ", TrueNamePhoto='" + TrueNamePhoto + '\'' +
                '}';
    }

}
