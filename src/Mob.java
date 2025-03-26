import java.util.List;
import java.util.Random;

public class Mob extends Entidade{
    static Random rng = new Random(System.currentTimeMillis());

    // Construtor simples, sem atributos
    public Mob(String nome, Arma armaAtual, int vidaMaxima, int atkBase) {
        super(nome, armaAtual);
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.atkBase = atkBase;
        this.defesa = false;

    }

    // Geração
    static int genVidaMaxima;
    static int genAtkMaximo;
    static float genMultiplier(){
        return (float) rng.nextDouble(0.1);
    }

    public static Mob gerarMob(Mob adversario, int salaAtual, Boolean isBoss) {
        float bossMultiplier = 1;
        if (isBoss) {
            bossMultiplier = 2;
        }
        genVidaMaxima = (int) Math.round(3000 * (genMultiplier() * salaAtual) * bossMultiplier);
        genAtkMaximo = (int) Math.round(1000 * (genMultiplier() * salaAtual) * bossMultiplier);

        return new Mob(NameHandler.generateMonster(), null, genVidaMaxima, genAtkMaximo);
    }



    // Seleção de ação
    public boolean defineAction(Entidade jogador){
        int action = 0;
        if (vidaAtual / vidaMaxima < 0.25){ // Se a vida for menor que 25%
            action = rng.nextInt(3);
        } else {
            action = rng.nextInt(2);
        }

        switch(action){
            case 0:
                return ferir(jogador);

            case 1:
                return defender(true);

            case 2:
                return curar();

            default:
                System.out.println("Opção inválida.");
        }
        return false;

    }
}
