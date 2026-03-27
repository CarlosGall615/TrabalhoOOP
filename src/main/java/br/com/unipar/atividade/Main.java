package br.com.unipar.atividade;

import java.util.Random;
import java.util.Scanner;

public class Main {

    // A classe principal ficou focada em seleção de personagem e execução da batalha no console.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        apresentarIntroducao();

        Personagem jogador = selecionarPersonagemDoJogador(scanner, random);
        Personagem inimigo = sortearInimigo(random, jogador.getBase());
        CenarioCasa cenario = sortearCenario(random);

        System.out.println();
        System.out.println("===== CENÁRIO DA BATALHA =====");
        System.out.println("Local sorteado: " + cenario.getNomeExibicao());
        System.out.println("Efeito da mansão: " + cenario.getDescricao() + ".");
        System.out.println();

        System.out.println("===== PERSONAGEM ESCOLHIDO =====");
        jogador.exibirEstado();
        System.out.println();

        System.out.println("===== OPONENTE CONTROLADO PELO SISTEMA =====");
        inimigo.exibirEstado();
        System.out.println();

        executarBatalha(scanner, jogador, inimigo, cenario, random);
        scanner.close();
    }

    private static void apresentarIntroducao() {
        System.out.println("====================================================");
        System.out.println(" BATALHA ACADÊMICA NA CASA DA FAMÍLIA ADDAMS");
        System.out.println("====================================================");
        System.out.println("O jogador escolhe um personagem pronto da família.");
        System.out.println("Cada personagem já possui classe narrativa e mascote oficial.");
        System.out.println("Durante a batalha, personagem, mascote e cenário interagem o tempo todo.");
    }

    private static Personagem selecionarPersonagemDoJogador(Scanner scanner, Random random) {
        PersonagemBaseAddams[] personagens = PersonagemBaseAddams.values();

        System.out.println();
        System.out.println("Escolha com qual personagem você deseja jogar:");
        for (int i = 0; i < personagens.length; i++) {
            PersonagemBaseAddams personagem = personagens[i];
            System.out.println((i + 1) + " - " + personagem.getNomeExibicao());
            System.out.println("Classe: " + personagem.getClassePersonagem().getDescricao());
            System.out.println("Mascote: " + personagem.getTipoMascote().getNomeExibicao());
            System.out.println("Descrição: " + personagem.getDescricao());
        }

        int opcao = lerOpcao(scanner, 1, personagens.length);
        return new Personagem(personagens[opcao - 1], 1, random);
    }

    private static Personagem sortearInimigo(Random random, PersonagemBaseAddams baseJogador) {
        PersonagemBaseAddams[] personagens = PersonagemBaseAddams.values();
        PersonagemBaseAddams baseInimiga;

        do {
            baseInimiga = personagens[random.nextInt(personagens.length)];
        } while (baseInimiga == baseJogador);

        int nivelInimigo = random.nextInt(1, 4);
        return new Personagem(baseInimiga, nivelInimigo, random);
    }

    private static CenarioCasa sortearCenario(Random random) {
        CenarioCasa[] cenarios = CenarioCasa.values();
        return cenarios[random.nextInt(cenarios.length)];
    }

    private static void executarBatalha(Scanner scanner, Personagem jogador, Personagem inimigo,
                                        CenarioCasa cenario, Random random) {
        int turno = 1;

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            System.out.println("====================================================");
            System.out.println("Turno " + turno);
            System.out.println("====================================================");
            jogador.exibirEstado();
            System.out.println();
            inimigo.exibirEstado();
            System.out.println();

            realizarTurnoDoJogador(scanner, jogador, inimigo, cenario);
            if (!inimigo.estaVivo()) {
                break;
            }

            System.out.println();
            realizarTurnoDoSistema(inimigo, jogador, cenario, random);

            jogador.finalizarTurno();
            inimigo.finalizarTurno();
            turno++;
            System.out.println();
        }

        exibirResultadoFinal(jogador, inimigo);
    }

    private static void realizarTurnoDoJogador(Scanner scanner, Personagem jogador, Personagem inimigo,
                                               CenarioCasa cenario) {
        System.out.println("Escolha sua ação:");
        System.out.println("1 - Ataque principal do personagem");
        System.out.println("2 - Defender");
        System.out.println("3 - Usar skill do mascote");
        System.out.println("4 - Descansar com o mascote");

        int acao = lerOpcao(scanner, 1, 4);
        System.out.println();

        switch (acao) {
            case 1:
                int dano = jogador.atacar(cenario);
                inimigo.receberDano(dano, cenario, "ataque de " + jogador.getNome());
                break;
            case 2:
                jogador.prepararDefesa();
                break;
            case 3:
                jogador.usarSkillDoMascote(inimigo, cenario);
                break;
            case 4:
                jogador.descansarComMascote();
                break;
            default:
                System.out.println("A mansão confundiu o personagem e o turno foi desperdiçado.");
        }
    }

    private static void realizarTurnoDoSistema(Personagem inimigo, Personagem jogador,
                                               CenarioCasa cenario, Random random) {
        System.out.println("Turno do sistema: " + inimigo.getNome());

        boolean vidaBaixa = inimigo.getVidaAtual() <= (inimigo.getVidaMaxima() / 3);

        if (vidaBaixa && random.nextBoolean()) {
            inimigo.descansarComMascote();
            return;
        }

        if (inimigo.getMascote().podeUsarSkill() && random.nextInt(100) < 35) {
            inimigo.usarSkillDoMascote(jogador, cenario);
            return;
        }

        if (random.nextInt(100) < 25) {
            inimigo.prepararDefesa();
            return;
        }

        int dano = inimigo.atacar(cenario);
        jogador.receberDano(dano, cenario, "ataque de " + inimigo.getNome());
    }

    private static void exibirResultadoFinal(Personagem jogador, Personagem inimigo) {
        System.out.println("====================================================");
        System.out.println(" RESULTADO FINAL");
        System.out.println("====================================================");

        if (jogador.estaVivo()) {
            System.out.println("Vitória do jogador com " + jogador.getNome() + ".");
        } else {
            System.out.println("Vitória do sistema com " + inimigo.getNome() + ".");
        }

        System.out.println();
        System.out.println("Estado final dos personagens:");
        jogador.exibirEstado();
        System.out.println();
        inimigo.exibirEstado();
    }

    private static int lerOpcao(Scanner scanner, int minimo, int maximo) {
        while (true) {
            System.out.print("Digite uma opção entre " + minimo + " e " + maximo + ": ");

            // Esse cuidado evita quebra do programa se a entrada terminar no meio da execução.
            if (!scanner.hasNext()) {
                System.out.println();
                System.out.println("Entrada encerrada. O sistema assumirá a opção " + minimo + ".");
                return minimo;
            }

            if (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }

                if (valor >= minimo && valor <= maximo) {
                    return valor;
                }
            } else if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            System.out.println("Entrada inválida. Tente novamente.");
        }
    }
}
