public class Guerreiro extends Entidade {
    // Inicialização com atributos
    public Guerreiro(String nome, Arma armaAtual) {
        super(nome, armaAtual);
        this.vidaMaxima = 75;
        this.vidaAtual = vidaMaxima;
        this.atkBase = 25;
    }

    @Override // Fúria: habilidade ativa de classe
    public void habilidade(Boolean estado) {
        if (estado){
            this.listaAtributos.add(Status.statusFuria(this));
        } else {
            this.listaAtributos.removeIf( status -> status.nome.equals("Fúria"));
        }
    }
}
