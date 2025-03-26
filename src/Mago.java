public class Mago extends Entidade {
    // Inicialização com atributos
    public Mago(String nome, Arma armaAtual) {
        super(nome, armaAtual);
        this.vidaMaxima = 200;
        this.vidaAtual = vidaMaxima;
        this.atkBase = 250;
    }

    @Override // Evasão: habilidade ativa de classe
    public boolean habilidade(Boolean estado) {
        if (cooldownHabilidade > 0) {
            System.out.println("A habilidade falhou!");
        } else {
            cooldownHabilidade = 3;
            System.out.println(nome + " prepara uma magia poderosa...");
            this.handleCura((int) (vidaMaxima * 0.5));
        }
        return false;
    }
}
