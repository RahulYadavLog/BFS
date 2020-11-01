package com.example.pdfview;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pdfview.activity.PDFCreatorActivity;
import com.example.pdfview.utils.PDFUtil;
import com.example.pdfview.views.PDFBody;
import com.example.pdfview.views.PDFFooterView;
import com.example.pdfview.views.PDFHeaderView;
import com.example.pdfview.views.PDFTableView;
import com.example.pdfview.views.basic.PDFHorizontalView;
import com.example.pdfview.views.basic.PDFHorizontalWait1;
import com.example.pdfview.views.basic.PDFHorizontalWait2;
import com.example.pdfview.views.basic.PDFHorizontalWait3;
import com.example.pdfview.views.basic.PDFHorizontalWait4;
import com.example.pdfview.views.basic.PDFImageView;
import com.example.pdfview.views.basic.PDFLineSeparatorView;
import com.example.pdfview.views.basic.PDFTextView;
import com.example.pdfview.views.basic.PDFVerticalView;
import com.example.pdfview.views.basic.PDFVerticalWaitView;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends PDFCreatorActivity {

    double total=0;


    int gurdSize;
    String billDate,billNo,billAddress;
    double intGst,intSGst;
    boolean gst;
    double toAmount;
    int totalAmount;
    String gstno;
    FileOutputStream outputStream;
    private File pdfFile;
    String monthName;
    MyApplicationClass myApplicationClass;

   /* public MainActivity(Context context, String s , String strGropNamePdf) {
        this.context = context;
        Type type = new TypeToken<List<LabelActivityModel>>() {
        }.getType();
        exsistingList = GsonUtils.getInstance().fromJson(s, type);
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplicationClass=(MyApplicationClass)getApplicationContext();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
        gurdSize=bundle.getInt("size");
        billDate=bundle.getString("date");
        billNo=bundle.getString("billno");
        billAddress=bundle.getString("add");
        monthName=bundle.getString("month");
        gst=bundle.getBoolean("gst");

        gstno=bundle.getString("gstno");
      /*  for (int i = 0; i <gurdSize ; i++) {

            rate = bundle.getIntegerArrayList("rate");
            strduty = bundle.getStringArrayList("duty");
            guard = bundle.getStringArrayList("guard");
            guardcount = bundle.getStringArrayList("guardCount");
        }*/
            /*rate =intent.getStringArrayExtra("rate");
            guard =intent.getStringArrayExtra("guard");*/

        }
       /* for (int i = 0; i <strduty.size() ; i++) {
            dblduty.add(Double.valueOf(strduty.get(i)));
        }

        for (int i = 0; i <rate.size() ; i++) {
            oneDayRate.add(Double.valueOf((rate.get(i))));
        }
        for (int i = 0; i <dblduty.size() ; i++) {
            amount.add(round(((((oneDayRate.get(i))/30)*dblduty.get(i))),4));
        }

        for (int i = 0; i <amount.size(); i++) {
            total+=(amount.get(i));
        }

        if(gst==true)
        {
            intGst=round(((total*9)/100),4);
            intSGst=round(((total*9)/100),4);
            toAmount=round((intGst+total+intSGst),4);
            String s = String.valueOf(toAmount);
            String str = s.replace(".", "");
            totalAmount = Integer.parseInt(str);

        }
        else
        {
            String s = String.valueOf(total);
            String str = s.replace(".", "");
            totalAmount = Integer.parseInt(str);

        }



*/
        createPDF("Month/test", new PDFUtil.PDFUtilListener() {
            @Override
            public void pdfGenerationSuccess(File savedPDFFile) {


                Toast.makeText(MainActivity.this, "PDF Created", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void pdfGenerationFailure(Exception exception) {
                Toast.makeText(MainActivity.this, "PDF NOT Created", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected PDFHeaderView getHeaderView(int pageIndex) {
        PDFHeaderView headerView = new PDFHeaderView(getApplicationContext());




        PDFHorizontalWait2 invoiceHeder2 = new PDFHorizontalWait2(getApplicationContext());

        PDFTextView pdfTextViewPage = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.HEADER);
        SpannableString word = new SpannableString("INVOICE");
        word.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        pdfTextViewPage.setText(word);
        pdfTextViewPage.setLayout(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 0));
        pdfTextViewPage.getView().setGravity(Gravity.CENTER_HORIZONTAL);
        pdfTextViewPage.getView().setTypeface(pdfTextViewPage.getView().getTypeface(), Typeface.BOLD);

        headerView.addView(pdfTextViewPage);


      /*  PDFLineSeparatorView lineSeparatorView12 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        headerView.addView(lineSeparatorView12);*/


        PDFHorizontalView horizontalView = new PDFHorizontalView(getApplicationContext());
        PDFHorizontalWait1 horizontalwait2 = new PDFHorizontalWait1(getApplicationContext());


        PDFImageView imageView = new PDFImageView(getApplicationContext());
        LinearLayout.LayoutParams imageLayoutParam = new LinearLayout.LayoutParams(
                100,
                100, 1);
        imageView.setImageScale(ImageView.ScaleType.FIT_START);

        imageView.setImageResource(R.drawable.logo2);
        imageLayoutParam.setMargins(0, 0, 0, 0);
        imageView.setLayout(imageLayoutParam);
        horizontalwait2.addView(imageView);
        horizontalView.addView(horizontalwait2);
        PDFHorizontalWait2 horizontalwait1 = new PDFHorizontalWait2(getApplicationContext());



        /*PDFTextView newline = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        newline.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        horizontalView.addView(newline);
        PDFTextView newline1 = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        newline1.setText("");
        horizontalView.addView(newline1);*/



        PDFVerticalView verticalView = new PDFVerticalView(getApplicationContext());

        PDFTextView newline2 = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H3);
        newline2.setText("\nOffice Address :");
        newline2.getView().setTypeface(pdfTextViewPage.getView().getTypeface(), Typeface.BOLD);

        verticalView.addView(newline2);

        PDFTextView newline3 = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        newline3.setText("Jai Ambe Soc. M. G. , \nCross Road No.3 , \nKandivali (West) , \nMumbai 400 067.");

        verticalView.addView(newline3);
        horizontalwait1.addView(verticalView);
        horizontalView.addView(horizontalwait1);

        headerView.addView(horizontalView);

        PDFLineSeparatorView lineSeparatorView1 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.WHITE);
        headerView.addView(lineSeparatorView1);

        return headerView;
    }

    @Override
    protected PDFBody getBodyViews() {
        PDFBody pdfBody = new PDFBody();
        PDFHorizontalView horizontalView = new PDFHorizontalView(getApplicationContext());
        PDFHorizontalWait1 horizontalView1 = new PDFHorizontalWait1(getApplicationContext());

        PDFTextView pdfCompanyNameView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H2);
        pdfCompanyNameView.setText("Bombay Facility Service");
        pdfCompanyNameView.getView().setTypeface(pdfCompanyNameView.getView().getTypeface(), Typeface.BOLD);

        horizontalView1.addView(pdfCompanyNameView);
        horizontalView.addView(horizontalView1);
        PDFHorizontalWait2 horizontalView2 = new PDFHorizontalWait2(getApplicationContext());

        PDFVerticalView verticalView = new PDFVerticalView(getApplicationContext());
        PDFTextView pdfCompanyMobile = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        pdfCompanyMobile.setText("\t\t\t  Mo. No.:09819758831");
        pdfCompanyMobile.setTextColor(Color.BLUE);
        verticalView.addView(pdfCompanyMobile);
        PDFTextView pdfCompanyemail= new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        pdfCompanyemail.setText("Email: yadavsudarshan1970@gmail.com");
        pdfCompanyemail.setTextColor(Color.BLUE);
        verticalView.addView(pdfCompanyemail);
        horizontalView2.addView(verticalView);
        horizontalView.addView(horizontalView2);
        pdfBody.addView(horizontalView);
        PDFLineSeparatorView lineSeparatorView1 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView1);
        PDFHorizontalView horizontal_bill_View = new PDFHorizontalView(getApplicationContext());
        PDFHorizontalWait3 horizontalViewaddress = new PDFHorizontalWait3(getApplicationContext());
        PDFVerticalWaitView verticalBillview = new PDFVerticalWaitView(getApplicationContext());

        PDFTextView pdfCompanybillAdd = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        pdfCompanybillAdd.setText("M/s\n"+""+billAddress);
        verticalBillview.addView(pdfCompanybillAdd);
        PDFTextView pdfCompanyGstNo= new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        pdfCompanyGstNo.setText("\tGST No.:" +gstno+"\n");
        pdfCompanyGstNo.setTextColor(Color.BLUE);

        verticalBillview.addView(pdfCompanyGstNo);
        horizontalViewaddress.addView(verticalBillview);
        horizontal_bill_View.addView(horizontalViewaddress);
        PDFHorizontalWait2 horizontalViewDate = new PDFHorizontalWait2(getApplicationContext());

        PDFVerticalWaitView verticalDateView = new PDFVerticalWaitView(getApplicationContext());
        PDFTextView pdfCompanybillDate = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        pdfCompanybillDate.setText("\nDate:"+billDate);
        verticalDateView.addView(pdfCompanybillDate);
        PDFTextView pdfCompanyBillNo= new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        pdfCompanyBillNo.setText("\nBill No:"+billNo);
        verticalDateView.addView(pdfCompanyBillNo);
        horizontalViewDate.addView(verticalDateView);
        horizontal_bill_View.addView(horizontalViewDate);
        pdfBody.addView(horizontal_bill_View);





        PDFLineSeparatorView lineSeparatorView4 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView4);

        PDFTableView.PDFTableRowView tableRowView2= new PDFTableView.PDFTableRowView(getApplicationContext());
        String[] textIntitle = {"PARTICULARS           ", " ", "\tRate", "\t\tAmount Rs. P."};
        for(int i = 0;  i<textIntitle.length; i++) {
            PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdfTextView.setText(textIntitle[i]);
            pdfTextView.setBackgroundColor(Color.GRAY);
            tableRowView2.addToRow(pdfTextView);
        }
        pdfBody.addView(tableRowView2);

        PDFLineSeparatorView lineSeparatorView2 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView2);


        PDFTextView space1 = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        space1.setText("");
        pdfBody.addView(space1);
        PDFLineSeparatorView lineSeparatorView13 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView13);

        PDFHorizontalView horizontaltext = new PDFHorizontalView(getApplicationContext());
        PDFTextView monthtitle = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        monthtitle.setText("Security Providing for the Month of "+monthName+" 2020\t\t\t");
        horizontaltext.addView(monthtitle);
       /* PDFTextView ratetitle = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        ratetitle.setText("\t\t\t\t");
        horizontaltext.addView(ratetitle);
        PDFTextView amountititle = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        amountititle.setText("\t\t");
        horizontaltext.addView(amountititle);
*/
        pdfBody.addView(horizontaltext);
        PDFLineSeparatorView lineSeparatorView5 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView5);

          PDFTextView newline = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        newline.setText("");
        pdfBody.addView(newline);
        for (int j = 0; j <gurdSize ; j++) {
            PDFTableView.PDFTableRowView tableRowView1 = new PDFTableView.PDFTableRowView(getApplicationContext());
            String[] textInTable = {""+myApplicationClass.grd.get(j), "("+myApplicationClass.guardcount.get(j)+")", "\t"+myApplicationClass.rt.get(j)+".00", "\t\t"+myApplicationClass.amount.get(j)+".00"};
            for(int i = 0;  i<textInTable.length; i++) {
                PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
                pdfTextView.setText(textInTable[i]);
                tableRowView1.addToRow(pdfTextView);
            }
            pdfBody.addView(tableRowView1);
        }



        for (int j = 0; j <5-gurdSize ; j++) {
            PDFTableView.PDFTableRowView tableRowView1 = new PDFTableView.PDFTableRowView(getApplicationContext());
            String[] textInTable = {" ", " "," ", " "," ", " "," "};
            for(int i = 0;  i<textInTable.length; i++) {
                PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
                pdfTextView.setText(textInTable[i]);
                tableRowView1.addToRow(pdfTextView);
            }
            pdfBody.addView(tableRowView1);
        }
        PDFTextView newline2 = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        newline2.setText("");
        pdfBody.addView(newline2);

        PDFLineSeparatorView lineSeparatorView6 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView6);
        PDFHorizontalView horizontaltaxable = new PDFHorizontalView(getApplicationContext());
        PDFHorizontalWait3 horizontalView1taxable = new PDFHorizontalWait3(getApplicationContext());
        PDFTableView.PDFTableRowView tableRowView4= new PDFTableView.PDFTableRowView(getApplicationContext());
        String[] taxableValue = {"Taxable Value",""};
        for(int i = 0;  i<taxableValue.length; i++) {
            PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdfTextView.setText(taxableValue[i]);
            pdfTextView.setBackgroundColor(Color.GRAY);
            tableRowView4.addToRow(pdfTextView);

        }
        horizontalView1taxable.addView(tableRowView4);
        horizontaltaxable.addView(horizontalView1taxable);
        PDFHorizontalWait2 horizontalView2taxable = new PDFHorizontalWait2(getApplicationContext());
        PDFTableView.PDFTableRowView tableRowtaxable= new PDFTableView.PDFTableRowView(getApplicationContext());
        String[] taxable = {" "+myApplicationClass.totalAmount+".00"};
        for(int i = 0;  i<taxable.length; i++) {
            PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdfTextView.setText(taxable[i]);
            pdfTextView.setBackgroundColor(Color.GRAY);
            tableRowtaxable.addToRow(pdfTextView);

        }
        horizontalView2taxable.addView(tableRowtaxable);
        horizontaltaxable.addView(horizontalView2taxable);
        pdfBody.addView(horizontaltaxable);


        PDFLineSeparatorView lineSeparatorView7 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView7);
        PDFHorizontalView horizontalcgst = new PDFHorizontalView(getApplicationContext());
        PDFHorizontalWait4 horizontalView1cgst = new PDFHorizontalWait4(getApplicationContext());
        PDFTableView.PDFTableRowView tableRowView5= new PDFTableView.PDFTableRowView(getApplicationContext());
        String[] GST = {"\nCGST No. 27ADRPY4158R3Z4,@ 09%"};
        for(int i = 0;  i<GST.length; i++) {
            PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdfTextView.setText(GST[i]);
            pdfTextView.setLayout(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1 ));
            pdfTextView.getView().setGravity(Gravity.CENTER_VERTICAL);

            tableRowView5.addToRow(pdfTextView);
        }
        horizontalView1cgst.addView(tableRowView5);
        horizontalcgst.addView(horizontalView1cgst);
        PDFHorizontalWait2 horizontalView2cgst = new PDFHorizontalWait2(getApplicationContext());
        PDFTableView.PDFTableRowView tableRowViewcgst= new PDFTableView.PDFTableRowView(getApplicationContext());
        String[] cGST = {"\n "+myApplicationClass.gstAmount+".00"};
        for(int i = 0;  i<cGST.length; i++) {
            PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdfTextView.setText(cGST[i]);
            pdfTextView.setLayout(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, 1 ));

            tableRowViewcgst.addToRow(pdfTextView);
        }
        horizontalView2cgst.addView(tableRowViewcgst);
        horizontalcgst.addView(horizontalView2cgst);

        pdfBody.addView(horizontalcgst);

        PDFHorizontalView horizontalsgst = new PDFHorizontalView(getApplicationContext());
        PDFHorizontalWait4 horizontalView1sgst = new PDFHorizontalWait4(getApplicationContext());
        PDFTableView.PDFTableRowView tableRowView6= new PDFTableView.PDFTableRowView(getApplicationContext());
        String[] SGST  = {"\nSGST NO .27ADRPY4158R3Z4,@ 09%"};
        for(int i = 0;  i<SGST .length; i++) {
            PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdfTextView.setText(SGST[i]);

            tableRowView6.addToRow(pdfTextView);
        }
        horizontalView1sgst.addView(tableRowView6);
        horizontalsgst.addView(horizontalView1sgst);


        PDFHorizontalWait2 horizontalView2sgst = new PDFHorizontalWait2(getApplicationContext());
        PDFTableView.PDFTableRowView tableRowView7= new PDFTableView.PDFTableRowView(getApplicationContext());
        String[] sgst  = {"\n "+myApplicationClass.gstAmount+".00"};
        for(int i = 0;  i<sgst .length; i++) {
            PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdfTextView.setText(sgst[i]);
            tableRowView7.addToRow(pdfTextView);
        }
        horizontalView2sgst.addView(tableRowView7);
        horizontalsgst.addView(horizontalView2sgst);

        pdfBody.addView(horizontalsgst);
        PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        pdfTextView.setText("\nPAN NO. ADRPY4158R\n");
        pdfBody.addView(pdfTextView);

        PDFLineSeparatorView lineSeparatorView11 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView11);
        PDFTableView.PDFTableRowView tableRowView13= new PDFTableView.PDFTableRowView(getApplicationContext());
        String[] total = {"Total Amount", "", "", "\t\t  "+myApplicationClass.gstandTotalAmount+".00"};
        for(int i = 0;  i<total.length; i++) {
            PDFTextView pdftotal = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdftotal.setText(total[i]);
            pdftotal.setBackgroundColor(Color.GRAY);
            tableRowView13.addToRow(pdftotal);
        }
        pdfBody.addView(tableRowView13);
        PDFLineSeparatorView lineSeparatorView9 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView9);
        PDFTextView space = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        space.setText("");
        pdfBody.addView(space);
        PDFLineSeparatorView lineSeparatorView10 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView10);
        PDFHorizontalView tableRowView8 = new PDFHorizontalView(getApplicationContext());

        PDFTextView word = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        word.setText("Words :\t");
        tableRowView8.addView(word);
        PDFTextView word_data = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        String english =   NumberToword.convert(myApplicationClass.totalforWord);
