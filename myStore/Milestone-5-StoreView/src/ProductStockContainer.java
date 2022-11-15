//Adam Sabbagh 101162538//
//Mohamad Deifallah 101123377//

  public abstract class ProductStockContainer {
    //this method will get the quantity of product that is given in parameter
    public abstract int getProductQuantity(Product product);
    //this method will add the quantity of product that is given in and quantity of the product
    public abstract void addProductQuantity(Product product, int quantity);
      //this method will update the quantity of product that is given in and quantity of the product
    public abstract int removeProductQuantity(Product product, int quantity);
    //this method will give information of the given product
    public abstract Product getProductInfo(Product product);
    //this method return the product by given id
    public abstract  Product getProductById(int productId);
    //this method return the size of the product list
    public abstract int getNumOfProducts();



  }
