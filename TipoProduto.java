package src;
public enum TipoProduto {

    Comida("Comida"),
    Bebida("Bebida");
    
    private String tipo;
    
    private TipoProduto(String tipo) {
        
        this.tipo = tipo;
        
    }
    
    public String getTipo() {
        
        return this.tipo;
        
    }
    
}
