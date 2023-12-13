package src;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;



import javax.swing.JOptionPane;


public class CadastroClientes implements ICliente, Iteravel<CadastroClientes.Cliente> {

    List<Cliente> listaClientes = new ArrayList<>();
    ViewCadastroClientes viewClientes = new ViewCadastroClientes();

    public void createCliente() {
        viewClientes.create();
    }

    public void readCliente() {
        viewClientes.read();
    }

    public void updateCliente() {
        viewClientes.update();
    }

    public void deleteCliente() {
        viewClientes.delete();
    }

  
    public Iterator<Cliente> iterator() {
        return listaClientes.iterator();
    }

    public class Cliente {

        int nMesa;
        String nome;
        boolean contaPaga;
        MetodoPagamento metodoPagamento;
        List<Produto> itensPedido = new ArrayList<>();
        
      
    

    
        
        
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Cliente cliente = (Cliente) obj;
            return nMesa == cliente.nMesa && nome.equals(cliente.nome);
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public int getnMesa() {
            return nMesa;
        }

        public void setnMesa(int nMesa) {
            this.nMesa = nMesa;
        }

        public boolean ContaPaga() {
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

        public String toString() {
            return "Nome: " + this.nome + "\nNumero da mesa: " + this.nMesa + "\nConta paga: " + this.contaPaga +
                    "\nMetodo de pagamento: " + this.metodoPagamento + "\nItens do pedido: \n" +
                    this.itensPedido.stream().map(Produto::toString).collect(Collectors.joining("\n"));
        }
    }

    public enum MetodoPagamento {
        Dinheiro, CartaoCredito, CartaoDebito;
    }

    private class ViewCadastroClientes {

        private Cliente cliente = new Cliente();
        ControlCadastroClientes controlClientes = new ControlCadastroClientes();
        boolean clienteJaExiste;
        
  

        public Cliente getCliente() {
            return cliente;
        }

        public void setCliente(Cliente cliente) {
            this.cliente = cliente;
        }

        public void create() {
            try {
                cliente.nome = JOptionPane.showInputDialog("Digite o nome do cliente");
                cliente.nMesa = Integer.parseInt(JOptionPane.showInputDialog("Digite o numero da mesa"));

                Object[] optionsContaPaga = {"Sim", "Nao"};
                int contaPagaOptions = JOptionPane.showOptionDialog(null, "A conta esta paga?", "Conta paga",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, optionsContaPaga, optionsContaPaga[0]);
                cliente.contaPaga = (contaPagaOptions == JOptionPane.YES_OPTION);

           
                MetodoPagamento[] metodo = MetodoPagamento.values();
                MetodoPagamento metodoEscolha = (MetodoPagamento) JOptionPane.showInputDialog( null,
                "Selecione o metodo de pagamento", "Metodo de pagamnto",
                JOptionPane.PLAIN_MESSAGE, null, metodo, metodo[0]);

                cliente.metodoPagamento = metodoEscolha;

                controlClientes.validarCreate(cliente);
     
                
                
                
                
            } catch (NumberFormatException e) {
                System.out.println("Numero invalido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Metodo de pagamento invalido.");
            }
        }

        public void read() {
            cliente.nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente");

            controlClientes.validarRead(cliente);
        }

        public void update() {
            cliente.nome = JOptionPane.showInputDialog(null, "Digite o nome do cliente a ser alterado");

            for (Cliente clienteAux : listaClientes) {
                if (cliente.equals(clienteAux)) {
                    clienteJaExiste = true;
                    controlClientes.validarUpdate(clienteAux);
                    break;
                }
            }

            if (!clienteJaExiste) {
                JOptionPane.showMessageDialog(null, "O cliente nao existe");
            }
        }

        
       
        public void delete() {
            String nomeCliente = JOptionPane.showInputDialog(null, "Digite o nome do cliente a ser excluido");

            Iterator<Cliente> iterator = listaClientes.iterator();
            while (iterator.hasNext()) {
                Cliente clienteAux = iterator.next();
                if (clienteAux.getNome().equals(nomeCliente)) {
                    iterator.remove();  
                    controlClientes.delete(clienteAux); 
                    JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
                    return;
                }
            }

            JOptionPane.showMessageDialog(null, "O cliente nao existe");
        }



    private class ControlCadastroClientes {

        ModelCadastroClientes modelClientes = new ModelCadastroClientes();

     
        public void validarCreate(Cliente cliente) {
            try {
                boolean clienteJaExiste = listaClientes.contains(cliente);
                if (!clienteJaExiste) {
                    listaClientes.add(cliente);
                    modelClientes.create(cliente);
                } else {
                    System.out.println("Cliente ja existe");
                }
            } catch (Exception e) {
                System.out.println("Erro ao validar criacao do cliente.");
            }
        }

