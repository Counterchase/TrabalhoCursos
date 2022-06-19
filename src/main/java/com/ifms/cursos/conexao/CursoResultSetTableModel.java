package com.ifms.cursos.conexao;
import com.ifms.cursos.DAO.CursoDao;
import com.ifms.cursos.DAO.ICursoDao;
import com.ifms.cursos.Model.Curso;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CursoResultSetTableModel extends AbstractTableModel {

    private final ICursoDao queries;
    private final List<Curso> lista;
    private final String[] colunas = {"Id", "Nome", "Descrição", "Duração em Horas", "Numero de Participantes", "Conteúdo Programático"};

    public CursoResultSetTableModel() {
        queries = new CursoDao();
        lista = queries.listar();
    }

    public void atualizaTabela(String nome) {
        lista.clear();
        if (nome != null && !nome.isBlank() && !nome.isEmpty()) {
            lista.addAll(queries.buscarPorNome(nome));
        } else {
            lista.addAll(queries.listar());
        }
        
        fireTableStructureChanged();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {

        return colunas.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Curso obj = lista.get(row);
        switch (col) {
            case 0: return obj.getId();
            case 1: return obj.getNome();
            case 2: return obj.getDescricao();
            case 3: return obj.getDuracao();
            case 4: return obj.getNumeroParticipantes();
            case 5: return obj.getConteudo();
        }
        return "";
    }

    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Long.class;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return String.class;
        }
       
        return Object.class;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

}
