import java.util.Random;

public class Mob extends Entidade{
    static Random rng = new Random(System.currentTimeMillis());
    public boolean isBoss;


    // Construtor simples, sem atributos
    public Mob(String nome, Arma armaAtual, int vidaMaxima, int atkBase, boolean isBoss) {
        super(nome, armaAtual);
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = vidaMaxima;
        this.baseAtk = atkBase;
        this.defesa = false;
        this.isBoss = isBoss;

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
            return new Mob(NameHandler.generateMonster() + " [BOSS]", null, genVidaMaxima, genAtkMaximo,true);
        }


        return new Mob(NameHandler.generateMonster(), null, genVidaMaxima, genAtkMaximo,false);
    }

    @Override
    public boolean habilidade(Boolean estado) {
        if (isBoss) {
            if (estado){
                if (cooldownHabilidade > 0) {
                    System.out.println("A habilidade falhou!");
                    return false;
                }
                cooldownHabilidade = 4;
                System.out.println("[+Frenesi] " + nome + " começou a preparar um grande ataque...");
                this.statusList.add(StatusHandler.statusFrenesi(this, GameHandler.getRodadaAtual()));
                return false;
            } else {
                System.out.println("[-Frenesi] " + "A fúria de " + nome + " te alcançou!");
                return GameHandler.jogador.handleDanoRecebido(baseAtk * 2);
            }
        }
        return false;
    }



    // Seleção de ação
    // Probabilidades de raridade

    // Ataque: 60% (100 - 40)
    // Defesa: 30% (40 - 10)
    // Cura: 10% (10 - 0)
    // Habilidade(BOSS): 15% da chance de defesa

    public boolean defineAction(Entidade jogador){
        int action = 0;
        if ((double) vidaAtual / vidaMaxima < 0.25){ // Se a vida for menor que 25%
            action = rng.nextInt(100);
        } else {
            action = rng.nextInt(80) + 20;
        }

        if (action > 40){
            return atacar(jogador);
        } else if (action > 10){
            if (isBoss){
                int ultChance = rng.nextInt(100);
                if (ultChance >= 50){
                    return habilidade(true);
                } else {
                    return setDefesa(true);
                }
            }

        } else if (action <= 10){
            return curar();
        }
        return false;

    }
}
