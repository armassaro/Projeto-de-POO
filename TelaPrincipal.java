package src;

import javax.swing.JOptionPane;

public class TelaPrincipal {
    
    public int opcao = -1;
    public int secao = 0;
 
    public void viewOpcoes() {
    
        switch(secao) {
            
            case 0:
                Object[] opcoes = {"Criar produto", "Buscar produto", "Modificar produto", "Deletar produto", "Acessar clientes", "Sair"};

                opcao = JOptionPane.showOptionDialog(
                null,
                      "Escolha a opção desejada",
                       null,
                    JOptionPane.DEFAULT_OPTION,
                   JOptionPane.QUESTION_MESSAGE,
                        null,
                      opcoes,
                            opcoes[0]);
                            break;
                            
            case 1:
                Object[] opcoes1 = {"Criar cliente", "Buscar cliente", "Modificar cliente", "Deletar cliente", "Acessar produtos", "Sair"};

                opcao = JOptionPane.showOptionDialog(
                null,
                      "Escolha a opção desejada",
                       null,
                    JOptionPane.DEFAULT_OPTION,
                   JOptionPane.QUESTION_MESSAGE,
                        null,
                      opcoes1,
                            opcoes1[0]);
        
    }
    }
}
