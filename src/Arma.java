public class Arma {
    private String nome;
    private int danoExtra;

    public Arma(String nome, int danoExtra) {
        this.nome = nome;
        this.danoExtra = danoExtra;
    }

    public int getDanoExtra() {
        return danoExtra;

    }

    public String getNome() {
        return nome;
    }
}
