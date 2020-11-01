package com.example.pdfview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.ContentValues.TAG;

public class LabelMasterAdapter extends RecyclerView.Adapter<LabelMasterAdapter.LabelView> {
    Context context;
    List<LabelActivityModel>labelMasterModels;
    public LabelMasterAdapter(Context context, List<LabelActivityModel> labelMasterModels) {
        this.context = context;
        this.labelMasterModels = labelMasterModels;
    }


    @NonNull
    @Override
    public LabelView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater= LayoutInflater.from(context);
        view=inflater.inflate(R.layout.label_recy_layout,parent,false);
        return new LabelMasterAdapter.LabelView(view);    }

    @Override
    public void onBindViewHolder(@NonNull LabelView holder, final int position) {
        LabelActivityModel label=labelMasterModels.get(position);
        holder.txtInvcode.setText(label.getGuardType());
        holder.txtRate.setText(label.getRate());
        holder.txtAmount.setText(label.getAmount());
        holder.txtguard.setText(label.getGuard());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                labelMasterModels.remove(position);

                notifyDataSetChanged();
                Log.e(TAG,"Data Not attach");

            }
        });

    }

    @Override
    public int getItemCount() {
        return labelMasterModels.size();
    }

    public class LabelView extends RecyclerView.ViewHolder{

        TextView txtInvcode,txtPcs,txtWt,txtRate,txtAmount,txtguard;
        LinearLayout remove;
        public LabelView(View itemView) {
            super(itemView);
            txtInvcode=itemView.findViewById(R.id.invcode);
            txtRate=itemView.findViewById(R.id.rate);
            txtAmount=itemView.findViewById(R.id.amt);
            txtguard=itemView.findViewById(R.id.guard);
            remove=itemView.findViewById(R.id.remove);
        }
    }

}
