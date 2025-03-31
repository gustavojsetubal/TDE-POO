public class Guerreiro extends Entidade {
    // Inicialização com atributos
    public Guerreiro(String nome, Arma armaAtual) {
        super(nome, armaAtual);
        this.vidaMaxima = 500;
        this.vidaAtual = vidaMaxima;
        this.atkBase = 125;
    }

    @Override // Fúria: habilidade ativa de classe
    public boolean habilidade(Boolean estado) {
        if (cooldownHabilidade > 0) {
            System.out.println("A habilidade falhou!");
            System.out.println("teste");
        } else {
            if (estado){
                cooldownHabilidade = 1;
                System.out.println("[+Fúria] " + nome + " se enfurece!");
                this.listaAtributos.add(StatusHandler.statusFuria(this, GameHandler.getRodadaAtual()));
            } else {
                System.out.println("[-Fúria] " + "A raiva de " + nome + " se esvaiu...");
            }
        }
        return false;
    }
}
