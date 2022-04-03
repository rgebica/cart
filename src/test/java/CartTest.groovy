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
        "Orange"    || null  || null     || false
        ""          || -123  || null     || false
        null        || 0     || null     || false
    }

}
