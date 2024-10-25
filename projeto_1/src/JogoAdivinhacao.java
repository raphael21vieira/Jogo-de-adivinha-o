import java.util.Random;
import java.util.Scanner;

public class JogoAdivinhacao {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        // Solicitar intervalo mínimo e máximo ao jogador
        System.out.println("Bem-vindo ao Jogo de Adivinhação!");
        System.out.println("Escolha o intervalo para o número secreto.");
        System.out.print("Digite o valor mínimo: ");
        int vMin = scan.nextInt();
        System.out.print("Digite o valor máximo: ");
        int vMax = scan.nextInt();

        // Validar o intervalo
        if (vMin >= vMax) {
            System.out.println("Erro: o valor mínimo deve ser menor que o valor máximo. Tente novamente.");
            return; // Sai do programa se o intervalo for inválido
        }

        // Gerar o número secreto aleatório dentro do intervalo definido pelo jogador
        int numeroSecreto = random.nextInt((vMax - vMin) + 1) + vMin;

        // Solicitar ao jogador o número máximo de tentativas
        System.out.print("Digite o número máximo de tentativas: ");
        int maxTentativas = scan.nextInt();
        int tentativasRestantes = maxTentativas;
        boolean acertou = false;

        System.out.printf("Tente adivinhar o número entre %d e %d.\n", vMin, vMax);

        // Loop principal do jogo
        while (!acertou && tentativasRestantes > 0) {
            System.out.printf("Escolha um número ou digite 'sair' para encerrar. Tentativas restantes: %d\n", tentativasRestantes);
            String input = scan.next(); // Captura a entrada do jogador como String

            // Verifica se o jogador quer sair do jogo
            if (input.equalsIgnoreCase("sair")) {
                System.out.println("Jogo encerrado pelo jogador. Até a próxima!");
                break; // Encerra o jogo
            }

            try {
                // Tenta converter a entrada para um número
                int palpite = Integer.parseInt(input);

                // Verifica se o palpite está correto
                if (palpite == numeroSecreto) {
                    System.out.println("Parabéns! Você acertou o número secreto!");
                    System.out.printf("Tentativas utilizadas: %d\n", maxTentativas - tentativasRestantes + 1);
                    acertou = true; // Termina o jogo
                } else {
                    tentativasRestantes--; // Decrementa as tentativas restantes

                    // Dicas para o jogador
                    if (palpite > numeroSecreto) {
                        System.out.println("Dica: o número secreto é menor do que o seu palpite.");
                    } else {
                        System.out.println("Dica: o número secreto é maior do que o seu palpite.");
                    }

                    // Verifica se o jogador esgotou as tentativas
                    if (tentativasRestantes == 0) {
                        System.out.println("Game Over! Você não conseguiu acertar o número.");
                        System.out.printf("O número correto era: %d\n", numeroSecreto);
                    }
                }
            } catch (NumberFormatException e) {
                // Trata erro caso o jogador insira algo que não seja um número
                System.out.println("Entrada inválida. Digite um número válido ou 'sair' para encerrar.");
            }
        }

        // Fecha o scanner após o término do jogo
        scan.close();
    }
}
