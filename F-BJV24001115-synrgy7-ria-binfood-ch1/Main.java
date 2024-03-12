
// Importing necessary libraries
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

// Main class definition
public class Main {

  // Scanner object for user input
  static final Scanner scanner = new Scanner(System.in);

  // Constant arrays for menu items and their prices
  static final String[] MENU_ITEMS = { "Nasi Goreng", "Mie Goreng", "Nasi + Ayam", "Es Teh Manis", "Es Jeruk" };
  static final int[] MENU_PRICES = { 15000, 13000, 18000, 3000, 5000 };

  // Array to store quantity of each menu item
  static final int[] quantity = new int[MENU_ITEMS.length];

  // Variable to store total payment
  static int totalPayment;

  // Main method
  public static void main(String[] args) {
    // Display the menu
    displayMenu();
    // Close the scanner
    scanner.close();
  }

  // Method to display the menu
  static void displayMenu() {
    // Print separator
    printSeparator();
    // Welcome message
    System.out.println("Selamat datang di BinarFud");
    // Print separator
    printSeparator();
    // Print menu items and prices
    System.out.println("\nSilahkan pilih makanan :");
    DecimalFormat formatter = new DecimalFormat("#,###");
    for (int i = 0; i < MENU_ITEMS.length; i++) {
      String formattedPrice = formatter.format(MENU_PRICES[i]).replace(",", ".");
      System.out.format("%-15s %1s %7s", (i + 1) + ". " + MENU_ITEMS[i], "|", formattedPrice + "\n");
    }
    // Print options
    System.out.println("99. Pesan dan Bayar");
    System.out.println("0. Keluar Aplikasi");
    System.out.print("\n=> ");
    // Select menu
    selectMenu();
  }

  // Method to print separator
  static void printSeparator() {
    System.out.println("==========================");
  }

  // Method to select menu
  static void selectMenu() {
    try {
      int menuID = scanner.nextInt();
      if (menuID > 0 && menuID <= MENU_ITEMS.length) {
        confirmOrder(menuID);
      } else if (menuID == 99) {
        confirmPayment();
      } else if (menuID == 0) {
        System.exit(0);
      } else {
        System.out.print("Input pesanan tidak valid. \nSilahkan masukkan kembali pesanan anda:");
        selectMenu();
      }
    } catch (java.util.InputMismatchException e) {
      System.out.print("Input tidak valid. \nSilahkan masukkan kembali pesanan anda:");
      scanner.next(); // Clear scanner buffer
      selectMenu();
    }
  }

  // Method to confirm order
  static void confirmOrder(int menuID) {
    printSeparator();
    System.out.println("Berapa pesanan anda");
    printSeparator();
    DecimalFormat formatter = new DecimalFormat("#,###");
    String formattedPrice = formatter.format(MENU_PRICES[menuID - 1]).replace(",", ".");
    System.out.format("%-15s %1s %7s", MENU_ITEMS[menuID - 1], "|", formattedPrice + "\n");
    System.out.println("(Input 0 untuk kembali)");
    orderQuantity(menuID);
  }

  // Method to input order quantity
  static void orderQuantity(int menuID) {
    System.out.print("\nqty => ");
    if (scanner.hasNextInt()) {
      int qty = scanner.nextInt();
      if (qty == 0) {
        displayMenu();
      } else if (qty > 0) {
        quantity[menuID - 1] += qty;
        System.out.println("Pesan Menu Lain: Y/T");
        System.out.print("\n=> ");
        char confirm = scanner.next().charAt(0);
        if (confirm == 'Y' || confirm == 'y') {
          displayMenu();
        } else if (confirm == 'T' || confirm == 't') {
          confirmPayment();
        } else {
          System.out.println("Input tidak valid. Silakan pilih Y atau T.");
          orderQuantity(menuID);
        }
      } else {
        System.out.print("Input tidak valid. \nSilahkan masukkan kembali jumlah pesanan anda:");
        orderQuantity(menuID);
      }
    } else {
      System.out.print("Input tidak valid. \nSilahkan masukkan kembali jumlah pesanan anda:");
      scanner.next(); // Clear scanner buffer
      orderQuantity(menuID);
    }
  }

  // Method to confirm payment
  static void confirmPayment() {
    try {
      printSeparator();
      System.out.println("Konfirmasi $ Pembayaran");
      printSeparator();
      totalPayment = 0;
      DecimalFormat formatter = new DecimalFormat("#,###");
      for (int i = 0; i < MENU_ITEMS.length; i++) {
        if (quantity[i] != 0) {
          String formattedPrice = formatter.format(quantity[i] * MENU_PRICES[i]).replace(",", ".");
          System.out.format("%-13s %3s %12s", MENU_ITEMS[i], quantity[i], formattedPrice + "\n");
          totalPayment += quantity[i] * MENU_PRICES[i];
        }
      }
      System.out.println("--------------------------------+");
      String formattedPrice = formatter.format(totalPayment).replace(",", ".");
      System.out.format("%-13s %3s %12s", "Total", Arrays.stream(quantity).sum(), formattedPrice + "\n");

      System.out.println("1. Konfirmasi dan Bayar");
      System.out.println("2. Kembali ke menu utama");
      System.out.println("0. Keluar aplikasi\n");

      // Input from user
      System.out.print("=> ");
      int input = scanner.nextInt();
      processPayment(input);
    } catch (java.util.InputMismatchException e) {
      System.out.println("Input tidak valid. Silakan pilih nomor pilihan yang tersedia.");
      scanner.next(); // Clear scanner buffer
      confirmPayment(); // Show menu and ask for correct input
    }
  }

  // Method to process payment
  static void processPayment(int input) {
    switch (input) {
      case 1:
        generateReceipt();
        break;
      case 2:
        displayMenu();
        break;
      case 0:
        System.exit(0);
      default:
        System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
        confirmPayment(); // Show menu and ask for correct input
        break;
    }
  }

  // Method to generate receipt
  static void generateReceipt() {
    try (BufferedWriter buffer = new BufferedWriter(new FileWriter("Bill.txt"))) {
      buffer.write("==========================\nBinarFud\n==========================\n");
      buffer.newLine();
      buffer.write("Terima kasih sudah memesan \ndi BinarFud");
      buffer.newLine();
      buffer.write("\nDibawah ini adalah pesanan anda\n");
      buffer.newLine();
      DecimalFormat formatter = new DecimalFormat("#,###");
      for (int i = 0; i < MENU_ITEMS.length; i++) {
        if (quantity[i] != 0) {
          String formattedPrice = formatter.format(quantity[i] * MENU_PRICES[i]).replace(",", ".");
          buffer.write(MENU_ITEMS[i] + "\t" + quantity[i] + "\t" + formattedPrice);
          buffer.newLine();
        }
      }
      buffer.write("-------------------------------+");
      buffer.newLine();
      String formattedPrice = formatter.format(totalPayment).replace(",", ".");
      buffer.write("Total\t\t" + Arrays.stream(quantity).sum() + "\t" + formattedPrice);
      buffer.newLine();
      buffer.write("\nPembayaran : BinarCash\n");
      buffer.newLine();
      buffer
          .write("==========================\nSimpan struk ini sebagai\nbukti pembayaran\n==========================");
      buffer.newLine();
      System.out.println("Struk telah tersimpan dalam file Bill.txt");
    } catch (IOException e) {
      System.out.println("Error saat menulis file: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
