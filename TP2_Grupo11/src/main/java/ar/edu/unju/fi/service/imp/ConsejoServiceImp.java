package ar.edu.unju.fi.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.Listas.ListaConsejo;
import ar.edu.unju.fi.model.Consejo;
import ar.edu.unju.fi.service.IConsejoService;
import jakarta.validation.Valid;

@Service
public class ConsejoServiceImp implements IConsejoService {

    @Autowired
	private ListaConsejo listaConsejos;
	@Autowired
	private Consejo formConsejo;

    public ModelAndView getConsejos(Model model){
        ModelAndView modelView = new ModelAndView("consejos");
        modelView.addObject("listaConsejos", listaConsejos.getListaConsejos());
        return modelView;
    }

    @Override
    public ModelAndView getnuevoConsejo(Model model) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("nuevo-consejo");
        modelView.addObject("nuevoConsejo", formConsejo);
        return modelView;
    }
    
    @Override
    public ModelAndView crearConsejo(@Valid Consejo nuevoConsejo, BindingResult result) {
        // TODO Auto-generated method stub
        ModelAndView modelView;
    	if(result.hasErrors()) {
    		modelView = new ModelAndView("nuevo-consejo");
    	}else {
    		modelView = new ModelAndView ("consejos");
    		listaConsejos.getListaConsejos().add(nuevoConsejo);
    		modelView.addObject("listaConsejos", listaConsejos.getListaConsejos());
    	}
    	return modelView;
    }

    @Override
    public ModelAndView eliminarConsejo(int id, Model model) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("consejos");
        for(Consejo consejo:listaConsejos.getListaConsejos()){
            if(consejo.getId()==id){
                listaConsejos.getListaConsejos().remove(consejo);
                break;
            }
        }
        modelView.addObject("listaConsejos", listaConsejos.getListaConsejos());
        return modelView;
    }

    @Override
    public ModelAndView editarConsejos(int id, Model model) {
        // TODO Auto-generated method stub
        ModelAndView modelView = new ModelAndView("modificar-consejo");
        for(Consejo consejo:listaConsejos.getListaConsejos()){
            if(consejo.getId()==id){
                modelView.addObject("consejosEditar", consejo);
                break;
            }
        }
        return modelView;
    }

    @Override
    public ModelAndView modificarLista(@Valid Consejo modificado, BindingResult result) {
        // TODO Auto-generated method stub
        ModelAndView modelView;
    	if(result.hasErrors()) {
    	modelView = new ModelAndView("modificar-consejo");
    	}else {
    		for(Consejo consejo:listaConsejos.getListaConsejos()){
    			if(modificado.getId()==consejo.getId()){
    				consejo.setTitulo(modificado.getTitulo());
    				consejo.setDescripcion(modificado.getDescripcion());
    			break;
            	}
            }
            modelView = new ModelAndView("consejos");
            modelView.addObject("listaConsejos", listaConsejos.getListaConsejos());
         }
    	return modelView;
    }
}
