package cn.ecar.insurance.mvvm.model.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdAppContext;
import cn.ecar.insurance.utils.file.FileUtils;
import cn.ecar.insurance.utils.file.ImageFactory;
import cn.ecar.insurance.utils.file.QRCodeUtil;
import cn.ecar.insurance.utils.ui.CommonUtils;

/**
 * @author ding
 * @date 2017/12/21
 */

public class ShareModel {

    private static volatile ShareModel instance;

    public static ShareModel getInstance() {
        if (instance == null) {
            synchronized (ShareModel.class) {
                if (instance == null) {
                    instance = new ShareModel();
                }
            }
        }
        return instance;
    }

    /**
     * 获取分享二维码
     *
     * @param content
     * @param widthPix
     * @param heightPix
     * @param filePath
     * @return
     */
    public LiveData<Bitmap> getShareQRCode(String content, int widthPix, int heightPix, String filePath) {
        MutableLiveData<Bitmap> data = new MutableLiveData<>();
        if (content == null || "".equals(content)) {
            return data;
        }
        if (widthPix == 0 || heightPix == 0) {
            widthPix = CommonUtils.dip2px(120);
            heightPix = widthPix;
        }
        if (filePath == null || filePath.endsWith("")) {
            filePath = FileUtils.DEFAULT_SAVE_IMAGE_PATH;
        }
        String fileName = String.valueOf(System.currentTimeMillis());
        Bitmap logo = BitmapFactory.decodeResource(XdAppContext.app().getmResources(), R.mipmap.ic_launcher);
        boolean bool = QRCodeUtil.createQRImage(
                content, widthPix, heightPix,
                logo, filePath, fileName);
        if (bool) {
            data.postValue(ImageFactory.getBitmap(filePath + fileName));
//            data.postValue(ImageFactory.getNewSampleBitmap(widthPix, heightPix, filePath + fileName));
        }
        return data;
    }
}
