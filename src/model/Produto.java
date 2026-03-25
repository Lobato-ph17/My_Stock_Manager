package model;

public class Produto {
    private String nome;
    private int quantidade;
    private double preco;

    public Produto(String nome, int quantidade, double preco){
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @Override
    public String toString(){
        return "Produto: " + nome + " | Quantidade: " + quantidade + " | Preço: R$" + String.format("%.2f", preco) ; 
    }

    
    public String getNome(){
        return nome;
    }

    public void setNome(String novoNome){
        this.nome = novoNome;
    }


    public int getQuantidade(){
        return quantidade;
    }

    public void setQuantidade(int novaQuantidade){
        if(novaQuantidade < 0){
            System.out.println("Quantidade Inválida!");
        }else{
            this.quantidade = novaQuantidade;
        }
    }


    public double getPreco(){
        return preco;
    }

    public void setPreco(double novoPreco){
        this.preco = novoPreco;
    }
}



