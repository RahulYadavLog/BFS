package com.example.pdfview.views.basic;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.io.Serializable;

public class PDFVerticalSpecifivView extends PDFView implements Serializable {

    public PDFVerticalSpecifivView(Context context) {
        super(context);
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams childLayoutParams = new LinearLayout.LayoutParams(
                100,
                100, 1);
        linearLayout.setLayoutParams(childLayoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        super.setView(linearLayout);
    }
    @Override
    public PDFVerticalSpecifivView addView(PDFView viewToAdd) {
        getView().addView(viewToAdd.getView());
        super.addView(viewToAdd);
        return this;
    }

    @Override
    public PDFVerticalSpecifivView setLayout(LinearLayout.LayoutParams layoutParams) {
        super.setLayout(layoutParams);
        return this;
    }

    @Override
    public LinearLayout getView() {
        return (LinearLayout) super.getView();
    }
}
