package br.com.unipar.atividade;

public enum CenarioCasa {

    // O cenário da mansão pode ajudar ou atrapalhar, como pedido no enunciado.
    SALA_DAS_ARMADURAS("Sala das Armaduras", 3, 0, "as armaduras antigas intimidam o oponente"),
    BIBLIOTECA_SOMBRIA("Biblioteca Sombria", 0, 2, "os livros antigos ajudam na concentração defensiva"),
    LABORATORIO_DO_TIO_CHICO("Laboratório do Tio Chico", 2, 0, "as invenções malucas aumentam o impacto do ataque"),
    JARDIM_CARNIVORO("Jardim Carnívoro", -2, -1, "as plantas assustadoras atrapalham os dois lados"),
    SOTAO_ASSOMBRADO("Sótão Assombrado", 1, -1, "os ruídos estranhos aceleram o ataque, mas tiram estabilidade");

    private final String nomeExibicao;
    private final int bonusAtaque;
    private final int bonusDefesa;
    private final String descricao;

    CenarioCasa(String nomeExibicao, int bonusAtaque, int bonusDefesa, String descricao) {
        this.nomeExibicao = nomeExibicao;
        this.bonusAtaque = bonusAtaque;
        this.bonusDefesa = bonusDefesa;
        this.descricao = descricao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public int getBonusAtaque() {
        return bonusAtaque;
    }

    public int getBonusDefesa() {
        return bonusDefesa;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return nomeExibicao;
    }
}
