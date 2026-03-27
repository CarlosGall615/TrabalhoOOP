package br.com.unipar.atividade;

import java.util.Random;
import java.util.Scanner;

/**
 * Controla o fluxo da batalha em turnos no console.
 */
public class Batalha {

    private final Personagem jogador;
    private final Personagem inimigo;
    private final Scanner scanner;
    private final Random random;
    private final IntervencaoMansao intervencaoMansao;

    public Batalha(Personagem jogador, Personagem inimigo, Scanner scanner, Random random) {
        this.jogador = jogador;
        this.inimigo = inimigo;
        this.scanner = scanner;
        this.random = random;
        this.intervencaoMansao = new IntervencaoMansao(random);
    }

    /**
     * Executa toda a batalha até que um dos personagens morra.
     */
    public void iniciar() {
        int turno = 1;
        exibirInicioDaBatalha();

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            registrarRodada();
            exibirCabecalhoDoTurno(turno);
            executarTurnoDoJogador();
            if (algumCombatenteFoiDerrotado()) {
                exibirTabelaDaRodada(turno);
                break;
            }

            executarTurnoDoInimigo();
            if (algumCombatenteFoiDerrotado()) {
                exibirTabelaDaRodada(turno);
                break;
            }

            executarIntervencaoDaMansao();
            exibirTabelaDaRodada(turno);
            if (algumCombatenteFoiDerrotado()) {
                break;
            }

            prepararProximaRodada();
            turno++;
        }

