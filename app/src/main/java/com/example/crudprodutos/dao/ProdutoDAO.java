package com.example.crudprodutos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.crudprodutos.helper.DbHelper;

//Essa classe que é responsável por controlar as questões de bd dos produtos
public class ProdutoDAO {

    //atributos relacionados ao modelo
    private Integer id;
    private String nome;
    private String descricao;
    private String foto;
    private String valor;

    //atributo relacionado ao banco de dados
    private DbHelper dbHelper;
    private SQLiteDatabase database;

    //pode ser preciso criar o contexto no DbHelper para que a gente defina o que seerá recebido ou não
    public ProdutoDAO(Integer id, String nome, String descricao, String foto, String valor, Context ctx){

        //versão para somente ler o banco de dados
        //database = dbHelper.getReadableDatabase();
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.valor = valor;

        //variável que instancia, dá acesso ao banco de dados
        dbHelper = new DbHelper(ctx);

        //versão para escrever no database
        database = dbHelper.getWritableDatabase();
    }

    public ProdutoDAO(Integer id, String nome, String descricao, String foto, String valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.foto = foto;
        this.valor = valor;
    }

    public ProdutoDAO(Context ctx){
        dbHelper = new DbHelper(ctx);

        database = dbHelper.getWritableDatabase();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    //em classes do tipo dao usa-se métodos para fazer o CRUD
    public boolean insert(){
        return false;
    }

    public boolean update(){
        return false;
    }

    public boolean delete(){
        return false;
    }

    public Cursor listaProdutos(){
        //como sempre haverá um campo chamado _id no SimpleCursorAdapter precisamos que no código sql algum dado seja lido como _id
        String sql = "SELECT id as _id, nome FROM produto;";

        //conexões e comandos precisam da variável database, o rawQuery faz consultas no sql, não passa outros argumentos (null) porque a instrução "SELECT..." não oferece nenhuma restrição
        Cursor c = database.rawQuery(sql, null);

        //precisa verificar se houve retorno na consulta
        if(c != null){

            //o cursor se movimenta para o primeiro elemento da lista retornada pelo rawQuery
            c.moveToFirst();
        }

        return c;
    }

    public boolean inserir(){
        ContentValues cv = new ContentValues();
        cv.put("nome", this.nome);
        cv.put("descricao", this.descricao);
        cv.put("foto", this.foto);
        cv.put("valor", this.valor);

        long ret = database.insert("produto",  null, cv);

        if(ret > 0 ){
            return true;
        }
        else{
            return false;
        }
    }

    public void obterProdutoById(int i){}
}
