package org.example.product;

import java.math.BigDecimal;

public abstract class ProductDecorator implements Sellable {
    protected Sellable decoratedProduct;

    protected ProductDecorator(Sellable sellable) {
        this.decoratedProduct = sellable;
    }

    @Override
    public String getId() {
        return decoratedProduct.getId();
    }

    @Override
    public String getName() {
        return decoratedProduct.getName();
    }

    @Override
    public BigDecimal getPrice() {
        return decoratedProduct.getPrice();
    }
}
