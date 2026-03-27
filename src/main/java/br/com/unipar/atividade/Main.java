package br.com.unipar.atividade;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe principal responsável por apresentar o jogo e iniciar a batalha.
 */
public class Main {

    /**
     * Executa o fluxo completo do jogo no console.
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag("pt-BR"));
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {
            executarPartida(scanner, random);
            if (!perguntarNovaLuta(scanner)) {
                break;
            }
        }

        System.out.println();
        System.out.println("A mansão voltou ao silêncio. Até a próxima batalha.");
        scanner.close();
    }

    /**
     * Executa uma luta completa do início ao fim.
     */
    private static void executarPartida(Scanner scanner, Random random) {
        GeradorPersonagem gerador = new GeradorPersonagem(random);

        apresentarJogo();
        gerador.exibirPersonagensDisponiveis();

        int opcao = lerOpcaoDoJogador(scanner);
        Personagem jogador = gerador.criarJogador(opcao);
        Personagem inimigo = gerador.criarInimigoAleatorio(jogador);

        exibirEscolhasIniciais(jogador, inimigo);
        new Batalha(jogador, inimigo, scanner, random).iniciar();
    }

    /**
     * Exibe a introdução temática do trabalho.
     */
    private static void apresentarJogo() {
        System.out.println("==============================================");
        System.out.println(" BATALHA EM CONSOLE - UNIVERSO FAMÍLIA ADDAMS");
        System.out.println("==============================================");
        System.out.println("Escolha um personagem da família, enfrente um rival aleatório");
        System.out.println("e combine ataques, defesa, habilidade exclusiva e ajuda do mascote.");
        System.out.println("Cuidado: a mansão é mal-assombrada e pode interferir a cada rodada.");
        System.out.println();
    }

    /**
     * Lê a opção do usuário e garante que ela esteja dentro do catálogo.
     */
    private static int lerOpcaoDoJogador(Scanner scanner) {
        int limite = PersonagemBaseAddams.values().length;

        while (true) {
            System.out.print("Digite o número do personagem desejado: ");

            if (!scanner.hasNext()) {
                System.out.println();
                System.out.println("Entrada encerrada. O sistema escolherá a opção 1.");
                return 1;
            }
            if (scanner.hasNextInt()) {
                int opcao = scanner.nextInt();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                if (opcao >= 1 && opcao <= limite) {
                    return opcao;
                }
            } else if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            System.out.println("Escolha inválida. Tente novamente.");
        }
    }

    /**
     * Mostra quem foi escolhido pelo jogador e quem foi sorteado pelo sistema.
     */
    private static void exibirEscolhasIniciais(Personagem jogador, Personagem inimigo) {
        System.out.println();
        System.out.println("Seu personagem será " + jogador.getNome() + ".");
        System.out.println("O adversário sorteado foi " + inimigo.getNome() + ".");
        System.out.println("A batalha começará com 1000 pontos de vida para cada lado.");
        System.out.println();
    }

    /**
     * Pergunta ao usuário se deseja iniciar uma nova luta.
     */
    private static boolean perguntarNovaLuta(Scanner scanner) {
        while (true) {
            System.out.print("Deseja iniciar uma nova luta? (S/N): ");

            if (!scanner.hasNext()) {
                System.out.println();
                return false;
            }

            String resposta = scanner.next().trim().toUpperCase(Locale.ROOT);
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            if ("S".equals(resposta)) {
                System.out.println();
                return true;
            }
            if ("N".equals(resposta)) {
                return false;
            }

            System.out.println("Resposta inválida. Digite S para sim ou N para não.");
        }
    }
}
