# 🚗 FIPE Explorer - Consulta de Veículos via API Pública

O FIPE Explorer é uma aplicação Java que consome a API pública da Tabela FIPE para permitir a consulta de marcas, modelos, anos e detalhes completos de veículos (carros, motos e caminhões).

O projeto foi desenvolvido com foco em boas práticas de arquitetura, separação de responsabilidades, tratamento de exceções customizadas e uso de generics, simulando a estrutura de uma aplicação backend organizada.

---

## Funcionalidades

- ✅ Consulta de veículos por tipo (CARRO, MOTO ou CAMINHÃO)
- ✅ Listagem dinâmica de marcas disponíveis
- ✅ Seleção interativa de modelos por ID
- ✅ Consulta de todos os anos disponíveis para um modelo
- ✅ Exibição detalhada das informações do veículo:
`Marca -
Ano -
Valor -
Combustível -
Código FIPE`
- ✅ Tratamento robusto de exceções
- ✅ Validação de listas e objetos retornados pela API
- ✅ Uso de logs com SLF4J

---

## Estrutura do Projeto

📦 tabela_fipe <br>
┣ 📂 enums <br>
┃ ┗ 📜 TipoVeiculo.java <br>
┣ 📂 exception <br>
┃ ┣ 📜 ConverterJsonException.java <br>
┃ ┣ 📜 DadosNaoEncontrados.java <br>
┃ ┗ 📜 FipeApiException.java <br>
┣ 📂 model <br>
┃ ┣ 📜 Dados.java <br>
┃ ┣ 📜 Modelos.java <br>
┃ ┗ 📜 Veiculo.java <br>
┣ 📂 service <br>
┃ ┣ 📜 ConsumoApi.java <br>
┃ ┣ 📜 ConverterDados.java <br>
┃ ┣ 📜 IConverteDados.java <br>
┃ ┗ 📜 FipeService.java <br>
┣ 📂 main <br>
┃ ┗ 📜 Menu.java <br>
┣ 📜 TabelaFipeApplication.java



---

## Conceitos Aplicados

- Programação Orientada a Objetos (POO)
- Organização em camadas (main, service, model, exception)
- Separação de responsabilidades
- Uso de `record`
- Generics `(<T>)`
- Stream API
- Tratamento de exceções customizadas
- Logging com SLF4J
- Consumo de API REST
- Conversão de JSON para objetos Java

---

## Fluxo da Aplicação

1. Usuário escolhe o tipo de veículo
2. Aplicação consulta a API FIPE
3. Exibe marcas disponíveis
4. Usuário seleciona a marca
5. Exibe modelos disponíveis
6. Usuário seleciona o modelo
7. Sistema busca todos os anos disponíveis
8. Exibe detalhes completos para cada ano
---

## 💡 Exemplo de Uso

```
Digite o tipo do veiculo:
CARRO

Marcas:
1 - Acura
2 - Agrale
...

Digite a marca pelo ID:
21

Modelos:
1 - Palio
2 - Uno
...

================================
Marca: Fiat
Ano: 2014
Valor: R$ 35.000,00
Combustível: Gasolina
Código FIPE: 001234-5
```

---

## Tecnologias

- Java 21
- Maven
- SLF4J (Logging)
- API pública da Tabela FIPE
- IntelliJ IDEA

---

## API Utilizada

API pública da Tabela FIPE:

https://parallelum.com.br/fipe/api/v1/

---

## 👨‍💻 Autor

Bruno Otavio
Desenvolvedor Java em evolução constante!



