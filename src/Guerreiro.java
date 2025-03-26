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
                System.out.println(nome + " se enfurece!");
                this.listaAtributos.add(Status.statusFuria(this, GameHandler.rodada));
            } else {
                System.out.println("A raiva de " + nome + " se esvaiu...");
            }
        }
        return false;
    }
}
