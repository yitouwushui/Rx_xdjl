package cn.ecar.insurance.widget.imagepicker;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.imnjh.imagepicker.ImageLoader;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdAppContext;

/**
 *
 * @author yx
 * @date 2017/11/2
 * imagepicker  Glide适配器
 */

public class GlideImageLoader implements ImageLoader {
    @Override
    public void bindImage(ImageView imageView, Uri uri, int width, int height) {
        GlideApp.with(XdAppContext.app().getContext()).load(uri).placeholder(R.drawable.loading_01)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(R.mipmap.ic_launcher).override(width, height).dontAnimate().into(imageView);
    }

    @Override
    public void bindImage(ImageView imageView, Uri uri) {
        GlideApp.with(XdAppContext.app().getContext()).load(uri).placeholder(R.drawable.loading_01)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(R.mipmap.ic_launcher).dontAnimate().into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    public ImageView createFakeImageView(Context context) {
        return new ImageView(context);
    }
}
