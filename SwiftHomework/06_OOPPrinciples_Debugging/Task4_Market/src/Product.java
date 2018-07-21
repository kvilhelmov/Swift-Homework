class Product{

    private String _name;
    private double _price;
    
    Product(String name, double price) {
        _name = name;
        _price = price;
    }

    String getName() {
        return _name;
    }

    double getPrice() {
        return _price;
    }
    
}