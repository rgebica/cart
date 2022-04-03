import java.util.List;

public class Cart implements CartOperation {

    public boolean addProducts(String productName, int price, int quantity) {
        return false;
    }

    public boolean deleteProducts(String productName, int quantity) {
        return false;
    }

    public int getQuantityOfProduct(String productName) {
        return 0;
    }

    public int getSumProductsPrices() {
        return 0;
    }

    public int getProductPrice(String productName) {
        return 0;
    }

    public List<String> getProductsNames() {
        return null;
    }
}
