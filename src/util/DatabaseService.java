package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseService {
    private static final String URL = "jdbc:sqlite:estoque.db?encoding=UTF-8";
    private static Connection conexao = null;

    public static Connection getConexao() throws SQLException {
        if (conexao == null) {
            conexao = DriverManager.getConnection(URL);
            System.out.println("Banco de dados conectado!");
        }
        return conexao;
    }

    public static void inicializar() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS produtos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "quantidade INTEGER NOT NULL," +
                "preco REAL NOT NULL)";

        Connection conn = getConexao();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
        System.out.println("Tabela criada com Sucesso!");
    }
}
