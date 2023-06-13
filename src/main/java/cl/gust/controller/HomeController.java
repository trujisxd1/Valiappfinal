package cl.gust.controller;

import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.gust.modelos.UsuarioImg;
import cl.gust.repositorios.IUsuarioimg;
import cl.gust.service.ReporteService;


@Controller
@RequestMapping("/")

public class HomeController {
	@Autowired
	private ReporteService repositorio;
	
	
	
	@GetMapping("/")
	
	public String home(Model model) {
	 	
		model.addAttribute("usuarios", this.repositorio.listar());
		
		return "home/panel";
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("usuarios", new UsuarioImg());
		
		return"param/form";
	}
	
	@PostMapping("/form")
	
	public String guardar(@RequestParam (name="file", required = false) MultipartFile foto, UsuarioImg usuario, RedirectAttributes flash
			 ) {
		 
		 
		 
		 if(!foto.isEmpty()) {
			 
			 String ruta= "C://Temp//uploads";
			
			 
			 try {
				
				 byte[] bytes=foto.getBytes();
				 Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
				Files.write(rutaAbsoluta, bytes);
				 usuario.setFoto(foto.getOriginalFilename());
			} catch (Exception e) {
				// TODO: handle exception
			}
			 
			 this.repositorio.guardar(usuario);

			 flash.addFlashAttribute("success", "foto subida");
			 
			 
		 }
	
		 return"redirect:/";
}
	
	
@GetMapping("/form/detalle/{id}")
	
	public String form_detalle(@PathVariable("id") String id,Model model) {
		
		UsuarioImg usuario= this.repositorio.buscarPorId(id);
		
		model.addAttribute("usuarios", usuario);
		return"param/reporte";
		
	}
	
	
@GetMapping("/form/editar/{id}")
	
	public String form_editar(@PathVariable("id") String id,Model model) {
		
		UsuarioImg usuario= this.repositorio.buscarPorId(id);
		
		model.addAttribute("usuarios", usuario);
		return"param/form_editar";
		
	}
	


@PostMapping("/form/editar/{id}")
	
	public String guardar_editar(@RequestParam (name="file", required = false) MultipartFile foto, UsuarioImg usuario, RedirectAttributes flash
			,@PathVariable("id") String id ) {
		 
		 
		 
		 if(!foto.isEmpty()) {
			 
			 String ruta= "C://Temp//uploads";
			
			 
			 try {
				
				 byte[] bytes=foto.getBytes();
				 Path rutaAbsoluta = Paths.get(ruta + "//" + foto.getOriginalFilename());
				Files.write(rutaAbsoluta, bytes);
				 usuario.setFoto(foto.getOriginalFilename());
			} catch (Exception e) {
				// TODO: handle exception
			}
			 
			 
			 this.repositorio.guardar(usuario);

			 flash.addFlashAttribute("success", "foto subida");
			 
			 
		 }
		 this.repositorio.guardar(usuario);
		 return"redirect:/";
}
	
	
	
	 @GetMapping("/listar1")
	 public String listar(Model model) {

		 model.addAttribute("usuarios", this.repositorio.listar());
		 return"param/listar";
		 
	 }
	
	@ModelAttribute

	public void setGenericos(Model model) {

		List<UsuarioImg> paises = new ArrayList<UsuarioImg>();
		
		paises.add(new UsuarioImg(null, "Departamento de baches", null, null, null,null,null));
		paises.add(new UsuarioImg(null, "Departamento Agua", null, null, null,null,null));
		paises.add(new UsuarioImg(null, "Departamento Luz", null, null, null,null,null));
		
	
		model.addAttribute("paises", paises);
	

 }
}