import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===================================================================");
        System.out.println("TEKIRDAG SPOR REZERVASYON SISTEMI - VERI YAPILARI ENTEGRASYONU");
        System.out.println("===================================================================");
        
        // 1. CSV'den Rezervasyonları Oku
        System.out.println("\n📂 CSV dosyasından rezervasyon kayıtları okunuyor...");
        ArrayList<Rezervasyon> tumRezervasyonlar = CSVReader.rezervasyonlariOku();
        System.out.println("   Topla Okunan Rezervasyon Sayısı: " + tumRezervasyonlar.size());
        
        // 2. Bekleme Kuyruğu (Queue) Oluştur ve 'Beklemede' olanları ekle
        System.out.println("\n👥 Bekleme Kuyruğu (FIFO Queue) oluşturuluyor...");
        BeklemeKuyrugu<Rezervasyon> beklemeKuyrugu = new BeklemeKuyrugu<>();
        
        int beklemeSayisi = 0;
        for (Rezervasyon r : tumRezervasyonlar) {
            if ("Beklemede".equals(r.getDurum())) {
                beklemeKuyrugu.enqueue(r);
                beklemeSayisi++;
            }
        }
        System.out.println("   Kuyruğa Alınan Bekleyen Sayısı: " + beklemeSayisi);
        
        // 3. Kuyruğu Listele
        System.out.println("\n📋 GÜNCEL BEKLEME KUYRUĞU LİSTESİ (İlk Gelen İlk Çıkar - FIFO):");
        System.out.println("-------------------------------------------------------------------");
        beklemeKuyrugu.listele();
        System.out.println("-------------------------------------------------------------------");
        
        // 4. Kuyruktan Eleman Çıkar (Sıradakini Al Simülasyonu)
        if (!beklemeKuyrugu.isEmpty()) {
            System.out.println("\n🔄 Simülasyon: Sıradaki kullanıcı kuyruktan alınıyor ve onaylanıyor...");
            Rezervasyon siradaki = beklemeKuyrugu.dequeue();
            System.out.println("   [ONAYLANDI] " + siradaki);
            
            System.out.println("\n📋 İŞLEM SONRASI YENİ BEKLEME KUYRUĞU LİSTESİ:");
            System.out.println("-------------------------------------------------------------------");
            beklemeKuyrugu.listele();
            System.out.println("-------------------------------------------------------------------");
        } else {
            System.out.println("\nℹ️ Şu anda bekleme kuyruğunda bekleyen rezervasyon bulunmamaktadır.");
        }
    }
}