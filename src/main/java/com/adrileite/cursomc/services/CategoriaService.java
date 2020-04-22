package com.adrileite.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrileite.cursomc.domain.Categoria;
import com.adrileite.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id){
		
	//	Categoria obj = repo.getOne(id);
		Optional<Categoria> obj = repo.findById(id);

		 return obj.orElse(null); 

	//	return obj;
	}
	
}
