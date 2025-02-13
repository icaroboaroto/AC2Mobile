package com.example.ac2_mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ac2_mobile.R;
import com.example.ac2_mobile.models.Alunos;

import java.util.List;

public class AlunosAdapter extends RecyclerView.Adapter<AlunosAdapter.AlunoHolder> {

    public AlunosAdapter(List<Alunos> alunos) {
        this.alunos = alunos
    }

    @NonNull
    @Override
    public AlunoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_alunos, parent, false);
        return new AlunoHolder(itemView);
    }
    //return new AlunoHolder(LayoutInflater.from(parent.getContext())
    //        .inflate(android.R.layout.simple_list_item_2, parent, false));    }

    @Override
    public void onBindViewHolder(AlunoHolder holder, int position) {
        Alunos aluno = alunos.get(position);
        holder.nomeText.setText(aluno.getNome());
        holder.cepText.setText(aluno.getCep());
        holder.logradouroText.setText(aluno.getLogradouro());
        holder.complementoText.setText(aluno.getComplemento());
        holder.bairroText.setText(aluno.getBairro());
        holder.cidadeText.setText(aluno.getCidade());
        holder.ufText.setText(aluno.getUf());
        holder.raText.setText(String.valueOf(aluno.getRa()));
    }

    @Override
    public int getItemCount() {
        return alunos != null ? alunos.size() : 0;
    }

    public static class AlunoHolder extends RecyclerView.ViewHolder {
        TextView raText, nomeText, cepText, logradouroText, complementoText, bairroText, cidadeText, ufText;
        public AlunoHolder(View itemView) {
            super(itemView);
            raText = itemView.findViewById(R.id.raText);
            nomeText = itemView.findViewById(R.id.nomeText);
            cepText = itemView.findViewById(R.id.cepText);
            logradouroText = itemView.findViewById(R.id.logradouroText);
            complementoText = itemView.findViewById(R.id.complementoText);
            bairroText = itemView.findViewById(R.id.bairroText);
            cidadeText = itemView.findViewById(R.id.cidadeText);
            ufText = itemView.findViewById(R.id.ufText);
        }
    }
}
