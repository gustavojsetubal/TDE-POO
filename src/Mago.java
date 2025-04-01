import java.util.Random;

public class Mago extends Entidade {
    Random rng = new Random(System.currentTimeMillis());

    // Inicialização com atributos
    public Mago(String nome, Arma armaAtual) {
        super(nome, armaAtual);
        this.vidaMaxima = 200;
        this.vidaAtual = vidaMaxima;
        this.baseAtk = 250;
    }

    @Override // Grimório: habilidade ativa de classe
    public boolean habilidade(Boolean estado) {
        if (cooldownHabilidade > 0) {
            System.out.println("A habilidade falhou!");
        } else {
            cooldownHabilidade = 2;
            System.out.println("[Grimório] " + nome + " prepara uma magia poderosa...");

            int action = 0;
            action = rng.nextInt(100);

            // Cura: 33%
            // Dano: 33%
            // Backfire: 33%


            if (action >= 66){
                System.out.println("[Grimório] " + nome + " dispara uma bola de fogo!");
                return GameHandler.adversario.handleDanoRecebido(baseAtk);
            } else if (action >= 33){
                System.out.println("[Grimório] " + nome + " cura suas feridas!");
                this.handleCura((int) ((int) vidaMaxima * 0.5));
            } else if (action < 33){
                System.out.println("[Grimório] " + nome + " errou o feitiço!");
                this.handleDanoRecebido((int) (vidaMaxima * 0.2));
            }
        }
        return false;
    }
}
