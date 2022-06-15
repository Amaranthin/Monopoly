import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Monopoly {

    static int exitloop = 0;
    static int curPlayer = 1;

    public static void main(String[] args) {

        MapArray.initializeMapValues(); //Once in beginning
        initializeFieldToRowColValues(); //Once in beggining
        setInflationField(); //working

        MapArray.mapText[11][4] = plName[1];  //Once
        MapArray.mapText[11][5] = plName[2];
        MapArray.mapText[11][6] = plName[3];

        curPlayer = 1;

        while (!endGame)
        {
            exitloop++; if (exitloop>200) endGame = true; // break infinity loop when test


            System.out.print("Player "+plName[curPlayer]+" enter your action (r-roll dices)");
            String act = scn.nextLine();

            if (act.equals("r"))
            {
                int d1 = rollDice(); int d2 = rollDice();
                System.out.printf("Хвърлихте %d + %d ",d1,d2);


                int newField = (plWhere[curPlayer] + d1  + d2) % 24;
                String txt = "Играч " + plName[curPlayer]+" попада на поле " + buildings[newField];
                System.out.println(txt);

                plWhere[curPlayer] = newField; movePlayerToField(); //working
            }

            ProPrintSettings.showMAP(); //working
            curPlayer++; if (curPlayer==4) curPlayer=1;
        }



        addBuildToPlayer(1,13); //working
        addBuildToPlayer(1,4);
        addBuildToPlayer(1,1);

        addBuildToPlayer(2,22); //working
        addBuildToPlayer(2,8);
        addBuildToPlayer(2,15);
        addBuildToPlayer(2,14);

        addBuildToPlayer(3,2);
        addBuildToPlayer(3,23);
        addBuildToPlayer(3,5);
        addBuildToPlayer(3,11);





        /*for (int x=1; x<=plOwnerList[1][0];x++)
        {
            System.out.println(plOwnerList[1][x]);
        }
         */

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

    public static void movePlayerToField()
    {
        for (int field=0; field<24; field++)
        {
            int row = mRow[field]; int col = mCol[field];
            int res=0;

            // I need this because special print players in same field with different colors
            if (field == plWhere[1]) res += 1;
            if (field == plWhere[2]) res += 10;
            if (field == plWhere[3]) res += 100;

            String txt="";
            if (res>0) txt = "#" + res; // this text must be formatet by parts; 1-3 players HERE

            updateTextArray(row, col, txt);
        }

    }

    public static void updateTextArray(int row, int col, String newText)
    {
        MapArray.mapText[row][col] = newText;
    }


    public static String getBuildFromOwnerList(String txt)
    {
        String newText = txt.substring(1);
        double val = Double.valueOf(newText);
        val = val*10;
        int player = (int) val/10;
        int index = (int) val%10;

        if (plOwnerList[player][index]==0) return "|";  //empty elements dont be show as (0) START
        else return buildings[plOwnerList[player][index]];
    }

    public static void addBuildToPlayer(int pl, int build)
    {
        int ix = plOwnerList[pl][0]+1;  //index [pl][0] show us number of added items
        plOwnerList[pl][ix] = build;

        //Sort Array
        if (ix>1)
        {        //find only position on new item
            for (int i=ix; i>=2; i--)
            {
                if (plOwnerList[pl][i]<plOwnerList[pl][i-1])
                {   //if new item is with low index let Up it
                    int p = plOwnerList[pl][i];
                    plOwnerList[pl][i] = plOwnerList[pl][i-1];
                    plOwnerList[pl][i-1] = p;
                }
            }
        }
        plOwnerList[pl][0]+=1;

    }

    public static void refreshPlayerMoney(int player, int change)
    {
        plMoney[player] += change;
        MapArray.mapText[12][3+player] = String.valueOf(plMoney[player]); //for MAP
    }


    public static void setInflationField()
    {
        String txt = "";
        String txtWithRealLength = "";

        txt += "\u001B[0m"; //color normal
        txt += "Глобална: ";
        txt += convertInflationToPercent(infGlobal);

        txt +=  "\u001B[36m"; //color cyan
        txt += "Вода: ";
        txt += convertInflationToPercent(infWater);

        txt +=  "\u001B[31m"; //color pink
        txt += "Храна: ";
        txt += convertInflationToPercent(infFood);

        txt +=  "\u001B[33m"; //color yellow
        txt += "Енергия: ";
        txt += convertInflationToPercent(infEnergy);

        txtWithRealLength += "Глобална: ";
        txtWithRealLength += convertInflationToPercent(infGlobal);
        txtWithRealLength += "Вода: ";
        txtWithRealLength += convertInflationToPercent(infWater);
        txtWithRealLength += "Храна: ";
        txtWithRealLength += convertInflationToPercent(infFood);
        txtWithRealLength += "Енергия: ";
        txtWithRealLength += convertInflationToPercent(infEnergy);

        MapArray.mapText[23][4] = txt;  //colored text
        MapArray.mapText[0][0] =  txtWithRealLength;
    }

 public static int rollDice()
 {
     Random x = new Random();
     int dice = x.nextInt(4)+1;
     return dice;
 }
    public static String convertInflationToPercent(double inf)
    {
        double newInf = inf*100;
        int perc = (int) newInf - 100;
        return String.valueOf(perc)+"% ";
    }

    //The real rows where shows level and owner // mrow = player on field
    public static int[] mRow = new int[24];
    public static int[] mCol = new int[24];

    //Continue the game while this is false
    public static boolean endGame = false;

    //Scanner
    static Scanner scn = new Scanner(System.in);

    //Global inflation variables
    public static double infGlobal = 1.22;
    public static double infEnergy = 1.11;
    public static double infWater = 1.07;
    public static double infFood = 1.45;

    //Player Arrays (Simulation of Player class)
    public static String[] plName = new String[] {"", "Smarto", "Greedy", "Fooly"};
    public static int[] plWhere = new int[] {0,0,0,0};
    public static int[] plMoney = new int[] {0,2000,2000,2000};
    public static int[][] plOwnerList = new int[4][9]; // [player] [building] /// [pl][0] - count elements
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

    //Prices without any inflation
    public static int[] bCost = new int[] {0, 130, 130, 0, 230, 230, 0, 270, 270, 0, 350, 350, 0,
                    300, 350, 200, 0, 0, 0, 400, 250, 300, 350, 400};

    //Upgrade cost without any inflation
    public static int[] bUpgrade = new int[] {0, 75, 75, 0, 125, 125, 0, 80, 80, 0, 175, 175, 0,
                    135, 150, 0, 0, 0, 0, 0, 125, 150, 200, 0};

    //Levels after upgrade
    public static int[] bLevel = new int[24];

    //Tax per Level
    public static int[] bTaxtPL = new int[] {0, 25, 25, 0, 50, 50, 0, 60, 60, 0, 80, 80, 0,
                    75, 80, 50, 0, 0, 0, 70, 60, 75, 80, 70};

    //Here we change ownership of buildings. 9 - not for sale
    public static int[] bOwner = new int[] {9, 0, 0, 9, 0, 0, 9, 0, 0, 9, 0, 0, 9, 0, 0, 0, 9, 9, 9, 0, 0, 0, 0, 0};

    //When some player get Monopoly the field in this array will become true and accept upgrade
    public static boolean[] bReadyForUpgrade = new boolean[24];

    //Mark how many and which players are in this field.
    public static int[] bPHere = new int[24]; // 1 - p1 , 10 - p2, 100 - p3 sum of them



}
