package com.example.pdfview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddDetailActivity1 extends AppCompatActivity {

  Button add,pdfBtn;
  AlertDialog.Builder dialogBuilder;
  AlertDialog data_alertDialog;
  ArrayList<LabelActivityModel> labelActivityModels;
  LabelActivityAdapter labelActivityAdapter;
  public static String  guardName;
  public static String  billAddress;
  GridLayoutManager label__Activity_LayoutManager;
  RecyclerView guardRecycler;
  LinearLayout GuardLayout;
  EditText billDate,gstno;
  EditText billNo,gstPercentage;
  DatePickerDialog picker;
  CheckBox gstCheck;
  Spinner addresh;
  String monthName;
  int monthvalue;
  MyApplicationClass myApplicationClass;
  boolean sGstCheck=false,GstCheck=true;

  double total;
  String strTotal;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_detail1);
    add=findViewById(R.id.add);
    pdfBtn=findViewById(R.id.pdfbtn);
    billDate=findViewById(R.id.date);
    billNo=findViewById(R.id.bill_no);
    addresh=findViewById(R.id.address);
    guardRecycler=findViewById(R.id.gurdRecycler);
    GuardLayout=findViewById(R.id.layout);
    gstCheck=findViewById(R.id.Gst);
    gstno=findViewById(R.id.gst);
    gstPercentage=findViewById(R.id.gst_per);
