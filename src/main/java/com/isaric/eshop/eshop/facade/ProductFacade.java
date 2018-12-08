package com.isaric.eshop.eshop.facade;

import java.util.List;

import com.isaric.eshop.eshop.facade.data.ProductData;

public interface ProductFacade {

    ProductData updateProduct(ProductData productData);

    List<ProductData> getAllProducts();

    ProductData createNewProduct(ProductData productData);
}
