package src;

public enum MetodoPagamento {
    
    Pix("Pix"),
    Credito("Crédito"),
    Debito("Débito");
    
    private String metodoPagamento;
    
    private MetodoPagamento(String metodoPagamento) {
        
        this.metodoPagamento = metodoPagamento;
        
    }
    
    public String getTipo() {
        
        return this.metodoPagamento;
        
    }
}
