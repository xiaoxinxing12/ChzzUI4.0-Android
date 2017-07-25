package org.chzz.demo.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by copy on 2017/7/25.
 */

public class ImageCache02 {

    //图片缓存
    LruCache<String, Bitmap> mImageCache;

    public ImageCache02() {
        initImageCache();
    }

    /**
     * 初始化图片加载器
     */
    private void initImageCache() {
        //计算内存大小
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        //取四份之一内存作为缓存大小
        final int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {


                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };

    }

    public void put( String url,Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    public Bitmap get(String url) {


        if (null != mImageCache && mImageCache.size() > 0) {
            Bitmap bitmap = mImageCache.get(url);

            return bitmap;
        }

        return null;
    }
}
