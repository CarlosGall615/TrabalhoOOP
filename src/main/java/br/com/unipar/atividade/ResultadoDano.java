package br.com.unipar.atividade;

/**
 * Guarda os detalhes de um cálculo de dano para narração do combate.
 */
public class ResultadoDano {

    private final int danoBruto;
    private final int defesaBase;
    private final int bonusDefesa;
    private final int danoFinal;
    private final int vidaRestante;

    public ResultadoDano(int danoBruto, int defesaBase, int bonusDefesa,
                         int danoFinal, int vidaRestante) {
        this.danoBruto = danoBruto;
        this.defesaBase = defesaBase;
        this.bonusDefesa = bonusDefesa;
        this.danoFinal = danoFinal;
        this.vidaRestante = vidaRestante;
    }

    public int getDanoBruto() {
        return danoBruto;
    }

    public int getDefesaBase() {
        return defesaBase;
    }

    public int getBonusDefesa() {
        return bonusDefesa;
    }

    public int getDanoFinal() {
        return danoFinal;
    }

    public int getVidaRestante() {
        return vidaRestante;
    }

    public int getDefesaTotal() {
        return defesaBase + bonusDefesa;
    }
}
