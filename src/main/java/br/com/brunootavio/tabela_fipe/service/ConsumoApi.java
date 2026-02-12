package br.com.brunootavio.tabela_fipe.service;

import br.com.brunootavio.tabela_fipe.exception.FipeApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConsumoApi {

    private static final Logger logger =
            LoggerFactory.getLogger(ConsumoApi.class);

    public String obterDados(String endereco) {

        HttpClient client =HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = null;

        try {
            response = client.send(request,HttpResponse.BodyHandlers.ofString());
        } catch (IOException |  InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Erro no consumo da API URL={} | METHOD={}",
                    request.uri(), //mostra a url do create, para verificacao de erro
                    request.method(), //get, post...
                    e);
            throw new FipeApiException(
                    "Erro ao consumir API da tabela Fipe", e
            );
        }

        String json = response.body();
        return json;
    }
}
