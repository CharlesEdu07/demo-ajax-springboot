package br.com.charlesedu.demoajax.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.charlesedu.demoajax.domain.Category;
import br.com.charlesedu.demoajax.repository.CategoryRepository;

@Controller
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private CategoryRepository categoryRepository;

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/add")
    public String openRegistration() {
        return "sale-add";
    }
}
