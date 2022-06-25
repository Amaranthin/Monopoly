import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Monopoly {

    // ============================= MONOPOLY =============================
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
            isPlayerFree = true;

            //PRISON ACTIONS
            checkForPrison(); //if player is on prison and dont have prison card isPlayerFree = false
            if (!isPlayerFree) doActionsFromPrison();

            //MAIN ACTIONS (When player is Free. * Must be after prison actions)
            if (isPlayerFree) doMainActions();

            //Prepare player for next move. But when pair happens Go Again = More FUN
            if (d1!=d2) {curPlayer++; if (curPlayer==4) curPlayer=1;}

            //After all actions is completed let show the new MAP
            ProPrintSettings.showMAP();
        }
    }

    public static void checkForPrison()
    {
        //Check for Player in Prison
        if (plWhere[curPlayer]==18)
        {
            if (!hadPrisonCard[curPlayer])
            {
                isPlayerFree = false;
            }
            else
            {
                System.out.println("����������� ������� �� �� �������� �� �������");
                hadPrisonCard[curPlayer] = false;
                isPlayerFree = true;
                Institutions.refreshPlayerCards();
            }
        }
    }

    public static void doActionsFromPrison()
    {
        String txtChooseAction = "";

        if (!isPlayerFree) {
            txtChooseAction += "����� " + plName[curPlayer] + " �������� ��������: " + actMercy + actPay200inPrisonAndRoll;

            System.out.print(txtChooseAction);
            String act = scn.next();

            //Paid Tax
            if (act.equalsIgnoreCase("P")) {
                Institutions.simplePayToInstitution(curPlayer, 200);
                System.out.println("���������� �� � ������� � ��� ��� ���������.");
                System.out.println();
                isPlayerFree = true;
            }

            //Wait for pair
            if (act.equalsIgnoreCase("M")) {
                d1 = rollDice();
                d2 = rollDice();
                if (d1 == d2) {      //succes
                    cntPair = 0;    //new beginning
                    System.out.println("������� �� ��������� ������� �� ����������� � ��� ����� ���������!");
                    isPlayerFree = true;
                } else {
                    System.out.println("����� �� �� ������ ��������. " +
                            "���� �� ������ �� ���������� ������� �� ��������� ���");
                }
            }
        }
    }

    public static void doMainActions()
    {
        String txtChooseAction = "";
        txtChooseAction = "����� " + plName[curPlayer] + " �������� ��������: " +
                actRoll + actUpgrade + actSell + actManipulations; // action1+action2+action3+action3m;
        if (cntPair==1) txtChooseAction += actPass;

        System.out.print(txtChooseAction);
        String act = scn.next();

        //Roll Dices
        if (act.equalsIgnoreCase("R")) doActionRollDices();

        //Upgrade something
        if (act.equalsIgnoreCase("U")) ;

        //Sell something
        if (act.equalsIgnoreCase("S")) ;

        //Pass
        if (act.equalsIgnoreCase("P"))
        {
            //Do Nothing
            d2=0; //next player to move because this option shows only when d1=d2
            cntPair = 0; //first move on next player
        }

        //MANIPULATION MENU
        if (act.equalsIgnoreCase("M")) showManipulations();

    }

    public static void showManipulations()
    {
        System.out.println("����������� �� ������:");
        System.out.println("1) ��������� ������ �� ��������� � 10%. �� �� ������ 200��");
        System.out.println("2) ��������� ������ �� ������ � 10%. �� �� ������ 120��");
        System.out.println("3) ��������� ������ �� ������� � 10%. �� �� ������ 200��");
        System.out.println("4) �������� ��������� �� ����� (-50�� �� ��������� �� ������)." +
                "�� �� ������ 350��");
        System.out.println("5) ��������� ���������. ������� �� ������ ����� �� ����� �� �������" +
                "\n� �� ���������� �� ��� ������. �� �� ������ 350��");
        System.out.println("6) ��������� ���������. ���������� �� ������� �� ����� �� �������,"+
                "�� �� �� ������������ ������� ������. �� �� ������ 200��");
        System.out.println("7) ��������� �������� �� �������� � +1. �� �� ������ 250��");
        System.out.println("8) �������� �������� �� �������� � -1. �� �� ������ 330��");

        System.out.print("������ ����� �:");

        doManipulationsChoice();
    }

    public static void doManipulationsChoice()
    {
        String ch = scn.next();

        switch(ch){
            case ("1") -> {
                infEnergy *= 1.10; setInflationField();
                Institutions.simplePayToInstitution(curPlayer, 200);
            }
            case ("2") -> {
                infWater *= 1.10; setInflationField();
                Institutions.simplePayToInstitution(curPlayer, 120);
            }
            case ("3") -> {
                infFood *= 1.10; setInflationField();
                Institutions.simplePayToInstitution(curPlayer, 200);
            }
            case ("4") -> {
                crossStartBonus += -50; MapArray.mapText[2][2] = "+" + crossStartBonus;
                Institutions.simplePayToInstitution(curPlayer, 350);
            }
            case ("5") -> {
                disabledPrisonCards = true;
                for (int x=1;x<=3;x++)
                {
                    hadPrisonCard[x]=false;
                }
                Institutions.refreshPlayerCards(); MapArray.mapText[28][2] = "[��������� �����]";
                Institutions.simplePayToInstitution(curPlayer, 350);
            }
            case ("6") -> {
                disabledPrisonCards = false;
                Institutions.simplePayToInstitution(curPlayer, 350);
                MapArray.mapText[28][2] = "[��������� �����]";
            }
            case ("7") -> {
                diceSizes += 1; //Increase dice size
                Institutions.simplePayToInstitution(curPlayer, 250);
                MapArray.mapText[4][2] = "������: 2x"+diceSizes;
            }
            case ("8") -> {
                if (diceSizes >2) {
                    diceSizes += - 1; //Decrease dice size
                    Institutions.simplePayToInstitution(curPlayer, 330);
                    MapArray.mapText[4][2] = "������: 2x"+diceSizes;
                }
                else {
                    Institutions.simplePayToInstitution(curPlayer, 100); //cant manipulate more but 100 tax
                    System.out.println("����������� ������ �� 2�2, � ��� ���������� 100�� ����� �� ����� �� �����������");
                }
            }
            default -> {}
        }
    }

    public static void doActionRollDices()
    {
        //Roll dices
            d1 = rollDice(); d2 = rollDice();
            if (d1==d2) cntPair++;                          //Count series of pairs
            else cntPair=0;                                 //When 1 pair appears > Go Again!

            if (cntPair==2) {

                boolean notice = false;
                if (hadPrisonCard[curPlayer]) {  //Clear full situation before showing MAP
                    hadPrisonCard[curPlayer] = false;
                    Institutions.refreshPlayerCards();
                    notice = true;
                }
                Institutions.goPrison(curPlayer);  //When 2 pairs appears > Go Prison!
                ProPrintSettings.showMAP();
                System.out.printf("��������� %d+%d! ����� �� ��������� �������! ������� � �������", d1, d2);
                System.out.println();
                if (notice) System.out.println("������ �� � � ������� �� �������� �� �������");

            }
            else checkActionsOnNewField();  //Normal move
    }

    public static void checkActionsOnNewField()  //Full Action
    {
        int newField = (plWhere[curPlayer] + d1  + d2) % 24;

        //Jump Prison   --- must be before showMAP --
        if (newField == 18) newField=19; //let go around Prison


        //When cross start. We need new variable because bonus notice must be after showing MAP
        boolean playerCrossStart = false;
        if (newField < plWhere[curPlayer]) {
            refreshPlayerMoney(curPlayer, crossStartBonus); //cross start refresh before show in MAP
            playerCrossStart = true;
        }

        //If player is not in Prison update him field
        plWhere[curPlayer] = newField; movePlayersToFields(); //Prison if (plWhere[curPlayer]!=18)

        ProPrintSettings.showMAP();

        if (playerCrossStart)
        {
            //just notice under MAP
            System.out.println("���������� ������. ���������� �������� �� � " + crossStartBonus);
        }

        System.out.printf("��������� %d + %d ",d1,d2);

        //Buy Actions on New Field
        String txt = " ����� " + plName[curPlayer] + " ������ �� ���� " + bName[newField]; //show notice
        System.out.println(txt);
        int costOfBuild = getCostOfBuild(newField);
        if (bOwner[newField]==0 && plMoney[curPlayer]> costOfBuild){
            System.out.print(bName[newField] + " ������ " + costOfBuild + " ������� �� �� � �������� (y/n)" );
            String act2 = scn.next();
            //System.out.println(); //todo maybe clear

            if (act2.equalsIgnoreCase("Y"))
            {
                //When this player buy 9 objects he win game!
                if (plOwnerList[curPlayer][0]==8)
                {
                    endGame = true;
                    MapArray.mapStyle[8][4] = MapArray.CONGRATS63;
                    MapArray.mapText[8][4] = " �������! ����� " + plName[curPlayer]+ " ������� ������! ";
                }
                else {
                    addBuildToPlayer(curPlayer, newField);
                    refreshPlayerMoney(curPlayer, -costOfBuild);
                }
            }
        }

        //When player put in other player field
        if (bOwner[newField]>0 && bOwner[newField]<4 && bOwner[newField]!=curPlayer)
        {
            if (!(newField==23||newField==19||newField==15)) {
                //let pay tax to owner
                letPayTaxToOwner(curPlayer, bOwner[newField], newField);
            }
            else {
                //Airport is bought
                if (newField == 15) Institutions.goAirport(curPlayer);
                //Water Company is bought
                if (newField == 19) Institutions.goWaterCompany(curPlayer);
                //Energy Company is bought
                if (newField == 23) Institutions.goEnergyCompany(curPlayer);
            }
        }

        //GO TO

        //University
        if (newField == 3 ) Institutions.goUniversity(curPlayer);

        //Police
        if (newField == 6) Institutions.goPolice(curPlayer);

        //Bank
        if (newField == 9) Institutions.goBank(curPlayer);

        //Borisova Garden
        if (newField == 12) Institutions.goBorGarden(curPlayer);

        //Hospital
        if (newField == 17) Institutions.goHospital(curPlayer);

        //Chance
        if (newField == 16) Institutions.goChance(curPlayer);

    }

    public static void movePlayersToFields()
    {
        for (int field=0; field<24; field++)
        {
            int row = mRow[field]; int col = mCol[field];

            String txt = "";
            boolean isSomePlayerHere = false;

            // I need this because special print players in same field with different colors
            if (field == plWhere[1]) {txt += "1"; isSomePlayerHere = true;}
            if (field == plWhere[2]) {txt += "2"; isSomePlayerHere = true;}
            if (field == plWhere[3]) {txt += "3"; isSomePlayerHere = true;}

            if (isSomePlayerHere) txt = "#" + txt; // this text must be formatet by parts; 1-3 players HERE

            updateTextArray(row, col, txt);
        }
    }

    public static void letPayTaxToOwner(int curPl, int ownerPl, int field)
    {
        if (plWhere[ownerPl]!=18) {
            double sum = 0;
            sum += (bLevel[field] + 1) * bTaxPL[field] * infGlobal;

            if (field == 6) sum = 150; //Card 10 from police > /every other paid 150 to us/
            if (field == 4 || field == 5) sum = sum * infFood;
            if (field == 19) sum = sum * infWater;
            if (field == 23) sum = sum * infEnergy;

            int isum = (int) sum;
            refreshPlayerMoney(curPl, -isum); //lose money
            refreshPlayerMoney(ownerPl, isum); //get money

            System.out.println("����� "+ plName[curPl]+ " ������� ����� "
                    + isum + " �� ����� "+ plName[ownerPl]);
        }
        else System.out.println("����� " + plName[ownerPl] + " � � ������� � �� ������� ����.");

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
        {   //find only position on new item
            for (int i=ix; i>=2; i--)
            {
                if (plOwnerList[pl][i]<plOwnerList[pl][i-1])
                {   //if new item is with low index let change the positions
                    int p = plOwnerList[pl][i];
                    plOwnerList[pl][i] = plOwnerList[pl][i-1];
                    plOwnerList[pl][i-1] = p;
                }
            }
        }

        bOwner[field] = pl; // mark as bought
        plOwnerList[pl][0]+=1; // rise up counter of player buildings

        MapArray.mapStyle[mRow[field]-1][mCol[field]] = pl+32; //Change cell style to player color style
        MapArray.mapText[mRow[field]-1][mCol[field]] = "["+plName[pl]+"]"; //Will show us ownership in building field

    }

    public static void setInflationField()
    {
        String txt = "";
        String txtWithRealLength = "";

        txt += "\u001B[0m"; //color normal
        txt += "��������: ";
        txt += convertInflationToPercent(infGlobal);

        txt +=  "\u001B[36m"; //color cyan
        txt += "����: ";
        txt += convertInflationToPercent(infWater);

        txt +=  "\u001B[31m"; //color pink
        txt += "�����: ";
        txt += convertInflationToPercent(infFood);

        txt +=  "\u001B[33m"; //color yellow
        txt += "�������: ";
        txt += convertInflationToPercent(infEnergy);

        txtWithRealLength += "��������: ";
        txtWithRealLength += convertInflationToPercent(infGlobal);
        txtWithRealLength += "����: ";
        txtWithRealLength += convertInflationToPercent(infWater);
        txtWithRealLength += "�����: ";
        txtWithRealLength += convertInflationToPercent(infFood);
        txtWithRealLength += "�������: ";
        txtWithRealLength += convertInflationToPercent(infEnergy);

        MapArray.mapText[23][4] = txt;  //colored text
        MapArray.mapText[0][0] =  txtWithRealLength;
    }

    public static void refreshPlayerMoney(int player, int change)
    {
        plMoney[player] += change;
        MapArray.mapText[11][3+player] = String.valueOf(plMoney[player]); //for MAP
    }

    public static int rollDice()  //Just Dice
    {
        Random x = new Random();
        int dice = x.nextInt(diceSizes)+1;
        return dice;
    }

    public static String convertInflationToPercent(double inf)
    {
        double newInf = inf*100;
        int perc = (int) newInf - 100;
        return String.valueOf(perc)+"% ";
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
        mRow[11] = 25; mCol[11]=8; //Inter Continental Hotel
        mRow[12] = 29; mCol[12]=8; //Borisova Garden
        mRow[13] = 29; mCol[13]=7; //NDK
        mRow[14] = 29; mCol[14]=6; //Sofia Mall
        mRow[15] = 29; mCol[15]=5; //Airport
        mRow[16] = 29; mCol[16]=4; //Chance
        mRow[17] = 29; mCol[17]=3; //Hospital
        mRow[18] = 29; mCol[18]=2; //Prison
        mRow[19] = 25; mCol[19]=2; //Water Company
        mRow[20] = 21; mCol[20]=2; //Chaos Group
        mRow[21] = 17; mCol[21]=2; //Global Services
        mRow[22] = 13; mCol[22]=2; //Buildings Company
        mRow[23] = 9; mCol[23]=2; //Energy Company
    }

    //Colors used in actions
    static String pink = "\u001B[31m", normal = "\u001B[0m";

    //Menu === Player Actions
    static String actRoll = "("+pink+"R"+normal+")oll dices; ";
    static String actUpgrade = "("+pink+"U"+normal+")pgrade something; ";
    static String actSell = "("+pink+"S"+normal+")ell something ";
    static String actPass =  "("+pink+"P"+normal+")ass! (Prevention from next pair and go on Prison) ";
    static String actManipulations = "("+pink+"M"+normal+")anipulations ";

    static String actMercy = "Beg for ("+pink+"M"+normal+")ercy; "; //in Prison
    static String actPay200inPrisonAndRoll = "("+pink+"P"+normal+")ay Tax 200 and roll dices; "; //in Prison

    //Scanner
    static Scanner scn = new Scanner(System.in);

    //Player to move
    static int curPlayer = 1;

    //Is current Player free or not
    static boolean isPlayerFree;

    //Dices
    static int d1=0, d2=0;

    //count series of pairs for current player
    static int cntPair = 0;

    //The real rows where shows level and owner // mrow = player on field
    public static int[] mRow = new int[24];
    public static int[] mCol = new int[24];

    //Continue the game while this is false
    public static boolean endGame = false;

    // === PARAMETERS IN THIS FRAME CAN BE MANIPULATED FROM PLAYERS AGAINST MONEY ==========================
    public static int crossStartBonus = 250;
    public static int diceSizes = 3;
    public static boolean disabledPrisonCards = false; //disable using of prison cards

    //Global inflation variables
    public static double infGlobal = 1.00;
    public static double infEnergy = 1.00;
    public static double infWater = 1.00;
    public static double infFood = 1.00;
    //======================================================================================================

    //Player Arrays (Simulation of Player class)
    public static String[] plName = new String[] {"", "Smarto", "Greedy", "Fooly"};
    public static int[] plWhere = new int[] {0,0,0,0}; //Current position of player
    public static int[] plMoney = new int[] {0,2000,2000,2000};
    public static int[][] plOwnerList = new int[4][9]; // [player] [building] /// [pl][0] - count elements
    public static boolean[] hadPrisonCard = new boolean[4]; //no one in beginning
    public static boolean[] hadHospitalCard = new boolean[4]; //no one in beginning
    public static boolean[] hadUniversityCard = new boolean[4]; //no one in beginning


    //Buildings Arrays
    public static String[] bName = new String[]
            {"(0) �����", "(1) �� ����", "(2) �� ������", "(3) �����������", "(4) Happy B&G", "(5) McDonnalds", "(6) �������",
                    "(7) �������", "(8) ���", "(9) �����", "(10) Grand H.", "(11) Inter C.", "(12) �������� �������",
                    "(13) ���", "(14) Sofia Mall", "(15) ������", "(16) ����", "(17) �������", "(18) ������",
                    "(19) ���", "(20) Chaos Group", "(21) Global Serv.", "(22) Building C.", "(23) Enegry C."};

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




}
