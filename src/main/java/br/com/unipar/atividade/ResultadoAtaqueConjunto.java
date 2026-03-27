package br.com.unipar.atividade;

/**
 * Armazena o resultado de um ataque em conjunto entre personagem e mascote.
 */
public class ResultadoAtaqueConjunto {

    private final ResultadoDano resultadoPersonagem;
    private final ResultadoDano resultadoMascote;
    private final String mensagemMascote;
    private final int danoTotal;
    private final int energiaRestanteMascote;

    public ResultadoAtaqueConjunto(ResultadoDano resultadoPersonagem,
                                   ResultadoDano resultadoMascote,
                                   String mensagemMascote, int danoTotal,
                                   int energiaRestanteMascote) {
        this.resultadoPersonagem = resultadoPersonagem;
        this.resultadoMascote = resultadoMascote;
        this.mensagemMascote = mensagemMascote;
        this.danoTotal = danoTotal;
        this.energiaRestanteMascote = energiaRestanteMascote;
    }

    public ResultadoDano getResultadoPersonagem() {
        return resultadoPersonagem;
    }

    public ResultadoDano getResultadoMascote() {
        return resultadoMascote;
    }

    public String getMensagemMascote() {
        return mensagemMascote;
    }

    public int getDanoTotal() {
        return danoTotal;
    }

    public int getEnergiaRestanteMascote() {
        return energiaRestanteMascote;
    }
}
