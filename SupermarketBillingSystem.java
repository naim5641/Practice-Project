import java.util.*;

class Product {
    int id;
    String name;
    double price;
    int quantity;

    Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

class SupermarketBillingSystem {
    private Map<Integer, Product> productCatalog = new HashMap<>();
    private List<Product> cart = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void addProductToCatalog() {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        Product product = new Product(id, name, price, quantity);
        productCatalog.put(id, product);
        System.out.println("Product added successfully!\n");
    }

    public void addToCart() {
        System.out.print("Enter product ID to add to cart: ");
        int id = scanner.nextInt();
        if (productCatalog.containsKey(id)) {
            Product product = productCatalog.get(id);
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            if (quantity <= product.quantity) {
                product.quantity -= quantity;
                cart.add(new Product(product.id, product.name, product.price, quantity));
                System.out.println("Product added to cart!\n");
            } else {
                System.out.println("Insufficient stock!\n");
            }
        } else {
            System.out.println("Product not found!\n");
        }
    }

    public void generateBill() {
        double totalAmount = 0;
        System.out.println("\n--- BILL ---");
        for (Product product : cart) {
            double amount = product.price * product.quantity;
            totalAmount += amount;
            System.out.println(product.name + " - " + product.quantity + " @ " + product.price + " each = " + amount);
        }
        System.out.println("Total Amount: " + totalAmount + "\n");
        cart.clear();
    }

    public void displayProducts() {
        System.out.println("\n--- PRODUCT CATALOG ---");
        for (Product product : productCatalog.values()) {
            System.out.println("ID: " + product.id + ", Name: " + product.name + ", Price: " + product.price + ", Quantity: " + product.quantity);
        }
        System.out.println();
    }

    public void run() {
        while (true) {
            System.out.println("1. Add Product to Catalog");
            System.out.println("2. Display Products");
            System.out.println("3. Add to Cart");
            System.out.println("4. Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addProductToCatalog();
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    addToCart();
                    break;
                case 4:
                    generateBill();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }

    public static void main(String[] args) {
        SupermarketBillingSystem system = new SupermarketBillingSystem();
        system.run();
    }
}
