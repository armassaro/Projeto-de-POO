package src;

import java.util.ArrayList;
import java.util.List;
import src.CadastroProdutos;

public class Cliente {
        
        int nMesa;
        String nome;
        boolean contaPaga;
        MetodoPagamento metodoPagamento;
        List<Produto> itensPedido = new ArrayList<>();

        public int getnMesa() {
            return nMesa;
        }

        public void setnMesa(int nMesa) {
            this.nMesa = nMesa;
        }

        public boolean isContaPaga() {
            return contaPaga;
        }

        public void setContaPaga(boolean contaPaga) {
            this.contaPaga = contaPaga;
        }

        public MetodoPagamento getMetodoPagamento() {
            return metodoPagamento;
        }

        public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
            this.metodoPagamento = metodoPagamento;
        }

        public List<Produto> getItensPedido() {
            return itensPedido;
        }

        public void setItensPedido(List<Produto> itensPedido) {
            this.itensPedido = itensPedido;
        }
        
    }