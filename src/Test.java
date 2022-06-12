public class Test {
    public static ProPrintSettings st = new ProPrintSettings();

    public static void main(String[] args) {

        System.out.println();

        for (int x=1; x<=3; x++)
        {
            proPrint(x, "A"); br();
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


