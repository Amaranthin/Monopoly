import java.util.Scanner;

public class Monopoly {

    static String newLine = "=========================================================================================================================================================";
    static String newLine2 = "---------------------------------------------------------------------------------------------------------------------------------------------------------";

    Scanner scn = new Scanner(System.in);  //Глобален скенер

    public static ProPrintSettings st = new ProPrintSettings();

    public static void main(String[] args) {

        MapArray.initializeMapValues();


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
}
