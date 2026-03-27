package br.com.unipar.atividade;

/**
 * Representa o efeito de uma ajuda ofensiva ou defensiva do mascote.
 */
public class ResultadoMascote {

    private final String mensagem;
    private final ResultadoDano resultadoDano;
    private final int bonusDefesaAplicado;
    private final int vidaRecuperada;
    private final int manaRecuperada;
    private final int energiaRestante;

    public ResultadoMascote(String mensagem, ResultadoDano resultadoDano,
                            int bonusDefesaAplicado, int vidaRecuperada,
                            int manaRecuperada, int energiaRestante) {
        this.mensagem = mensagem;
        this.resultadoDano = resultadoDano;
        this.bonusDefesaAplicado = bonusDefesaAplicado;
        this.vidaRecuperada = vidaRecuperada;
        this.manaRecuperada = manaRecuperada;
        this.energiaRestante = energiaRestante;
    }

    public String getMensagem() {
        return mensagem;
    }

    public ResultadoDano getResultadoDano() {
        return resultadoDano;
    }

    public int getBonusDefesaAplicado() {
        return bonusDefesaAplicado;
    }

    public int getVidaRecuperada() {
        return vidaRecuperada;
    }

    public int getManaRecuperada() {
        return manaRecuperada;
    }

    public int getEnergiaRestante() {
        return energiaRestante;
    }
}
