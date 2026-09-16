package org.example.product;

import org.assertj.core.api.SoftAssertions;
import org.example.discount.DiscountDecorator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class ProductTest {
    @Test
    void createProductWithNoId_ThrowsIllegalArgumentException() {
        Product.Builder productBuilder = new Product.Builder()
                .name("Test")
                .price(BigDecimal.valueOf(10));

        assertThatThrownBy(productBuilder::build)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Produkten saknar ett ID");
    }

    @Test
    void createProductWithNoName_ThrowsIllegalArgumentException() {
        Product.Builder productBuilder = new Product.Builder()
                .id("1")
                .name("  ")
                .price(BigDecimal.valueOf(10));

        assertThatThrownBy(productBuilder::build)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Produkten saknar ett namn");
    }

    @Test
    void createProductWithNoPrice_ThrowsIllegalArgumentException() {
        Product.Builder productBuilder = new Product.Builder()
                .id("1")
                .name("Test");

        assertThatThrownBy(productBuilder::build)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Produkten saknar ett pris");
    }

    @Test
    void createProductWithMissingOptionalFields_UsesFallbackValues() {
        LocalDateTime before = LocalDateTime.now().minusSeconds(1);

        Product product = new Product.Builder()
                .id("1")
                .name("Test")
                .price(BigDecimal.valueOf(20))
                .build();

        LocalDateTime after = LocalDateTime.now().plusSeconds(1);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(product.getCategory()).isEqualTo(Product.Category.MISC);
            softly.assertThat(product.getRating()).isEqualTo(0.0);
            softly.assertThat(product.getCreatedDate())
                    .isBetween(before, after);
            softly.assertThat(product.getModifiedDate())
                    .isBetween(before, after);
        });
    }

    @Test
    void initializeDiscountPercentWithNaN_ThrowsIllegalArgumentException() {
        Product product = new Product.Builder()
                .id("1")
                .name("Test")
                .price(BigDecimal.valueOf(100))
                .build();

        assertThatIllegalArgumentException()
                .isThrownBy(() -> new DiscountDecorator(product, Double.NaN))
                .withMessage("Värdet på rabatten får inte vara NaN");
    }

    @Test
    void getPriceOfDiscountedProduct_ShouldReturnDiscountedPrice() {
        Product product = new Product.Builder()
                .id("1")
                .name("Test")
                .price(BigDecimal.valueOf(1000))
                .build();

        Sellable discountedProduct = new DiscountDecorator(product, 0.20);

        assertThat(discountedProduct.getPrice()).isEqualTo(BigDecimal.valueOf(800.0));
    }
}
