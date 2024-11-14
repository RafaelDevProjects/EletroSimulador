# Simulador de Gasto de Energia

Este projeto é uma aplicação de desktop Java que simula o consumo de energia e o custo total de eletrodomésticos com base em sua potência, horas de uso diário e a tarifa de energia configurada. O objetivo do sistema é permitir aos usuários calcular facilmente os custos de energia de aparelhos em sua residência ou estabelecimento comercial.

## Funcionalidades

- **Configuração da Casa**: O usuário pode configurar a tarifa de energia (por kWh) e o tipo de residência (residencial ou comercial).
- **Adição de Aparelhos**: O usuário pode adicionar aparelhos eletrodoméstricos, informando o nome, a potência (em watts) e as horas de uso diárias.
- **Cálculo de Consumo e Custo**: Com base nas informações inseridas, o sistema calcula o consumo total de energia e o custo total.

## Tecnologias Utilizadas

- **Java**: A linguagem de programação principal utilizada no desenvolvimento da aplicação.
- **Swing**: Biblioteca para construção da interface gráfica.
- **GridBagLayout, BoxLayout e BorderLayout**: Layouts utilizados para posicionar e organizar os componentes da interface.
- **JComboBox, JTextField, JButton e JLabel**: Componentes para interação com o usuário.

## Como Rodar o Projeto

1. Clone o repositório para o seu ambiente local:
   ```bash
   git clone https://github.com/seu-usuario/simulador-gasto-energia.git
2. Navegue até o diretório do projeto:
   ```bash
    cd simulador-gasto-energia
3. Compile o projeto:
   ```bash
    javac -d bin src/energia/*.java
4. Execute o aplicativo:
   ```bash
    java -cp bin energia.SimuladorGastoEnergiaGUI
   
## Como Funciona
1. Configuração da Casa
O usuário começa configurando a tarifa de energia por kWh e o tipo de residência (residencial ou comercial).

2. Adicionando Aparelhos
Após configurar a casa, o usuário pode adicionar aparelhos, informando o nome, a potência e as horas de uso diário de cada um. O sistema calculará automaticamente o consumo de cada aparelho.

3. Cálculo de Consumo e Custo
Quando o usuário clicar em "Calcular Consumo e Custo", o sistema calculará o consumo total de energia em kWh e o custo total, com base na tarifa configurada.

4. Tela de Ajuda
O aplicativo também oferece uma tela de ajuda acessível através de um botão, explicando o funcionamento da aplicação e o passo a passo para configurar e calcular os custos.

## Estrutura do Projeto
SimuladorGastoEnergiaGUI.java: A classe principal que inicializa e configura a interface gráfica.
Casa.java: Representa a casa ou o estabelecimento (residencial ou comercial) e gerencia a tarifa de energia.
Eletrodomestico.java: Representa os aparelhos que consomem energia, armazenando nome, potência e horas de uso diário.
Residencial.java / Comercial.java: Classes específicas para tipos de residência, herdando de Casa e calculando o custo com base na tarifa.

## Contribuição
Sinta-se à vontade para contribuir com melhorias! Para isso, basta seguir os passos abaixo:

Faça um fork deste repositório.
Crie uma branch para sua modificação (git checkout -b feature/nova-feature).
Faça as alterações necessárias e envie um pull request.
