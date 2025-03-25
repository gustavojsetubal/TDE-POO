public class Ladino extends Entidade {
    // Inicialização com atributos
    public Ladino(String nome, Arma armaAtual) {
        super(nome, armaAtual);
        this.vidaMaxima = 50;
        this.vidaAtual = vidaMaxima;
        this.atkBase = 50;
    }

    @Override // Evasão: habilidade ativa de classe
    public void habilidade(Boolean estado) {
        if (estado){
            this.listaAtributos.add(Status.statusEvasao(this));
        } else {
            this.listaAtributos.removeIf( status -> status.nome.equals("Evasão"));
        }
    }
}
