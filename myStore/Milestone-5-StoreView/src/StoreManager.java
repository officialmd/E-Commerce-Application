//Adam Sabbagh 101162538//
//Mohamad Deifallah 101123377//

import java.util.ArrayList;

/**
 *
 * @author
 */
public class StoreManager {

    //instance variable declaration
    //declare inventory object
    private Inventory inventory;
    //declare the shopping cart item
    private ArrayList<ShoppingCart> cartItems;
    //declare the totalprice variable to store the total amount
    private double totalPrice;


    //by default constructor to initialize the instance variables
    public StoreManager() {
        inventory = new Inventory();
        cartItems = new ArrayList<>();
        totalPrice = 0;
    }

    //this method return the quantity for the given product
    public int getProductQuantity(Product product) {
        if(product!=null) {
            return inventory.getProductQuantity(product);
        }
        return -1;
    }

    //this method return the total price of the product for given cartId
    public double processTransaction(int cartId) {
        ShoppingCart cart = cartItems.get(cartId);
        totalPrice = 0;
        for (int i = 0; i < cart.getCartItems().size(); i++) {
            Product p = cart.getCartItems().get(i);
            totalPrice += p.getProductPrice() * cart.getProductQuantity(p);
        }
        return totalPrice;
    }

    //this method generate the id for the cart
    public int assignNewCartID() {
        ShoppingCart cart = new ShoppingCart();
        cartItems.add(cart);
        return cartItems.indexOf(cart);
    }

    //this method get the product of given cart id
    public ArrayList<Product> getCartItems(int cartId) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (i == cartId) {
                return cartItems.get(i).getCartItems();
            }
        }
        return null;
    }

    //this method return the quantity of a product in a given cart
    public int getCartItemQuantity(int cartId, Product product) {
        ShoppingCart cartItem = cartItems.get(cartId);
        return cartItem.getProductQuantity(product);
    }

    //this return the inventory list
    public ArrayList<Product> getInventoryList() {
        return inventory.getInventoryList();
    }

    //this add the item in the cart
    public boolean addCartItem(int cartId, Product p, int quantity) {
        if(p!=null) {
            if (inventory.removeProductQuantity(p, quantity) == 1) {
                ShoppingCart cart = cartItems.get(cartId);
                cart.addProductQuantity(p, quantity);
                cartItems.set(cartId, cart);
                return true;
            }
        }
        return false;
    }

    //this method remove the product from the cart
    public boolean removeCartItem(int carts, Product prod) {
        if(prod!=null){
        int quantity = cartItems.get(carts).getProductQuantity(prod);
        ShoppingCart shopCart = cartItems.get(carts);
        if (shopCart.removeProductQuantity(prod,quantity)==1) {
            cartItems.set(carts, shopCart);
            inventory.addProductQuantity(prod, quantity);
            return true;
        }
        }
        return false;
    }

    //this method return the details of the product in inventory
    public Product getInvProductInfo(int product_id){
        if(product_id>0) {
            Product product = inventory.getProductById(product_id);
            return inventory.getProductInfo(product);
        }
        return null;
    }

    //this method return the info of a product in a given cart
    public Product getCartItemInfo(int cartId, int product_id){
        if(product_id>0) {
            Product product = inventory.getProductById(product_id);
            ShoppingCart cart = cartItems.get(cartId);
            return cart.getProductInfo(product);
        }
        return  null;
    }
}
