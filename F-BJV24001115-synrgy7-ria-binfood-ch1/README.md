# Challenge-1: BinarFud

This challenge involves creating a system that can display order details including price, order quantity, and order variations.

## Task Description

1. **Main Menu**: Create a main menu for choosing food, ordering, and exiting the application. The menu should display the food items along with their prices and options to order, pay, or exit the application.

2. **Order Confirmation**: Create an order confirmation menu along with the order quantity. For example, if the user inputs 1 to order Nasi Goreng, a display will appear asking the user to enter the quantity of Nasi Goreng orders.

3. **Confirmation & Payment**: After the user has finished choosing the order, the user will be directed to the Confirmation and Payment screen, and the user's order details will be shown. Here the user can confirm the order and pay or return to the main menu until exiting the application.

4. **Payment Receipt**: Finally, the user can see the payment receipt which is also saved as a .txt file. The receipt should include the order details and the total amount paid.

## Commands

Here are the commands and expected interfaces for each step of the challenge:

1. **Main Menu**:

```text
==========================
Selamat datang di BinarFud
==========================

Silahkan pilih makanan :
1. Nasi Goreng  | 15.000
2. Mie Goreng   | 13.000
3. Nasi + Ayam  | 18.000
4. Es Teh Manis | 3.000
5. Es Jeruk     | 5.000
99. Pesan dan Bayar
0. Keluar Aplikasi

=>
```

2. **Order Confirmation**:

```text
==========================
Berapa pesanan anda
==========================

Nasi Goreng     | 15.000
(input 0 untuk kembali)

qty =>

```

3. **Confirmation & Payment**:

```text
==========================
Konfirmasi & Pembayaran
==========================

Nasi Goreng     2       30.000
Mie Goreng      1       13.000
Nasi + Ayam     1       18.000
Es Jeruk        4       20.000
-------------------------------+
Total           8       81.000

1. Konfirmasi dan Bayar
2. Kembali ke menu utama
3. Keluar aplikasi

=>
```

4. **Payment Receipt**:

```text
==========================
BinarFud
==========================

Terima kasih sudah memesan
di BinarFud

Di bawah ini adalah pesanan anda

Nasi Goreng      2       30.000
Mie Goreng       1       13.000
Nasi + Ayam      1       18.000
Es Jeruk         4       20.000
--------------------------------+
Total            8       81.000

Pembayaran : BinarCash

==========================
Simpan struk ini sebagai
bukti pembayaran
==========================
```

## How to Run

To run this challenge, you need to execute the main program file. Instructions on how to do this will depend on the programming language used.

## Contributions

If you find any mistakes or want to add something, please create an issue or pull request.

## License

[MIT](https://choosealicense.com/licenses/mit/)
