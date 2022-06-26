public class ProPrint {


    //MAP Frames
    static String newLine = "=========================================================================================================================================================";
    static String newLine2 = "---------------------------------------------------------------------------------------------------------------------------------------------------------";


    //Global Parameters for proPrint()
    public static String bgColor;
    public static String mainColor;
    public static String frameColor;
    public static int inWeight;
    public static String align;
    public static String frameSymbol;

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
                    if (c=='#') partPrintPlayersOrCards(MapArray.mapText[row][col], row, col );
                    else if (c=='@')
                    {
                        String buildName = Monopoly.getBuildFromOwnerList(MapArray.mapText[row][col]);
                        printCentered(buildName, buildName.length(), 21);
                    }
                    else if (c=='%')
                    {
                        showPlayerNameLevelAndTax(MapArray.mapText[row][col], row, col);
                    }

                    //else if (ifSingleSector(row, col)) printWithSameBackground(row, col); //AirC, WaterC, EnergyC todo
                    else if (row==23 && col==4) printCentered(MapArray.mapText[23][4], MapArray.mapText[0][0].length(), 63);
                    else proPrint(MapArray.mapStyle[row][col], MapArray.mapText[row][col]);
                }
                else proPrint(MapArray.mapStyle[row][col], MapArray.mapText[row][col]);
            }

            if (row==5 || row==25) {System.out.println(); System.out.println(newLine2);}
            else System.out.println();

            if (row==30)  {System.out.println(newLine);}
        }
    }

    public static void partPrintPlayersOrCards(String txt, int row, int col)
    {   //when 2 or more players is in same field we need print them in different style
        String nText = txt.substring(1);

        ProPrint st = new ProPrint();
        int cntPlayersOrCardsHere = 0;

        String fullstring = "";
        for (int x=1; x<txt.length();x++)
        {
            char c = txt.charAt(x);

            switch(c)
            {
                case '1' -> {
                    ProPrint.changeStyle(MapArray.PLAYER1_3);
                    fullstring += ProPrint.cText(st,"[1]");
                    cntPlayersOrCardsHere++;
                }
                case '2' -> {
                    ProPrint.changeStyle(MapArray.PLAYER2_3);
                    fullstring += ProPrint.cText(st,"[2]");
                    cntPlayersOrCardsHere++;
                }
                case '3' -> {
                    ProPrint.changeStyle(MapArray.PLAYER3_3);
                    fullstring += ProPrint.cText(st,"[3]");
                    cntPlayersOrCardsHere++;
                }
                case 'P' -> {
                    ProPrint.changeStyle(MapArray.GREEN3);
                    fullstring += ProPrint.cText(st,"[P]");
                    cntPlayersOrCardsHere++;
                }
                case 'H' -> {
                    ProPrint.changeStyle(MapArray.RED3);
                    fullstring += ProPrint.cText(st,"[H]");
                    cntPlayersOrCardsHere++;
                }
                case 'U' -> {
                    ProPrint.changeStyle(MapArray.YELLOW3);
                    fullstring += ProPrint.cText(st,"[U]");
                    cntPlayersOrCardsHere++;
                }
            }
        }

        //Don't use global styles for this field because each player or card must be in own color
        changeStyle(MapArray.mapStyle[row][col]);
        proPrint(0,"     "); //use style on cell for 5 spaces before
        printCentered(fullstring,cntPlayersOrCardsHere*3,11); //inWidth = 21 - left spaces - right spaces
        proPrint(0,"     "); //use style on cell for 5 spaces after
    }

    public static void showPlayerNameLevelAndTax(String txt, int row, int col)
    {

        int pNum = Integer.valueOf(txt.substring(1));

        changeStyle(MapArray.mapStyle[row][col]);
        String fullstring = "["+Monopoly.plName[pNum]+"]";

        int br = 0 ; String c = "";
        int field = getFieldFromRowCol(row, col);

        while (br<Monopoly.bLevel[field])
        {
            c +="*"; br++;
        }

        fullstring += c;

        double sum = 0; // sum from building level
        sum += (Monopoly.bLevel[field] + 1) * Monopoly.bTaxPL[field] * Monopoly.infGlobal;
        if (field == 4 || field == 5) sum = sum * Monopoly.infFood;  //food fields
        if (field == 19) sum = sum * Monopoly.infWater; //Water Company field
        if (field == 23) sum = sum * Monopoly.infEnergy; //Energy Company field

        fullstring += " "+ (int) sum;
        proPrint(MapArray.mapStyle[row][col],fullstring);
    }

    public static int getFieldFromRowCol(int row, int col)
    {
        if (row == 4 && col==2) return 0; //START
        if (row == 4 && col==3) return 1; //FC CSKA
        if (row == 4 && col==4) return 2; //FC Levski
        if (row == 4 && col==5) return 3; //Universitat *********************
        if (row == 4 && col==6) return 4; //Happy B&G
        if (row == 4 && col==7) return 5; //McDonnalds
        if (row == 4 && col==8) return 6; //POLICE **************************

        if (row == 8 && col==8) return 7; //str Vitoshka
        if (row == 12 && col==8) return 8; //CUM
        if (row == 16 && col==8) return 9; //BANK ***************************
        if (row == 20 && col==8) return 10; //Grand Hotel
        if (row == 24 && col==8) return 11; //Inter Continental Hotel
        if (row == 28 && col==8) return 12; //Borisova Garden ***************

        if (row == 28 && col==7) return 13; //NDK
        if (row == 28 && col==6) return 14; //Sofia Mall
        if (row == 28 && col==5) return 15; //Airport
        if (row == 28 && col==4) return 16; //Chance ***********************
        if (row == 28 && col==3) return 17; //Hospital *********************
        if (row == 28 && col==2) return 18; //Prison ***********************

        if (row == 24 && col==2) return 19; //Water Company
        if (row == 20 && col==2) return 20; //Chaos Group
        if (row == 16 && col==2) return 21; //Global Services
        if (row == 12 && col==2) return 22; //Buildings Company
        if (row == 8 && col==2) return 23; //Energy Company
        return 0;
    }

    public static boolean ifSingleSector(int row, int col)
    { //todo
        if (row==8 && col==2) return true;  //Energy Company
        if (row==24 && col==2) return true; //Water Company
        if (row==28 && col==5) return true; //Airport
        return false;
    }

    public static void printWithSameBackground(int row, int col)
    { //todo
        changeStyle(MapArray.mapStyle[row][col]);
        proPrint(0,"     "); //use style on cell for 5 spaces before
        printCentered(MapArray.mapText[row][col],MapArray.mapText[row][col].length(),11); //inWidth = 21 - left spaces - right spaces
        proPrint(0,"     "); //use style on cell for 5 spaces after
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

    //Print text using specific style:
    //cell length, align, maincolor, background color, background symbol, background symbol color
    public static void proPrint(int style, String txt) {

        ProPrint st = new ProPrint();
        changeStyle(style);

        String res;
        switch (st.align) {
            case "left" -> res = ProPrint.lText(st, txt);
            case "center" -> res = ProPrint.cText(st, txt);
            case "right" -> res = ProPrint.rText(st, txt);
            default -> res = "";
        }
        System.out.print(res);
    }

    public static String cText(ProPrint st, String txt) {
        int n = txt.length();
        if (st.inWeight < n) return "err in cText parameter";

        int diff = st.inWeight - n;
        int leftSpaces = diff / 2;
        int rightSpaces = diff - leftSpaces;

        StringBuilder resText = new StringBuilder();
        resText.append(addBackground(st.bgColor));

        for (int x = 1; x <= leftSpaces; x++) {
            if (x == 1) resText.append(addColor(st.frameColor));
            resText.append(st.frameSymbol);
        }

        resText.append(addColor(st.mainColor) + txt);

        for (int x = 1; x <= rightSpaces; x++) {
            if (x == 1) resText.append(addColor(st.frameColor));
            resText.append(st.frameSymbol);
        }
        resText.append("\u001B[0m"); //break this style

        return resText.toString();
    }

    public static String rText(ProPrint st, String txt) {
        int n = txt.length();
        if (n > st.inWeight) return "err in rText parameters";

        int leftSpaces = st.inWeight - n;
        StringBuilder resText = new StringBuilder();
        resText.append(addBackground(st.bgColor));
        for (int x = 1; x <= leftSpaces; x++) {
            if (x == 1) resText.append(addColor(st.frameColor));
            resText.append(st.frameSymbol);
        }
        resText.append(addColor(st.mainColor) + txt);
        resText.append("\u001B[0m"); //да прекъсне бекграунда
        return resText.toString();
    }

    public static String lText(ProPrint st, String txt) {
        int n = txt.length();
        if (n > st.inWeight) return "Err";

        int rightSpaces = st.inWeight - n;
        StringBuilder resText = new StringBuilder();
        resText.append(addBackground(st.bgColor));
        resText.append(addColor(st.mainColor) + txt);
        for (int x = 1; x <= rightSpaces; x++) {
            if (x == 1) resText.append(addColor(st.frameColor));
            resText.append(st.frameSymbol);
        }
        resText.append("\u001B[0m"); //да прекъсне бекграунда
        return resText.toString();
    }

    public static String addColor(String color) {
        String clr;
        switch (color) {
            case "normal" -> clr = "\u001B[0m";
            case "black" -> clr = "\033[1;30m";  // this is bold. Normal is "\u001B[30m";
            case "pink" -> clr = "\u001B[31m";
            case "green" -> clr = "\u001B[32m";
            case "yellow" -> clr = "\u001B[33m";
            case "purple" -> clr = "\u001B[34m";
            case "red" -> clr = "\u001B[35m";
            case "cyan", "white" -> clr = "\u001B[36m";  //Official cyan
            // case "white" -> clr = "\033[1;37m";  //This is too dark
            case "whitethin" -> clr = "\033[0;37m";
            case "back" -> clr = "\033[41m";

            default -> clr = "\u001B[30m";
        }
        return clr;
    }

    public static String addBackground(String bgColor) {
        String clr = "";
        switch (bgColor) {
            case "red" -> clr = "\033[41m";
            case "green" -> clr = "\033[42m";
            case "yellow" -> clr = "\033[43m";
            case "blue" -> clr = "\033[44m";
            case "purple" -> clr = "\033[45m";
            case "cyan" -> clr = "\033[46m";
            default -> clr = "";
        }
        return clr;
    }

    public static void changeStyle(int x) {

        ProPrint nst = new ProPrint();
        switch (x) {
            case 1 -> { //BLUE3
                nst.bgColor = "blue"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 3;
            }
            case 2 -> { // YELLOW3
                nst.bgColor = "yellow"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = "|"; nst.inWeight = 3;
            }
            case 3 -> { // GREEN3
                nst.bgColor = "green"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = "|"; nst.inWeight = 3;
            }
            case 4 -> { // CYAN3
                nst.bgColor = "cyan"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = "|"; nst.inWeight = 3;
            }
            case 5 -> { // PURPLE3
                nst.bgColor = "purple"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = "|"; nst.inWeight = 3;
            }
            case 6-> { // BLACK21
                nst.bgColor = "black"; nst.mainColor = "white"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 7-> { // NAVY21
                nst.bgColor = "navy"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 8-> { // YELLOW21
                nst.bgColor = "yellow"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 9-> { // CYAN21
                nst.bgColor = "cyan"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 10-> { // PRISON21
                nst.bgColor = "black"; nst.mainColor = "red"; nst.frameColor = "white";
                nst.align = "center";  nst.frameSymbol = "|"; nst.inWeight = 21;
            }
            case 11-> { // RED21
                nst.bgColor = "red"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 12-> { // GREEN21
                nst.bgColor = "green"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 13-> { // BLUE21
                nst.bgColor = "blue"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 14-> { // LIME21
                nst.bgColor = "lime"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 15-> { // RED21R
                nst.bgColor = "red"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "right";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 16-> { // RED21L
                nst.bgColor = "red"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "left";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 17-> { // ORANGE21R
                nst.bgColor = "yellow"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "right";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 18-> { // ORANGE21L
                nst.bgColor = "yellow"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "left";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 19-> { // PURPLE21R
                nst.bgColor = "purple"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "right";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 20-> { // PURPLE21L
                nst.bgColor = "purple"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "left";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 21-> { // LOGO63
                nst.bgColor = "black"; nst.mainColor = "cyan"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 63;
            }
            case 22-> { // BLACK21F
                nst.bgColor = "black"; nst.mainColor = "cyan"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = "="; nst.inWeight = 21;
            }
            case 23-> { // BLACK21L
                nst.bgColor = "black"; nst.mainColor = "white"; nst.frameColor = "black";
                nst.align = "left";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 24-> { // BLACK21R
                nst.bgColor = "black"; nst.mainColor = "white"; nst.frameColor = "black";
                nst.align = "right";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 25-> { //NOTICE63
                nst.bgColor = "black"; nst.mainColor = "normal"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 63;
            }
            case 26-> { //BLACK21LN за закупените обекти
                nst.bgColor = "black"; nst.mainColor = "green"; nst.frameColor = "black";
                nst.align = "left";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 27-> { //MERGEDCELL
                nst.bgColor = "black"; nst.mainColor = "red"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 0;
            }
            case 28-> { // RED3
                nst.bgColor = "red"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = "|"; nst.inWeight = 3;
            }
            case 29 -> { // GREEN3B Without Frame
                nst.bgColor = "green"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 3;
            }
            case 30 -> { // PLAYER1_3
                nst.bgColor = "black"; nst.mainColor = "normal"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 3;
            }
            case 31 -> { // PLAYER2_3
                nst.bgColor = "black"; nst.mainColor = "pink"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 3;
            }
            case 32 -> { // PLAYER3_3
                nst.bgColor = "black"; nst.mainColor = "green"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 3;
            }

            case 33 -> { // PLAYER1_21
                nst.bgColor = "black"; nst.mainColor = "normal"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 34 -> { // PLAYER2_21
                nst.bgColor = "black"; nst.mainColor = "pink"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 21;
            }
            case 35 -> { // PLAYER3_21
                nst.bgColor = "black"; nst.mainColor = "green"; nst.frameColor = "black";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 21;
            }

            case 36 -> { // CONGRATS63
                nst.bgColor = "black"; nst.mainColor = "green"; nst.frameColor = "yellow";
                nst.align = "center";  nst.frameSymbol = "*"; nst.inWeight = 63;
            }

            case 0 -> nst.inWeight = 5; //for players and cards we use style 0 after original style

        }

    }

}
