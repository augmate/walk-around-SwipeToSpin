package com.augmate.swipetospin;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.io.File;


/**
 * Created by frank on 10/18/14.
 */
public class SwipeToSpinApp {

    public static void main(String[] args){
        System.out.println("Hello,OpenCV");

        nu.pattern.OpenCV.loadShared();
        System.loadLibrary("opencv_java249");

        //Quick 3D Image Pair processing for books and rooms
        Mat roomA = Highgui.imread("room/roomA.jpg");
        Mat roomB = Highgui.imread("room/roomB.jpg");
        Quick3DPairProcess(roomA, roomB, true, false, false,"roomA vs roomB");
        Quick3DPairProcess(roomB, roomA, true, false, false,"roomB vs roomA");//Broken

        Mat Book1 = Highgui.imread("books/book1.jpg");
        Mat Book2 = Highgui.imread("books/book2.jpg");
        Mat Book3 = Highgui.imread("books/book3.jpg");
        Quick3DPairProcess(Book2, Book1, true, false, false,"book2v1");
        Quick3DPairProcess(Book3, Book2, true, false, false,"book3v2");
        Quick3DPairProcess(Book3, Book1, true, false, false,"book3v1");

//        //Quick 3D Image Pair processing for joe the bmw
//        File joeFolder = new File("training_subset/bmwbayside/4jgda7db8da163624/img");
//        CarSet joe = new CarSet("bmwbayside", "4jgda7db8da163624", joeFolder);
//        for(int i=1;i<joe.exteriorNum;i++) {
//            String pairNames = new String("" + i + " vs " + (i - 1));
//            FundHomoMat fundHomoMat = new FundHomoMat();
//            fundHomoMat = Quick3DPairProcess(
//                    joe.exterior.get(i),
//                    joe.exterior.get(i - 1),
//                    true, false, false, pairNames);
//            System.out.println("fundamental " + pairNames + "\n" + fundHomoMat.fundamentaAB.dump());
//            System.out.println();


    }

    private static FundHomoMat Quick3DPairProcess(Mat imgA, Mat imgB, boolean showEpipole, boolean showMatch, boolean showStitch, String pairNames) {

        Mat grayImgA=new Mat();
        Mat grayImgB=new Mat();
        Imgproc.cvtColor(imgA, grayImgA, Imgproc.COLOR_RGB2GRAY);
        Imgproc.cvtColor(imgB, grayImgB, Imgproc.COLOR_RGB2GRAY);

        ImagePair3DProcess imagePair3DProcess = new ImagePair3DProcess(imgA, imgB, grayImgA, grayImgB, 1000);
        imagePair3DProcess.ProcessPair();
        Mat epipoleAB = imagePair3DProcess.getEpipoleAB();
        Mat stitchedAB = imagePair3DProcess.getStitchedAB();
        Mat matchedImage = imagePair3DProcess.getMatchedImage();
        FundHomoMat fhMat = new FundHomoMat();
        fhMat.fundamentaAB = imagePair3DProcess.getFundamentaAB();
        fhMat.homographyAB = imagePair3DProcess.getHomographyAB();

        if(showEpipole)
            ImageUtils.showResult(epipoleAB,"epipoleAB "+pairNames);
        if(showStitch)
            ImageUtils.showResult(stitchedAB,"stitchedAB "+pairNames);
        if(showMatch)
            ImageUtils.showResult(matchedImage,"matchedImage "+pairNames);

        return fhMat;
    }

}
