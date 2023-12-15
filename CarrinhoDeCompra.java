package ExercicioFabricio;

import java.util.ArrayList;

public class CarrinhoDeCompra {
    public ArrayList<ItemDoCarrinho> itensDoCarrinho;

    public CarrinhoDeCompra() {
        this.itensDoCarrinho = new ArrayList<>();
    }

    public void adicionarItem(ItemDoCarrinho itemDoCarrinho) {
        this.itensDoCarrinho.add(itemDoCarrinho);
    }
}