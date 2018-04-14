package com.caocao.convertmoney;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;
    Spinner spinnerto, spinnerfrom;
    EditText editNumber;
    Button btnConvert;
    ArrayAdapter<CharSequence> adapterfrom,adapterto;
    LinearLayout mCLayout;

    int positionSpinnerfrom,positionSpinnerto;
    double giaVND = 22800;
    double giaUSD = 1;
    double giaEUR= 0.81098;
    private static DecimalFormat df2 = new DecimalFormat("#.####");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addControl() {
        mCLayout = (LinearLayout) findViewById(R.id.lnLayout);
        txtResult = findViewById(R.id.txtResult);
        spinnerto = findViewById(R.id.spinnerto);
        spinnerfrom = findViewById(R.id.spinnerfrom);
        editNumber = findViewById(R.id.editNumber);
        btnConvert = findViewById(R.id.btnConvert);
        adapterto = ArrayAdapter.createFromResource(
                this, R.array.money_arrays, android.R.layout.simple_spinner_item);
        adapterfrom = ArrayAdapter.createFromResource(
                this, R.array.money_arrays, android.R.layout.simple_spinner_item);
        positionSpinnerfrom = 0;
        positionSpinnerto = 0;
    }
    private void addEvent(){

        mCLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the input method manager
                InputMethodManager inputMethodManager = (InputMethodManager)
                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                // Hide the soft keyboard
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
            }
        });

        adapterfrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerfrom.setAdapter(adapterfrom);
        spinnerfrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                positionSpinnerfrom = i;
                Convert();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        adapterto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerto.setAdapter(adapterfrom);
        spinnerto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),i+"",Toast.LENGTH_SHORT).show();
                positionSpinnerto  = i;

                Convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Convert();

            }
        });
    }

    private  void Convert(){
        int temp = 0;
        try{
            temp =Integer.parseInt(editNumber.getText().toString());
        }
        catch (Exception e){
            temp = 0;
        }
        Log.d("Lai",editNumber.getText().toString());
        if(positionSpinnerto==1 && positionSpinnerfrom==0){
            int moneyfrom =temp;
            double total =((double) moneyfrom)*giaUSD/giaVND;
            txtResult.setText("Kết quả quy đổi: \n" +
                    moneyfrom+"VND = " +
                    df2.format(total)+ "USD" +
                    "");
            Log.d("Lai",total+"");
        }
        if(positionSpinnerto==0 && positionSpinnerfrom==1){
            int moneyfrom = temp;
            double total =((double) moneyfrom)*giaVND/giaUSD;
            txtResult.setText("Kết quả quy đổi: \n" +
                    moneyfrom+"USD = " +
                    df2.format(total)+ "VND" +
                    "");
            Log.d("Lai",total+"");
        }
        if(positionSpinnerto==0 && positionSpinnerfrom==0){
            int moneyfrom = temp;
            double total =((double) moneyfrom)*giaVND/giaVND;
            txtResult.setText("Kết quả quy đổi: \n" +
                    moneyfrom+"VND = " +
                    df2.format(total)+ "VND" +
                    "");
            Log.d("Lai",total+"");
        }
        if(positionSpinnerto==1 && positionSpinnerfrom==1){
            int moneyfrom = temp;
            double total =((double) moneyfrom)*giaUSD/giaUSD;
            txtResult.setText("Kết quả quy đổi: \n" +
                    moneyfrom+"USD = " +
                    df2.format(total)+ "USD " +
                    "");
            Log.d("Lai",total+"");
        }
        if(positionSpinnerto==2 && positionSpinnerfrom==2){
            int moneyfrom = temp;
            double total =((double) moneyfrom)*giaVND/giaVND;
            txtResult.setText("Kết quả quy đổi: \n" +
                    moneyfrom+"EUR = " +
                    df2.format(total)+ "EUR " +
                    "");
            Log.d("Lai",total+"");
        }
        if(positionSpinnerto==2 && positionSpinnerfrom==0){
            int moneyfrom = temp;
            double total =((double) moneyfrom)*giaEUR/giaVND;
            txtResult.setText("Kết quả quy đổi: \n" +
                    moneyfrom+"VND = " +
                    df2.format(total)+ "EUR " +
                    "");
            Log.d("Lai",total+"");
        }
        if(positionSpinnerto==0 && positionSpinnerfrom==2){
            int moneyfrom = temp;
            double total =((double) moneyfrom)*giaVND/giaEUR;
            txtResult.setText("Kết quả quy đổi: \n" +
                    moneyfrom+"EUR = " +
                    df2.format(total)+ "VND " +
                    "");
            Log.d("Lai",total+"");
        }
        if(positionSpinnerto==2 && positionSpinnerfrom==1){
            int moneyfrom = temp;
            double total =((double) moneyfrom)*giaEUR/giaUSD;
            txtResult.setText("Kết quả quy đổi: \n" +
                    moneyfrom+"USD = " +
                    df2.format(total)+ "EUR " +
                    "");
            Log.d("Lai",total+"");
        }
        if(positionSpinnerto==1 && positionSpinnerfrom==2){
            int moneyfrom = temp;
            double total =((double) moneyfrom)*giaUSD/giaEUR;
            txtResult.setText("Kết quả quy đổi: \n" +
                    moneyfrom+"EUR = " +
                    df2.format(total)+ "USD " +
                    "");
            Log.d("Lai",total+"");
        }

    }

}
