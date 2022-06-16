import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Monopoly {

    static int curPlayer = 1;

    public static void main(String[] args) {

        MapArray.initializeMapValues(); //Once
        initializeFieldToRowColValues(); //Once
        setInflationField(); //Once

        MapArray.mapText[10][4] = plName[1];  //Once
        MapArray.mapText[10][5] = plName[2];
        MapArray.mapText[10][6] = plName[3];

        movePlayersToFields();
        ProPrintSettings.showMAP();

        curPlayer = 1; //Player One To Move

        while (!endGame)
        {
            System.out.print("Играч " + plName[curPlayer] + " изберете действие (r-roll dices)");
            String act = scn.nextLine();

            int d1=0, d2=0; //Dices (1-4) * Not (1-6) because of small map and big jail out chance

            if (act.equals("r"))
            {
                d1 = rollDice(); d2 = rollDice();
                System.out.printf("Хвърлихте %d + %d ",d1,d2);

                int newField = (plWhere[curPlayer] + d1  + d2) % 24;
                if (newField < plWhere[curPlayer]) {
                    refreshPlayerMoney(curPlayer, 200); //cross start
                    System.out.println("Пресякохте старта. Увеличихме сметката ви с " + crossStartBonus);
                }
                if (newField == 18) newField=19; //let go around jail
                String txt = "Играч " + plName[curPlayer] + " попада на поле " + bName[newField]; //show notice
                System.out.println(txt);
                int costOfBuild = getCostOfBuild(newField);
                if (bOwner[newField]==0 && plMoney[curPlayer]> costOfBuild){
                    System.out.print(bName[newField] + " струва " + costOfBuild + " Желаете ли да я закупите (y/n)" );
                    String act2 = scn.nextLine();
                    if (act2.equals("y"))
                    {
                        addBuildToPlayer(curPlayer, newField);
                        refreshPlayerMoney(curPlayer, - costOfBuild);
                        if (plOwnerList[curPlayer][0]==9)
                        {
                            endGame = true;
                            MapArray.mapStyle[8][4] = MapArray.CONGRATS63;
                            MapArray.mapText[8][4] = " ЧЕСТИТО! Играч " + plName[curPlayer]+ " спечели играта! ";
                        }

                    }
                }

                if (bOwner[newField]>0 && bOwner[newField]<4 && bOwner[newField]!=curPlayer)
                {
                    //let pay tax to owner
                    int tax = letPayTaxToOwner(curPlayer, bOwner[newField], newField);
                    System.out.println("Играч "+ plName[curPlayer]+ " заплати такса "
                            + tax+ " на играч "+ plName[bOwner[newField]]);
                }

                //Police field
                if (newField == 6) System.out.println(Institutions.goPolice(curPlayer));

                //If player is not in Jail update him field
                if (plWhere[curPlayer]!=18) plWhere[curPlayer] = newField; movePlayersToFields(); //working
            }

            ProPrintSettings.showMAP(); //working

            if (d1!=d2) {curPlayer++; if (curPlayer==4) curPlayer=1;} //When pair Go Again = More FUN
        }
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

    public static void movePlayersToFields()
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
        //Usage when we need to show list of ownership under player names
        String newText = txt.substring(1);
        double val = Double.valueOf(newText);
        val = val*10;
        int player = (int) val/10;
        int index = (int) val%10;

        if (plOwnerList[player][index]==0) return "|";  //empty elements dont be show as (0) START
        else return bName[plOwnerList[player][index]];
    }

     public static int getCostOfBuild(int field)
     {
         if (bOwner[field]>0) return 99999; //not for sale
         double res = bCost[field];

         //Buildings on inflation sectors cost more when inflation begins
         if (field==4||field==5) res = infFood * res;
         if (field==18) res = infWater * res;
         if (field==23) res = infEnergy * res;

         res = res*infGlobal;
         return (int) res;
     }


    public static void addBuildToPlayer(int pl, int field)
    {
        int ix = plOwnerList[pl][0]+1;  //index [pl][0] show us number of added items
        plOwnerList[pl][ix] = field;

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

        bOwner[field] = pl; // mark as bought
        plOwnerList[pl][0]+=1; // add in list of player buildings

        MapArray.mapStyle[mRow[field]-1][mCol[field]] = pl+32; //Change cell style to player color style
        MapArray.mapText[mRow[field]-1][mCol[field]] = "["+plName[pl]+"]"; //Will show us ownership in building field

    }

    public static int letPayTaxToOwner(int curPl, int ownerPl, int field)
    {
        double sum=0;
        sum += (bLevel[field]+1) * bTaxPL[field] * infGlobal;

        if (field==4||field==5) sum=sum*infFood;
        if (field==19) sum=sum*infWater;
        if (field==23) sum = sum*infEnergy;

        int isum = (int) sum;
        refreshPlayerMoney(curPl, - isum);
        refreshPlayerMoney(ownerPl, isum);
        return isum;
    }

    public static void refreshPlayerMoney(int player, int change)
    {
        plMoney[player] += change;
        MapArray.mapText[11][3+player] = String.valueOf(plMoney[player]); //for MAP
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
    public static double infGlobal = 1.03;
    public static double infEnergy = 1.05;
    public static double infWater = 1.06;
    public static double infFood = 1.12;
    public static int crossStartBonus = 200;

    //Player Arrays (Simulation of Player class)
    public static String[] plName = new String[] {"", "Smarto", "Greedy", "Fooly"};
    public static int[] plWhere = new int[] {0,0,0,0};
    public static int[] plMoney = new int[] {0,2000,2000,2000};
    public static int[][] plOwnerList = new int[4][9]; // [player] [building] /// [pl][0] - count elements
    public static boolean[] hadPrisonCard = new boolean[4]; //no one in beginning
    public static boolean[] hadHospitalCard = new boolean[4]; //no one in beginning

    //Buildings Arrays
    public static String[] bName = new String[]
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
    public static int[] bTaxPL = new int[] {0, 25, 25, 0, 50, 50, 0, 60, 60, 0, 80, 80, 0,
                    75, 80, 50, 0, 0, 0, 70, 60, 75, 80, 70};

    //Here we change ownership of buildings. 9 - not for sale
    public static int[] bOwner = new int[] {9, 0, 0, 9, 0, 0, 9, 0, 0, 9, 0, 0, 9, 0, 0, 0, 9, 9, 9, 0, 0, 0, 0, 0};

    //When some player get Monopoly the field in this array will become true and accept upgrade
    public static boolean[] bReadyForUpgrade = new boolean[24];

    //Mark how many and which players are in this field.
    public static int[] bPHere = new int[24]; // 1 - p1 , 10 - p2, 100 - p3 sum of them



}
