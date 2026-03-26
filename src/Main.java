import model.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import util.DatabaseService;
import util.ProdutoDAO;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(java.util.Locale.US);

        boolean rodando = true;

        try {
            DatabaseService.inicializar();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
        }
        ProdutoDAO dao = new ProdutoDAO();

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

                    try {
                        dao.adicionar(novoProduto);
                        System.out.println("Produto adicionado com sucesso!");
                    } catch (SQLException e) {
                        System.out.println("Erro ao adicionar: " + e.getMessage());
                    }
                    break;

                case 2:

                    try {
                        ArrayList<Produto> lista = dao.listarTodos();
                        if (lista.isEmpty()) {
                            System.out.println("Nenhum produto cadastrado!");
                        } else {
                            for (Produto produto : lista) {
                                System.out.println(produto);
                            }
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao listar: " + e.getMessage());
                    }

                    break;

                case 3:
                    System.out.println("Qual produto deseja buscar?: ");
                    String nomeBusca = scanner.nextLine();
                    try {
                        Produto resultado = dao.buscar(nomeBusca);

                        if (resultado != null) {
                            System.out.println("Produto encontrado:  " + resultado);
                        } else {
                            System.out.println("Produto não encontrado");
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro ao buscar: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("Qual produto deseja remover?: ");
                    String nomeRemover = scanner.nextLine();

                    try {
                        dao.remover(nomeRemover);
                        System.out.println("Produto removido com sucesso!");
                    } catch (SQLException e) {
                        System.out.println("Erro ao remover: " + e.getMessage());
                    }
                    break;

                case 0:
                    rodando = false;
                    break;
            }
        }

        scanner.close();
    }
}