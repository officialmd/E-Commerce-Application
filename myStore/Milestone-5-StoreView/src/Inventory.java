//Adam Sabbagh 101162538//
//Mohamad Deifallah 101123377//

import java.util.ArrayList;

public class Inventory extends ProductStockContainer {

    //decleration of instance variables
    //private variavle to store the products
    private ArrayList<Product> products;
    //instance variable to store the stock data
    private ArrayList<Integer> stocks;

    //by default constructor to intialize the instance variables
    public Inventory() {

        fillInventory();
    }

    //this method return the inventory list of products
    public ArrayList<Product> getInventoryList() {
        return products;
    }

    //this method is overridden method  and this method return the quantity of a given product
    public int getProductQuantity(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == product.getProductId()) {
                return stocks.get(i);
            }
        }
        return -1;
    }

    //this method is overridden method  and this method  increment the quantity of the given product
    public void addProductQuantity(Product product, int quantity) {
        boolean flag = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == product.getProductId()) {
                stocks.set(i, stocks.get(i) + quantity);
                flag = true;
            }
        }
        if (!flag) {
            products.add(product);
            stocks.add(quantity);
        }
    }

    //this method is overridden method  and this method minus  the quantity of a given product and return an integer
    //value
    public int removeProductQuantity(Product product, int quantity) {
        int flag = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == product.getProductId()) {
                if (stocks.get(i) > quantity) {
                    stocks.set(i, stocks.get(i) - quantity);
                    flag = 1;
                }
            }
        }
        return flag;
    }

    //this method is overridden method  and this method return the information fo the given product
    public Product getProductInfo(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == product.getProductId()) {
                return products.get(i);
            }
        }
        return null;
    }

    // //this method is overridden method  and this method return the number of the products in the arraylist
    public int getNumOfProducts() {
        return products.size();
    }

    //this method is overridden method  and this method return the product object of given product id
    public Product getProductById(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    //this method fills the inventory that means this will add the product to the arraylist
    //add that product to the stock
    public void fillInventory(){
        products = new ArrayList<>();
        stocks = new ArrayList<>();
        Product product1=new Product("The Shawshank Redemption", 3382, 20.0);
        products.add(product1);
        stocks.add(27);
        Product product2=new Product("Avengers", 3928, 34.0);
        products.add(product2);
        stocks.add(89);
        Product product3=new Product("Harry Potter", 1389, 47);
        products.add(product3);
        stocks.add(123);
        Product product4=new Product("Captain America", 2891, 28.0);
        products.add(product4);
        stocks.add(98);
        Product product5=new Product("Clash of The Titans", 4823, 27.0);
        products.add(product5);
        stocks.add(9);
        Product product6=new Product("Avatar", 1226, 19.0);
        products.add(product6);
        stocks.add(56);
        Product product7=new Product("The Matrix", 5992, 25.0);
        products.add(product7);
        stocks.add(22);
        Product product8=new Product("Titanic", 9212, 30.0);
        products.add(product8);
        stocks.add(28);
        Product product9=new Product("The Terminator", 2783, 40.0);
        products.add(product9);
        stocks.add(189);
    }
}
