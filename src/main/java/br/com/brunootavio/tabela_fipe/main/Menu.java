package br.com.brunootavio.tabela_fipe.main;

import br.com.brunootavio.tabela_fipe.model.Dados;
import br.com.brunootavio.tabela_fipe.model.Modelos;
import br.com.brunootavio.tabela_fipe.model.Veiculo;
import br.com.brunootavio.tabela_fipe.service.ConsumoApi;
import br.com.brunootavio.tabela_fipe.service.ConverterDados;
import org.apache.logging.log4j.message.StringFormattedMessage;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverterDados converterDados = new ConverterDados();

    private final String API_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibirMenu() {
        boolean menu = true;
        while (menu) {
            System.out.println("""
                        Qual tipo de veiculo quer consultar ?
                        - Carros
                        - Motos
                        - Caminhões
                        """);
            var tipoVeiculo = scanner.nextLine();
            tipoVeiculo = tipoVeiculo.toLowerCase();
            String endereco;

            if (tipoVeiculo.contains("car")) {
                endereco = API_BASE + "carros/marcas/";
            } else if (tipoVeiculo.contains("mot")) {
                endereco = API_BASE + "motos/marcas/";
            } else if (tipoVeiculo.contains("cam")){
                endereco = API_BASE + "caminhoes/marcas/";
            } else {
                System.out.println("Opção incorreta!");
                continue;
            }

            var json = consumoApi.obterDados(endereco);

            List<Dados> dados = converterDados.obterList(json, Dados.class);
            String dados1 = dados.stream()
                    .map(Dados::toString)
                    .collect(Collectors.joining(""));
            System.out.println("Marcas: ");
            System.out.println(dados1);

            System.out.println("Digite a marca pelo ID: ");
            var idMarca = scanner.nextLine();
            endereco = endereco + idMarca + "/modelos/";

            var json1 = consumoApi.obterDados(endereco);
            Modelos dados2 = converterDados.obterDados(json1, Modelos.class);
            String dados3 = dados2.modelos().stream()
                    .map(Dados::toString)
                    .collect(Collectors.joining(""));
            System.out.println("Modelos: ");
            System.out.println(dados3);

            System.out.println("Digite o modelo pelo ID, para obter todas as informações dos veiculos: ");
            var idModelo = scanner.nextLine();
            endereco = endereco + idModelo + "/anos/";
            var jsonAnos = consumoApi.obterDados(endereco);
            List<Dados> anos = converterDados.obterList(jsonAnos, Dados.class);

            for (Dados ano : anos) {

                String enderecoAnos = endereco + ano.id();
                var jsonDetalhes = consumoApi.obterDados(enderecoAnos);

                Veiculo veiculo = converterDados.obterDados(jsonDetalhes, Veiculo.class);

                System.out.println("================================");
                System.out.println("Marca: " + veiculo.marca());
                System.out.println("Ano: " + veiculo.anoModelo());
                System.out.println("Valor: " + veiculo.valor());
                System.out.println("Combustível: " + veiculo.combustivel());
                System.out.println("Código FIPE: " + veiculo.codigoFipe());


            }
        menu = false;
        }
    }
}
