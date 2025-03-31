import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Status {
    // Função: delegar atributos de um efeito de status.

    String nome; // Nome do status
    int duracao; // Duração do status, caso aplicável
    int modifierVida; // Modificação por turno à vida causada pelo status
    int modifierAtk; // Modificação de atk causado pelo status
    float modifierRes; // Modificação de resistência causado pelo status
    int turnoAplicado; // Turno em que status foi aplicado
    boolean causaImobilizacao; // Define se o status imobiliza a entidade
    Map<String, String> textoStatus = new HashMap<>(); // Define as mensagens utilizadas pelo status

    // Construtores

    public Status(String nome, int duracao, int modifierVida, int modifierAtk, float modifierRes, int turnoAplicado, boolean causaImobilizacao, Map<String, String> textoStatus) {
        this.nome = nome;
        this.duracao = duracao;
        this.modifierVida = modifierVida;
        this.modifierAtk = modifierAtk;
        this.modifierRes = modifierRes;
        this.turnoAplicado = turnoAplicado;
        this.causaImobilizacao = causaImobilizacao;
        this.textoStatus.putAll(textoStatus);
    }
}


