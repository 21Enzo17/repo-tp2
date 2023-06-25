package ar.edu.unju.fi.service;

import java.util.List;

import ar.edu.unju.fi.entity.Autor;

public interface IAutorService {

    public List<Autor> getAllAutores(); // Listar

    public List<Autor> getDisponibles(); // Listar Disponibles

    public void addAutor(Autor autor); // Modificar y Guardar

    public void eliminarAutor(Autor autor);
    
    public Autor findAutorById(Long id); // Buscar por ID
}
