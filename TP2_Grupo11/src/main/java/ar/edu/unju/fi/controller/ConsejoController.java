package ar.edu.unju.fi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import ar.edu.unju.fi.entity.Consejo;
import ar.edu.unju.fi.service.IAutorService;
import ar.edu.unju.fi.service.IConsejoService;
import jakarta.validation.Valid;

import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/consejos")
public class ConsejoController {

	@Autowired
    private IConsejoService consejoService;
	
	@Autowired
	private IAutorService autorService;
	
    @Autowired
    private Consejo formConsejo;
	 /**
     * Método que muestra la página de consejos
     * @param model
     * @return consejos.html
     */
    @GetMapping("/listado")
    public String getConsejos(Model model){
    	model.addAttribute("buscado");
    	model.addAttribute("listaConsejo", consejoService.getConsejos());
    	model.addAttribute("listaAutores", autorService.getDisponibles());
    	return "consejos";
    }
    
    /**
     * Método que muestra la página para crear un nuevo consejo
     * @param model
     * @return nuevo-consejo.html
     */
    @GetMapping("/nuevo-consejo")
    public String getnuevoConsejo(Model model) { 	
    	model.addAttribute("listaAutor", consejoService.getConsejos());
    	model.addAttribute("listaAutores", autorService.getDisponibles());
        model.addAttribute("formConsejo",formConsejo);
        if (autorService.getDisponibles().size() == 0) {
      	  model.addAttribute("alerta", true);
        }
    	return "nuevo-consejo";
    }
    /**
     * Método que crea un nuevo consejo y lo agrega a la lista
     * @param listaConsejo
     * @return consejos.html
     */
 
    @PostMapping("/nuevo-consejo")
    public ModelAndView crearConsejo(@Valid @ModelAttribute("formConsejo")Consejo nuevoConsejo, BindingResult result){
    	  ModelAndView modelView;
          if(result.hasErrors()) {
              modelView = new ModelAndView("nuevo-consejo");
              modelView.addObject("listaAutores",autorService.getDisponibles());
          }else {
              modelView = new ModelAndView ("consejos");
              consejoService.addConsejo(nuevoConsejo);
              modelView.addObject("listaConsejos", consejoService.getDisponibles());
              modelView.addObject("listaAutores",autorService.getDisponibles());
              if (autorService.getDisponibles().size() == 0) {
            	  modelView.addObject("alerta", true);
              }
          }
          return modelView;
    }
    
    /**
     * Metodo que elimina un consejo de la lista
     * @param id
     * @param model
     * @return consejos.html
     */
    @GetMapping("/eliminar-consejos/{id}")
    public String eliminarConsejo(@PathVariable(value="id")long id,Model model){
        Consejo consejo = consejoService.findConsejoById(id);
    	consejoService.eliminarConsejo(consejo);
    	model.addAttribute("listaConsejos", consejoService.getDisponibles());
    	model.addAttribute("listaAutores", autorService.getDisponibles());
    	return "consejos";
    }
    /*
     * Metodo que permite editar un consejo por id
     * @param id
     * @param model
     * @return modificar-consejo.html
     */
    @GetMapping("/editar-consejos/{id}")
    public String editarConsejos(@PathVariable(value="id")long id,Model model){
        model.addAttribute("listaAutores", autorService.getDisponibles());
    	model.addAttribute("encontrado", consejoService.findConsejoById(id));
    	return "modificar-consejo";
    }
    /**
     * Metodo que permite guardar el consejo modificado
     * @param modificado
     * @return  consejos.html
     */
    @PostMapping("modificar-consejo")
    public ModelAndView modificarLista(@Valid @ModelAttribute("consejosEditar")Consejo modificado, BindingResult result){
    	ModelAndView modelView;
        if(result.hasErrors()){
            modelView = new ModelAndView("modificar-producto");
        }else{
        	Consejo consejo = consejoService.findConsejoById(modificado.getId());
        	consejo.setTitulo(modificado.getTitulo());
        	consejo.setDescripcion(modificado.getDescripcion());
        	consejo.setAutor(modificado.getAutor());
        	modelView = new ModelAndView("consejo");
        	modelView.addObject("listaConsejos", consejoService.getDisponibles());
        	modelView.addObject("listaAutores", autorService.getDisponibles());
        }
    	return modelView;
    }

    
    /**
     * Metodo que permite buscar consejos
     *	@param titulo, model
     * @return consejo.html
     */
  @GetMapping("buscar-consejo")
   public ModelAndView buscarByNombreAutor(@RequestParam("nombre") String buscado,@RequestParam("autor") Long id, Model model){
      ModelAndView modelView = new ModelAndView("consejo");
      List<Consejo> coincidenteList = new ArrayList<Consejo>();
      List<Consejo> autorList = new ArrayList<Consejo>();
      if(id!=0) {
    	  autorList=consejoService.findByAutor(autorService.findAutorById(id));
      }else {
    	  autorList=consejoService.getDisponibles();
      }
      System.out.println(coincidenteList.toString());
      for(Consejo consejo : autorList) {
    	  if(consejo.getAutor().getNombre().toLowerCase().contains(buscado.toLowerCase())) {
    		  coincidenteList.add(consejo); 		  
    	  }
      }
      modelView.addObject("listaAutores", autorService.getDisponibles());
      modelView.addObject("listaConsejos", coincidenteList);
      if(coincidenteList.size()==0)
    	  modelView.addObject("alerta", true);
	  return modelView;
   }
  
 }

