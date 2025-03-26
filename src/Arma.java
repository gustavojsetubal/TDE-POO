import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Arma {
    static Random rng = new Random(System.currentTimeMillis());

    public final String nome;
    public final int atkExtra;
    public final int raridade; // 0 = comum, 1 = incomum, 2 = raro, 3 = épico, 4 = lendário

    public Arma(String nome, int atkExtra, int raridade) {
        this.nome = nome;
        this.atkExtra = atkExtra;
        this.raridade = raridade;
    }

    // Probabilidades de raridade
    private static final int raridadeComum = 50;
    private static final int raridadeIncomum = 30;
    private static final int raridadeRaro = 15;
    private static final int raridadeEpico = 4;
    private static final int raridadeLendario = 1;

    // Geração
    static int genAtkExtra;
    static int genRaridade;
    public static Arma gerarArma(int salaAtual) {
        int genRaridadeRng = rng.nextInt(100);
        if (genRaridadeRng <= raridadeComum && genRaridadeRng > raridadeIncomum){
            genRaridade = 0;
        } else if (genRaridadeRng <= raridadeIncomum && genRaridadeRng > raridadeRaro) {
            genRaridade = 1;
        } else if (genRaridadeRng <= raridadeRaro && genRaridadeRng > raridadeEpico) {
            genRaridade = 2;
        } else if (genRaridadeRng <= raridadeEpico && genRaridadeRng > raridadeLendario) {
            genRaridade = 3;
        } else if (genRaridadeRng <= raridadeLendario) {
            genRaridade = 4;
        }

        genAtkExtra = (int) Math.round(500 * (0.1 * salaAtual) * genRaridade + 1);

        return new Arma(NameHandler.generateWeapon(), genAtkExtra, genRaridade);

    }

    // Getters
    public int getAtkExtra() {
        return atkExtra;

    }

    public String getRaridade(){
        if (raridade == 0) {
            return "Comum";
        } else if (raridade == 1){
            return "Incomum";
        } else if (raridade == 2){
            return "Raro";
        } else if (raridade == 3){
            return "Épico";
        } else {
            return "Lendário";
        }
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome + " (" + getRaridade() + ") | +" + getAtkExtra() + " dano";
    }
}
