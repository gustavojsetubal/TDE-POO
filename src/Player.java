abstract class Player extends Entidade {
    // Função: atribuir o aspecto de jogabilidade ao jogador
    protected AbilityHandler abilityHandler;

    public Player(String nome, Arma armaAtual) {
        super(nome, armaAtual);
    }

    abstract class AbilityHandler{
        // Função: lidar com a habilidade da classe

        protected int cooldownHabilidadeAtual;
        protected static int cooldownHabilidade;
        protected abstract boolean usarHabilidade(boolean estado);
    }

    public boolean defineAction(Entidade oponente){
        System.out.println("Escolha sua ação:");

        // Seleção de ação
        int escolha = 0;
        Boolean escolhaValida = false;
        while (!escolhaValida){
            System.out.println("1) Atacar");
            System.out.println("2) Defender");
            System.out.println("3) Curar");
            System.out.println("4) Habilidade");
            try{
                escolha = Integer.parseInt(input.next());
                if (escolha == 1 || escolha == 2 || escolha == 3 || escolha == 4){
                    escolhaValida = true;
                } else {
                    System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException error){
                System.out.println("Opção inválida.");
            }
        }

        System.out.println("\n\n\n");
        switch (escolha){
            case 1:
                return actionHandler.atacar(oponente);
            case 2:
                return actionHandler.setDefesa(true);
            case 3:
                return actionHandler.curar();
            case 4:
                return abilityHandler.usarHabilidade(true);
            default:
                System.out.println("Opção inválida.");
        }

        return false;
    }
}

// Classes
class Guerreiro extends Player {
    // Atributos da Classe
    public Guerreiro(String nome, Arma armaAtual) {
        super(nome, armaAtual);
        this.vidaMaxima = 500;
        this.vidaAtual = vidaMaxima;
        this.baseAtk = 125;
    }


    // Fúria: habilidade ativa da Classe
    class AbilityHandler{
        protected int cooldownHabilidadeAtual = 0;
        protected static int cooldownHabilidade = 1;

        public boolean usarHabilidade(boolean estado) {
            if (cooldownHabilidadeAtual > 0) { // Se o cooldown ainda estiver em andamento
                System.out.println("A habilidade falhou!");
            } else {
                if (estado){ // true indica que a habilidade está sendo ativada
                    cooldownHabilidadeAtual = 1;
                    StatusHandler.printStatusMessage(/*Encontrar instância de StatusFuria em statusList*/, "inicio-efeito");
                    statusHandler.addStatus("StatusFuria");
                } else { // false indica que a habilidade está sendo ativada
                    System.out.println("[-Fúria] " + "A raiva de " + nome + " se esvaiu...");
                }
            }
            return false;
        }

        public boolean tickCooldownHabilidade(){
            cooldownHabilidadeAtual--;
            if (cooldownHabilidadeAtual <= 0){
                cooldownHabilidadeAtual = 0;
                return true; // Retorna true quando habilidade está disponível
            }
            return false; // Retorna falso quando habilidade continua indisponível
        }

    }

}

public class Ladino extends Player {
    // Atributos da Classe
    public Ladino(String nome, Arma armaAtual) {
        super(nome, armaAtual);
        this.vidaMaxima = 300;
        this.vidaAtual = vidaMaxima;
        this.baseAtk = 175;
    }


    // Evasão: habilidade ativa de classe
    class AbilityHandler{
        protected int cooldownHabilidadeAtual = 0;
        protected static int cooldownHabilidade = 3;

        public boolean usarHabilidade(boolean estado) {
            if (cooldownHabilidadeAtual > 0) { // Se o cooldown ainda estiver em andamento
                System.out.println("A habilidade falhou!");
            } else {
                if (estado){ // true indica que a habilidade está sendo ativada
                    cooldownHabilidadeAtual = 1;
                    System.out.println("[+Fúria] " + " se enfurece!");
                    statusHandler.addStatus("statusFuria");
                } else { // false indica que a habilidade está sendo ativada
                    System.out.println("[-Fúria] " + "A raiva de " + nome + " se esvaiu...");
                }
            }
            return false;
        }

        public boolean tickCooldownHabilidade(){
            cooldownHabilidadeAtual--;
            if (cooldownHabilidadeAtual <= 0){
                cooldownHabilidadeAtual = 0;
                return true; // Retorna true quando habilidade está disponível
            }
            return false; // Retorna falso quando habilidade continua indisponível
        }

    }


}


