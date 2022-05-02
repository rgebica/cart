import spock.lang.Specification

class CartTest extends Specification {

    def "shouldAddProductToCart"() {
        given:
        def cart = new Cart()

        when:
        boolean result = cart.addProducts("Apple", 123, 1)

        then:
        result
    }

    def "shouldNotAddProductToCart"() {
        given:
        def cart = new Cart()

        when:
        boolean result = cart.addProducts(productName, price, quantity)

        then:
        result == expected

        where:
        productName || price || quantity || expected
        "Apple"     || 123   || -1       || false
        "Milka"     || -123  || 0        || false
        null        || null  || null     || false
        "null"      || -13   || null     || false
    }

    def "shouldDeleteProducts"() {
        given:
        def shoppingCart = new Cart()
        shoppingCart.addProducts("Apple", 123, 32)

        expect:
        shoppingCart.deleteProducts(productName, quantity) == expected

        where:
        productName | quantity | expected
        "Apple"     | 1        | true
    }

    def "shouldGetQuantityOfProduct"() {
        given:
        def shoppingCart = new Cart()
        shoppingCart.addProducts("Apple", 123, 12)
        shoppingCart.addProducts("Milk", 321, 15)

        expect:
        shoppingCart.getQuantityOfProduct("Apple") == 12
    }

    def "shouldNotGetQuantityOfProduct"() {
        given:
        def shoppingCart = new Cart()
        shoppingCart.addProducts("Apple", 123, 12)
        shoppingCart.addProducts("Milk", 321, 15)

        expect:
        shoppingCart.getQuantityOfProduct(productName) == expected

        where:
        productName | expected
        "Coffee"    | 0
        "Tea"       | 0
        null        | 0
    }

    def "shouldGetSumDifferentProductsPrices"() {
        given:
        def shoppingCart = new Cart()
        shoppingCart.addProducts("Apple", 5, 3)
        shoppingCart.addProducts('Tea', 10, 1)

        expect:
        shoppingCart.getSumProductsPrices() == 25
    }

    def "shouldGetSumProductsPricesWithSameProduct"() {
        given:
        def shoppingCart = new Cart()
        shoppingCart.addProducts("Apple", 5, 3)
        shoppingCart.addProducts('Tea', 10, 1)
        shoppingCart.addProducts('Tea', 15, 3)

        expect:
        shoppingCart.getSumProductsPrices() == 25
    }

    def "shouldGetProductPrice"() {
        given:
        def shoppingCart = new Cart()
        shoppingCart.addProducts("Apple", 5, 4)
        shoppingCart.addProducts("Milk", 10, 2)

        expect:
        shoppingCart.getProductPrice(productName) == expected

        where:
        productName | expected
        "Apple"     | 20
        "Milk"      | 20
    }

    def "shouldGetProductsNames"() {
        given:
        def shoppingCart = new Cart()
        shoppingCart.addProducts("Apple", 5, 4)
        shoppingCart.addProducts("Milk", 10, 2)
        shoppingCart.addProducts("Tea", 5, 4)
        shoppingCart.addProducts("Coffee", 10, 2)

        when:
        List<String> shoppingCartList = shoppingCart.getProductsNames()

        then:
        shoppingCartList.get(0) == "Apple"
        shoppingCartList.get(1) == "Milk"
        shoppingCartList.get(2) == "Tea"
        shoppingCartList.get(3) == "Coffee"
    }

    def "shouldNotGetProductsNames"() {
        given:
        def shoppingCart = new Cart()

        when:
        List<String> shoppingCartList = shoppingCart.getProductsNames()

        then:
        shoppingCartList.size() == 0

    }

}
