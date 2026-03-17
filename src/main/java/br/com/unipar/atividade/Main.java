package br.com.unipar.atividade;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random personagenRandom = new Random();
        Personagem personagem1 = new Personagem();
        Personagem personagem2 = new Personagem();


        String[] familiaAdans = {"Gomez", "Morticia", "Wandinha", "Feioso", "Tio Chico", "Vovó", "Tropeço", "Mãozinha", "Primo Itt"};

        int indicePersonagem1 = personagenRandom.nextInt(familiaAdans.length);
        int indicePersonagem2;
        do {
            indicePersonagem2 = personagenRandom.nextInt(familiaAdans.length);
        } while (indicePersonagem1 == indicePersonagem2);

        String personagemSorteado1 = familiaAdans[indicePersonagem1];
        String personagemSorteado2 = familiaAdans[indicePersonagem2];

        double armadura1 = 15 + (personagenRandom.nextDouble() * 31);
        double armadura2 = 15 + (personagenRandom.nextDouble() * 31);
        
        
        personagem1.setNomePersonagem(personagemSorteado1);
        personagem2.setNomePersonagem(personagemSorteado2);
        personagem1.setArmadura(armadura1);
        personagem2.setArmadura(armadura2);

        System.out.println("======================================");
        System.out.println("=== Bem-Vindo ao Família Adans K.O ===");
        System.out.println("======================================\n");

        System.out.println("--- Round 01 ---\n");
        System.out.println(" ->> " + personagem1.getNomePersonagem() + "   VS   " + personagem2.getNomePersonagem() + " << -- ");
        personagem1.statusInicial();
        personagem2.statusInicial();
        
        double danoCausado = personagem1.atacar();
        personagem2.defender(danoCausado);
    }
}
