package com.augmate.swipetospin;

import com.davekoelle.alphanum.AlphanumComparator;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by frank0631 on 10/17/14.
 */
public class CarSet {

    public ArrayList<Mat> exterior;
    public int exteriorNum;

    public CarSet(){
        exterior = new ArrayList<Mat>();
    }

    public void loadFromDir(String dir) {
        File folder = new File(dir);
        loadFromDir(folder);
    }

    public void loadFromDir(File folder){

        if (folder.isFile())return;

        File[] listedFiles = folder.listFiles();
        String[] listedFileNames = new String[listedFiles.length];
        for (int i = 0; i < listedFiles.length; ++i)
            listedFileNames[i] = listedFiles[i].getAbsolutePath();

        Comparator<String> comp = new AlphanumComparator();
        Arrays.sort(listedFileNames, comp);

        for (final String filename : listedFileNames)
            System.out.println(filename);

        for (final String filename : listedFileNames) {
            File fileEntry = new File(filename);
            if (fileEntry.isDirectory()) {
                loadFromDir(fileEntry);
            } else if (fileEntry.isFile()){
                //fileEntry.getAbsoluteFile()
                try {
                    System.out.println(filename);
                    Mat img = Highgui.imread(filename);
                    if (img!=null)
                        exterior.add(img);
                }
                catch (Exception ex) {
                    //ex.printStackTrace();
                }
            }
        }
        exteriorNum = exterior.size();

    }

}
