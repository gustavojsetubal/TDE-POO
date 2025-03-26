import java.util.Random;

public class Upgrade {
    static Random rng = new Random(System.currentTimeMillis());

    public String nome;
    public int aumentoVida;
    public int aumentoDano;

    public Upgrade(String nome, int aumentoVida, int aumentoDano) {
        this.nome = nome;
        this.aumentoVida = aumentoVida;
        this.aumentoDano = aumentoDano;
    }

    // Geração
    static int genAumentoVida;
    static int genAumentoDano;
    public static Upgrade gerarItem(int sala) {
        int tipo = rng.nextInt(); // 0 = Vida, 1 = Dano
        int atributo = (int) (1 + rng.nextInt(5) + rng.nextInt(sala));

        if (tipo == 0) {
            return new Upgrade(NameHandler.generateUpgrade(), atributo , 0);
        } else {
            return new Upgrade(NameHandler.generateUpgrade(), 0 , atributo);
        }
    }

    public String mostrarUpgrade(){
        if (aumentoDano != 0){
            return " | +" + aumentoDano + " dano";
        } else if (aumentoVida != 0){
            return " | +" + aumentoVida + " vida";
        }

        return "Upgrade inválido.";
    }

    public void escolhaUpgrade (Entidade jogador){
        jogador.atkBase += aumentoDano;
        jogador.vidaMaxima += aumentoVida;
    }

    @Override
    public String toString() {
        return nome;
    }
}
