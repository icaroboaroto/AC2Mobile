package com.example.ac2_mobile;

import androidx.appcompat.app.AppCompatActivity;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ac2_mobile.api.ApiService;
import com.example.ac2_mobile.api.MockApi;
import com.example.ac2_mobile.api.ViaCepApi;
import com.example.ac2_mobile.api.ViaCepService;
import com.example.ac2_mobile.models.Alunos;
import com.example.ac2_mobile.models.EnderecoCep;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Activity  extends AppCompatActivity {

    private EditText editNome, editRa, editCep, editLogradouro, editComplemento, editBairro, editCidade, editUf;
    private FloatingActionButton buttonBuscarCep;
    private Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        editNome = (EditText)findViewById(R.id.editNome);
        editRa = (EditText)findViewById(R.id.editRa);
        editCep = (EditText)findViewById(R.id.editCep);
        editLogradouro = (EditText)findViewById(R.id.editLogradouro);
        editComplemento = (EditText)findViewById(R.id.editComplemento);
        editBairro = (EditText)findViewById(R.id.editBairro);
        editCidade = (EditText)findViewById(R.id.editCidade);
        editUf = (EditText)findViewById(R.id.editUf);
        buttonSalvar = (Button)findViewById(R.id.buttonSalvar);

        buttonSalvar.setOnClickListener(v ->{
            String nome = editNome.getText().toString();
            int ra = Integer.valueOf(editRa.getText().toString());
            String cep = editCep.getText().toString();
            String logradouro = editLogradouro.getText().toString();
            String complemento = editComplemento.getText().toString();
            String bairro = editNome.getText().toString();
            String cidade = editCidade.getText().toString();
            String uf = editUf.getText().toString();

            Alunos alunos = new Alunos(0,ra,nome,cep,logradouro,complemento,bairro,cidade,uf);

            enviarAluno(alunos);
        });

        buttonBuscarCep = (FloatingActionButton)findViewById(R.id.buttonBuscarCep);
        buttonBuscarCep.setOnClickListener(v -> {
            String cep = editCep.getText().toString().trim();
            Toast.makeText(Activity.this, cep, Toast.LENGTH_SHORT).show();
            if (!cep.isEmpty()){
                buscarCep(cep);
            } else {
                Toast.makeText(Activity.this, "Por favor, insira um CEP válido.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void buscarCep(String cep) {
        ViaCepApi viaCepApi = ViaCepService.create();
        viaCepApi.getCepInfo(cep).enqueue(new Callback<ViaCepService>() {
            @Override
            public void onResponse(Call<EnderecoCep> call, Response<EnderecoCep> response) {
                if (response.isSuccessful() && response.body() != null) {
                    EnderecoCep cepInfo = response.body();
                    editLogradouro.setText(cepInfo.getLogradouro());
                    editComplemento.setText(cepInfo.getComplemento());
                    editBairro.setText(cepInfo.getBairro());
                    editCidade.setText(cepInfo.getCidade());
                    editUf.setText(cepInfo.getUf());
                } else {
                    Log.e(TAG, "Erro na resposta: " + response.message());
                    Toast.makeText(Activity.this, "CEP não encontrado.", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<EnderecoCep> call, Throwable t) {
                Log.e(TAG, "Erro ao buscar CEP: " + t.getMessage(), t);
                Toast.makeText(Activity.this, "Erro ao buscar CEP.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void enviarAluno(Alunos aluno){
        ApiService apiService = MockApi.create();
        apiService.createAluno(aluno).enqueue(new Callback<Alunos>() {
            @Override
            public void onResponse(Call<Alunos> call, Response<Alunos> response) {
                if (response.isSuccessful()){
                    Toast.makeText(Activity.this, "Aluno Salvo!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("aluno_adicionado", true);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(Activity.this, "Houve um erro ao salvar aluno!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Alunos> call, Throwable t) {
                Toast.makeText(Activity.this, "Não foi possível acessar o servidor", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
