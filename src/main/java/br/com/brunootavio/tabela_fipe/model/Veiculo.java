package br.com.brunootavio.tabela_fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(@JsonAlias("Valor") String valor,
                      @JsonAlias("Marca") String marca,
                      @JsonAlias("Modelo") String modelo,
                      @JsonAlias("AnoModelo") String anoModelo,
                      @JsonAlias("Combustivel") String combustivel,
                      @JsonAlias("CodigoFipe") String codigoFipe
                      ) {

    @Override
    public String toString() {
        return "Veiculo: " +
                "Valor: " + valor + '\'' +
                "Marca: " + marca + '\'' +
                "Modelo: " + modelo + '\'' +
                "Ano do Modelo: " + anoModelo + '\'' +
                "Combustivel: " + combustivel + '\'' +
                "Codigo Fipe: " + codigoFipe + '\'';
    }

    @Override
    public String valor() {
        return valor;
    }

    @Override
    public String marca() {
        return marca;
    }

    @Override
    public String modelo() {
        return modelo;
    }

    @Override
    public String anoModelo() {
        return anoModelo;
    }

    @Override
    public String combustivel() {
        return combustivel;
    }

    @Override
    public String codigoFipe() {
        return codigoFipe;
    }
}
