package br.com.brunootavio.tabela_fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados (@JsonAlias("codigo") String id,
                     @JsonAlias("nome") String nome) {

    @Override
    public String toString() {
        return "ID: " + id + " - Nome: " + nome + "\n";
    }

    @Override
    public String nome() {
        return nome;
    }

    @Override
    public String id() {
        return id;
    }
}
