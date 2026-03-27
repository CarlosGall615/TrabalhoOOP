package br.com.unipar.atividade;

public enum ClassePersonagem {

    // As classes foram pensadas com base no papel de cada figura dentro do universo Addams.
    PATRIARCA_DA_MANSAO("Patriarca da Mansão", 135, 24, 16,
            "Representa a liderança apaixonada e impulsiva da família."),
    DAMA_DA_ELEGANCIA_SOMBRIA("Dama da Elegância Sombria", 128, 22, 18,
            "Simboliza presença marcante, serenidade e controle da mansão."),
    HERDEIRA_DO_MACABRO("Herdeira do Macabro", 118, 27, 13,
            "Une inteligência fria, ironia e precisão sombria."),
    ARTILHEIRO_DO_CAOS_CASEIRO("Artilheiro do Caos Caseiro", 126, 25, 14,
            "Representa travessuras explosivas e energia imprevisível."),
    CIENTISTA_DAS_ENGRENAGENS("Cientista das Engrenagens", 122, 23, 15,
            "Reúne invenção, eletricidade e criatividade excêntrica."),
    MATRIARCA_DAS_RECEITAS_OCULTAS("Matriarca das Receitas Ocultas", 132, 21, 19,
            "Expressa sabedoria antiga, poções e tradição misteriosa."),
    SENTINELA_DA_PORTA("Sentinela da Porta", 145, 20, 21,
            "Representa proteção silenciosa, força e lealdade à casa."),
    NOBRE_DO_CAOS_CABELUDO("Nobre do Caos Cabeludo", 124, 24, 15,
            "Transforma extravagância e surpresa em vantagem na batalha.");

    private final String descricao;
    private final int vidaBase;
    private final int forcaBase;
    private final int defesaBase;
    private final String contextoNarrativo;

    ClassePersonagem(String descricao, int vidaBase, int forcaBase, int defesaBase, String contextoNarrativo) {
        this.descricao = descricao;
        this.vidaBase = vidaBase;
        this.forcaBase = forcaBase;
        this.defesaBase = defesaBase;
        this.contextoNarrativo = contextoNarrativo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getVidaBase() {
        return vidaBase;
    }

    public int getForcaBase() {
        return forcaBase;
    }

    public int getDefesaBase() {
        return defesaBase;
    }

    public String getContextoNarrativo() {
        return contextoNarrativo;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
