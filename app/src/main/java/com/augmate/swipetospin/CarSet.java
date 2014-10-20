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

public class CarSet{

    public ArrayList<Mat> exterior;
    public ArrayList<Mat> interior;
    public ArrayList<Mat> closeup;
    public int exteriorNum;
    public int interiorNum;
    public int closeupNum;
    public String username;
    public String vin;

    public CarSet(){
        exterior = new ArrayList<Mat>();
        interior = new ArrayList<Mat>();
        closeup = new ArrayList<Mat>();
    }

    public CarSet(String username, String vin, File imgFolder){
        this.username=username;
        this.vin=vin;

        File ecFolder = new File(imgFolder,"ec");
        File iFolder = new File(imgFolder,"i");
        File closeupFolder = new File(imgFolder,"closeups");

        this.exterior = loadFromDir(ecFolder);
        this.exteriorNum = this.exterior.size();
    }

    public ArrayList<Mat> loadFromDir(String dir) {
        File folder = new File(dir);
        return loadFromDir(folder);
    }

    public ArrayList<Mat> loadFromDir(File folder){

        ArrayList<Mat> imageList = new ArrayList<Mat>();
        if (folder.isFile())return null;

        File[] listedFiles = folder.listFiles();
        String[] listedFileNames = new String[listedFiles.length];
        for (int i = 0; i < listedFiles.length; ++i)
            listedFileNames[i] = listedFiles[i].getAbsolutePath();

        Comparator<String> comp = new AlphanumComparator();
        Arrays.sort(listedFileNames, comp);

        //for (final String filename : listedFileNames)
        //System.out.println(filename);

        for (final String filename : listedFileNames) {
            File fileEntry = new File(filename);
            if (fileEntry.isDirectory()) {
                loadFromDir(fileEntry);
            } else if (fileEntry.isFile()){
                //fileEntry.getAbsoluteFile()
                try {
                    //System.out.println(filename);
                    Mat img = Highgui.imread(filename);
                    if (img!=null)
                        imageList.add(img);
                }
                catch (Exception ex) {
                    //ex.printStackTrace();
                }
            }
        }
        return imageList;
    }

    /*  training set folder structure
        training subset
            -username
                -hash
                    -img`
                        -closeup
                        -ec (exterior)
                        -i (interior)*/
    public static ArrayList<CarSet> traverseTrainingDir(String dir){
        File folder = new File(dir);
        return  traverseTrainingDir(folder);
    }

    public static ArrayList<CarSet> traverseTrainingDir(File trainingFolder){
        ArrayList<CarSet> carSetArrayList= new ArrayList<CarSet>();

        File[] listedUsernameFolders = trainingFolder.listFiles();

        for (final File userFolderName : listedUsernameFolders) {

            if(!userFolderName.isDirectory()) continue;
            System.out.println("User: " + userFolderName.getName());
            File[] listedVINFolders = userFolderName.listFiles();

            for (final File vinFolderName : listedVINFolders) {
                System.out.println("VIN: " + vinFolderName.getName());

                File imgFolder = new File(vinFolderName,"img");
                if(!imgFolder.isDirectory()) continue;

                CarSet tmpCarSet = new CarSet(userFolderName.getName(), vinFolderName.getName(), imgFolder);
                carSetArrayList.add(tmpCarSet);
            }
        }
        return  carSetArrayList;
    }
}
