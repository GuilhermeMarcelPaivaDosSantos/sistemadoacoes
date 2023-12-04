import java.text.DecimalFormat;
import java.util.Scanner;

// Classe que representa uma doação
class Doacao {
    private String nomeDoador; // Nome do doador
    private double valorDoacao; // Valor da doação

    // Construtor da classe Doacao
    public Doacao(String nomeDoador, double valorDoacao) {
        this.nomeDoador = nomeDoador;
        this.valorDoacao = valorDoacao;
    }

    // Métodos para obter o nome do doador e o valor da doação
    public String getNomeDoador() {
        return nomeDoador;
    }

    public double getValorDoacao() {
        return valorDoacao;
    }

    // Método para formatar a representação da doação como String
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String valorFormatado = df.format(valorDoacao);
        return "Doação de " + nomeDoador + " no valor de R$" + valorFormatado;
    }
}

// Classe principal do sistema de doações
public class sistemadedoacoes {
    private static Doacao[] doacoes = new Doacao[100]; // Array para armazenar doações
    private static int totalDoacoes = 0; // Contador para o número total de doações

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] instituicoes = {"Unilever", "ONG", "Instituto Luisa Mell", "Projeto Velho Amigo", "Doutores da Alegria"};

        exibirOpcoesInstituicoes(instituicoes); // Exibe as opções de instituições para doação
        int escolhaInstituicao = solicitarEscolhaInstituicao(scanner, instituicoes.length); // Solicita a escolha da instituição

        if (escolhaInstituicao == -1) {
            System.out.println("Escolha inválida. Programa Encerrado.");
            scanner.close();
            return;
        }

        System.out.println("Você escolheu: " + instituicoes[escolhaInstituicao - 1]); // Mostra a instituição escolhida

        interagirComSistema(scanner); // Inicia a interação com o sistema
        scanner.close(); // Fecha o Scanner
    }

    // Métodos para exibir opções de instituições e solicitar a escolha
    private static void exibirOpcoesInstituicoes(String[] instituicoes) {
        System.out.println("Escolha uma instituição para doar:");
        for (int i = 0; i < instituicoes.length; i++) {
            System.out.println((i + 1) + ". " + instituicoes[i]);
        }
    }

    private static int solicitarEscolhaInstituicao(Scanner scanner, int tamanho) {
        while (true) {
            try {
                System.out.print("Escolha: ");
                int escolha = scanner.nextInt();
                if (escolha >= 1 && escolha <= tamanho) {
                    return escolha;
                } else {
                    return -1;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Por favor, digite um número inteiro válido.");
                scanner.next();
            }
        }
    }

    // Função principal de interação com o sistema
    private static void interagirComSistema(Scanner scanner) {
        boolean sair = false;
        while (!sair) {
            exibirMenu(); // Exibe o menu de opções
            int opcao = solicitarOpcao(scanner); // Solicita a opção do usuário

            switch (opcao) {
                case 1:
                    realizarDoacao(scanner); // Realiza uma doação
                    break;
                case 2:
                    verDetalhesDasDoacoes(); // Exibe os detalhes das doações
                    break;
                case 3:
                    mostrarValorTotalDoacoes(); // Exibe o valor total das doações
                    break;
                case 4:
                    System.out.println("Sistema Encerrado"); // Encerra o sistema
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente."); // Opção inválida
                    break;
            }
        }
    }

    // Métodos para exibir o menu e solicitar a opção do usuário
    private static void exibirMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1. Fazer doação");
        System.out.println("2. Ver detalhes da doação");
        System.out.println("3. Ver valor total das doações");
        System.out.println("4. Sair");
    }

    private static int solicitarOpcao(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Opção: ");
                int opcao = scanner.nextInt();
                return opcao;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Por favor, digite um número inteiro válido.");
                scanner.next();
            }
        }
    }

    // Método para realizar uma doação
    private static void realizarDoacao(Scanner scanner) {
        scanner.nextLine();

        System.out.println("Digite o nome do doador:");
        String nomeDoador = scanner.nextLine();

        System.out.println("Digite o valor da doação:");
        String inputValor = scanner.nextLine();

        inputValor = inputValor.replace(",", ".");

        try {
            double valorDoacao = Double.parseDouble(inputValor);
            Doacao novaDoacao = new Doacao(nomeDoador, valorDoacao);

            DecimalFormat df = new DecimalFormat("#,##0.00");
            String valorFormatado = df.format(novaDoacao.getValorDoacao());

            novaDoacao = new Doacao(nomeDoador, Double.parseDouble(inputValor));
            System.out.println("Doação realizada: " + novaDoacao.getNomeDoador() + " no valor de R$" + valorFormatado);

            doacoes[totalDoacoes] = novaDoacao;
            totalDoacoes++;

        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Digite um número válido.");
        }
    }

    // Método para exibir os detalhes das doações realizadas
    private static void verDetalhesDasDoacoes() {
        if (totalDoacoes == 0) {
            System.out.println("Nenhuma doação realizada ainda.");
            return;
        }

        System.out.println("Detalhes das doações realizadas:");
        for (int i = 0; i < totalDoacoes; i++) {
            System.out.println("Doação " + (i + 1) + ": " + doacoes[i]);
        }
    }

    // Método para mostrar o valor total das doações
    private static void mostrarValorTotalDoacoes() {
        if (totalDoacoes == 0) {
            System.out.println("Nenhuma doação realizada ainda.");
            return;
        }

        double valorTotal = 0;
        for (int i = 0; i < totalDoacoes; i++) {
            valorTotal += doacoes[i].getValorDoacao();
        }

        DecimalFormat df = new DecimalFormat("#,##0.00");
        String valorTotalFormatado = df.format(valorTotal);

        System.out.println("Valor total das doações: R$" + valorTotalFormatado);
    }
}