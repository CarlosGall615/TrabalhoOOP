package br.com.unipar.atividade;

import java.util.Random;

public class Personagem {

    private String nomePersonagem;
    private double pontosVida = 100.00;
    private double armadura;
    private double ultimate;
    
    private Random random = new Random();

    public Random getDano() {
        return random;
    }

    public void setDano(Random dano) {
        this.random = dano;
    }

    public Personagem() { }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public double getPontosVida() {
        return pontosVida;
    }

    public void setPontosVida(double pontosVida) {
        this.pontosVida = pontosVida;
    }

    public double getArmadura() {
        return armadura;
    }

    public void setArmadura(double armadura) {
        this.armadura = armadura;
    }

    public double getUltimate() {
        return ultimate;
    }

    public void setUltimate(double ultimate) {
        this.ultimate = ultimate;
    }

    // Métodos para nosso personagem

    public double atacar() {
                
        int danoBruto = 10;
        double valorBonus = random.nextInt(1, 11) * 0.5;
        double danoTotal = danoBruto + valorBonus;
        System.out.println(this.nomePersonagem + ": Atacou " + danoTotal + " com sucesso!");
        return danoTotal;
    }

    public boolean esquivar() {

        int chanceEsquiva = random.nextInt(1, 101);
        boolean esquivou = chanceEsquiva <= 25;

        if (esquivou) {
            System.out.println(this.nomePersonagem + ": Esquivou-se.");
        } else {
            System.out.println(this.nomePersonagem + ": Nao conseguiu esquivar.");
        }
        return esquivou;
    }

    public double defender(double danoRecebido){

        if (esquivar()){
            System.out.println("Defesa ativada!");
            return pontosVida;
        }

        
        double mitigacao = danoRecebido * (armadura / 100);
        double danoMitigado = danoRecebido - mitigacao;

        if (danoMitigado < 0.0){
            danoMitigado = 0.0;
        }

        pontosVida = pontosVida - danoMitigado;

        if (pontosVida < 0.0){
            pontosVida = 0.0;
        }

        System.out.println(this.nomePersonagem + ": Recebeu " + danoMitigado + " de dano. Vida atual: " + pontosVida);
        return pontosVida; 
    }

    public void statusInicial (){
        
        System.out.printf("Armadura -> %.2f", this.armadura);
        System.out.println();
        System.out.printf("Vida ->     %.2f", this.pontosVida);
        System.out.println();
    }
}


