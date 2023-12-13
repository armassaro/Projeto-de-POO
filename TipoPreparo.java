package src;

public enum TipoPreparo {

    Cozido("Cozido"),
    Assado("Assado"),
    Frito("Frito");
    
    private String tipo;
    
    private TipoPreparo(String tipo) {
        
        this.tipo = tipo;
        
    }
    
    public String getTipo() {
        
        return this.tipo;
        
    }
    
}
