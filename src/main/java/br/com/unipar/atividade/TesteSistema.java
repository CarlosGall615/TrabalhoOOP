package br.com.unipar.atividade;

import java.util.Random;

/**
 * Executa testes simples em console sem dependências externas.
 */
public class TesteSistema {

    /**
     * Contadores usados para resumir os resultados ao final.
     */
    private static int aprovados;
    private static int reprovados;

    /**
     * Inicia todos os testes lógicos do projeto.
     */
    public static void main(String[] args) {
        System.out.println("===== TESTES DO SISTEMA =====");
        testarAtaque();
        testarDefesa();
        testarHabilidadeEspecial();
        testarReducaoDeVida();
        testarMorte();
        testarAjudaDoMascote();
        testarAtaqueConjunto();
        testarEstatisticasDaTabela();
        testarIntervencaoDaMansao();
        testarRetratosDaMansao();
        testarSelecaoDeInimigo();
        exibirResumoFinal();
    }

    /**
     * Verifica se um ataque comum reduz a vida do adversário.
     */
    private static void testarAtaque() {
        Personagem atacante = criarPersonagem(PersonagemBaseAddams.GOMEZ, TipoMascote.MORCEGO, 1);
        Personagem alvo = criarPersonagem(PersonagemBaseAddams.MORTICIA, TipoMascote.GATO_PRETO, 2);
        int vidaAnterior = alvo.getVida();
        alvo.receberDano(atacante.atacar());
        validar("Ataque reduz a vida do alvo", alvo.getVida() < vidaAnterior);
    }

    /**
     * Verifica se a ação de defender reduz o dano recebido.
     */
    private static void testarDefesa() {
        Personagem atacanteA = criarPersonagem(PersonagemBaseAddams.WANDINHA, TipoMascote.ESCORPIAO, 3);
        Personagem atacanteB = criarPersonagem(PersonagemBaseAddams.WANDINHA, TipoMascote.ESCORPIAO, 3);
        Personagem alvoSemDefesa = criarPersonagem(PersonagemBaseAddams.FEIOSO, TipoMascote.ARANHA, 4);
        Personagem alvoComDefesa = criarPersonagem(PersonagemBaseAddams.FEIOSO, TipoMascote.ARANHA, 4);

        int danoSemDefesa = alvoSemDefesa.receberDano(atacanteA.atacar());
        alvoComDefesa.defender();
        int danoComDefesa = alvoComDefesa.receberDano(atacanteB.atacar());
        validar("Defesa reduz o dano recebido", danoComDefesa < danoSemDefesa);
    }

    /**
     * Verifica se a habilidade especial consome mana e causa efeito.
     */
    private static void testarHabilidadeEspecial() {
        Personagem atacante = criarPersonagem(PersonagemBaseAddams.GOMEZ, TipoMascote.MORCEGO, 10);
        Personagem alvo = criarPersonagem(PersonagemBaseAddams.TROPECO, TipoMascote.ABUTRE, 11);
        int manaAnterior = atacante.getMana();
        int vidaAnterior = alvo.getVida();

        ResultadoHabilidade resultado = atacante.usarHabilidadeEspecial(alvo);
        boolean consumiuMana = atacante.getMana() < manaAnterior;
        boolean causouImpacto = alvo.getVida() < vidaAnterior;
        boolean retornouSucesso = resultado.isSucesso();
        validar("Habilidade especial consome mana e afeta o alvo", consumiuMana && causouImpacto);
        validar("Habilidade especial retorna sucesso", retornouSucesso);
    }

    /**
     * Verifica se a vida nunca fica negativa após receber dano.
     */
    private static void testarReducaoDeVida() {
        Personagem alvo = criarPersonagem(PersonagemBaseAddams.TIO_CHICO, TipoMascote.SERPENTE, 5);
        alvo.receberDano(5000);
        validar("Vida não fica abaixo de zero", alvo.getVida() == 0);
    }

    /**
     * Verifica se o personagem morre corretamente após dano excessivo.
     */
    private static void testarMorte() {
        Personagem alvo = criarPersonagem(PersonagemBaseAddams.MAOZINHA, TipoMascote.SERPENTE, 6);
        alvo.receberDano(5000);
        validar("Personagem morre corretamente", !alvo.estaVivo());
    }

    /**
     * Verifica se a ajuda do mascote gera impacto na batalha.
     */
    private static void testarAjudaDoMascote() {
        Personagem dono = criarPersonagem(PersonagemBaseAddams.WANDINHA, TipoMascote.ESCORPIAO, 7);
        Personagem alvo = criarPersonagem(PersonagemBaseAddams.TROPECO, TipoMascote.ABUTRE, 8);
        int energiaAnterior = dono.getMascote().getEnergia();
        int vidaAnterior = alvo.getVida();

        ResultadoMascote resultado = dono.pedirAjudaDoMascote(alvo, ModoAjudaMascote.OFENSIVO);
        boolean houveEfeito = dono.getMascote().getEnergia() < energiaAnterior || alvo.getVida() < vidaAnterior;
        boolean registrouDano = resultado.getResultadoDano() != null;
        validar("Mascote participa da batalha", houveEfeito);
        validar("Mascote ofensivo registra dano", registrouDano);
    }

