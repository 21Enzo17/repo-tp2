package ar.edu.unju.fi.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.entity.Categoria;
import ar.edu.unju.fi.repository.ICategoriaRepository;
import ar.edu.unju.fi.service.ICategoriaService;

@Service
public class CategoriaServiceImp implements ICategoriaService{


    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getAllCategorias() {
        return (List<Categoria>) categoriaRepository.findAll();
    }

    @Override
    public void addCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    @Override
    public Categoria findCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarCategoria(Categoria categoria) {
        categoria.setEstado(false);
        categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> getDisponibles() {
        return categoriaRepository.categoriasDisponibles();
    }
    
}