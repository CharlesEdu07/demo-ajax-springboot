package br.com.charlesedu.demoajax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.charlesedu.demoajax.domain.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT DISTINCT s.site FROM Sale s WHERE s.site LIKE %:term%")
    List<String> findSitesByTerm(@Param("term") String term);

    @Transactional(readOnly = false)
    @Modifying
    @Query("UPDATE Sale s SET s.likes = COALESCE(s.likes, 0) + 1 WHERE s.id = :id")
    void sumLikes(@Param("id") Long id);

    @Query("SELECT s.likes FROM Sale s WHERE s.id = :id")
    int findLikesById(@Param("id") Long id);
}
