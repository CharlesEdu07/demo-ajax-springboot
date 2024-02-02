package br.com.charlesedu.demoajax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.charlesedu.demoajax.domain.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
