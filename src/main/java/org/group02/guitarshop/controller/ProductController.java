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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public String productDetail(Integer id, Model model) {
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
        return "admin/addProduct";
    }

    @GetMapping("/admin/updateProduct/{id}")
    public ModelAndView updateProduct(@PathVariable(name = "id") int id){
        ModelAndView mav = new ModelAndView("/admin/updateProduct");
        Product product = productService.getProductById(id);
        mav.addObject("product", product);
        return mav;
    }

    @PostMapping("/admin/saveProduct")
    public String saveCategory(@ModelAttribute("product") Product product,
                               @RequestParam("fileImage")MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        product.setImageThumbnail(fileName);
        Product savedProduct = productService.save(product);
        String uploadDir = "src/main/resources/static/img/products/" + savedProduct.getId();
        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try(InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new IOException("Không Upload được File: " + fileName);
        }
        return "redirect:/admin/viewProducts";
    }

    @GetMapping("/admin/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id){
        productService.deleteById(id);
        return "redirect:/admin/viewProducts";
    }
}
