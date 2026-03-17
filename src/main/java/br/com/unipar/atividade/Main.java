package br.com.unipar.atividade;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        String vencedor;

        Random personagemRandom = new Random();
        Personagem personagem1 = new Personagem();
        Personagem personagem2 = new Personagem();


        String[] familiaAdans = {"Gomez", "Morticia", "Wandinha", "Feioso", "Tio Chico", "Vovó", "Tropeço", "Mãozinha", "Primo Itt"};

        int indicePersonagem1 = personagemRandom.nextInt(familiaAdans.length);
        int indicePersonagem2;
        do {
            indicePersonagem2 = personagemRandom.nextInt(familiaAdans.length);
        } while (indicePersonagem1 == indicePersonagem2);

        String personagemSorteado1 = familiaAdans[indicePersonagem1];
        String personagemSorteado2 = familiaAdans[indicePersonagem2];

        personagem1.setArmadura(15 + (personagemRandom.nextDouble() * 31));
        personagem2.setArmadura(15 + (personagemRandom.nextDouble() * 31));
        
        personagem1.setNomePersonagem(personagemSorteado1);
        personagem2.setNomePersonagem(personagemSorteado2);
        

        System.out.println("======================================");
        System.out.println("=== Bem-Vindo ao Família Adans K.O ===");
        System.out.println("======================================\n");

        System.out.println("|| ->> " + personagem1.getNomePersonagem() + "  VS  " + personagem2.getNomePersonagem() + " << -- ||\n");

        System.out.println("------------- Round 01 -------------\n");



        
        while (personagem1.getPontosVida() > 0.0 && personagem2.getPontosVida() > 0.0) {

            double danoCausadoPersonagem1 = personagem1.atacar();
            personagem2.defender(danoCausadoPersonagem1);

            if (personagem2.getPontosVida() <= 0.0) {
                break;
            }

            tempoEntreAtaques();

            double danoCausadoPersonagem2 = personagem2.atacar();
            personagem1.defender(danoCausadoPersonagem2);

            if (personagem1.getPontosVida() <= 0.0) {
                break;
            }

            tempoEntreAtaques();

            System.out.println("\n");
        }

        
        if (personagem1.getPontosVida() > 0.0) {
            vencedor = personagem1.getNomePersonagem();
        } else {
            vencedor = personagem2.getNomePersonagem();
        }

        System.out.println("\n======================================");
        System.out.println("=== Vencedor: " + vencedor + " ===");
        System.out.println("======================================");
    }

    private static void tempoEntreAtaques() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Nao foi possivel pausar a luta.");
        }
    }
}
