package com.caocao.client_get_distance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private EditText editKinhdo1,editKinhdo2,editVido1,editVido2;
    private Button btnShow;
    private TextView txtShow;

    private static MainActivity mInstance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInstance = this;
        addControl();
        addEvent();
    }

    private void addControl(){
        editKinhdo1 = findViewById(R.id.editkinhdo1);
        editKinhdo2 = findViewById(R.id.editkinhdo2);
        editVido1 = findViewById(R.id.editvido1);
        editVido2 = findViewById(R.id.editvido2);
        btnShow = findViewById(R.id.btnDistance);
        txtShow = findViewById(R.id.txtShow);
    }
    private void addEvent(){
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //URL of the request we are sending

                String url = "https://cpdist.herokuapp.com/location";
                Log.v("1",url);
                JSONObject postparams=new JSONObject();
                try {

                    String tem = editVido1.getText().toString()+","+editKinhdo1.getText().toString();
                    Log.v("a",tem);
                  //  Toast.makeText(getApplicationContext(),tem,Toast.LENGTH_SHORT).show();
                    postparams.put("origin",tem);
                } catch (JSONException e) {
                    e.printStackTrace();
                  //  Toast.makeText(getApplicationContext(),"Loi 1",Toast.LENGTH_SHORT).show();
                    Log.v("a","Loi 1");
                }
                try {
                    String tem = editVido2.getText().toString()+","+editKinhdo2.getText().toString();
                    Log.v("a",tem);
                  //  Toast.makeText(getApplicationContext(),tem,Toast.LENGTH_SHORT).show();
                    postparams.put("destination",tem);
                } catch (JSONException e) {
                    e.printStackTrace();
                   // Toast.makeText(getApplicationContext(),"Loi 2",Toast.LENGTH_SHORT).show();
                    Log.v("a","Loi 2");
                }
                //Toast.makeText(getApplicationContext(),postparams.toString(),Toast.LENGTH_SHORT).show();
                Log.v("a",postparams.toString());

                JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                        url, postparams,
                        new Response.Listener() {
                            @Override
                            public void onResponse(Object response) {
                                Log.v("1",response.toString());
                                JSONObject jsonObject = (JSONObject) response;
                                try {
                                    if((Boolean)jsonObject.get("success")==true&&jsonObject.get("data")!=null){
                                        JSONObject temp = (JSONObject) jsonObject.get("data");
                                        txtShow.setText("Điểm thứ nhất:"+temp.get("origin")+
                                                "\n Điểm thứ hai:"+temp.get("destination")+
                                                "\n Khoảng cách hai điểm: "+temp.get("distance").toString());
                                    }else{
                                        txtShow.setText("Không thể tìm được khoảng cách giưa hai điểm");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    txtShow.setText("Không thể tìm được khoảng cách giưa hai điểm");
                                }
                            }

                            public void onResponse(JSONObject response) {

                                //Success Callback
                                Log.v("1",response.toString());
                                JSONObject jsonObject = (JSONObject) response;
                                try {
                                    if((Boolean)jsonObject.get("success")==true&&jsonObject.get("data")!=null){
                                        JSONObject temp = (JSONObject) jsonObject.get("data");
                                        txtShow.setText("Điểm thứ nhất:"+temp.get("origin")+
                                                "\n Điểm thứ hai:"+temp.get("destination")+
                                                "\n Khoảng cách hai điểm: "+temp.get("distance").toString());
                                    }else{
                                        txtShow.setText("Không thể tìm được khoảng cách giưa hai điểm");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    txtShow.setText("Không thể tìm được khoảng cách giưa hai điểm");
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                //Failure Callback
                                txtShow.setText("Không thể tìm được khoảng cách giưa hai điểm");
                                Log.v("5",error.toString());

                            }
                        });

                    // Adding the request to the queue along with a unique string tag
                Log.v("tag 1",jsonObjReq.toString());
                MainActivity.getInstance().addToRequestQueue(jsonObjReq,"getRequest");

            }
        });
    }
    public static synchronized MainActivity getInstance()
    {
        return mInstance;
    }
    public RequestQueue getRequestQueue()
    {
        if (requestQueue==null)
            requestQueue= Volley.newRequestQueue(getApplicationContext());

        return requestQueue;
    }
    public void addToRequestQueue(Request request, String tag)
    {
        request.setTag(tag);
        getRequestQueue().add(request);

    }

    public void cancelAllRequests(String tag)
    {
        getRequestQueue().cancelAll(tag);
    }
}
