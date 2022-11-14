package com.example.crudprodutos;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.crudprodutos.dao.ProdutoDAO;

public class MainActivity extends AppCompatActivity {

    //estando aqui fora as variáveis são iniciadas para toda a classe MainActivity
    ListView lvProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProdutos = findViewById(R.id.listviewProdutos);

        //define que quando a lista estiver vazia o text view de nada cadastrado aparece, pode mostrar outras views, componentes e etc.
        lvProdutos.setEmptyView(findViewById(R.id.tvEmpty));

        //o ctx é a activity que será utilizada, nesse caso a this se refere a essa activity atual Main, é possível fazer Cursor c = ProdutoDAO(this).listaProdutos();
        //nesse formato apenas criou uma variável pDao para receber as informações da classe ProdutoDAO
        ProdutoDAO pDao = new ProdutoDAO(this);
        Cursor c = pDao.listaProdutos();

        //o cursor é um tipo de dado diferente, porém seu uso facilita mecher com o list view, para desenhar a tela de forma simples tem essa classe SimpleCursorAdapter, ele adapta o cursor para que o List view consiga entender e desenhar a lista,
        //o R.layout se refere ao layout que será utilizado para exibir as coisinhas, os arrays de String e int são os textView do layout lista_produtos que vão mostrar as informações
        //obrigatóriamente sempre haverá um campo chamado _id
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                R.layout.lista_produtos,
                c,
                new String[]{"_id", "nome"}, //o cursor pega o valor dentro do banco de dados na coluna id vai ser mostrado no componente id da activity lista_produtos
                new int[]{R.id.id, R.id.nome},
                0);

        //utiliza-se o adaptador no listview de produtos para mostrar os dados
        lvProdutos.setAdapter(adapter);

        //método que verifica qual item foi clicado

        lvProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MainActivity.this, ModificarProduto.class);

                //o putExtra permite que se vá para outra tela e leve algo, no caso o id do produto clicado
                it.putExtra("id", id+"");

                startActivity(it);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //é possível trabalhar de forma dinâmica sobre elementos, no caso aqui o elemento menu
        //método inflate substitui/adiciona um novo menu pelo que for chamado, no caso o menu_tela_principal
        getMenuInflater().inflate(R.menu.menu_tela, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //nesse switch verifica se o item foi clicado
        switch(item.getItemId()){

            //os cases verificam quais elementos do menu foram clicados, nesse caso vai verificar se o elemento menu_add foi clicado
            case R.id.menu_add:
                Intent add_menu = new Intent(this, cadastro.class);
                startActivity(add_menu);
        }

        return super.onOptionsItemSelected(item);
    }
}