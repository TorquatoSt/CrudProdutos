package com.example.crudprodutos.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{


    public DbHelper(Context ctx) {
        super(ctx, "meuDB.db", null, 1);
    }

    //meuBD arquivo de banco de dados, 1 é a primeira versão do bd e a cada modificação deve-se atualizar a versão ex.: 1.01, toda activity é um contexto
    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "meuDB.db", null, 1);
    }

    //quando instala o app no celular pela primeira vez o android executa esse método, só é executado quando instala o app
    @Override
    public void onCreate(SQLiteDatabase db) {

        //criando as tabelas do bd, para cada tabela nova deve-se gerar uma nova string e executar de novo, ex.: String sql1 = ("CREATE..."); db.execSQL(sql1)
        String sql = "CREATE TABLE produto (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, descricao TEXT, valor TEXT, foto TEXT)";

        //executando o BD, o db é o nome da variável que está entre os parênteses
        db.execSQL(sql);
    }

    //para modificar o banco de dados posteriormente quando algo for modificado para uma nova versão, o android executa esse método quando percebe que há uma atualização
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
