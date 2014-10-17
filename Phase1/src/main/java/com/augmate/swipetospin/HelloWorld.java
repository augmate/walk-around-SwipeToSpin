package com.augmate.swipetospin;

import com.augmate.swipetospin.ImageUtils;
import com.sun.imageio.plugins.common.ImageUtil;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class HelloWorld {

    public static void main(String[] args){
        System.out.println("Hello,OpenCV");

        nu.pattern.OpenCV.loadShared();
        System.loadLibrary("opencv_java249");
        VideoCapture cap = new VideoCapture(0);

        Mat frame = Highgui.imread("Phase1/HarpSeal.jpg");
        ImageUtils.showResult(frame);

    }

}

