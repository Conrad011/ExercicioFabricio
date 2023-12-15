package ExercicioFabricio;

import java.util.ArrayList;
public class Vitrine {
    public ArrayList<Item> itens;

    public Vitrine() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }
}
