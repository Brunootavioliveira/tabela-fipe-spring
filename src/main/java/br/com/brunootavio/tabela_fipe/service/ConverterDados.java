package br.com.brunootavio.tabela_fipe.service;

import br.com.brunootavio.tabela_fipe.exception.ConverterJsonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.sun.net.httpserver.Request;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.List;


public class ConverterDados implements IConverteDados{

    private ObjectMapper mapper = new ObjectMapper();

    private static final Logger logger =
            LoggerFactory.getLogger(ConverterDados.class);

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            logger.error("Falha ao converter Json {}", classe.getSimpleName(), e);
            throw new ConverterJsonException(
                    "Erro ao converter Json", e
            );
        }
    }

    @Override
    public <T> List<T> obterList(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory() // informar que queremos uma lista da classe que estamos fornecendo
                .constructCollectionType(List.class,classe); //devolver uma lista que devolve dados de código e nome
        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            logger.error("Falha ao converter lista de Json", classe.getSimpleName(), e);
            throw new ConverterJsonException(
                    "Erro ao converter LISTA Json", e
            );
        }
    }
}
