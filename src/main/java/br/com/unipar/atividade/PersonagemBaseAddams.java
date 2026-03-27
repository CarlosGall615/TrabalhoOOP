package br.com.unipar.atividade;

/**
 * Catálogo fixo com os personagens jogáveis do trabalho.
 */
public enum PersonagemBaseAddams {

    GOMEZ("Gomez Addams", ClassePersonagem.PATRIARCA_SOMBRIO, 165, 70, 120, 1,
            "Terno listrado com florete cerimonial", "Duelar com Estilo",
            30, 100, 0, 20,
            "É carismático, ofensivo e gosta de decidir a luta no ritmo dele."),
    MORTICIA("Morticia Addams", ClassePersonagem.DAMA_MACABRA, 150, 85, 160, 1,
            "Vestido negro elegante e postura impecável", "Passo Frio e Preciso",
            35, 90, 40, 25,
            "Mantém o controle da batalha e responde com calma estratégica."),
    WANDINHA("Wandinha Addams", ClassePersonagem.HERDEIRA_SOMBRIA, 170, 60, 140, 1,
            "Uniforme sombrio e tranças firmes", "Plano Calculado",
            30, 110, 0, 15,
            "Ataca com precisão cruel e pouca demonstração de emoção."),
    FEIOSO("Feioso Addams", ClassePersonagem.TRAVESSO_DO_CAOS, 145, 80, 130, 1,
            "Roupa listrada com bolsos cheios de truques", "Armadilha Caseira",
            25, 95, 0, 30,
            "É resistente e transforma travessuras em vantagem prática."),
    TIO_CHICO("Tio Chico", ClassePersonagem.TIO_EXCENTRICO, 155, 65, 180, 1,
            "Casaco puído e lâmpada de bolso", "Choque Improvisado",
            40, 105, 20, 10,
            "É imprevisível e usa invenções malucas para pressionar o rival."),
    TROPECO("Tropeço", ClassePersonagem.CRIATURA_LEAL, 135, 95, 110, 1,
            "Uniforme reforçado do mordomo da mansão", "Postura de Guarda",
            25, 70, 0, 45,
            "Protege a casa com grande resistência e disciplina."),
    MAOZINHA("Mãozinha", ClassePersonagem.MENSAGEIRO_SORRATEIRO, 160, 55, 150, 1,
            "Luva escura e movimentos silenciosos", "Golpe Rasteiro",
            20, 100, 0, 10,
            "É pequena, veloz e muito eficiente quando pega o oponente desprevenido.");

    /**
     * Nome do personagem usado em todas as saídas do console.
     */
    private final String nome;

    /**
     * Classe narrativa que define o papel do personagem.
     */
    private final ClassePersonagem classePersonagem;

    /**
     * Força base do personagem para ataques comuns.
     */
    private final int forca;

    /**
     * Defesa base usada na redução de dano.
     */
    private final int defesa;

    /**
     * Mana inicial disponível para habilidades especiais.
     */
    private final int mana;

    /**
     * Nível inicial do personagem no trabalho acadêmico.
     */
    private final int nivel;

    /**
     * Roupa ou armadura temática do personagem.
     */
    private final String armaduraRoupa;

    /**
     * Nome da habilidade exclusiva do personagem.
     */
    private final String habilidadeExclusiva;

    /**
     * Quantidade de mana consumida pela habilidade especial.
     */
    private final int custoHabilidade;

    /**
     * Dano extra causado pela habilidade especial.
     */
    private final int bonusDanoHabilidade;

    /**
     * Vida recuperada ao usar a habilidade especial.
     */
    private final int curaHabilidade;

    /**
     * Defesa adicional recebida ao usar a habilidade especial.
     */
    private final int bonusDefesaHabilidade;

    /**
     * Descrição didática do estilo de jogo do personagem.
     */
    private final String descricao;

    PersonagemBaseAddams(String nome, ClassePersonagem classePersonagem, int forca,
                         int defesa, int mana, int nivel, String armaduraRoupa,
                         String habilidadeExclusiva, int custoHabilidade,
                         int bonusDanoHabilidade, int curaHabilidade,
                         int bonusDefesaHabilidade, String descricao) {
        this.nome = nome;
        this.classePersonagem = classePersonagem;
        this.forca = forca;
        this.defesa = defesa;
        this.mana = mana;
        this.nivel = nivel;
        this.armaduraRoupa = armaduraRoupa;
        this.habilidadeExclusiva = habilidadeExclusiva;
        this.custoHabilidade = custoHabilidade;
        this.bonusDanoHabilidade = bonusDanoHabilidade;
        this.curaHabilidade = curaHabilidade;
        this.bonusDefesaHabilidade = bonusDefesaHabilidade;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public ClassePersonagem getClassePersonagem() {
        return classePersonagem;
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getMana() {
        return mana;
    }

    public int getNivel() {
        return nivel;
    }

    public String getArmaduraRoupa() {
        return armaduraRoupa;
    }

    public String getHabilidadeExclusiva() {
        return habilidadeExclusiva;
    }

    public int getCustoHabilidade() {
        return custoHabilidade;
    }

    public int getBonusDanoHabilidade() {
        return bonusDanoHabilidade;
    }

    public int getCuraHabilidade() {
        return curaHabilidade;
    }

    public int getBonusDefesaHabilidade() {
        return bonusDefesaHabilidade;
    }

    public String getDescricao() {
        return descricao;
    }
}
