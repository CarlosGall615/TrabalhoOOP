package br.com.unipar.atividade;

import java.util.Random;

/**
 * Representa um personagem jogável com encapsulamento e comportamento próprio.
 */
public class Personagem {

    /**
     * Vida padrão exigida no enunciado do trabalho.
     */
    private static final int VIDA_INICIAL = 1000;

    /**
     * Base fixa do personagem escolhida no catálogo Addams.
     */
    private final PersonagemBaseAddams base;

    /**
     * Nome exibido no console.
     */
    private final String nome;

    /**
     * Classe narrativa inspirada no universo da Família Addams.
     */
    private final ClassePersonagem classePersonagem;

    /**
     * Roupa ou armadura que compõe a identidade do personagem.
     */
    private final String armaduraRoupa;

    /**
     * Nome da habilidade exclusiva do personagem.
     */
    private final String habilidadeExclusiva;

    /**
     * Mascote associado ao personagem.
     */
    private final Mascote mascote;

    /**
     * Gerador aleatório usado para pequenas variações do combate.
     */
    private final Random random;

    /**
     * Estado de vida, força, defesa, mana e nível do personagem.
     */
    private int vida;
    private int forca;
    private int defesa;
    private int mana;
    private int nivel;

    /**
     * Bônus temporários aplicados por defesa ou mascote.
     */
    private int bonusDefesaTemporario;
    private boolean defendendo;

    public Personagem(PersonagemBaseAddams base, Mascote mascote, Random random) {
        validarMascote(base, mascote);
        this.base = base;
        this.nome = base.getNome();
        this.classePersonagem = base.getClassePersonagem();
        this.armaduraRoupa = base.getArmaduraRoupa();
        this.habilidadeExclusiva = base.getHabilidadeExclusiva();
        this.mascote = mascote;
        this.random = random;
        carregarAtributosBase();
    }

    /**
     * Garante a compatibilidade entre classe narrativa e mascote.
     */
    private void validarMascote(PersonagemBaseAddams base, Mascote mascote) {
        if (base.getClassePersonagem().aceitaMascote(mascote.getTipo())) {
            return;
        }

        throw new IllegalArgumentException("Mascote incompatível com a classe do personagem.");
    }

    /**
     * Copia os atributos fixos do catálogo para o objeto criado.
     */
    private void carregarAtributosBase() {
        this.vida = VIDA_INICIAL;
        this.forca = base.getForca();
        this.defesa = base.getDefesa();
        this.mana = base.getMana();
        this.nivel = base.getNivel();
        this.bonusDefesaTemporario = 0;
        this.defendendo = false;
    }

