package com.adrileite.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adrileite.cursomc.domain.Categoria;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Integer> {

}
