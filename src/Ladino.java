public class Ladino extends Entidade {
    // Inicialização com atributos
    public Ladino(String nome, Arma armaAtual) {
        super(nome, armaAtual);
        this.vidaMaxima = 300;
        this.vidaAtual = vidaMaxima;
        this.atkBase = 175;
    }

    @Override // Evasão: habilidade ativa de classe
    public boolean habilidade(Boolean estado) {
        if (cooldownHabilidade > 0) {
            System.out.println("A habilidade falhou!");
            return false;
        }
        if (estado){
            cooldownHabilidade = 2;
            System.out.println(nome + " se prepara pra desviar!");
            this.listaAtributos.add(Status.statusEvasao(this, GameHandler.rodada));
        } else if (!estado){
            System.out.println(nome + " abaixa novamente a guarda...");
        }
        return false;
    }
}
