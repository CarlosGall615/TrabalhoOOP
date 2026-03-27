package br.com.unipar.atividade;

public enum PersonagemBaseAddams {

    // Este enum representa o catálogo fixo de personagens disponíveis para seleção.
    GOMEZ("Gomez Addams", ClassePersonagem.PATRIARCA_DA_MANSAO, TipoMascote.MORCEGO_DUQUE, 3, 4, 3,
            "Líder ousado da família, sempre elegante e competitivo."),
    MORTICIA("Morticia Addams", ClassePersonagem.DAMA_DA_ELEGANCIA_SOMBRIA, TipoMascote.CORVO_OFELIA, 3, 3, 4,
            "Mantém a calma da mansão com charme sombrio e presença firme."),
    WANDINHA("Wandinha Addams", ClassePersonagem.HERDEIRA_DO_MACABRO, TipoMascote.ARANHA_NERO, 4, 5, 2,
            "Observadora, inteligente e precisa em cada movimento."),
    FEIOSO("Feioso Addams", ClassePersonagem.ARTILHEIRO_DO_CAOS_CASEIRO, TipoMascote.POLVO_ARISTOTELES, 4, 4, 2,
            "Transforma brincadeiras perigosas em vantagem no combate."),
    TIO_CHICO("Tio Chico", ClassePersonagem.CIENTISTA_DAS_ENGRENAGENS, TipoMascote.ENGUIA_VOLTAICA, 3, 4, 3,
            "Usa invenções e eletricidade para surpreender qualquer rival."),
    VOVO("Vovó Addams", ClassePersonagem.MATRIARCA_DAS_RECEITAS_OCULTAS, TipoMascote.GATO_ESMERALDA, 2, 3, 5,
            "Mistura sabedoria, poções e segredos antigos da família."),
    TROPECO("Tropeço", ClassePersonagem.SENTINELA_DA_PORTA, TipoMascote.CAO_SOMBRIO, 2, 2, 6,
            "Protege a mansão com força, disciplina e presença imponente."),
    PRIMO_ITT("Primo Itt", ClassePersonagem.NOBRE_DO_CAOS_CABELUDO, TipoMascote.DONINHA_LUXO, 3, 5, 2,
            "É imprevisível, veloz e sempre parece escapar das regras comuns.");

    private final String nomeExibicao;
    private final ClassePersonagem classePersonagem;
    private final TipoMascote tipoMascote;
    private final int bonusVida;
    private final int bonusForca;
    private final int bonusDefesa;
    private final String descricao;

    PersonagemBaseAddams(String nomeExibicao, ClassePersonagem classePersonagem, TipoMascote tipoMascote,
                         int bonusVida, int bonusForca, int bonusDefesa, String descricao) {
        this.nomeExibicao = nomeExibicao;
        this.classePersonagem = classePersonagem;
        this.tipoMascote = tipoMascote;
        this.bonusVida = bonusVida;
        this.bonusForca = bonusForca;
        this.bonusDefesa = bonusDefesa;
        this.descricao = descricao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public ClassePersonagem getClassePersonagem() {
        return classePersonagem;
    }

    public TipoMascote getTipoMascote() {
        return tipoMascote;
    }

    public int getBonusVida() {
        return bonusVida;
    }

    public int getBonusForca() {
        return bonusForca;
    }

    public int getBonusDefesa() {
        return bonusDefesa;
    }

    public String getDescricao() {
        return descricao;
    }
}
