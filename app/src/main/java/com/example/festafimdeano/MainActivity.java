package com.example.festafimdeano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewHolder mviewholder = new ViewHolder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mviewholder.textData = findViewById(R.id.text_data);
        this.mviewholder.textDiasRestantes = findViewById(R.id.text_dias_restantes);
        this.mviewholder.ButtonConfirmacao = findViewById(R.id.button_confirmacao);
        this.mviewholder.ButtonConfirmacao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_confirmacao){
            Intent intent = new Intent(this, DetailsActivity.class);
            startActivity(intent);
        }
    }

    public static class ViewHolder{
        TextView textData;
        TextView textDiasRestantes;
        Button ButtonConfirmacao;

    }
}