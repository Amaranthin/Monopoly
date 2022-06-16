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
            case 1 -> {action = "Разкрити финансови престъпления. Отивате в затвора!"; goJail(player);}
            case 2 -> {action = "Замесен сте във фирмени измами. Отивате в затвора!"; goJail(player);}
            case 3 -> {action = "Претърпявате ПТП. Отивате в болницата!"; goHospital(player);}
            case 4 -> {
                action = "Уличен сте в корупционни схеми! Глоба 200 лв + затвор";
                simplePayToInstitution(player, 200);
                goJail(player);
            }
            case 5 -> {action = "Глобен сте за улично хулиганство 100 лв!"; simplePayToInstitution(player, 100);;}
            case 6 -> {
                action = "Глоба за превишена скорост на пътя 50 лв!";
                if (Monopoly.hadPrisonCard[player]) {
                    Monopoly.hadPrisonCard[player] = false;
                    action += "\nОтнета ви е картата за излизане от затвора";
                }
                simplePayToInstitution(player, 50);
            }
            case 7 -> {
                double dinner = 50*Monopoly.infFood;
                action = "Млада полицайка ви спира... СЪРЦЕТО! Плащате за романтична вечеря " + (int) dinner + " лв!";
                simplePayToInstitution(player, (int) dinner);
            }
            case 8 -> {
                action = "Всичко е точно! Получавате потупване по рамото и карта за изход от затвора.";
                Monopoly.hadPrisonCard[player] = true;
            }
            case 9 -> {
                action = "В игра на голф се запознавате с шефа на полицията. Получавате карта за изход от затвора";
                Monopoly.hadPrisonCard[player] = true;
            }
            case 10 -> {
                action = "Разкрити са измами на вашите конкуренти. Получавате от всеки друг по 150 лв";
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
        int tax = (rnd.nextInt(10)+1)*25; //25 до 250лв
        System.out.println("Заплатихте такса за лечение " + tax + " лв");
        Monopoly.plWhere[player]=17;
    }


}
