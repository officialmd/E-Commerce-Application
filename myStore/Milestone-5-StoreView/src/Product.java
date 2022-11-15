//Adam Sabbagh 101162538//
//Mohamad Deifallah 101123377//

public class Product {

    //instance variable decleration
    private String productName;
    private int productId;
    private double productPrice;


    //by default constructor to intialize the constructor to assign by default values to the
    //instance variable
    public Product(){
        productName="";
        productId=0;
        productPrice=0.0;
    }

    //parametric constructor to make the object of the product
    public Product(String productName, int productId, double productPrice) {
        this.productName = productName;
        this.productId = productId;
        this.productPrice = productPrice;
    }

    //all instance variable setters and getters
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }
}
