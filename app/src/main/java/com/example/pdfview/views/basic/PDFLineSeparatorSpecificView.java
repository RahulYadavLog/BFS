package com.example.pdfview.views.basic;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class PDFLineSeparatorSpecificView extends PDFView {
    public PDFLineSeparatorSpecificView(Context context) {
        super(context);

        View separatorLine = new View(context);
        LinearLayout.LayoutParams separatorLayoutParam = new LinearLayout.LayoutParams(100, 1);
        separatorLine.setPadding(0, 5, 0, 5);
        separatorLine.setLayoutParams(separatorLayoutParam);

        super.setView(separatorLine);
    }

    @Override
    protected PDFLineSeparatorSpecificView addView(PDFView viewToAdd) throws IllegalStateException {
        throw new IllegalStateException("Cannot add subview to Line Separator");
    }

    @Override
    public PDFLineSeparatorSpecificView setLayout(LinearLayout.LayoutParams layoutParams) {
        super.setLayout(layoutParams);
        return this;
    }



    public PDFLineSeparatorSpecificView setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        return this;
    }

   /* public PDFLineSeparatorView setBackground( color) {
        super.setBackgroundColor(color);
        return this;
    }*/
}
