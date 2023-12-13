package src;

public class Principal {
    
    CadastroClientes cadClientes = new CadastroClientes();
    CadastroProdutos cadProdutos = new CadastroProdutos();
    
    public Principal() {

        TelaPrincipal t1 = new TelaPrincipal();
        Administrador admin = new Administrador();
        
        do {
        
            t1.viewOpcoes();

            if(t1.secao == 0) {

                switch(t1.opcao) {

                    case 0:
                        admin.createProduto();
                        break;

                    case 1:
                        admin.readProduto();
                        break;

                    case 2:
                        admin.updateProduto();
                        break;

                    case 3:
                        admin.deleteProduto();
                        break;

                    case 4:
                        if(t1.secao == 0) {

                            t1.secao = 1;

                        }
                        else {

                            t1.secao = 0;

                        }
                        break;

                    case 5:
                        break;

                }

            }
            else{

                switch(t1.opcao) {

                    case 0:
                        admin.createCliente();
                        break;

                    case 1:
                        admin.readCliente();
                        break;

                    case 2:
                        admin.updateCliente();
                        break;

                    case 3:
                        admin.deleteCliente();
                        break;

                    case 4:
                        if(t1.secao == 0) {

                            t1.secao = 1;

                        }
                        else {

                            t1.secao = 0;

                        }
                        break;

                    case 5:
                        break;
            }
        }
        }while(t1.opcao != 5);
        
    }
    
    public class Administrador implements ICliente, IProduto{
    
        public void createCliente() {

            cadClientes.createCliente();

        }
        public void readCliente() {

            cadClientes.readCliente();

        }
        public void updateCliente() {

            cadClientes.updateCliente();

        }
        public void deleteCliente() {

            cadClientes.deleteCliente();

        }
        public void createProduto() {

            cadProdutos.createProduto();

        }
        public void readProduto() {

            cadProdutos.readProduto();

        }
        public void updateProduto() {

            cadProdutos.updateProduto();

        }
        public void deleteProduto() {

            cadProdutos.deleteProduto();

        }
    
    }
    
    public static void main(String[] args) {
        
        Principal p1 = new Principal();

    }
    
}
