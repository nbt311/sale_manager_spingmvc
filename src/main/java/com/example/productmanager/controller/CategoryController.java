package com.example.productmanager.controller;

import com.example.productmanager.model.Category;
import com.example.productmanager.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping()
    public ModelAndView listCategory() {
        List<Category> categories = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("/category/list");
        modelAndView.addObject("categories",categories);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView formCreateCategory(){
        ModelAndView modelAndView = new ModelAndView("/category/form");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }
    @PostMapping("/create")
    public String createCategory(@ModelAttribute Category category){
        categoryService.save(category);
        return "redirect:/api/categories";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView formEditCategory(@PathVariable Long id){
        Category category = categoryService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/category/form");
        modelAndView.addObject("category",category);
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public String editCategory(@ModelAttribute Category category){
        categoryService.save(category);
        return "redirect:/api/categories";
    }

}
