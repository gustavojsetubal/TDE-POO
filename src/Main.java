import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Jogador player = new Guerreiro("Carlos", null);

        player.habilidade(true);
        System.out.println(player.handleDanoAtual());
        player.habilidade(false);
        System.out.println(player.listaAtributos);
        System.out.println(player.handleDanoAtual());

    }
}