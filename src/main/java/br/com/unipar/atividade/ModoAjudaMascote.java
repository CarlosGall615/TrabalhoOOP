package br.com.unipar.atividade;

/**
 * Define os modos de ajuda disponíveis para o mascote.
 */
public enum ModoAjudaMascote {

    OFENSIVO(1, "Ajuda ofensiva"),
    DEFENSIVO(2, "Ajuda defensiva");

    /**
     * Código digitado no submenu do mascote.
     */
    private final int codigo;

    /**
     * Texto exibido para orientar o jogador.
     */
    private final String descricao;

    ModoAjudaMascote(int codigo, String descricao) {
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
     * Converte o código informado em um modo válido.
     */
    public static ModoAjudaMascote porCodigo(int codigo) {
        for (ModoAjudaMascote modo : values()) {
            if (modo.codigo == codigo) {
                return modo;
            }
        }
        return null;
    }
}
