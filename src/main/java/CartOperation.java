import java.util.List;

interface CartOperation {

    int PRODUCTS_LIMIT = 500;

    boolean addProducts(String productName, int price, int quantity);

    boolean deleteProducts(String productName, int quantity);

    int getQuantityOfProduct(String productName);

    int getSumProductsPrices();

    int getProductPrice(String productName);

    List<String> getProductsNames();
}