package cn.ecar.insurance.mvvm.model.home;

/**
 *
 * @author ding
 * @date 2017/12/19
 */

public class AdvertsModel {

    private static volatile AdvertsModel instance;

    public static AdvertsModel getInstance() {
        if (instance == null) {
            synchronized (AdvertsModel.class) {
                if (instance == null) {
                    instance = new AdvertsModel();
                }
            }
        }
        return instance;
    }
}
