package com.example.springclouddemotrade.product.controller;

import com.example.springclouddemotrade.common.constants.Constants;
import com.example.springclouddemotrade.common.resp.ApiResult;
import com.example.springclouddemotrade.product.entity.Category;
import com.example.springclouddemotrade.product.entity.PageSearch;
import com.example.springclouddemotrade.product.entity.Product;
import com.example.springclouddemotrade.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @Description TODO
 * @Author hnuya
 * @Date 2019/9/25
 **/
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

    /**
     * 列出分类
     * @return
     */
    @RequestMapping("/category")
    public ApiResult<List<Category>> listCategory() {
        ApiResult<List<Category>> result = new ApiResult<>(Constants.RESP_STATUS_OK,"查询分类成功");
        List<Category> list = productService.listCategory();
        result.setData(list);
        return result;
    }

    /**
     * 列出分类下的产品
     * @param categoryId
     * @return
     */
    @RequestMapping("/list/{categoryId}")
    public ApiResult<List<Product>> listProduct(@PathVariable Long categoryId) {
        ApiResult<List<Product>> result = new ApiResult<>(Constants.RESP_STATUS_OK,"查询分类下产品成功");
        List<Product> list = productService.listProduct(categoryId);
        result.setData(list);
        return result;
    }

    /**
     * 检索商品
     * @param pageSearch
     * @return
     */
    @RequestMapping("/search")
    public ApiResult<List<Product>> searchProduct(@RequestBody PageSearch pageSearch) throws IOException {
        ApiResult<List<Product>> result = new ApiResult<>(Constants.RESP_STATUS_OK,"查询商品成功");
        List<Product> list = productService.searchProduct(pageSearch.getPageNum(),
                pageSearch.getPageSize(), pageSearch.getSearchContent());
        result.setData(list);
        return result;
    }

    /**
     * 查询商品详情
     * @param id
     * @return
     */
    @RequestMapping("/detail/{id}")
    public ApiResult<Product> productDetail(@PathVariable Long id){
        ApiResult<Product> result = new ApiResult<>(Constants.RESP_STATUS_OK,"查询商品详情成功");
        Product product = productService.productDetail(id);
        result.setData(product);
        return result;
    }
}
