package br.com.unipar.atividade;

/**
 * Representa as ações possíveis no menu de cada turno.
 */
public enum AcaoBatalha {

    ATACAR(1, "Atacar"),
    DEFENDER(2, "Defender"),
    HABILIDADE_ESPECIAL(3, "Usar habilidade especial"),
    AJUDA_MASCOTE(4, "Pedir ajuda do mascote"),
    EXIBIR_STATUS(5, "Exibir status");

    /**
     * Guarda o número digitado pelo usuário no menu.
     */
    private final int codigo;

    /**
     * Guarda o texto exibido para a ação.
     */
    private final String descricao;

    AcaoBatalha(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * Converte o número informado em uma ação do jogo.
     */
    public static AcaoBatalha porCodigo(int codigo) {
        for (AcaoBatalha acao : values()) {
            if (acao.codigo == codigo) {
                return acao;
            }
        }
        return null;
    }
}
