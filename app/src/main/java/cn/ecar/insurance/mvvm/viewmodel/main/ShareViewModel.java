package cn.ecar.insurance.mvvm.viewmodel.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Bitmap;

import cn.ecar.insurance.mvvm.model.main.ShareModel;

/**
 * @author ding
 * @date 2017/12/21
 */

public class ShareViewModel extends ViewModel {


    private ShareModel mShareModel;

    public ShareViewModel() {
        mShareModel = ShareModel.getInstance();
    }

    /**
     * 获的二维码
     *
     * @return
     */
    public LiveData<Bitmap> getShareQRCode(String content) {
        return mShareModel.getShareQRCode(content, 0, 0, null);
    }

    /**
     * 获的二维码
     *
     * @return
     */
    public LiveData<Bitmap> getShareQRCode(String content, int widthPix, int heightPix, String filePath) {
        return mShareModel.getShareQRCode(content, widthPix, heightPix, filePath);
    }

}
