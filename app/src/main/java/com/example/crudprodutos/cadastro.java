package com.example.crudprodutos;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.crudprodutos.dao.ProdutoDAO;

public class cadastro extends AppCompatActivity {

    EditText name;
    EditText desc;
    EditText val;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        bt = findViewById(R.id.botaoEnviar);
        name = findViewById(R.id.nome);
        desc = findViewById(R.id.descricao);
        val = findViewById(R.id.valor);

        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){

                //aqui os dados são inseridos
                ProdutoDAO p = new ProdutoDAO( null, name.getText().toString(), desc.getText().toString(), val.getText().toString(), "", cadastro.this);

                if(p.inserir()){

                    //após inserir os dados iniciais é preciso retornar à tela inicial
                    Intent main = new Intent(cadastro.this, MainActivity.class);

                    startActivity(main);
                }
            }
        });
    }
}