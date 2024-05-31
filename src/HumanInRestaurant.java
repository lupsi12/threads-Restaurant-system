import java.util.ArrayList;

public class HumanInRestaurant implements Runnable
{
    private HumanType humanType;
    private String humanName;
    private int id;
    private boolean shouldContinue;

    private int currentTick;
    private int totalTick;

    private Customer currentCustomer;
    private ArrayList<CookingData> cookingData;

    public HumanInRestaurant(String humanName, int id, HumanType humanType)
    {
        this.humanName = humanName + id;
        this.id = id;
        this.humanType = humanType;
        this.currentTick = 0;
        this.totalTick = 0;
        this.shouldContinue = false;
        this.currentCustomer = null;
        this.cookingData = new ArrayList<>();
    }

    public HumanType getHumanType() {
        return humanType;
    }

    public void setHumanType(HumanType humanType) {
        this.humanType = humanType;
    }

    public String getHumanName() {
        return humanName;
    }

    public void setHumanName(String humanName) {
        this.humanName = humanName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentTick() {
        return currentTick;
    }

    public void setCurrentTick(int currentTick) {
        this.currentTick = currentTick;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public ArrayList<CookingData> getCookingData() {
        return cookingData;
    }

    public void setCookingData(ArrayList<CookingData> cookingData) {
        this.cookingData = cookingData;
    }

    public int getTotalTick() {
        return totalTick;
    }

    public void setTotalTick(int totalTick) {
        this.totalTick = totalTick;
    }

    public boolean isShouldContinue() {
        return shouldContinue;
    }

    public void setShouldContinue(boolean shouldContinue) {
        this.shouldContinue = shouldContinue;
    }

    @Override
    public void run()
    {
        System.out.println(ANSICodes.colorGreen + "Thread [" + this.humanName + "] başlatıldı." + ANSICodes.colorReset);

        switch(humanType)
        {
            case Customer:
                try {
                    CustomerLoop();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        Main.mutexOfCustomerArrayList.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Main.customers.removeIf(c -> c.getHumanInRestaurant().id == this.id);
                    Main.mutexOfCustomerArrayList.release();
                }
                break;

            case Waiter:
                try {
                    WaiterLoop();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        Main.mutexOfWaiterArrayList.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Main.waiters.removeIf(w -> w.getHumanInRestaurant().id == this.id);
                    Main.mutexOfWaiterArrayList.release();
                }
                break;

            case Chief:
                try {
                    ChiefLoop();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        Main.mutexOfChiefArrayList.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Main.chiefs.removeIf(c -> c.getHumanInRestaurant().id == this.id);
                    Main.mutexOfChiefArrayList.release();
                }
                break;

            case CashRegister:
                try {
                    CashRegisterLoop();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        Main.mutexOfCashRegisterArrayList.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Main.cashRegisters.removeIf(r -> r.getHumanInRestaurant().id == this.id);
                    Main.mutexOfCashRegisterArrayList.release();
                }
                break;

            default:
                throw new RuntimeException("[" + humanName + "] Ulaşılamaz nokta: Tüm humanType değerleri kontrol edilmemiş ya da mevcut değer bozulmuş.");
        }

        System.out.println(ANSICodes.colorRed + "Thread [" + this.humanName + "] sonlandırıldı." + ANSICodes.colorReset);
    }

    private void SitTheCustomer(Customer c) throws InterruptedException
    {
        for(int i = 0; i < Main.tables.size(); i++)
        {
            if(!Main.tables.get(i).isDolu())
            {
                Main.tables.get(i).setDolu(true);
                Main.customers.get(Main.customers.indexOf(c)).setLabelNo(i);
                Main.customers.get(Main.customers.indexOf(c)).setCustomerState(CustomerState.WaitingToOrder);
                Main.customers.get(Main.customers.indexOf(c)).getHumanInRestaurant().setCurrentTick(0);

                //Main.mutexOfLogger.acquire();
                //System.out.println("[Tick " + this.totalTick + "] " + this.humanName + " " + i + " no.lu masaya oturuyor.");
                //Main.mutexOfLogger.release();

                return;
            }
        }
    }

    private void KickTheCustomer(Customer c) throws InterruptedException
    {
        Main.mutexOfTablesArrayList.acquire();
        Main.tables.get(c.getLabelNo()).setDolu(false);
        Main.mutexOfTablesArrayList.release();
        Main.customers.get(Main.customers.indexOf(c)).setLabelNo(-1);
        Main.customers.get(Main.customers.indexOf(c)).setCustomerState(CustomerState.Exit);
    }

    public void CustomerLoop() throws InterruptedException
    {
        while(true)
        {
            Main.semaphoreOfSteps.acquire();

            //if(Main.globalShouldContinue) Thread.sleep(Math.round(Tick.secsPerTick * 1000.0f));

            Main.mutexOfThreadTicks.acquire();
            Main.threadTicks++;
            Main.mutexOfThreadTicks.release();

            this.totalTick++;

            CustomerState state = CustomerState.Invalid;
            Main.mutexOfCustomerArrayList.acquire();
            for (Customer c : Main.customers) {
                if (this.id == c.getHumanInRestaurant().id) {
                    state = c.getCustomerState();
                    this.currentTick = c.getHumanInRestaurant().getCurrentTick();
                    break;
                }
            }
            Main.mutexOfCustomerArrayList.release();

            switch (state) {
                case Invalid:
                    throw new RuntimeException("[" + humanName + "] Ulaşılamaz nokta: Invalid durumu hiçbir zaman kullanılmıyor. Mevcut Customer objesi customers listesinde bulunmuyor olabilir.");

                case WaitingToSit:
                    if(this.currentTick >= Tick.ticksToRageQuit)
                    {
                        //System.out.println(ANSICodes.colorMagenta + "[" + humanName + "] \"Sizi mi bekleyecez kardeşim!\" diyerek restorandan ayrılıyor!" + ANSICodes.colorReset);
                        Logger.WriteLog("[" + humanName + "] \"Sizi mi bekleyecez kardeşim!\" diyerek restorandan ayrılıyor!", ANSICodes.colorMagenta);

                        if(Main.threadTicks < Main.threadCount)
                            Main.semaphoreOfSteps.release();
                        else
                            Main.UpdateUI();
                        return;
                    }

                    Main.mutexOfCustomersToSit.acquire();
                    Main.mutexOfCustomerArrayList.acquire();
                    for(Customer c : Main.customers)
                    {
                        if(c.getHumanInRestaurant().id == this.id)
                        {
                            if(Main.vipCustomerCount > 0)
                            {
                                if(c.getCustomerState() == CustomerState.WaitingToSit && c.getCustomerType() == CustomerType.Vip)
                                {
                                    SitTheCustomer(c);
                                    Main.vipCustomerCount--;
                                }
                            }
                            else
                            {
                                if(c.getCustomerState() == CustomerState.WaitingToSit)
                                    SitTheCustomer(c);
                            }
                        }
                    }
                    Main.mutexOfCustomerArrayList.release();
                    this.currentTick++;
                    Main.mutexOfCustomersToSit.release();
                    break;

                case WaitingToOrder, Ordering:
                    // Buradaki durumlar daha sonra Waiter thread'leri tarafından değiştirilecek.
                    break;

                case WaitingToEat:
                    // Buradaki durum daha sonra Chief thread'leri tarafından değiştirilecek.
                    break;

                case Eating:
                    if(this.currentTick >= Tick.ticksToEat)
                    {
                        Main.mutexOfCustomerArrayList.acquire();
                        for(int i = 0; i < Main.customers.size(); i++)
                        {
                            if(Main.customers.get(i).getHumanInRestaurant().getId() == this.id)
                            {
                                //Main.mutexOfLogger.acquire();
                                //System.out.println("[Tick " + this.totalTick + "] " + this.humanName + " yemeğini bitirdi ve ödeme için kasaya gidiyor.");
                                //Main.mutexOfLogger.release();

                                Main.customers.get(i).setCustomerState(CustomerState.WaitingToPay);
                                break;
                            }
                        }
                        Main.mutexOfCustomerArrayList.release();
                    }
                    this.currentTick++;
                    break;

                case WaitingToPay, Paying:
                    // Buradaki durumlar daha sonra CashRegister thread'leri tarafından değiştirilecek.
                    break;

                case Exit:
                    //Main.mutexOfLogger.acquire();
                    //System.out.println(ANSICodes.colorRed + "[Tick " + this.totalTick + "] " + this.humanName + " ödemesini yaptı ve restorandan ayrılıyor." + ANSICodes.colorReset);
                    //Main.mutexOfLogger.release();

                    if(Main.threadTicks < Main.threadCount)
                        Main.semaphoreOfSteps.release();
                    else
                        Main.UpdateUI();
                    return;

                default:
                    throw new RuntimeException("[" + humanName + "] Ulaşılamaz nokta: Tüm CustomerState değerleri kontrol edilmemiş ya da mevcut değer bozulmuş.");
            }

            //Logger.WriteLog("[Tick " + this.totalTick + "] " + this.humanName + " " + state + " durumunda bekliyor.");

            this.shouldContinue = false;

            if(Main.threadTicks < Main.threadCount)
                Main.semaphoreOfSteps.release();
            else
                Main.UpdateUI();

            Thread.sleep(5);
        }
    }

    public void WaiterLoop() throws InterruptedException
    {
        while(true)
        {
            Main.semaphoreOfSteps.acquire();

            //if(Main.globalShouldContinue) Thread.sleep(Math.round(Tick.secsPerTick * 1000.0f));

            Main.mutexOfThreadTicks.acquire();
            Main.threadTicks++;
            Main.mutexOfThreadTicks.release();

            this.totalTick++;

            //WaiterState state = WaiterState.Invalid;
            Waiter waiter = null;
            Main.mutexOfWaiterArrayList.acquire();
            for (Waiter c : Main.waiters) {
                if (this.id == c.getHumanInRestaurant().id) {
                    waiter = c;
                    break;
                }
            }
            Main.mutexOfWaiterArrayList.release();

            if(waiter == null)
                throw new RuntimeException("[" + humanName + "] Ulaşılamaz nokta: Mevcut Waiter objesi null değerine sahip. Mevcut Waiter objesi waiters listesinde bulunmuyor olabilir.");

            this.currentTick = waiter.getHumanInRestaurant().getCurrentTick();

            switch (waiter.getWaiterState()) {
                case Invalid:
                    throw new RuntimeException("[" + humanName + "] Ulaşılamaz nokta: Invalid durumu hiçbir zaman kullanılmıyor. Mevcut Waiter objesi waiters listesinde bulunmuyor olabilir.");

                case WaitingToTakeOrder:
                    Main.mutexOfCustomerArrayList.acquire();
                    if(Main.customers.isEmpty())
                    {
                        Main.mutexOfCustomerArrayList.release();
                        if(Main.threadTicks < Main.threadCount)
                            Main.semaphoreOfSteps.release();
                        else
                            Main.UpdateUI();
                        return;
                    }

                    Main.mutexOfWaiterArrayList.acquire();
                    for(int i = 0; i < Main.customers.size(); i++)
                    {
                        if(Main.customers.get(i).getCustomerState() == CustomerState.WaitingToOrder)
                        {
                            //Main.mutexOfLogger.acquire();
                            //System.out.println(ANSICodes.colorYellow + "[Tick " + this.totalTick + "] " + this.humanName + " " + Main.customers.get(i).getHumanInRestaurant().getHumanName() + "'nin siparişini alıyor." + ANSICodes.colorReset);
                            //Main.mutexOfLogger.release();

                            Main.waiters.get(Main.waiters.indexOf(waiter)).getHumanInRestaurant().currentCustomer = Main.customers.get(i);
                            Main.customers.get(i).setCustomerState(CustomerState.Ordering);
                            Main.customers.get(i).getHumanInRestaurant().setCurrentTick(0);
                            Main.waiters.get(Main.waiters.indexOf(waiter)).setWaiterState(WaiterState.TakingOrder);
                            Main.waiters.get(Main.waiters.indexOf(waiter)).getHumanInRestaurant().setCurrentTick(0);

                            break;
                        }
                    }
                    Main.mutexOfWaiterArrayList.release();

                    Main.mutexOfCustomerArrayList.release();
                    break;

                case TakingOrder:
                    if(this.currentCustomer == null)
                        throw new RuntimeException("[" + humanName + "] Ulaşılamaz nokta: Mevcut Waiter objesinde bulunan currentCustomer objesi null değerine sahip. TakingOrder durumunda currentCustomer her zaman null olmayan bir değerdir.");

                    Main.mutexOfWaitersToTakeOrder.acquire();
                    Main.mutexOfOrderListArrayList.acquire();
                    if(this.currentTick >= Tick.ticksToTakeOrder)
                    {
                        //Main.mutexOfLogger.acquire();
                        //System.out.println(ANSICodes.colorYellow + "[Tick " + this.totalTick + "] " + this.humanName + " " + this.currentCustomer.getHumanInRestaurant().getHumanName() + "'nin siparişini aldı." + ANSICodes.colorReset);
                        //Main.mutexOfLogger.release();

                        Main.orderList.add(this.currentCustomer);
                        Main.customers.get(Main.customers.indexOf(this.currentCustomer)).setCustomerState(CustomerState.WaitingToEat);
                        Main.customers.get(Main.customers.indexOf(this.currentCustomer)).getHumanInRestaurant().setCurrentTick(0);
                        Main.waiters.get(Main.waiters.indexOf(waiter)).setWaiterState(WaiterState.WaitingToTakeOrder);
                        Main.waiters.get(Main.waiters.indexOf(waiter)).getHumanInRestaurant().setCurrentTick(0);
                        Main.waiters.get(Main.waiters.indexOf(waiter)).getHumanInRestaurant().setCurrentCustomer(null);
                    }
                    this.currentTick++;
                    Main.mutexOfOrderListArrayList.release();
                    Main.mutexOfWaitersToTakeOrder.release();
                    break;

                default:
                    throw new RuntimeException("[" + humanName + "] Ulaşılamaz nokta: Tüm WaiterState değerleri kontrol edilmemiş ya da mevcut değer bozulmuş.");
            }

            //Logger.WriteLog("[Tick " + this.totalTick + "] " + this.humanName + " " + waiter.getWaiterState() + " durumunda bekliyor.", ANSICodes.colorYellow);

            this.shouldContinue = false;

            if(Main.threadTicks < Main.threadCount)
                Main.semaphoreOfSteps.release();
            else
                Main.UpdateUI();

            Thread.sleep(5);
        }
    }

    private void AddOrderToChief(Chief chief) throws InterruptedException
    {
        int i = 0;
        while(i < Main.orderList.size())
        {
            if(Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData().size() < 2)
            {
                //Main.mutexOfLogger.acquire();
                //System.out.println(ANSICodes.colorYellow + "[Tick " + this.totalTick + "] " + this.humanName + " " + Main.orderList.get(i).getHumanInRestaurant().getHumanName() + "'nin siparişini pişirmeye başladı." + ANSICodes.colorReset);
                //Main.mutexOfLogger.release();

                Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData().add(new CookingData(Main.orderList.get(i), 0));
                Main.orderList.remove(Main.orderList.get(i));
            }
            i++;
        }
    }

    public void ChiefLoop() throws InterruptedException
    {
        while(true)
        {
            Main.semaphoreOfSteps.acquire();

            //if(Main.globalShouldContinue) Thread.sleep(Math.round(Tick.secsPerTick * 1000.0f));

            Main.mutexOfThreadTicks.acquire();
            Main.threadTicks++;
            Main.mutexOfThreadTicks.release();

            this.totalTick++;

            Chief chief = null;

            Main.mutexOfChiefArrayList.acquire();
            for (Chief c : Main.chiefs) {
                if (this.id == c.getHumanInRestaurant().id) {
                    chief = c;
                    break;
                }
            }
            Main.mutexOfChiefArrayList.release();

            if(chief == null)
                throw new RuntimeException("[" + humanName + "] Ulaşılamaz nokta: Mevcut Chief objesi null değerine sahip. Mevcut Chief objesi chiefs listesinde bulunmuyor olabilir.");

            Main.mutexOfCustomerArrayList.acquire();
            if(Main.customers.isEmpty())
            {
                Main.mutexOfCustomerArrayList.release();
                if(Main.threadTicks < Main.threadCount)
                    Main.semaphoreOfSteps.release();
                else
                    Main.UpdateUI();
                return;
            }
            Main.mutexOfCustomerArrayList.release();

            Main.mutexOfChiefArrayList.acquire();
            if(Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData().size() < 2)
            {
                if(Main.chiefs.get(Main.chiefs.indexOf(chief)).getChiefState() != ChiefState.WaitingToCook)
                    Main.chiefs.get(Main.chiefs.indexOf(chief)).setChiefState(ChiefState.WaitingToCook);

                Main.mutexOfOrderListArrayList.acquire();
                if(!Main.orderList.isEmpty())
                    AddOrderToChief(chief);

                else if(Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData().size() == 1)
                {
                    CookingData c = Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData().getFirst();

                    if(c.cookETA >= Tick.ticksToCook)
                    {
                        Main.mutexOfCustomerArrayList.acquire();

                        //Main.mutexOfLogger.acquire();
                        //System.out.println(ANSICodes.colorYellow + "[Tick " + this.totalTick + "] " + this.humanName + " " + c.customerToCook.getHumanInRestaurant().getHumanName() + "'nin siparişini tamamladı." + ANSICodes.colorReset);
                        //Main.mutexOfLogger.release();

                        Main.customers.get(Main.customers.indexOf(c.customerToCook)).setCustomerState(CustomerState.Eating);
                        Main.customers.get(Main.customers.indexOf(c.customerToCook)).getHumanInRestaurant().setCurrentTick(0);
                        Main.mutexOfCustomerArrayList.release();

                        Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData().remove(c);
                    }
                    else
                        Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData().get(Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData().indexOf(c)).cookETA++;

                    AddOrderToChief(chief);
                }
                Main.mutexOfOrderListArrayList.release();
            }

            if(Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData().size() >= 2)
            {
                if(Main.chiefs.get(Main.chiefs.indexOf(chief)).getChiefState() != ChiefState.Cooking)
                    Main.chiefs.get(Main.chiefs.indexOf(chief)).setChiefState(ChiefState.Cooking);

                for(CookingData c : Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData())
                {
                    if(c.cookETA >= Tick.ticksToCook)
                    {
                        Main.mutexOfCustomerArrayList.acquire();
                        Main.customers.get(Main.customers.indexOf(c.customerToCook)).setCustomerState(CustomerState.Eating);
                        Main.customers.get(Main.customers.indexOf(c.customerToCook)).getHumanInRestaurant().setCurrentTick(0);
                        Main.mutexOfCustomerArrayList.release();

                        Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData().remove(c);
                    }
                    else
                        Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData().get(Main.chiefs.get(Main.chiefs.indexOf(chief)).getHumanInRestaurant().getCookingData().indexOf(c)).cookETA++;
                }
            }
            Main.mutexOfChiefArrayList.release();

            //Logger.WriteLog("[Tick " + this.totalTick + "] " + this.humanName + " " + chief.getChiefState() + " durumunda bekliyor.", ANSICodes.colorCyan);

            this.shouldContinue = false;

            if(Main.threadTicks < Main.threadCount)
                Main.semaphoreOfSteps.release();
            else
                Main.UpdateUI();

            Thread.sleep(5);
        }
    }

    public void CashRegisterLoop() throws InterruptedException
    {
        while(true)
        {
            Main.semaphoreOfSteps.acquire();

            //if(Main.globalShouldContinue) Thread.sleep(Math.round(Tick.secsPerTick * 1000.0f));

            Main.mutexOfThreadTicks.acquire();
            Main.threadTicks++;
            Main.mutexOfThreadTicks.release();

            this.totalTick++;

            CashRegister register = null;

            Main.mutexOfCashRegisterArrayList.acquire();
            for (CashRegister c : Main.cashRegisters)
            {
                if (this.id == c.getHumanInRestaurant().id)
                {
                    register = c;
                    break;
                }
            }
            Main.mutexOfCashRegisterArrayList.release();

            if(register == null)
                throw new RuntimeException("[" + humanName + "] Ulaşılamaz nokta: Mevcut CashRegister objesi null değerine sahip. Mevcut CashRegister objesi cashRegisters listesinde bulunmuyor olabilir.");

            this.currentTick = register.getHumanInRestaurant().getCurrentTick();

            switch (register.getCashRegisterState()) {
                case Invalid:
                    throw new RuntimeException("[" + humanName + "] Ulaşılamaz nokta: Invalid durumu hiçbir zaman kullanılmıyor. Mevcut CashRegister objesi cashRegisters listesinde bulunmuyor olabilir.");

                case WaitingToReceivePayment:
                    Main.mutexOfCustomerArrayList.acquire();
                    if(Main.customers.isEmpty())
                    {
                        Main.mutexOfCustomerArrayList.release();
                        if(Main.threadTicks < Main.threadCount)
                            Main.semaphoreOfSteps.release();
                        else
                            Main.UpdateUI();
                        return;
                    }

                    for(Customer c : Main.customers)
                    {
                        if(c.getCustomerState() == CustomerState.WaitingToPay)
                        {
                            Main.mutexOfCashRegisterArrayList.acquire();
                            Main.cashRegisters.get(Main.cashRegisters.indexOf(register)).setCashRegisterState(CashRegisterState.ReceivingPayment);
                            Main.cashRegisters.get(Main.cashRegisters.indexOf(register)).getHumanInRestaurant().setCurrentCustomer(c);
                            Main.cashRegisters.get(Main.cashRegisters.indexOf(register)).getHumanInRestaurant().setCurrentTick(1);
                            Main.mutexOfCashRegisterArrayList.release();

                            Main.customers.get(Main.customers.indexOf(c)).setCustomerState(CustomerState.Paying);

                            //Main.mutexOfLogger.acquire();
                            //System.out.println(ANSICodes.colorYellow + "[Tick " + this.totalTick + "] " + this.humanName + " " + c.getHumanInRestaurant().getHumanName() + "'nin ödemesini alıyor." + ANSICodes.colorReset);
                            //Main.mutexOfLogger.release();

                            break;
                        }
                    }

                    Main.mutexOfCustomerArrayList.release();
                    break;

                case ReceivingPayment:
                    Main.mutexOfCashRegisterArrayList.acquire();
                    if(this.currentTick >= Tick.ticksToPay)
                    {
                        Main.cashRegisters.get(Main.cashRegisters.indexOf(register)).setCashRegisterState(CashRegisterState.WaitingToReceivePayment);
                        Main.cashRegisters.get(Main.cashRegisters.indexOf(register)).getHumanInRestaurant().setCurrentTick(0);
                        Main.mutexOfCustomerArrayList.acquire();
                        KickTheCustomer(Main.customers.get(Main.customers.indexOf(register.getHumanInRestaurant().getCurrentCustomer())));
                        Main.customers.get(Main.customers.indexOf(register.getHumanInRestaurant().getCurrentCustomer())).setCustomerState(CustomerState.Exit);
                        Main.mutexOfCustomerArrayList.release();
                    }
                    else
                        Main.cashRegisters.get(Main.cashRegisters.indexOf(register)).getHumanInRestaurant().currentTick++;

                    Main.mutexOfCashRegisterArrayList.release();
                    break;

                default:
                    throw new RuntimeException("[" + humanName + "] Ulaşılamaz nokta: Tüm CashRegisterState değerleri kontrol edilmemiş ya da mevcut değer bozulmuş.");
            }

            //Logger.WriteLog("[Tick " + this.totalTick + "] " + this.humanName + " " + register.getCashRegisterState() + " durumunda bekliyor.", ANSICodes.colorBlue);

            this.shouldContinue = false;

            if(Main.threadTicks < Main.threadCount)
                Main.semaphoreOfSteps.release();
            else
                Main.UpdateUI();

            Thread.sleep(5);
        }
    }
}
