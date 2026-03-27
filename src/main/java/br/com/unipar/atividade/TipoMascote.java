package br.com.unipar.atividade;

/**
 * Enum com os mascotes temáticos permitidos no universo do jogo.
 */
public enum TipoMascote {

    CORVO("Corvo", "Rasante Sombrio", 55, 10, 0, 10, 20,
            "Ataca do alto e ajuda o dono a enxergar oportunidades."),
    ARANHA("Aranha", "Teia de Proteção", 35, 30, 0, 0, 18,
            "Cria uma defesa extra e atrapalha a aproximação do rival."),
    MORCEGO("Morcego", "Voo Intimidador", 45, 15, 0, 15, 20,
            "Ataca em rasante e renova a coragem do personagem."),
    GATO_PRETO("Gato Preto", "Passo Silencioso", 20, 10, 45, 20, 18,
            "Recupera vida e mana com discrição elegante."),
    ABUTRE("Abutre", "Investida do Céu", 60, 20, 0, 0, 22,
            "Golpeia com força e abre espaço para um novo ataque."),
    SERPENTE("Serpente", "Bote Preciso", 50, 10, 0, 20, 20,
            "Ataca rápido e ajuda a recuperar o foco do dono."),
    ESCORPIAO("Escorpião", "Ferroada Cruel", 65, 5, 0, 0, 22,
            "É agressivo e causa grande impacto em um único lance."),
    CORUJA_SOMBRIA("Coruja Sombria", "Guia da Noite", 30, 20, 20, 25, 18,
            "Equilibra proteção, recuperação e ataque leve.");

    /**
     * Nome amigável apresentado no console.
     */
    private final String nomeExibicao;

    /**
     * Nome da habilidade exclusiva do mascote.
     */
    private final String nomeHabilidade;

    /**
     * Valor bruto de ataque causado pelo mascote.
     */
    private final int bonusAtaque;

    /**
     * Valor de defesa temporária concedido ao dono.
     */
    private final int bonusDefesa;

    /**
     * Quantidade de vida restaurada ao dono.
     */
    private final int recuperacaoVida;

    /**
     * Quantidade de mana restaurada ao dono.
     */
    private final int recuperacaoMana;

    /**
     * Custo de energia do mascote para agir.
     */
    private final int custoEnergia;

    /**
     * Texto didático sobre o papel do mascote.
     */
    private final String descricao;

    TipoMascote(String nomeExibicao, String nomeHabilidade, int bonusAtaque,
                int bonusDefesa, int recuperacaoVida, int recuperacaoMana,
                int custoEnergia, String descricao) {
        this.nomeExibicao = nomeExibicao;
        this.nomeHabilidade = nomeHabilidade;
        this.bonusAtaque = bonusAtaque;
        this.bonusDefesa = bonusDefesa;
        this.recuperacaoVida = recuperacaoVida;
        this.recuperacaoMana = recuperacaoMana;
        this.custoEnergia = custoEnergia;
        this.descricao = descricao;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public String getNomeHabilidade() {
        return nomeHabilidade;
    }

    public int getBonusAtaque() {
        return bonusAtaque;
    }

    public int getBonusDefesa() {
        return bonusDefesa;
    }

    public int getRecuperacaoVida() {
        return recuperacaoVida;
    }

    public int getRecuperacaoMana() {
        return recuperacaoMana;
    }

    public int getCustoEnergia() {
        return custoEnergia;
    }

    public String getDescricao() {
        return descricao;
    }
}
