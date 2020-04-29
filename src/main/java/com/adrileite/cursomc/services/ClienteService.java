package com.adrileite.cursomc.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.adrileite.cursomc.domain.Cidade;
import com.adrileite.cursomc.domain.Cliente;
import com.adrileite.cursomc.domain.Endereco;
import com.adrileite.cursomc.domain.enums.TipoCliente;
import com.adrileite.cursomc.dto.ClienteDTO;
import com.adrileite.cursomc.dto.ClienteNewDTO;
import com.adrileite.cursomc.repositories.CidadeRepository;
import com.adrileite.cursomc.repositories.ClienteRepository;
import com.adrileite.cursomc.repositories.EnderecoRepository;
import com.adrileite.cursomc.services.exceptions.DataIntegrityException;
import com.adrileite.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public Cliente find(Integer id) { 
		Optional<Cliente> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException(   
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 
		
	}

	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		repo.save(obj)	;
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	

	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId()); //verifica se existe 
		updateData(newObj, obj);
		return repo.save(newObj)	; //serve para inserir e atualizar
	}
	
	public void delete (Integer id) {
		find(id);
		try{
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidade relacionada");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
		
	}
	
	public Cliente fromDTO (ClienteDTO objDto){
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	

	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}
}
