package com.ecommerce.uenes.desafio.model.dbhelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ecommerce.uenes.desafio.model.contract.TransacaoContrato.Transacao;

public class TransacaoDbHelper  extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Transacao.TABLE_NAME + " (" +
                    Transacao._ID + " INTEGER PRIMARY KEY," +
                    Transacao.COLUMN_NOME_CLIENTE + TEXT_TYPE + COMMA_SEP +
                    Transacao.COLUMN_NUMERO_CARTAO + INT_TYPE + COMMA_SEP +
                    Transacao.COLUMN_VENCIMENTO + TEXT_TYPE + COMMA_SEP +
                    Transacao.COLUMN_BANDEIRA + TEXT_TYPE + COMMA_SEP +
                    Transacao.COLUMN_CVV + INT_TYPE + COMMA_SEP +
                    Transacao.COLUMN_VALOR + REAL_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Transacao.TABLE_NAME;

    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Transacao.db";

    public TransacaoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
