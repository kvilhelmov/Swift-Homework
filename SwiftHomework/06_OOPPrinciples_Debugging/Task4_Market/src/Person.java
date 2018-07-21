class Person{

    private String _name;
    private double _balance;
    private int _productsCount;
    
    private Product[] _productsBought;
    
    Person(String name, double balance) {
        _name = name;
        _balance = balance;
        _productsBought = new Product[500];
        _productsCount = 0;
    }

    String getName() {
        return _name;
    }

    boolean tryBuy(Product product) {
        if(_balance >= product.getPrice()){
            _balance -= product.getPrice();
            _productsBought[_productsCount++] = product;
            return true;
        } 
        
        return false;
    }

    Product[] getProductsBought() {
        
        Product[] products = new Product[_productsCount];
        
        //System.arraycopy(_productsBought, 0, products, 0, _productsCount);
        
        for(int i = 0 ; i < _productsCount; i++){
            products[i] = _productsBought[i];
        }
        
        return products;
    }
    
}