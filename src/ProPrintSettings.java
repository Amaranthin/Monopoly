public class ProPrintSettings {

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
                    if (c=='#') partPrintPlayers(MapArray.mapText[row][col]);
                    else if (c=='@') {
                        String buildName = Monopoly.getBuildFromOwnerList(MapArray.mapText[row][col]);
                        printCentered(buildName, buildName.length(), 21);
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

    public static void partPrintPlayers(String txt)
    {   //when 2 or more players is in same field we need print them in different style
        String nText = txt.substring(1);

        String[] player = new String[4];
        player[1]=""; player[2]=""; player[3]="";

        int num = Integer.valueOf(nText);
        int cntPlayersHere = 0;

        ProPrintSettings st = new ProPrintSettings();
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

    //Print text using specific style:
    //cell length, align, maincolor, background color, background symbol, background symbol color
    public static void proPrint(int style, String txt) {

        ProPrintSettings st = new ProPrintSettings();
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

    public static String cText(ProPrintSettings st, String txt) {
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
        resText.append("\u001B[0m"); //�� �������� ����������

        return resText.toString();
    }

    public static String rText(ProPrintSettings st, String txt) {
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
        resText.append("\u001B[0m"); //�� �������� ����������
        return resText.toString();
    }

    public static String lText(ProPrintSettings st, String txt) {
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
        resText.append("\u001B[0m"); //�� �������� ����������
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

        ProPrintSettings nst = new ProPrintSettings();
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
            case 10-> { // JAIL21
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
            case 26-> { //BLACK21LN �� ���������� ������
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

        }

    }

    public static void br()
    {
        System.out.println();
    }

}
