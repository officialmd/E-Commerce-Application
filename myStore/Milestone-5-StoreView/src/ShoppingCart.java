//Adam Sabbagh 101162538//
//Mohamad Deifallah 101123377//


import java.util.ArrayList;

/**
 *
 * @author
 */
public class ShoppingCart extends ProductStockContainer{

    //instance variable declaration

    //private variable to store the products
    private ArrayList<Product> products;
    //private variable to store the quantities of the products
    private ArrayList<Integer> quantities;

    //by default constructor to initialize the instance variable declaration
    public ShoppingCart() {
        products = new ArrayList<>();
        quantities = new ArrayList<>();
    }

    //this method is override method and is used to add the quantity for given product
    public void addProductQuantity(Product product, int quantity) {
        if(product!=null && quantity>0) {
            this.products.add(product);
            this.quantities.add(quantity);
        }
    }

    //this method is overidden method and is to subtract the given quantity for the given product
    public int removeProductQuantity(Product product ,int quantity) {
        int flag = 0;
        if(product!=null && quantity>0) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductId() == product.getProductId()) {
                    quantities.set(i, (quantities.get(i) - quantity));
                    flag = 1;
                }
            }
        }
        return flag;
    }

    // //this method is overridden method  and this method return the details of the given product
    public Product getProductInfo(Product product) {
        if (product != null){
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductId() == product.getProductId()) {
                    return products.get(i);
                }
            }
        }
        return null;
    }

    //this method is overridden method , used to get the quantity of given product
    public int getProductQuantity(Product product) {
        if(product!=null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductId() == product.getProductId()) {
                    return quantities.get(i);
                }
            }
        }
        return -1;

    }

    // //this method is overridden method  and this method return the number of the products in the arraylist
    @Override
    public int getNumOfProducts(){
        return products.size();
    }

    //this method return the products that are in the cart
    public ArrayList<Product> getCartItems() {
        return products;
    }

    //this method is overridden method  and this method return the product object of given product id
    public Product getProductById(int productId){
        if(productId>0){
        for(Product product:products){
            if(product.getProductId()==productId){
                return  product;
            }
        }
        }
        return  null;
    }

}
