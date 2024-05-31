public enum CustomerState {
   Invalid, // Restorana girmeyi bekliyor (kullanılmadı)
   WaitingToSit, // Boş masa bekliyor
   WaitingToOrder, // Sipariş alınmasını bekliyor
   Ordering, // Sipariş alınıyor
   WaitingToEat, // Yemeğini bekliyor
   Eating, // Yiyor (ama çalışıyor)
   WaitingToPay, // Ödemeyi bekliyor
   Paying, // Ödüyor
   Exit // Çıkış yapıyor
}
