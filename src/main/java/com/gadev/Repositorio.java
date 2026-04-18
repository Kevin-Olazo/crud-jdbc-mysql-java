package com.gadev;

import java.util.List;

public interface Repositorio<T> {
    public List<T> listarTodos();
    public T porId(int id);
    public void guardar(T t); // Debe servir tanto para INSERT (si id es null/0) como para UPDATE (si id existe)
    public void eliminar(int id);

}
