import java.util.ArrayList;

public class EstoqueService {
    private ArrayList <Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public ArrayList<Produto> listar (){
        return produtos;
    }

    public Produto buscarProduto(String nome){
        for(Produto produto : produtos){
            if(produto.getNome().equals(produto){
                return produto;
            });
        }
    } return null;
}
