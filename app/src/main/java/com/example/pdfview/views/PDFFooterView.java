package com.example.pdfview.views;

import android.content.Context;
import android.widget.LinearLayout;


import com.example.pdfview.views.basic.PDFVerticalView;
import com.example.pdfview.views.basic.PDFView;

import java.io.Serializable;

public class PDFFooterView extends PDFVerticalView implements Serializable {

    public PDFFooterView(Context context) {
        super(context);
    }

    @Override
    public PDFFooterView addView(PDFView viewToAdd) {
        super.addView(viewToAdd);
        return this;
    }

    @Override
    public LinearLayout getView() {
        return (LinearLayout) super.getView();
    }
}
