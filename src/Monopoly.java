import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Monopoly {

    // ============================= MONOPOLY =============================
    public static void main(String[] args) {

        MapArray.initializeMapValues(); //Once
        initializeFieldToRowColValues(); //Once
        setInflationField(); //Once
        setPlayersAlive(); //By default all are alive. But in inputPlayerNames method some from them may be mark as empty slot
        inputPlayerNames();
        showPlayerNames(); //Once in center of MAP
        movePlayersToFields();

        MapArray.mapText[4][2] = "зарове: 2x" + diceSizes + "";
        ProPrint.showMAP();

        curPlayer = 1; //Player One To Move

        //GAME
        while (!endGame)
        {
            isPlayerFree = true;

            //PRISON ACTIONS
            checkForPrison(); //if player is on prison and dont have prison card isPlayerFree = false
            if (!isPlayerFree) doActionsFromPrison();

            //MAIN ACTIONS (When player is Free)
            if (isPlayerFree)
            {
                boolean finAction = false;
                while(!finAction) {
                    finAction = doMainActions();  //need from correct action to continue
                };
            }

            //Prepare player for next move. But when pair happens Go Again = More FUN
            if (d1!=d2 || !isPlayerAlive[curPlayer])
            {
                curPlayer++;
                if (curPlayer==4) curPlayer=1;
                if (!isPlayerAlive[curPlayer]) curPlayer++; //if player is not alive
                if (curPlayer==4) curPlayer=1;
            }

            //After all actions is completed let show the new MAP
            ProPrint.showMAP();
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
                System.out.println("Използвахте картата си за излизане от затвора");
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
            txtChooseAction += "Играч " + plName[curPlayer] + " изберете действие: " + actMercy;

            if (plMoney[curPlayer]> 200) txtChooseAction += actPay200inPrisonAndRoll;

            System.out.print(txtChooseAction);
            String act = scn.next();

            //Paid Tax
            if (act.equalsIgnoreCase("P")) {
                if (plMoney[curPlayer]> 200) {
                    Institutions.simplePayToInstitution(curPlayer, 200);
                    System.out.println("Гаранцията ви е платена и вие сте освободен.");
                    System.out.println();
                    isPlayerFree = true;
                }
                else {
                    System.out.println("Нямате необходимите пари за да платите гаранцията си!");
                }
            }

            //Wait for pair
            if (act.equalsIgnoreCase("M")) {
                d1 = rollDice();
                d2 = rollDice();

                if (d1 == d2)
                {   //succes
                    cntPair = 0;    //new beginning
                    System.out.println("Молбите ви докоснаха сърцата на управниците и вие бяхте освободен!");
                    isPlayerFree = true;
                }
                else
                {
                    System.out.println("Никой не ви обърна внимание. " +
                            "Може би трябва да развържете кесията си следващия път");
                }
            }
        }
    }

    public static boolean doMainActions()
    {
        String txtChooseAction = "";
        txtChooseAction = "Играч " + plName[curPlayer] + " изберете действие: " +
                actRoll + actUpgrade + actSell + actManipulations;

        if (cntPair==1) txtChooseAction += actPass; //add action prevent from prison

        System.out.print(txtChooseAction);
        String act = scn.next();

        boolean correctChoice = false ;
        act = act.toUpperCase();

        switch(act) {
            //Roll Dices
            case "R" -> {doActionRollDices(); correctChoice = true;}
            //Upgrade something
            case "U" ->  {upgradeBuilding(); correctChoice = true;}
            //Sell something
            case "S" -> {sellBuilding(); correctChoice = true;}
            //Pass
            case "P" ->  //Do Nothing > Prevent from Prison (if another pair happens)
                {
                    d2=-1; //next player to move because this option shows only when d1=d2
                    cntPair = 0; //first move on next player
                    correctChoice = true;
                }
            //MANIPULATION MENU
            case "M" -> {showManipulations(); correctChoice = true;}
        }
        return correctChoice;
    }

    public static void upgradeBuilding()
    {
        int br=0; //count just our buildings with monopoly
        int[] buildingIxReadyToUpgrade = new int[9]; //This Array contains index of buildings with monopoly

        if (plOwnerList[curPlayer][0]>0){
            for (int ix=1; ix<=plOwnerList[curPlayer][0]; ix++)
            {
                if (bLevel[plOwnerList[curPlayer][ix]]>0)   //If level on buildings on Player Sorted Array > 0
                {                                           //When player create monopoly this level set from 0 to 1
                    if (br==0)
                    {
                        System.out.println("Изберете сграда за ъпгрейд:");
                        System.out.println("0) Изход");
                    }

                    br++;
                    System.out.println(br + ") " + bName[plOwnerList[curPlayer][ix]] +
                            " за " + bUpgrade[plOwnerList[curPlayer][ix]]);
                    buildingIxReadyToUpgrade[br] = plOwnerList[curPlayer][ix]; //Building Index on position br in menu
                }
            }

            int nSell=-1; //number of chosen building in sorted list
            while ((nSell<0 || nSell>br) && br>0) {
                System.out.print("Вашият избор е:"); nSell = scn.nextInt();
            }

            //UPGRADE
            if (br>0)
            {
                if (bLevel[buildingIxReadyToUpgrade[nSell]]<4)
                {
                    bLevel[buildingIxReadyToUpgrade[nSell]]++;
                    refreshPlayerMoney(curPlayer, - bUpgrade[buildingIxReadyToUpgrade[nSell]]);
                }
                else System.out.println("Вие сте достигнали максималното 4-то ниво");
            }
            else
            {
                System.out.println("Още сте твърде млад за такива сложни дейности");
            }
        }
    }

    public static void sellBuilding()
    {
        if (plOwnerList[curPlayer][0]>0){
            System.out.println("Коя от вашите собствености искате да продадете:");
            for (int ix=1; ix<=plOwnerList[curPlayer][0]; ix++)
            {
                System.out.println(ix + ")" + bName[plOwnerList[curPlayer][ix]] + " за "
                        + (int) (bCost[plOwnerList[curPlayer][ix]]/2+100*(1+bLevel[plOwnerList[curPlayer][ix]])));
                        //COST HALF PRICE and 100*(Upgrade Level+1) (=add 100-500)
            }

            int nSell=0; //number of chosen building in sorted list
            while (nSell==0 || nSell>plOwnerList[curPlayer][0]) {
                System.out.print("Вашият избор е:"); nSell = scn.nextInt();
            }

            //SELL
            refreshPlayerMoney(curPlayer, (int) (bCost[plOwnerList[curPlayer][nSell]]/2+100*(1+bLevel[plOwnerList[curPlayer][nSell]])));
            removeBuildingFromPlayer(nSell, curPlayer, plOwnerList[curPlayer][nSell]);
        }
        else
        {
            System.out.println("Вие не разполагате с никаква собственост за продажба!");
        }
    }

    public static void showManipulations()
    {
        System.out.println("Манипулация на пазара:");
        if (plMoney[curPlayer]>= 200) System.out.println("1) Увеличете цената на Енергията с 10%. Ще ви струва 200лв");
        if (plMoney[curPlayer]>= 120) System.out.println("2) Увеличете цената на Водата с 10%. Ше ви струва 120лв");
        if (plMoney[curPlayer]>= 200) System.out.println("3) Увеличете цената на Храните с 10%. Ще ви струва 200лв");
        if (plMoney[curPlayer]>= 350) System.out.println("4) Занижете стандарта на живот (-50лв за пресичане на старта)." +
                "Ще ви струва 350лв");
        if (plMoney[curPlayer]>= 150) System.out.println("5) Подкупете полицията. Отнемат се всички карти за изход от затвора" +
                "\nи се забраняват до нов подкуп. Ще ви струва 150лв");
        if (plMoney[curPlayer]>= 100) System.out.println("6) Подкупете полицията. Разрешават се картите за изход от затвора,"+
                "но не се възтановяват старите такива. Ще ви струва 100лв");
        if (plMoney[curPlayer]>= 150) System.out.println("7) Увеличете страните на заровете с +1. Ще ви струва 150лв");
        if (plMoney[curPlayer]>= 250) System.out.println("8) Намалете страните на заровете с -1. Ще ви струва 250лв");

        System.out.print("Вашият избор е:");

        doManipulationsChoice();
    }

    public static void doManipulationsChoice()
    {
        String ch = scn.next();

        switch(ch){
            case ("1") -> {
                if (plMoney[curPlayer]>= 200) {
                    infEnergy *= 1.10; setInflationField();
                    Institutions.simplePayToInstitution(curPlayer, 200);
                }
            }
            case ("2") -> {
                if (plMoney[curPlayer]>= 120) {
                    infWater *= 1.10;
                    setInflationField();
                    Institutions.simplePayToInstitution(curPlayer, 120);
                }
            }
            case ("3") -> {
                if (plMoney[curPlayer]>= 200) {
                    infFood *= 1.10;
                    setInflationField();
                    Institutions.simplePayToInstitution(curPlayer, 200);
                }
            }
            case ("4") -> {
                if (plMoney[curPlayer]>= 350) {
                    crossStartBonus += -50;
                    MapArray.mapText[2][2] = "+" + crossStartBonus;
                    Institutions.simplePayToInstitution(curPlayer, 350);
                }
            }
            case ("5") -> {
                if (plMoney[curPlayer]>= 150) {
                    disabledPrisonCards = true;
                    for (int x = 1; x <= 3; x++) {
                        hadPrisonCard[x] = false;
                    }
                    Institutions.refreshPlayerCards();
                    MapArray.mapText[28][2] = "[забранени карти]";
                    Institutions.simplePayToInstitution(curPlayer, 150);
                }
            }
            case ("6") -> {
                if (plMoney[curPlayer]>= 100) {
                    disabledPrisonCards = false;
                    Institutions.simplePayToInstitution(curPlayer, 100);
                    MapArray.mapText[28][2] = "[разрешени карти]";
                }
            }
            case ("7") -> {
                if (plMoney[curPlayer]>= 150) {
                    diceSizes += 1; //Increase dice size
                    Institutions.simplePayToInstitution(curPlayer, 150);
                    MapArray.mapText[4][2] = "зарове: 2x" + diceSizes;
                }
            }
            case ("8") -> {

                if (diceSizes >2) {
                    if (plMoney[curPlayer]>= 250) {
                        diceSizes += -1; //Decrease dice size
                        Institutions.simplePayToInstitution(curPlayer, 250);
                        MapArray.mapText[4][2] = "зарове: 2x" + diceSizes;
                    }
                }
                else {
                    Institutions.simplePayToInstitution(curPlayer, 100); //cant manipulate more but 100 tax
                    System.out.println("Минималните страни са 2х2, а вие заплатихте 100лв глоба за опита за манипулация");
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
                ProPrint.showMAP();
                System.out.printf("Хвърлихте %d+%d! Глоба за превишена скорост! Отивате в затвора", d1, d2);
                System.out.println();
                if (notice) System.out.println("Отнета ви е и картата за излизане от затвора");

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

        ProPrint.showMAP();

        if (playerCrossStart)
        {
            //just notice under MAP
            System.out.println("Пресякохте старта. Увеличихме сметката ви с " + crossStartBonus);
        }

        System.out.printf("Хвърлихте %d + %d ",d1,d2);

        //Buy Actions on New Field
        String txt = " Играч " + plName[curPlayer] + " попада на поле " + bName[newField]; //show notice
        System.out.println(txt);
        int costOfBuild = getCostOfBuild(newField);
        if (bOwner[newField]==0 && plMoney[curPlayer]> costOfBuild){
            System.out.print(bName[newField] + " струва " + costOfBuild + " Желаете ли да я закупите (y/n)" );
            String act2 = scn.next();
            //System.out.println(); //todo maybe clear

            if (act2.equalsIgnoreCase("Y"))
            {
                //When this player buy 9 objects he win game!
                if (plOwnerList[curPlayer][0]==8)
                {
                    congratsWinner(curPlayer);
                }
                else {
                    addBuildingToPlayer(curPlayer, newField);
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

            sum += (bLevel[field] + 1) * bTaxPL[field] * infGlobal; //when tax is from building

            if (field == 6) sum = 150; //Card 10 from Police > /each other paid 150 to us/
            if (field == 4 || field == 5) sum = sum * infFood;  //food fields
            if (field == 19) sum = sum * infWater; //Water Company field
            if (field == 23) sum = sum * infEnergy; //Energy Company field

            int isum = (int) sum;
            refreshPlayerMoney(curPl, -isum); //lose money
            refreshPlayerMoney(ownerPl, isum); //get money

            System.out.println("Играч "+ plName[curPl]+ " заплати такса "
                    + isum + " на играч "+ plName[ownerPl]);
        }
        else System.out.println("Играч " + plName[ownerPl] + " е в затвора и не плащате нищо.");
    }

    public static void letPayTaxToOwner(int curPl, int ownerPl, String resource)
    {
        //PREDEFINITION. 2 usages on this method is only from sector CHANCE

        //No prison check here, because when our position is on sector chance we obviously are free :)
        double sum = 0;

        if ( resource.equals("energy")) sum = infEnergy*100; //Card 9 from Chance > every one to us
        if ( resource.equals("water")) sum = infWater*50; //Card 10 from Chance > every one to us

        int isum = (int) sum;
        refreshPlayerMoney(curPl, -isum); //lose money
        refreshPlayerMoney(ownerPl, isum); //get money

        System.out.println("Играч "+ plName[curPl]+ " заплати такса "
                + isum + " на играч "+ plName[ownerPl]);
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


    public static void addBuildingToPlayer(int pl, int field)
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
        MapArray.mapText[mRow[field]-1][mCol[field]] = "%"+pl; //set player like owner with parse symbol $

        checkForNewMonopoly(pl, field);
    }

    public static void removeBuildingFromPlayer(int ix, int pl, int field)
    {
        //Player Choose Ix as Option in Sell List

        plOwnerList[pl][ix] = 0;  //set IX as 0

        //Update Array List
        //Change positions of all items after it. 0 bubble go down
        for (int i=ix; i<plOwnerList[pl][0]; i++)
        {
            if (plOwnerList[pl][i]<plOwnerList[pl][i+1]) //ALWAYS MUST BE TRUE but for every case
            {       //move deleted item down
                    int p = plOwnerList[pl][i];
                    plOwnerList[pl][i] = plOwnerList[pl][i+1];
                    plOwnerList[pl][i+1] = p;
            }
        }

        bOwner[field] = 0; // now as free
        plOwnerList[pl][0]+=-1; // rise up counter of player buildings

        //Erase possible Monopoly
        int sector = bMPSplit[field]; boolean isErase = false;
        for (int f=1; f<24; f++)
        {
            if (bOwner[f]==pl && bMPSplit[f]==sector)
            {
                if (bLevel[f]>0) isErase = true;

                bLevel[f]=0; // erase all upgrades on each field on this sector
            }
        }

        if (isErase) System.out.println("Съжаляваме, но вие разрушихте вашия монопол в този сектор!");

        MapArray.mapStyle[mRow[field]-1][mCol[field]] =  MapArray.mapStyle[mRow[field]-2][mCol[field]] ; //Back to original style
        MapArray.mapText[mRow[field]-1][mCol[field]] = ""; //clear label notice for ownership

        System.out.println("Продажбата премина успешно. Парите са захранени във вашата сметка");
    }

    public static void checkForNewMonopoly(int player, int field)
    {
        int searchFor = bMPSplit[field];
        int cnt = 0; //counter

        if (searchFor>0)
        {
            boolean isNewMonopoly = false;
            for (int ix=1;ix<24; ix++)
            {
                if (bOwner[ix]==player && bMPSplit[ix]==searchFor) cnt++;
            }

            if (cnt==2){
                if (searchFor!=6) {bHadMonopoly[searchFor] = true; isNewMonopoly = true;}
            }

            if (cnt>2) {bHadMonopoly[6] = true; isNewMonopoly = true;}

            if (isNewMonopoly)
            {
                System.out.println("Честито! Играч " + plName[player] +
                        " успешно сглоби Монополи в сектор " + mpSector[searchFor]);
                //Set level on these buildings to ONE
                for (int ix=1;ix<24; ix++)
                {
                    if (bMPSplit[ix] == searchFor) bLevel[ix] = 1;
                }
            }
        }

    }

    public static void refreshPlayerMoney(int player, int change)
    {
        plMoney[player] += change;
        MapArray.mapText[11][3+player] = String.valueOf(plMoney[player]); //for MAP
        if (plMoney[player] < 0)
        {
            isPlayerAlive[player] = false;
            clearOwnershipsOnDeathPlayer(player);
        }
    }

    public static void clearOwnershipsOnDeathPlayer(int player)
    {
        for (int f=1; f<24; f++)
        {
            if (bOwner[f]==player)
            {
                bOwner[f] = 0;
                MapArray.mapText[mRow[f]-1][mCol[f]] = ""; //clear labels with player name on buildings
                MapArray.mapStyle[mRow[f]-1][mCol[f]] =  MapArray.mapStyle[mRow[f]-2][mCol[f]]; //get style from row up
            }
        }

        plOwnerList[player][0] = 0; //index [0] = count of buildings > become 0
        for (int ix=1;ix<9;ix++)
        {
            plOwnerList[player][ix] = 0; //and all of them too become 0 (as free fields)
        }

        //check for winner
        int cntAlivePls = 0; int lastAliveIndex = 0;
        for (int ix=1;ix<4;ix++)
        {
            if (isPlayerAlive[ix])
            {
                cntAlivePls++;
                lastAliveIndex = ix;
            }
        }

        if (cntAlivePls == 1)
        {
            congratsWinner(lastAliveIndex);
        }
    }

    public static String convertInflationToPercent(double inf)
    {
        double newInf = inf*100;
        int perc = (int) newInf - 100;
        return String.valueOf(perc)+"% ";
    }

    public static void setPlayersAlive()
    {
        isPlayerAlive[1] = true;
        isPlayerAlive[2] = true;
        isPlayerAlive[3] = true;
    }

    public static void inputPlayerNames()
    {
        int br=1;
        while (br<=3)
        {
            System.out.println("Въведете име за играч #"+br+" (max 10symbols). Или 0 - за празен слот:");
            String name = scn.nextLine();
            if (name.length()>0 && name.length()<11)
            {   //correct input
                if(name.equals("0")) {isPlayerAlive[br] = false; plName[br] ="";}
                else plName[br] = name;

                br++;
            }
        }
    }

    public static void showPlayerNames()
    {
        MapArray.mapText[10][4] = plName[1];
        MapArray.mapText[10][5] = plName[2];
        MapArray.mapText[10][6] = plName[3];
    }

    public static int rollDice()  //Just Dice
    {
        Random x = new Random();
        int dice = x.nextInt(diceSizes)+1;
        return dice;
    }

    public static void congratsWinner(int player)
    {
        endGame = true;
        MapArray.mapStyle[8][4] = MapArray.CONGRATS63;
        MapArray.mapText[8][4] = " ЧЕСТИТО! Играч " + plName[player]+ " спечели играта! ";
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


    public static void initializeFieldToRowColValues()
    {
        //mRow is show players moves
        //mRow -1 shows ownership and upgrade level
        //mRow -2 shows building name

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

    //Live or Death players
    static boolean[] isPlayerAlive = new boolean[4];

    //Dices
    static int d1=-3, d2=-2;  //it is better for first move (not equal and not real)

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
            {"(0) СТАРТ", "(1) ФК ЦСКА", "(2) ФК Левски", "(3) Унивеститет", "(4) Happy B&G", "(5) McDonnalds", "(6) ПОЛИЦИЯ",
                    "(7) Витошка", "(8) ЦУМ", "(9) БАНКА", "(10) Grand H.", "(11) Inter C.", "(12) БОРИСОВА ГРАДИНА",
                    "(13) НДК", "(14) Sofia Mall", "(15) Летище", "(16) ШАНС", "(17) Болница", "(18) ЗАТВОР",
                    "(19) ВИК", "(20) Chaos Group", "(21) Global Serv.", "(22) Building C.", "(23) Enegry C."};

    //Split monopoly fields  //Like Fotons :)
    public static int[] bMPSplit = new int[] {-1,1,1,-1,2,2,-1,3,3,0,4,4,-1,5,5,0,0,-1,-1,0,6,6,6,0};

    //Prices without any inflation
    public static int[] bCost = new int[] {0, 130, 130, 0, 230, 230, 0, 270, 270, 0, 330, 310, 0,
                    300, 330, 200, 0, 0, 0, 333, 250, 300, 350, 400};

    //Upgrade cost without any inflation
    public static int[] bUpgrade = new int[] {0, 75, 75, 0, 125, 125, 0, 80, 80, 0, 175, 175, 0,
                    135, 150, 0, 0, 0, 0, 0, 125, 150, 200, 0};

    //Levels after upgrade
    public static int[] bLevel = new int[24];

    //Tax per Level
    public static int[] bTaxPL = new int[] {0, 35, 35, 0, 50, 50, 0, 60, 60, 0, 80, 80, 0,
                    75, 80, 50, 0, 0, 0, 70, 60, 75, 80, 70};

    //Here we change ownership of buildings. 9 - not for sale
    public static int[] bOwner = new int[] {9, 0, 0, 9, 0, 0, 9, 0, 0, 9, 0, 0, 9, 0, 0, 0, 9, 9, 9, 0, 0, 0, 0, 0};

    //When some player get Monopoly mark this sector as monopoly
    public static boolean[] bHadMonopoly = new boolean[7];

    //Monopoly Sectors
    public static String[] mpSector = new String[] {"", "Спорт", "Хранене", "Мода", "Хотели", "Развлечения", "Бизнес"};




}
