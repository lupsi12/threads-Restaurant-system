import java.io.*;

public class Logger
{
    //private static final String filePath = "/Users/user/workspaces/koü/yazlab3/log/";     // Mac path
    private static final String filePath = "D:\\IdeaProjects\\yazlab3\\log\\";     // Windows path
    private static String fileName;

    public static void SetFileName(String name)
    {
        fileName = name;

        try
        {
            File file = new File(filePath + fileName);
            if (file.createNewFile())
                System.out.println(file.getName() + " başarıyla oluşturuldu.\n");
            else
                System.out.println("Dosya zaten var (ama olmaması lazım).\n");
        }
        catch (IOException e)
        {
            System.out.println("Dosya oluşturma şeyi şey oldu.");
            e.printStackTrace();
        }
    }

    public static void WriteLog(String message, String color, boolean consoleOut) throws InterruptedException
    {
        Main.mutexOfLogger.acquire();
        if(consoleOut) System.out.println(color + message + ANSICodes.colorReset);

        try
        {
            FileWriter a4Paper = new FileWriter(filePath + fileName, true);
            BufferedWriter newspaperOffice = new BufferedWriter(a4Paper);
            PrintWriter hpLaserJet = new PrintWriter(newspaperOffice);

            hpLaserJet.println(message);

            hpLaserJet.close();
            newspaperOffice.close();
            a4Paper.close();
        }
        catch(IOException e)
        {
            System.out.println("Dosya yazma şeyi şey oldu.");
            e.printStackTrace();
        }
        finally
        {
            Main.mutexOfLogger.release();
        }
    }

    public static void WriteLog(String message, String color) throws InterruptedException
    {
        Main.mutexOfLogger.acquire();
        System.out.println(color + message + ANSICodes.colorReset);

        try
        {
            FileWriter a4Paper = new FileWriter(filePath + fileName, true);
            BufferedWriter newspaperOffice = new BufferedWriter(a4Paper);
            PrintWriter hpLaserJet = new PrintWriter(newspaperOffice);

            hpLaserJet.println(message);

            hpLaserJet.close();
            newspaperOffice.close();
            a4Paper.close();
        }
        catch(IOException e)
        {
            System.out.println("Dosya yazma şeyi şey oldu.");
            e.printStackTrace();
        }
        finally
        {
            Main.mutexOfLogger.release();
        }
    }

    public static void WriteLog(String message) throws InterruptedException
    {
        WriteLog(message, ANSICodes.colorReset);
    }
}
