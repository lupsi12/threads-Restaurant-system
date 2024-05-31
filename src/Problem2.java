import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Problem2
{
    int chiefPrice = 1;
    int waiterPrice = 1;
    int tablePrice = 1;
    int chiefTime = 3;
    int waiterTime = 2;
    int customerTime = 3;
    int cashTime = 1;

    public void StartProblem2() throws InterruptedException
    {
        ANSICodes.ClearScreen();

        //System.out.println(ANSICodes.colorRed + "Daha Problem 2'yi implement etmedik ki la LJSFDLNASDLFN" + ANSICodes.colorReset);
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nSimülasyon süresi (sn): ");
        int sure;
        sure = scanner.nextInt();
        System.out.print("Müşteri gelme aralığı (sn): ");
        int aralik ;
        aralik = scanner.nextInt();
        System.out.print("Tek seferde gelen toplam müşteri sayısı: ");
        int musteri ;
        musteri = scanner.nextInt();
        System.out.print("Tek seferde gelen öncelikli müşteri sayısı: ");
        int oncelikliMusteri ;
        oncelikliMusteri = scanner.nextInt();
        //System.out.println(sure+" "+aralik+" "+musteri+" "+oncelikliMusteri);
        System.out.println();

        LocalDateTime history = LocalDateTime.now();
        DateTimeFormatter formationLap = DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss");
        Logger.SetFileName("Problem2 - " + history.format(formationLap) + ".log");

        Logger.WriteLog("Simülasyon süresi: " + sure + " sn", ANSICodes.colorYellow);
        Logger.WriteLog("Müşteri gelme aralığı: " + aralik + " sn", ANSICodes.colorCyan);
        Logger.WriteLog("Tek seferde gelen toplam müşteri sayısı: " + musteri, ANSICodes.colorGreen);
        Logger.WriteLog("Tek seferde gelen önemli müşteri sayısı: " + musteri, ANSICodes.colorRed);
        Logger.WriteLog("");

        int tahminiSayi = sure/aralik*musteri;
        int musteriTahmini = tahminiSayi;
        if(tahminiSayi>4){
        tahminiSayi = tahminiSayi/4;}

        tahminiSayi=15;

        ArrayList<Problem2_Hesaplama> problem2Hesaplamas = new ArrayList<>();
        ArrayList<Problem2_Customer> problem2Customers = new ArrayList<>();
        ArrayList<Problem2_Table> problem2Tables = new ArrayList<>();
        ArrayList<Problem2_Chief> problem2Chiefs = new ArrayList<>();
        ArrayList<Problem2_Waiter> problem2Waiters = new ArrayList<>();
        ArrayList<Integer> vip = new ArrayList<>();
        ArrayList<Problem2_TakenMeal> tampon = new ArrayList<>();

        for (int t = 1; t < tahminiSayi*3; t++) {
            for (int j = 1; j < tahminiSayi; j++) {
                for (int k = 2; k < tahminiSayi; k=k+2) {
                    //System.out.println(vip.size() +" vip ve customer sayısı "+customers.size());
                    //t masa sayısı
                    //j garson sayısı
                    //k chef sayısı
                    if(musteriTahmini>=t+j+k){
                        problem2Hesaplamas.add(new Problem2_Hesaplama(0,j,k,t));
                        //System.out.println("-***************************************** yeni hesaplamalar yapılıyorr -*****************************************");
                        //if(hesaplamas.get(hesaplamas.size()-1).getTableN()>=5);
                        //System.out.println(" table "+hesaplamas.get(hesaplamas.size()-1).getTableN()+" waiter "+hesaplamas.get(hesaplamas.size()-1).getWaiterN()+" chief "+hesaplamas.get(hesaplamas.size()-1).getChiefN()+" customer "+hesaplamas.get(hesaplamas.size()-1).getCustomerN());

                        for (int i = 0; i < t; i++) {
                            problem2Tables.add(new Problem2_Table(i,false));
                        }
                        for (int i = 0; i < j; i++) {
                            problem2Waiters.add(new Problem2_Waiter("waiter"+i,0,false,0,false));
                        }
                        for (int i = 0; i < k; i++) {
                            problem2Chiefs.add(new Problem2_Chief("chief"+i,0,false,0,false));
                        }
                        //System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwww "+tables.size()+" masa "+waiters.size()+" garson "+chiefs.size()+" sef ");
                        for (int l = 0; l < sure; l++) {
                            //System.out.println(l+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            if(problem2Customers.size()==0 || l%aralik==0){
                                for (int i = 0; i < oncelikliMusteri; i++) {
                                    problem2Customers.add(new Problem2_Customer("customer"+ problem2Customers.size(),"vip","waitingToSit",0));
                                    vip.add(problem2Customers.size()-1);
                                }
                                for (int i = 0; i < musteri-oncelikliMusteri; i++) {
                                    problem2Customers.add(new Problem2_Customer("customer"+ problem2Customers.size(),"normal","waitingToSit",0));
                                }
                            }
                            for (int b = 0; b < problem2Tables.size(); b++) {
                                if(problem2Tables.get(b).isTable()==false){
                                    for (int i = 0; i < vip.size(); i++) {
                                        if(problem2Customers.get(vip.get(i)).getCustomerState().equals("waitingToSit") && problem2Customers.get(vip.get(i)).getCustomerType().equals("vip") && problem2Tables.get(b).isTable()==false){
                                            problem2Customers.get(vip.get(i)).setCustomerState("waitingToOrder");
                                            problem2Tables.get(b).setTable(true);
                                            //hesaplamas.get(hesaplamas.size()-1).setCustomerN(hesaplamas.get(hesaplamas.size()-1).getCustomerN()+1);
                                            //System.out.println("içeri alınıyo vip pppppppppppppppppppppppppppppppppppppp");
                                        }
                                    }
                                    for (int i = 0; i < problem2Customers.size(); i++) {
                                        if(problem2Customers.get(i).getCustomerType().equals("normal") && problem2Customers.get(i).getCustomerState().equals("waitingToSit") && problem2Tables.get(b).isTable()==false){
                                            problem2Customers.get(i).setCustomerState("waitingToOrder");
                                            problem2Tables.get(b).setTable(true);
                                            //hesaplamas.get(hesaplamas.size()-1).setCustomerN(hesaplamas.get(hesaplamas.size()-1).getCustomerN()+1);
                                           //System.out.println("içeri alınıyo ikinci else eeeeeeeeeeeeeeeeeeeeeeeee");
                                        }
                                    }
                                }
                            }

                            for (int i = 0; i < problem2Waiters.size(); i++) {
                                /*
                                if(waiters.get(i).getTakenOrder()==false && waiters.get(i).isWaiter()==true){
                                    waiters.get(i).setWaiterTimer(waiters.get(i).getWaiterTimer()+1);
                                }

                                 */
                                if (problem2Waiters.get(i).isWaiter()==false){
                                    for (int m = 0; m < problem2Customers.size(); m++) {
                                        if(problem2Customers.get(m).getCustomerState().equals("waitingToOrder") && problem2Waiters.get(i).isWaiter()==false){
                                            problem2Customers.get(m).setCustomerState("takeAorder");
                                            problem2Waiters.get(i).setWaiter(true);
                                            /*
                                            if(waiters.get(i).getTakenMeals()!=null){
                                                System.out.println(waiters.get(i).getTakenMeals().size());
                                            for (int n = 0; n < waiters.get(i).getTakenMeals().size(); n++) {
                                                System.out.println("tampona ekliyor");
                                                tampon.add(new TakenMeal());
                                            }
                                            tampon = waiters.get(i).getTakenMeals();
                                            }
                                            //if(waiters.get(i).getTakenMeals()!=null){
                                            //tampon =(ArrayList) waiters.get(i).getTakenMeals().clone();}
                                            System.out.println(m+"-------------------------------");
                                            tampon.add(new TakenMeal(m,false));
                                            System.out.println(tampon.get(tampon.size()-1).getCustomerId()+" "+tampon.get(tampon.size()-1).getTaken());
                                            waiters.get(i).getTakenMeals().get(0).setTaken(false);
                                            waiters.get(i).getTakenMeals().get(0).setCustomerId(m);
                                            System.out.println(waiters.get(i).getTakenMeals().get(waiters.get(i).getTakenMeals().size()-1).getCustomerId()+" "+waiters.get(i).getTakenMeals().get(waiters.get(i).getTakenMeals().size()-1).getTaken());
                                            //waiters.get(i).setWaiter(false);
                                             */
                                            problem2Waiters.get(i).setCustomerId(m);
                                            problem2Waiters.get(i).setTakenOrder(false);
                                            problem2Waiters.get(i).setWaiterTimer(0);
                                            //System.out.println("sipariş alıncak  "+waiters.get(i).getWaiterTimer());
                                        }
                                    }
                                }
                                else {
                                    if(problem2Waiters.get(i).getWaiterTimer()==waiterTime && problem2Customers.get(problem2Waiters.get(i).getCustomerId()).getCustomerState().equals("takeAorder")){
                                        //System.out.println("sipariş alındı "+waiters.get(i).getCustomerId());
                                        //waiters.get(i).getTakenMeals().get(waiters.get(i).getTakenMeals().size()-1).setTaken(true);
                                        problem2Customers.get(Integer.valueOf(problem2Waiters.get(i).getCustomerId())).setCustomerState("waitingToEat");
                                        problem2Waiters.get(i).setTakenOrder(true);
                                        //waiters.get(i).setWaiter(false);

                                    }
                                }

                            }
                            for (int i = 0; i < problem2Chiefs.size(); i++) {
                                if(problem2Chiefs.get(i).isMealOne()==false){
                                    for (int m = 0; m < problem2Waiters.size(); m++) {
                                        if(problem2Chiefs.get(i).isMealOne()==false && problem2Waiters.get(m).getTakenOrder()==true){
                                                problem2Chiefs.get(i).setMealOne(true);
                                                problem2Chiefs.get(i).setMealOneId(problem2Waiters.get(m).getCustomerId());
                                                problem2Chiefs.get(i).setMealOneTimer(0);
                                                //System.out.println("11 yemek hazırlanıyorr "+chiefs.get(i).getMealOneTimer());
                                                problem2Customers.get(problem2Waiters.get(m).getCustomerId()).setCustomerTimer(0);
                                                problem2Waiters.get(m).setWaiter(false);
                                                problem2Waiters.get(m).setTakenOrder(false);
                                                problem2Waiters.get(m).setWaiterTimer(0);
                                        }
                                    }
                                }
                                /*
                                else if (chiefs.get(i).isMealTwo()==false) {
                                    for (int m = 0; m < waiters.size(); m++) {
                                        if(chiefs.get(i).isMealTwo()==false && waiters.get(m).getTakenOrder()==true){

                                                chiefs.get(i).setMealTwo(true);
                                                chiefs.get(i).setMealTwoId(waiters.get(m).getCustomerId());
                                                chiefs.get(i).setMealTwoTimer(0);
                                                System.out.println("22 yemek hazırlanıyorr "+chiefs.get(i).getMealTwoTimer());
                                                customers.get(waiters.get(m).getCustomerId()).setCustomerTimer(0);
                                                waiters.get(m).setWaiter(false);
                                                waiters.get(m).setTakenOrder(false);
                                                waiters.get(m).setWaiterTimer(0);

                                        }
                                    }
                                }
                                 */
                                    if(problem2Customers.get(problem2Chiefs.get(i).getMealOneId()).getCustomerTimer()==chiefTime && problem2Customers.get(problem2Chiefs.get(i).getMealOneId()).getCustomerState().equals("waitingToEat") && problem2Chiefs.get(i).isMealTwo()==false){
                                       // System.out.println("11 yemeye geçiliyor "+chiefs.get(i).getMealOneTimer());
                                        problem2Customers.get(Integer.valueOf(problem2Chiefs.get(i).getMealOneId())).setCustomerState("eating");
                                        problem2Customers.get(Integer.valueOf(problem2Chiefs.get(i).getMealOneId())).setCustomerTimer(0);
                                        problem2Chiefs.get(i).setMealOneTimer(0);
                                        problem2Chiefs.get(i).setMealOne(false);

                                        //waiters.get(m).setTakenOrder(true);
                                    }
                                    /*
                                    else if (customers.get(chiefs.get(i).getMealTwoId()).getCustomerTimer()==chiefTime && customers.get(chiefs.get(i).getMealTwoId()).getCustomerState().equals("waitingToEat") && chiefs.get(i).isMealTwo()==true) {
                                        System.out.println("22 yemeye geçiliyor "+chiefs.get(i).getMealTwoTimer());
                                        customers.get(Integer.valueOf(chiefs.get(i).getMealTwoId())).setCustomerState("eating");
                                        customers.get(Integer.valueOf(chiefs.get(i).getMealTwoId())).setCustomerTimer(0);
                                        chiefs.get(i).setMealTwoTimer(0);
                                        chiefs.get(i).setMealTwo(false);
                                        //waiters.get(m).setTakenOrder(true);
                                    }

                                     */

                            }

                            for (int i = 0; i < problem2Customers.size(); i++) {
                                /*
                                if(customers.get(i).getCustomerState().equals("eating")){
                                    customers.get(i).setCustomerTimer(customers.get(i).getCustomerTimer()+1);
                                }
                                 */
                                if(problem2Customers.get(i).getCustomerTimer()==customerTime && problem2Customers.get(i).getCustomerState().equals("eating")){
                                    problem2Customers.get(i).setCustomerState("paying");
                                    problem2Customers.get(i).setCustomerTimer(0);
                                }
                                /*
                                if(customers.get(i).getCustomerState().equals("paying")){
                                    customers.get(i).setCustomerTimer(customers.get(i).getCustomerTimer()+1);
                                }

                                 */
                                if(problem2Customers.get(i).getCustomerTimer()==cashTime && problem2Customers.get(i).getCustomerState().equals("paying")){
                                    problem2Customers.get(i).setCustomerState("completed");
                                    problem2Hesaplamas.get(problem2Hesaplamas.size()-1).setCustomerN(problem2Hesaplamas.get(problem2Hesaplamas.size()-1).getCustomerN()+1);
                                    problem2Customers.get(i).setCustomerTimer(0);
                                    int sayac=0;
                                    for (int m = 0; m < problem2Tables.size() ; m++) {
                                        if(problem2Tables.get(m).isTable()==true && sayac==0){
                                        problem2Tables.get(m).setTable(false);
                                            //System.out.println("bir masa boşaltıldı");
                                        }
                                    }
                                }

                            }

                            for (int i = 0; i < problem2Customers.size(); i++) {
                                //if(customers.get(i).getCustomerState().equals("waitingToSit"))
                                  problem2Customers.get(i).setCustomerTimer(problem2Customers.get(i).getCustomerTimer()+1);
                                if(problem2Customers.get(i).getCustomerState().equals("waitingToSit")&& problem2Customers.get(i).getCustomerTimer()==20)
                                    problem2Customers.get(i).setCustomerState("terkediyor");
                                //System.out.println(customers.size());
                                //System.out.println("customers  "+customers.get(i).getCustomerId() +" id "+customers.get(i).getCustomerType()+" type "+customers.get(i).getCustomerState()+" state "+customers.get(i).getCustomerTimer()+" time");
                            }
                            for (int i = 0; i < problem2Waiters.size(); i++) {
                                problem2Waiters.get(i).setWaiterTimer(problem2Waiters.get(i).getWaiterTimer()+1);
                                //System.out.println("waiters "+waiters.get(i).getWaiterId()+" id "+waiters.get(i).isWaiter()+" bool "+waiters.get(i).getWaiterTimer()+" timer "+waiters.get(i).getTakenOrder()+" siparişdurumu "+waiters.get(i).getCustomerId()+" siprişverenıd");
                            }
                            for (int i = 0; i < problem2Chiefs.size(); i++) {
                                problem2Chiefs.get(i).setMealOneTimer(problem2Chiefs.get(i).getMealOneTimer()+1);
                                problem2Chiefs.get(i).setMealTwoTimer(problem2Chiefs.get(i).getMealTwoTimer()+1);
                            }

                        }

                    }
                    problem2Tables.clear();
                    problem2Waiters.clear();
                    problem2Chiefs.clear();
                    problem2Customers.clear();
                    vip.clear();
                }
            }
        }

        //System.out.println(" size  "+ problem2Hesaplamas.size());
        Logger.WriteLog("Yapılan toplam hesaplama sayısı: " + problem2Hesaplamas.size());

        ArrayList<Problem2_Hesaplama> problem2HesaplamaSon = new ArrayList<>();
        int enYuksek = 0;

        for (int i = 0; i < problem2Hesaplamas.size(); i++) {
            //System.out.println(" table "+ problem2Hesaplamas.get(i).getTableN()+" waiter "+ problem2Hesaplamas.get(i).getWaiterN()+" chief "+ problem2Hesaplamas.get(i).getChiefN()+" customer "+ problem2Hesaplamas.get(i).getCustomerN());

            problem2Hesaplamas.get(i).setChiefN(problem2Hesaplamas.get(i).getChiefN()/2);
            problem2Hesaplamas.get(i).setPrice(problem2Hesaplamas.get(i).getCustomerN()-(problem2Hesaplamas.get(i).getWaiterN()+ problem2Hesaplamas.get(i).getChiefN()+ problem2Hesaplamas.get(i).getTableN()));

            //System.out.println(problem2Hesaplamas.get(i).getCustomerN()-(problem2Hesaplamas.get(i).getWaiterN()+ problem2Hesaplamas.get(i).getChiefN()+ problem2Hesaplamas.get(i).getTableN()));
            int cost = problem2Hesaplamas.get(i).getCustomerN()-(problem2Hesaplamas.get(i).getWaiterN()+ problem2Hesaplamas.get(i).getChiefN()+ problem2Hesaplamas.get(i).getTableN());
            Logger.WriteLog((i+1) + ") Masa sayısı: " + problem2Hesaplamas.get(i).getTableN() + " | Garson sayısı: " + problem2Hesaplamas.get(i).getWaiterN() + " | Aşçı sayısı: " + problem2Hesaplamas.get(i).getChiefN() + " | Müşteri sayısı: " + problem2Hesaplamas.get(i).getCustomerN() + " | Kazanç: " + cost);

            if(enYuksek< problem2Hesaplamas.get(i).getCustomerN()-(problem2Hesaplamas.get(i).getWaiterN()+ problem2Hesaplamas.get(i).getChiefN()+ problem2Hesaplamas.get(i).getTableN()))
                enYuksek = problem2Hesaplamas.get(i).getCustomerN()-(problem2Hesaplamas.get(i).getWaiterN()+ problem2Hesaplamas.get(i).getChiefN()+ problem2Hesaplamas.get(i).getTableN());

            //System.out.println();
        }
        //System.out.println(enYuksek+"--------------------------------");
        Logger.WriteLog("\nEn İyi Senaryo", ANSICodes.colorGreen);
        for (int i = 0; i < problem2Hesaplamas.size(); i++) {
            if(enYuksek == problem2Hesaplamas.get(i).getPrice()){
                int cost = problem2Hesaplamas.get(i).getCustomerN()-(problem2Hesaplamas.get(i).getWaiterN()+ problem2Hesaplamas.get(i).getChiefN()+ problem2Hesaplamas.get(i).getTableN());
                //System.out.println(" table "+ problem2Hesaplamas.get(i).getTableN()+" waiter "+ problem2Hesaplamas.get(i).getWaiterN()+" chief "+ problem2Hesaplamas.get(i).getChiefN()+" customer "+ problem2Hesaplamas.get(i).getCustomerN());
                Logger.WriteLog((i+1) + ") Masa sayısı: " + problem2Hesaplamas.get(i).getTableN() + " | Garson sayısı: " + problem2Hesaplamas.get(i).getWaiterN() + " | Aşçı sayısı: " + problem2Hesaplamas.get(i).getChiefN() + " | Müşteri sayısı: " + problem2Hesaplamas.get(i).getCustomerN() + " | Kazanç: " + cost, ANSICodes.colorGreen);
                break;
            }
        }

    }
}
