package org.example.product;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;

class ProductTest {
    @Test
    void createProductWithNoId_ThrowsIllegalArgumentException() {
        Product.Builder productBuilder = new Product.Builder()
                .name("Test");

        assertThatThrownBy(productBuilder::build)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Produkten saknar ett ID");
    }

    @Test
    void createProductWithNoName_ThrowsIllegalArgumentException() {
        Product.Builder productBuilder = new Product.Builder()
                .id("1")
                .name("  ");

        assertThatThrownBy(productBuilder::build)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Produkten saknar ett namn");
    }

    @Test
    void createProductWithMissingOptionalFields_UsesFallbackValues() {
        Product product = new Product.Builder()
                .id("1")
                .name("Test")
                .build();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(product.getCategory()).isEqualTo(Product.Category.MISC);
            softly.assertThat(product.getRating()).isEqualTo(0.0);
            softly.assertThat(product.getCreatedDate())
                    .isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
            softly.assertThat(product.getModifiedDate())
                    .isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
        });
    }
}
