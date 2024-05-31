import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Graphics extends JFrame {
    JLabel[][] resim=new JLabel[9][2];
    JLabel[][] bilgi=new JLabel[9][2];
    JLabel[] chiefR = new JLabel[Main.chiefs.size()];
    JLabel[] chiefB = new JLabel[Main.chiefs.size()];
    JLabel[] waiterR = new JLabel[Main.waiters.size()];
    JLabel[] waiterB = new JLabel[Main.waiters.size()];
    JLabel[] registerR = new JLabel[Main.cashRegisters.size()];
    JLabel[] registerB = new JLabel[Main.cashRegisters.size()];
    JLabel queueB;
    JLabel queueR;
    JLabel orderB;
    JLabel orderR;
    ArrayList<Table> tables;
    String path = "/Users/user/workspaces/koü/yazlab3/img/";    // Mac path
    //String path = "D:\\IdeaProjects\\yazlab3\\img\\";    // Windows path

    public Graphics(ArrayList<Table> koordinats1) throws InterruptedException {
        tables = koordinats1;
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        add(panel);
        setSize(1800,1000);
        setTitle("Restoran Yönetim Sistemi");
        label.setBackground(Color.black);
        label.setBounds(0,0,1800,1000);
        label.setBackground(Color.BLACK);
        getContentPane().add(label);
        JScrollPane scrollPane = new JScrollPane(panel);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        /*int sayac = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                resim[i][j] = new JLabel();
                resim[i][j].setBounds(25 + 330 * i, 5+ 180 * j, 150, 150);
                resim[i][j].setIcon(new ImageIcon(new ImageIcon(path+"emptyTable.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
                label.add(resim[i][j]);
                bilgi[i][j] = new JLabel();
                bilgi[i][j].setBounds(25 + 330 * i, 145 + 180 * j, 300, 50);
                bilgi[i][j].setText(sayac+ " label");
                sayac++;
                label.add(bilgi[i][j]);
            }
        }*/

        for(Table t : tables)
        {
            resim[t.getSutun()][t.getSatir()] = new JLabel();
            resim[t.getSutun()][t.getSatir()].setBounds(25 + 200 * t.getSutun(), 5 + 180 * t.getSatir(), 150, 150);
            resim[t.getSutun()][t.getSatir()].setIcon(new ImageIcon(new ImageIcon(path+"emptyTable.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
            label.add(resim[t.getSutun()][t.getSatir()]);

            bilgi[t.getSutun()][t.getSatir()] = new JLabel();
            bilgi[t.getSutun()][t.getSatir()].setBounds(25 + 200 * t.getSutun(), 145 + 180 * t.getSatir(), 150, 50);
            bilgi[t.getSutun()][t.getSatir()].setText("Boş Masa");
            label.add(bilgi[t.getSutun()][t.getSatir()]);
        }

        queueR = new JLabel();
        queueR.setBounds(25, 400, 100, 100);
        queueR.setIcon(new ImageIcon(new ImageIcon(path+"crowd.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        label.add(queueR);

        queueB = new JLabel();
        queueB.setBounds(25, 500, 100,400);
        queueB.setVerticalTextPosition(SwingConstants.TOP);
        label.add(queueB);

        for (int i = 0; i < Main.waiters.size(); i++) {
            waiterR[i] = new JLabel();
            //waiterR[i].setBounds(25 , i*75+(380+ 80 * Main.chiefs.size()), 75, 75);
            waiterR[i].setBounds(i*120 + 200, 400, 100, 100);
            waiterR[i].setIcon(new ImageIcon(new ImageIcon(path+"Waiter.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            label.add(waiterR[i]);
            waiterB[i] = new JLabel();
            //waiterB[i].setBounds(110, i*75+(400+ 80 * Main.chiefs.size()), 1500, 50);
            waiterB[i].setBounds(i*120 + 200, 500, 100, 100);
            waiterB[i].setVerticalTextPosition(SwingConstants.TOP);
            label.add(waiterB[i]);
        }

        orderR = new JLabel();
        orderR.setBounds(250 + 120*Main.waiters.size(), 400, 100, 100);
        orderR.setIcon(new ImageIcon(new ImageIcon(path+"orderList.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        label.add(orderR);

        orderB = new JLabel();
        orderB.setBounds(250 + 120*Main.waiters.size(), 500, 100, 100);
        orderB.setVerticalTextPosition(SwingConstants.TOP);
        label.add(orderB);

        for (int i = 0; i < Main.chiefs.size(); i++) {
            chiefR[i] = new JLabel();
            //chiefR[i].setBounds(25 , 380+ 80 * i, 75, 75);
            chiefR[i].setBounds(350 + (140 * Main.waiters.size()) + i * 120, 400, 100, 100);
            chiefR[i].setIcon(new ImageIcon(new ImageIcon(path+"Chief.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            label.add(chiefR[i]);
            chiefB[i] = new JLabel();
            //chiefB[i].setBounds(110, 400 + 80 * i, 1500, 50);
            chiefB[i].setBounds(350 + (140 * Main.waiters.size()) + i * 120, 500, 100, 130);
            chiefB[i].setVerticalTextPosition(SwingConstants.TOP);
            label.add(chiefB[i]);
        }

        for (int i = 0; i < Main.cashRegisters.size(); i++) {
            registerR[i] = new JLabel();
            //registerR[i].setBounds(25 , i*75+(380+ 80 * Main.chiefs.size()) + 75 * Main.waiters.size(), 75, 75);
            registerR[i].setBounds(i * 120 + (400 + 140 * Main.chiefs.size()) + 140 * Main.waiters.size(), 400, 100, 100);
            registerR[i].setIcon(new ImageIcon(new ImageIcon(path+"CashRegister.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            label.add(registerR[i]);
            registerB[i] = new JLabel();
            //registerB[i].setBounds(110, i*75+(400+ 80 * Main.chiefs.size()) + 75 * Main.waiters.size(), 1500, 50);
            registerB[i].setBounds(i * 120 + (400 + 140 * Main.chiefs.size()) + 140 * Main.waiters.size(), 500, 100, 400);
            registerB[i].setVerticalTextPosition(SwingConstants.TOP);
            label.add(registerB[i]);
        }
        guncelle();

        JButton sonraki = new JButton("Bir Adım İlerle");
        sonraki.setFont(new Font((""), Font.BOLD,20));
        sonraki.setBounds(250,900,200,40);
        label.add(sonraki);
        sonraki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!Main.globalShouldContinue)
                {
                    try {
                        Main.mutexOfThreadTicks.acquire();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.threadTicks = 0;
                    Main.mutexOfThreadTicks.release();

                    Main.semaphoreOfSteps.release();
                }
            }
        });

        JButton startStop = new JButton("Başlat/Durdur");
        startStop.setFont(new Font((""), Font.BOLD,20));
        startStop.setBounds(475,900,200,40);
        label.add(startStop);
        startStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {
                    Main.mutexOfThreadTicks.acquire();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                Main.threadTicks = 0;
                Main.mutexOfThreadTicks.release();

                Main.semaphoreOfSteps.release();

                Main.globalShouldContinue = !Main.globalShouldContinue;
            }
        });
        JLabel bos = new JLabel();
        label.add(bos);
    }
    public void guncelle() throws InterruptedException
    {
        Main.mutexOfCustomerArrayList.acquire();
        ArrayList<Customer> tempCustomers = (ArrayList<Customer>) Main.customers.clone();
        Main.mutexOfCustomerArrayList.release();

        Main.mutexOfTablesArrayList.acquire();
        tables = (ArrayList<Table>) Main.tables.clone();
        Main.mutexOfTablesArrayList.release();

        for(Table t : tables)
        {
            if(!t.isDolu())
            {
                resim[t.getSutun()][t.getSatir()].setIcon(new ImageIcon(new ImageIcon(path + "emptyTable.png").getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
                bilgi[t.getSutun()][t.getSatir()].setText("Boş Masa");
            }
            else
            {
                for(int i = 0; i < tempCustomers.size(); i++)
                {
                    if(tempCustomers.get(i).getLabelNo() == tables.indexOf(t))
                    {
                        String text = "<html>" + tempCustomers.get(i).getHumanInRestaurant().getHumanName() + " (" + ((tempCustomers.get(i).getCustomerType() == CustomerType.Normal) ? "Normal" : "Öncelikli") + ")<br/>";
                        String icon = "";
                        switch(tempCustomers.get(i).getCustomerState())
                        {
                            case WaitingToOrder -> {
                                text += "Sipariş vermeyi bekliyor.";
                                icon = (tempCustomers.get(i).getCustomerType() == CustomerType.Normal) ? "normal_WaitingToOrder.jpg" : "vip_WaitingToOrder.jpg";
                            }
                            case Ordering -> {
                                text += "Sipariş veriyor.";
                                icon = (tempCustomers.get(i).getCustomerType() == CustomerType.Normal) ? "normal_WaitingToOrder.jpg" : "vip_WaitingToOrder.jpg";
                            }
                            case WaitingToEat -> {
                                text += "Yemeğini bekliyor.";
                                icon = (tempCustomers.get(i).getCustomerType() == CustomerType.Normal) ? "normal_WaitingToEat.jpg" : "vip_WaitingToEat.jpg";
                            }
                            case Eating -> {
                                text += "Yemeğini yiyor.";
                                icon = (tempCustomers.get(i).getCustomerType() == CustomerType.Normal) ? "normal_Eating.jpg" : "vip_Eating.jpg";
                            }
                            case WaitingToPay, Paying -> {
                                text += "Hesabını ödemeye gitti.";
                                icon = "wentToPay.png";
                            }
                        }

                        text += "</html>";

                        resim[t.getSutun()][t.getSatir()].setIcon(new ImageIcon(new ImageIcon(path + icon).getImage().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
                        bilgi[t.getSutun()][t.getSatir()].setText(text);

                        break;
                    }
                }
            }
        }

        String wait = "<html>BEKLEYENLER:<br/><br/>";

        for (Customer c : tempCustomers) {
            if(c.getCustomerState()==CustomerState.WaitingToSit)
                wait = wait + c.getHumanInRestaurant().getHumanName()+ "<br/>";
        }
        wait += "</html>";
        queueB.setText(wait);

        Main.mutexOfChiefArrayList.acquire();
        ArrayList<Chief> tempChiefs = (ArrayList<Chief>) Main.chiefs.clone();
        Main.mutexOfChiefArrayList.release();

        for (int i = 0; i < tempChiefs.size(); i++)
        {
            String meal = "<html>" + tempChiefs.get(i).getHumanInRestaurant().getHumanName() + "<br/><br/>";
            if(tempChiefs.get(i).getHumanInRestaurant().getCookingData().isEmpty())
                meal += "Sipariş bekliyor.";
            else
            {
                meal += "Siparişleri hazırlıyor:<br/><br/>";
                for(CookingData c : tempChiefs.get(i).getHumanInRestaurant().getCookingData())
                {
                    meal += c.customerToCook.getHumanInRestaurant().getHumanName() + "<br/>";
                }
            }
            meal += "</html>";
            chiefB[i].setText(meal);
        }

        Main.mutexOfOrderListArrayList.acquire();
        ArrayList<Customer> tempOrder = (ArrayList<Customer>) Main.orderList.clone();
        Main.mutexOfOrderListArrayList.release();

        String orders = "<html>SİPARİŞLER<br/><br/>";
        for(Customer c : tempOrder)
        {
            orders += c.getHumanInRestaurant().getHumanName() + "<br/>";
        }
        orders += "</html>";
        orderB.setText(orders);

        Main.mutexOfWaiterArrayList.acquire();
        ArrayList<Waiter> tempWaiters = (ArrayList<Waiter>) Main.waiters.clone();
        Main.mutexOfWaiterArrayList.release();

        for (int i = 0; i < tempWaiters.size(); i++) {
            String order = "<html>" + tempWaiters.get(i).getHumanInRestaurant().getHumanName() + "<br/><br/>";
            if(tempWaiters.get(i).getHumanInRestaurant().getCurrentCustomer() == null)
                order += "Boşta bekliyor.";
            else
                order += "Sipariş alıyor:<br/><br/>" + tempWaiters.get(i).getHumanInRestaurant().getCurrentCustomer().getHumanInRestaurant().getHumanName() + "<br/>";

            order += "</html>";
            waiterB[i].setText(order);
        }


        Main.mutexOfCashRegisterArrayList.acquire();
        ArrayList<CashRegister> tempCashRegisters = (ArrayList<CashRegister>) Main.cashRegisters.clone();
        Main.mutexOfCashRegisterArrayList.release();

        for (int i = 0; i < tempCashRegisters.size(); i++) {
            String regis = "<html>KASADAKİLER:<br/><br/>";
            for (Customer c : tempCustomers) {
                if(c.getCustomerState()==CustomerState.WaitingToPay)
                    regis = regis + c.getHumanInRestaurant().getHumanName()+ "<br/>";
            }
            regis += "</html>";
            registerB[i].setText(regis);
        }
    }
}
