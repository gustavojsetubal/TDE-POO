import java.util.ArrayList;
import java.util.List;

public class Status {
    public String nome;
    public int duracao; // Duração da habilidade, caso aplicável
    public int dmgStatus; // Dano causado pelo status
    public int atkChange; // Alteração de atk causado com o status
    public float dmgChange; // Alteração no dano recebido com o status, porcentagem descrita entre 0 e 1
    public int turnosDesdeUso; // Turno em que se iniciou para contagem de cooldown


    public Status(String nome, int duracao, int dmgStatus, int atkChange, float dmgChange) {
        this.nome = nome;
        this.duracao = duracao;
        this.dmgStatus = dmgStatus;
        this.atkChange = atkChange;
        this.dmgChange = dmgChange;
        this.turnosDesdeUso = 0;
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


    // Override de nomenclatura
    @Override
    public String toString() {
        return this.nome;
    }
}
