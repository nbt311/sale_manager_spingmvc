package com.example.productmanager.controller;

import com.example.productmanager.model.Category;
import com.example.productmanager.model.Product;
import com.example.productmanager.service.ICategoryService;
import com.example.productmanager.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @GetMapping()
    public ModelAndView listProducts(@PageableDefault(size = 3) Pageable pageable) {
        ModelAndView mv = new ModelAndView("/product/list");
        Page<Product> products = productService.findAllPage(pageable);
        mv.addObject("products",products);
        return mv;
    }
    @ModelAttribute("category")
    public List<Category> listCategory() {
        return categoryService.findAll();
    }
    @GetMapping("/create")
    public ModelAndView fromCreateProduct(){
        ModelAndView mv = new ModelAndView("/product/form");
        mv.addObject("product", new Product());
        return mv;
    }
    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product){
        productService.save(product);
        return "redirect:/api/products";
    }
    @GetMapping("edit/{id}")
    public ModelAndView fromEditProduct(@PathVariable Long id){
        Product product = productService.findById(id);
        ModelAndView mv = new ModelAndView("/product/form");
        mv.addObject("product",product);
        return  mv;

    }
    @PostMapping("edit/{id}")
    public String editProduct(@ModelAttribute Product product){
        productService.save(product);
        return "redirect:/api/products";
    }
    @GetMapping("delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/api/products";
    }

    @PostMapping("/search")
    public ModelAndView findName(@RequestParam String name) {
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", productService.findByName(name));
        return modelAndView;
    }
    @GetMapping("/sort")
    public ModelAndView sort() {
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", productService.sortByPrice());
        return modelAndView;
    }
}
