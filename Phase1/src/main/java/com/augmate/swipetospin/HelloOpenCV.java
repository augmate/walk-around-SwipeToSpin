package com.augmate.swipetospin;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.Highgui;

import java.io.File;

/**
 * Created by frank on 10/18/14.
 */
public class HelloOpenCV {

    public static void main(String[] args){
        System.out.println("Hello,OpenCV");

        nu.pattern.OpenCV.loadShared();
        System.loadLibrary("opencv_java249");
        //VideoCapture cap = new VideoCapture(0);

        Mat sealColor = Highgui.imread("Phase1/HarpSeal.jpg");
        Mat sealGray = Highgui.imread("Phase1/HarpSeal.jpg",Highgui.CV_LOAD_IMAGE_GRAYSCALE);

        //get Fast points
        FeatureDetector fast = FeatureDetector.create(FeatureDetector.PYRAMID_ORB);
        MatOfKeyPoint points = new MatOfKeyPoint();
        fast.detect(sealGray, points);

        //Mark Fast points`
        Scalar redcolor = new Scalar(0,255,0);
        Features2d.drawKeypoints(sealColor, points, sealColor, redcolor, 3);

        //show marked Fast points
        //ImageUtils.showResult(sealColor);

        File joeFolder = new File("training_subset/bmwbayside/4jgda7db8da163624/img");
        CarSet joe = new CarSet("bmwbayside", "4jgda7db8da163624", joeFolder);

        for (final Mat img : joe.exterior)
            ImageUtils.showResult(img);

    }
}