myApplicationClass=(MyApplicationClass)getApplicationContext();

    List<String> categories = new ArrayList<String>();
    categories.add("Eltee Geejay");
    categories.add("Hormoni CHS Ltd Malad");
    categories.add("Jay Sukh Sagar");
    categories.add("New Shreeji Chemist hk");
    categories.add("New Sree Jee Chemist");
    categories.add("Ramesh");
    categories.add("Shree kandivali achal gachh jain");
    categories.add("Simca");
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddDetailActivity1.this, android.R.layout.simple_spinner_item, categories);

    // Drop down layout style - list view with radio button
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // attaching data adapter to spinner
    addresh.setAdapter(dataAdapter);
    addresh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object item = parent.getItemAtPosition(position);
        switch (position)
        {
          case 0:
            billAddress= "\tEltee Geejay CHS.LTD. , \n\tSai Baba  Nagar , \n\tBoriwali  West , \n\tMumbai.";
            break;
          case 1:
            billAddress= "\tHormoni CHS Ltd. , \n\tMarve Road,  , \n\tMalad (W) , \n\tMumbai.400095";
            break;
          case 2:
            billAddress= "\tJay Sukh Sagar CHS.Ltd. , \n\tM. G . Road Patel Nagar  , \n\tKandivali West Mumbai 400067";
            break;
          case 3:
            billAddress= "\tNew Shreeji Chemist , \n\tM g  Road ,opp. SVC Bank Kandivali (w) , \n\tMumbai -400 067.";
            break;
          case 4:
            billAddress= "\tNew Shreeji Chemist , \n\tM g  Road ,opp. SVC Bank Kandivali (w) , \n\tMumbai -400 067.";
            break;
          case 5:
            billAddress= "\tRamesh And Associates Wagal , \n\tCompound  Khuni Gav Bhiwandi";
            break;
          case 6:
            billAddress= "\tSHREE KANDIVALI ACHAL GACHH JAIN , \n\tSANGH SAI BAUG ESTATE MEENA , \n\tSUTHAR MARG, M.G.CROSS RD NO.3 , \n\tKANDIVALI (W) ,MUMBAI-400 067.";
            break;
          case 7:
            billAddress= "\tSimca Adverting , \n\tC-6, Saranga  Bungalow , \n\t3rd X Lane, Lokhandwala Market , \n\tAndheri(W) ,Mumbai- 400-053. , \n\t(Khandala Bungalow)";
            break;
        }



      }
      public void onNothingSelected(AdapterView<?> parent) {
      }
    });

    billDate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int  month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(AddDetailActivity1.this,
                new DatePickerDialog.OnDateSetListener() {
                  @Override
                  public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    billDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    monthvalue=monthOfYear+1;

                    switch (monthvalue)
                    {
                      case 1:
                        monthName="January";
                        break;
                      case 2:
                        monthName="February";
                        break;
                      case 3:
                        monthName="March";
                        break;
                      case 4:
                        monthName="April";
                        break;
                      case 5:
                        monthName="May";
                        break;
                      case 6:
                        monthName="June";
                        break;
                      case 7:
                        monthName="July";
                        break;
                      case 8:
                        monthName="August";
                        break;
                      case 9:
                        monthName="September";
                        break;
                      case 10:
                        monthName="October";
                        break;
                      case 11:
                        monthName="November";
                        break;
                      case 12:
                        monthName="December";
                        break;
                    }
                  }
                }, year, month, day);
        picker.show();
      }

    });



    pdfBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        for (int i = 0; i <labelActivityModels.size() ; i++) {
          myApplicationClass.amount.add(i, Float.valueOf(labelActivityModels.get(i).getAmount()));
          myApplicationClass.rt.add(i, Float.valueOf(labelActivityModels.get(i).getRate()));
          myApplicationClass.grd.add(i, (labelActivityModels.get(i).getGuardType()));
          myApplicationClass.guardcount.add(i, (labelActivityModels.get(i).getGuard()));




        }

        for (int i = 0; i <myApplicationClass.amount.size() ; i++) {

          myApplicationClass.totalAmount=myApplicationClass.totalAmount+myApplicationClass.amount.get(i);
        }




        if(gstCheck.isChecked())
        {
          GstCheck=true;
        }
        else {
          GstCheck=false;
        }


        if(gstCheck.isChecked()&&!gstPercentage.getText().toString().isEmpty())
        {
          myApplicationClass.gstAmount=((myApplicationClass.totalAmount)*(Integer.parseInt(gstPercentage.getText().toString()))/100);
          myApplicationClass.gstandTotalAmount=myApplicationClass.totalAmount+myApplicationClass.gstAmount;
        }
        else {
          myApplicationClass.gstandTotalAmount=myApplicationClass.totalAmount;
        }


        String strTotal= String.valueOf(myApplicationClass.gstandTotalAmount);
        myApplicationClass.totalforWord= Math.round(Float.parseFloat(strTotal));
        Intent intent=new Intent(AddDetailActivity1.this,MainActivity.class);

      /*  intent.putIntegerArrayListExtra("rate", myApplicationClass.rt);
        intent.putStringArrayListExtra("duty", myApplicationClass.duty);*/
        intent.putStringArrayListExtra("guard", myApplicationClass.grd);
        intent.putStringArrayListExtra("guardCount", myApplicationClass.guardcount);
        intent.putExtra("date",billDate.getText().toString());
        intent.putExtra("billno",billNo.getText().toString());
        intent.putExtra("size",labelActivityModels.size());
        intent.putExtra("add",billAddress);
        intent.putExtra("gst",GstCheck);
        intent.putExtra("gstno", gstno.getText().toString());
        intent.putExtra("month",monthName);
        startActivity(intent);


      }
    });
    label__Activity_LayoutManager = new GridLayoutManager(AddDetailActivity1.this, 1);
    guardRecycler.setLayoutManager(label__Activity_LayoutManager);
    labelActivityModels = new ArrayList<>();
    labelActivityAdapter = new LabelActivityAdapter(AddDetailActivity1.this, labelActivityModels, new LabelActivityAdapter.RecycleCheck() {
      @Override
      public void sendCheck(boolean viewCheck) {
        if(viewCheck==true) {
          GuardLayout.setVisibility(View.GONE);
        }

      }
    });

    guardRecycler.setAdapter(labelActivityAdapter);

    add.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {


        dialogBuilder = new AlertDialog.Builder(AddDetailActivity1.this);
// ...Irrelevant code for customizing the buttons and title
        View       dialogView = LayoutInflater.from(AddDetailActivity1.this).inflate(R.layout.label_data_layout,null, false);
        final EditText etPcs,etRate,etWt,etDuty,etguard;
        LinearLayout addItem;

        Button btnSave;

        final Spinner sprInvcode;
        final ArrayList<LabelMasterModel> labelMasterModel;
        final LabelMasterAdapter labelMasterAdapter;
        RecyclerView labelRecycler;
        GridLayoutManager label_LayoutManager;
        sprInvcode=dialogView.findViewById(R.id.invcode);
        etRate=dialogView.findViewById(R.id.rate);
        etDuty=dialogView.findViewById(R.id.duty);
        addItem=dialogView.findViewById(R.id.addItem);
        etguard=dialogView.findViewById(R.id.guard);
        btnSave=dialogView.findViewById(R.id.save);

        List<String> categories = new ArrayList<String>();
        categories.add("Security Guard");
        categories.add("Bouncer");
        categories.add("Body Guard");
        categories.add("Lady Guard");
        categories.add("House keeping");
        categories.add("Supervisor");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddDetailActivity1.this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        sprInvcode.setAdapter(dataAdapter);
        sprInvcode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Object item = parent.getItemAtPosition(position);
            guardName= item.toString();


          }
          public void onNothingSelected(AdapterView<?> parent) {
          }
        });



        final DecimalFormat amountFormate  = new DecimalFormat("#######.###");
        amountFormate.setMaximumFractionDigits(3);




        labelRecycler =dialogView.findViewById(R.id.labelRecycle);


        label_LayoutManager = new GridLayoutManager(AddDetailActivity1.this, 1);
        labelRecycler.setLayoutManager(label_LayoutManager);
        labelMasterModel = new ArrayList<>();
        labelMasterAdapter = new LabelMasterAdapter(AddDetailActivity1.this, labelActivityModels);
        labelRecycler.setAdapter(labelMasterAdapter);



        addItem.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (!etRate.getText().toString().isEmpty()&& !etDuty.getText().toString().isEmpty()) {
              try {
                             /*   float number = Float.valueOf(etAmount.getText().toString());
                                int i = Math.round(number);*/

                double frate = Double.valueOf(etRate.getText().toString());

                String round_rate= String.valueOf(frate);
                double  oneDay=frate/30;
                double totalAmount=oneDay*(Double.valueOf(etDuty.getText().toString()));
                String amount= String.valueOf(totalAmount);
                String strGuard=etguard.getText().toString();

                labelMasterModel.add(new LabelMasterModel(guardName,round_rate,amount,strGuard));
                labelActivityModels.add(new LabelActivityModel(guardName,round_rate,amount,strGuard));

                labelActivityAdapter.notifyDataSetChanged();
                labelMasterAdapter.notifyDataSetChanged();


                etRate.setText("");
                etDuty.setText("");
                etguard.setText("");
                etRate.requestFocus();
              } catch (Exception e) {
                e.getMessage();
              }

            } else {
              Toast.makeText(AddDetailActivity1.this, "Please Fill Add Fields", Toast.LENGTH_SHORT).show();
            }
          }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            data_alertDialog.dismiss();

          }
        });


        dialogBuilder.setView(dialogView);


        data_alertDialog = dialogBuilder.create();
        data_alertDialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, 1350);
        data_alertDialog.show();



      }
    });
  }

}