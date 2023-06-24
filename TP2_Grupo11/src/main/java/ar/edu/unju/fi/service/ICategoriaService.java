package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Categoria;

public interface ICategoriaService {
    
    public List<Categoria> getAllCategorias(); // Listar

    public List<Categoria> getDisponibles(); // Listar Disponibles

    public void addCategoria(Categoria categoria); // Modificar y Guardar

    public void eliminarCategoria(Categoria categoria);

    public Categoria findCategoriaById(Long id); // Buscar por ID

}