    /**
     * Verifica se o ataque conjunto soma dano do personagem e do mascote.
     */
    private static void testarAtaqueConjunto() {
        Personagem atacante = criarPersonagem(PersonagemBaseAddams.GOMEZ, TipoMascote.CORVO, 18);
        Personagem alvo = criarPersonagem(PersonagemBaseAddams.MORTICIA, TipoMascote.GATO_PRETO, 19);
        ResultadoAtaqueConjunto resultado = atacante.atacarComMascote(alvo);
        boolean houveDanoDoPersonagem = resultado.getResultadoPersonagem().getDanoFinal() > 0;
        boolean registrouTotal = resultado.getDanoTotal() >= resultado.getResultadoPersonagem().getDanoFinal();
        validar("Ataque conjunto registra dano do personagem", houveDanoDoPersonagem);
        validar("Ataque conjunto consolida dano total", registrouTotal);
    }

    /**
     * Verifica se as estatísticas acumuladas são preenchidas.
     */
    private static void testarEstatisticasDaTabela() {
        Personagem jogador = criarPersonagem(PersonagemBaseAddams.FEIOSO, TipoMascote.ARANHA, 20);
        Personagem inimigo = criarPersonagem(PersonagemBaseAddams.TROPECO, TipoMascote.ABUTRE, 21);

        jogador.registrarRodadaParticipada();
        inimigo.registrarRodadaParticipada();
        jogador.defender();
        inimigo.executarAtaqueBasico(jogador);
        jogador.pedirAjudaDoMascote(inimigo, ModoAjudaMascote.DEFENSIVO);

        boolean rodadaRegistrada = jogador.getRodadasParticipadas() == 1;
        boolean bloqueioRegistrado = jogador.getMaiorBloqueioPersonagem() > 0;
        boolean suporteRegistrado = jogador.getMaiorSuporteMascote() > 0;
        validar("Tabela registra quantidade de rodadas", rodadaRegistrada);
        validar("Tabela registra maior bloqueio do personagem", bloqueioRegistrado);
        validar("Tabela registra maior suporte do mascote", suporteRegistrado);
    }

    /**
     * Verifica se a mansão pode aplicar um evento com efeito real.
     */
    private static void testarIntervencaoDaMansao() {
        IntervencaoMansao intervencao = new IntervencaoMansao(new Random(12));
        Personagem jogador = criarPersonagem(PersonagemBaseAddams.GOMEZ, TipoMascote.CORVO, 13);
        Personagem inimigo = criarPersonagem(PersonagemBaseAddams.MORTICIA, TipoMascote.GATO_PRETO, 14);

        ResultadoIntervencaoMansao resultado = intervencao.aplicarEvento(EventoMansao.BRISA_GELADA,
                jogador, inimigo);
        boolean houveDefesa = jogador.getBonusDefesaTemporario() > 0
                || inimigo.getBonusDefesaTemporario() > 0;
        validar("Intervenção da mansão aplica efeito na rodada", resultado.isOcorreu() && houveDefesa);
    }

    /**
     * Verifica se a mansão consegue afetar a energia de um mascote.
     */
    private static void testarRetratosDaMansao() {
        IntervencaoMansao intervencao = new IntervencaoMansao(new Random(15));
        Personagem jogador = criarPersonagem(PersonagemBaseAddams.WANDINHA, TipoMascote.ESCORPIAO, 16);
        Personagem inimigo = criarPersonagem(PersonagemBaseAddams.TROPECO, TipoMascote.ABUTRE, 17);
        int energiaJogador = jogador.getMascote().getEnergia();
        int energiaInimigo = inimigo.getMascote().getEnergia();

        intervencao.aplicarEvento(EventoMansao.RETRATOS_VIGIANDO, jogador, inimigo);
        boolean energiaMudou = jogador.getMascote().getEnergia() < energiaJogador
                || inimigo.getMascote().getEnergia() < energiaInimigo;
        validar("Retratos da mansão reduzem energia de mascote", energiaMudou);
    }

    /**
     * Verifica se o sistema não sorteia o mesmo personagem do jogador.
     */
    private static void testarSelecaoDeInimigo() {
        GeradorPersonagem gerador = new GeradorPersonagem(new Random(9));
        Personagem jogador = gerador.criarPersonagem(PersonagemBaseAddams.GOMEZ);
        Personagem inimigo = gerador.criarInimigoAleatorio(jogador);
        validar("Inimigo sorteado é diferente do jogador", inimigo.getBase() != jogador.getBase());
    }

    /**
     * Cria personagens com sementes fixas para testes previsíveis.
     */
    private static Personagem criarPersonagem(PersonagemBaseAddams base,
                                              TipoMascote tipoMascote, int semente) {
        return new Personagem(base, new Mascote(tipoMascote), new Random(semente));
    }

    /**
     * Registra o resultado de cada teste de forma legível no console.
     */
    private static void validar(String nomeDoTeste, boolean condicao) {
        if (condicao) {
            aprovados++;
            System.out.println("[APROVADO] " + nomeDoTeste);
            return;
        }

        reprovados++;
        System.out.println("[REPROVADO] " + nomeDoTeste);
    }

    /**
     * Mostra o resumo final da execução dos testes.
     */
    private static void exibirResumoFinal() {
        System.out.println();
        System.out.println("Aprovados: " + aprovados);
        System.out.println("Reprovados: " + reprovados);
    }
}
