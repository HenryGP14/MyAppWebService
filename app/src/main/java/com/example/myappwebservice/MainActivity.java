package com.example.myappwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnEnviar_Click(View view){
        Intent intent = new Intent(MainActivity.this, actPrincipal.class);
        EditText usuario = findViewById(R.id.edUsuario);

        Bundle bundle = new Bundle();
        bundle.putString("token", usuario.getText().toString());

        intent.putExtras(bundle);

        startActivity(intent);

    }
}