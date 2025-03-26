import java.util.Scanner;

public class GameHandler {
    public Entidade jogador;
    public Mob adversario;

    private Turno turnoAtual;
    private int salas;
    public static int rodada;
    private Boolean batalha;

    Scanner input = new Scanner(System.in).useDelimiter("\n");

    // Utilidades
    private void pressEnterToContinue()
    {
        System.out.println("\nPressione ENTER pra continuar...");
        try
        {
            input.next();
        }
        catch(Exception _)
        {}
    }

    // Game Handler
    public GameHandler() {
        this.turnoAtual = Turno.JOGADOR;
        this.salas = 1;
        rodada = 1;
    }

    public void iniciarJogo() {
        System.out.println("Iniciando o jogo...");
        System.out.println();
        System.out.println("Insira um nome:");
        String playerName = input.next();
        System.out.println("Escolha sua classe:");

        // Seleção de classe
        int escolha = 0;
        boolean escolhaValida = false;
        while (!escolhaValida){
            System.out.println("1) Guerreiro");
            System.out.println("2) Ladino");
            System.out.println("3) Mago");
            try{
                escolha = Integer.parseInt(input.next());
                if (escolha == 1 || escolha == 2 || escolha == 3){
                    escolhaValida = true;
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException error){
                System.out.println("Opção inválida.");
            }
        }

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

        iniciarSala();

    }

    public void displayScenario(Entidade jogador, Mob adversario){
        int armaDano;
        try {
            armaDano = jogador.armaAtual.getAtkExtra();
            System.out.println(jogador.nome + " | " + jogador.vidaAtual + " / " + jogador.vidaMaxima + "HP " + jogador.listaAtributos + " | " + jogador.atkBase + " | " + jogador.armaAtual.getNome() + " (" + jogador.armaAtual.getRaridade() + "): " + armaDano + " ATK " );

        } catch (NullPointerException error){
            System.out.println(jogador.nome + " | " + jogador.vidaAtual + " / " + jogador.vidaMaxima + "HP " + jogador.listaAtributos + " | " + jogador.atkBase + " ATK " );
        }

        System.out.println(adversario.nome + " | " + adversario.vidaAtual + " / " + adversario.vidaMaxima + "HP " + adversario.listaAtributos + " | " + adversario.atkBase + " ATK | ");
    }


    public void iniciarSala(){
        // Gerenciar Spawns
        adversario = Mob.gerarMob(adversario, salas, (salas % 5 == 0));
        batalha = true;

        // Iniciar batalha
        System.out.println("\n\n\nSala: " + salas);
        while (batalha){ // Enquanto a sala não estiver completa
            displayScenario(jogador, adversario);
            if (turnoAtual == Turno.JOGADOR) {
                // Início de turno
                jogador.defesa = false;
                System.out.println("Rodada: " + rodada);
                System.out.println();

                //Turno das ações do jogador
                System.out.println("Turno: " + turnoAtual);
                System.out.println();
                jogador.cooldownHabilidade--;
                if (jogador.defineAction(adversario)){
                    batalha = false;
                    break;
                };
                jogador.handleStatus();
                pressEnterToContinue();
                turnoAtual = Turno.ADVERSARIO;// Passa para a máquina

            } else {
                //Turno das ações da máquina
                adversario.defesa = false;
                System.out.println("Turno: " + turnoAtual);
                System.out.println("\n");
                adversario.cooldownHabilidade--;
                if (adversario.defineAction(jogador)){
                    break;
                };
                adversario.handleStatus();
                pressEnterToContinue();
                turnoAtual = Turno.JOGADOR; // Volta para o jogador
                rodada++;
            }
        }

        // Fim da batalha
        if (jogador.estaVivo()){
            System.out.println("Voce derrotou o inimigo!");
            System.out.println("\nEscolha sua recompensa e vá pra próxima sala.");

            // Gerar loot
            Upgrade loot1 = Upgrade.gerarItem(salas);
            Upgrade loot2 = Upgrade.gerarItem(salas);
            Arma loot3 = Arma.gerarArma(salas);

            // Seleção de classe
            int escolha = 0;
            boolean escolhaValida = false;
            while (!escolhaValida){
                System.out.println("1) " + loot1 + loot1.mostrarUpgrade());
                System.out.println("2) " + loot2 + loot2.mostrarUpgrade());
                System.out.println("3) " + loot3);
                try{
                    escolha = Integer.parseInt(input.next());
                    if (escolha == 1 || escolha == 2 || escolha == 3){
                        escolhaValida = true;
                    } else {
                        System.out.println("Opção inválida.");
                    }
                } catch (NumberFormatException error){
                    System.out.println("Opção inválida.");
                }
            }

            switch (escolha){
                case 1:
                    loot1.escolhaUpgrade(jogador);
                    break;
                case 2:
                    loot2.escolhaUpgrade(jogador);
                    break;
                case 3:
                    jogador.armaAtual = loot3;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            salas += 1;
            iniciarSala();
        } else {
            System.out.println("Fim de jogo");
            System.out.println("Você sobreviveu " + salas + "dentro da masmorra.");
        }
    }
}
