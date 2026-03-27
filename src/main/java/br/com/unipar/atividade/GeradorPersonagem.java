package br.com.unipar.atividade;

import java.util.Random;

/**
 * Centraliza a criação de personagens e a escolha aleatória do inimigo.
 */
public class GeradorPersonagem {

    /**
     * Fonte aleatória compartilhada entre sorteios do sistema.
     */
    private final Random random;

    public GeradorPersonagem(Random random) {
        this.random = random;
    }

    /**
     * Exibe o catálogo de personagens disponíveis no console.
     */
    public void exibirPersonagensDisponiveis() {
        PersonagemBaseAddams[] personagens = PersonagemBaseAddams.values();

        for (int i = 0; i < personagens.length; i++) {
            PersonagemBaseAddams base = personagens[i];
            System.out.println((i + 1) + " - " + base.getNome());
            System.out.println("Classe: " + base.getClassePersonagem().getDescricao());
            System.out.println("Mascotes permitidos: "
                    + base.getClassePersonagem().listarMascotesPermitidos());
            System.out.println("Perfil: " + base.getDescricao());
            System.out.println();
        }
    }

    /**
     * Cria o personagem controlado pelo usuário a partir da opção escolhida.
     */
    public Personagem criarJogador(int indiceEscolhido) {
        PersonagemBaseAddams[] personagens = PersonagemBaseAddams.values();
        return criarPersonagem(personagens[indiceEscolhido - 1]);
    }

    /**
     * Sorteia um inimigo diferente do personagem já escolhido pelo jogador.
     */
    public Personagem criarInimigoAleatorio(Personagem jogador) {
        PersonagemBaseAddams[] personagens = PersonagemBaseAddams.values();
        PersonagemBaseAddams sorteado;

        do {
            sorteado = personagens[random.nextInt(personagens.length)];
        } while (sorteado == jogador.getBase());

        return criarPersonagem(sorteado);
    }

    /**
     * Constrói o objeto personagem e associa um mascote compatível.
     */
    public Personagem criarPersonagem(PersonagemBaseAddams base) {
        Mascote mascote = new Mascote(sortearMascote(base.getClassePersonagem()));
        return new Personagem(base, mascote, random);
    }

    /**
     * Sorteia um mascote apenas entre os permitidos para a classe.
     */
    private TipoMascote sortearMascote(ClassePersonagem classePersonagem) {
        TipoMascote[] permitidos = classePersonagem.getMascotesPermitidos();
        return permitidos[random.nextInt(permitidos.length)];
    }
}
