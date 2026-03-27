package br.com.unipar.atividade;

import java.util.Random;

/**
 * Aplica eventos aleatórios da mansão durante a batalha.
 */
public class IntervencaoMansao {

    /**
     * Chance máxima de a mansão interferir em uma rodada.
     */
    private static final int CHANCE_INTERVENCAO = 30;

    /**
     * Fonte aleatória usada nos sorteios dos eventos.
     */
    private final Random random;

    public IntervencaoMansao(Random random) {
        this.random = random;
    }

    /**
     * Decide se haverá intervenção e aplica um evento sorteado.
     */
    public ResultadoIntervencaoMansao tentarInterferir(Personagem jogador,
                                                       Personagem inimigo) {
        if (random.nextInt(100) >= CHANCE_INTERVENCAO) {
            return new ResultadoIntervencaoMansao(false, null,
                    "A mansão observou em silêncio e não interferiu nesta rodada.");
        }

        EventoMansao[] eventos = EventoMansao.values();
        EventoMansao evento = eventos[random.nextInt(eventos.length)];
        return aplicarEvento(evento, jogador, inimigo);
    }

    /**
     * Aplica um evento específico para permitir testes previsíveis.
     */
    public ResultadoIntervencaoMansao aplicarEvento(EventoMansao evento,
                                                    Personagem jogador,
                                                    Personagem inimigo) {
        if (evento == EventoMansao.ARMADURA_DESPENCANDO) {
            return aplicarArmaduraDespencando(escolherAlvo(jogador, inimigo));
        }
        if (evento == EventoMansao.SUSSURROS_DA_MANSAO) {
            return aplicarSussurrosDaMansao(escolherAlvo(jogador, inimigo));
        }
        if (evento == EventoMansao.BRISA_GELADA) {
            return aplicarBrisaGelada(escolherAlvo(jogador, inimigo));
        }
        if (evento == EventoMansao.PORTA_BATENDO) {
            return aplicarPortaBatendo(escolherAlvo(jogador, inimigo));
        }
        if (evento == EventoMansao.RETRATOS_VIGIANDO) {
            return aplicarRetratosVigiando(escolherAlvo(jogador, inimigo));
        }
        return aplicarVelasTremulando(jogador, inimigo);
    }

    /**
     * Escolhe um alvo entre os combatentes ainda vivos.
     */
    private Personagem escolherAlvo(Personagem jogador, Personagem inimigo) {
        if (!jogador.estaVivo()) {
            return inimigo;
        }
        if (!inimigo.estaVivo()) {
            return jogador;
        }
        return random.nextBoolean() ? jogador : inimigo;
    }

    /**
     * Derruba uma armadura da casa sobre um dos combatentes.
     */
    private ResultadoIntervencaoMansao aplicarArmaduraDespencando(Personagem alvo) {
        int dano = 35 + random.nextInt(21);
        ResultadoDano resultado = alvo.receberDanoDetalhado(dano);
        String mensagem = "Uma armadura antiga despencou perto de " + alvo.getNome()
                + " e causou " + resultado.getDanoFinal() + " de dano inesperado.";
        return new ResultadoIntervencaoMansao(true, EventoMansao.ARMADURA_DESPENCANDO,
                mensagem);
    }

    /**
     * Faz a mansão sussurrar e drenar parte da concentração do alvo.
     */
    private ResultadoIntervencaoMansao aplicarSussurrosDaMansao(Personagem alvo) {
        int perdaMana = alvo.reduzirMana(12 + random.nextInt(9));
        String mensagem = "Sussurros ecoaram pelos corredores e " + alvo.getNome()
                + " perdeu " + perdaMana + " de mana ao se distrair.";
        return new ResultadoIntervencaoMansao(true, EventoMansao.SUSSURROS_DA_MANSAO,
                mensagem);
    }

    /**
     * Concede proteção temporária por causa do frio sobrenatural.
     */
    private ResultadoIntervencaoMansao aplicarBrisaGelada(Personagem alvo) {
        int bonusDefesa = 15 + random.nextInt(11);
        alvo.aplicarBonusDefesa(bonusDefesa);
        String mensagem = "Uma brisa gelada atravessou a sala e fortaleceu a defesa de "
                + alvo.getNome() + " em " + bonusDefesa + " pontos.";
        return new ResultadoIntervencaoMansao(true, EventoMansao.BRISA_GELADA, mensagem);
    }

    /**
     * Fecha portas violentamente e remove a defesa erguida.
     */
    private ResultadoIntervencaoMansao aplicarPortaBatendo(Personagem alvo) {
        int defesaRemovida = alvo.removerDefesaTemporaria();
        if (defesaRemovida == 0) {
            int perdaMana = alvo.reduzirMana(10);
            String mensagem = "Uma porta bateu sozinha, assustou " + alvo.getNome()
                    + " e drenou " + perdaMana + " de mana.";
            return new ResultadoIntervencaoMansao(true, EventoMansao.PORTA_BATENDO,
                    mensagem);
        }

        String mensagem = "Uma porta bateu com força e derrubou " + defesaRemovida
                + " pontos de defesa temporária de " + alvo.getNome() + ".";
        return new ResultadoIntervencaoMansao(true, EventoMansao.PORTA_BATENDO, mensagem);
    }

    /**
     * Faz os retratos da casa assustarem o mascote do alvo.
     */
    private ResultadoIntervencaoMansao aplicarRetratosVigiando(Personagem alvo) {
        int energiaPerdida = alvo.getMascote().reduzirEnergia(15 + random.nextInt(11));
        String mensagem = "Os retratos da mansão pareceram se mover e o mascote de "
                + alvo.getNome() + " perdeu " + energiaPerdida + " de energia.";
        return new ResultadoIntervencaoMansao(true, EventoMansao.RETRATOS_VIGIANDO,
                mensagem);
    }

    /**
     * Faz as velas assombradas reorganizarem a energia dos dois lados.
     */
    private ResultadoIntervencaoMansao aplicarVelasTremulando(Personagem jogador,
                                                              Personagem inimigo) {
        int manaJogador = jogador.recuperarMana(8);
        int manaInimigo = inimigo.recuperarMana(8);
        String mensagem = "As velas da mansão tremularam e espalharam energia estranha: "
                + jogador.getNome() + " recuperou " + manaJogador + " de mana e "
                + inimigo.getNome() + " recuperou " + manaInimigo + ".";
        return new ResultadoIntervencaoMansao(true, EventoMansao.VELAS_TREMULANDO,
                mensagem);
    }
}
