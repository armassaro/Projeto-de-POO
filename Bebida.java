package src;

public class Bebida extends Produto {
    
    int volume;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
    
    public String toString() {
        
        return "Nome: " + this.nome + "\nPreço: " + this.preco + "\nQuantidade: " + this.quantidade + "\nVolume: " + this.volume;
    
    }
}