        exibirResultadoFinal();
    }

    /**
     * Marca que os dois lados participaram da rodada atual.
     */
    private void registrarRodada() {
        jogador.registrarRodadaParticipada();
        inimigo.registrarRodadaParticipada();
    }

    /**
     * Confere se a luta deve encerrar imediatamente.
     */
    private boolean algumCombatenteFoiDerrotado() {
        return !jogador.estaVivo() || !inimigo.estaVivo();
    }

    /**
     * Renova recursos no fim da rodada quando ambos seguem vivos.
     */
    private void prepararProximaRodada() {
        jogador.prepararProximoTurno();
        inimigo.prepararProximoTurno();
    }

    /**
     * Mostra a apresentação dos dois personagens antes do combate.
     */
    private void exibirInicioDaBatalha() {
        System.out.println();
        System.out.println("===== INÍCIO DA BATALHA =====");
        System.out.println("Os portões da mansão se fecham e o duelo vai começar.");
        System.out.println();
        System.out.println("Lado do jogador:");
        jogador.exibirEstado();
        System.out.println();
        System.out.println("Lado adversário:");
        inimigo.exibirEstado();
        System.out.println();
    }

    /**
     * Exibe um resumo inicial da rodada.
     */
    private void exibirCabecalhoDoTurno(int turno) {
        System.out.println("========================================");
        System.out.println("Rodada " + turno);
        System.out.println("========================================");
        System.out.println("Panorama antes das ações:");
        System.out.println(jogador.getResumoStatus());
        System.out.println(inimigo.getResumoStatus());
        System.out.println();
    }

    /**
     * Executa o turno do jogador até uma ação válida consumir a rodada.
     */
    private void executarTurnoDoJogador() {
        boolean turnoConcluido = false;

        while (!turnoConcluido) {
            exibirMenuDeAcoes();
            AcaoBatalha acao = lerAcaoDoJogador();
            turnoConcluido = processarAcao(jogador, inimigo, acao, true);
        }
    }

    /**
     * Mostra o menu principal de ações do turno.
     */
    private void exibirMenuDeAcoes() {
        for (AcaoBatalha acao : AcaoBatalha.values()) {
            System.out.println(acao.getCodigo() + " - " + acao.getDescricao());
        }
    }

    /**
     * Lê a ação do usuário com validação simples.
     */
    private AcaoBatalha lerAcaoDoJogador() {
        while (true) {
            System.out.print("Escolha sua ação: ");

            if (!scanner.hasNext()) {
                System.out.println();
                System.out.println("Entrada encerrada. O turno seguirá com ataque básico.");
                return AcaoBatalha.ATACAR;
            }
            if (scanner.hasNextInt()) {
                AcaoBatalha acao = AcaoBatalha.porCodigo(scanner.nextInt());
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                if (acao != null) {
                    return acao;
                }
            } else if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            System.out.println("Entrada inválida. Digite um número do menu.");
        }
    }

    /**
     * Executa a ação escolhida para um dos lados da batalha.
     */
    private boolean processarAcao(Personagem ator, Personagem alvo,
                                  AcaoBatalha acao, boolean permitirStatus) {
        if (acao == AcaoBatalha.EXIBIR_STATUS && permitirStatus) {
            exibirStatusDetalhado();
            return false;
        }
        if (acao == AcaoBatalha.ATACAR) {
            executarAtaque(ator, alvo);
            return true;
        }
        if (acao == AcaoBatalha.DEFENDER) {
            executarDefesa(ator);
            return true;
        }
        if (acao == AcaoBatalha.HABILIDADE_ESPECIAL) {
            executarHabilidadeEspecial(ator, alvo);
            return true;
        }
        if (acao == AcaoBatalha.ATAQUE_CONJUNTO) {
            executarAtaqueConjunto(ator, alvo);
            return true;
        }

        executarUsoExclusivoDoMascote(ator, alvo, permitirStatus);
        return true;
    }

    /**
     * Exibe o estado completo dos dois personagens sem consumir o turno.
     */
    private void exibirStatusDetalhado() {
        System.out.println();
        jogador.exibirEstado();
        System.out.println();
        inimigo.exibirEstado();
        System.out.println();
    }

    /**
     * Executa o ataque básico do personagem.
     */
    private void executarAtaque(Personagem ator, Personagem alvo) {
        ResultadoDano resultado = ator.executarAtaqueBasico(alvo);
        System.out.println(ator.getNome() + " avançou e atacou " + alvo.getNome() + ".");
        exibirDetalhesDoDano(alvo, resultado);
    }

    /**
     * Ativa a postura defensiva do personagem.
     */
    private void executarDefesa(Personagem ator) {
        ator.defender();
        System.out.println(ator.getNome() + " recuou um passo e firmou a guarda.");
        System.out.println("Defesa temporária preparada: " + ator.getBonusDefesaTemporario() + ".");
        System.out.println();
    }

    /**
     * Usa a habilidade especial do personagem.
     */
    private void executarHabilidadeEspecial(Personagem ator, Personagem alvo) {
        ResultadoHabilidade resultado = ator.usarHabilidadeEspecial(alvo);
        System.out.println(ator.getNome() + " acionou a habilidade exclusiva "
                + ator.getHabilidadeExclusiva() + ".");
        if (!resultado.isSucesso()) {
            System.out.println(resultado.getMensagem());
            System.out.println();
            return;
        }

        exibirDetalhesDoDano(alvo, resultado.getResultadoDano());
        System.out.println("Vida recuperada por " + ator.getNome() + ": "
                + resultado.getVidaRecuperada() + ".");
        System.out.println("Defesa temporária recebida: "
                + resultado.getBonusDefesaAplicado() + ".");
        System.out.println("Mana restante de " + ator.getNome() + ": "
                + resultado.getManaRestante() + ".");
        System.out.println();
    }

    /**
     * Executa um ataque em conjunto entre personagem e mascote.
     */
    private void executarAtaqueConjunto(Personagem ator, Personagem alvo) {
        ResultadoAtaqueConjunto resultado = ator.atacarComMascote(alvo);
        System.out.println(ator.getNome() + " iniciou uma investida conjunta.");
        System.out.println("Impacto do personagem:");
        exibirDetalhesDoDano(alvo, resultado.getResultadoPersonagem());
        System.out.println(resultado.getMensagemMascote());

        if (resultado.getResultadoMascote() != null) {
            System.out.println("Impacto do mascote:");
            exibirDetalhesDoDano(alvo, resultado.getResultadoMascote());
        }

        System.out.println("Dano total combinado da jogada: " + resultado.getDanoTotal() + ".");
        System.out.println("Energia restante do mascote: "
                + resultado.getEnergiaRestanteMascote() + ".");
        System.out.println();
    }

    /**
     * Executa uma jogada em que apenas o mascote entra em ação.
     */
    private void executarUsoExclusivoDoMascote(Personagem ator, Personagem alvo,
                                               boolean escolhaDoJogador) {
        ModoAjudaMascote modo = escolhaDoJogador
                ? lerModoAjudaMascote()
                : escolherModoAjudaDoInimigo(ator);
        ResultadoMascote resultado = ator.pedirAjudaDoMascote(alvo, modo);
        System.out.println(resultado.getMensagem());
        exibirDetalhesDoMascote(ator, alvo, resultado, modo);
        System.out.println();
    }

    /**
     * Lê o tipo de ajuda quando somente o mascote será usado.
     */
    private ModoAjudaMascote lerModoAjudaMascote() {
        while (true) {
            for (ModoAjudaMascote modo : ModoAjudaMascote.values()) {
                System.out.println(modo.getCodigo() + " - " + modo.getDescricao());
            }
            System.out.print("Escolha como o mascote vai agir: ");

            if (!scanner.hasNext()) {
                System.out.println();
                return ModoAjudaMascote.OFENSIVO;
            }
            if (scanner.hasNextInt()) {
                ModoAjudaMascote modo = ModoAjudaMascote.porCodigo(scanner.nextInt());
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
                if (modo != null) {
                    return modo;
                }
            } else if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            System.out.println("Modo inválido. Escolha 1 ou 2.");
        }
    }

    /**
     * Executa a ação automática do adversário.
     */
    private void executarTurnoDoInimigo() {
        AcaoBatalha acao = escolherAcaoInimiga();
        System.out.println("O adversário se movimenta: " + inimigo.getNome() + ".");
        processarAcao(inimigo, jogador, acao, false);
    }

    /**
     * Seleciona a ação mais coerente para o adversário.
     */
    private AcaoBatalha escolherAcaoInimiga() {
        if (inimigo.getVida() < 280 && inimigo.getMascote().podeAgir()) {
            return AcaoBatalha.USAR_SOMENTE_MASCOTE;
        }
        if (inimigo.getMana() >= 40 && random.nextInt(100) < 30) {
            return AcaoBatalha.HABILIDADE_ESPECIAL;
        }
        if (inimigo.getMascote().podeAtacarEmConjunto() && random.nextInt(100) < 20) {
            return AcaoBatalha.ATAQUE_CONJUNTO;
        }
        if (random.nextInt(100) < 20) {
            return AcaoBatalha.DEFENDER;
        }
        if (inimigo.getMascote().podeAgir() && random.nextInt(100) < 15) {
            return AcaoBatalha.USAR_SOMENTE_MASCOTE;
        }
        return AcaoBatalha.ATACAR;
    }

    /**
     * Define se o mascote do adversário vai atacar ou proteger.
     */
    private ModoAjudaMascote escolherModoAjudaDoInimigo(Personagem ator) {
        if (ator.getVida() < 350 || ator.getMana() < 40) {
            return ModoAjudaMascote.DEFENSIVO;
        }
        return ModoAjudaMascote.OFENSIVO;
    }

    /**
     * Mostra o cálculo completo do dano causado ao alvo.
     */
    private void exibirDetalhesDoDano(Personagem alvo, ResultadoDano resultado) {
        System.out.println("Dano bruto do golpe: " + resultado.getDanoBruto() + ".");
        System.out.println("Defesa base de " + alvo.getNome() + ": "
                + resultado.getDefesaBase() + ".");
        System.out.println("Defesa adicional usada na rodada: "
                + resultado.getBonusDefesa() + ".");
        System.out.println("Defesa total considerada: " + resultado.getDefesaTotal() + ".");
        System.out.println("Dano final recebido: " + resultado.getDanoFinal() + ".");
        System.out.println("Vida restante de " + alvo.getNome() + ": "
                + resultado.getVidaRestante() + ".");
        System.out.println();
    }

    /**
     * Exibe os efeitos do uso exclusivo do mascote.
     */
    private void exibirDetalhesDoMascote(Personagem ator, Personagem alvo,
                                         ResultadoMascote resultado,
                                         ModoAjudaMascote modo) {
        if (modo == ModoAjudaMascote.OFENSIVO && resultado.getResultadoDano() != null) {
            System.out.println("O mascote abriu espaço no combate e pressionou o adversário.");
            exibirDetalhesDoDano(alvo, resultado.getResultadoDano());
        }
        if (modo == ModoAjudaMascote.DEFENSIVO) {
            System.out.println("O mascote reforçou a retaguarda de " + ator.getNome() + ".");
            System.out.println("Defesa concedida a " + ator.getNome() + ": "
                    + resultado.getBonusDefesaAplicado() + ".");
            System.out.println("Vida recuperada: " + resultado.getVidaRecuperada() + ".");
            System.out.println("Mana recuperada: " + resultado.getManaRecuperada() + ".");
        }
        System.out.println("Energia restante do mascote: "
                + resultado.getEnergiaRestante() + ".");
    }

    /**
     * Permite que a mansão interfira ao fim da rodada.
     */
    private void executarIntervencaoDaMansao() {
        ResultadoIntervencaoMansao resultado = intervencaoMansao.tentarInterferir(jogador, inimigo);
        if (!resultado.isOcorreu()) {
            return;
        }

        System.out.println("===== INTERFERÊNCIA DA MANSÃO =====");
        System.out.println("Evento: " + resultado.getEvento().getNomeExibicao());
        System.out.println(resultado.getMensagem());
        System.out.println();
    }

    /**
     * Exibe a tabela ASCII com o resultado acumulado da rodada.
     */
    private void exibirTabelaDaRodada(int turno) {
        System.out.println("===== TABELA DA RODADA " + turno + " =====");
        exibirBordaDaTabela();
        exibirCabecalhoDaTabela();
        exibirBordaDaTabela();
        exibirLinhaDaTabela("Jogador", jogador);
        exibirLinhaDaTabela("Adversário", inimigo);
        exibirBordaDaTabela();
        System.out.println();
    }

    /**
     * Desenha a linha horizontal da tabela.
     */
    private void exibirBordaDaTabela() {
        System.out.println("+--------------+------+-------+---------------+---------+------------------+"
                + "------------------+------------------+---------------------+");
    }

    /**
     * Desenha a linha de títulos da tabela de rodada.
     */
    private void exibirCabecalhoDaTabela() {
        System.out.printf("| %-12s | %4s | %5s | %-13s | %7s | %-16s | %-16s | %-16s | %-19s |%n",
                "Lado", "Vida", "Mana", "Energia Masc.", "Rodadas",
                "Maior Dano Pers.", "Maior Bloq. Pers.",
                "Maior Dano Masc.", "Maior Suporte Masc.");
    }

    /**
     * Preenche a linha de um dos lados da tabela.
     */
    private void exibirLinhaDaTabela(String lado, Personagem personagem) {
        System.out.printf("| %-12s | %4d | %5d | %13d | %7d | %16d | %16d | %16d | %19d |%n",
                lado, personagem.getVida(), personagem.getMana(),
                personagem.getMascote().getEnergia(), personagem.getRodadasParticipadas(),
                personagem.getMaiorDanoPersonagem(), personagem.getMaiorBloqueioPersonagem(),
                personagem.getMaiorDanoMascote(), personagem.getMaiorSuporteMascote());
    }

    /**
     * Apresenta o resultado final da luta.
     */
    private void exibirResultadoFinal() {
        System.out.println("===== RESULTADO FINAL =====");
        if (jogador.estaVivo()) {
            System.out.println("A mansão silenciou: vitória do jogador com " + jogador.getNome() + ".");
        } else {
            System.out.println("A mansão silenciou: vitória do adversário com " + inimigo.getNome() + ".");
        }

        System.out.println();
        System.out.println("Estado final dos combatentes:");
        jogador.exibirEstado();
        System.out.println();
        inimigo.exibirEstado();
        System.out.println();
    }
}
