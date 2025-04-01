import java.util.Map;
import java.util.Random;

public abstract class Enemy extends Entity {
    // Função: tratar da IA de um inimigo

    static Random rng = new Random(System.currentTimeMillis());
    public Enemy(String nome, Arma armaAtual) {
        super(nome, armaAtual);
    }

    // Banco de ações e suas chances
    Map<String, Double> actionBank = Map.ofEntries(
            Map.entry("atacar", 0.6),
            Map.entry("defender", 0.3),
            Map.entry("curar", 0.1)
    );

    // Gera ação aleatória dentre disponíveis
    protected String generateAction() {
        // Valor total de pesos (não precisa resultar em 1)
        float totalPool = 0;
        for (Double actionChance : actionBank.values()){
            totalPool += actionChance;
        }

        // Sorteia ação dentre banco
        double r = rng.nextDouble(0, totalPool);
        for (Map.Entry<String, Double> action : actionBank.entrySet()){
            if (r < action.getValue()){
                return action.getKey();
            }
            r -= action.getValue();
        }
        return "atacar";
    }

    abstract boolean defineAction(Entity oponente);
}

public class Mob extends Enemy {
    // Função: delegar atributos e capacidades de inimigos normais

    // Construtor simples, sem atributos
    public Mob(String nome, Arma armaAtual, int vidaMaxima, int atkBase) {
        super(nome, armaAtual);
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.baseAtk = atkBase;
        this.defesa = false;
    }

    // Seleciona ação com base no valor gerado aleatóriamente
    public boolean defineAction(Entity oponente){
        switch(generateAction()) {
            case "atacar":
                return actionHandler.atacar(oponente);

            case "defender":
                return actionHandler.setDefesa(true);

            case "curar":
                return actionHandler.curar();
        }
        return actionHandler.atacar(oponente);
    }

    static class SpawnManager {
        // Função: lidar com a geração de inimigos normais

        // Valores de vida e ataque gerados aleatóriamente
        static int genVidaMaxima;
        static int genAtkBase;

        // Gera multiplicador de atributos aleatório
        static float genMultiplier(){
            return (float) rng.nextDouble(0.1);
        }

        // Cria um mob com nome e atributos aleatórios
        public static Mob generateMob(Arma armaAtual, int salaAtual) {
            genVidaMaxima = (int) Math.round(1000 * (genMultiplier() * salaAtual));
            genAtkBase = (int) Math.round(750 * (genMultiplier() * salaAtual));

            return new Mob(NameHandler.generateMonster(), null, genVidaMaxima, genAtkBase);
        }
    }
}

class Boss extends Enemy {
    // Função: delegar atributos e capacidades de inimigos normais

    // Alteração no banco de ação


    // Construtor simples, sem atributos
    public Boss(String nome, Arma armaAtual, int vidaMaxima, int atkBase) {
        super(nome, armaAtual);
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.baseAtk = atkBase;
        this.defesa = false;
    }

    // Seleciona ação com base no valor gerado aleatóriamente
    public boolean defineAction(Entity oponente){
        switch(generateAction()) {
            case "atacar":
                return actionHandler.atacar(oponente);

            case "defender":
                return actionHandler.setDefesa(true);

            case "curar":
                return actionHandler.curar();
        }
        return actionHandler.atacar(oponente);
    }

    static class SpawnManager {
        // Função: lidar com a geração de inimigos boss

        // Valores de vida e ataque gerados aleatóriamente
        static int genVidaMaxima;
        static int genAtkBase;

        // Gera multiplicador de atributos aleatório
        static float genMultiplier(){
            return (float) rng.nextDouble(0.1);
        }

        // Cria um boss com nome e atributos aleatórios
        public static Boss generateMob(Arma armaAtual, int salaAtual) {
            genVidaMaxima = (int) Math.round(1000 * (genMultiplier() * salaAtual) * 2);
            genAtkBase = (int) Math.round(750 * (genMultiplier() * salaAtual) * 1.25);

            return new Boss(NameHandler.generateMonster() + " [BOSS]", null, genVidaMaxima, genAtkBase);
        }
    }
}
