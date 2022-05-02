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
        shoppingCart.getQuantityOfProduct(productName) == expected

        where:
        productName | expected
        "Apple"     | 12
        "Milk"      | 15
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

}
