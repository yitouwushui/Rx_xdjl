package cn.ecar.insurance.dao.base;

/**
 * Created by yx on 2016/10/11.
 * aes加密实体类
 */

public class AesEntity extends BaseEntity<AesEntity.RowsBean> {


    private String d;

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public static class RowsBean {

        private String d;

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

    }


}
