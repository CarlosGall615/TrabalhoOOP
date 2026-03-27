package br.com.unipar.atividade;

/**
 * Armazena o efeito completo de uma habilidade especial.
 */
public class ResultadoHabilidade {

    private final boolean sucesso;
    private final String mensagem;
    private final ResultadoDano resultadoDano;
    private final int vidaRecuperada;
    private final int bonusDefesaAplicado;
    private final int manaRestante;

    public ResultadoHabilidade(boolean sucesso, String mensagem,
                               ResultadoDano resultadoDano, int vidaRecuperada,
                               int bonusDefesaAplicado, int manaRestante) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.resultadoDano = resultadoDano;
        this.vidaRecuperada = vidaRecuperada;
        this.bonusDefesaAplicado = bonusDefesaAplicado;
        this.manaRestante = manaRestante;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public ResultadoDano getResultadoDano() {
        return resultadoDano;
    }

    public int getVidaRecuperada() {
        return vidaRecuperada;
    }

    public int getBonusDefesaAplicado() {
        return bonusDefesaAplicado;
    }

    public int getManaRestante() {
        return manaRestante;
    }
}
