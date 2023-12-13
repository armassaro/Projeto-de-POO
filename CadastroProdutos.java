package src;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.Iterator;

public class CadastroProdutos implements IProduto, Iterable<Produto> {
    
    List<Produto> listaProdutos = new ArrayList<>();
    private Produto produto;
    String nome;
    int quantidade;
    int preco;
    
    ViewCadastroProdutos viewProd = new ViewCadastroProdutos();

    public void createProduto() {
        
        viewProd.create();
        
    }
    public void readProduto() {
    
        viewProd.read();
        
    }
    public void updateProduto() {
    
        viewProd.update();
        
    }
    public void deleteProduto() {
    
        viewProd.delete();
        
    }
    
    public Iterator<Produto> iterator() {
        
        return listaProdutos.iterator();
        
    }
    
    public void printAllProducts() {
        
        for (Produto produto : this) {
            
            System.out.println(produto.toString());
            
        }
    }
    
    private class ViewCadastroProdutos {
        
        ControlCadastroProdutos controlProd = new ControlCadastroProdutos();
        
        public void create() {
            
            nome = JOptionPane.showInputDialog(null, "Digite o nome do produto");
            //se existe um objeto com nome igual a um ja cadastrado, exibe aviso
            if(controlProd.validarCreate() == true) {
                
                JOptionPane.showMessageDialog(null, "O produto que você tentou criar já existe");
                
            }
            
            
        }
        
        public void read() {
            
            nome = JOptionPane.showInputDialog(null,"Digite o nome do produto");
            
            if(controlProd.validarRead() == false) {
                
                JOptionPane.showMessageDialog(null, "O produto que você pesquisou não existe");
                
            }
            else {
                
                if(produto instanceof Comida) {

                    JOptionPane.showMessageDialog(null, ((Comida)produto).toString());

                }
                if(produto instanceof Bebida) {
                    
                    JOptionPane.showMessageDialog(null, ((Bebida)produto).toString());
                
                }
                
            }
           
            System.out.println("-----------------------------------");
            
            printAllProducts();
            
        }
        
        public void update() {
            
            nome = "";
            
            nome = JOptionPane.showInputDialog(null, "Digite o nome do produto a ser alterado");
                
                if(controlProd.validarUpdate() == false) {
                    
                    JOptionPane.showMessageDialog(null, "O produto que você procurou não existe");
                
                }
                
        }
        
        public void delete() {
            
            nome = JOptionPane.showInputDialog(null, "Digite o nome do produto que você deseja excluir");
            
            if(controlProd.validarDelete() == false) {
                
                JOptionPane.showMessageDialog(null, "O produto procurado não foi encontrado");
                
            }
            
        }
        
        private class ControlCadastroProdutos {

            int index;
            ModelCadastroProdutos modelProd = new ModelCadastroProdutos();
            
