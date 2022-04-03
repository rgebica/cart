import java.util.ArrayList;
import java.util.List;

public class Cart implements CartOperation {

    private final List<Product> products = new ArrayList<>();

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
        return false;
    }

    public Integer getQuantityOfProduct(String productName) {
        return 0;
    }

    public Integer getSumProductsPrices() {
        return 0;
    }

    public Integer getProductPrice(String productName) {
        return 0;
    }

    public List<String> getProductsNames() {
        return null;
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
}
