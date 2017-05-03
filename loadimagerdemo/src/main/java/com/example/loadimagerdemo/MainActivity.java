package com.example.loadimagerdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {
    private ImageView bigIv;
    private int Width, Height, ImWidth, ImHeight;//获取屏幕的高度和宽度以及图片的高度和宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getScreenWidthAndHeight();
        bigIv = (ImageView) findViewById(R.id.big_iv);
//        loadBigImage();
        Button big= (Button) findViewById(R.id.btn);
        big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = Environment.getExternalStorageDirectory() + "/Aiss-141545-com_093.jpg";
                getImageWidthAndHeight(path);
                int scaleSize =2;//1就表示不压缩
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize=scaleSize;
                Bitmap bitmap = BitmapFactory.decodeFile(path,options);
//                Bitmap bitmap=getImageByScaleSizeByTec(path);
                Log.e("123", "压缩后图宽：" + bitmap.getWidth() + "压缩后图高：" + bitmap.getHeight());
                bigIv.setImageBitmap(bitmap);
            }
        });
    }

    public void loadBigImage() {
        //读取SD卡的状态，并且-判断SD卡是否可用
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //先判断SD卡的状态是否可用，mnt目录shell--->emualted---->0
            //如果是可用的状态，就读到SD卡的路径，然后将它加载出来
            //如果图片过大就容易造成图片加载的延迟甚至会造成内存溢出，所以需要对图片做一定的压缩处理
            BitmapFactory.Options options = new BitmapFactory.Options();
            String path = Environment.getExternalStorageDirectory() + "/4fffdacf88244.jpg";
            /**
             * 思考：如何合理设置inSampleSize来针对不同分辨率的手机，从而得到一个更佳的图片压缩方案呢？？
             * 分析：
             * 由于图片大多数都是位图显示，即具体个数的像素点来显示的，在不同分辨率的手机屏幕显示图片
             * 说白就是在不同像素点的总数的屏幕中显示，很容易理解，当我有个很大的图片，所谓很很大的图片
             * 就是总的像素点数很多，并且在低分辨（总的像素点少显示）肯定有问题，只能显示部分，所以需要
             * 根据当前的手机分辨率的大小，来适当压缩图片的大小比例，然后来显示在相应分辨率的屏幕上
             * 当我通过某个方式拿到一张图片会有如下几种情况:
             *       图片宽度（ImWidth）,图片高度(ImHeight),屏幕宽度(Width),屏幕高度(Height)
             *       一、根据SD卡路径加载图片的大小比例压缩
             *              1、若图片的宽度大于图片高度（即横向图片）,且宽度大于屏幕宽度：Size=ImWidth/Width
             *              2、若图片的高度大于图片宽度(即纵向图片)，且高度大于屏幕高度:Size=ImHeight/Height;
             *              3、就是根据一个图片压缩比例算法公式：取图片宽度压缩倍数和图片高度的压缩倍数的平均值:Size=(ImWidth/Width+ImHeight/Height)/2;
             *       二、图片的质量的压缩
             *       三、根据Bitmap来压缩图片大小比例
             * */
            Bitmap mBitmap=getImageByScaleSize(path);
            Bitmap bitmap=getImageCompress(mBitmap);
            // bitmap= getImageByScaleSize(bitmap);
//            Bitmap bitmap=getImageByScaleSizeByTec(path);
            bigIv.setImageBitmap(bitmap);
        }
    }
    /**
     * 图片的质量压缩:
     * 图片质量的压缩思想大致如下:
     * 先将一张图片到一个字节数组输出流对象保存，
     * 然后通过不断压缩数据，直到图片大小压缩到某个具体大小时，然后再把
     * 字节数组输出流对象作为一个字节数组输入流参数对象传入得到一个字节数组输入流
     * 最后再将字节数组输入流得到Bitmap对象，最终拿到图片质量压缩后的图片
     */
    public Bitmap getImageCompress(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到字节数组输出流中。
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap2 = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap2;
    }

    /**
     * 根据SD卡路径加载图片的大小比例压缩
     */
    public Bitmap getImageByScaleSize(String path) {
        int scaleSize =1;//1就表示不压缩
        BitmapFactory.Options options = new BitmapFactory.Options();
     /*       options.inJustDecodeBounds=true;//只读取图片的信息，不读取图片的具体数据
            ImWidth = options.outWidth;
            ImHeight = options.outHeight;*/
        getImageWidthAndHeight(path);//得到图片的高度和宽度
        if (ImWidth > ImHeight && ImWidth > Width) {
            scaleSize = (int)(ImWidth*1.0f / Width+0.5f);//加0.5是为了四舍五入，取一个很好的精度
        } else if (ImHeight > ImWidth && ImHeight > Height) {
            scaleSize = (int)(ImHeight*1.0f / Height+0.5f);
        } else {//其他情况表示，就是当是横向或者纵向图片时，它的长度和宽度都大于屏幕
            scaleSize = (int)(ImWidth*1.0f / Width + ImHeight*1.0f / Height+0.5f) / 2;
        }
        //设置图片的采样率
        options.inSampleSize = scaleSize;//针对不同的手机分辨率，设置的缩放比也不一样，这里的值可能是不一样的
        Bitmap bitmap2 = BitmapFactory.decodeFile(path, options);
        return bitmap2;
    }
    //获取当前手机屏幕的高度和宽度
    private void getScreenWidthAndHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Width = metrics.widthPixels;
        Height = metrics.heightPixels;
    }
    //得到原图的高度和宽度
    private void getImageWidthAndHeight(String path) {
        try {
            ExifInterface exifInterfece=new ExifInterface(path);
            ImWidth=exifInterfece.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH,0);
            ImHeight=exifInterfece.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH,0);
            Log.e("123","原图宽："+ImWidth+"原图高："+ImHeight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 公式法
     * */
    public Bitmap getImageByScaleSizeByTec(String path){
        int scaleSize =1;//1就表示不压缩
        getImageWidthAndHeight(path);
        int WidthScaleSize=(int)(ImWidth*1.0f/Width*1.0f+0.5f);
        int HeightScaleSize=(int)(ImHeight*1.0f/Height*1.0f+0.5f);
        scaleSize=(int)(Math.sqrt(WidthScaleSize*WidthScaleSize+HeightScaleSize*HeightScaleSize)+0.5f);
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置图片的采样率
        options.inSampleSize = scaleSize;//针对不同的手机分辨率，设置的缩放比也不一样，这里的值可能是不一样的
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

}
