package com.example.myappwebservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

        WebService webService = new WebService("https://api-uat.kushkipagos.com/transfer-subscriptions/v1/bankList",
                datos, actPrincipal.this, actPrincipal.this);

        webService.execute("GET", "Public-Merchant-Id", bundle.getString("token"));
    }

    @Override
    public void processFinish(String result) {
        TextView respueta = findViewById(R.id.txtMensaje);
        ArrayList<String> lsBancos = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(result);
            lsBancos.add("Seleccione un Banco");
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject banco = jsonArray.getJSONObject(i);
                lsBancos.add(banco.getString("name"));
            }
            respueta.setText("El token que ingreso es valido");
            Spinner sp = findViewById(R.id.spDatos);
            ArrayAdapter<CharSequence> adapter;
            adapter = new ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, lsBancos);
            sp.setAdapter(adapter);

            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(parent.getContext(),"A seleccionado el: " + parent.getItemAtPosition(position),Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }catch (Exception e)
        {
            respueta.setText("El token que ingreso no es valido" );
        }
    }
}