        public void validarRead(Cliente cliente) {
            try {
                boolean clienteEncontrado = false;
                for (Cliente clienteAux : listaClientes) {
                    if (cliente.getNome().equals(clienteAux.getNome())) {
                        clienteEncontrado = true;
                        JOptionPane.showMessageDialog(null, clienteAux);
                        break;
                    }
                }

                if (!clienteEncontrado) {
                    JOptionPane.showMessageDialog(null, "Cliente nao encontrado");
                }
            } catch (Exception e) {
                System.out.println("Erro na leitura do cliente");
            }
        }


        public void validarUpdate(Cliente cliente) {
            
            String novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome do cliente");
            int novaMesa = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o novo número da mesa"));
            boolean novaContaPaga = Boolean.parseBoolean(JOptionPane.showInputDialog(null, "A nova conta está paga?"));

            String novoMetodoPagamento = JOptionPane.showInputDialog(null,
                    "Digite o novo método de pagamento (Dinheiro, CartaoCredito, CartaoDebito)");
            MetodoPagamento novoMetodo = MetodoPagamento.valueOf(novoMetodoPagamento);

            
            cliente.setNome(novoNome);
            cliente.setnMesa(novaMesa);
            cliente.setContaPaga(novaContaPaga);
            cliente.setMetodoPagamento(novoMetodo);

            
            JOptionPane.showMessageDialog(null, "Cliente atualizado:\n" + cliente);
        }

        public void delete(Cliente cliente) {
            Iterator<Cliente> iterator = CadastroClientes.this.listaClientes.iterator();
            while (iterator.hasNext()) {
                Cliente clienteExistente = iterator.next();
                if (clienteExistente.equals(cliente)) {
                    iterator.remove();
                    modelClientes.delete(cliente);  // Call the delete method in ModelCadastroClientes
                    System.out.println("Cliente excluido");
                    return;
                }
            }
            System.out.println("O cliente nao existe");
        }
    }


    public class ModelCadastroClientes {

        public void create(Cliente cliente) {
            CadastroClientes.this.listaClientes.add(cliente);
        }

        public Cliente read(String nomeCliente) {
            for (Cliente cliente : CadastroClientes.this.listaClientes) {
                if (cliente.getNome().equals(nomeCliente)) {
                    return cliente;
                }
            }
            return null;
        }

        public void update(Cliente cliente) {
            for (Cliente clienteExistente : CadastroClientes.this.listaClientes) {
                if (clienteExistente.equals(cliente)) {
                    String novoNome = JOptionPane.showInputDialog(null, "Digite o novo nome do cliente");
                    if (novoNome != null && !novoNome.isEmpty()) {
                        clienteExistente.setNome(novoNome);
                    }

                    String novaMesa = JOptionPane.showInputDialog(null, "Digite o novo numero da mesa");
                    if (novaMesa != null && !novaMesa.isEmpty()) {
                        try {
                            int novaMesaInt = Integer.parseInt(novaMesa);
                            clienteExistente.setnMesa(novaMesaInt);
                        } catch (NumberFormatException e) {
                            System.out.println("Nao foi possível realizar a atualizacao da mesa");
                        }
                        
                    }
                    String novaConta = JOptionPane.showInputDialog(null, "A conta esta paga?");
                    if (novaConta != null && !novaConta.isEmpty()) {
                        try {
                            boolean novoStatusContaBool = Boolean.parseBoolean(novaConta);
                            clienteExistente.setContaPaga(novoStatusContaBool);
                        } catch (NumberFormatException e) {
                            System.out.println("Nao foi possIvel realizar a atualização da conta");
                        }
                    }
                    
                    
                    
                    String novoMetodoPagamento = JOptionPane.showInputDialog(null,"Digite o novo "
                    		+ "metodo de pagamento (Dinheiro, CartaoCredito, CartaoDebito)");
                    if (novoMetodoPagamento != null && !novoMetodoPagamento.isEmpty()) {
                        try {
                            MetodoPagamento novoMetodo = MetodoPagamento.valueOf(novoMetodoPagamento);
                            clienteExistente.setMetodoPagamento(novoMetodo);
                        } catch (IllegalArgumentException e) {
                           
                            System.out.println("Nao foi possivel realizar a atualizacao do pagamento");
                        }

                    break;
                  }
               }
            }
        }

        public void delete(Cliente cliente) {
            Iterator<Cliente> iterator = CadastroClientes.this.listaClientes.iterator();
            while (iterator.hasNext()) {
                Cliente clienteExistente = iterator.next();
                if (clienteExistente.equals(cliente)) {
                    iterator.remove();
                    System.out.println("Cliente excluido");
                    return;
                }
            }
            System.out.println("O cliente nao existe");
        }

      
    }
    }


}