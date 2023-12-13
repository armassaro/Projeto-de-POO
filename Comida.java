package src;

public class Comida extends Produto {
    
    TipoPreparo tipoPreparo;
    boolean acompanhaFritas;

    public TipoPreparo getTipoPreparo() {
        return tipoPreparo;
    }

    public void setTipoPreparo(TipoPreparo tipoPreparo) {
        this.tipoPreparo = tipoPreparo;
    }

    public boolean getAcompanhaFritas() {
        return acompanhaFritas;
    }

    public void setAcompanhaFritas(boolean acompanhaFritas) {
        this.acompanhaFritas = acompanhaFritas;
    }
    
    public String toString() {
        
        return "Nome: " + this.nome + "\nPreço: " + this.preco + "\nQuantidade: " + this.quantidade + "\nTipo de produto: Comida" + "\nTipo de preparo: " + this.tipoPreparo + "\nAcompanha fritas: " + this.acompanhaFritas;
    
    }
}
