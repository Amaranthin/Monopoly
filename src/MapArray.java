public class MapArray {

    // PRO PRINT STYLES  (For more info look at Monopoly.xls > enumStyles)
    public	static	int	BLUE3	=	1;
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
    public	static	int	NOTICE63 = 25; //For inflation fields
    public	static	int	BLACK21LN = 26;
    public	static	int	MERGEDCELL = 27;
    public	static	int	RED3 = 28;
    public	static	int	GREEN3B = 29;

    public static int PLAYER1_3 = 30;
    public static int PLAYER2_3 = 31;
    public static int PLAYER3_3 = 32;
    public static int PLAYER1_21 = 33; //====== Warning!!! ======
    public static int PLAYER2_21 = 34; //Dont change these 3 constants, they are FIXED and used in addBuildToPlayer()
    public static int PLAYER3_21 = 35; //When we build something we use them as (32 + player number) to print ownership

    public static int CONGRATS63 = 36;


    public static int[][] mapStyle = new int[31][10];
    public static String[][] mapText = new String[31][10];


    public static void initializeMapValues()
    {
        //When mapText begin with # - this will show players on field
        //When mapText begin with @ - this will show build from array @1@5 > build 5 from player 1 list

        int c=1;
        // COLUMN 1
        mapStyle[1][1] = BLUE3; mapText[1][1] = "";
        mapStyle[2][1] = BLUE3; mapText[2][1] = "";
        mapStyle[3][1] = BLUE3; mapText[3][1] = "";
        mapStyle[4][1] = BLUE3; mapText[4][1] = "";
        mapStyle[5][1] = BLUE3; mapText[5][1] = "";

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

        mapStyle[26][1] = BLUE3; mapText[26][1] = "";
        mapStyle[27][1] = BLUE3; mapText[27][1] = "";
        mapStyle[28][1] = BLUE3; mapText[28][1] = "";
        mapStyle[29][1] = BLUE3; mapText[29][1] = "";
        mapStyle[30][1] = BLUE3; mapText[30][1] = "";


        c = 2; //COLUMN 2
        mapStyle[1][c] = BLUE21; mapText[1][c] = "";
        mapStyle[2][c] = BLUE21; mapText[2][c] = "+200";
        mapStyle[3][c] = BLUE21; mapText[3][c] = ">>> —“¿–“ >>>  ";
        mapStyle[4][c] = BLUE21; mapText[4][c] = "";
        mapStyle[5][c] = BLUE21; mapText[5][c] = "";

        mapStyle[6][c] = YELLOW21; mapText[6][c] = "";
        mapStyle[7][c] = YELLOW21; mapText[7][c] = "Energy C.(23)";
        mapStyle[8][c] = YELLOW21; mapText[8][c] = "";
        mapStyle[9][c] = YELLOW21; mapText[9][c] = "";

        mapStyle[10][c] = BLACK21; mapText[10][c] = "Building";
        mapStyle[11][c] = BLACK21; mapText[11][c] = "Corporation (22)";
        mapStyle[12][c] = BLACK21; mapText[12][c] = "";
        mapStyle[13][c] = BLACK21; mapText[13][c] = "";

        mapStyle[14][c] = BLACK21; mapText[14][c] = "Global";
        mapStyle[15][c] = BLACK21; mapText[15][c] = "Services (21)";
        mapStyle[16][c] = BLACK21; mapText[16][c] = "";
        mapStyle[17][c] = BLACK21; mapText[17][c] = "";

        mapStyle[18][c] = BLACK21; mapText[18][c] = "Chaos";
        mapStyle[19][c] = BLACK21; mapText[19][c] = "Group (20)";
        mapStyle[20][c] = BLACK21; mapText[20][c] = "";
        mapStyle[21][c] = BLACK21; mapText[21][c] = "";

        mapStyle[22][c] = CYAN21; mapText[22][c] = "";
        mapStyle[23][c] = CYAN21; mapText[23][c] = "¬»  (19)";
        mapStyle[24][c] = CYAN21; mapText[24][c] = "";
        mapStyle[25][c] = CYAN21; mapText[25][c] = "";

        mapStyle[26][c] = JAIL21; mapText[26][c] = "";
        mapStyle[27][c] = JAIL21; mapText[27][c] = "«¿“¬Œ– (18)";
        mapStyle[28][c] = JAIL21; mapText[28][c] = "";
        mapStyle[29][c] = JAIL21; mapText[29][c] = "";
        mapStyle[30][c] = JAIL21; mapText[30][c] = "";


        c = 3; //COLUMN 3
        mapStyle[1][c] = RED21R; mapText[1][c] = "—œŒ";
        mapStyle[2][c] = BLACK21; mapText[2][c] = "";
        mapStyle[3][c] = BLACK21; mapText[3][c] = "‘  ÷— ¿ (1)";
        mapStyle[4][c] = BLACK21; mapText[4][c] = " ";
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
        mapStyle[27][c] = RED21; mapText[27][c] = "¡ŒÀÕ»÷¿ (17)";
        mapStyle[28][c] = RED21; mapText[28][c] = "";
        mapStyle[29][c] = RED21; mapText[29][c] = "";
        mapStyle[30][c] = RED21; mapText[30][c] = "";

        c = 4; //COLUMN 4
        mapStyle[1][c] = RED21L; mapText[1][c] = "–“";
        mapStyle[2][c] = BLACK21; mapText[2][c] = "";
        mapStyle[3][c] = BLACK21; mapText[3][c] = "‘  À≈¬— » (2)";
        mapStyle[4][c] = BLACK21; mapText[4][c] = " ";
        mapStyle[5][c] = BLACK21; mapText[5][c] = " ";

        mapStyle[6][c] = BLACK21; mapText[6][c] = "";
        mapStyle[7][c] = LOGO63; mapText[7][c] = "================ MONOPOLY =================";
        mapStyle[8][c] = LOGO63; mapText[8][c] = "===== SOFIA =====";
        mapStyle[9][c] = BLACK21; mapText[9][c] = "";

        mapStyle[10][c] = PLAYER1_21; mapText[10][c] = "»„‡˜ 1";
        mapStyle[11][c] = PLAYER1_21; mapText[11][c] = "2000 Î‚";
        mapStyle[12][c] = PLAYER1_21; mapText[12][c] = "==============";
        mapStyle[13][c] = PLAYER1_21; mapText[13][c] = "@1.1"; //@ - call to method Monopoly.getBuildFromOwnerList()

        mapStyle[14][c] = PLAYER1_21; mapText[14][c] = "@1.2"; //with these parameters
        mapStyle[15][c] = PLAYER1_21; mapText[15][c] = "@1.3";
        mapStyle[16][c] = PLAYER1_21; mapText[16][c] = "@1.4";
        mapStyle[17][c] = PLAYER1_21; mapText[17][c] = "@1.5";

        mapStyle[18][c] = PLAYER1_21; mapText[18][c] = "@1.6";
        mapStyle[19][c] = PLAYER1_21; mapText[19][c] = "@1.7";
        mapStyle[20][c] = PLAYER1_21; mapText[20][c] = "@1.8";
        mapStyle[21][c] = PLAYER1_21; mapText[21][c] = "";

        mapStyle[22][c] = BLACK21L; mapText[22][c] = "";
        mapStyle[23][c] = NOTICE63; mapText[23][c] = "»ÌÙÎ‡ˆËˇ: “ÓÍ: 0%  ¬Ó‰‡: 0%  ’‡Ì‡: 0% "; //Field Inflation
        mapStyle[24][4] = NOTICE63; mapText[24][c] = "«‡ ÔÓ·Â‰‡ Â ‰ÓÒÚ‡Ú˙˜ÌÓ Ë Ô˙‚ ‰‡ Á‡ÍÛÔËÚÂ 9 Ó·ÂÍÚ‡!"; //Field last action
        mapStyle[25][c] = BLACK21L; mapText[25][c] = "";

        mapStyle[26][c] = BLACK21; mapText[26][c] = "";
        mapStyle[27][c] = BLACK21; mapText[27][c] = "ÿ¿Õ— (16)";
        mapStyle[28][c] = BLACK21; mapText[28][c] = "";
        mapStyle[29][c] = BLACK21; mapText[29][c] = "";
        mapStyle[30][c] = BLACK21; mapText[30][c] = "";

        c = 5; //COLUMN 5
        mapStyle[1][c] = GREEN21; mapText[1][c] = "Õ¿” ¿";
        mapStyle[2][c] = GREEN21; mapText[2][c] = "";
        mapStyle[3][c] = GREEN21; mapText[3][c] = "”Õ»¬≈–—»“≈“ (3)";
        mapStyle[4][c] = GREEN21; mapText[4][c] = " ";
        mapStyle[5][c] = GREEN21; mapText[5][c] = " ";

        mapStyle[6][c] = BLACK21; mapText[6][c] = "";
        mapStyle[7][c] = MERGEDCELL; mapText[7][c] = ""; //Dont show in map
        mapStyle[8][c] = MERGEDCELL; mapText[8][c] = ""; //Dont show in map
        mapStyle[9][c] = BLACK21; mapText[9][c] = "";

        mapStyle[10][c] = PLAYER2_21; mapText[10][c] = "»„‡˜ 2";
        mapStyle[11][c] = PLAYER2_21; mapText[11][c] = "2000 Î‚";
        mapStyle[12][c] = PLAYER2_21; mapText[12][c] = "==============";
        mapStyle[13][c] = PLAYER2_21; mapText[13][c] = "@2.1"; //@ call to function with this parameters

        mapStyle[14][c] = PLAYER2_21; mapText[14][c] = "@2.2";
        mapStyle[15][c] = PLAYER2_21; mapText[15][c] = "@2.3";
        mapStyle[16][c] = PLAYER2_21; mapText[16][c] = "@2.4";
        mapStyle[17][c] = PLAYER2_21; mapText[17][c] = "@2.5";

        mapStyle[18][c] = PLAYER2_21; mapText[18][c] = "@2.6";
        mapStyle[19][c] = PLAYER2_21; mapText[19][c] = "@2.7";
        mapStyle[20][c] = PLAYER2_21; mapText[20][c] = "@2.8";
        mapStyle[21][c] = PLAYER2_21; mapText[21][c] = "";

        mapStyle[22][c] = BLACK21; mapText[22][c] = "=== »Õ‘À¿÷»ﬂ ===";
        mapStyle[23][c] = MERGEDCELL; mapText[23][c] = "";
        mapStyle[24][c] = MERGEDCELL; mapText[24][c] = "";
        mapStyle[25][c] = BLACK21L; mapText[25][c] = "";

        mapStyle[26][c] = BLUE21; mapText[26][c] = "";
        mapStyle[27][c] = BLUE21; mapText[27][c] = "À≈“»Ÿ≈ (15)";
        mapStyle[28][c] = BLUE21; mapText[28][c] = "";
        mapStyle[29][c] = BLUE21; mapText[29][c] = "";
        mapStyle[30][c] = BLUE21; mapText[30][c] = "“–¿Õ—œŒ–“";

        c = 6; //COLUMN 6
        mapStyle[1][c] = ORANGE21R; mapText[1][c] = "’–¿Õ»";
        mapStyle[2][c] = BLACK21; mapText[2][c] = "";
        mapStyle[3][c] = BLACK21; mapText[3][c] = "Happy B&G (4)";
        mapStyle[4][c] = BLACK21; mapText[4][c] = "";
        mapStyle[5][c] = BLACK21; mapText[5][c] = "";

        mapStyle[6][c] = BLACK21; mapText[6][c] = "";
        mapStyle[7][c] = MERGEDCELL; mapText[7][c] = ""; //Dont show in map
        mapStyle[8][c] = MERGEDCELL; mapText[8][c] = ""; //Dont show in map
        mapStyle[9][c] = BLACK21; mapText[9][c] = "";

        mapStyle[10][c] = PLAYER3_21; mapText[10][c] = "»„‡˜ 3";
        mapStyle[11][c] = PLAYER3_21; mapText[11][c] = "2000 Î‚";
        mapStyle[12][c] = PLAYER3_21; mapText[12][c] = "==============";
        mapStyle[13][c] = PLAYER3_21; mapText[13][c] = "@3.1"; //@ call to function with this parameters

        mapStyle[14][c] = PLAYER3_21; mapText[14][c] = "@3.2";
        mapStyle[15][c] = PLAYER3_21; mapText[15][c] = "@3.3";
        mapStyle[16][c] = PLAYER3_21; mapText[16][c] = "@3.4";
        mapStyle[17][c] = PLAYER3_21; mapText[17][c] = "@3.5";

        mapStyle[18][c] = PLAYER3_21; mapText[18][c] = "@3.6";
        mapStyle[19][c] = PLAYER3_21; mapText[19][c] = "@3.7";
        mapStyle[20][c] = PLAYER3_21; mapText[20][c] = "@3.8";
        mapStyle[21][c] = PLAYER3_21; mapText[21][c] = "";

        mapStyle[22][c] = BLACK21L; mapText[22][c] = "";
        mapStyle[23][c] = MERGEDCELL; mapText[23][c] = "";
        mapStyle[24][c] = MERGEDCELL; mapText[24][c] = "";
        mapStyle[25][c] = BLACK21L; mapText[25][c] = "";

        mapStyle[26][c] = BLACK21; mapText[26][c] = "";
        mapStyle[27][c] = BLACK21; mapText[27][c] = "Sofia Mall (14)";
        mapStyle[28][c] = BLACK21; mapText[28][c] = "";
        mapStyle[29][c] = BLACK21; mapText[29][c] = "";
        mapStyle[30][c] = RED21R; mapText[30][c] = "–¿«¬À≈";

        c = 7; //COLUMN 7
        mapStyle[1][c] = ORANGE21L; mapText[1][c] = "“≈ÀÕ»";
        mapStyle[2][c] = BLACK21; mapText[2][c] = "";
        mapStyle[3][c] = BLACK21; mapText[3][c] = "McDonnalds (5)";
        mapStyle[4][c] = BLACK21; mapText[4][c] = "";
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
        mapStyle[28][c] = BLACK21; mapText[28][c] = "";
        mapStyle[29][c] = BLACK21; mapText[29][c] = "";
        mapStyle[30][c] = RED21L; mapText[30][c] = "◊≈Õ»ﬂ";

        c = 8; //COLUMN 8
        mapStyle[1][c] = BLUE21 ; mapText[1][c] = "";
        mapStyle[2][c] = BLUE21; mapText[2][c] = "";
        mapStyle[3][c] = BLUE21; mapText[3][c] = "  œŒÀ»÷»ﬂ (6)";
        mapStyle[4][c] = BLUE21; mapText[4][c] = "";
        mapStyle[5][c] = BLUE21; mapText[5][c] = "";

        mapStyle[6][c] = BLACK21; mapText[6][c] = "";
        mapStyle[7][c] = BLACK21; mapText[7][c] = "¬ËÚÓ¯Í‡ (7)";
        mapStyle[8][c] = BLACK21; mapText[8][c] = "";
        mapStyle[9][c] = BLACK21; mapText[9][c] = "";

        mapStyle[10][c] = BLACK21; mapText[10][c] = "";
        mapStyle[11][c] = BLACK21; mapText[11][c] = "÷”Ã (8)";
        mapStyle[12][c] = BLACK21; mapText[12][c] = "";
        mapStyle[13][c] = BLACK21; mapText[13][c] = "";

        mapStyle[14][c] = YELLOW21; mapText[14][c] = "";
        mapStyle[15][c] = YELLOW21; mapText[15][c] = "¡¿Õ ¿ (9)";
        mapStyle[16][c] = YELLOW21; mapText[16][c] = "";
        mapStyle[17][c] = YELLOW21; mapText[17][c] = "";

        mapStyle[18][c] = BLACK21; mapText[18][c] = "Grand Hotel";
        mapStyle[19][c] = BLACK21; mapText[19][c] = "Millenium (10)";
        mapStyle[20][c] = BLACK21; mapText[20][c] = "";
        mapStyle[21][c] = BLACK21; mapText[21][c] = "";

        mapStyle[22][c] = BLACK21; mapText[22][c] = "Inter";
        mapStyle[23][c] = BLACK21; mapText[23][c] = "Continental (11)";
        mapStyle[24][c] = BLACK21; mapText[24][c] = "";
        mapStyle[25][c] = BLACK21; mapText[25][c] = "";

        mapStyle[26][c] = GREEN21; mapText[26][c] = "";
        mapStyle[27][c] = GREEN21; mapText[27][c] = "  ¡Œ–»—Œ¬¿ (12)";
        mapStyle[28][c] = GREEN21; mapText[28][c] = "  √–¿ƒ»Õ¿";
        mapStyle[29][c] = GREEN21; mapText[29][c] = "";
        mapStyle[30][c] = GREEN21; mapText[30][c] = "";

        c = 9; //COLUMN 9
        mapStyle[1][c] = BLUE3 ; mapText[1][c] = " ";
        mapStyle[2][c] = BLUE3 ; mapText[2][c] = " ";
        mapStyle[3][c] = BLUE3 ; mapText[3][c] = " ";
        mapStyle[4][c] = BLUE3 ; mapText[4][c] = " ";
        mapStyle[5][c] = BLUE3 ; mapText[5][c] = " ";

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
