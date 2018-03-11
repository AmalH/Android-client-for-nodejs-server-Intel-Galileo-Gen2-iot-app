package ous.esprit.tn.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import ous.esprit.tn.utils.AppSingleton;
import ous.esprit.tn.utils.R;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** led and buzzer controls **/
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

        /** adding threshold**/
        ( findViewById(R.id.sendThreshold)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST TEST",((EditText)(findViewById(R.id.thresholdValue))).getText().toString());
                callMyApi("http://172.16.220.137:4300/updateSeuil?seuil="+((EditText)(findViewById(R.id.thresholdValue))).getText());
            }
        });

        /** reading maximum temperature **/
         readMaxTmpValue();
        ( findViewById(R.id.refreshMaxTmp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readMaxTmpValue();
            }
        });

        /** sending message to db **/
        readMaxTmpValue();
        ( findViewById(R.id.sendMsg)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callMyApi("http://172.16.220.137:4300/sendMessage?content="+"\""+((EditText)(findViewById(R.id.msgContent))).getText()+"\"");

            }
        });
    }

    private void callMyApi(String url) {
        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Call Passed:", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("ERROR", error.getMessage());
            }
        });
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq);
    }


    private void readMaxTmpValue(){
        StringRequest strReq = new StringRequest(Request.Method.GET,
                "http://172.16.220.137:4300/getSeuil", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Call Passed:", response.toString());
                ((TextView)(findViewById(R.id.maxTmpVal))).setText(response.toString());
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