/*
        String english =   EnglishNumberToWords.convert((totalAmount));
*/
        word_data.setText(""+english+"\tOnly");
        tableRowView8.addView(word_data);
        pdfBody.addView(tableRowView8);

        PDFLineSeparatorView lineSeparatorView12 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView12);


        PDFTableView.PDFTableRowView tableRowView9= new PDFTableView.PDFTableRowView(getApplicationContext());
        String[] sign = {"\n\nNote :Payment by Cheque Ac. Pay\n\n\nBombay Facility Service Only ","\n\n\n\t\t\tFor Bombay Facility Services"};
        for(int i = 0;  i<sign.length; i++) {
            PDFTextView pdftotal = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdftotal.setText(sign[i]);
            tableRowView9.addToRow(pdftotal);
        }
        pdfBody.addView(tableRowView9);

        PDFTextView pdftotal = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        pdftotal.setText("\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tAuthorised Signature");
        pdfBody.addView(pdftotal);
       /* PDFTextView pdfIconLicenseView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H3);
        Spanned icon8Link = Html.fromHtml("Icon from <a href='https://icons8.com'>https://icons8.com</a>");
        pdfIconLicenseView.getView().setText(icon8Link);
        pdfBody.addView(pdfIconLicenseView);*/


        return pdfBody;

    }

    @Override
    protected void onNextClicked(File savedPDFFile) {

        
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);

        Uri apkURI = FileProvider.getUriForFile(
                getApplicationContext(),
                getApplicationContext()
                        .getPackageName() + ".provider", savedPDFFile);
        intentShareFile.setDataAndType(apkURI, URLConnection.guessContentTypeFromName(savedPDFFile.getName()));
        intentShareFile.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intentShareFile.putExtra(Intent.EXTRA_STREAM,
                Uri.parse("file://" + savedPDFFile.getAbsolutePath()));

        startActivity(Intent.createChooser(intentShareFile, "Share File"));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onDownloadClicked(File savedPDFFile) {
        String BFS;
        File folder = new File(Environment.getExternalStorageDirectory().toString(), "BFS");
        PrintManager printManager=(PrintManager)getSystemService(Context.PRINT_SERVICE);
        PrintAttributes.Builder attributes = new PrintAttributes.Builder();
        attributes.setMediaSize(PrintAttributes.MediaSize.ISO_A4);

        try {

            PrintDocumentAdapter printDocumentAdapter = new PdfDocumentAdapter(MainActivity.this,"/storage/emulated/0/Android/data/com.example.pdfview/files/temp/Month/test.pdf");
            printManager.print("document", printDocumentAdapter, new PrintAttributes.Builder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   /* private String numToWords (int n){ //optional
        NumberToword ntw = new NumberToword(); // directly implement this
        return ntw.convert(n);
    }
*/
   public static double round(double value, int scale) {
       return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
   }

    @Override
    public void onBackPressed() {
        super.onBackPressed();



        total=0;

       myApplicationClass.amount.clear();
        myApplicationClass.rt.clear();
        myApplicationClass.grd.clear();
        myApplicationClass.guardcount.clear();
        myApplicationClass.gstandTotalAmount=0.0;
        myApplicationClass.totalforWord=0;
        myApplicationClass.gstAmount=0.0;
        myApplicationClass.totalAmount=0.0;

         gurdSize=0;
        billDate="";
                billNo="";
                billAddress="";
        intGst=0;
                intSGst=0;
        gst=false;
        toAmount=0;
        totalAmount=0;
         gstno="";

        monthName="";
    }
}
