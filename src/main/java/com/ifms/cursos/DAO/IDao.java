package com.ifms.cursos.DAO;

import java.util.List;
public interface IDao<T> {

    public T inserir(T object);

    public T alterar(T object);

    public void excluir(Object object);

    public List<T> listar();
    
    public T buscarPorId(Object object);
}
