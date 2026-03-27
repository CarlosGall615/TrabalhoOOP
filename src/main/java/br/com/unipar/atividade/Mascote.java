package br.com.unipar.atividade;

public class Mascote {

    // O mascote agora é mais completo para demonstrar atributos, estado e comportamento próprios.
    private final TipoMascote tipo;
    private int vidaAtual;
    private int armaduraAtual;
    private int comboAtual;
    private int vigorAtual;
    private int manaAtual;

    public Mascote(TipoMascote tipo) {
        this.tipo = tipo;
        this.vidaAtual = tipo.getVidaBase();
        this.armaduraAtual = tipo.getArmaduraBase();
        this.comboAtual = tipo.getComboInicial();
        this.vigorAtual = tipo.getVigorBase();
        this.manaAtual = tipo.getManaBase();
    }

    public TipoMascote getTipo() {
        return tipo;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public int getArmaduraAtual() {
        return armaduraAtual;
    }

    public int getComboAtual() {
        return comboAtual;
    }

    public int getVigorAtual() {
        return vigorAtual;
    }

    public int getManaAtual() {
        return manaAtual;
    }

    public boolean estaAtivo() {
        return vidaAtual > 0;
    }

    public int apoiarAtaque() {
        if (!estaAtivo()) {
            return 0;
        }

        return tipo.getBonusAtaque() + comboAtual;
    }

    public int apoiarDefesa() {
        if (!estaAtivo()) {
            return 0;
        }

        return tipo.getBonusDefesa() + (armaduraAtual / 4);
    }

    public boolean podeUsarSkill() {
        return estaAtivo() && vigorAtual >= 3 && manaAtual >= 3;
    }

    public int usarSkillEspecial() {
        if (!podeUsarSkill()) {
            return 0;
        }

        vigorAtual -= 3;
        manaAtual -= 3;
        comboAtual += 1;
        return tipo.getPoderSkill() + comboAtual;
    }

    public int aplicarCuraAoDono() {
        if (!estaAtivo()) {
            return 0;
        }

        return tipo.getCuraAoDono();
    }

    public void absorverImpacto(int danoRecebido) {
        if (!estaAtivo()) {
            return;
        }

        int desgaste = Math.max(1, danoRecebido / 10);

        if (armaduraAtual > 0) {
            armaduraAtual -= desgaste;
            if (armaduraAtual < 0) {
                armaduraAtual = 0;
            }
            return;
        }

        vidaAtual -= desgaste;
        if (vidaAtual < 0) {
            vidaAtual = 0;
        }
    }

    public void recuperarEntreTurnos() {
        if (!estaAtivo()) {
            return;
        }

        vigorAtual += 1;
        manaAtual += 1;

        if (vigorAtual > tipo.getVigorBase()) {
            vigorAtual = tipo.getVigorBase();
        }

        if (manaAtual > tipo.getManaBase()) {
            manaAtual = tipo.getManaBase();
        }

        if (comboAtual > tipo.getComboInicial()) {
            comboAtual -= 1;
        }
    }

    public String getResumoAcademico() {
        return tipo.getNomeExibicao()
                + " | vida " + vidaAtual
                + ", armadura " + armaduraAtual
                + ", combo " + comboAtual
                + ", vigor " + vigorAtual
                + ", mana " + manaAtual
                + ", skill " + tipo.getNomeSkill();
    }
}
