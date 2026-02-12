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
        return "Veiculo: " + "\n" +
                "Valor: " + valor + '\n' +
                "Marca: " + marca + '\n' +
                "Modelo: " + modelo + '\n' +
                "Ano do Modelo: " + anoModelo + '\n' +
                "Combustivel: " + combustivel + '\n' +
                "Codigo Fipe: " + codigoFipe + '\n';
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
