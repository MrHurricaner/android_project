package com.wuli.delivery.portal;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.util.MD5Util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import libcore.io.DiskLruCache;


/**
 * @author ziv
 * @date 2018/5/9
 */

public class ImageLoader {

    private final static String TAG = ImageLoader.class.getSimpleName();

    private final static int MAX_DISKLRUCACHE_SIZE = 10 * 1024 * 1024;

    private LruCache<String, Bitmap> mLruChache;
    private DiskLruCache mDiskLruCache;
    private Dialog dialog;
    private ImageView imageView;


    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {

            if (dialog != null) {
                dialog.dismiss();
            }

            Bitmap bitmap = getBitmapFromDiskCache((String) msg.obj);

            imageView.setImageBitmap(bitmap);
        }
    };

    private ImageLoader() {

    }

    public final static class InnerClass {
        public final static ImageLoader IMAGE_LOADER = new ImageLoader();
    }

    public static ImageLoader getInstance() {
        return InnerClass.IMAGE_LOADER;
    }

    public void init(Context context) {

        int maxLruCacheSize = (int) (Runtime.getRuntime().maxMemory() / 1024);

        mLruChache = new LruCache<String, Bitmap>(maxLruCacheSize / 8) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return getBitmapSize(value) / 1024;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
            }
        };

        try {
            mDiskLruCache = DiskLruCache.open(getDiskLruCacheFile(context), 1, 1, MAX_DISKLRUCACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadBitmap(Context context,final String url, ImageView imageView) {

        this.imageView = imageView;

        final String key = MD5Util.hashKeyForDisk(url);

        Bitmap bitmap = getBitmapFromMemoryCache(key);

        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }

        bitmap = getBitmapFromDiskCache(key);

        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }

        dialog = new Dialog(context);
        dialog.setContentView(new ProgressBar(context));
        dialog.show();

        Thread thread = new Thread() {
            @Override
            public void run() {

                try {
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (getBitmapFromHttp(url, editor.newOutputStream(0))) {
                        editor.commit();
                    } else {
                        editor.abort();
                    }

                    mDiskLruCache.flush();

                    Message message = Message.obtain();
                    message.obj = key;
                    message.what = 0;

                    mHandler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();


    }

    private void putBitmapToMemoryCache(String url, Bitmap bitmap) {

        if (mLruChache == null) {
            return;
        }

        if (mLruChache.get(url) != null) {
            return;
        }

        mLruChache.put(url, bitmap);
    }


    private Bitmap getBitmapFromMemoryCache(String url) {

        if (mLruChache == null) {
            return null;
        }


        return mLruChache.get(url);
    }

    private int computeInSmallSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            final float totalPixels = width * height;

            // Anything more than 2x the requested pixels we'll sample down
            // further
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }


    private Bitmap decodeBitmapFromFileDescriptor(FileDescriptor fileDescriptor, int reqWith, int reqHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

        options.inSampleSize = computeInSmallSize(options, reqWith, reqHeight);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

    }

    private Bitmap getBitmapFromDiskCache(String key) {

        if (mDiskLruCache == null) {
            return null;
        }

        FileInputStream inputStream = null;

        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
            if (snapshot != null) {

                inputStream = (FileInputStream) snapshot.getInputStream(0);

                Bitmap bitmap = decodeBitmapFromFileDescriptor(inputStream.getFD(), 500, 500);

                if (bitmap != null) {
                    putBitmapToMemoryCache(key, bitmap);
                }

                return bitmap;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;

    }

    private boolean getBitmapFromHttp(String url, OutputStream outputStream) {

        HttpURLConnection urlConnection = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            URL u = new URL(url);
            urlConnection = (HttpURLConnection) u.openConnection();

            bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());

            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int b;

            while ((b = bufferedInputStream.read()) != -1) {
                outputStream.write(b);
            }

            return true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }

            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;

    }

    private File getDiskLruCacheFile(Context context) {

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalCacheDir();
        } else {
            return context.getCacheDir();
        }

    }

    /**
     * 得到bitmap的大小
     */
    private static int getBitmapSize(Bitmap bitmap) {
        //API 19
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return bitmap.getAllocationByteCount();
        }
        //API 12
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        }
        // 在低版本中用一行的字节x高度
        return bitmap.getRowBytes() * bitmap.getHeight();                //earlier version
    }

    private byte[] inputStream2ByteArr(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buff)) != -1) {
            outputStream.write(buff, 0, len);
        }
        inputStream.close();
        outputStream.close();
        return outputStream.toByteArray();
    }


    private Bitmap decodeBitmap(InputStream is) throws IOException {
        if (is == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置该属性可以不占用内存，并且能够得到bitmap的宽高等属性，此时得到的bitmap是空
        options.inJustDecodeBounds = true;
        byte[] data = inputStream2ByteArr(is);//将InputStream转为byte数组，可以多次读取
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        //设置计算得到的压缩比例
        options.inSampleSize = 4;
        //设置为false，确保可以得到bitmap != null
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        return bitmap;
    }
}
