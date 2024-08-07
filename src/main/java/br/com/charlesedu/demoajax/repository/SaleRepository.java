package br.com.charlesedu.demoajax.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import br.com.charlesedu.demoajax.domain.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s WHERE s.price = :price")
    Page<Sale> findByPrice(@Param("price") BigDecimal price, Pageable pageable);

    @Query("SELECT s FROM Sale s WHERE s.title LIKE %:search% OR s.site LIKE %:search% OR s.category.title LIKE %:search%")
    Page<Sale> findByTitleOrSiteOrCategory(@Param("search") String search, Pageable pageable);

    @Query("SELECT s FROM Sale s WHERE s.site LIKE :site")
    Page<Sale> findBySite(@Param("site") String site, Pageable pageable);

    @Query("SELECT DISTINCT s.site FROM Sale s WHERE s.site LIKE %:term%")
    List<String> findSitesByTerm(@Param("term") String term);

    @Transactional(readOnly = false)
    @Modifying
    @Query("UPDATE Sale s SET s.likes = COALESCE(s.likes, 0) + 1 WHERE s.id = :id")
    void sumLikes(@Param("id") Long id);

    @Query("SELECT s.likes FROM Sale s WHERE s.id = :id")
    int findLikesById(@Param("id") Long id);

    @Query("SELECT MAX(s.registerDate) FROM Sale s")
    LocalDateTime findSaleWithLastDate();

    @Query("SELECT COUNT(s.id) AS count, MAX(s.registerDate) AS lastDate FROM Sale s WHERE s.registerDate > :lastDate")
    Map<String, Object> countAndMaxNewSaleByRegisterDate(LocalDateTime lastDate);
}
