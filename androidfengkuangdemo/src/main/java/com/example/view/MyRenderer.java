package com.example.view;

import android.opengl.GLSurfaceView;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by DY on 2017/4/13.
 */

public class MyRenderer implements GLSurfaceView.Renderer {
    float[] triangleData = new float[] {
            0.1f, 0.6f , 0.0f , // 上顶点
            -0.3f, 0.0f , 0.0f , // 左顶点
            0.3f, 0.1f , 0.0f  // 右顶点
    };
    int[] triangleColor = new int[] {
            65535, 0, 0, 0, // 上顶点红色
            0, 65535, 0, 0, // 左顶点绿色
            0, 0, 65535, 0 // 右顶点蓝色
    };
    float[] rectData = new float[] {
            0.4f, 0.4f , 0.0f, // 右上顶点
            0.4f, -0.4f , 0.0f, // 右下顶点
            -0.4f, 0.4f , 0.0f, // 左上顶点
            -0.4f, -0.4f , 0.0f // 左下顶点
    };
    int[] rectColor = new int[] {
            0, 65535, 0, 0, // 右上顶点绿色
            0, 0, 65535, 0, // 右下顶点蓝色
            65535, 0, 0, 0, // 左上顶点红色
            65535, 65535, 0, 0 // 左下顶点黄色
    };
    // 依然是正方形的4个顶点，只是顺序交换了一下
    float[] rectData2 = new float[] {
            -0.4f, 0.4f , 0.0f, // 左上顶点
            0.4f, 0.4f , 0.0f, // 右上顶点
            0.4f, -0.4f , 0.0f, // 右下顶点
            -0.4f, -0.4f , 0.0f // 左下顶点
    };
    float[] pentacle = new float[]{
            0.4f , 0.4f , 0.0f,
            -0.2f , 0.3f , 0.0f,
            0.5f , 0.0f , 0f,
            -0.4f , 0.0f , 0f,
            -0.1f, -0.3f , 0f
    };
    private FloatBuffer triangleDataBuffer,rectDataBuffer,rectData2Buffer,pentacleBuffer;
    private IntBuffer triangleColorBuffer,rectColorBuffer;

    public MyRenderer() {
        Log.e("MyRenderer","===MyRenderer===");
        triangleDataBuffer = floatBufferUtil(triangleData);
        rectDataBuffer = floatBufferUtil(rectData);
        rectData2Buffer = floatBufferUtil(rectData2);
        pentacleBuffer = floatBufferUtil(pentacle);
        triangleColorBuffer = intBufferUtil(triangleColor);
        rectColorBuffer = intBufferUtil(rectColor);

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Log.e("MyRenderer","===onSurfaceCreated===");
        //关闭抗抖动
        gl.glDisable(GL10.GL_DITHER);
        //设置系统对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);
        gl.glClearColor(0,0,0,0);
        //设置阴影平滑模式
        gl.glShadeModel(GL10.GL_SMOOTH);
        //启用深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //设置深度测试类型
        gl.glDepthFunc(GL10.GL_LEQUAL);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Log.e("MyRenderer","===onSurfaceChanged===");
        // 设置3D视窗的大小及位置
        gl.glViewport(0, 0, width, height);
        // 将当前矩阵模式设为投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION);
        // 初始化单位矩阵
        gl.glLoadIdentity();
        // 计算透视视窗的宽度、高度比
        float ratio = (float) width / height;
        // 调用此方法设置透视视窗的空间大小
        gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        Log.e("MyRenderer","===onDrawFrame===");
        //清除屏幕缓存和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        //启用顶点坐标
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //启用顶点颜色
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        //设置当前矩阵堆栈为模型堆栈
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        //绘制第一张图
        gl.glLoadIdentity();
        gl.glTranslatef(-0.32f,0.35f,-1.2f);
        gl.glVertexPointer(3, GL10.GL_FLOAT,0,triangleDataBuffer);
        gl.glColorPointer(4,GL10.GL_FIXED,0,triangleColorBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLES,0,3);
        // 绘制结束
        gl.glFinish();
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
    public FloatBuffer floatBufferUtil(float[] arr){
        ByteBuffer byteBuffer=ByteBuffer.allocateDirect(arr.length*4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = byteBuffer.asFloatBuffer();
        buffer.put(arr);
        buffer.position(0);
        return buffer;
    }
    private IntBuffer intBufferUtil(int[] arr)
    {
        IntBuffer mBuffer;
        // 初始化ByteBuffer，长度为arr数组的长度*4，因为一个int占4个字节
        ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
        // 数组排列用nativeOrder
        qbb.order(ByteOrder.nativeOrder());
        mBuffer = qbb.asIntBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }
}
