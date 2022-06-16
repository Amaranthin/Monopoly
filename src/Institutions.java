import java.util.Random;

public class Institutions
{
    public static void simplePayToInstitution(int curPl, int tax)
    {
        Monopoly.refreshPlayerMoney(curPl, - tax);
    }

    public static String goPolice(int player)
    {
        Random rnd = new Random();
        int situation = rnd.nextInt(10)+1;

        String action = "";
        switch (situation) {
            case 1 -> {action = "�������� ��������� ������������. ������� � �������!"; goJail(player);}
            case 2 -> {action = "������� ��� ��� ������� ������. ������� � �������!"; goJail(player);}
            case 3 -> {action = "������������ ���. ������� � ���������!"; goHospital(player);}
            case 4 -> {
                action = "������ ��� � ����������� �����! ����� 200 �� + ������";
                simplePayToInstitution(player, 200);
                goJail(player);
            }
            case 5 -> {action = "������ ��� �� ������ ����������� 100 ��!"; simplePayToInstitution(player, 100);;}
            case 6 -> {
                action = "����� �� ��������� ������� �� ���� 50 ��!";
                if (Monopoly.hadPrisonCard[player]) {
                    Monopoly.hadPrisonCard[player] = false;
                    action += "\n������ �� � ������� �� �������� �� �������";
                }
                simplePayToInstitution(player, 50);
            }
            case 7 -> {
                double dinner = 50*Monopoly.infFood;
                action = "����� ��������� �� �����... �������! ������� �� ���������� ������ " + (int) dinner + " ��!";
                simplePayToInstitution(player, (int) dinner);
            }
            case 8 -> {
                action = "������ � �����! ���������� ��������� �� ������ � ����� �� ����� �� �������.";
                Monopoly.hadPrisonCard[player] = true;
            }
            case 9 -> {
                action = "� ���� �� ���� �� ����������� � ���� �� ���������. ���������� ����� �� ����� �� �������";
                Monopoly.hadPrisonCard[player] = true;
            }
            case 10 -> {
                action = "�������� �� ������ �� ������ ����������. ���������� �� ����� ���� �� 150 ��";
                for (int x=1; x<=3; x++)
                {
                    if (x!=player) Monopoly.letPayTaxToOwner(x, player, 6); //field is nosense here
                }
            }
            default -> action = "Logic Error in Police";
        }
        return action;
    }

    public static void goJail(int player)
    {
        Monopoly.plWhere[player]=18;
    }

    public static void goHospital(int player)
    {
        Random rnd = new Random();
        int tax = (rnd.nextInt(10)+1)*25; //25 �� 250��
        System.out.println("���������� ����� �� ������� " + tax + " ��");
        Monopoly.plWhere[player]=17;
    }


}
