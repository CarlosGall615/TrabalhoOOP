package br.com.unipar.atividade;

/**
 * Guarda o resultado de uma intervenção da mansão.
 */
public class ResultadoIntervencaoMansao {

    private final boolean ocorreu;
    private final EventoMansao evento;
    private final String mensagem;

    public ResultadoIntervencaoMansao(boolean ocorreu, EventoMansao evento,
                                      String mensagem) {
        this.ocorreu = ocorreu;
        this.evento = evento;
        this.mensagem = mensagem;
    }

    public boolean isOcorreu() {
        return ocorreu;
    }

    public EventoMansao getEvento() {
        return evento;
    }

    public String getMensagem() {
        return mensagem;
    }
}
