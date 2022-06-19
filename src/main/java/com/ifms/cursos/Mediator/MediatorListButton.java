package com.ifms.cursos.Mediator;
import com.ifms.Cursos.conexao.CursoHibernateTableModel;
import javax.swing.JButton;
import javax.swing.JTextField;
public class MediatorListButton {
    
    private JButton btAlterar;
    private JButton btExcluir;
    private CursoHibernateTableModel model;
    private JTextField txtNome;
    
    public void registerAlterar(JButton buttonAlterar) {
        this.btAlterar = buttonAlterar;
    }
    
    public void registerExcluir(JButton buttonExcluir) {
        this.btExcluir = buttonExcluir;
    }
    
    public void registerModel(CursoHibernateTableModel model) {
        this.model = model;
    }
    
    public void registerField(JTextField txtNome) {
        this.txtNome = txtNome;
    }
    
    public void buscar() {
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
        model.refresh(txtNome.getText());
    }
    
    public void cadastrar() {
        btAlterar.setVisible(false);
        btExcluir.setVisible(false);
    }
    
    public void selecionar() {
        btAlterar.setVisible(true);
        btExcluir.setVisible(true);
    }
    
}
