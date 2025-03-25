import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NameHandler {
    static Random rng = new Random(System.currentTimeMillis());

    // Nomes
    static List<String> monsterNameBank = new ArrayList<>(Arrays.asList(
            "Dragão", "Kobold", "Draugr", "Orc", "Goblin", "Espectro", "Lich", "Gnoll", "Troll", "Esqueleto",
            "Zumbi", "Golem", "Harpia", "Mantícora", "Basilisco", "Quimera", "Beholder", "Gárgula", "Wyvern", "Vampiro"
    ));

    static List<String> weaponNameBank = new ArrayList<>(Arrays.asList(
            "Espada Longa", "Machado de Guerra", "Adaga", "Martelo de Batalha", "Arco Longo",
            "Besta", "Lança", "Cajado", "Maça", "Foice", "Chicote", "Florete", "Clava",
            "Katana", "Espada Curta", "Mangual", "Tridente", "Sabre", "Alabarda", "Rapieira"
    ));

    static List<String> upgradeNameBank = new ArrayList<>(Arrays.asList(
            "Benção", "Luz", "Aura", "Essência", "Chama",
            "Eco", "Vínculo", "Cântico", "Reflexo", "Vontade",
            "Elo", "Visão", "Sopro", "Mantra", "Ascensão",
            "Coração", "Lembrança", "Sentença", "Energias", "Legado"
    ));

    // Adjetivos
    static List<String> monsterAdjectiveBank = new ArrayList<>(Arrays.asList(
            "Sombrio", "Voraz", "Ancestral", "Profano", "Espectral",
            "Sanguinário", "Implacável", "Maldito", "Colossal", "Frenético",
            "Corrompido", "Esquecido", "Poderoso", "Sinistro", "Abissal",
            "Cansado", "Sujo", "Grosseiro", "Enfurecido", "Pálido"
    ));

    static List<String> weaponAdjectiveBank = new ArrayList<>(Arrays.asList(
            "Afiado", "Enferrujado", "Destruidor", "Caótico", "Letal",
            "Antigo", "Pesado", "Quebrado", "Sagrado", "Macabro",
            "Violento", "Sinuoso", "Gélido", "Flamejante", "Rachado",
            "Brilhante", "Desgastado", "Venenoso", "Sombrio", "Amaldiçoado"
    ));

    static List<String> upgradeAdjectiveBank = new ArrayList<>(Arrays.asList(
            "Forte", "Gélido", "Eterno", "Radiante", "Sombrio",
            "Celestial", "Ancestral", "Efêmero", "Sagrado", "Profano",
            "Incandescente", "Sereno", "Caótico", "Ígneo", "Místico",
            "Puro", "Abissal", "Veloz", "Intenso", "Oculto"
    ));

    public static String generateMonster(){
        return (monsterNameBank.get(rng.nextInt(monsterNameBank.size())) + " " + monsterAdjectiveBank.get(rng.nextInt(monsterAdjectiveBank.size())));
    }

    public static String generateWeapon(){
        return (weaponNameBank.get(rng.nextInt(weaponNameBank.size())) + " " + weaponAdjectiveBank.get(rng.nextInt(weaponAdjectiveBank.size())));
    }

    public static String generateUpgrade(){
        return (upgradeNameBank.get(rng.nextInt(upgradeNameBank.size())) + " " + upgradeAdjectiveBank.get(rng.nextInt(upgradeAdjectiveBank.size())));
    }
}
