package ous.esprit.tn.iot;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import ous.esprit.tn.iot.utils.AppSingleton;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Switch)findViewById(R.id.led)).setOnCheckedChangeListener(this);
        ((Switch)findViewById(R.id.buzz)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(b)
        {
            buzzerOn();

        }else{

            buzzerOff();
        }
    }


    /** buzzer **/
    private void buzzerOn(){ StringRequest strReq = new StringRequest(Request.Method.GET,
                "http://192.168.236.1:4300/updateSensor?status=1&sensor=buzzer", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("buzzer","res: "+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("buzzer", "test "+error.getMessage());
            }
        });
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq);
    }

    private void buzzerOff(){
        StringRequest strReq = new StringRequest(Request.Method.GET,
                "http://192.168.236.1:4300/updateSensor?status=0&sensor=buzzer", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("buzzer","res: "+response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("buzzer", "test "+error.getMessage());
            }
        });
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq);
    }


    /** le **/
    public void ledOn(){
        StringRequest strReq = new StringRequest(Request.Method.GET,
                "http://192.168.236.1:4300/updateSensor?status=1&sensor=led", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("led"," yemchi");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("led", error.getMessage());
            }
        });
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq);
    }
}
