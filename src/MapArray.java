public class MapArray {

    // PRO PRINT STYLES  (For more info look at Monopoly.xls > enumStyles)
    public	static	int	NAVY3	=	1;
    public	static	int	YELLOW3	=	2;
    public	static	int	GREEN3	=	3;
    public	static	int	CYAN3	=	4;
    public	static	int	PURPLE3	=	5;
    public	static	int	BLACK21	=	6;
    public	static	int	NAVY21	=	7;
    public	static	int	YELLOW21	=	8;
    public	static	int	CYAN21	=	9;
    public	static	int	JAIL21	=	10;
    public	static	int	RED21	=	11;
    public	static	int	GREEN21	=	12;
    public	static	int	BLUE21	=	13;
    public	static	int	LIME21	=	14;
    public	static	int	RED21R	=	15;
    public	static	int	RED21L	=	16;
    public	static	int	ORANGE21R	=	17;
    public	static	int	ORANGE21L	=	18;
    public	static	int	PURPLE21R	=	19;
    public	static	int	PURPLE21L	=	20;
    public	static	int	LOGO63	=	21;
    public	static	int	BLACK21F	=	22; //Filed with =======
    public	static	int	BLACK21L = 23; //Black Background Left Align
    public	static	int	BLACK21R = 24; //Black Background Right Align
    public	static	int	WINNER63 = 25;
    public	static	int	BLACK21LN = 26;
    public	static	int	MERGEDCELL = 27;
    public	static	int	RED3 = 28;
    public	static	int	GREEN3B = 29;

    public static int[][] mapStyle = new int[31][10];
    public static String[][] mapText = new String[31][10];



    public static void initializeMapValues()
    {
        int c=1;
        // COLUMN 1
        mapStyle[1][1] = NAVY3; mapText[1][1] = "";
        mapStyle[2][1] = NAVY3; mapText[2][1] = "";
        mapStyle[3][1] = NAVY3; mapText[3][1] = "";
        mapStyle[4][1] = NAVY3; mapText[4][1] = "";
        mapStyle[5][1] = NAVY3; mapText[5][1] = "";

        mapStyle[6][1] = YELLOW3; mapText[6][1] = "E";
        mapStyle[7][1] = YELLOW3; mapText[7][1] = "O";
        mapStyle[8][1] = YELLOW3; mapText[8][1] = "N";
        mapStyle[9][1] = YELLOW3; mapText[9][1] = " ";

        mapStyle[10][1] = GREEN3; mapText[10][1] = " ";
        mapStyle[11][1] = GREEN3; mapText[11][1] = " ";
        mapStyle[12][1] = GREEN3; mapText[12][1] = " ";
        mapStyle[13][1] = GREEN3; mapText[13][1] = "Œ";

        mapStyle[14][1] = GREEN3; mapText[14][1] = "Ã";
        mapStyle[15][1] = GREEN3; mapText[15][1] = "œ";
        mapStyle[16][1] = GREEN3; mapText[16][1] = "¿";
        mapStyle[17][1] = GREEN3; mapText[17][1] = "Õ";

        mapStyle[18][1] = GREEN3; mapText[18][1] = "»";
        mapStyle[19][1] = GREEN3; mapText[19][1] = "»";
        mapStyle[20][1] = GREEN3; mapText[20][1] = " ";
        mapStyle[21][1] = GREEN3; mapText[21][1] = " ";

        mapStyle[22][1] = CYAN3; mapText[22][1] = "¬";
        mapStyle[23][1] = CYAN3; mapText[23][1] = "»";
        mapStyle[24][1] = CYAN3; mapText[24][1] = " ";
        mapStyle[25][1] = CYAN3; mapText[25][1] = " ";

        mapStyle[26][1] = NAVY3; mapText[26][1] = "";
        mapStyle[27][1] = NAVY3; mapText[27][1] = "";
        mapStyle[28][1] = NAVY3; mapText[28][1] = "";
        mapStyle[29][1] = NAVY3; mapText[29][1] = "";
        mapStyle[30][1] = NAVY3; mapText[30][1] = "";


        c = 2; //COLUMN 2
        mapStyle[1][c] = NAVY21; mapText[1][c] = "";
        mapStyle[2][c] = NAVY21; mapText[2][c] = "";
        mapStyle[3][c] = NAVY21; mapText[3][c] = ">>> START >>>";
        mapStyle[4][c] = NAVY21; mapText[4][c] = ""; //dynamic
        mapStyle[5][c] = NAVY21; mapText[5][c] = "";

        mapStyle[6][c] = YELLOW21; mapText[6][c] = "EON (23)";
        mapStyle[7][c] = YELLOW21; mapText[7][c] = "* tax"; //dynamic
        mapStyle[8][c] = YELLOW21; mapText[8][c] = "";
        mapStyle[9][c] = YELLOW21; mapText[9][c] = ""; //dynamic

        mapStyle[10][c] = BLACK21; mapText[10][c] = "Building";
        mapStyle[11][c] = BLACK21; mapText[11][c] = "Corporation (22)";
        mapStyle[12][c] = BLACK21; mapText[12][c] = "* tax"; //dynamic
        mapStyle[13][c] = BLACK21; mapText[13][c] = ""; //dynamic

        mapStyle[14][c] = BLACK21; mapText[14][c] = "Global";
        mapStyle[15][c] = BLACK21; mapText[15][c] = "Services (21)";
        mapStyle[16][c] = BLACK21; mapText[16][c] = "* tax"; //dynamic
        mapStyle[17][c] = BLACK21; mapText[17][c] = ""; //dynamic

        mapStyle[18][c] = BLACK21; mapText[18][c] = "Chaos";
        mapStyle[19][c] = BLACK21; mapText[19][c] = "Group (20)";
        mapStyle[20][c] = BLACK21; mapText[20][c] = "* tax"; //dynamic
        mapStyle[21][c] = BLACK21; mapText[21][c] = ""; //dynamic

        mapStyle[22][c] = BLACK21; mapText[22][c] = "¬»  (19)";
        mapStyle[23][c] = BLACK21; mapText[23][c] = "* tax";
        mapStyle[24][c] = BLACK21; mapText[24][c] = ""; //dynamic
        mapStyle[25][c] = BLACK21; mapText[25][c] = ""; //dynamic

        mapStyle[26][c] = JAIL21; mapText[26][c] = "";
        mapStyle[27][c] = JAIL21; mapText[27][c] = "«¿“¬Œ–";
        mapStyle[28][c] = JAIL21; mapText[28][c] = ""; //dynamic
        mapStyle[29][c] = JAIL21; mapText[29][c] = "";
        mapStyle[30][c] = JAIL21; mapText[30][c] = "";


        c = 3; //COLUMN 3
        mapStyle[1][c] = RED21R; mapText[1][c] = "—œŒ";
        mapStyle[2][c] = BLACK21; mapText[2][c] = "‘  ÷— ¿ (1)";
        mapStyle[3][c] = BLACK21; mapText[3][c] = "* tax"; //dynamic
        mapStyle[4][c] = BLACK21; mapText[4][c] = " "; //dynamic
        mapStyle[5][c] = BLACK21; mapText[5][c] = " ";

        mapStyle[6][c] = BLACK21L; mapText[6][c] = "|";
        mapStyle[7][c] = BLACK21L; mapText[7][c] = "|";
        mapStyle[8][c] = BLACK21L; mapText[8][c] = "|";
        mapStyle[9][c] = BLACK21L; mapText[9][c] = "|";

        mapStyle[10][c] = BLACK21L; mapText[10][c] = "|";
        mapStyle[11][c] = BLACK21L; mapText[11][c] = "|";
        mapStyle[12][c] = BLACK21L; mapText[12][c] = "|";
        mapStyle[13][c] = BLACK21L; mapText[13][c] = "|";

        mapStyle[14][c] = BLACK21L; mapText[14][c] = "|";
        mapStyle[15][c] = BLACK21L; mapText[15][c] = "|";
        mapStyle[16][c] = BLACK21L; mapText[16][c] = "|";
        mapStyle[17][c] = BLACK21L; mapText[17][c] = "|";

        mapStyle[18][c] = BLACK21L; mapText[18][c] = "|";
        mapStyle[19][c] = BLACK21L; mapText[19][c] = "|";
        mapStyle[20][c] = BLACK21L; mapText[20][c] = "|";
        mapStyle[21][c] = BLACK21L; mapText[21][c] = "|";

        mapStyle[22][c] = BLACK21L; mapText[22][c] = "|";
        mapStyle[23][c] = BLACK21L; mapText[23][c] = "|";
        mapStyle[24][c] = BLACK21L; mapText[24][c] = "|";
        mapStyle[25][c] = BLACK21L; mapText[25][c] = "|";

        mapStyle[26][c] = RED21; mapText[26][c] = "";
        mapStyle[27][c] = RED21; mapText[27][c] = "¡ŒÀÕ»÷¿";
        mapStyle[28][c] = RED21; mapText[28][c] = ""; //dynamic
        mapStyle[29][c] = RED21; mapText[29][c] = "";
        mapStyle[30][c] = RED21; mapText[30][c] = "";

        c = 4; //COLUMN 4
        mapStyle[1][c] = RED21L; mapText[1][c] = "–“";
        mapStyle[2][c] = BLACK21; mapText[2][c] = "‘  À≈¬— » (2)";
        mapStyle[3][c] = BLACK21; mapText[3][c] = "* tax"; //dynamic
        mapStyle[4][c] = BLACK21; mapText[4][c] = " "; //dynamic
        mapStyle[5][c] = BLACK21; mapText[5][c] = " ";

        mapStyle[6][c] = BLACK21; mapText[6][c] = "";
        mapStyle[7][c] = LOGO63; mapText[7][c] = "================ MONOPOLY =================";
        mapStyle[8][c] = LOGO63; mapText[8][c] = "===== SOFIA =====";
        mapStyle[9][c] = BLACK21; mapText[9][c] = "";

        mapStyle[10][c] = BLACK21; mapText[10][c] = "";
        mapStyle[11][c] = BLACK21; mapText[11][c] = "»„‡˜ 1"; //dynamic
        mapStyle[12][c] = BLACK21; mapText[12][c] = "2000 Î‚"; //dymanic
        mapStyle[13][c] = BLACK21; mapText[13][c] = "==============";

        mapStyle[14][c] = BLACK21LN; mapText[14][c] = ""; //dynamic
        mapStyle[15][c] = BLACK21LN; mapText[15][c] = ""; //dynamic
        mapStyle[16][c] = BLACK21LN; mapText[16][c] = ""; //dynamic
        mapStyle[17][c] = BLACK21LN; mapText[17][c] = ""; //dynamic

        mapStyle[18][c] = BLACK21LN; mapText[18][c] = ""; //dynamic
        mapStyle[19][c] = BLACK21LN; mapText[19][c] = ""; //dynamic
        mapStyle[20][c] = BLACK21LN; mapText[20][c] = ""; //dynamic
        mapStyle[21][c] = BLACK21LN; mapText[21][c] = ""; //dynamic

        mapStyle[22][c] = BLACK21L; mapText[22][c] = "";
        mapStyle[23][c] = WINNER63; mapText[23][c] = "«‡ ÔÓ·Â‰‡ Â ‰ÓÒÚ‡Ú˙˜ÌÓ Ë Ô˙‚ ‰‡ Á‡ÍÛÔËÚÂ 9 Ó·ÂÍÚ‡!";
        mapStyle[24][c] = BLACK21L; mapText[24][c] = "";
        mapStyle[25][c] = BLACK21L; mapText[25][c] = "";

        mapStyle[26][c] = BLACK21; mapText[26][c] = "";
        mapStyle[27][c] = BLACK21; mapText[27][c] = "ÿ¿Õ—";
        mapStyle[28][c] = BLACK21; mapText[28][c] = ""; //dynamic
        mapStyle[29][c] = BLACK21; mapText[29][c] = "";
        mapStyle[30][c] = BLACK21; mapText[30][c] = "";

        c = 5; //COLUMN 5
        mapStyle[1][c] = GREEN21; mapText[1][c] = "Õ¿” ¿";
        mapStyle[2][c] = GREEN21; mapText[2][c] = "”Õ»¬≈–—»“≈“ (3)";
        mapStyle[3][c] = GREEN21; mapText[3][c] = "* tax"; //dynamic
        mapStyle[4][c] = GREEN21; mapText[4][c] = " "; //dynamic
        mapStyle[5][c] = GREEN21; mapText[5][c] = " ";

        mapStyle[6][c] = BLACK21; mapText[6][c] = "";
        mapStyle[7][c] = MERGEDCELL; mapText[7][c] = ""; //Dont show in map
        mapStyle[8][c] = MERGEDCELL; mapText[8][c] = ""; //Dont show in map
        mapStyle[9][c] = BLACK21; mapText[9][c] = "";

        mapStyle[10][c] = BLACK21; mapText[10][c] = "";
        mapStyle[11][c] = BLACK21; mapText[11][c] = "»„‡˜ 2"; //dynamic
        mapStyle[12][c] = BLACK21; mapText[12][c] = "2000 Î‚"; //dymanic
        mapStyle[13][c] = BLACK21; mapText[13][c] = "==============";

        mapStyle[14][c] = BLACK21LN; mapText[14][c] = ""; //dynamic
        mapStyle[15][c] = BLACK21LN; mapText[15][c] = ""; //dynamic
        mapStyle[16][c] = BLACK21LN; mapText[16][c] = ""; //dynamic
        mapStyle[17][c] = BLACK21LN; mapText[17][c] = ""; //dynamic

        mapStyle[18][c] = BLACK21LN; mapText[18][c] = ""; //dynamic
        mapStyle[19][c] = BLACK21LN; mapText[19][c] = ""; //dynamic
        mapStyle[20][c] = BLACK21LN; mapText[20][c] = ""; //dynamic
        mapStyle[21][c] = BLACK21LN; mapText[21][c] = ""; //dynamic

        mapStyle[22][c] = BLACK21L; mapText[22][c] = "";
        mapStyle[23][c] = MERGEDCELL; mapText[23][c] = "";
        mapStyle[24][c] = BLACK21L; mapText[24][c] = "";
        mapStyle[25][c] = BLACK21L; mapText[25][c] = "";

        mapStyle[26][c] = NAVY21; mapText[26][c] = "";
        mapStyle[27][c] = NAVY21; mapText[27][c] = "À≈“»Ÿ≈";
        mapStyle[28][c] = NAVY21; mapText[28][c] = "* tax"; //dynamic
        mapStyle[29][c] = NAVY21; mapText[29][c] = ""; //dynamic
        mapStyle[30][c] = NAVY21; mapText[30][c] = "“–¿Õ—œŒ–“";

        c = 6; //COLUMN 6
        mapStyle[1][c] = ORANGE21R; mapText[1][c] = "’–¿Õ»";
        mapStyle[2][c] = BLACK21; mapText[2][c] = "Happy B&G (4)";
        mapStyle[3][c] = BLACK21; mapText[3][c] = "* tax"; //dynamic
        mapStyle[4][c] = BLACK21; mapText[4][c] = ""; //dynamic
        mapStyle[5][c] = BLACK21; mapText[5][c] = "";

        mapStyle[6][c] = BLACK21; mapText[6][c] = "";
        mapStyle[7][c] = MERGEDCELL; mapText[7][c] = ""; //Dont show in map
        mapStyle[8][c] = MERGEDCELL; mapText[8][c] = ""; //Dont show in map
        mapStyle[9][c] = BLACK21; mapText[9][c] = "";

        mapStyle[10][c] = BLACK21; mapText[10][c] = "";
        mapStyle[11][c] = BLACK21; mapText[11][c] = "»„‡˜ 3"; //dynamic
        mapStyle[12][c] = BLACK21; mapText[12][c] = "2000 Î‚"; //dymanic
        mapStyle[13][c] = BLACK21; mapText[13][c] = "==============";

        mapStyle[14][c] = BLACK21LN; mapText[14][c] = ""; //dynamic
        mapStyle[15][c] = BLACK21LN; mapText[15][c] = ""; //dynamic
        mapStyle[16][c] = BLACK21LN; mapText[16][c] = ""; //dynamic
        mapStyle[17][c] = BLACK21LN; mapText[17][c] = ""; //dynamic

        mapStyle[18][c] = BLACK21LN; mapText[18][c] = ""; //dynamic
        mapStyle[19][c] = BLACK21LN; mapText[19][c] = ""; //dynamic
        mapStyle[20][c] = BLACK21LN; mapText[20][c] = ""; //dynamic
        mapStyle[21][c] = BLACK21LN; mapText[21][c] = ""; //dynamic

        mapStyle[22][c] = BLACK21L; mapText[22][c] = "";
        mapStyle[23][c] = MERGEDCELL; mapText[23][c] = "";
        mapStyle[24][c] = BLACK21L; mapText[24][c] = "";
        mapStyle[25][c] = BLACK21L; mapText[25][c] = "";

        mapStyle[26][c] = BLACK21; mapText[26][c] = "";
        mapStyle[27][c] = BLACK21; mapText[27][c] = "Sofia Mall (14)";
        mapStyle[28][c] = BLACK21; mapText[28][c] = "* tax"; //dynamic
        mapStyle[29][c] = BLACK21; mapText[29][c] = ""; //dynamic
        mapStyle[30][c] = RED21R; mapText[30][c] = "–¿«¬À≈";

        c = 7; //COLUMN 7
        mapStyle[1][c] = ORANGE21L; mapText[1][c] = "“≈ÀÕ»";
        mapStyle[2][c] = BLACK21; mapText[2][c] = "McDonnalds (5)";
        mapStyle[3][c] = BLACK21; mapText[3][c] = "* tax"; //dynamic
        mapStyle[4][c] = BLACK21; mapText[4][c] = ""; //dynamic
        mapStyle[5][c] = BLACK21; mapText[5][c] = "";

        mapStyle[6][c] = BLACK21R; mapText[6][c] = "|";
        mapStyle[7][c] = BLACK21R; mapText[7][c] = "|";
        mapStyle[8][c] = BLACK21R; mapText[8][c] = "|";
        mapStyle[9][c] = BLACK21R; mapText[9][c] = "|";

        mapStyle[10][c] = BLACK21R; mapText[10][c] = "|";
        mapStyle[11][c] = BLACK21R; mapText[11][c] = "|";
        mapStyle[12][c] = BLACK21R; mapText[12][c] = "|";
        mapStyle[13][c] = BLACK21R; mapText[13][c] = "|";

        mapStyle[14][c] = BLACK21R; mapText[14][c] = "|";
        mapStyle[15][c] = BLACK21R; mapText[15][c] = "|";
        mapStyle[16][c] = BLACK21R; mapText[16][c] = "|";
        mapStyle[17][c] = BLACK21R; mapText[17][c] = "|";

        mapStyle[18][c] = BLACK21R; mapText[18][c] = "|";
        mapStyle[19][c] = BLACK21R; mapText[19][c] = "|";
        mapStyle[20][c] = BLACK21R; mapText[20][c] = "|";
        mapStyle[21][c] = BLACK21R; mapText[21][c] = "|";

        mapStyle[22][c] = BLACK21R; mapText[22][c] = "|";
        mapStyle[23][c] = BLACK21R; mapText[23][c] = "|";
        mapStyle[24][c] = BLACK21R; mapText[24][c] = "|";
        mapStyle[25][c] = BLACK21R; mapText[25][c] = "|";

        mapStyle[26][c] = BLACK21; mapText[26][c] = "";
        mapStyle[27][c] = BLACK21; mapText[27][c] = "Õƒ  (13)";
        mapStyle[28][c] = BLACK21; mapText[28][c] = "* tax"; //dynamic
        mapStyle[29][c] = BLACK21; mapText[29][c] = ""; //dynamic
        mapStyle[30][c] = RED21L; mapText[30][c] = "◊≈Õ»ﬂ";

        c = 8; //COLUMN 8
        mapStyle[1][c] = BLUE21 ; mapText[1][c] = "";
        mapStyle[2][c] = BLUE21; mapText[2][c] = "";
        mapStyle[3][c] = BLUE21; mapText[3][c] = "œŒÀ»÷»ﬂ";
        mapStyle[4][c] = BLUE21; mapText[4][c] = ""; //dynamic
        mapStyle[5][c] = BLUE21; mapText[5][c] = "";

        mapStyle[6][c] = BLACK21; mapText[6][c] = "¬ËÚÓ¯Í‡ (7)";
        mapStyle[7][c] = BLACK21; mapText[7][c] = "* tax"; //dynamic
        mapStyle[8][c] = BLACK21; mapText[8][c] = ""; //dynamic
        mapStyle[9][c] = BLACK21; mapText[9][c] = "";

        mapStyle[10][c] = BLACK21; mapText[10][c] = "÷”Ã (8)";
        mapStyle[11][c] = BLACK21; mapText[11][c] = "* tax"; //dynamic
        mapStyle[12][c] = BLACK21; mapText[12][c] = ""; //dynamic
        mapStyle[13][c] = BLACK21; mapText[13][c] = "";

        mapStyle[14][c] = YELLOW21; mapText[14][c] = "";
        mapStyle[15][c] = YELLOW21; mapText[15][c] = "¡¿Õ ¿";
        mapStyle[16][c] = YELLOW21; mapText[16][c] = "";
        mapStyle[17][c] = YELLOW21; mapText[17][c] = "";

        mapStyle[18][c] = BLACK21; mapText[18][c] = "Grand Hotel";
        mapStyle[19][c] = BLACK21; mapText[19][c] = "Millenium (10)";
        mapStyle[20][c] = BLACK21; mapText[20][c] = "* tax";
        mapStyle[21][c] = BLACK21; mapText[21][c] = "";

        mapStyle[22][c] = BLACK21; mapText[22][c] = "Inter";
        mapStyle[23][c] = BLACK21; mapText[23][c] = "Continental (11)";
        mapStyle[24][c] = BLACK21; mapText[24][c] = "* tax";
        mapStyle[25][c] = BLACK21; mapText[25][c] = "";

        mapStyle[26][c] = GREEN21; mapText[26][c] = "";
        mapStyle[27][c] = GREEN21; mapText[27][c] = "¡Œ–»—Œ¬¿";
        mapStyle[28][c] = GREEN21; mapText[28][c] = "√–¿ƒ»Õ¿";
        mapStyle[29][c] = GREEN21; mapText[29][c] = ""; //dynamic
        mapStyle[30][c] = GREEN21; mapText[30][c] = "";

        c = 9; //COLUMN 9
        mapStyle[1][c] = NAVY3 ; mapText[1][c] = " ";
        mapStyle[2][c] = NAVY3 ; mapText[2][c] = " ";
        mapStyle[3][c] = NAVY3 ; mapText[3][c] = " ";
        mapStyle[4][c] = NAVY3 ; mapText[4][c] = " ";
        mapStyle[5][c] = NAVY3 ; mapText[5][c] = " ";

        mapStyle[6][c] = RED3; mapText[6][c] = " ";
        mapStyle[7][c] = RED3; mapText[7][c] = " ";
        mapStyle[8][c] = RED3; mapText[8][c] = "M";
        mapStyle[9][c] = RED3; mapText[9][c] = "O";

        mapStyle[10][c] = RED3; mapText[10][c] = "ƒ";
        mapStyle[11][c] = RED3; mapText[11][c] = "¿";
        mapStyle[12][c] = RED3; mapText[12][c] = " ";
        mapStyle[13][c] = RED3; mapText[13][c] = " ";

        mapStyle[14][c] = YELLOW3; mapText[14][c] = " ";
        mapStyle[15][c] = YELLOW3; mapText[15][c] = "$";
        mapStyle[16][c] = YELLOW3; mapText[16][c] = " ";
        mapStyle[17][c] = YELLOW3; mapText[17][c] = " ";

        mapStyle[18][c] = CYAN3; mapText[18][c] = " ";
        mapStyle[19][c] = CYAN3; mapText[19][c] = "’";
        mapStyle[20][c] = CYAN3; mapText[20][c] = "Œ";
        mapStyle[21][c] = CYAN3; mapText[21][c] = "“";

        mapStyle[22][c] = CYAN3; mapText[22][c] = "≈";
        mapStyle[23][c] = CYAN3; mapText[23][c] = "À";
        mapStyle[24][c] = CYAN3; mapText[24][c] = "»";
        mapStyle[25][c] = CYAN3; mapText[25][c] = " ";

        mapStyle[26][c] = GREEN3B; mapText[26][c] = " ";
        mapStyle[27][c] = GREEN3B; mapText[27][c] = " ";
        mapStyle[28][c] = GREEN3B; mapText[28][c] = " ";
        mapStyle[29][c] = GREEN3B; mapText[29][c] = " ";
        mapStyle[30][c] = GREEN3B; mapText[30][c] = " ";
    }

}
