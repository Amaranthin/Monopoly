import java.util.Scanner;

public class Monopoly {

    public static int[] mRow = new int[24]; //the real row for show level and owner // mrow+1 = player on field
    public static int[] mCol = new int[24];


    public static void main(String[] args) {

        MapArray.initializeMapValues();

        initializeFieldToRowColValues();

        //showMAP();

        int curPlayer = 1, curField = 0;
        movePlayerToField(curPlayer, curField);
        curPlayer = 2; curField = 0;
        movePlayerToField(curPlayer, curField);
        curPlayer = 3; curField = 0;
        movePlayerToField(curPlayer, curField);


        setInflationField();
        showOwnership();

        showMAP();


    }

    public static void initializeFieldToRowColValues()
    {
        mRow[0] = 5; mCol[0]=2; //START
        mRow[1] = 5; mCol[1]=3; //FC CSKA
        mRow[2] = 5; mCol[2]=4; //FC Levski
        mRow[3] = 5; mCol[3]=5; //Universitat
        mRow[4] = 5; mCol[4]=6; //Happy B&G
        mRow[5] = 5; mCol[5]=7; //McDonnalds
        mRow[6] = 5; mCol[6]=8; //POLICE
        mRow[7] = 9; mCol[7]=8; //str Vitoshka
        mRow[8] = 13; mCol[8]=8; //CUM
        mRow[9] = 17; mCol[9]=8; //BANK
        mRow[10] = 21; mCol[10]=8; //Grand Hotel
        mRow[11] = 25; mCol[11]=8; //Inter Continental
        mRow[12] = 29; mCol[12]=8; //Borisova Garden
        mRow[13] = 29; mCol[13]=7; //NDK
        mRow[14] = 29; mCol[14]=6; //Sofia Mall
        mRow[15] = 29; mCol[15]=5; //Airport
        mRow[16] = 29; mCol[16]=4; //Chance
        mRow[17] = 29; mCol[17]=3; //Hospital
        mRow[18] = 29; mCol[18]=2; //Jail
        mRow[19] = 25; mCol[19]=2; //Water Company
        mRow[20] = 21; mCol[20]=2; //Chaos Group
        mRow[21] = 17; mCol[21]=2; //Global Services
        mRow[22] = 13; mCol[22]=2; //Buildings Company
        mRow[23] = 9; mCol[23]=2; //Energy Company

    }

    public static void movePlayerToField(int player, int field)
    {
       int row = mRow[field]; int col = mCol[field];
       plWhere[player] = field;

        int res=0;

        // I need this because special print players in same field with different colors
        if (field == plWhere[1]) res += 1;
        if (field == plWhere[2]) res += 10;
        if (field == plWhere[3]) res += 100;
        //bPHere[field] = res;

        String txt="";
        if (res>0) txt = "#" + res; // this text must be formatet by parts; 1-3 players HERE

       updateTextArray(row, col, txt);
    }

    public static void updateTextArray(int row, int col, String newText)
    {
        MapArray.mapText[row][col] = newText;
    }

    public static void showMAP()
    {
        char c;
        for (int row=1; row<=30; row++)
        {
            if (row==1) {System.out.println(newLine);}
            for (int col=1; col<=9; col++)
            {
                //java dont support && for char type
                if (MapArray.mapText[row][col].length()>0)
                {
                    c = MapArray.mapText[row][col].charAt(0);
                    if (c=='#') {
                        partPrintPlayers(MapArray.mapText[row][col]);
                    }
                    else if (row==23 && col==4) printCentered(MapArray.mapText[23][4], MapArray.mapText[0][0].length(), 63);
                    else proPrint(MapArray.mapStyle[row][col], MapArray.mapText[row][col]);
                }
                else proPrint(MapArray.mapStyle[row][col], MapArray.mapText[row][col]);
            }
            if (row==5 || row==25) {br(); System.out.println(newLine2);}
            else br();
            if (row==30)  {System.out.println(newLine);}
        }
    }

    public static void showOwnership()
    {
        MapArray.mapText[11][4] = plName[1]; MapArray.mapText[12][4] = String.valueOf(plMoney[1]);
        MapArray.mapText[11][5] = plName[2]; MapArray.mapText[12][5] = String.valueOf(plMoney[2]);
        MapArray.mapText[11][6] = plName[3]; MapArray.mapText[12][6] = String.valueOf(plMoney[3]);
    }


