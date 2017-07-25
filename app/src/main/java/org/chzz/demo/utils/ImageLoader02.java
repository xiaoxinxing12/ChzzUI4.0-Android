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

public class ImageLoader02 {

    //图片缓存
    ImageCache02 imageCache=new ImageCache02();
    //线程池 固定大小 线程数量为CPU的数量
    ExecutorService mExecutorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    /**
     * 显示图片
     * @param url
     * @param imageView
     */
    public void displayImage(final String url, final ImageView imageView){
        if(null!=imageCache.get(url)){
                imageView.setImageBitmap(imageCache.get(url));
            return;
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
                imageCache.put(url,bitmap);
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
