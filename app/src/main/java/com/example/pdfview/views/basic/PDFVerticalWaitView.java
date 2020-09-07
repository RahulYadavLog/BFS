package com.example.pdfview.views.basic;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.Serializable;

public class PDFVerticalWaitView extends PDFView implements Serializable {

    public PDFVerticalWaitView(Context context) {
        super(context);
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams childLayoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        linearLayout.setLayoutParams(childLayoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        super.setView(linearLayout);
    }
    @Override
    public PDFVerticalWaitView addView(PDFView viewToAdd) {
        getView().addView(viewToAdd.getView());
        super.addView(viewToAdd);
        return this;
    }

    @Override
    public PDFVerticalWaitView setLayout(LinearLayout.LayoutParams layoutParams) {
        super.setLayout(layoutParams);
        return this;
    }

    @Override
    public LinearLayout getView() {
        return (LinearLayout) super.getView();
    }
}
