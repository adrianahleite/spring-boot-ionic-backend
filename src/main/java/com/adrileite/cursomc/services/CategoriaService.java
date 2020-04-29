package com.adrileite.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.adrileite.cursomc.domain.Categoria;
import com.adrileite.cursomc.domain.Cliente;
import com.adrileite.cursomc.dto.CategoriaDTO;
import com.adrileite.cursomc.repositories.CategoriaRepository;
import com.adrileite.cursomc.services.exceptions.DataIntegrityException;
import com.adrileite.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) { 
		Optional<Categoria> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException(   
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); 
		
	}
	
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj)	;
	}
	
	
	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId()); //verifica se existe 
		updateData(newObj, obj);
		return repo.save(newObj)	; //serve para inserir e atualizar
	}
	
	public void delete (Integer id) {
		find(id);
		try{
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um categoria que possui produto");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
		
	}
	
	public Categoria fromDTO (CategoriaDTO objDto){
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
		
	}
	
}
