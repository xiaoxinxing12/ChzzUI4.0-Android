package org.chzz.demo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by copy on 2017/7/25.
 */

public class ImageLoader01 {
    //图片缓存
    LruCache<String,Bitmap> mImageCache;
    //线程池 固定大小 线程数量为CPU的数量
    ExecutorService mExecutorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public ImageLoader01(){
        initImageCache();
    }

    /**
     * 初始化图片加载器
     */
    private void initImageCache(){
        //计算内存大小
        final int maxMemory= (int) (Runtime.getRuntime().maxMemory()/1024);
        //取四份之一内存作为缓存大小
        final int cacheSize=maxMemory/4;
        mImageCache=new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {


                return value.getRowBytes()*value.getHeight()/1024;
            }
        };

    }

    /**
     * 显示图片
     * @param url
     * @param imageView
     */
    public void displayImage(final String url, final ImageView imageView){
        if(null!=mImageCache&&mImageCache.size()>0){
            Bitmap bitmap=mImageCache.get(url);
            //从缓存获取
            if(null!=bitmap){
                imageView.setImageBitmap(bitmap);
                return;
            }
        }
        imageView.setTag(url);
        mExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                //图片转bitmap
                Bitmap bitmap=downloadImage(url);
                if(bitmap==null){
                    return;
                }
                if(imageView.getTag().equals(url)){
                    imageView.setImageBitmap(bitmap);
                }
                //缓存到内存
                mImageCache.put(url,bitmap);
            }
        });
    }

    /**
     * 下载图片
     * @param imageUrl
     * @return
     */
    private Bitmap downloadImage(String imageUrl){

        Bitmap bitmap=null;
        try {
            URL url=new URL(imageUrl);
            final HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            bitmap= BitmapFactory.decodeStream(connection.getInputStream());
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

}
