package week06.milano.id.ac.umn;

class Handphone extends Barang {
    private String warna;
    public static int total;

    public Handphone(int id, String nama, double harga, int stok, String warna) {
        super(id, nama, harga);
        this.stok = stok;
        this.warna = warna;
        total++;
    }

    public String getWarna() { return warna; }
}
