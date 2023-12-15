package ExercicioFabricio;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



import java.util.Scanner;

class Loja {
    String codigo;
    String nome;
    double valor;

    public Item(String codigo, String nome, double valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
    }
}

class Vitrine {
    List<Item> itens;

    public Vitrine() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public void removerItem(String codigo) {
        itens.removeIf(item -> item.codigo.equals(codigo));
    }

    public void atualizarPrecoItem(String codigo, double novoPreco) {
        for (Item item : itens) {
            if (item.codigo.equals(codigo)) {
                item.valor = novoPreco;
                break;
            }
        }
    }
}

class CarrinhoDeCompra {
    List<ItemDoCarrinho> itensDoCarrinho;

    public CarrinhoDeCompra() {
        this.itensDoCarrinho = new ArrayList<>();
    }

    public void adicionarItem(ItemDoCarrinho itemDoCarrinho) {
        itensDoCarrinho.add(itemDoCarrinho);
    }
}

class ItemDoCarrinho {
    Item item;
    int quantidade;

    public ItemDoCarrinho(Item item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }
}

class Loja {
    Vitrine vitrine;
    CarrinhoDeCompra carrinhoDeCompra;

    public Loja() {
        this.vitrine = new Vitrine();
        this.carrinhoDeCompra = new CarrinhoDeCompra();
    }

    public void verVitrine() {
        System.out.println("Produtos disponíveis na vitrine:");
        for (Item item : vitrine.itens) {
            System.out.println("Código: " + item.codigo + ", Nome: " + item.nome + ", Valor: " + item.valor);
        }
    }

    public void verCarrinhoDeCompra() {
        System.out.println("Itens no carrinho de compra:");
        for (ItemDoCarrinho itemDoCarrinho : carrinhoDeCompra.itensDoCarrinho) {
            System.out.println("Código: " + itemDoCarrinho.item.codigo + ", Nome: " + itemDoCarrinho.item.nome
                    + ", Quantidade: " + itemDoCarrinho.quantidade);
        }
    }

    public void comprarItem(String codigo, int quantidade) {
        Item itemSelecionado = null;
        for (Item item : vitrine.itens) {
            if (item.codigo.equals(codigo)) {
                itemSelecionado = item;
                break;
            }
        }

        if (itemSelecionado != null) {
            if (quantidade <= 0) {
                System.out.println("Quantidade inválida. Tente novamente.");
                return;
            }

            ItemDoCarrinho itemDoCarrinho = new ItemDoCarrinho(itemSelecionado, quantidade);
            carrinhoDeCompra.adicionarItem(itemDoCarrinho);

            System.out.println("Item adicionado ao carrinho de compra: " + itemSelecionado.nome + ", Quantidade: " + quantidade);
        } else {
            System.out.println("Produto não encontrado na vitrine. Verifique o código e tente novamente.");
        }
    }

    public void realizarPagamento(boolean pagamentoAVista) {
        double totalCompra = calcularTotalCompra();

        if (pagamentoAVista) {
            double desconto = totalCompra * 0.1;
            totalCompra -= desconto;
            System.out.println("Desconto aplicado: 10%");
        }

        exibirResumoCompra(totalCompra);
    }

    private double calcularTotalCompra() {
        double total = 0.0;
        for (ItemDoCarrinho itemDoCarrinho : carrinhoDeCompra.itensDoCarrinho) {
            total += itemDoCarrinho.item.valor * itemDoCarrinho.quantidade;
        }
        return total;
    }

    private void exibirResumoCompra(double totalCompra) {
        System.out.println("Resumo da Compra:");
        for (ItemDoCarrinho itemDoCarrinho : carrinhoDeCompra.itensDoCarrinho) {
            System.out.println("Item: " + itemDoCarrinho.item.nome +
                    ", Quantidade: " + itemDoCarrinho.quantidade +
                    ", Subtotal: " + (itemDoCarrinho.item.valor * itemDoCarrinho.quantidade));
        }
        System.out.println("Total da Compra: " + totalCompra);
    }

    public void cadastrarItem(String codigo, String nome, double valor) {
        Item novoItem = new Item(codigo, nome, valor);
        vitrine.adicionarItem(novoItem);
        System.out.println("Item cadastrado: " + codigo + ", nome: " + nome + ", valor:" + valor);
    }

    public void removerItem(String codigo) {
        vitrine.removerItem(codigo);
        System.out.println("Item removido: " + codigo);
    }

    public void atualizarPrecoItem(String codigo, double novoPreco) {
        vitrine.atualizarPrecoItem(codigo, novoPreco);
        System.out.println("Preço atualizado para o item " + codigo + ": " + novoPreco);
    }

    public void acessarOpcoesDoAdministrador() {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\nOpções do Administrador:");
            System.out.println("1. Cadastrar Item");
            System.out.println("2. Remover Item");
            System.out.println("3. Atualizar Preço do Item");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Digite o código do novo item: ");
                    String codigoNovoItem = scanner.nextLine();
                    System.out.print("Digite o nome do novo item: ");
                    String nomeNovoItem = scanner.nextLine();
                    System.out.print("Digite o valor do novo item: ");
                    double valorNovoItem = scanner.nextDouble();
                    cadastrarItem(codigoNovoItem, nomeNovoItem, valorNovoItem);
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.print("Digite o código do item a ser removido: ");
                    String codigoItemRemover = scanner.nextLine();
                    removerItem(codigoItemRemover);
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("Digite o código do item a ter o preço atualizado: ");
                    String codigoItemAtualizar = scanner.nextLine();
                    System.out.print("Digite o novo preço para o item: ");
                    double novoPreco = scanner.nextDouble();
                    atualizarPrecoItem(codigoItemAtualizar, novoPreco);
                    break;
                case 0:
                    System.out.println("Retornando ao Menu Principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }

    public static void main(String[] args) {
        Loja loja = new Loja();
        Scanner entrada = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("Menu Principal:");
            System.out.println("1. Ver Vitrine");
            System.out.println("2. Ver Carrinho de Compra");
            System.out.println("3. Comprar Item");
            System.out.println("4. Realizar Pagamento");
            System.out.println("5. Acessar Opções do Administrador");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    loja.verVitrine();
                    break;
                case 2:
                    loja.verCarrinhoDeCompra();
                    break;
                case 3:
                    System.out.print("Informe o código do produto: ");
                    String codigoProduto = entrada.next();
                    System.out.print("Informe a quantidade: ");
                    int quantidade = entrada.nextInt();
                    loja.comprarItem(codigoProduto, quantidade);
                    break;
                case 4:
                    System.out.print("Pagamento à vista? (true/false): ");
                    boolean pagamentoAVista = entrada.nextBoolean();
                    loja.realizarPagamento(pagamentoAVista);
                    break;
                case 5:
                    loja.acessarOpcoesDoAdministrador();
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        entrada.close();
    }
}
}