    //Print text using specific style:
    //cell length, align, maincolor, background color, background symbol, background symbol color
    public static void proPrint(int style, String txt) {

        ProPrintSettings.changeStyle(style);

        String res;
        switch (st.align) {
            case "left" -> res = ProPrintSettings.lText(st, txt);
            case "center", "centre" -> res = ProPrintSettings.cText(st, txt);
            case "right" -> res = ProPrintSettings.rText(st, txt);
            default -> res = "";
        }
        System.out.print(res);
    }

    public static void setInflationField()
    {
        String txt = "";
        String txtWithRealLength = "";

        txt += "\u001B[0m"; //color normal
        txt += "Инфлация: глобална: "; txtWithRealLength += "Инфлация: глобална: ";

        txt +=  "\u001B[31m"; //color pink
        txt += "храна: "; txtWithRealLength += "храна: ";

        txt +=  "\u001B[36m"; //color cyan
        txt += "вода: "; txtWithRealLength += "вода: ";

        txt +=  "\u001B[33m"; //color yellow
        txt += "енергия: "; txtWithRealLength += "енергия: ";

        MapArray.mapText[23][4] = txt;  //colored text
        MapArray.mapText[0][0] =  txtWithRealLength;
    }

    public static void partPrintPlayers(String txt)
    {   //when 2 or more players is in same field we need print them in different style
        String nText = txt.substring(1);

        String[] player = new String[4];
        player[1]=""; player[2]=""; player[3]="";

        int num = Integer.valueOf(nText);
        int cntPlayersHere = 0;

        ProPrintSettings.changeStyle(MapArray.PLAYER3_3); //use global styles only for format each player
        if (num/100==1) {player[3] = ProPrintSettings.cText(st,"[3]"); cntPlayersHere++;}
        num = num%100;
        ProPrintSettings.changeStyle(MapArray.PLAYER2_3);
        if (num/10==1) {player[2] = ProPrintSettings.cText(st,"[2]"); cntPlayersHere++;}
        ProPrintSettings.changeStyle(MapArray.PLAYER1_3);
        if (num%10==1) {player[1] = ProPrintSettings.cText(st,"[1]"); cntPlayersHere++;}

        String allPls = player[1] + player[2] + player[3];

        printCentered(allPls,cntPlayersHere*3,21); //but don't use global styles for this field because each player must be in own color
    }

    public static void printCentered(String text, int txtlen, int inWidth)
    {
        String finalText = "";
        int leftSp = (inWidth - txtlen)/2;
        int rightSp = inWidth - leftSp - txtlen;

        for (int i=1; i<= leftSp; i++) finalText += " ";
        finalText+= text;
        for (int i=1; i<= rightSp; i++) finalText += " ";
        System.out.print(finalText);
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
    public static String[] plName = new String[] {"", "Smarto", "Greedy", "Fooly"};
    public static int[] plWhere = new int[] {0,0,0,0};
    public static int[] plMoney = new int[] {0,2000,2000,2000};
    public static boolean[] hadPrisonCard = new boolean[4]; //no one in beginning
    public static boolean[] hadHospitalCard = new boolean[4]; //no one in beginning

    //Buildings Arrays
    public static String[] buildings = new String[]
            {"(0) СТАРТ", "(1) ЦСКА", "(2) Левски", "(3) Унивеститет", "(4) Happy", "(5) McDonnalds", "(6) ПОЛИЦИЯ",
                    "(7) Витошка", "(8) ЦУМ", "(9) БАНКА", "(10) Grand H.", "(11) Inter C.", "(12) БОРИСОВА ГРАДИНА",
                    "(13) НДК", "(14) Sofia Mall", "(15) Летище", "(16) ШАНС", "(17) Болница", "(18) ЗАТВОР",
                    "(19) ВИК", "(20) Chaos Group", "(21) Global Serv.", "(22) Building C.", "(23) Enegry C."};

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
    public static int[] bPHere = new int[24]; // 1 - p1 , 10 - p2, 100 - p3 sum of them


}
