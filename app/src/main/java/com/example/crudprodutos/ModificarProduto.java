package com.example.crudprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.crudprodutos.dao.ProdutoDAO;

public class ModificarProduto extends AppCompatActivity {

    EditText editText;
    EditText etdesc;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_produto);

        Intent it = getIntent();
        String id = it.getStringExtra("id");

        ProdutoDAO pdao = new ProdutoDAO(this);
        pdao.obterProdutoById(Integer.parseInt(id));

        editText = findViewById(R.id.nome);
        etdesc = findViewById(R.id.descricao);

        editText.setText(pdao.getNome());
        etdesc.setText(pdao.getDescricao());

        Button btnAtualizar = findViewById(R.id.atualizar);
    }
}