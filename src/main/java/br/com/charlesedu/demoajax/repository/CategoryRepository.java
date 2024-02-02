package br.com.charlesedu.demoajax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.charlesedu.demoajax.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
