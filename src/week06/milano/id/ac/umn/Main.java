package week06.milano.id.ac.umn;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Barang[] daftarBarang = new Barang[100];
        Order[] daftarPesanan = new Order[100];
        int jumlahPesanan = 0;
        int jumlahBarang = 0;

        int pilih;
        do {
            System.out.println("-------------Menu Toko Voucher & HP-------------");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("3. Barang Baru");
            System.out.println("0. Keluar");
            System.out.print("Pilihan : ");
            pilih = sc.nextInt();

            switch (pilih) {
                case 1:
                    if (jumlahBarang == 0) {
                        System.out.println("Barang tidak tersedia\n");
                        break;
                    }

                    System.out.println("Daftar Barang Toko Voucher & HP");
                    for (int i = 0; i < jumlahBarang; i++) {
                        Barang b = daftarBarang[i];
                        if (b instanceof Handphone) {
                            Handphone hp = (Handphone) b;
                            System.out.println("Handphone - ID: " + b.getId() +
                                    ", Nama: " + b.getNama() +
                                    ", Harga: " + b.getHarga() +
                                    ", Stok: " + b.getStok() +
                                    ", Warna: " + hp.getWarna());
                        } else if (b instanceof Voucher) {
                            Voucher v = (Voucher) b;
                            System.out.println("Voucher - ID: " + b.getId() +
                                    ", Nama: " + b.getNama() +
                                    ", Harga Jual: " + v.getHargaJual() +
                                    ", Stok: " + b.getStok() +
                                    ", Pajak: " + v.getPajak());
                        }
                    }

                    System.out.print("Ketik 0 untuk batal\nPesan Barang (ID) : ");
                    int id = sc.nextInt();
                    if (id == 0) break;

                    Barang brg = null;
                    for (int i = 0; i < jumlahBarang; i++) {
                        if (daftarBarang[i].getId() == id) {
                            brg = daftarBarang[i];
                            break;
                        }
                    }

                    if (brg == null) {
                        System.out.println("Pilihan barang tidak sesuai");
                        break;
                    }

                    System.out.print("Masukkan jumlah: ");
                    int jumlah = sc.nextInt();
                    if (jumlah > brg.getStok()) {
                        System.out.println("Stok tidak mencukupi");
                        break;
                    }

                    String orderId = "ORD-" + brg.getId();

                    double total;
                    if (brg instanceof Voucher) {
                        Voucher v = (Voucher) brg;
                        total = v.getHargaJual() * jumlah;
                        daftarPesanan[jumlahPesanan++] = new Order(orderId, v, jumlah);
                    } else {
                        Handphone hp = (Handphone) brg;
                        total = hp.getHarga() * jumlah;
                        daftarPesanan[jumlahPesanan++] = new Order(orderId, hp, jumlah);
                    }

                    System.out.println("Total harga: " + total);
                    System.out.print("Masukkan uang: ");
                    double uang = sc.nextDouble();

                    if (uang < total) {
                        System.out.println("Jumlah uang tidak mencukupi");
                        break;
                    }

                    brg.minusStok(jumlah);
                    System.out.println("Pesanan berhasil disimpan!\n");
                    break;

                case 2:
                    System.out.println("========= Daftar Pesanan =========");
                    if (jumlahPesanan == 0) {
                        System.out.println("Belum ada pesanan");
                    } else {
                        for (int i = 0; i < jumlahPesanan; i++) {
                            Order o = daftarPesanan[i];
                            System.out.println("Order ID: " + o.getId() + ", Jumlah: " + o.getJumlah());
                            if (o.getVoucher() != null) {
                                Voucher v = o.getVoucher();
                                System.out.println("Barang: Voucher " + v.getNama() + " Harga Jual: " + v.getHargaJual());
                            } else if (o.getHandphone() != null) {
                                Handphone h = o.getHandphone();
                                System.out.println("Barang: Handphone " + h.getNama() + " Harga: " + h.getHarga());
                            }
                            System.out.println("-------------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Voucher / Handphone (V/H): ");
                    char jenisBrg = sc.next().toLowerCase().charAt(0);
                    System.out.print("ID : ");
                    int newId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nama : ");
                    String nama = sc.nextLine();
                    System.out.print("Harga : ");
                    double harga = sc.nextDouble();
                    System.out.print("Stok : ");
                    int stok = sc.nextInt();

                    if (jenisBrg == 'v') {
                        System.out.print("Pajak (misal 0.1 untuk 10%): ");
                        double pajak = sc.nextDouble();
                        daftarBarang[jumlahBarang++] = new Voucher(newId, nama, harga, stok, pajak);
                        System.out.println("Voucher telah berhasil diinput\n");
                    } else if (jenisBrg == 'h') {
                        sc.nextLine();
                        System.out.print("Warna : ");
                        String warna = sc.nextLine();
                        daftarBarang[jumlahBarang++] = new Handphone(newId, nama, harga, stok, warna);
                        System.out.println("Handphone telah berhasil diinput\n");
                    } else {
                        System.out.println("Pilihan salah");
                    }
                    break;

                case 0:
                    System.out.println("Keluar dari program...");
                    break;

                default:
                    System.out.println("Pilihan tidak ada");
            }
        } while (pilih != 0);

        sc.close();
    }
}

