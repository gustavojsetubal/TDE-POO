public class Mago extends Entidade {
    // Inicialização com atributos
    public Mago(String nome, Arma armaAtual) {
        super(nome, armaAtual);
        this.vidaMaxima = 25;
        this.vidaAtual = vidaMaxima;
        this.atkBase = 75;
    }

    @Override // Evasão: habilidade ativa de classe
    public void habilidade(Boolean estado) {
        this.handleCura(30);
    }
}
