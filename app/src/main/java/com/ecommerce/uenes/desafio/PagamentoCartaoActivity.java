package com.ecommerce.uenes.desafio;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ecommerce.uenes.desafio.model.TransacaoDb;
import com.ecommerce.uenes.desafio.model.contract.TransacaoContrato.Transacao;

import com.ecommerce.uenes.desafio.model.dbhelper.TransacaoDbHelper;

public class PagamentoCartaoActivity extends AppCompatActivity {
    private TransacaoDb transacaoDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento_cartao);

        EditText numeroCartao = (EditText)findViewById(R.id.numero_cartao);
        numeroCartao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence numeroCartao, int i, int i1, int i2) {
                ((TextView)findViewById(R.id.bandeira_cartao)).setText(getBandeira(numeroCartao));
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        final Button button = (Button) findViewById(R.id.finalizar_compra);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (isAllFieldsFilled()) {
                    transacaoDb = new TransacaoDb(getApplicationContext());
                    transacaoDb.salvarTransacao(getDataFromScreen());
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Todos os campos devem ser preenchidos";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }



    public String getBandeira(CharSequence numeroCartao) {
        String bandeira = "";
        if ((numeroCartao != null) && (numeroCartao.length() > 0) ) {
            if (numeroCartao.charAt(0) == '4')
                bandeira = "VISA";
            else if (numeroCartao.charAt(0) == '5')
                bandeira = "MASTERCARD";
            else
                bandeira = "Bandeira não suportada";
        }
        return bandeira;
    }

    public Transacao getDataFromScreen() {
        Transacao t = new Transacao();

        EditText nome = (EditText)findViewById(R.id.nome_cliente);
        EditText numero = (EditText)findViewById(R.id.numero_cartao);
        EditText vencimento = (EditText)findViewById(R.id.vencimento_cartao);
        EditText cvv = (EditText)findViewById(R.id.CVV_cartao);
        EditText valor = (EditText)findViewById(R.id.valor_compra);

        if (nome != null && vencimento != null && numero != null && cvv != null && valor != null) {
            t.setNomeCliente(nome.getText().toString());
            Integer n = Integer.parseInt(numero.getText().toString());
            t.setNumeroCartao(n);
            t.setVencimentoCartao(vencimento.getText().toString());
            Integer c = Integer.parseInt(cvv.getText().toString());
            t.setCvv(c);
            Float v = Float.parseFloat(valor.getText().toString());
            t.setValor(v);
        } else {
            return null;
        }

        return t;
    }

    public boolean isAllFieldsFilled() {
        EditText nome = (EditText)findViewById(R.id.nome_cliente);
        EditText numero = (EditText)findViewById(R.id.numero_cartao);
        EditText vencimento = (EditText)findViewById(R.id.vencimento_cartao);
        EditText cvv = (EditText)findViewById(R.id.CVV_cartao);
        EditText valor = (EditText)findViewById(R.id.valor_compra);

        if (!nome.getText().toString().isEmpty() &&
                !numero.getText().toString().isEmpty() &&
                !vencimento.getText().toString().isEmpty() &&
                !cvv.getText().toString().isEmpty() &&
                !valor.getText().toString().isEmpty()){
            return true;
        }
        return false;
    }
}
