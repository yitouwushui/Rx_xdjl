package cn.ecar.insurance.dao.gson;

import cn.ecar.insurance.dao.base.BaseGson;

/**
 * @author ding
 * @date 2018/3/8
 */

public class UploadImageGson extends BaseGson {

    /**
     * filePath : /mnt/ecar-upload/4/3WOR1C6W4W1N5CZUCE5WWHE655STYB6L.jpg
     */

    private String filePath;

    private int type;

    private String typeStr;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
