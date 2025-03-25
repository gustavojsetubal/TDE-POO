import java.util.List;
import java.util.Random;

public class Mob extends Entidade{
    Random rng = new Random();

    // Construtor simples, sem atributos
    public Mob(String nome, Arma armaAtual, int vidaMaxima, int atkBase) {
        super(nome, armaAtual);
        this.vidaMaxima = vidaMaxima;
        this.atkBase = atkBase;

    }

    // Geração
    static int genVidaMaxima;
    static int genAtkMaximo;
    public static void gerarMob(Mob adversario, int salaAtual, Boolean isBoss) {
        float bossMultiplier = 0;
        if (isBoss) {
            bossMultiplier = 2;
        }
        genVidaMaxima = (int) Math.round(3000 * (0.1 * salaAtual) * bossMultiplier);

        adversario = new Mob(NameHandler.generateMonster(), null, genVidaMaxima, genAtkMaximo);
    }



    // Ação
    public void defineAction(Entidade jogador){
        int action = 0;
        if (vidaAtual / vidaMaxima < 0.25){ // Se a vida for menor que 25%
            action = rng.nextInt(3);
        } else {
            action = rng.nextInt(2);
        }

        switch(action){
            case 0:
                ferir(jogador);
                break;

            case 1:
                defender(true);
                break;

            case 2:
                curar();
        }

    }

}
