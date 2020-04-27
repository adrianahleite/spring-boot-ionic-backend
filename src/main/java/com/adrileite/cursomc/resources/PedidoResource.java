package com.adrileite.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adrileite.cursomc.domain.Pedido;
import com.adrileite.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos") // end point
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity <?> find(@PathVariable Integer id) {

		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);   //1 teste
	}

}
