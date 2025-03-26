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
        float bossMultiplierVida = 1;
        float bossMultiplierAtk = 1;
        if (isBoss) {
            bossMultiplierVida = 2;
            bossMultiplierAtk = 1.25F;
        }
        genVidaMaxima = (int) Math.round(1000 * (genMultiplier() * salaAtual) * bossMultiplierVida);
        genAtkMaximo = (int) Math.round(750 * (genMultiplier() * salaAtual) * bossMultiplierAtk);

        if (isBoss) {
            return new Mob(NameHandler.generateMonster() + "[BOSS]", null, genVidaMaxima, genAtkMaximo);
        }

        return new Mob(NameHandler.generateMonster(), null, genVidaMaxima, genAtkMaximo);
    }



    // Seleção de ação
    // Probabilidades de raridade

    // Ataque: 60% (100 - 40)
    // Defesa: 30% (40 - 10)
    // Cura: 10% (10 - 0)

    public boolean defineAction(Entidade jogador){
        int action = 0;
        if ((double) vidaAtual / vidaMaxima < 0.25){ // Se a vida for menor que 25%
            action = rng.nextInt(100);
        } else {
            action = rng.nextInt(80) + 20;
        }

        if (action > 40){
            return ferir(jogador);
        } else if (action > 10){
            return defender(true);
        } else if (action <= 10){
            return curar();
        }
        return false;

    }
}
