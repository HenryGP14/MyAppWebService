package com.example.myappwebservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myappwebservice.WebService.Asynchtask;
import com.example.myappwebservice.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class actPrincipal extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_principal);

        Bundle bundle = this.getIntent().getExtras();

        Map<String, String> datos = new HashMap<>();

        WebService webService = new WebService("https://api-uat.kushkipagos.com/transfer/v1/bankList",
                datos, actPrincipal.this, actPrincipal.this);

        webService.execute("GET", "Public-Merchant-Id", bundle.getString("token"));
    }

    @Override
    public void processFinish(String result) {
        TextView respueta = findViewById(R.id.txtMensaje);
        ArrayList<String> lsBancos = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject banco = jsonArray.getJSONObject(i);
                lsBancos.add(banco.getString("name"));
            }
            respueta.setText("El token que ingreso es valido");
            RecyclerView rc = findViewById(R.id.rcView);
        }catch (Exception e)
        {
            respueta.setText("El token que ingreso no es valido" );
        }
    }
}