package br.com.unipar.atividade;

/**
 * Define os tipos narrativos inspirados na Família Addams.
 */
public enum ClassePersonagem {

    PATRIARCA_SOMBRIO("Patriarca Sombrio",
            "Lidera a família com ousadia e presença ofensiva.",
            new TipoMascote[]{TipoMascote.MORCEGO, TipoMascote.CORVO}),
    DAMA_MACABRA("Dama Macabra",
            "Combina elegância, controle emocional e estratégia.",
            new TipoMascote[]{TipoMascote.CORUJA_SOMBRIA, TipoMascote.GATO_PRETO}),
    HERDEIRA_SOMBRIA("Herdeira Sombria",
            "Prefere precisão, sangue-frio e ataques diretos.",
            new TipoMascote[]{TipoMascote.ARANHA, TipoMascote.ESCORPIAO}),
    TRAVESSO_DO_CAOS("Travesso do Caos",
            "Usa truques, improviso e pressão constante.",
            new TipoMascote[]{TipoMascote.ARANHA, TipoMascote.MORCEGO}),
    TIO_EXCENTRICO("Tio Excêntrico",
            "Transforma engenhocas estranhas em vantagem no combate.",
            new TipoMascote[]{TipoMascote.SERPENTE, TipoMascote.CORVO}),
    CRIATURA_LEAL("Criatura Leal",
            "Resiste mais que os outros e protege a casa com firmeza.",
            new TipoMascote[]{TipoMascote.ABUTRE, TipoMascote.CORUJA_SOMBRIA}),
    MENSAGEIRO_SORRATEIRO("Mensageiro Sorrateiro",
            "É rápido, discreto e confunde o oponente.",
            new TipoMascote[]{TipoMascote.ESCORPIAO, TipoMascote.SERPENTE});

    /**
     * Texto exibido ao jogador no menu e no status.
     */
    private final String descricao;

    /**
     * Explicação resumida do papel narrativo da classe.
     */
    private final String papelNarrativo;

    /**
     * Lista fixa de mascotes que podem ser usados por essa classe.
     */
    private final TipoMascote[] mascotesPermitidos;

    ClassePersonagem(String descricao, String papelNarrativo,
                     TipoMascote[] mascotesPermitidos) {
        this.descricao = descricao;
        this.papelNarrativo = papelNarrativo;
        this.mascotesPermitidos = mascotesPermitidos;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPapelNarrativo() {
        return papelNarrativo;
    }

    public TipoMascote[] getMascotesPermitidos() {
        return mascotesPermitidos.clone();
    }

    /**
     * Valida se o mascote escolhido é compatível com a classe.
     */
    public boolean aceitaMascote(TipoMascote tipoMascote) {
        for (TipoMascote permitido : mascotesPermitidos) {
            if (permitido == tipoMascote) {
                return true;
            }
        }
        return false;
    }

    /**
     * Monta a lista de mascotes permitidos em formato amigável.
     */
    public String listarMascotesPermitidos() {
        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < mascotesPermitidos.length; i++) {
            if (i > 0) {
                texto.append(", ");
            }
            texto.append(mascotesPermitidos[i].getNomeExibicao());
        }

        return texto.toString();
    }
}
