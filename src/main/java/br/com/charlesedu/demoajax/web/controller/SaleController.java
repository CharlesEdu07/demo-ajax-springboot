package br.com.charlesedu.demoajax.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.charlesedu.demoajax.domain.Category;
import br.com.charlesedu.demoajax.domain.Sale;
import br.com.charlesedu.demoajax.repository.CategoryRepository;
import br.com.charlesedu.demoajax.repository.SaleRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/sale")
public class SaleController {

    private static Logger log = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/add")
    public String openRegistration() {
        return "sale-add";
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveSale(@Valid Sale sale, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.unprocessableEntity().body(errors);
        }

        log.info("Sale: {}", sale);

        sale.setRegisterDate(LocalDateTime.now());

        saleRepository.save(sale);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public String saleList(ModelMap model) {
        Sort sort = Sort.by("registerDate").descending();

        PageRequest pageRequest = PageRequest.of(0, 8, sort);

        model.addAttribute("sales", saleRepository.findAll(pageRequest));

        return "sale-list";
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
