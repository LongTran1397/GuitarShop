package org.group02.guitarshop.controller;

import org.group02.guitarshop.entity.Category;
import org.group02.guitarshop.entity.Manufacturer;
import org.group02.guitarshop.entity.Product;
import org.group02.guitarshop.service.CategoryService;
import org.group02.guitarshop.service.ManufacturerService;
import org.group02.guitarshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/chi-tiet", method = RequestMethod.GET)
    public String productDetail(String name, Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        productService.GetProductExtraInfo(id);
        model.addAttribute("TotalRate", productService.getTotalRate());
        model.addAttribute("ListCountRate", productService.getListCountRate());
        model.addAttribute("ListRelativeProduct", productService.getListRelatedProducts());
        model.addAttribute("AverageRate", productService.getAverageRate());
        model.addAttribute("ListImage", productService.getProductImage());
        return "main/product-detail";
    }

    @GetMapping("/admin/viewProducts")
    public String viewProduct(Model model) {
        List<Product> productList = productService.listAll();
        model.addAttribute("productList", productList);
        return "admin/viewProducts";
    }

    @ModelAttribute(name = "CategoryList")
    public List<Category> listCategoryName(){
        return productService.listAllCategory();
    }

    @ModelAttribute(name = "ManufacturerList")
    public List<Manufacturer> listManufacturerName(){
        return productService.listAllManufacturer();
    }

    @GetMapping("/admin/createProduct")
    public String createMovie(ModelMap model){
        model.addAttribute("product", new Product());
        model.addAttribute("action", "/saveProduct");
        return "admin/addProduct";
    }
//
//    @PostMapping("/saveMovie")
//    public String saveMovie(ModelMap map, @ModelAttribute("movieDto") MovieDetailsDto movieDto) {
//        Optional<MovieDetails> optionalMovie = movieService.findById(movieDto.getMovieid());
//        MovieDetails movie = null;
//        String image = "blankmovie.jpg";
//        Path path = Paths.get("src/main/resources/static/images/uploads/");
//        if (optionalMovie.isPresent()) {
//            //update
//            if (movieDto.getImage().isEmpty()) {
//                image = optionalMovie.get().getImage();
//            } else {
//                try {
//                    InputStream inputStream = movieDto.getImage().getInputStream();
//                    Files.copy(inputStream, path.resolve(movieDto.getImage().getOriginalFilename()),
//                            StandardCopyOption.REPLACE_EXISTING);
//                    image = movieDto.getImage().getOriginalFilename().toString();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            //add new
//            if (!movieDto.getImage().isEmpty()) {
//                try {
//                    InputStream inputStream = movieDto.getImage().getInputStream();
//                    Files.copy(inputStream, path.resolve(movieDto.getImage().getOriginalFilename()),
//                            StandardCopyOption.REPLACE_EXISTING);
//                    image = movieDto.getImage().getOriginalFilename().toString();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        movie = new MovieDetails(movieDto.getMovieid(), movieDto.getMoviename(), movieDto.getDirector(), movieDto.getDuration(),
//                movieDto.getGenre(), movieDto.getDescription(), movieDto.getRating(), image);
//        movieService.save(movie);
//        return "redirect:/viewMovies";
//    }

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
