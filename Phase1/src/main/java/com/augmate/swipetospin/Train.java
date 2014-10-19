package com.augmate.swipetospin;

import org.opencv.core.Mat;

import java.util.ArrayList;

public class Train {

    public static void main(String[] args){

        nu.pattern.OpenCV.loadShared();
        System.loadLibrary("opencv_java249");

        ArrayList<CarSet> trainingSet=null;

        //TODO Make training set serializable
        trainingSet = CarSet.traverseTrainingDir("training_subset");

        for (final Mat img : trainingSet.get(9).exterior)
            ImageUtils.showResult(img,"exterior");

    }

}

