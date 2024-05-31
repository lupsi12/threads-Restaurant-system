import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main
{
    public static Semaphore mutexOfCustomersToSit = new Semaphore(1);
    public static Semaphore mutexOfWaitersToTakeOrder = new Semaphore(1);
    public static Semaphore mutexOfReceivingPayment = new Semaphore(1);
    public static Semaphore mutexOfLogger = new Semaphore(1);
    public static Semaphore mutexOfCustomerArrayList = new Semaphore(1);
    public static Semaphore mutexOfWaiterArrayList = new Semaphore(1);
    public static Semaphore mutexOfChiefArrayList = new Semaphore(1);
    public static Semaphore mutexOfCashRegisterArrayList = new Semaphore(1);
    public static Semaphore mutexOfOrderListArrayList = new Semaphore(1);
    public static Semaphore mutexOfTablesArrayList = new Semaphore(1);
    public static Semaphore mutexOfThreadTicks = new Semaphore(1);
    public static Semaphore mutexOfThreadCount = new Semaphore(1);

    public static Semaphore semaphoreOfSteps = new Semaphore(0);


    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Waiter> waiters = new ArrayList<>();
    public static ArrayList<Chief> chiefs = new ArrayList<>();
    public static ArrayList<CashRegister> cashRegisters = new ArrayList<>();
    public static ArrayList<Customer> orderList = new ArrayList<>();
    public static ArrayList<Table> tables = new ArrayList<>();
    public static boolean globalShouldContinue;

    public static int totalCustomerCount;
    public static int vipCustomerCount;
    public static int threadTicks;
    public static int threadCount;

    public static Graphics graphics;

    public static void main(String[] args) throws InterruptedException {

        System.out.printf("\n%sRestoran Yönetim Sistemi%s\n\n", ANSICodes.colorYellow, ANSICodes.colorReset);

        System.out.println("1) Problem 1\n2) Problem 2\n");

        Scanner key = new Scanner(System.in);
        String problemNumber;

        do {
            System.out.print("Çözmek istediğiniz problem numarasını giriniz: ");
            problemNumber = key.nextLine();

            if(!(problemNumber.equals("1") || problemNumber.equals("2")) || problemNumber.isBlank())
                System.out.println(ANSICodes.colorRed + "Geçerli bir problem numarası girin ki problem yaşanmasın." + ANSICodes.colorReset);
        } while(!(problemNumber.equals("1") || problemNumber.equals("2")));

        if(problemNumber.equals("1"))
            new Problem1().StartProblem1();
        else if(problemNumber.equals("2"))
            new Problem2().StartProblem2();
        else
            System.out.println(ANSICodes.colorRed + "[Main] Ulaşılamaz nokta: Problem numarası olarak sadece 1 ya da 2 kabul edileceği kesin olmasına rağmen farklı bir değerle karşılaşıldı." + ANSICodes.colorReset);
    }

    public static void UpdateUI() throws InterruptedException
    {
        String temp;

        mutexOfCustomerArrayList.acquire();
        ArrayList<Customer> tempCus = (ArrayList<Customer>) customers.clone();
        mutexOfCustomerArrayList.release();

        //Logger.WriteLog("Oturmayı bekleyen müşteriler:");
        temp = "";

        for(int i = 0; i < tempCus.size(); i++)
        {
            if(tempCus.get(i).getCustomerState() == CustomerState.WaitingToSit)
            {
                temp += tempCus.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempCus.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Oturmayı bekleyen müşteriler: " + temp);

        //Logger.WriteLog("Sipariş vermeyi bekleyen müşteriler:");
        temp = "";

        for(int i = 0; i < tempCus.size(); i++)
        {
            if(tempCus.get(i).getCustomerState() == CustomerState.WaitingToOrder)
            {
                temp += tempCus.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempCus.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Sipariş vermeyi bekleyen müşteriler: " + temp);

        //Logger.WriteLog("Şu anda sipariş veren müşteriler:");
        temp = "";

        for(int i = 0; i < tempCus.size(); i++)
        {
            if(tempCus.get(i).getCustomerState() == CustomerState.Ordering)
            {
                temp += tempCus.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempCus.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Şu anda sipariş veren müşteriler: " + temp);

        //Logger.WriteLog("Yemek bekleyen müşteriler:");
        temp = "";

        for(int i = 0; i < tempCus.size(); i++)
        {
            if(tempCus.get(i).getCustomerState() == CustomerState.WaitingToEat)
            {
                temp += tempCus.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempCus.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Yemek bekleyen müşteriler: " + temp);

        //Logger.WriteLog("Şu anda yemek yiyen müşteriler:");
        temp = "";

        for(int i = 0; i < tempCus.size(); i++)
        {
            if(tempCus.get(i).getCustomerState() == CustomerState.Eating)
            {
                temp += tempCus.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempCus.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Şu anda yemek yiyen müşteriler: " + temp);

        //Logger.WriteLog("Ödeme yapmayı bekleyen müşteriler:");
        temp = "";

        for(int i = 0; i < tempCus.size(); i++)
        {
            if(tempCus.get(i).getCustomerState() == CustomerState.WaitingToPay)
            {
                temp += tempCus.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempCus.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Ödeme yapmayı bekleyen müşteriler: " + temp);

        //Logger.WriteLog("Şu anda ödeme yapan müşteriler:");
        temp = "";

        for(int i = 0; i < tempCus.size(); i++)
        {
            if(tempCus.get(i).getCustomerState() == CustomerState.Paying)
            {
                temp += tempCus.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempCus.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Şu anda ödeme yapan müşteriler: " + temp);


        mutexOfWaiterArrayList.acquire();
        ArrayList<Waiter> tempWait = (ArrayList<Waiter>) waiters.clone();
        mutexOfWaiterArrayList.release();

        //Logger.WriteLog("Sipariş almayı bekleyen garsonlar:");
        temp = "";

        for(int i = 0; i < tempWait.size(); i++)
        {
            if(tempWait.get(i).getWaiterState() == WaiterState.WaitingToTakeOrder)
            {
                temp += tempWait.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempWait.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Sipariş almayı bekleyen garsonlar: " + temp, ANSICodes.colorYellow);

        //Logger.WriteLog("Şu anda sipariş alan garsonlar:");
        temp = "";

        for(int i = 0; i < tempWait.size(); i++)
        {
            if(tempWait.get(i).getWaiterState() == WaiterState.TakingOrder)
            {
                temp += tempWait.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempWait.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Şu anda sipariş alan garsonlar: " + temp, ANSICodes.colorYellow);


        mutexOfChiefArrayList.acquire();
        ArrayList<Chief> tempChi = (ArrayList<Chief>) chiefs.clone();
        mutexOfChiefArrayList.release();

        //Logger.WriteLog("Sipariş bekleyen aşçılar:");
        temp = "";

        for(int i = 0; i < tempChi.size(); i++)
        {
            if(tempChi.get(i).getChiefState() == ChiefState.WaitingToCook)
            {
                temp += tempChi.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempChi.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Sipariş bekleyen aşçılar: " + temp, ANSICodes.colorBlue);

        //Logger.WriteLog("Şu anda yemek pişiren aşçılar:");
        temp = "";

        for(int i = 0; i < tempChi.size(); i++)
        {
            if(tempChi.get(i).getChiefState() == ChiefState.Cooking)
            {
                temp += tempChi.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempChi.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Şu anda yemek pişiren aşçılar: " + temp, ANSICodes.colorBlue);


        mutexOfCashRegisterArrayList.acquire();
        ArrayList<CashRegister> tempCash = (ArrayList<CashRegister>) cashRegisters.clone();
        mutexOfCashRegisterArrayList.release();

        //Logger.WriteLog("Ödeme bekleyen kasalar:");
        temp = "";

        for(int i = 0; i < tempCash.size(); i++)
        {
            if(tempCash.get(i).getCashRegisterState() == CashRegisterState.WaitingToReceivePayment)
            {
                temp += tempCash.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempCash.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Ödeme bekleyen kasalar: " + temp, ANSICodes.colorCyan);

        //Logger.WriteLog("Şu anda ödeme alan kasalar:");
        temp = "";

        for(int i = 0; i < tempCash.size(); i++)
        {
            if(tempCash.get(i).getCashRegisterState() == CashRegisterState.ReceivingPayment)
            {
                temp += tempCash.get(i).getHumanInRestaurant().getHumanName();
                if(i < tempCash.size() - 1)
                    temp += ", ";
            }
        }
        if(temp.isBlank())
            temp = "(yok)";

        Logger.WriteLog("Şu anda ödeme alan kasalar: " + temp, ANSICodes.colorCyan);
        Logger.WriteLog("");

        graphics.guncelle();

        mutexOfThreadCount.acquire();
        Main.threadCount = Main.customers.size() + Main.waiters.size() + Main.chiefs.size() + Main.cashRegisters.size();
        mutexOfThreadCount.release();

        if(globalShouldContinue)
        {
            Thread.sleep(Math.round(Tick.secsPerTick * 1000.0f));

            mutexOfThreadTicks.acquire();
            threadTicks = 0;
            mutexOfThreadTicks.release();

            semaphoreOfSteps.release();
        }
    }
}