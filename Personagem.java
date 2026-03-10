package br.com.unipar.atividade;

import java.util.Random;

public class Personagem {

    private String nomePersonagem;
    private int pontosVida;
    private Double  armadura;
    private Double  ultimate;

    public Personagem() {}

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public int getPontosVida() {
        return pontosVida;
    }

    public void setPontosVida(int pontosVida) {
        this.pontosVida = pontosVida;
    }

    public Double getArmadura() {
        return armadura;
    }

    public void setArmadura(Double armadura) {
        this.armadura = armadura;
    }

    public Double getUltimate() {
        return ultimate;
    }

    public void setUltimate(Double ultimate) {
        this.ultimate = ultimate;
    }

    public criarPersonagem(String nome, int pontosVida, double armadura, double ultimate){
        this.nomePersonagem = nome;
        this.pontosVida = 100;
        this.armadura = 30.50;
        this.ultimate = 8.3;

    }
    public atacar( ){
        int danoBruto = 10;
        double redução = 10 * 0,305;
        double danoFinal = danoBruto - redução;



    }

    public esquivar(){
        Random chanceEsquiva = new Random();


    }

}



