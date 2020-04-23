package com.adrileite.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adrileite.cursomc.domain.Estado;

@Repository
public interface EstadoRepository  extends JpaRepository<Estado, Integer> {

}