    public PersonagemBaseAddams getBase() {
        return base;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
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

    public Mascote getMascote() {
        return mascote;
    }

    public int getBonusDefesaTemporario() {
        return bonusDefesaTemporario;
    }

    public boolean isDefendendo() {
        return defendendo;
    }

    /**
     * Indica se o personagem ainda pode participar da batalha.
     */
    public boolean estaVivo() {
        return vida > 0;
    }

    /**
     * Executa o ataque básico usando a força como base do dano.
     */
    public int atacar() {
        if (!estaVivo()) {
            return 0;
        }

        defendendo = false;
        return forca + random.nextInt(31);
    }

    /**
     * Ativa um estado defensivo para reduzir o próximo dano recebido.
     */
    public void defender() {
        defendendo = true;
        bonusDefesaTemporario += 35;
    }

    /**
     * Usa a habilidade exclusiva do personagem, consumindo mana.
     */
    public ResultadoHabilidade usarHabilidadeEspecial(Personagem alvo) {
        if (!estaVivo()) {
            return criarFalhaHabilidade(nome + " já foi derrotado e não pode mais agir.");
        }
        if (mana < base.getCustoHabilidade()) {
            return criarFalhaHabilidade(nome + " está sem mana suficiente para usar "
                    + habilidadeExclusiva + ".");
        }

        mana -= base.getCustoHabilidade();
        int danoBase = forca + base.getBonusDanoHabilidade() + random.nextInt(21);
        int vidaRecuperada = recuperarVida(base.getCuraHabilidade());
        ResultadoDano resultadoDano = alvo.receberDanoDetalhado(danoBase);

        aplicarBonusDefesa(base.getBonusDefesaHabilidade());
        return new ResultadoHabilidade(true, habilidadeExclusiva, resultadoDano,
                vidaRecuperada, base.getBonusDefesaHabilidade(), mana);
    }

    /**
     * Cria um retorno padronizado para falhas de uso da habilidade.
     */
    private ResultadoHabilidade criarFalhaHabilidade(String mensagem) {
        return new ResultadoHabilidade(false, mensagem, null, 0, 0, mana);
    }

    /**
     * Delega ao mascote uma ação de suporte ou ataque.
     */
    public ResultadoMascote pedirAjudaDoMascote(Personagem alvo, ModoAjudaMascote modo) {
        if (!estaVivo()) {
            return new ResultadoMascote(nome + " foi derrotado e não consegue chamar o mascote.",
                    null, 0, 0, 0, mascote.getEnergia());
        }

        return mascote.usarHabilidade(this, alvo, random, modo);
    }

    /**
     * Calcula o dano final depois da defesa do personagem.
     */
    public int receberDano(int danoBruto) {
        return receberDanoDetalhado(danoBruto).getDanoFinal();
    }

    /**
     * Calcula o dano e devolve detalhes para a narração do combate.
     */
    public ResultadoDano receberDanoDetalhado(int danoBruto) {
        int bonusDefesa = calcularBonusDefesaTotal();
        int defesaTotal = defesa + bonusDefesaTemporario;

        if (defendendo) {
            defesaTotal += 20;
        }

        int danoFinal = danoBruto - defesaTotal;
        if (danoFinal < 0) {
            danoFinal = 0;
        }

        vida -= danoFinal;
        if (vida < 0) {
            vida = 0;
        }

        limparDefesaTemporaria();
        return new ResultadoDano(danoBruto, defesa, bonusDefesa, danoFinal, vida);
    }

    /**
     * Soma todos os bônus defensivos ativos no personagem.
     */
    private int calcularBonusDefesaTotal() {
        int bonusDefesa = bonusDefesaTemporario;

        if (defendendo) {
            bonusDefesa += 20;
        }

        return bonusDefesa;
    }

    /**
     * Adiciona um bônus de defesa usado por mascotes e habilidades.
     */
    public void aplicarBonusDefesa(int valor) {
        if (valor > 0) {
            bonusDefesaTemporario += valor;
        }
    }

    /**
     * Recupera vida sem ultrapassar o limite máximo do personagem.
     */
    public int recuperarVida(int valor) {
        if (valor <= 0) {
            return 0;
        }

        int vidaAnterior = vida;
        vida += valor;
        if (vida > VIDA_INICIAL) {
            vida = VIDA_INICIAL;
        }
        return vida - vidaAnterior;
    }

    /**
     * Recupera mana sem ultrapassar o valor inicial do personagem.
     */
    public int recuperarMana(int valor) {
        if (valor <= 0) {
            return 0;
        }

        int manaAnterior = mana;
        mana += valor;
        if (mana > base.getMana()) {
            mana = base.getMana();
        }
        return mana - manaAnterior;
    }

    /**
     * Reduz mana sem permitir que o valor fique negativo.
     */
    public int reduzirMana(int valor) {
        if (valor <= 0) {
            return 0;
        }

        int manaAnterior = mana;
        mana -= valor;
        if (mana < 0) {
            mana = 0;
        }
        return manaAnterior - mana;
    }

    /**
     * Remove toda a defesa temporária ativa do personagem.
     */
    public int removerDefesaTemporaria() {
        int defesaRemovida = bonusDefesaTemporario;

        if (defendendo) {
            defesaRemovida += 20;
        }

        limparDefesaTemporaria();
        return defesaRemovida;
    }

    /**
     * Encerra o turno e renova um pouco da mana e da energia do mascote.
     */
    public void prepararProximoTurno() {
        limparDefesaTemporaria();
        recuperarMana(10);
        mascote.recuperarEnergia();
    }

    /**
     * Remove efeitos defensivos temporários após o turno ou impacto.
     */
    private void limparDefesaTemporaria() {
        defendendo = false;
        bonusDefesaTemporario = 0;
    }

    /**
     * Exibe todos os atributos obrigatórios do personagem no console.
     */
    public void exibirEstado() {
        System.out.println(getResumoStatus());
        System.out.println("Mascote: " + mascote.getResumo());
        System.out.println("Classe: " + classePersonagem.getDescricao());
        System.out.println("Tipo narrativo: " + classePersonagem.getPapelNarrativo());
        System.out.println("Armadura/Roupa: " + armaduraRoupa);
        System.out.println("Habilidade exclusiva: " + habilidadeExclusiva);
    }

    /**
     * Resume o estado principal do personagem para menus e testes.
     */
    public String getResumoStatus() {
        return nome
                + " | Vida: " + vida
                + " | Força: " + forca
                + " | Defesa: " + defesa
                + " | Mana: " + mana
                + " | Nível: " + nivel;
    }

    /**
     * Resume os dados de fim de rodada de forma compacta.
     */
    public String getResumoRodada() {
        return nome
                + " | Vida: " + vida
                + " | Mana: " + mana
                + " | Defesa temporária: " + bonusDefesaTemporario
                + " | Postura defensiva: " + (defendendo ? "ativa" : "inativa")
                + " | Energia do mascote: " + mascote.getEnergia();
    }
}
