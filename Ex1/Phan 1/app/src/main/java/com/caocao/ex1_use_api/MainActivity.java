package com.caocao.ex1_use_api;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button btnConvert;
    private TextView txtLocation;
    private EditText editKinhDo;
    private EditText editViDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();
    }

    private  void addControl(){
        btnConvert = findViewById(R.id.btnSend);
        txtLocation = findViewById(R.id.txtLocation);
        editKinhDo = findViewById(R.id.editKinhDo);
        editViDo = findViewById(R.id.editVido);
    }


    private void addEvent() {
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kinhdo = editKinhDo.getText().toString();
                String vido = editViDo.getText().toString();

                if(kinhdo.trim().isEmpty()||vido.trim().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        Location location = new Location("vi tri");
                        location.setLongitude(Double.valueOf(kinhdo).doubleValue());
                        location.setLatitude(Double.valueOf(vido).doubleValue());
                        txtLocation.setText(convertLocationToAddress(location));
                    }catch(Exception e){
                        Toast.makeText(getApplicationContext(),"Vui lòng kiểm tra lại thông tin",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private String convertLocationToAddress(Location location) {
        String addressText;
        String errorMessage = "";

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1
            );
        } catch (IOException ioException) {
            // Network or other I/O issues
            errorMessage ="Lỗi mạng";
            Log.e("1", errorMessage, ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Invalid long / lat
            errorMessage = "Lỗi không xác đinh kinh độ vi độ";
            Log.e("2", errorMessage + ". " +
                    "Latitude = " + location.getLatitude() +
                    ", Longitude = " +
                    location.getLongitude(), illegalArgumentException);
        }

        // No address was found
        if (addresses == null || addresses.size() == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = "Lỗi chuổi trống";
                Log.e("3", errorMessage);
            }
            addressText = errorMessage;

        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<>();

            // Fetch the address lines, join them, and return to thread
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            Log.i("4", "Không tìm thấy địa chỉ");
            addressText =
                    TextUtils.join(System.getProperty("line.separator"),
                            addressFragments);
        }

        return addressText;

    }
}
