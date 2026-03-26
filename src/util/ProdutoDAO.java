package util;

import model.Produto;
import java.sql.*;
import java.util.ArrayList;

public class ProdutoDAO {

    public void adicionar(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (nome, quantidade, preco) VALUES (?, ?, ?)";
        Connection conn = DatabaseService.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getQuantidade());
        stmt.setDouble(3, produto.getPreco());
        stmt.executeUpdate();
        stmt.close();
        System.out.println("Produto salvo no banco!");
    }

    public ArrayList<Produto> listarTodos() throws SQLException {
        String sql = "SELECT * FROM produtos";
        Connection conn = DatabaseService.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        ArrayList<Produto> lista = new ArrayList<>();
        while (rs.next()) {
            String nome = rs.getString("nome");
            int quantidade = rs.getInt("quantidade");
            double preco = rs.getDouble("preco");
            Produto produto = new Produto(nome, quantidade, preco);
            lista.add(produto);
        }
        rs.close();
        stmt.close();
        return lista;
    }

    public void remover(String nome) throws SQLException {
        String sql = "DELETE FROM produtos WHERE nome = ?";
        Connection conn = DatabaseService.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.executeUpdate();
        stmt.close();
    }

    public Produto buscar(String nome) throws SQLException {
        String sql = "SELECT * FROM produtos WHERE nome = ?";
        Connection conn = DatabaseService.getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            String nomeProduto = rs.getString("nome");
            int quantidade = rs.getInt("quantidade");
            double preco = rs.getDouble("preco");
            return new Produto(nomeProduto, quantidade, preco);
        }
        return null;
    }
}
