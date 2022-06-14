import java.util.Scanner;

public class Monopoly {

    public static void main(String[] args) {

        MapArray.initializeMapValues();

        showMAP();
    }


    public static void showMAP()
    {
        for (int row=1; row<=30; row++)
        {
            if (row==1) {System.out.println(newLine);}
            for (int col=1; col<=9; col++)
            {
                proPrint(MapArray.mapStyle[row][col], MapArray.mapText[row][col]);
            }
            if (row==5 || row==25) {br(); System.out.println(newLine2);}
            else br();
            if (row==30)  {System.out.println(newLine);}
        }
    }

    //Print text using specific style:
    // cell length, align, maincolor, background color, background symbol, background symbol color
    public static void proPrint(int style, String txt) {

        ProPrintSettings.changeStyle(style);

        String res;
        switch (st.align) {
            case "left" -> res = ProPrintSettings.lText(st, txt);
            case "center", "centre" -> res = ProPrintSettings.cText(st, txt);
            case "right", "rigth" -> res = ProPrintSettings.rText(st, txt);
            default -> res = "";
        }
        System.out.print(res);
    }

    public static void br()
    {
        System.out.println();
    }

    //MAP Frames
    static String newLine = "=========================================================================================================================================================";
    static String newLine2 = "---------------------------------------------------------------------------------------------------------------------------------------------------------";

    //Scanner
    Scanner scn = new Scanner(System.in);

    //Instance of style to use
    public static ProPrintSettings st = new ProPrintSettings();

    //Global inflation variables
    public static double infGlobal = 1;
    public static double infEnergy = 1;
    public static double infWater = 1;
    public static double infFood = 1;

    //Player Arrays (Simulation of Player class)
    public static String[] plName = new String[] {"", "Marto", "Greedy", "Fooly"};
    public static int[] plWhere = new int[] {0,0,0,0};
    public static int[] plMoney = new int[] {0,2000,2000,2000};
    public static boolean[] hadPrisonCard = new boolean[4]; //no one in beginning
    public static boolean[] hadHospitalCard = new boolean[4]; //no one in beginning

    //Buildings Arrays
    public static String[] buildings = new String[]
            {"(0) ÑÒÀĞÒ", "(1) ÖÑÊÀ", "(2) Ëåâñêè", "(3) Óíèâåñòèòåò", "(4) Happy", "(5) McDonnalds", "(6) ÏÎËÈÖÈß",
                    "(7) Âèòîøêà", "(8) ÖÓÌ", "(9) ÁÀÍÊÀ", "(10) Grand H.", "(11) Inter C.", "(12) ÁÎĞÈÑÎÂÀ ÃĞÀÄÈÍÀ",
                    "(13) ÍÄÊ", "(14) Sofia Mall", "(15) Ëåòèùå", "(16) ØÀÍÑ", "(17) Áîëíèöà", "(18) ÇÀÒÂÎĞ",
                    "(19) ÂÈÊ", "(20) Chaos Group", "(21) Global Serv.", "(22) Building C.", "(23) Enegry C."};

    //Split monopoly fields
    public static int[] bMPsplit = new int[] {-1,1,1,-1,2,2,-1,3,3,0,4,4,-1,5,5,0,0,-1,-1,0,6,6,6,0};
    public static int[] bCost = new int[] {0, 130, 130, 0, 230, 230, 0, 270, 270, 0, 350, 350, 0,
                    300, 350, 200, 0, 0, 0, 400, 250, 300, 350, 400};
    public static int[] bUpgrade = new int[] {0, 75, 75, 0, 125, 125, 0, 80, 80, 0, 175, 175, 0,
                    135, 150, 0, 0, 0, 0, 0, 125, 150, 200, 0};
    public static int[] bLevel = new int[24];
    public static int[] bTaxtPL = new int[] {0, 25, 25, 0, 50, 50, 0, 60, 60, 0, 80, 80, 0,
                    75, 80, 50, 0, 0, 0, 70, 60, 75, 80, 70};
    public static int[] bOwner = new int[] {9, 0, 0, 9, 0, 0, 9, 0, 0, 9, 0, 0, 9, 0, 0, 0, 9, 9, 9, 0, 0, 0, 0, 0};
    public static boolean[] bReadyForUpgrade = new boolean[24];
}
