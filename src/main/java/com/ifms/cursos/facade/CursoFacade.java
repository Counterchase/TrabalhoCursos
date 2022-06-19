package com.ifms.cursos.facade;
import com.ifms.cursos.DAO.CursoDao;
import com.ifms.cursos.DAO.ICursoDao;
import com.ifms.cursos.Model.Curso;
import com.ifms.cursos.View.TelaFormCurso;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.time.LocalTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CursoFacade {

    private ICursoDao dao;

    public CursoFacade(ICursoDao dao) {
        this.dao = dao;
    }

    public CursoFacade() {
        this(new CursoDao());
    }

    public TelaFormCurso abrirFormulario(JFrame frame, CursoFacade facade) {
        TelaFormCurso dialog = new TelaFormCurso(frame, true, facade);
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
        return dialog;
    }

    public TelaFormCurso editarFormulario(
            JFrame frame,
            CursoFacade facade,
            Long id) {
        TelaFormCurso dialog = abrirFormulario(frame, facade);
        dialog.setId(id);
        return dialog;
    }

    public void carregarDados(
            Long id,
            JTextField txtId,
            JTextField txtNome,
            JTextField txtDescricao,
            JTextField txtDuracao,
            JTextField txtNumeroParticipantes,
            JTextArea txtConteudo
    ) {
        Curso c = dao.buscarPorId(id);
        txtId.setText(c.getId().toString()); 
        txtNome.setText(c.getNome());
        
    }

    public Boolean salvar(
            JTextField txtId,
            JTextField txtNome,
            JTextField txtDescricao,
            JTextField txtDuracao,
            JTextField txtNumeroParticipantes,
            JTextArea txtConteudo
    ) {
        boolean isId = txtId.getText().matches("\\d+");
        Long id = isId ? Long.parseLong(txtId.getText()) : null;

        Curso curso = new Curso();
        curso.setId(id);
        curso.setNome(txtNome.getText());
        curso.setDescricao(txtDescricao.getText());
        curso.setDuracao(Float.parseFloat(txtDuracao.getText()));  
        curso.setNumeroParticipantes(Integer.parseInt(txtNumeroParticipantes.getText()));
        curso.setConteudo(txtConteudo.getText());
        

        if (!isId) {
            dao.inserir(curso);
        } else {
            dao.alterar(curso);
        }

        return Boolean.TRUE;
    }
    
    public Boolean excluir(JFrame frame, Long id) {
        if (JOptionPane.showConfirmDialog(frame, "Deseja excluir esse registro?",
                "Excluir", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            
            dao.excluir(id);
            
            JOptionPane.showMessageDialog(frame, "Registro excluídocom sucesso!",
                    "Informação", JOptionPane.INFORMATION_MESSAGE);
        }
        return true;
    }

}
