package br.com.brunootavio.tabela_fipe.main;

import br.com.brunootavio.tabela_fipe.exception.ConverterJsonException;
import br.com.brunootavio.tabela_fipe.exception.DadosNaoEncontrados;
import br.com.brunootavio.tabela_fipe.exception.FipeApiException;
import br.com.brunootavio.tabela_fipe.model.Dados;
import br.com.brunootavio.tabela_fipe.model.Modelos;
import br.com.brunootavio.tabela_fipe.enums.TipoVeiculo;
import br.com.brunootavio.tabela_fipe.model.Veiculo;
import br.com.brunootavio.tabela_fipe.service.FipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class Menu {
    private static final Logger logger =
            LoggerFactory.getLogger(Menu.class);

    private Scanner scanner = new Scanner(System.in);
    private final String API_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private FipeService fipeService = new FipeService();

    private <T> T executarComTratamento(Supplier<T> acao) {
        try {
            return acao.get();
        } catch (FipeApiException e) {
            System.out.println("Erro ao conectar na API.");
        } catch (ConverterJsonException e) {
            System.out.println("Erro ao processar JSON.");
        } catch (DadosNaoEncontrados e){
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Erro inesperado.");
        }

        throw new RuntimeException("Execução interrompida.");
    }

    public void exibirMenu() {

        System.out.println("Digite o tipo do veiculo: ");
        var tipoVeiculo = scanner.nextLine().toUpperCase();

        TipoVeiculo tipo;

        try {
            tipo = TipoVeiculo.valueOf(tipoVeiculo);
            logger.info("Usuário escolheu o tipo {}", tipo);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo inválido! ");
            logger.warn("Tipo invalido escolhido pelo usuário {}", tipoVeiculo);
            return;
        }

        String endereco = API_BASE + tipo.getEndpoint() + "/marcas/";
        String json;

        json = executarComTratamento(() -> fipeService.buscarJson(endereco));

        List<Dados> dados = executarComTratamento(() ->
                fipeService.validarLista(fipeService.buscarLista(json, Dados.class), "Nenhuma marca retornada pela API.")
        );

        System.out.println("Marcas: ");
        dados.forEach(System.out::println);

        Dados marcaSelecionada;

        do {
            System.out.println("Digite a marca pelo ID: ");
            var idMarca = scanner.nextLine();

             marcaSelecionada = dados.stream()
                    .filter(m -> m.id().equals(idMarca))
                    .findFirst()
                    .orElse(null);

             if (marcaSelecionada == null) {
                 System.out.println("Marca não encontrada. Tente novamente!");
             }
        } while (marcaSelecionada == null);


        String enderecoModelo = endereco + marcaSelecionada.id() + "/modelos/";

        var json1 = executarComTratamento(() -> fipeService.buscarJson(enderecoModelo));
        Modelos modelosResponse = executarComTratamento(() ->
                fipeService.validarObjeto(fipeService.buscarObjeto(json1, Modelos.class), "Nenhum modelo retornado pela API"));

        System.out.println("Modelos: ");
        modelosResponse.modelos()
                        .forEach(System.out::println);

        Dados modeloSelecionado;
        do {
            System.out.println("Digite o modelo pelo ID, para obter todas as informações dos veiculos: ");
            var idModelo = scanner.nextLine();

            modeloSelecionado = modelosResponse.modelos().stream()
                    .filter(m -> m.id().equals(idModelo))
                    .findFirst()
                    .orElse(null);
            
            if (modeloSelecionado == null){
                System.out.println("Modelo não encontrado. Tente novamente!");
            }
        } while (modeloSelecionado == null);


        String enderecoAno = enderecoModelo + modeloSelecionado.id() + "/anos/";
        var jsonAnos = executarComTratamento(() -> fipeService.buscarJson(enderecoAno));
        List<Dados> anos = executarComTratamento(() ->
                fipeService.validarLista(fipeService.buscarLista(jsonAnos, Dados.class),"Ano não encontrado"));

        for (Dados ano : anos) {

            String enderecoAnos = enderecoAno + ano.id();
            var jsonDetalhes = executarComTratamento(() -> fipeService.buscarJson(enderecoAnos));

            Veiculo veiculo = executarComTratamento(() -> fipeService.buscarObjeto(jsonDetalhes, Veiculo.class));

            System.out.println("================================");
            System.out.println(veiculo);


        }
    }
}
