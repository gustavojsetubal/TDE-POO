import java.util.ArrayList;
import java.util.List;

public class Jogador {
    public String nome;
    public int vidaAtual;
    public int vidaMaxima;
    public int atkBase;
    public boolean defesa = false;
    public Arma armaAtual = null;
    public List<Status> listaAtributos = new ArrayList<>();

    public Jogador(String nome, Arma armaAtual) {
        this.nome = nome;
        // this.vidaMaxima = Herda de classe;
        // this.danoBase = Herda de classe;
        this.defesa = true;
        this.armaAtual = armaAtual;
    }

    // Handler de Dano
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

    // Handler de Cura
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
    public void ferir(Jogador adversario){
        int dmg = handleDanoAtual();

        System.out.println(nome + " ataca " + adversario.nome + " com " + armaAtual.getNome());
        if(adversario.defesa){
            dmg /= 4;
            adversario.defesa = false;
            System.out.println(adversario.nome + " se defendeu! Dano reduzido para " + dmg);
            System.out.println(adversario.nome + " agora possui " + adversario.vidaAtual + "de vida!!");
        } else {
            if (adversario.vidaAtual <= 0) {
                System.out.println(adversario.nome + " foi derrotado!");
            }
            else {
                adversario.vidaAtual -= dmg;
            }
        }
    }

    public void defender(Boolean estado){
        this.defesa = estado;
    }

    public void habilidade(Boolean estado){
        // Definido por classe
    }

    public boolean estaVivo() {
        return vidaAtual > 0;
    }

    public String getNome() {
        return nome;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }
}

// 75 / 25 - Guerreiro
// 50 / 50 - Ladino
// 25 / 75 - Mago

// Ladino: 75hp 25atk
// Mago: 50hp 45atk
// Guerreiro: 100hp 20atk

