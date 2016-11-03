package com.ecommerce.uenes.desafio.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ecommerce.uenes.desafio.model.contract.TransacaoContrato;
import com.ecommerce.uenes.desafio.model.contract.TransacaoContrato.Transacao;
import com.ecommerce.uenes.desafio.model.dbhelper.TransacaoDbHelper;

public class TransacaoDb {
    private TransacaoDbHelper transacaoDbHelper;

    public TransacaoDb (Context context) {
        transacaoDbHelper = new TransacaoDbHelper(context);
    }

    public void salvarTransacao(Transacao t) {
        SQLiteDatabase db = transacaoDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Transacao.COLUMN_NOME_CLIENTE, t.getNomeCliente());
        values.put(Transacao.COLUMN_NUMERO_CARTAO, t.getNumeroCartao());
        values.put(Transacao.COLUMN_CVV, t.getCvv());
        values.put(Transacao.COLUMN_VENCIMENTO, t.getVencimentoCartao());
        values.put(Transacao.COLUMN_VALOR, t.getValor());

        long newRowId = db.insert(Transacao.TABLE_NAME, null, values);
    }

    public Integer countTransacoesDb() {
        SQLiteDatabase db = transacaoDbHelper.getReadableDatabase();

        String[] projection = {
                Transacao._ID,
                Transacao.COLUMN_NOME_CLIENTE,
                Transacao.COLUMN_NUMERO_CARTAO,
                Transacao.COLUMN_CVV,
                Transacao.COLUMN_VENCIMENTO,
                Transacao.COLUMN_VALOR
        };

        String selection = Transacao.COLUMN_NOME_CLIENTE + " = ?";
        String[] selectionArgs = { "uenes" };

        String sortOrder =
                Transacao.COLUMN_NOME_CLIENTE + " DESC";

        Cursor c = db.query(
                Transacao.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return c.getCount();
    }
}
