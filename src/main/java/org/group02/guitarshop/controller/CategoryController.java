package org.group02.guitarshop.controller;

import org.group02.guitarshop.entity.Category;
import org.group02.guitarshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/san-pham/{metadata}", method = RequestMethod.GET)
    public String Category(Model model,
                           @PathVariable(value="metadata") String metadata,
                           @RequestParam(value="manufacturer", defaultValue = "All", required = false) String manufacturer,
                           @RequestParam(value="style", defaultValue = "All", required = false) String style,
                           @RequestParam(value="minPrice", defaultValue = "0", required = false) int minPrice,
                           @RequestParam(value="maxPrice", defaultValue = "10000000", required = false) int maxPrice,
                           @RequestParam(value="page", defaultValue = "1", required = false) int pageNumber) {
        Category category = categoryService.getCategoryByMetadata(metadata);
        model.addAttribute("mMetadata", metadata);
        model.addAttribute("mManufacturer", manufacturer);
        model.addAttribute("mStyle", style);
        model.addAttribute("mMinPrice", minPrice);
        model.addAttribute("mMaxPrice", maxPrice);
        model.addAttribute("styleList", categoryService.getListOfStyleNames(category.getId()));
        model.addAttribute("manufacturerList", categoryService.getListOfManufacturerNames(category.getId()));

        PagedListHolder<?> pageListProduct = new PagedListHolder<>(categoryService.getListProduct(category.getId(), manufacturer, style, minPrice, maxPrice));
        pageListProduct.setPageSize(9);
        final int goToPage = pageNumber - 1;
        if (goToPage <= pageListProduct.getPageCount() && goToPage >= 0) {
            pageListProduct.setPage(goToPage);
        }
        int currentPage = pageListProduct.getPage() + 1;
        int beginPage = Math.max(1, currentPage - pageListProduct.getSource().size());
        int endPage = Math.min(beginPage + 4, pageListProduct.getPageCount());
        model.addAttribute("beginPage", beginPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageListProduct", pageListProduct);

        return "/main/category";
    }
}