            public boolean validarCreate() {
                
                for(Produto prodAux : listaProdutos) {
                    
                    if(prodAux.nome.equalsIgnoreCase(nome)) {

                        return true;

                    }
                    
                }
                
                try {

                    quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade do produto"));
                    preco = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o preço do produto"));

                }
                catch (NumberFormatException e) {
                    // tratamento de exceção para conversão de string inválida
                    System.err.println("Erro ao converter a String para int: " + e.getMessage());

                }
                
                Object[] opcoes = {"Comida", "Bebida"};
                    
                    int opcao = JOptionPane.showOptionDialog(
                    null,
                          "Qual o tipo de produto?",
                           null,
                        JOptionPane.DEFAULT_OPTION,
                       JOptionPane.QUESTION_MESSAGE,
                            null,
                          opcoes,
                                opcoes[0]);
                    
                switch(opcao) {
                    
                    case 0:
                        produto = new Comida();
                        produto.setTipoProduto(TipoProduto.Comida);        
                        produto.setPreco(preco);
                        produto.setQuantidade(quantidade);
                        produto.setNome(nome);
                        
                        Object[] opcoes2 = {"Cozido", "Assado", "Frito"};
                    
                        opcao = JOptionPane.showOptionDialog(
                        null,
                              "Qual o tipo de preparo?",
                               null,
                            JOptionPane.DEFAULT_OPTION,
                           JOptionPane.QUESTION_MESSAGE,
                                null,
                              opcoes2,
                                    opcoes2[0]);
                        
                        switch(opcao) {
                            
                            case 0:
                                ((Comida)produto).setTipoPreparo(TipoPreparo.Cozido);
                                break;
                                
                            case 1:
                                ((Comida)produto).setTipoPreparo(TipoPreparo.Assado);
                                break;
                                
                            case 2:
                                ((Comida)produto).setTipoPreparo(TipoPreparo.Frito);
                                break;
                                
                        }
                        
                        Object[] opcoes3 = {"Sim", "Não"};
                    
                        opcao = JOptionPane.showOptionDialog(
                        null,
                              "Acompanha fritas?",
                               null,
                            JOptionPane.DEFAULT_OPTION,
                           JOptionPane.QUESTION_MESSAGE,
                                null,
                              opcoes3,
                                    opcoes3[0]);
                        
                        switch(opcao) {
                            
                            case 0:
                                ((Comida)produto).setAcompanhaFritas(true);
                                break;
                                
                            case 1:
                                ((Comida)produto).setAcompanhaFritas(false);
                                break;
                                
                        }
                        break;
                        
                    case 1:
                        produto = new Bebida();
                        produto.setTipoProduto(TipoProduto.Bebida);
                        produto.setNome(nome);
                        produto.setQuantidade(quantidade);
                        produto.setPreco(preco);
                        
                        try {
                            
                            ((Bebida)produto).setVolume(Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o volume da bebida")));
                            
                        }
                        catch (NumberFormatException e) {
                            // tratamento de exceção para conversão de string inválida
                            System.err.println("Erro ao converter a String para int: " + e.getMessage());

                        }
                        break;
                        
                }
                
                modelProd.create();
                
                return false;
                
            }
            
            public boolean validarRead() {
                
                for(int a = 0; a < listaProdutos.size(); a++) {
                    
                    if(listaProdutos.get(a).nome.equalsIgnoreCase(nome)) {
                        
                        index = a;
                        modelProd.read();
                        return true;
                        
                    }
                    
                }
                
                return false;
                
            }
        
            public boolean validarUpdate() {
                
                for(int a = 0; a < listaProdutos.size(); a++) {
                    
                    if(listaProdutos.get(a).nome.equalsIgnoreCase(nome)) {
                        
                        index = a;
                        modelProd.update();
                        return true;
                        
                    }
                    
                }
                
                return false;
                
            }
            
            public boolean validarDelete() {
                
                for(int a = 0; a < listaProdutos.size(); a++) {
                    
                    if(listaProdutos.get(a).nome.equalsIgnoreCase(nome)) {
                        
                        index = a;
                        modelProd.delete();
                        return true;
                        
                    }
                    
                }
                
                return false;
                
            }

            private class ModelCadastroProdutos {

                public void create() {
                
                    listaProdutos.add(produto);
                    nome = "";
                    
                }
                
                public void read() {
                    
                    if(listaProdutos.get(index) instanceof Comida) {

                        produto = new Comida();
                        Comida comida;
                        comida = (Comida) listaProdutos.get(index);
                        
                        produto.setNome(comida.getNome());
                        produto.setPreco(comida.getPreco());
                        produto.setQuantidade(comida.getQuantidade());
                        produto.setTipoProduto(comida.getTipoProduto());
                        ((Comida)produto).setAcompanhaFritas(comida.getAcompanhaFritas());
                        ((Comida)produto).setTipoPreparo(comida.getTipoPreparo());
                        nome = "";
                        
                    }
                    else {
                        
                        produto = new Bebida();
                        Bebida bebida;
                        bebida = (Bebida) listaProdutos.get(index);
                        
                        produto.setNome(bebida.getNome());
                        produto.setPreco(bebida.getPreco());
                        produto.setQuantidade(bebida.getQuantidade());
                        produto.setTipoProduto(bebida.getTipoProduto());
                        ((Bebida)produto).setVolume(bebida.getVolume());
                        nome = "";
                    
                    }
                    
                }
                
                public void update() {
                    
                    int opcao;
                    Produto produtoAux = listaProdutos.get(index);
                    
                    if(produtoAux instanceof Comida) {
                        
                        opcao = 0;
                        produto = new Comida();
                        
                    }
                    else {
                        
                        opcao = 1;
                        produto = new Bebida();
                        
                    }
                    
                    switch(opcao) {
                        case 0:
                    // se o produto for comida
                        while(opcao != 5) {
                            
                            Object[] opcoes1 = {"Tipo de preparo", "Acompanha fritas", "Preço", "Quantidade", "Nome", "Não modificar"};

                            opcao = JOptionPane.showOptionDialog(
                        null,
                              "Escolha a modificação desejada",
                               null,
                            JOptionPane.DEFAULT_OPTION,
                           JOptionPane.QUESTION_MESSAGE,
                                null,
                              opcoes1,
                                    opcoes1[0]);

                            switch(opcao) {

                                case 0:
                                    Object[] opcoes2 = {"Cozido", "Assado", "Frito", "Não modificar"};

                                    opcao = JOptionPane.showOptionDialog(
                        null,
                              "Escolha o tipo de preparo da comida",
                               null,
                            JOptionPane.DEFAULT_OPTION,
                           JOptionPane.QUESTION_MESSAGE,
                                null,
                              opcoes2,
                                    opcoes2[0]);

                                    switch(opcao) {

                                        case 0:
                                            ((Comida) produto).setTipoPreparo(TipoPreparo.Cozido);
                                            break;

                                        case 1:
                                            ((Comida) produto).setTipoPreparo(TipoPreparo.Assado);
                                            break;

                                        case 3:
                                            ((Comida) produto).setTipoPreparo(TipoPreparo.Frito);
                                            break;

                                        case 4:
                                            break;

                                    }
                                    break;

                                case 1:
                                    Object[] opcoes3 = {"Sim", "Não"};

                                    opcao = JOptionPane.showOptionDialog(
                        null,
                              "Escolha a modificação desejada",
                               null,
                            JOptionPane.DEFAULT_OPTION,
                           JOptionPane.QUESTION_MESSAGE,
                                null,
                              opcoes3,
                                    opcoes3[0]);

                                    switch(opcao) {

                                        case 0:
                                            ((Comida) produto).setAcompanhaFritas(true);
                                            break;

                                        case 1:
                                            ((Comida) produto).setAcompanhaFritas(false);
                                            break;

                                    }
                                    break;

                                case 2:
                                    produto.preco = Integer.valueOf(JOptionPane.showInputDialog("Digite o novo preço"));
                                    break;

                                case 3:
                                    produto.quantidade = Integer.valueOf(JOptionPane.showInputDialog("Digite a nova quantidade"));
                                    break;

                                case 4:
                                    produto.nome = JOptionPane.showInputDialog("Digite o novo nome");
                                    break;

                                case 5:
                                    break;

                            }
                        
                        }
                        listaProdutos.set(index, ((Comida)produto));
                        break;
                    
                        case 1:
                            while(opcao != 4) {
                            Object[] opcoes1 = {"Volume", "Preço", "Quantidade", "Nome", "Não modificar"};

                            opcao = JOptionPane.showOptionDialog(
                        null,
                              "Escolha a modificação desejada",
                               null,
                            JOptionPane.DEFAULT_OPTION,
                           JOptionPane.QUESTION_MESSAGE,
                                null,
                              opcoes1,
                                    opcoes1[0]);

                            switch(opcao) {

                                case 0:
                                    ((Bebida) produto).setVolume(Integer.parseInt(JOptionPane.showInputDialog("Digite o novo volume")));
                                    break;

                                case 1:
                                    produto.preco = Integer.valueOf(JOptionPane.showInputDialog("Digite o novo preço"));
                                    break;

                                case 2:
                                    produto.setQuantidade(Integer.parseInt(JOptionPane.showInputDialog("Digite a nova quantidade")));
                                    break;

                                case 3:
                                    produto.setNome(JOptionPane.showInputDialog("Digite o novo nome"));
                                    break;

                                case 4:
                                    break;

                            }
                            }
                            listaProdutos.set(index, ((Bebida)produto));
                            break;
                    
                        case 2:
                            break;
                    
                    }
                    nome = "";
                    
                }
                
                public void delete() {
                    
                    listaProdutos.remove(index);
                    
                }

            }
            
        }
        
}
}