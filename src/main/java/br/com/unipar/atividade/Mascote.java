package br.com.unipar.atividade;

import java.util.Random;

/**
 * Representa o mascote associado a um personagem da batalha.
 */
public class Mascote {

    /**
     * Define o tipo do mascote e seus bônus fixos.
     */
    private final TipoMascote tipo;

    /**
     * Energia atual do mascote, consumida ao ajudar.
     */
    private int energia;

    /**
     * Valor máximo de energia para recuperação entre turnos.
     */
    private final int energiaMaxima;

    public Mascote(TipoMascote tipo) {
        this.tipo = tipo;
        this.energia = 100;
        this.energiaMaxima = 100;
    }

    public TipoMascote getTipo() {
        return tipo;
    }

    public int getEnergia() {
        return energia;
    }

    /**
     * Verifica se o mascote possui energia suficiente para agir.
     */
    public boolean podeAgir() {
        return energia >= tipo.getCustoEnergia();
    }

    /**
     * Verifica se o mascote tem energia para participar de um ataque conjunto.
     */
    public boolean podeAtacarEmConjunto() {
        return energia >= getCustoAtaqueConjunto();
    }

    /**
     * Executa a ajuda do mascote com ataque, defesa e recuperação.
     */
    public ResultadoMascote usarHabilidade(Personagem dono, Personagem alvo,
                                           Random random, ModoAjudaMascote modo) {
        if (!podeAgir()) {
            return criarResultadoSemAcao(tipo.getNomeExibicao()
                    + " está exausto e não conseguiu entrar em ação.");
        }

        energia -= tipo.getCustoEnergia();
        if (modo == ModoAjudaMascote.OFENSIVO) {
            return executarAjudaOfensiva(alvo, random);
        }
        return executarAjudaDefensiva(dono);
    }

    /**
     * Executa um ataque em conjunto com custo menor que a ação solo.
     */
    public ResultadoDano executarAtaqueConjunto(Personagem alvo, Random random) {
        if (!podeAtacarEmConjunto() || alvo == null || !alvo.estaVivo()) {
            return null;
        }

        energia -= getCustoAtaqueConjunto();
        int danoBase = Math.max(12, (tipo.getBonusAtaque() / 2) + random.nextInt(11));
        return alvo.receberDanoDetalhado(danoBase);
    }

    /**
     * Informa a participação do mascote no ataque conjunto.
     */
    public String getMensagemAtaqueConjunto() {
        return tipo.getNomeExibicao() + " acompanhou o movimento com "
                + tipo.getNomeHabilidade() + ".";
    }

    /**
     * Cria um retorno padronizado quando o mascote não consegue agir.
     */
    private ResultadoMascote criarResultadoSemAcao(String mensagem) {
        return new ResultadoMascote(mensagem, null, 0, 0, 0, energia);
    }

    /**
     * Calcula o custo reduzido do ataque conjunto.
     */
    private int getCustoAtaqueConjunto() {
        return Math.max(8, tipo.getCustoEnergia() - 6);
    }

    /**
     * Faz o mascote atacar o oponente e narrar o resultado.
     */
    private ResultadoMascote executarAjudaOfensiva(Personagem alvo, Random random) {
        if (alvo == null || !alvo.estaVivo() || tipo.getBonusAtaque() <= 0) {
            return criarResultadoSemAcao(tipo.getNomeExibicao()
                    + " circulou pelo campo, mas não encontrou abertura.");
        }

        int danoBase = tipo.getBonusAtaque() + random.nextInt(16);
        ResultadoDano resultadoDano = alvo.receberDanoDetalhado(danoBase);
        String mensagem = tipo.getNomeExibicao() + " entrou em cena com "
                + tipo.getNomeHabilidade() + ".";
        return new ResultadoMascote(mensagem, resultadoDano, 0, 0, 0, energia);
    }

    /**
     * Faz o mascote proteger, curar e recuperar recursos do dono.
     */
    private ResultadoMascote executarAjudaDefensiva(Personagem dono) {
        int bonusDefesa = aplicarDefesa(dono);
        int vidaRecuperada = aplicarVida(dono);
        int manaRecuperada = aplicarMana(dono);
        String mensagem = tipo.getNomeExibicao() + " protegeu "
                + dono.getNome() + " com " + tipo.getNomeHabilidade() + ".";
        return new ResultadoMascote(mensagem, null, bonusDefesa,
                vidaRecuperada, manaRecuperada, energia);
    }

    /**
     * Concede defesa temporária ao personagem quando o mascote protege.
     */
    private int aplicarDefesa(Personagem dono) {
        if (tipo.getBonusDefesa() <= 0) {
            return 0;
        }

        dono.aplicarBonusDefesa(tipo.getBonusDefesa());
        return tipo.getBonusDefesa();
    }

    /**
     * Recupera vida do dono quando o mascote tem essa função.
     */
    private int aplicarVida(Personagem dono) {
        if (tipo.getRecuperacaoVida() <= 0) {
            return 0;
        }

        return dono.recuperarVida(tipo.getRecuperacaoVida());
    }

    /**
     * Recupera mana do dono quando o mascote tem essa função.
     */
    private int aplicarMana(Personagem dono) {
        if (tipo.getRecuperacaoMana() <= 0) {
            return 0;
        }

        return dono.recuperarMana(tipo.getRecuperacaoMana());
    }

    /**
     * Regenera energia ao final do turno para manter o equilíbrio.
     */
    public void recuperarEnergia() {
        energia += 12;
        if (energia > energiaMaxima) {
            energia = energiaMaxima;
        }
    }

    /**
     * Reduz a energia do mascote sem deixar o valor negativo.
     */
    public int reduzirEnergia(int valor) {
        if (valor <= 0) {
            return 0;
        }

        int energiaAnterior = energia;
        energia -= valor;
        if (energia < 0) {
            energia = 0;
        }
        return energiaAnterior - energia;
    }

    /**
     * Resume o estado do mascote para exibição acadêmica.
     */
    public String getResumo() {
        return tipo.getNomeExibicao()
                + " | energia: " + energia
                + " | papel: " + tipo.getDescricao();
    }
}
