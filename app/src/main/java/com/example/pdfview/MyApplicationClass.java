package com.example.pdfview;

import android.app.Application;

import java.util.ArrayList;

public class MyApplicationClass extends Application {
    public static ArrayList<Float> duty = new ArrayList<Float >();
    public static  ArrayList<Float> rt = new ArrayList<Float>();
    public static  ArrayList<String> guardcount = new ArrayList<String>();
    public static  ArrayList<Float> amount = new ArrayList<Float>();
    public static  ArrayList<String  >grd = new ArrayList<String >();
    public static   Double totalAmount=0.0,gstAmount=0.0,gstandTotalAmount=0.0;
    public  static int totalforWord=0;
    public  static float grantTotal;
}
