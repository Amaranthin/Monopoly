public class ProPrintSettings {

    //Global Parameters for proPrint()
    public static String bgColor;
    public static String mainColor;
    public static String frameColor;
    public static int inWeight;
    public static String align;
    public static String frameSymbol;

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
        resText.append("\u001B[0m"); //да прекъсне бекграунда

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
        resText.append("\u001B[0m"); //да прекъсне бекграунда
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
            case "cyan" -> clr = "\u001B[36m";
            case "white" -> clr = "\033[1;37m";
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
            case 1 -> {
                nst.bgColor = "purple"; nst.mainColor = "black"; nst.frameColor = "black";
                nst.align = "left";  nst.frameSymbol = "="; nst.inWeight = 5;
            }
            case 2 -> {
                nst.bgColor = "red"; nst.mainColor = "black"; nst.frameColor = "red";
                nst.align = "center";  nst.frameSymbol = " "; nst.inWeight = 5;
            }
            case 3 -> {
                nst.bgColor = "blue"; nst.mainColor = "black"; nst.frameColor = "green";
                nst.align = "right";  nst.frameSymbol = "="; nst.inWeight = 5;
            }
        }

       // st = nst;
    }

}
