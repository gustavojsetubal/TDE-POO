import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Entidade {
    public String nome;
    public int vidaAtual;
    public int vidaMaxima;
    public int atkBase;
    public boolean defesa = false;
    public Arma armaAtual = null;
    public List<Status> listaAtributos = new ArrayList<>();
    public int cooldownHabilidade = 0;

    Scanner input = new Scanner(System.in).useDelimiter("\n");

    // Construtor de Entidade Simples
    public Entidade(String nome, Arma armaAtual) {
        this.nome = nome;
        // this.vidaMaxima = Herda de classe;
        // this.danoBase = Herda de classe;
        this.defesa = true;
        this.armaAtual = armaAtual;
    }

    // Construtor de Entidade Composto
    public Entidade(String nome, Arma armaAtual, List<Status> listaAtributos) {
        this.nome = nome;
        // this.vidaMaxima = Herda de classe;
        // this.danoBase = Herda de classe;
        this.defesa = true;
        this.armaAtual = armaAtual;
        this.listaAtributos = listaAtributos;
    }

    // Handler de Dano Causado
    public int handleDanoAtual(){
        int atkEfeito = 0;
        int armaDano = 0;
        for (Status atributo : listaAtributos){
            atkEfeito += atributo.atkChange;
        }

        // Lidar com
        try {
            armaDano = armaAtual.getAtkExtra();
        } catch (NullPointerException error){
            armaDano = 0;
        }
        return atkBase + armaDano + atkEfeito;
    }

    // Handler de Dano Recebido
    public Boolean handleDanoRecebido(int dmg){
        // Checar se Evasão ativou
        if (Status.doesStatusExist("Evasão", listaAtributos)) {
            dmg -= dmg * 1;
            System.out.println(nome + "se esquivou!");
            listaAtributos.remove(Status.searchStatus("Evasão", listaAtributos));
        }

        vidaAtual -= dmg;
        System.out.println(nome + " recebeu " + dmg + " de dano.");

        if (vidaAtual <= 0) {
            System.out.println(nome + " foi derrotado!");
            return true; // Retorna true se estiver morto.
        } else {
            System.out.println(nome + " agora possui " + vidaAtual + " de vida.");
            return false; // Retorna false se estiver vivo.
        }
    }

    // Handler de Cura Recebida
    public void handleCura(int quantia) {
        int cura = quantia;
        if (vidaAtual + cura > vidaMaxima) {
            vidaAtual = vidaMaxima;
        } else {
            vidaAtual += cura;
        }
        System.out.println(nome + " se curou!");
        System.out.println(nome + " agora possui " + vidaAtual + " de vida.");
    }

    // Handler de atributo
    public List<Status> toRemove = new ArrayList<>();
    public void handleStatus(){
        int dmgStatus = 0;

        if (!toRemove.isEmpty()){
            habilidade(false);
            listaAtributos.removeAll(toRemove);
        }
        listaAtributos.removeAll(toRemove);
        for (Status atributo : listaAtributos){
            dmgStatus += atributo.dmgStatus;
            atributo.tickStatus(this);
        }
        if (dmgStatus > 0){
            handleDanoRecebido(dmgStatus);
            System.out.println(dmgStatus);
        }

    }


    // Ação: Ferir
    public boolean ferir(Entidade adversario){
        int dmg = handleDanoAtual();

        try {
            System.out.println(nome + " ataca " + adversario.nome + "com " + armaAtual.getNome());
        } catch (NullPointerException e){
            System.out.println(nome + " ataca " + adversario.nome);
        }

        if(adversario.defesa){
            dmg /= 4;
            adversario.defesa = false;
            System.out.println(adversario.nome + " se defendeu! Dano reduzido em 75%.");
        }
        return adversario.handleDanoRecebido(dmg);

    }

    // Ação: Curar
    public boolean curar(){
        handleCura((int) (vidaMaxima * 0.3));
        return false;
    }

    // Ação: Defender
    public boolean defender(Boolean estado){
        this.defesa = estado;
        System.out.println(nome + " se prepara pro impacto!");
        return false;
    }

    // Ação: Habilidade
    public boolean habilidade(Boolean estado){
        // Definido por classe
        return false;
    }

    // Seleção de ação
    public boolean defineAction(Mob adversario){
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
                return ferir(adversario);
            case 2:
                return defender(true);
            case 3:
                return curar();
            case 4:
                return habilidade(true);
            default:
                System.out.println("Opção inválida.");
        }

        return false;
    }



    // Getters
    public Boolean estaVivo(){
        return vidaAtual > 0;
    }

    public String getNome() {
        return nome;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}


