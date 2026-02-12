package br.com.brunootavio.tabela_fipe.service;

import br.com.brunootavio.tabela_fipe.exception.DadosNaoEncontrados;

import java.util.List;

public class FipeService {
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverterDados converterDados = new ConverterDados();

    public String buscarJson(String endereco) {
        return consumoApi.obterDados(endereco);
    }

    public <T> T buscarObjeto(String json, Class<T> classe) {
        return converterDados.obterDados(json, classe);
    }

    public  <T> List<T> buscarLista(String json, Class<T> classe) {
        return converterDados.obterList(json, classe);
    }

    public <T> T validarObjeto(T objeto, String mensagem) {
        if (objeto == null) {
            throw new DadosNaoEncontrados(mensagem);
        }
        return objeto;
    }

    public <T> List<T> validarLista(List<T> lista, String mensagem) {
        if (lista == null || lista.isEmpty()) {
            throw new DadosNaoEncontrados(mensagem);
        }
        return lista;
    }

}
