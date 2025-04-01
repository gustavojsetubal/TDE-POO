public class Main {
    public static void main(String[] args) {
        /*GameHandler jogo = new GameHandler();

        jogo.iniciarJogo();*/

        Player a = new Guerreiro("Carlos", null);
        System.out.println(a.abilityHandler.playerInstance);
        System.out.println(a);
    }
}