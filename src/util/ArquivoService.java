package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Produto;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ArquivoService {
    private static final String ARQUIVO = "produtos.json";

    public void salvar(ArrayList<Produto> produtos) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(produtos);
            FileWriter writer = new FileWriter(ARQUIVO);
            writer.write(json);
            writer.close();
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public ArrayList<Produto> carregar() {
        try {
            File arquivo = new File(ARQUIVO);
            if (!arquivo.exists()) {
                return new ArrayList<>();
            }
            BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO));
            StringBuilder conteudo = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha);
            }
            reader.close();
            Gson gson = new Gson();
            Type tipo = new TypeToken<ArrayList<Produto>>() {
            }.getType();
            return gson.fromJson(conteudo.toString(), tipo);
        } catch (IOException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
