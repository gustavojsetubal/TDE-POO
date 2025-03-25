public class Status {
    public String nome;
    public int cooldown; // Cooldown em turnos da habilidade
    public int dmgStatus; // Dano causado pelo status
    public int atkChange; // Alteração de atk causado com o status
    public float dmgChange; // Alteração no dano recebido com o status, porcentagem descrita entre 0 e 1

    public Status(String nome, int cooldown, int dmgStatus, int atkChange, float dmgChange) {
        this.nome = nome;
        this.dmgStatus = dmgStatus;
        this.atkChange = atkChange;
        this.dmgChange = dmgChange;
    }

    // Factory methods

    // "Fúria": habilidade ativa de GUERREIRO
    // Perde metade da vida em troca de dobro de dano
    public static Status statusFuria(Entidade entidade){
        return new Status(
                "Fúria",
                0,
                entidade.vidaAtual / 2,
                entidade.atkBase * 1,
                0
        );
    }

    // "Evasão": habilidade ativa de LADINO
    // Desvia do próximo ataque do oponente
    public static Status statusEvasao(Entidade entidade){
        return new Status(
                "Evasão",
                3,
                0,
                0,
                1
        );
    }


    // Override de nomenclatura
    @Override
    public String toString() {
        return this.nome;
    }
}
