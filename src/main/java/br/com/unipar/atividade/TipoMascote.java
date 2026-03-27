package br.com.unipar.atividade;

public enum TipoMascote {

    // Cada mascote tem atributos completos para demonstrar melhor a interação entre objetos.
    MORCEGO_DUQUE("Morcego Duque", "Rasante Aristocrático", 42, 8, 4, 2, 2, 12, 10, 16, 2),
    CORVO_OFELIA("Corvo Ofélia", "Asas de Veludo", 44, 9, 3, 3, 1, 10, 12, 15, 3),
    ARANHA_NERO("Aranha Nero", "Teia Venenosa", 38, 7, 5, 1, 3, 11, 9, 18, 1),
    POLVO_ARISTOTELES("Polvo Aristóteles", "Combo de Tentáculos", 48, 10, 4, 2, 2, 13, 8, 17, 2),
    ENGUIA_VOLTAICA("Enguia Voltaica", "Descarga do Subsolo", 40, 6, 5, 1, 3, 12, 11, 19, 1),
    GATO_ESMERALDA("Gato Esmeralda", "Miado Arcano", 46, 11, 2, 4, 1, 9, 13, 14, 4),
    CAO_SOMBRIO("Cão Sombrio", "Investida do Guardião", 52, 12, 4, 3, 1, 14, 8, 16, 2),
    DONINHA_LUXO("Doninha de Luxo", "Giro Desconcertante", 41, 8, 3, 2, 4, 10, 10, 15, 2);

    private final String nomeExibicao;
    private final String nomeSkill;
    private final int vidaBase;
    private final int armaduraBase;
    private final int bonusAtaque;
    private final int bonusDefesa;
    private final int comboInicial;
    private final int vigorBase;
    private final int manaBase;
    private final int poderSkill;
    private final int curaAoDono;

    TipoMascote(String nomeExibicao, String nomeSkill, int vidaBase, int armaduraBase, int bonusAtaque,
                int bonusDefesa, int comboInicial, int vigorBase, int manaBase, int poderSkill, int curaAoDono) {
        this.nomeExibicao = nomeExibicao;
        this.nomeSkill = nomeSkill;
        this.vidaBase = vidaBase;
        this.armaduraBase = armaduraBase;
        this.bonusAtaque = bonusAtaque;
        this.bonusDefesa = bonusDefesa;
        this.comboInicial = comboInicial;
        this.vigorBase = vigorBase;
        this.manaBase = manaBase;
        this.poderSkill = poderSkill;
        this.curaAoDono = curaAoDono;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public String getNomeSkill() {
        return nomeSkill;
    }

    public int getVidaBase() {
        return vidaBase;
    }

    public int getArmaduraBase() {
        return armaduraBase;
    }

    public int getBonusAtaque() {
        return bonusAtaque;
    }

    public int getBonusDefesa() {
        return bonusDefesa;
    }

    public int getComboInicial() {
        return comboInicial;
    }

    public int getVigorBase() {
        return vigorBase;
    }

    public int getManaBase() {
        return manaBase;
    }

    public int getPoderSkill() {
        return poderSkill;
    }

    public int getCuraAoDono() {
        return curaAoDono;
    }
}
