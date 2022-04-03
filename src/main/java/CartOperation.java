import java.util.List;

interface CartOperation {

    int PRODUCTS_LIMIT = 500;

    boolean addProducts(String productName, Integer price, Integer quantity);

    boolean deleteProducts(String productName, Integer quantity);

    Integer getQuantityOfProduct(String productName);

    Integer getSumProductsPrices();

    Integer getProductPrice(String productName);

    List<String> getProductsNames();
}