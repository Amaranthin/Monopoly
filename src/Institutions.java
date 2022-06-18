import java.util.Random;

public class Institutions
{
    public static void simplePayToInstitution(int curPl, int tax)
    {
        Monopoly.refreshPlayerMoney(curPl, - tax);
    }

    public static void goPolice(int player)
    {
        //If we come here from other field, not from dices (not use right now, but for every case)
        Monopoly.plWhere[player]=6;

        Random rnd = new Random();
        int situation = rnd.nextInt(10)+1;

        switch (situation) {
            case 1 -> {
                printn("�������� ��������� ������������. ������� � �������!"); goJail(player);
            }
            case 2 -> {
                printn("������� ��� ��� ������� ������. ������� � �������!"); goJail(player);
            }
            case 3 -> {
                printn("������������ ���. ������� � ���������!"); goHospital(player);
            }
            case 4 -> {
                printn("������ ��� � ����������� �����! ����� 200 �� + ������");
                simplePayToInstitution(player, 200);
                goJail(player);
            }
            case 5 -> {
                printn("������ ��� �� ������ ����������� 100 ��!"); simplePayToInstitution(player, 100);
            }
            case 6 -> {
                print("����� �� ��������� ������� �� ���� 50 ��!");
                if (Monopoly.hadPrisonCard[player]) {
                    Monopoly.hadPrisonCard[player] = false;
                    printn("������ �� � ������� �� �������� �� �������");
                }
                simplePayToInstitution(player, 50);
            }
            case 7 -> {
                double dinner = 50*Monopoly.infFood;
                printn("����� ��������� �� �����... �������! ������� �� ���������� ������ " + (int) dinner + " ��!");
                simplePayToInstitution(player, (int) dinner);

                int x = rnd.nextInt(10)+1;
                if (x>2) { //test todo 5
                    //If she is happy you got freePrisonCard
                    getPrisonExitCardWithNotice(player, "���������� ����� �� ����� �� �������");
                }

            }
            case 8 -> {
                printn("������ � �����! ��� ��� ������� �� �������� ���������.");
                getPrisonExitCardWithNotice(player, "��������� �� ������ ����� �� ����� �� �������");
            }
            case 9 -> {
                print("� ���� �� ���� �� ����������� � ���� �� ���������.");
                getPrisonExitCardWithNotice(player, "���������� ����� �� ����� �� �������");
            }
            case 10 -> {
                printn("�������� �� ������ �� ������ ����������. ���������� �� ����� ���� �� 150 ��");
                for (int x=1; x<=3; x++)
                {
                    if (x!=player) Monopoly.letPayTaxToOwner(x, player, 6); //field is nosense here
                }
            }
            default -> printn("Logic Error in Police");
        }
    }

    public static void getPrisonExitCardWithNotice(int player, String notice)
    {
        if (!Monopoly.hadPrisonCard[player]) {
            printn(notice);
            Monopoly.hadPrisonCard[player] = true;
        }
    }

    public static void goJail(int player)
    {
        //Because come here only from other fields
        Monopoly.plWhere[player]=18;
        Monopoly.movePlayersToFields();
    }

    public static void goHospital(int player)
    {
        //If we come here from other field, not from dices
        Monopoly.plWhere[player]=17;

        if (!Monopoly.hadHospitalCard[player]){
            Random rnd = new Random();
            int tax = (rnd.nextInt(10)+1)*25; //25 �� 150��
            simplePayToInstitution(player, (int) tax);

            System.out.println("���������� ����� �� ������� " + tax + " ��");
        }
        else
        {
            System.out.println("����������� ������� �� �� ��������� �������.");
            Monopoly.hadHospitalCard[player]=false;
        }

        Monopoly.movePlayersToFields();
    }

    public static void goUniversitat(int player)
    {
        //If we come here from other field, not from dices
        Monopoly.plWhere[player]=3;

        if (!Monopoly.hadUniversitatyCard[player]){
            Random rnd = new Random();
            int tax = (rnd.nextInt(6)+1)*25; //25 �� 150��
            simplePayToInstitution(player, (int) tax);

            System.out.println("���������� ����� �� ������������ �������� " + tax + " ��");
        }
        else
        {
            System.out.println("����������� ������� �� �� ��������� ��������.");
            Monopoly.hadUniversitatyCard[player]=false;
        }

        Monopoly.movePlayersToFields();
    }

    public static void printn(String txt)
    {
        System.out.println(txt);
    }
    public static void print(String txt)
    {
        System.out.print(txt);
    }
}
