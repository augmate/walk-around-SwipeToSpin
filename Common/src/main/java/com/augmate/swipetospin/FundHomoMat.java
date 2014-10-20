package com.augmate.swipetospin;

import org.opencv.core.Mat;

/**
 * Created by frank on 10/19/14.
 */
public class FundHomoMat {

    public Mat fundamentaAB;
    public Mat homographyAB;

    public FundHomoMat(){
        fundamentaAB = new Mat();
        homographyAB = new Mat();
    }
}
