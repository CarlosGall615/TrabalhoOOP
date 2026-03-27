package br.com.unipar.atividade;

/**
 * Representa os eventos aleatórios causados pela mansão mal-assombrada.
 */
public enum EventoMansao {

    ARMADURA_DESPENCANDO("Armadura Despencando"),
    SUSSURROS_DA_MANSAO("Sussurros da Mansão"),
    BRISA_GELADA("Brisa Gelada"),
    PORTA_BATENDO("Porta Batendo"),
    RETRATOS_VIGIANDO("Retratos Vigiando"),
    VELAS_TREMULANDO("Velas Tremulando");

    /**
     * Nome exibido no bloco de intervenção da mansão.
     */
    private final String nomeExibicao;

    EventoMansao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }
}
