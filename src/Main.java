import model.Produto;
import service.EstoqueService;

import java.util.ArrayList;
import java.util.Scanner;
import util.ArquivoService;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(java.util.Locale.US);

        boolean rodando = true;

        EstoqueService estoque = new EstoqueService();
        ArquivoService arquivoService = new ArquivoService();

        estoque.setProdutos(arquivoService.carregar());

        while (rodando) {
            System.out.println("=== Sistema de Estoque ===");
            System.out.println("1- Adicionar Produto");
            System.out.println("2- Listar Produtos");
            System.out.println("3- Buscar Produto");
            System.out.println("4- Remover Produto");
            System.out.println("0- Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Nome do Produto: ");
                    String nome = scanner.nextLine();

                    System.out.println("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Preço:");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();

                    Produto novoProduto = new Produto(nome, quantidade, preco);
                    estoque.adicionarProduto(novoProduto);
                    System.out.println("Produto adicionado com sucesso!");
                    break;

                case 2:
                    ArrayList<Produto> lista = estoque.listar();

                    if (lista.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado!");
                    } else {
                        for (Produto produto : lista) {
                            System.out.println(produto);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Qual produto deseja buscar?: ");
                    String nomeBusca = scanner.nextLine();

                    Produto resultado = estoque.buscarProduto(nomeBusca);

    
                    if(resultado != null){
                        System.out.println("Produto encontrado:  " + resultado);
                    }else {
                        System.out.println("Produto não encontrado");
                    }

                    break;

                case 4:
                    System.out.println("Qual produto deseja remover?: ");
                    String nomeRemover = scanner.nextLine();

                   

                    if(estoque.removerProduto(nomeRemover)) {
                        System.out.println("Produto removido com Sucesso!");
                    }else {
                        System.out.println("Produto não encontrado T.T");
                    }
                    break;

                case 0:
                    arquivoService.salvar(estoque.listar());    
                    rodando = false;
                    break;
            }
        }

        scanner.close();
    }
}