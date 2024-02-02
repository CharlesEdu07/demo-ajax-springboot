package br.com.charlesedu.demoajax.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.charlesedu.demoajax.domain.Category;
import br.com.charlesedu.demoajax.domain.Sale;
import br.com.charlesedu.demoajax.repository.CategoryRepository;
import br.com.charlesedu.demoajax.repository.SaleRepository;

@Controller
@RequestMapping("/sale")
public class SaleController {

    private static Logger log = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/save")
    public ResponseEntity<Sale> saveSale(Sale sale) {
        log.info("Sale: {}", sale);

        sale.setRegisterDate(LocalDateTime.now());

        saleRepository.save(sale);

        return ResponseEntity.ok().build();
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/add")
    public String openRegistration() {
        return "sale-add";
    }
}
