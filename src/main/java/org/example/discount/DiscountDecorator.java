package org.example.discount;

import org.example.product.ProductDecorator;
import org.example.product.Sellable;

import java.math.BigDecimal;

public class DiscountDecorator extends ProductDecorator {
    private final double discountPercent;

    public DiscountDecorator(Sellable sellableProduct, double discountPercent) {
        super(sellableProduct);
        this.discountPercent = Math.clamp(discountPercent, 0.0, 1.0);
    }

    @Override
    public BigDecimal getPrice() {
        var discount = BigDecimal.valueOf(Math.abs(discountPercent - 1));
        return super.getPrice().multiply(discount);
    }
}
