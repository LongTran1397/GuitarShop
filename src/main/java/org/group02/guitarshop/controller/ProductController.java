package org.group02.guitarshop.controller;

import org.group02.guitarshop.entity.Product;
import org.group02.guitarshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/chi-tiet", method = RequestMethod.GET)
    public String productDetail(String name, Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        productService.GetProductExtraInfo(id);
        model.addAttribute("TotalRate",productService.getTotalRate());
        model.addAttribute("ListCountRate",productService.getListCountRate());
        model.addAttribute("ListRelativeProduct",productService.getListRelatedProducts());
        model.addAttribute("AverageRate",productService.getAverageRate());
        model.addAttribute("ListImage",productService.getProductImage());
        return "main/product-detail";
    }

    @GetMapping("/admin/viewProducts")
    public String viewProduct(Model model){
        List<Product> productList = productService.listAll();
        model.addAttribute("productList", productList);
        return "admin/viewProducts";
    }

    @GetMapping("admin/createProduct")
    public String createMovie(ModelMap model){
        model.addAttribute("movieDto", movieDto);
        model.addAttribute("action", "/saveMovie");
        return "admin/createMovie";
    }
//
//    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
//    public ModelAndView saveNewProduct(@ModelAttribute Product product, BindingResult result) {
//        ModelAndView mv = new ModelAndView("redirect:/home");
//
//        if (result.hasErrors()) {
//            return new ModelAndView("error");
//        }
//        boolean isAdded = productService.addProduct(product);
//        if (isAdded) {
//            mv.addObject("message", "New product successfully added");
//        } else {
//            return new ModelAndView("error");
//        }
//
//        return mv;
//    }
//
//    @RequestMapping(value = "/editProduct/{id}", method = RequestMethod.GET)
//    public ModelAndView displayEditProductForm(@PathVariable Long id) {
//        ModelAndView mv = new ModelAndView("/editProduct");
//        Product product = productService.getProductById(id);
//        mv.addObject("headerMessage", "Edit Product Details");
//        mv.addObject("product", product);
//        return mv;
//    }
//
//    @RequestMapping(value = "/editProduct/{id}", method = RequestMethod.POST)
//    public ModelAndView saveEditedProduct(@ModelAttribute Product product, BindingResult result) {
//        ModelAndView mv = new ModelAndView("redirect:/home");
//
//        if (result.hasErrors()) {
//            System.out.println(result.toString());
//            return new ModelAndView("error");
//        }
//        boolean isSaved = productService.addProduct(product);
//        if (!isSaved) {
//
//            return new ModelAndView("error");
//        }
//
//        return mv;
//    }
//
//    @RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.GET)
//    public ModelAndView deleteProductById(@PathVariable Long id) {
//        boolean isDeleted = productService.removeProduct(id);
//        System.out.println("Product deletion respone: " + isDeleted);
//        ModelAndView mv = new ModelAndView("redirect:/home");
//        return mv;
//
//    }

}
