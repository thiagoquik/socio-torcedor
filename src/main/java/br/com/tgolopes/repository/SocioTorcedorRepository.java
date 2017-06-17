package br.com.tgolopes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tgolopes.entity.SocioTorcedor;

@Repository
public interface SocioTorcedorRepository extends JpaRepository<SocioTorcedor, Long>{
	SocioTorcedor findOneById(Long id);
	SocioTorcedor findByEmail(String email);
}