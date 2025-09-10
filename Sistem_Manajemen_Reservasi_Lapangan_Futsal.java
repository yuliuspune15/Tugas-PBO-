package com.mycompany.sistem_manajemen_reservasi_lapangan_futsal;

import java.util.ArrayList;
import java.util.Scanner;

class Reservasi {
    String nama;
    String tanggal;
    String jam;
    int lapangan; // lapangan 1-4

    public Reservasi(String nama, String tanggal, String jam, int lapangan) {
        this.nama = nama;
        this.tanggal = tanggal;
        this.jam = jam;
        this.lapangan = lapangan;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + 
               ", Tanggal: " + tanggal + 
               ", Jam: " + jam + 
               ", Lapangan: " + lapangan;
    }
}

public class Sistem_Manajemen_Reservasi_Lapangan_Futsal {
    private static ArrayList<Reservasi> daftarReservasi = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("\n=== Sistem Manajemen Reservasi Lapangan Futsal ===");
            System.out.println("1. Tambah Reservasi");
            System.out.println("2. Lihat Reservasi");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    tambahReservasi();
                    break;
                case 2:
                    lihatReservasi();
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan sistem!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 3);
    }

    private static void tambahReservasi() {
        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan tanggal (contoh: 2025-09-09): ");
        String tanggal = scanner.nextLine();
        System.out.print("Masukkan jam (contoh: 19:00): ");
        String jam = scanner.nextLine();
        System.out.print("Pilih Lapangan (1/2/3/4): ");
        int lapangan = scanner.nextInt();
        scanner.nextLine();

        // cek apakah lapangan sudah dipesan
        if (cekBentrok(tanggal, jam, lapangan)) {
            System.out.println("❌ Lapangan " + lapangan + " sudah dipesan pada tanggal " + tanggal + " jam " + jam);
        } else {
            Reservasi reservasi = new Reservasi(nama, tanggal, jam, lapangan);
            daftarReservasi.add(reservasi);
            System.out.println("✅ Reservasi berhasil ditambahkan!");
        }
    }

    private static void lihatReservasi() {
        if (daftarReservasi.isEmpty()) {
            System.out.println("Belum ada reservasi.");
        } else {
            System.out.println("\n=== Daftar Reservasi ===");
            for (int i = 0; i < daftarReservasi.size(); i++) {
                System.out.println((i + 1) + ". " + daftarReservasi.get(i));
            }
        }
    }

    // fungsi cek bentrok
    private static boolean cekBentrok(String tanggal, String jam, int lapangan) {
        for (Reservasi r : daftarReservasi) {
            if (r.tanggal.equals(tanggal) && r.jam.equals(jam) && r.lapangan == lapangan) {
                return true; // bentrok
            }
        }
        return false; // tidak bentrok
    }
}
