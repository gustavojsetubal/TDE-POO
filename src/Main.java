public class Main {
    public static void main(String[] args) {
        /*Entidade player = new Guerreiro("Carlos", null);

        player.habilidade(true);
        System.out.println(player.handleDanoAtual());
        player.habilidade(false);
        System.out.println(player.listaAtributos);
        System.out.println(player.handleDanoAtual());*/

        System.out.println(NameHandler.generateMonster());
        System.out.println(NameHandler.generateWeapon());
        System.out.println(NameHandler.generateUpgrade());

    }
}