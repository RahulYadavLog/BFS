package com.example.pdfview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
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
import com.example.pdfview.views.basic.PDFImageView;
import com.example.pdfview.views.basic.PDFLineSeparatorView;
import com.example.pdfview.views.basic.PDFTextView;
import com.example.pdfview.views.basic.PDFVerticalView;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URLConnection;
import java.util.Locale;

public class MainBFSActivity extends PDFCreatorActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createPDF("test", new PDFUtil.PDFUtilListener() {
            @Override
            public void pdfGenerationSuccess(File savedPDFFile) {
                Toast.makeText(MainBFSActivity.this, "PDF Created", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void pdfGenerationFailure(Exception exception) {
                Toast.makeText(MainBFSActivity.this, "PDF NOT Created", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected PDFHeaderView getHeaderView(int pageIndex) {
        PDFHeaderView headerView = new PDFHeaderView(getApplicationContext());

        PDFTextView pdfTextViewPage = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.HEADER);
        SpannableString word = new SpannableString("INVOICE");
        word.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        pdfTextViewPage.getView().setTypeface(pdfTextViewPage.getView().getTypeface(), Typeface.BOLD);
        pdfTextViewPage.setText(word);
        pdfTextViewPage.setLayout(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 0));
        pdfTextViewPage.getView().setGravity(Gravity.CENTER_HORIZONTAL);

        headerView.addView(pdfTextViewPage);

        PDFHorizontalView horizontalView = new PDFHorizontalView(getApplicationContext());



        PDFHorizontalWait1 horizontalwait2 = new PDFHorizontalWait1(getApplicationContext());


        PDFImageView imageView = new PDFImageView(getApplicationContext());
        LinearLayout.LayoutParams imageLayoutParam = new LinearLayout.LayoutParams(
                100,
                100, 1);
        imageView.setImageScale(ImageView.ScaleType.FIT_CENTER);
        imageView.setImageResource(R.drawable.logo2);
        imageLayoutParam.setMargins(0, 0, 0, 0);
        imageView.setLayout(imageLayoutParam);
        horizontalwait2.addView(imageView);
        horizontalView.addView(horizontalwait2);
        PDFHorizontalWait1 horizontalwait1 = new PDFHorizontalWait1(getApplicationContext());



        /*PDFTextView newline = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        newline.setText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        horizontalView.addView(newline);
        PDFTextView newline1 = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        newline1.setText("");
        horizontalView.addView(newline1);*/



        PDFVerticalView verticalView = new PDFVerticalView(getApplicationContext());

        PDFTextView newline2 = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H3);
        newline2.setText("Office Address :");
        newline2.getView().setTypeface(pdfTextViewPage.getView().getTypeface(), Typeface.BOLD);
        verticalView.addView(newline2);

        PDFTextView newline3 = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        newline3.setText("Jai Ambe Soc. M. G. \nCross Road No.3\nKandivali (West)\nMumbai 400 067.");
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

        PDFTextView pdfCompanyNameView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H3);
        pdfCompanyNameView.setText("Company Name");
        pdfBody.addView(pdfCompanyNameView);
        PDFLineSeparatorView lineSeparatorView1 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.WHITE);
        pdfBody.addView(lineSeparatorView1);
        PDFTextView pdfAddressView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        pdfAddressView.setText("Address Line 1\nCity, State - 123456");
        pdfBody.addView(pdfAddressView);

        String[] textInTable = {"1", "2", "3", "4"};

        PDFLineSeparatorView lineSeparatorView2 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.WHITE);
        pdfBody.addView(lineSeparatorView2);
        PDFTextView pdfTableTitleView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
        pdfTableTitleView.setText("Table Example");
        pdfBody.addView(pdfTableTitleView);

        PDFTableView.PDFTableRowView tableHeader = new PDFTableView.PDFTableRowView(getApplicationContext());
        for (String s : textInTable) {
            PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdfTextView.setText("Header Title: " + s);
            tableHeader.addToRow(pdfTextView);
        }
        PDFTableView.PDFTableRowView tableRowView1 = new PDFTableView.PDFTableRowView(getApplicationContext());
        for (String s : textInTable) {
            PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
            pdfTextView.setText("Row 1 : " + s);
            tableRowView1.addToRow(pdfTextView);
        }

        PDFTableView tableView = new PDFTableView(getApplicationContext(), tableHeader, tableRowView1);
        for (int i = 0; i < 40; i++) {
            // Create 10 rows
            PDFTableView.PDFTableRowView tableRowView = new PDFTableView.PDFTableRowView(getApplicationContext());
            for (String s : textInTable) {
                PDFTextView pdfTextView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.P);
                pdfTextView.setText("Row " + (i + 2) + ": " + s);
                tableRowView.addToRow(pdfTextView);
            }
            tableView.addRow(tableRowView);
        }
        pdfBody.addView(tableView);

        PDFLineSeparatorView lineSeparatorView3 = new PDFLineSeparatorView(getApplicationContext()).setBackgroundColor(Color.BLACK);
        pdfBody.addView(lineSeparatorView3);

        PDFTextView pdfIconLicenseView = new PDFTextView(getApplicationContext(), PDFTextView.PDF_TEXT_SIZE.H3);
        Spanned icon8Link = Html.fromHtml("Icon from <a href='https://icons8.com'>https://icons8.com</a>");
        pdfIconLicenseView.getView().setText(icon8Link);
        pdfBody.addView(pdfIconLicenseView);

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

    @Override
    protected void onDownloadClicked(File savedPDFFile) throws FileNotFoundException {

    }
}