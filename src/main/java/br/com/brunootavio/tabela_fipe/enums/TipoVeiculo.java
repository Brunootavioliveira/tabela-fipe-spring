package br.com.brunootavio.tabela_fipe.enums;

public enum TipoVeiculo {
    CARROS("carros"),
    MOTOS("motos"),
    CAMINHOES("caminhoes");

    private final String endpoint;

    TipoVeiculo(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
