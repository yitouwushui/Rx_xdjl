package cn.ecar.insurance.xdjl.utils.trilateral;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yx on 2017/9/15.
 * rxjava工具类
 */

public class RxJavaUtils {

    //获取间隔观察者
    public static Observable<Long> getIntervalObservable(int intervalSecond, int takeCount) {
        return Observable.interval(intervalSecond, TimeUnit.SECONDS)
                .take(takeCount)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
