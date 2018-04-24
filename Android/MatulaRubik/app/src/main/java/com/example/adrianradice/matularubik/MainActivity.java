package com.example.adrianradice.matularubik;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{

    private final static String TAG = "MainActivity";

    static {
        if(!OpenCVLoader.initDebug())
            Log.d(TAG, "Error");
        else
            Log.d(TAG, "OK");

    }

    int iLowH = 45;
    int iHighH = 75;
    int iLows = 20;
    int iHighS = 255;
    int iLowV = 10;
    int iHighV = 255;
    Mat imgHSV, imgThresholded;
    Scalar sc1, sc2;
    JavaCameraView camaraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView t = (TextView)findViewById(R.id.txt);
        //t.setText(OpenCVLoader.initDebug()?"OK":"ERROR");



        sc1 = new Scalar(iLowH, iLows, iLowV);
        sc2 = new Scalar(iHighH, iHighS, iHighV);
        camaraView = (JavaCameraView)findViewById(R.id.camara);
        camaraView.setCameraIndex(0);
        camaraView.setCvCameraViewListener(this);
        camaraView.enableView();


    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        imgHSV = new Mat(width,height, CvType.CV_16UC4);
        imgThresholded = new Mat(width,height,CvType.CV_16UC4);
    }

    @Override
    public void onCameraViewStopped() {

    }


    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Imgproc.cvtColor(inputFrame.rgba(),imgHSV,Imgproc.COLOR_BGR2HSV);
        Core.inRange(imgHSV,sc1,sc2,imgThresholded);
        return imgThresholded;
    }




}
