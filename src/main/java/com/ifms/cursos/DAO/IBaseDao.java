package com.ifms.cursos.DAO;

import java.util.List;
public interface IBaseDao<T> extends IDao<T> {

    public List<T> buscarPorNome(String nome);
}
