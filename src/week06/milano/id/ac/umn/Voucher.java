package week06.milano.id.ac.umn;

class Voucher extends Barang {
    private double pajak;
    public static int total;

    public Voucher(int id, String nama, double harga, int stok, double pajak) {
        super(id, nama, harga);
        this.stok = stok;
        this.pajak = pajak;
        total++;
    }

    public double getPajak() { return pajak; }

    public double getHargaJual() {
        return harga + (harga * pajak);
    }
}
