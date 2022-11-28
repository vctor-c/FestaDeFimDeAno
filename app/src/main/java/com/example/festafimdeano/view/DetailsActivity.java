package com.example.festafimdeano.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.example.festafimdeano.R;
import com.example.festafimdeano.contants.FimDeAnoConstants;
import com.example.festafimdeano.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        this.mSecurityPreferences = new SecurityPreferences(this);
        this.mViewHolder.checkParticipar = findViewById(R.id.check_participar);
        this.mViewHolder.checkParticipar.setOnClickListener(this);
        this.carregarDadosDaActivity();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.check_participar) {
            if (this.mViewHolder.checkParticipar.isChecked()) {
                this.mSecurityPreferences.StoreString(FimDeAnoConstants.CHAVE_PRESENCA, FimDeAnoConstants.CONFIRMACAO_SIM);
            } else {
                this.mSecurityPreferences.StoreString(FimDeAnoConstants.CHAVE_PRESENCA, FimDeAnoConstants.CONFIRMACACAO_NAO);
            }
        }
    }

    public void carregarDadosDaActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String presenca = extras.getString(FimDeAnoConstants.CHAVE_PRESENCA);
            if (presenca != null && presenca.equals(FimDeAnoConstants.CONFIRMACAO_SIM)) {
                this.mViewHolder.checkParticipar.setChecked(true);
            } else {
                this.mViewHolder.checkParticipar.setChecked(false);
            }
        }
    }

    public static class ViewHolder {
        CheckBox checkParticipar;
    }
}