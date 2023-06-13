package cl.gust.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cl.gust.modelos.UsuarioImg;
import cl.gust.repositorios.IUsuarioimg;

@Service
@Primary
public class ReporteService {

	@Autowired
	
	private IUsuarioimg repositorio;
	
	
public List<UsuarioImg>listar(){
		
		
		return this.repositorio.findAll();
		
	}	


public void guardar(UsuarioImg usuario) {
	
	this.repositorio.save(usuario);



}



public UsuarioImg buscarPorId(String id) {
	
	Optional<UsuarioImg>optional=this.repositorio.findById(id);
	
	if(optional.isPresent()) {
		
		return optional.get();
	}
	else {
		
		return null;
	}
	
}

public void eliminar(String id) {

this.repositorio.deleteById(id);
}
}