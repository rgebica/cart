import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Cart implements CartOperation {

    private final List<Product> products = new ArrayList<>();
    private final int NO_PRODUCT = 0;

    public boolean addProducts(String productName, Integer price, Integer quantity) {
        if (productName == null || price == null || quantity == null) {
            return false;
        }

        if (getCurrentQuantity() + quantity > PRODUCTS_LIMIT || quantity <= 0 || price <= 0 || productName.isBlank()) {
            return false;
        }
        if (isAlreadyInCart(productName)) {
            products.stream()
                    .filter(product -> product.getProductName().equals(productName))
                    .map(product -> {
                        product.setQuantity(product.getQuantity() + quantity);
                        return true;
                    });
        }
        products.add(new Product(productName, price, quantity));
        return true;
    }

    public boolean deleteProducts(String productName, Integer quantity) {
        if (quantity == null || quantity <= 0) {
            return false;
        }

        Optional<Product> product = getProduct(productName);

        return product
                .map(p -> {
                    if (quantity > p.getQuantity()) {
                        return false;
                    } else {
                        p.setQuantity(p.getQuantity() - quantity);
                    }
                    return true;
                }).orElse(false);
    }

    public Integer getQuantityOfProduct(String productName) {
        Optional<Product> product = getProduct(productName);

        return product
                .map(Product::getQuantity)
                .orElse(NO_PRODUCT);
    }

    @Override
    public Integer getSumProductsPrices() {
        if (products.size() < 0) {
            return 0;
        }

        return products.stream()
                .map(product -> product.getQuantity() * product.getPrice())
                .mapToInt(Integer::intValue)
                .sum();
    }

    @Override
    public Integer getProductPrice(String productName) {
        Optional<Product> product = getProduct(productName);

        return product.map(p -> p.getPrice() * p.getQuantity())
                .orElse(NO_PRODUCT);
    }

    public List<String> getProductsNames() {
        Optional.of(this.products)
                .orElse(Collections.emptyList());

        return products.stream()
                .map(Product::getProductName)
                .collect(Collectors.toList());
    }

    public Integer getCurrentQuantity() {
        return products.stream()
                .mapToInt(Product::getQuantity)
                .sum();
    }

    private boolean isAlreadyInCart(String productName) {
        return products.stream()
                .anyMatch(product -> product.getProductName().equals(productName));
    }

    private Optional<Product> getProduct(String productName) {
        return products.stream()
                .filter(p -> p.getProductName().equals(productName))
                .findAny();
    }
}
