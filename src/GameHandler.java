import java.util.Random;
import java.util.Scanner;

public class GameHandler {
    public Entidade jogador;
    public Mob adversario;

    private Turno turnoAtual;
    private int salas;
    private int rodada;
    private Boolean batalha;

    public GameHandler() {
        this.turnoAtual = Turno.JOGADOR;
        this.salas = 1;
        this.rodada = 1;
    }

    public void iniciarJogo() {
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        Random random = new Random();
        //Eu acho q precisa de random pra decidir as escolhas da maquina (qualquer coisa vamos retirar isso)

        System.out.println("Iniciando o jogo...");
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Insira um nome:");
        String playerName = input.next();
        System.out.println("Escolha sua classe:");

        // Seleção de classe
        int escolha = 0;
        System.out.println("1) Guerreiro");
        System.out.println("2) Ladino");
        System.out.println("3) Mago");

        switch (escolha){
            case 1:
                jogador = new Guerreiro(playerName, null);
                break;
            case 2:
                jogador = new Ladino(playerName, null);
                break;
            case 3:
                jogador = new Mago(playerName, null);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    public void iniciarSala(){
        // Gerenciar Spawns
        Mob.gerarMob(adversario, salas, (salas % 5 == 0));

        // Iniciar batalha
        System.out.println("Sala: " + salas);
        while (batalha){ // Enquanto a sala não estiver completa
            if (turnoAtual == Turno.JOGADOR) {
                // Início de turno
                System.out.println("Rodada: " + rodada);

                //Turno das ações do jogador
                System.out.println("Turno: " + turnoAtual);
                turnoAtual = Turno.MAQUINA;// Passa para a máquina

            } else {
                //Turno das ações da maquina
                System.out.println("Turno: " + turnoAtual);
                turnoAtual = Turno.JOGADOR; // Volta para o jogador
                rodada++;
            }
        }

        // Fim da batalha
        System.out.println("Voce derrotou o inimigo!");
        System.out.println("\nEscolha sua recompensa e vá pra proxima sala!");
        salas += 1;

        // Gerar loot
    }
}
