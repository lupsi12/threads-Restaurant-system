public class ANSICodes
{
    public static final String colorReset = "\033[0m";
    public static final String colorBlack = "\033[1;30m";
    public static final String colorRed = "\033[1;31m";
    public static final String colorGreen = "\033[1;32m";
    public static final String colorYellow = "\033[1;33m";
    public static final String colorBlue = "\033[1;34m";
    public static final String colorMagenta = "\033[1;35m";
    public static final String colorCyan = "\033[1;36m";
    public static final String colorWhite = "\033[1;37m";

    public static void ClearScreen()
    {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
}
