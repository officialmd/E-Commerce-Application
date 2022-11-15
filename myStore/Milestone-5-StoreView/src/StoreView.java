//Adam Sabbagh 101162538//
//Mohamad Deifallah 101123377//

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * The StoreView class
 */
public class StoreView {
    //Declaration of instance variables
    //jframe declaration
    private JFrame jFrame;
    //store manager object declaration
    private StoreManager storeManager;
    //to store the carts
    private int carts;
    //an array of colors to store the color
    private Color[] colors;

    //parametric constructor to get the gui and initialize the instance variables
    public StoreView(StoreManager storeManager, int carts) {
        setFrame(storeManager,carts);
    }

    //method to create and show GUI on the screen
    public void displayGUI() {
        JLabel labWel = new JLabel("Welcome to the Movie Store! (ID: " + carts + ")");
        labWel.setFont(new Font("Calibri", Font.BOLD, 25));
        JPanel panWel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panWel.add(labWel);
        ArrayList<Product> inv = storeManager.getInventoryList();
        double columns = 3.0;
        GridLayout gridLayout = new GridLayout((int) Math.ceil(inv.size() / columns), (int) columns, 5, 5);
        JPanel panProductsView = new JPanel(gridLayout);
        JScrollPane scrollProduct = new JScrollPane(panProductsView);
        scrollProduct.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollProduct.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        for (int i = 0; i < storeManager.getInventoryList().size(); i++) {
            Product prod = storeManager.getInventoryList().get(i);
            int stock = storeManager.getProductQuantity(prod);
            BorderLayout border=new BorderLayout();
            JPanel panProuduct = new JPanel(border);
            panProuduct.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), prod.getProductName()
                    + " | " + prod.getProductId()));
            panProuduct.setName(prod.getProductId() + "");
            Color color = colors[(int) (Math.random() * colors.length)];
            panProuduct.setBackground(color);
            JLabel labPrice = new JLabel("($" + prod.getProductPrice() + ") - Stock: " + stock);
            labPrice.setHorizontalAlignment(JLabel.CENTER);
            panProuduct.add(labPrice, BorderLayout.NORTH);
            JLabel labImage = new JLabel();
            labImage.setIcon(new ImageIcon("movie-icon.jpg"));
            panProuduct.add(labImage, BorderLayout.CENTER);
            setAddRemovePanel(color, panProuduct, labPrice, stock, panProductsView);
            JPanel panBottom = new JPanel();
            panBottom.setLayout(new BoxLayout(panBottom, BoxLayout.X_AXIS));
            panBottom.setPreferredSize(new Dimension(0, 135));
            JPanel panBtns = new JPanel();
            panBtns.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panBtns.setLayout(new BoxLayout(panBtns, BoxLayout.Y_AXIS));
            panBtns.setPreferredSize(new Dimension(230, 110));
            panBtns.setMaximumSize(new Dimension(230, 110));
            JButton btnViewCart = new JButton("View Cart", new ImageIcon("shopping-cart.png"));
            btnViewCart.setMaximumSize(new Dimension(130, 40));
            btnViewCart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (storeManager.getCartItems(carts).size() > 0) {
                        String str = "";
                        for (Product prod : storeManager.getCartItems(carts)) {
                            str += storeManager.getCartItemQuantity(carts, prod)
                                    + "    |    " + prod.getProductName() + prod.getProductId()
                                    + "    |    $" + prod.getProductPrice() + "\n";
                        }
                        JOptionPane.showMessageDialog(jFrame, str, "My Cart", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(jFrame, "Your cart is empty.", "My Cart",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            btnViewCart.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton btnCheckOut = new JButton("Checkout");
            btnCheckOut.setMaximumSize(new Dimension(100, 35));
            btnCheckOut.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (storeManager.getCartItems(carts).size() > 0) {
                        double totalPrice = storeManager.processTransaction(carts);

                        String str = "";
                        for (int i = 0; i < storeManager.getCartItems(carts).size(); i++) {
                            Product prod = storeManager.getCartItems(carts).get(i);
                            str += storeManager.getCartItemQuantity(carts, prod)
                                    + "    |    " + prod.getProductName() + prod.getProductId()
                                    + "    |    $" + prod.getProductPrice() + "\n";
                        }
                        str += "Total: $" + totalPrice;
                        JOptionPane.showMessageDialog(jFrame, str, "My Cart", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    } else {
                        JOptionPane.showMessageDialog(jFrame, "Your cart is empty.", "Checkout",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });
            btnCheckOut.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton btnQuit = new JButton("Quit");
            btnQuit.setMaximumSize(new Dimension(80, 35));
            btnQuit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            btnQuit.setAlignmentX(Component.CENTER_ALIGNMENT);
            panBtns.add(btnViewCart);
            panBtns.add(btnCheckOut);
            panBtns.add(btnQuit);
            panBtns.revalidate();
            panBtns.repaint();
            JPanel panSale = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    g.drawImage(new ImageIcon("sale.png").getImage(), 0, 0, null);
                }
            };
            panSale.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            panSale.revalidate();
            panSale.repaint();
            panBottom.add(panBtns);
            panBottom.add(panSale);
            jFrame.add(panWel, BorderLayout.NORTH);
            jFrame.add(scrollProduct, BorderLayout.CENTER);
            jFrame.add(panBottom, BorderLayout.SOUTH);
            jFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        StoreManager storeManager = new StoreManager();
        StoreView sv = new StoreView(storeManager, storeManager.assignNewCartID());
        sv.displayGUI();
    }

    public void setFrame(StoreManager storeManager,int carts){
        this.storeManager = storeManager;
        this.carts = carts;
        colors = new Color[]{Color.GRAY, Color.ORANGE, new Color(51, 153, 255), new Color(178, 102, 255),
                new Color(255, 201, 14), new Color(255, 55, 84), new Color(187, 95, 13),
                new Color(65, 216, 139)};
        jFrame = new JFrame("Client StoreView");
        jFrame.setBounds(350,2,800,850);
        jFrame.setLayout(new BorderLayout(5, 5));
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private void setAddRemovePanel(Color color,JPanel panProuduct,JLabel labPrice,int stock , JPanel panProductsView) {
        JPanel panAddRemove = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panAddRemove.setBackground(color);
        JButton btnRemove = new JButton("Remove");
        btnRemove.setFont(new Font("Arial", Font.BOLD, 13));
        btnRemove.setEnabled(false);
        JButton btnAdd = new JButton("Add", new ImageIcon("shopping-cart.png"));
        btnAdd.setFont(new Font("Arial", Font.BOLD, 13));
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //asks the quantity from user
                String quantityStr = JOptionPane.showInputDialog(jFrame, "Quantity");
                if (quantityStr == null) {
                    return;
                }
                int quantity = Integer.parseInt(quantityStr);
                int productId = Integer.parseInt(panProuduct.getName());
                System.out.println("product id"+productId);
                Product p = storeManager.getInvProductInfo(productId);
                if (storeManager.getCartItemInfo(carts, productId) != null) {
                    int option = JOptionPane.showConfirmDialog(jFrame,
                            "Movie is already added in the cart.\nDo you want to add it again?", "Confirm",
                            JOptionPane.YES_NO_OPTION);
                    if (option == 1) {
                        return;
                    }
                    quantity += storeManager.getCartItemQuantity(carts, p);
                    storeManager.removeCartItem(carts, p);
                }
                boolean check = storeManager.addCartItem(carts, p, quantity);
                if (!check) {
                    JOptionPane.showMessageDialog(jFrame, "Sorry, the stock is not available.");
                } else {
                    labPrice.setText("($" + p.getProductPrice() + ") - Stock: " + storeManager.getProductQuantity(p));
                    btnRemove.setEnabled(true);
                    if (stock - quantity == 0) {
                        btnAdd.setEnabled(false);
                    }
                    panProuduct.revalidate();
                    panProuduct.repaint();
                }
            }
        });
        panAddRemove.add(btnAdd);

        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(jFrame,
                        "Do you want to remove the movie from cart?", "Confirm",
                        JOptionPane.YES_NO_OPTION);
                if (option == 1) {
                    return;
                }
                int productId = Integer.parseInt(panProuduct.getName());
                Product p = storeManager.getCartItemInfo(carts, productId);
                storeManager.removeCartItem(carts, p);
                labPrice.setText("($" + p.getProductPrice() + ") - Stock: " + storeManager.getProductQuantity(p));
                btnAdd.setEnabled(true);
                btnRemove.setEnabled(false);
                panProuduct.revalidate();
                panProuduct.repaint();
            }
        });
        panAddRemove.add(btnRemove);
        panProuduct.add(panAddRemove, BorderLayout.SOUTH);
        panProductsView.add(panProuduct);
    }

}
