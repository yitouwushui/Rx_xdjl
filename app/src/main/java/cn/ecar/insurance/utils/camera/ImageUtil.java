package cn.ecar.insurance.utils.camera;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.ecar.insurance.utils.file.FileUtils;
import cn.ecar.insurance.utils.ui.ToastUtils;

/**
 * @author ding
 * @date 2016/12/14
 */
public class ImageUtil {

    private final static String TAG = "imageUtil";


    /**
     * 通过内容提供器来获取图片缩略图
     * 缺点:必须更新媒体库才能看到最新的缩略图
     *
     * @param context
     * @param cr
     * @param Imagepath
     * @return
     */
    public static Bitmap getImageThumbnail(Context context, ContentResolver cr, String Imagepath) {
        ContentResolver testcr = context.getContentResolver();
        String[] projection = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID,};
        String whereClause = MediaStore.Images.Media.DATA + " = '" + Imagepath + "'";
        Cursor cursor = testcr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, whereClause, null, null);
        int _id = 0;
        String imagePath = "";
        if (cursor == null || cursor.getCount() == 0) {
            Logger.d(TAG, "cursor is null");
            return null;
        } else if (cursor.moveToFirst()) {
            int _idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID);
            int _dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            Logger.d(TAG, "id:" + _idColumn);
            Logger.d(TAG, "data:" + _dataColumn);
            do {
                _id = cursor.getInt(_idColumn);
                imagePath = cursor.getString(_dataColumn);
            } while (cursor.moveToNext());
        }
        cursor.close();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(cr, _id, MediaStore.Images.Thumbnails.MINI_KIND, options);
        return bitmap;
    }

    public static Bitmap getThumbnail(Context context, Uri uri, int size) throws FileNotFoundException, IOException {
        InputStream input = context.getContentResolver().openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        if (input != null) {
            input.close();
        }
        if ((onlyBoundsOptions.outWidth == -1) || (onlyBoundsOptions.outHeight == -1)) {
            return null;
        }
        int originalSize = (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) ? onlyBoundsOptions.outHeight : onlyBoundsOptions.outWidth;
        double ratio = (originalSize > size) ? (originalSize / size) : 1.0;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio);
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//optional
        input = context.getContentResolver().openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        if (input != null) {
            input.close();
        }
        return bitmap;
    }

    private static int getPowerOfTwoForSampleRatio(double ratio) {
        int k = Integer.highestOneBit((int) Math.floor(ratio));
        if (k == 0) {
            return 1;
        } else {
            return k;
        }
    }

    public static Bitmap getImageFromUri(Uri selectUri, Context context) {
        Bitmap bitmap = null;
        Cursor cursor = null;
        try {
            String[] filePathColumns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
            cursor = context.getContentResolver().query(selectUri,
                    filePathColumns, null, null, null);
            if (cursor == null || cursor.getCount() == 0) {
                bitmap = getBitmapDecodeFile(selectUri.getPath());
            } else if (cursor.moveToFirst()) {
                int dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                String imagePath = cursor.getString(dataColumn);
                bitmap = getBitmapDecodeFile(imagePath);
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showToast("获取照片失败,请重试");
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return bitmap;
    }


    /**
     * 通过绝对路径获取图片
     *
     * @param srcPath
     * @return
     */
    public static Bitmap getBitmapDecodeFile(String srcPath) {
        File file = new File(srcPath);
        if (file.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            //开始读入图片，此时把options.inJustDecodeBounds 设回true了
            options.inJustDecodeBounds = true;
            Bitmap bitmap = BitmapFactory.decodeFile(srcPath, options);//此时返回bm为空
            int inSampleSize = calculateScaleInSampleSize(options, 1080, 720);
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inJustDecodeBounds = false;
            options.inSampleSize = inSampleSize;
            options.inPurgeable = true;
            return BitmapFactory.decodeFile(srcPath, options);
        }
        return null;
    }


    /**
     * 保存文件
     *
     * @param bm
     * @param fileName
     * @throws IOException
     */
    public static String saveFile(Bitmap bm, String fileName) throws Exception {
        String path = FileUtils.getImagePath();
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        Logger.i("logpath: 保存之后的地址：" + path + fileName);
        return path + fileName;
    }


    /**
     * 压缩图片
     *
     * @param newWid
     * @param newHei
     * @param imagePath
     * @return
     */
    public static Bitmap getNewSampleBitmap(int newWid, int newHei, String imagePath, Context context) {
        File file = new File(imagePath);
        Log.i("123", "exists:" + file.exists());
        if (file.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imagePath, options);
            options.inSampleSize = calculateScaleInSampleSize(options, newWid, newHei);
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(imagePath, options);
        }
        return null;
    }

    /**
     * 压缩图片
     *
     * @param newWid
     * @param newHei
     * @param imagePath
     * @return
     */
    public static Bitmap getNewSampleBitmap(int newWid, int newHei, String imagePath) {
        File file = new File(imagePath);
        Log.i("123", "exists:" + file.exists());
        if (file.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imagePath, options);
            options.inSampleSize = calculateScaleInSampleSize(options, newWid, newHei);
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeFile(imagePath, options);
        }
        return null;
    }

    /**
     * 官方推荐的计算sample的公式
     */
    private static int calculateScaleInSampleSize(BitmapFactory.Options options, int targetWidth, int targetHeight) {
        int inSampleSize = 1;
        int height;
        int width;
        if (options.outWidth >= options.outHeight) {
            // 实际图片宽大于高
            height = options.outHeight;
            width = options.outWidth;
        } else {
            // 实际图片高大于宽
            width = options.outHeight;
            height = options.outWidth;
        }
        if (height > targetHeight || width > targetWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= targetHeight
                    && (halfWidth / inSampleSize) >= targetWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
