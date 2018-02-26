package cn.ecar.insurance.dao.base;

/**
 *
 * @author lq
 * @date 2017/12/4
 */

public class PhotoBean extends BaseEntity {
        private String Photo;
        private String BigPhoto;

        @Override
        public String getMsg() {
            return super.getMsg();
        }

        public String getBigPhoto() {
            return BigPhoto;
        }

        public String getPhoto() {
            return Photo;
        }

        @Override
        public String getResult() {
            return super.getResult();
        }

        @Override
        public String toString() {
            return "SfzPhotoEntity{" +
                    "result='" + result + '\'' +
                    ", msg='" + msg + '\'' +
                    ", Photo='" + Photo + '\'' +
                    ", BigPhoto='" + BigPhoto + '\'' +
                    '}';
        }
}
