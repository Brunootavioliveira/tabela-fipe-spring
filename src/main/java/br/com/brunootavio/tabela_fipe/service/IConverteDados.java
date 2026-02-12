package br.com.brunootavio.tabela_fipe.service;

import java.util.List;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterList(String json, Class<T> classe);

}
