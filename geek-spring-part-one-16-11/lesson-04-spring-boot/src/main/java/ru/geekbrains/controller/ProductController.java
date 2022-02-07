package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.service.ProductService;
import ru.geekbrains.service.dto.ProductDto;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductService productService,
                             CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("nameFilter") Optional<String> nameFilter,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sort") Optional<String> sort) {
        logger.info("Product filter with name pattern {}", nameFilter.orElse(null));

        int currentPage = page.orElse(1);
        model.addAttribute("products", productService.findAll(
                nameFilter,
                page.orElse(1) - 1,
                size.orElse(5),
                sort.filter(s -> !s.isBlank()).orElse("id")
        ));
        model.addAttribute("currentPage", currentPage);
        return "product";
    }

    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        model.addAttribute("categories", categoryRepository.findAll());
        return "product_form";
    }

    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("product", new ProductDto());
        model.addAttribute("categories", categoryRepository.findAll());
        return "product_form";
    }

    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @PostMapping
    public String save(@Valid ProductDto product, BindingResult result) {
        if (result.hasErrors()) {
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return "redirect:/product";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}

