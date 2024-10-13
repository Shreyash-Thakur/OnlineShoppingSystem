import java.util.*;

class Product {
    private String productId;
    private String name;
    private double price;

    public Product(String productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product ID: " + productId + ", Name: " + name + ", Price: $" + price;
    }
}

class Admin {

    private static int productCounter = 1;
    public String login_id = "12345";
    public String password = "ILoveOOPM";

    public void addProduct(ArrayList<Product> products, String name, double price) {
        String productId = "P" + String.format("%03d", productCounter);
        productCounter++;
        Product product = new Product(productId, name, price);
        products.add(product);
        System.out.println("Product added: " + product);
    }

    public void removeProduct(ArrayList<Product> products, String productId) {
        Product productToRemove = null;
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            products.remove(productToRemove);
            System.out.println("Product removed: " + productToRemove);
        } else {
            System.out.println("Product with ID " + productId + " not found.");
        }
    }
}

class Guest {
    private String name;
    private List<Product> cart = new ArrayList<>();

    public Guest() {
        this.name = "Guest";
    }

    public List<Product> getCart() {
        return cart;
    }

    public void addToCart(ArrayList<Product> products, String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                cart.add(product);
                System.out.println("Added to cart: " + product);
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
    }

    public void removeFromCart(String productId) {
        Product productToRemove = null;
        for (Product product : cart) {
            if (product.getProductId().equals(productId)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            cart.remove(productToRemove);
            System.out.println("Removed from cart: " + productToRemove);
        } else {
            System.out.println("Product with the ID " + productId + " not found in your cart.");
        }
    }

    public void viewCart() {
        System.out.println("Shopping Cart for " + name + ":");
        for (Product product : cart) {
            System.out.println(product);
        }
    }

    public void checkout(Payment payment,String name) {
        this.name  = name;
        double total = 0;
        for (Product product : cart) {
            total += product.getPrice();
        }
        System.out.println("Total: $" + total);
        payment.processPayment(total);
        System.out.println("Checkout complete for " + name + ". Thank you for shopping!");
        cart.clear();
    }

    public void viewProducts(ArrayList<Product> products) {
        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}

class Payment {

    public void processPayment(double amount) {
        System.out.println("Payment processed: $" + amount);
    }
}

class Customer {

    private String name;
    private List<Product> cart = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public List<Product> getCart() {
        return cart;
    }

    public void addToCart(ArrayList<Product> products, String productId) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                cart.add(product);
                System.out.println("Added to cart: " + product);
                return;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
    }

    public void removeFromCart(String productId) {
        Product productToRemove = null;
        for (Product product : cart) {
            if (product.getProductId().equals(productId)) {
                productToRemove = product;
                break;
            }
        }
        if (productToRemove != null) {
            cart.remove(productToRemove);
            System.out.println("Removed from cart: " + productToRemove);
        } else {
            System.out.println("Product with ID " + productId + " not found in your cart.");
        }
    }

    public void viewCart() {
        System.out.println("Shopping Cart for " + name + ":");
        for (Product product : cart) {
            System.out.println(product);
        }
    }

    public void checkout(Payment payment) {
        double total = 0;
        for (Product product : cart) {
            total += product.getPrice();
        }
        System.out.println("Total: $" + total);
        payment.processPayment(total);
        System.out.println("Checkout complete for " + name + ". Thank you for shopping!");
        cart.clear();
    }
}

public class OnlineShoppingSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        Admin admin = new Admin();
        Guest guest = new Guest();
        Customer customer = null;
        Payment payment = new Payment();

        int choice;
        do {
            System.out.println("\n** Online Shopping System **");
            System.out.println("1. Admin Login");
            System.out.println("2. Guest");
            System.out.println("3. Customer");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    sc.nextLine();
                    System.out.println("Enter the login id");
                    String login_id = sc.nextLine();
                    System.out.println("Enter the password");
                    String password = sc.nextLine();
                    if (!(login_id.compareToIgnoreCase(admin.login_id)==0 && password.compareToIgnoreCase(admin.password)==0)) {
                        System.out.println("Invalid Credentials");
                        break;
                    }
                    System.out.println("Admin Panel");
                    System.out.print("1. Add product\n2. Remove product\nEnter choice: ");
                    int adminChoice = sc.nextInt();
                    sc.nextLine();
                    switch (adminChoice) {
                        case 1 -> {
                            System.out.print("Enter product name: ");
                            String name = sc.nextLine();
                            System.out.print("Enter product price: ");
                            double price = sc.nextDouble();
                            admin.addProduct(products, name, price);
                        }
                        case 2 -> {
                            System.out.print("Enter product ID to remove: ");
                            String productId = sc.next();
                            admin.removeProduct(products, productId);
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                }

                case 2 -> {
                    System.out.println("Welcome Guest");
                    Guest gst= new Guest();
                    int guestChoice;
                    do {
                        System.out.println("Guest Panel");
                        System.out.println("1. Add to Cart");
                        System.out.println("2. Remove from Cart");
                        System.out.println("3. View Cart");
                        System.out.println("4. Checkout");
                        System.out.println("5. Exit");
                        System.out.print("Enter your choice: ");
                        guestChoice = sc.nextInt();

                        switch (guestChoice) {
                            case 1 -> {
                                guest.viewProducts(products);
                                System.out.print("Enter product ID to add to cart: ");
                                String addProductId = sc.next();
                                guest.addToCart(products, addProductId);
                            }
                            case 2 -> {
                                System.out.print("Enter product ID to remove from cart: ");
                                String removeProductId = sc.next();
                                guest.removeFromCart(removeProductId);
                            }
                            case 3 -> guest.viewCart();
                            case 4 -> {
                                sc.nextLine();
                                System.out.println("Enter name to checkout");
                                String name = sc.nextLine();
                                guest.checkout(payment,name);
                                guestChoice = 5;
                                System.out.println("Exiting Guest Panel.");
                            }
                            case 5 -> System.out.println("Exiting Guest Panel.");
                            default -> System.out.println("Invalid choice. Please try again.");
                        }
                    } while (guestChoice != 5);
                }

                case 3 -> {
                    System.out.print("Enter your name: ");
                    sc.nextLine();
                    String customerName = sc.nextLine();
                    System.out.println("Welcome "+customerName);
                    customer = new Customer(customerName);
                    int customerChoice;
                    do {
                        System.out.println("Customer Panel");
                        System.out.println("1. Add to Cart");
                        System.out.println("2. Remove from Cart");
                        System.out.println("3. View Cart");
                        System.out.println("4. Checkout");
                        System.out.println("5. Exit");
                        System.out.print("Enter your choice: ");
                        customerChoice = sc.nextInt();

                        switch (customerChoice) {
                            case 1 -> {
                                guest.viewProducts(products);
                                System.out.print("Enter product ID to add to cart: ");
                                String addProductId = sc.next();
                                customer.addToCart(products, addProductId);
                            }
                            case 2 -> {
                                System.out.print("Enter product ID to remove from cart: ");
                                String removeProductId = sc.next();
                                customer.removeFromCart(removeProductId);
                            }
                            case 3 -> customer.viewCart();
                            case 4 -> {
                                customer.checkout(payment);
                                customerChoice = 5;
                                System.out.println("Exiting Customer Panel.");
                            }
                            case 5 -> System.out.println("Exiting Customer Panel.");
                            default -> System.out.println("Invalid choice. Please try again.");
                        }
                    } while (customerChoice != 5);
                }

                case 4 -> System.out.println("Exiting the system.");

                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }
}