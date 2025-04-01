import java.util.Scanner;

public class GameHandler {
    public static Entidade jogador;
    public static Mob adversario;

    private static Turno turnoAtual;
    private static int salaAtual;
    private static int rodadaAtual;
    private static Boolean emBatalha;

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
        this.salaAtual = 1;
        rodadaAtual = 1;
    }


    public void iniciarJogo() {
        System.out.println("-= cmdventure =- \n");
        System.out.println("-= Insira um nome =-");
        String playerName = input.next();
        System.out.println("-= Escolha sua classe =-");

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

    // Exibição de cenário de jogo
    public void displayGameScenario(Entidade jogador, Mob adversario){
        jogador.displayEntityScenario();
        adversario.displayEntityScenario();
    }


    public void iniciarSala(){
        // Gerenciar Spawns
        adversario = Mob.gerarMob(adversario, salaAtual, (salaAtual % 5 == 0));
        emBatalha = true;

        // Iniciar batalha
        System.out.println("\n\n\n-= Sala " + salaAtual + " =-");
        while (emBatalha){ // Enquanto a sala não estiver completa
            displayGameScenario(jogador, adversario);
            if (turnoAtual == Turno.JOGADOR) {
                // Início de turno
                jogador.defesa = false;
                System.out.println("Rodada: " + rodadaAtual);
                System.out.println();

                //Turno das ações do jogador
                System.out.println("Turno: " + turnoAtual);
                System.out.println();
                jogador.cooldownHabilidade--;
                if (!jogador.handleIdle()){
                    if (jogador.defineAction(adversario)){
                        emBatalha = false;
                        break;
                    }
                }
                jogador.handleStatus();
                pressEnterToContinue();
                turnoAtual = Turno.ADVERSARIO;// Passa para a máquina

            } else {
                //Turno das ações da máquina
                adversario.defesa = false;
                System.out.println("Turno: " + turnoAtual);
                System.out.println("\n");
                adversario.cooldownHabilidade--;
                if (!adversario.handleIdle()){
                    if (adversario.defineAction(jogador)){
                        break;
                    }
                }
                adversario.handleStatus();
                pressEnterToContinue();
                turnoAtual = Turno.JOGADOR; // Volta para o jogador
                rodadaAtual++;
            }
        }

        // Fim da batalha
        if (jogador.estaVivo()){
            System.out.println("Voce derrotou o inimigo!");
            System.out.println("\nEscolha sua recompensa e vá pra próxima sala.");

            // Gerar loot
            Upgrade loot1 = Upgrade.gerarItem(salaAtual);
            Upgrade loot2 = Upgrade.gerarItem(salaAtual);
            Arma loot3 = Arma.gerarArma(salaAtual);

            // Seleção de classe
            int escolha = 0;
            boolean escolhaValida = false;
            while (!escolhaValida){
                System.out.println("1) Benção: " + loot1 + loot1.mostrarUpgrade());
                System.out.println("2) Benção: " + loot2 + loot2.mostrarUpgrade());
                System.out.println("3) Arma: " + loot3);
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
                    jogador.setArmaAtual(loot3);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            salaAtual += 1;
            iniciarSala();
        } else {
            System.out.println("\n\n-= Fim de jogo =-");
            System.out.println("Você sobreviveu " + salaAtual + " salas dentro da masmorra.");
        }
    }

    // Getters
    public static Turno getTurnoAtual() {
        return turnoAtual;
    }

    public static int getSalaAtual() {
        return salaAtual;
    }

    public static int getRodadaAtual() {
        return rodadaAtual;
    }

    public static Boolean getEmBatalha() {
        return emBatalha;
    }
}
