package ous.esprit.tn.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import ous.esprit.tn.utils.R;
import ous.esprit.tn.utils.AppSingleton;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Switch) findViewById(R.id.ledSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    callMyApi("http://172.16.220.137:4300/updateSensor?status=1&sensor=led");
                else
                    callMyApi("http://172.16.220.137:4300/updateSensor?status=0&sensor=led");
            }
        });
        ((Switch) findViewById(R.id.buzzerSwitch)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    callMyApi("http://172.16.220.137:4300/updateSensor?status=1&sensor=buzzer");
                else
                    callMyApi("http://172.16.220.137:4300/updateSensor?status=0&sensor=buzzer");
            }
        });
    }

    private void callMyApi(String url) {
        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Call Passed:", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getMessage());
            }
        });
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq);
    }
}
