package com.ecommerce.uenes.desafio.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.ecommerce.uenes.desafio.model.contract.TransacaoContrato;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Communication extends AsyncTask<String, Void, String>{
    private static final String SERVER_URL = "http://private-62583-desafioecommerce.apiary-mock.com/questions";
    private static final String NOME_CLIENTE = "nome_cliente";
    private static final String NUMERO_CARTAO = "numero_cartao";
    private static final String VENCIMENTO_CARTAO = "vencimento_cartao";
    private static final String BANDEIRA_CARTAO = "bandeira_cartao";
    private static final String CVV = "cvv";
    private static final String VALOR = "valor";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private String error = null;

    public String post(String url, String json) throws IOException {
        return null;
    }

    @Override
    protected String doInBackground(String... objects) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, objects[1]);
        Request request = new Request.Builder()
                .url(objects[0])
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            error = null;
        } catch (IOException e) {
            error = e.getMessage();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
    }

    public Boolean enviarTransacao(TransacaoContrato.Transacao transacao) throws JSONException {
        String json = "";
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate(NOME_CLIENTE, transacao.getNomeCliente());
        jsonObject.accumulate(NUMERO_CARTAO, transacao.getNumeroCartao());
        jsonObject.accumulate(VENCIMENTO_CARTAO, transacao.getVencimentoCartao());
        jsonObject.accumulate(BANDEIRA_CARTAO, transacao.getBandeiraCartao());
        jsonObject.accumulate(CVV, transacao.getCvv().toString());
        jsonObject.accumulate(VALOR, transacao.getValor().toString());

        json = jsonObject.toString();
        String[] param = new String[2];
        param[0] = SERVER_URL;
        param[1] = json;
        AsyncTask r = this.execute(param);
        return error == null;
    }
}
