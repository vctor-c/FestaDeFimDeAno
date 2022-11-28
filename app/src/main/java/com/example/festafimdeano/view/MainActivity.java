package com.example.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.festafimdeano.R;
import com.example.festafimdeano.contants.FimDeAnoConstants;
import com.example.festafimdeano.data.SecurityPreferences;
import com.example.festafimdeano.view.DetailsActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewHolder mviewholder = new ViewHolder();
    private static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mviewholder.textData = findViewById(R.id.text_data);
        this.mviewholder.textDiasRestantes = findViewById(R.id.text_dias_restantes);
        this.mviewholder.ButtonConfirmacao = findViewById(R.id.button_confirmacao);
        this.mviewholder.ButtonConfirmacao.setOnClickListener(this);
        this.mSecurityPreferences = new SecurityPreferences(this);
        //DATA DE HOJE
        this.mviewholder.textData.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));
        this.mviewholder.textDiasRestantes.setText(String.format("%s %s", String.valueOf(this.getDiasRestantes()), getString(R.string.dias)));

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verificarPresenca();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_confirmacao) {
            String presenca = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.CHAVE_PRESENCA);
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.CHAVE_PRESENCA, presenca);
            startActivity(intent);
        }
    }

    private void verificarPresenca() {
        String presenca = this.mSecurityPreferences.getStoredString(FimDeAnoConstants.CHAVE_PRESENCA);
        if (presenca.equals("")) {
            this.mviewholder.ButtonConfirmacao.setText(getString(R.string.nao_confirmado));
        } else if (presenca.equals(FimDeAnoConstants.CONFIRMACAO_SIM)) {
            this.mviewholder.ButtonConfirmacao.setText(getString(R.string.sim));
        } else {
            this.mviewholder.ButtonConfirmacao.setText(getString(R.string.nao));
        }
    }

    private int getDiasRestantes() {
        Calendar calendarHoje = Calendar.getInstance();
        int hoje = calendarHoje.get(Calendar.DAY_OF_YEAR);

        Calendar calendarUltimoDia = Calendar.getInstance();
        int diaMaximo = calendarUltimoDia.getActualMaximum(Calendar.DAY_OF_YEAR);

        return diaMaximo - hoje;
    }

    public static class ViewHolder {
        TextView textData;
        TextView textDiasRestantes;
        Button ButtonConfirmacao;

    }
}