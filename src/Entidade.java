import java.util.ArrayList;
import java.util.List;

public class Entidade {
    public String nome;
    public int vidaAtual;
    public int vidaMaxima;
    public int atkBase;
    public boolean defesa = false;
    public Arma armaAtual = null;
    public List<Status> listaAtributos = new ArrayList<>();

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
            armaDano = armaAtual.getDanoExtra();
        } catch (NullPointerException error){
            armaDano = 0;
        }
        return atkBase + armaDano + atkEfeito;
    }

    // Handler de Dano Recebido
    public Boolean handleDanoRecebido(int dmg){
        vidaAtual -= dmg;
        System.out.println(nome + " recebeu " + dmg + "de dano.");

        if (vidaAtual <= 0) {
            System.out.println(nome + " foi derrotado!");
            return false; // Retorna false se estiver morto.
        } else {
            System.out.println(nome + " agora possui " + vidaAtual + "de vida.");
            return true; // Retorna true se estiver vivo.
        }
    }

    // Handler de Cura Recebida
    public void handleCura(int quantia) {
        int cura = quantia;
        if (vidaAtual + cura > vidaMaxima) {
            vidaAtual = vidaMaxima;
            System.out.println(nome + " se curou! Vida atual: " + vidaAtual);
        } else {
            vidaAtual += cura;
            System.out.println(nome + " se curou! Vida atual: " + vidaAtual);
        }
    }


    // Ação: Ferir
    public void ferir(Entidade adversario){
        int dmg = handleDanoAtual();

        System.out.println(nome + " ataca " + adversario.nome + " com " + armaAtual.getNome());
        if(adversario.defesa){
            dmg /= 4;
            adversario.defesa = false;
            System.out.println(adversario.nome + " se defendeu! Dano reduzido em 75%.");
        }
        adversario.handleDanoRecebido(dmg);

    }

    // Ação: Curar
    public void curar(){
        handleCura(15);
    }

    // Ação: Defender
    public void defender(Boolean estado){
        this.defesa = estado;
    }

    // Ação: Habilidade
    public void habilidade(Boolean estado){
        // Definido por classe
    }


    // Getters
    public String getNome() {
        return nome;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }
}

// 500 / 125 - Guerreiro
// 300 / 175 - Ladino
// 200 / 250 - Mago

// Inimigo base: 300 / 100

// Ladino: 75hp 25atk
// Mago: 50hp 45atk
// Guerreiro: 100hp 20atk

