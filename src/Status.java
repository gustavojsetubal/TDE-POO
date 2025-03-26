import java.util.ArrayList;
import java.util.List;

public class Status {
    public String nome;
    private int duracao; // Duração da habilidade, caso aplicável
    private int dmgStatus; // Dano causado pelo status
    private int atkChange; // Alteração de atk causado com o status
    private float dmgChange; // Alteração no dano recebido com o status, porcentagem descrita entre 0 e 1
    private int turnosDesdeUso; // Turno em que se iniciou para contagem de cooldown
    private boolean statusIdle; // Define se o status imobiliza a entidade
    private String statusIdleMsg; // Define a mensagem que aparece quando statusIdle ativa


    public Status(String nome, int duracao, int dmgStatus, int atkChange, float dmgChange) {
        this.nome = nome;
        this.duracao = duracao;
        this.dmgStatus = dmgStatus;
        this.atkChange = atkChange;
        this.dmgChange = dmgChange;
        this.turnosDesdeUso = 0;
        this.statusIdle = false;

    }

    public Status(String nome, int duracao, int dmgStatus, int atkChange, float dmgChange, boolean statusIdle, String statusIdleMsg) {
        this.nome = nome;
        this.duracao = duracao;
        this.dmgStatus = dmgStatus;
        this.atkChange = atkChange;
        this.dmgChange = dmgChange;
        this.turnosDesdeUso = 0;
        this.statusIdle = statusIdle;
        this.statusIdleMsg = statusIdleMsg;
    }

    // Factory methods

    // "Fúria": habilidade ativa de GUERREIRO
    // Perde metade da vida em troca de dobro de dano
    public static Status statusFuria(Entidade entidade, int turnoUso){
        int atkArma;
        try {
            atkArma = entidade.armaAtual.getAtkExtra();
        } catch (NullPointerException e) {
            atkArma = 0;
        }
        return new Status(
                "Fúria",
                1,
                entidade.vidaAtual / 5,
                (entidade.atkBase + atkArma) * 1,
                0


        );
    }

    //Frenesi: habilidade ativa de mob [BOSS]
    //Pela espera de um turno o [BOSS] vai dar o dobro do dano em um unico ataque
    public static Status statusFrenesi(Entidade entidade, int turnoUso){
        return new Status(
                "Frenesi",
                1,
                0,
                0,
                0,
                true,
                entidade.nome + "está se preparando para atacar..."

        );
    }

    // "Evasão": habilidade ativa de LADINO
    // Desvia do próximo ataque do oponente
    public static Status statusEvasao(Entidade entidade, int turnoUso){
        return new Status(
                "Evasão",
                1,
                0,
                0,
                1
        );
    }

    // Tick de atributo
    public void tickStatus(Entidade entidade){
        turnosDesdeUso++;
        if (turnosDesdeUso >= duracao){
            entidade.toRemove.add(this);
        }
    }

    public static Status searchStatus(String searchQuery, List<Status> searchLocale){
        return searchLocale.stream()
                .filter(atributo -> atributo.nome.matches(searchQuery))
                .findFirst()
                .orElse(null);
    }

    public static boolean doesStatusExist(String searchQuery, List<Status> searchLocale){
        return searchLocale.stream()
                .anyMatch(atributo -> atributo.nome.equals(searchQuery));
    }

    // Getters
    public int getDuracao() {
        return duracao;
    }

    public int getDmgStatus() {
        return dmgStatus;
    }

    public int getAtkChange() {
        return atkChange;
    }

    public float getDmgChange() {
        return dmgChange;
    }

    public int getTurnosDesdeUso() {
        return turnosDesdeUso;
    }

    public boolean isStatusIdle() {
        return statusIdle;
    }

    public String getStatusIdleMsg() {
        return statusIdleMsg;
    }

    // Override de nomenclatura
    @Override
    public String toString() {
        return this.nome;
    }
}
