package com.example.myappwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myappwebservice.WebService.Asynchtask;
import com.example.myappwebservice.WebService.WebService;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class actPrincipal extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_principal);

        Bundle bundle = this.getIntent().getExtras();

        Map<String, String> datos = new HashMap<String, String>();

        WebService webService = new WebService("https://api-uat.kushkipagos.com/transfer/v1/bankList/Public-Merchant-Id="+bundle.getString("token"),
                datos, actPrincipal.this, actPrincipal.this);

        webService.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView respueta = findViewById(R.id.txtMensaje);
        respueta.setText(result);
    }
}