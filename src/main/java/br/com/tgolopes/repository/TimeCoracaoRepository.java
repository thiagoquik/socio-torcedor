package br.com.tgolopes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tgolopes.entity.TimeCoracao;

@Repository
public interface TimeCoracaoRepository extends JpaRepository<TimeCoracao, Long>{
	TimeCoracao findByNomeIgnoreCase(String nome);
}