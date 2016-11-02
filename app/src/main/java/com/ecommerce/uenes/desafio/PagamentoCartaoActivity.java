package com.ecommerce.uenes.desafio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class PagamentoCartaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento_cartao);

        EditText numeroCartao = (EditText)findViewById(R.id.numero_cartao);
        numeroCartao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if ((charSequence != null) && (charSequence.length() > 0) ) {
                    if (charSequence.charAt(0) == '4')
                        ((TextView)findViewById(R.id.bandeira_cartao)).setText("VISA");
                    else if (charSequence.charAt(0) == '5')
                        ((TextView)findViewById(R.id.bandeira_cartao)).setText("MASTERCARD");
                    else
                        ((TextView)findViewById(R.id.bandeira_cartao)).setText("Bandeira não suportada");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
