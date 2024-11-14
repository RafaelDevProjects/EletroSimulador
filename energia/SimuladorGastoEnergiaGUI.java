package energia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class SimuladorGastoEnergiaGUI extends JFrame {
    private JTextField tarifaField;
    private JTextField nomeField;
    private JTextField potenciaField;
    private JTextField horasPorDiaField;
    private JPanel resultadoPanel;
    private Casa casa;

    /**
     * Construtor da classe SimuladorGastoEnergiaGUI.
     * Este método configura a interface gráfica do simulador, incluindo os campos de entrada, botões, e áreas de resultado.
     * Inicializa a janela com título, tamanho e comportamento de fechamento.
     */
    public SimuladorGastoEnergiaGUI() {
        setTitle("Simulador de Gasto de Energia");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Impede o redimensionamento da janela (maximização)
        setResizable(false); // Não permite redimensionar

        // Painel principal com layout BorderLayout para maior controle do design
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240)); // fundo claro

        // Seção de configuração da casa
        JPanel configCasaPanel = new JPanel(new GridBagLayout());
        configCasaPanel.setBackground(Color.WHITE);
        configCasaPanel.setBorder(BorderFactory.createTitledBorder("Configuração da Casa"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel tipoCasaLabel = new JLabel("Tipo de residência:");
        tipoCasaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        configCasaPanel.add(tipoCasaLabel, gbc);

        String[] tiposCasa = {"Residencial", "Comercial"};
        JComboBox<String> tipoCasaBox = new JComboBox<>(tiposCasa);
        tipoCasaBox.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1;
        configCasaPanel.add(tipoCasaBox, gbc);

        JLabel tarifaLabel = new JLabel("Tarifa por kWh:");
        tarifaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        configCasaPanel.add(tarifaLabel, gbc);

        tarifaField = new JTextField(10);
        tarifaField.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1;
        configCasaPanel.add(tarifaField, gbc);

        JButton configurarCasaButton = new JButton("Configurar Casa");
        configurarCasaButton.setBackground(new Color(72, 134, 255));
        configurarCasaButton.setForeground(Color.WHITE);
        configurarCasaButton.setFont(new Font("Arial", Font.BOLD, 12));
        configurarCasaButton.addActionListener(e -> configurarCasa(tipoCasaBox));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        configCasaPanel.add(configurarCasaButton, gbc);

        mainPanel.add(configCasaPanel);

        // Seção para adicionar aparelhos
        JPanel addAparelhoPanel = new JPanel(new GridBagLayout());
        addAparelhoPanel.setBackground(Color.WHITE);
        addAparelhoPanel.setBorder(BorderFactory.createTitledBorder("Adicionar Aparelho"));

        JLabel nomeLabel = new JLabel("Nome do Aparelho:");
        nomeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        addAparelhoPanel.add(nomeLabel, gbc);

        nomeField = new JTextField(10);
        nomeField.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1;
        addAparelhoPanel.add(nomeField, gbc);

        JLabel potenciaLabel = new JLabel("Potência (W):");
        potenciaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        addAparelhoPanel.add(potenciaLabel, gbc);

        potenciaField = new JTextField(10);
        potenciaField.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1;
        addAparelhoPanel.add(potenciaField, gbc);

        JLabel horasPorDiaLabel = new JLabel("Horas de Uso Diárias:");
        horasPorDiaLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        addAparelhoPanel.add(horasPorDiaLabel, gbc);

        horasPorDiaField = new JTextField(10);
        horasPorDiaField.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1;
        addAparelhoPanel.add(horasPorDiaField, gbc);

        JButton adicionarAparelhoButton = new JButton("Adicionar Aparelho");
        adicionarAparelhoButton.setBackground(new Color(72, 134, 255));
        adicionarAparelhoButton.setForeground(Color.WHITE);
        adicionarAparelhoButton.setFont(new Font("Arial", Font.BOLD, 12));
        adicionarAparelhoButton.addActionListener(e -> adicionarAparelho());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        addAparelhoPanel.add(adicionarAparelhoButton, gbc);

        mainPanel.add(addAparelhoPanel);

        // Botão centralizado para calcular consumo e custo
        JPanel calcularPanel = new JPanel();
        calcularPanel.setBackground(new Color(240, 240, 240));
        JButton calcularButton = new JButton("Calcular Consumo e Custo");
        calcularButton.setBackground(new Color(72, 134, 255));
        calcularButton.setForeground(Color.WHITE);
        calcularButton.setFont(new Font("Arial", Font.BOLD, 14));
        calcularButton.addActionListener(e -> calcularConsumoCusto());
        calcularPanel.add(calcularButton);

        mainPanel.add(calcularPanel);

        // Painel de resultado com layout BoxLayout para adicionar JLabel dinamicamente
        resultadoPanel = new JPanel();
        resultadoPanel.setLayout(new BoxLayout(resultadoPanel, BoxLayout.Y_AXIS));
        resultadoPanel.setBackground(Color.WHITE);
        resultadoPanel.setBorder(BorderFactory.createTitledBorder("Resultados"));

        JScrollPane scrollPane = new JScrollPane(resultadoPanel);
        scrollPane.setPreferredSize(new Dimension(450, 200));
        mainPanel.add(scrollPane);

        // Adicionando o botão de ajuda
        JButton ajudaButton = new JButton("?");
        ajudaButton.setBackground(new Color(72, 134, 255));
        ajudaButton.setForeground(Color.WHITE);
        ajudaButton.setFont(new Font("Arial", Font.BOLD, 12));
        ajudaButton.setPreferredSize(new Dimension(45, 45));
        ajudaButton.setFocusPainted(false);
        ajudaButton.addActionListener(e -> mostrarAjuda());

        JPanel ajudaPanel = new JPanel();
        ajudaPanel.setBackground(new Color(240, 240, 240));
        ajudaPanel.add(ajudaButton);
        mainPanel.add(ajudaPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Exibe uma janela de ajuda com informações sobre o funcionamento do simulador.
     */
    private void mostrarAjuda() {
        // Criar a janela de ajuda (JDialog)
        JDialog ajudaDialog = new JDialog(this, "Ajuda", true);
        ajudaDialog.setSize(600, 500);
        ajudaDialog.setLocationRelativeTo(this);

        // Usar um layout mais simples para a janela
        ajudaDialog.setLayout(new BorderLayout());

        // Criar o painel para o texto de ajuda
        JPanel painelTexto = new JPanel();
        painelTexto.setLayout(new BorderLayout());
        painelTexto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Espaçamento

        // Configurar o JEditorPane para exibir o texto estático em HTML
        String textoAjuda = "<html><body style='font-family: Arial; font-size: 12px; line-height: 1.6;'>"
                + "<h2>Bem-vindo à aplicação de simulação de consumo de energia!</h2>"
                + "<p>Esta aplicação permite que você calcule o consumo de energia e o custo total de eletricidade"
                + " para uma casa com diversos aparelhos elétricos. Você pode configurar os dados da residência e dos"
                + " aparelhos para realizar a simulação.</p>"
                + "<h3>Passo a Passo para a Simulação:</h3>"
                + "<ol>"
                + "<li>Insira o valor da tarifa de energia (preço por kWh) no campo 'Tarifa de Energia'.</li>"
                + "<li>Escolha o tipo de residência (padrão ou personalizada) e insira os dados solicitados.</li>"
                + "<li>Para adicionar aparelhos, clique no botão 'Adicionar Aparelho' e informe o nome, potência"
                + " e o número de horas de uso diário de cada aparelho.</li>"
                + "<li>Após adicionar todos os aparelhos, clique no botão 'Calcular Consumo e Custo'.</li>"
                + "<li>A aplicação irá calcular o consumo total de energia e o custo total com base nos dados fornecidos.</li>"
                + "</ol>"
                + "<p>Com esses dados, você pode visualizar o total de energia consumida por sua casa e o custo da eletricidade"
                + " para os aparelhos que você inseriu.</p>"
                + "</body></html>";

        // Criar o JEditorPane para exibir o texto formatado
        JEditorPane ajudaTexto = new JEditorPane("text/html", textoAjuda);
        ajudaTexto.setEditable(false);  // Garantir que o texto não seja editável
        ajudaTexto.setBackground(Color.WHITE);

        // Adicionar o JEditorPane dentro de um JScrollPane para rolagem
        JScrollPane ajudaScroll = new JScrollPane(ajudaTexto);
        painelTexto.add(ajudaScroll, BorderLayout.CENTER);

        // Botão Fechar
        JButton fecharButton = new JButton("Fechar");
        fecharButton.addActionListener(e -> ajudaDialog.dispose());
        painelTexto.add(fecharButton, BorderLayout.SOUTH);

        // Adicionar o painel de texto ao diálogo
        ajudaDialog.add(painelTexto, BorderLayout.CENTER);

        // Tornar a janela de ajuda visível
        ajudaDialog.setVisible(true);
    }


    /**
     * Configura a casa com base no tipo de residência e a tarifa fornecida.
     * @param tipoCasaBox O JComboBox que contém as opções de tipo de residência selecionada.
     */
    private void configurarCasa(JComboBox<String> tipoCasaBox) {
        try {
            double tarifaKWh = Double.parseDouble(tarifaField.getText());
            String tipo = (String) tipoCasaBox.getSelectedItem();

            if ("Residencial".equals(tipo)) {
                casa = new Residencial(tarifaKWh);
            } else {
                casa = new Comercial(tarifaKWh);
            }

            adicionarResultado("Casa configurada como " + tipo + " com tarifa de " + tarifaKWh + " R$/kWh.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite um valor válido para a tarifa.");
        }
    }
    /**
     * Adiciona um novo aparelho à casa, usando os dados fornecidos pelo usuário.
     * @param nome O nome do aparelho.
     * @param potencia A potência do aparelho em watts.
     * @param horasPorDia O número de horas que o aparelho é usado por dia.
     */
    private void adicionarAparelho() {
        try {
            String nome = nomeField.getText();
            double potencia = Double.parseDouble(potenciaField.getText());
            double horasPorDia = Double.parseDouble(horasPorDiaField.getText());

            Eletrodomestico eletrodomestico = new Eletrodomestico(nome, potencia, horasPorDia);
            casa.adicionarEletrodomestico(eletrodomestico);

            nomeField.setText("");
            potenciaField.setText("");
            horasPorDiaField.setText("");

            adicionarResultado("Aparelho " + nome + " adicionado.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Digite valores válidos para potência e horas.");
        }
    }


    private void adicionarResultado(String texto) {
        JLabel resultadoLabel = new JLabel(texto);
        resultadoPanel.add(resultadoLabel);
        resultadoPanel.revalidate();
    }

    /**
     * Calcula o consumo total de energia e o custo com base nos aparelhos cadastrados na casa.
     * Exibe o resultado na interface gráfica.
     */
    private void calcularConsumoCusto() {
        double consumoTotal = casa.calcularConsumoTotal();
        double custoTotal = casa.calcularCustoTotal();

        DecimalFormat df = new DecimalFormat("#.##");

        adicionarResultado("Consumo total: " + df.format(consumoTotal) + " kWh");
        adicionarResultado("Custo total: " + df.format(custoTotal) + " R$");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimuladorGastoEnergiaGUI frame = new SimuladorGastoEnergiaGUI();
            frame.setVisible(true);
        });
    }
}
