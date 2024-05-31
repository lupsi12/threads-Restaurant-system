import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Problem1
{
    Scanner key = new Scanner(System.in);

    public final int tableCount = 6;
    public final int waiterCount = 3;
    public final int chiefCount = 2;
    public final int cashRegisterCount = 1;
    public final int stepCount = 4;

    public void StartProblem1() throws InterruptedException
    {
        Main.mutexOfCustomersToSit = new Semaphore(1);
        Main.mutexOfWaitersToTakeOrder = new Semaphore(1);
        Main.mutexOfReceivingPayment = new Semaphore(1);
        Main.mutexOfLogger = new Semaphore(1);
        Main.mutexOfCustomerArrayList = new Semaphore(1);
        Main.mutexOfWaiterArrayList = new Semaphore(1);
        Main.mutexOfChiefArrayList = new Semaphore(1);
        Main.mutexOfCashRegisterArrayList = new Semaphore(1);
        Main.mutexOfOrderListArrayList = new Semaphore(1);
        Main.mutexOfTablesArrayList = new Semaphore(1);
        Main.mutexOfThreadTicks = new Semaphore(1);
        Main.mutexOfThreadCount = new Semaphore(1);

        Main.semaphoreOfSteps = new Semaphore(0);

        Main.customers.clear();
        Main.waiters.clear();
        Main.chiefs.clear();
        Main.cashRegisters.clear();
        Main.orderList.clear();

        Main.globalShouldContinue = false;

        Main.totalCustomerCount = 0;
        Main.vipCustomerCount = 0;
        Main.threadTicks = 0;

        ANSICodes.ClearScreen();

        LocalDateTime history = LocalDateTime.now();
        DateTimeFormatter formationLap = DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm.ss");
        Logger.SetFileName("Problem1 - " + history.format(formationLap) + ".log");

        System.out.println("Müşteri bilgilerini " + ANSICodes.colorGreen + "(Toplam Müşteri Sayısı) " + ANSICodes.colorRed + "(Öncelikli Müşteri Sayısı)" + ANSICodes.colorReset + " formatında giriniz.\n");

        for(int i = 1; i <= stepCount; ++i)
        {
            String[] input;
            int tcc = 0, vcc = 0;
            boolean errorFlag;

            do {
                errorFlag = false;
                System.out.print(i + ". adım: ");
                input = key.nextLine().split(" ");

                if(input[0].isBlank())
                {
                    errorFlag = true;
                    System.out.println(ANSICodes.colorRed + "Toplam Müşteri Sayısı'nı girmeyi unutmayın." + ANSICodes.colorReset);
                }

                else if(input.length < 2)
                {
                    errorFlag = true;
                    System.out.println(ANSICodes.colorRed + "Öncelikli Müşteri Sayısı'nı girmeyi unutmayın." + ANSICodes.colorReset);
                }

                else
                {
                    tcc = Integer.parseInt(input[0]);

                    if (tcc > 10)
                    {
                        errorFlag = true;
                        System.out.println(ANSICodes.colorRed + "Restorana tek adımda 10'dan fazla müşteri giremez." + ANSICodes.colorReset);
                    }

                    vcc = Integer.parseInt(input[1]);
                }
            } while (errorFlag);

            Main.totalCustomerCount += tcc;
            Main.vipCustomerCount += vcc;

            Logger.WriteLog(i + ". Adım - Toplam müşteri sayısı: " + tcc + " | Önemli müşteri sayısı: " + vcc, ANSICodes.colorReset, false);
        }

        //System.out.println("\n" + ANSICodes.colorGreen + "Toplam Müşteri Sayısı: " + ANSICodes.colorReset + Main.totalCustomerCount);
        //System.out.println(ANSICodes.colorRed + "Önemli Müşteri Sayısı: " + ANSICodes.colorReset + Main.vipCustomerCount + "\n");
        Logger.WriteLog("\nToplam Müşteri Sayısı: " + Main.totalCustomerCount, ANSICodes.colorGreen);
        Logger.WriteLog("Önemli Müşteri Sayısı: " + Main.vipCustomerCount + "\n", ANSICodes.colorRed);

        /*for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                Main.tables.add(new Table());
                Main.tables.getLast().setSutun(i);
                Main.tables.getLast().setSatir(j);
                Main.tables.getLast().setDolu(false);
            }
        }*/

        for(int i = 0; i < tableCount; i++)
        {
            Main.tables.add(new Table(i % 2, i / 2, false));
        }

        for(int i = 1; i <= Main.totalCustomerCount; ++i)
        {
            HumanInRestaurant humanInRestaurant = new HumanInRestaurant("Musteri ", i, HumanType.Customer);
            Customer customer = new Customer();
            customer.setCustomerState(CustomerState.WaitingToSit);

            if(Main.vipCustomerCount>Main.customers.size())
                customer.setCustomerType(CustomerType.Vip);
            else
                customer.setCustomerType(CustomerType.Normal);

            customer.setHumanInRestaurant(humanInRestaurant);
            customer.setLabelNo(-1);
            Main.customers.add(customer);
        }

        for(int i = 1; i <= waiterCount; ++i)
        {
            HumanInRestaurant humanInRestaurant = new HumanInRestaurant("Garson ", i, HumanType.Waiter);

            Waiter waiter = new Waiter();
            waiter.setWaiterState(WaiterState.WaitingToTakeOrder);
            waiter.setHumanInRestaurant(humanInRestaurant);
            Main.waiters.add(waiter);
        }

        for(int i = 1; i <= chiefCount; ++i)
        {
            HumanInRestaurant humanInRestaurant = new HumanInRestaurant("Asci ", i, HumanType.Chief);

            Chief chief = new Chief();
            chief.setChiefState(ChiefState.WaitingToCook);
            chief.setHumanInRestaurant(humanInRestaurant);
            Main.chiefs.add(chief);
        }

        for(int i = 1; i <= cashRegisterCount; ++i)
        {
            HumanInRestaurant humanInRestaurant = new HumanInRestaurant("Kasa ", i, HumanType.CashRegister);

            CashRegister cashRegister = new CashRegister();
            cashRegister.setCashRegisterState(CashRegisterState.WaitingToReceivePayment);
            cashRegister.setHumanInRestaurant(humanInRestaurant);
            Main.cashRegisters.add(cashRegister);
        }

        Main.threadCount = Main.customers.size() + Main.waiters.size() + Main.chiefs.size() + Main.cashRegisters.size();

        Main.graphics = new Graphics(Main.tables);
        Main.graphics.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main.graphics.setVisible(true);

        for(Waiter w : Main.waiters)
        {
            Thread waiter = new Thread(w.getHumanInRestaurant());
            waiter.start();
        }

        for(Chief c : Main.chiefs)
        {
            Thread chief = new Thread(c.getHumanInRestaurant());
            chief.start();
        }

        for(CashRegister r : Main.cashRegisters)
        {
            Thread cashRegister = new Thread(r.getHumanInRestaurant());
            cashRegister.start();
        }

        for(Customer c : Main.customers)
        {
            Thread customer = new Thread(c.getHumanInRestaurant());
            customer.start();
        }
    }
}
