package br.com.unipar.atividade;

import java.util.Random;

public class Personagem {

    // O personagem agora nasce a partir de uma identidade fixa da família e do seu mascote oficial.
    private final PersonagemBaseAddams base;
    private final String nome;
    private final ClassePersonagem classePersonagem;
    private final Mascote mascote;
    private final Random random;

    private int nivel;
    private int vidaMaxima;
    private int vidaAtual;
    private int forca;
    private int defesa;
    private int defesaTemporaria;

    public Personagem(PersonagemBaseAddams base, int nivel, Random random) {
        this.base = base;
        this.nome = base.getNomeExibicao();
        this.classePersonagem = base.getClassePersonagem();
        this.mascote = new Mascote(base.getTipoMascote());
        this.nivel = nivel;
        this.random = random;

        this.vidaMaxima = classePersonagem.getVidaBase() + base.getBonusVida() + (nivel * 4);
        this.vidaAtual = vidaMaxima;
        this.forca = classePersonagem.getForcaBase() + base.getBonusForca() + nivel;
        this.defesa = classePersonagem.getDefesaBase() + base.getBonusDefesa() + nivel;
        this.defesaTemporaria = 0;
    }

    public String getNome() {
        return nome;
    }

    public PersonagemBaseAddams getBase() {
        return base;
    }

    public ClassePersonagem getClassePersonagem() {
        return classePersonagem;
    }

    public Mascote getMascote() {
        return mascote;
    }

    public int getNivel() {
        return nivel;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public int getForca() {
        return forca;
    }

    public int getDefesa() {
        return defesa;
    }

    public boolean estaVivo() {
        return vidaAtual > 0;
    }

    public int atacar(CenarioCasa cenario) {
        int variacao = random.nextInt(5);
        int bonusMascote = mascote.apoiarAtaque();
        int danoTotal = forca + variacao + bonusMascote + cenario.getBonusAtaque();

        if (danoTotal < 1) {
            danoTotal = 1;
        }

        System.out.println(nome + " realizou um ataque direto.");
        System.out.println("O mascote " + mascote.getTipo().getNomeExibicao() + " aumentou o dano em +" + bonusMascote + ".");
        return danoTotal;
    }

    public void prepararDefesa() {
        defesaTemporaria = 6;
        System.out.println(nome + " reforçou a guarda e vai receber melhor a próxima investida.");
    }

    public boolean usarSkillDoMascote(Personagem alvo, CenarioCasa cenario) {
        if (!mascote.podeUsarSkill()) {
            System.out.println("O mascote de " + nome + " não possui vigor e mana suficientes para a skill.");
            return false;
        }

        int danoSkill = mascote.usarSkillEspecial() + (cenario.getBonusAtaque() / 2);
        int cura = mascote.aplicarCuraAoDono();

        System.out.println("O mascote " + mascote.getTipo().getNomeExibicao()
                + " usou a skill " + mascote.getTipo().getNomeSkill() + ".");
        alvo.receberDano(danoSkill, cenario, "skill do mascote de " + nome);
        recuperarVida(cura);

        if (cura > 0) {
            System.out.println(nome + " recuperou " + cura + " pontos de vida com o apoio do mascote.");
        }

        return true;
    }

    public void receberDano(int danoRecebido, CenarioCasa cenario, String origem) {
        int defesaTotal = defesa + defesaTemporaria + mascote.apoiarDefesa() + cenario.getBonusDefesa();
        int danoFinal = danoRecebido - defesaTotal;

        if (danoFinal < 0) {
            danoFinal = 0;
        }

        vidaAtual -= danoFinal;
        if (vidaAtual < 0) {
            vidaAtual = 0;
        }

        mascote.absorverImpacto(danoRecebido);

        System.out.println(nome + " sofreu um ataque vindo de " + origem + ".");
        System.out.println("Dano final recebido: " + danoFinal + ".");
        System.out.println("Vida atual: " + vidaAtual + "/" + vidaMaxima + ".");

        defesaTemporaria = 0;
    }

    public void descansarComMascote() {
        recuperarVida(4);
        mascote.recuperarEntreTurnos();
        System.out.println(nome + " respirou fundo e reorganizou forças com o mascote.");
    }

    public void finalizarTurno() {
        mascote.recuperarEntreTurnos();
    }

    public void recuperarVida(int valor) {
        vidaAtual += valor;
        if (vidaAtual > vidaMaxima) {
            vidaAtual = vidaMaxima;
        }
    }

    public void exibirEstado() {
        System.out.println("Personagem: " + nome);
        System.out.println("Classe da história: " + classePersonagem.getDescricao());
        System.out.println("Contexto: " + classePersonagem.getContextoNarrativo());
        System.out.println("Perfil: " + base.getDescricao());
        System.out.println("Nível: " + nivel);
        System.out.println("Vida: " + vidaAtual + "/" + vidaMaxima);
        System.out.println("Força: " + forca);
        System.out.println("Defesa: " + defesa);
        System.out.println("Mascote oficial: " + mascote.getResumoAcademico());
    }
}
