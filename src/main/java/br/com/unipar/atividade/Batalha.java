package br.com.unipar.atividade;

import java.util.Random;
import java.util.Scanner;

/**
 * Controla o fluxo da batalha em turnos no console.
 */
public class Batalha {

    /**
     * Personagem controlado pelo jogador.
     */
    private final Personagem jogador;

    /**
     * Personagem controlado automaticamente pelo sistema.
     */
    private final Personagem inimigo;

    /**
     * Scanner usado para ler as escolhas do usuário.
     */
    private final Scanner scanner;

    /**
     * Fonte aleatória da inteligência do sistema.
     */
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
            exibirCabecalhoDoTurno(turno);
            executarTurnoDoJogador();
            exibirResumoDaAcaoDoJogador();
            if (!inimigo.estaVivo()) {
                exibirResumoDaRodada(turno);
                break;
            }

            executarTurnoDoInimigo();
            executarIntervencaoDaMansao();
            exibirResumoDaRodada(turno);
            jogador.prepararProximoTurno();
            inimigo.prepararProximoTurno();
            turno++;
        }

        exibirResultadoFinal();
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
     * Exibe status resumido no começo de cada turno.
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
     * Permite ao jogador escolher uma ação válida no menu.
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
     * Mostra o menu principal de ações do turno do jogador.
     */
    private void exibirMenuDeAcoes() {
        for (AcaoBatalha acao : AcaoBatalha.values()) {
            System.out.println(acao.getCodigo() + " - " + acao.getDescricao());
        }
    }

    /**
     * Lê a ação do jogador com validação simples de entrada.
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
     * Executa a ação automática do inimigo com base no estado atual.
     */
    private void executarTurnoDoInimigo() {
        AcaoBatalha acao = escolherAcaoInimiga();
        System.out.println("O adversário se movimenta: " + inimigo.getNome() + ".");
        processarAcao(inimigo, jogador, acao, false);
    }

    /**
     * Seleciona a ação mais coerente para o inimigo controlado pelo sistema.
     */
    private AcaoBatalha escolherAcaoInimiga() {
        if (inimigo.getVida() < 350 && inimigo.getMascote().podeAgir()) {
            return AcaoBatalha.AJUDA_MASCOTE;
        }
        if (inimigo.getMana() >= 40 && random.nextBoolean()) {
            return AcaoBatalha.HABILIDADE_ESPECIAL;
        }
        if (random.nextInt(100) < 25) {
            return AcaoBatalha.DEFENDER;
        }
        return AcaoBatalha.ATACAR;
    }

    /**
     * Direciona cada ação para um método menor e reutilizável.
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

        executarAjudaDoMascote(ator, alvo, permitirStatus);
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
     * Realiza o ataque básico e mostra o dano final causado.
     */
    private void executarAtaque(Personagem ator, Personagem alvo) {
        int danoBruto = ator.atacar();
        ResultadoDano resultado = alvo.receberDanoDetalhado(danoBruto);
        System.out.println(ator.getNome() + " avançou e atacou " + alvo.getNome() + ".");
        exibirDetalhesDoDano(alvo, resultado);
    }

    /**
     * Ativa a defesa do personagem para o próximo impacto.
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
     * Aciona o mascote para dar suporte ou atacar.
     */
    private void executarAjudaDoMascote(Personagem ator, Personagem alvo,
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
     * Lê o modo ofensivo ou defensivo da ajuda do mascote.
     */
    private ModoAjudaMascote lerModoAjudaMascote() {
        while (true) {
            for (ModoAjudaMascote modo : ModoAjudaMascote.values()) {
                System.out.println(modo.getCodigo() + " - " + modo.getDescricao());
            }
            System.out.print("Escolha como o mascote vai ajudar: ");

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
     * Define se o inimigo usará mascote para atacar ou proteger.
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
     * Exibe os efeitos específicos da ajuda do mascote.
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
     * Exibe o estado do jogador logo após sua ação.
     */
    private void exibirResumoDaAcaoDoJogador() {
        System.out.println("Resumo após a jogada do jogador:");
        System.out.println(jogador.getResumoRodada());
        System.out.println(inimigo.getResumoRodada());
        System.out.println();
    }

    /**
     * Fecha a rodada com um resumo compacto dos dois lados.
     */
    private void exibirResumoDaRodada(int turno) {
        System.out.println("===== RESUMO DA RODADA " + turno + " =====");
        System.out.println("Lado do jogador: " + jogador.getResumoRodada());
        System.out.println("Lado adversário: " + inimigo.getResumoRodada());
        System.out.println();
    }

    /**
     * Permite que a mansão mal-assombrada interfira ao fim da rodada.
     */
    private void executarIntervencaoDaMansao() {
        if (!jogador.estaVivo() || !inimigo.estaVivo()) {
            return;
        }

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
     * Apresenta o resultado final depois do encerramento da batalha.
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
    }
}
