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
                printn("Разкрити финансови престъпления. Отивате в затвора!"); goJail(player);
            }
            case 2 -> {
                printn("Замесен сте във фирмени измами. Отивате в затвора!"); goJail(player);
            }
            case 3 -> {
                printn("Претърпявате ПТП. Отивате в болницата!"); goHospital(player);
            }
            case 4 -> {
                printn("Уличен сте в корупционни схеми! Глоба 200 лв + затвор");
                simplePayToInstitution(player, 200);
                goJail(player);
            }
            case 5 -> {
                printn("Глобен сте за улично хулиганство 100 лв!"); simplePayToInstitution(player, 100);
            }
            case 6 -> {
                print("Глоба за превишена скорост на пътя 50 лв!");
                if (Monopoly.hadPrisonCard[player]) {
                    Monopoly.hadPrisonCard[player] = false;
                    printn("Отнета ви е картата за излизане от затвора");
                }
                simplePayToInstitution(player, 50);
            }
            case 7 -> {
                double dinner = 50*Monopoly.infFood;
                printn("Млада полицайка ви спира... СЪРЦЕТО! Плащате за романтична вечеря " + (int) dinner + " лв!");
                simplePayToInstitution(player, (int) dinner);

                int x = rnd.nextInt(10)+1;
                if (x>2) { //test todo 5
                    //If she is happy you got freePrisonCard
                    getPrisonExitCardWithNotice(player, "Получавате карта за изход от затвора");
                }

            }
            case 8 -> {
                printn("Всичко е точно! Вие сте образец за примерен гражданин.");
                getPrisonExitCardWithNotice(player, "Полицията ви дарява карта за изход от затвора");
            }
            case 9 -> {
                print("В игра на голф се запознавате с шефа на полицията.");
                getPrisonExitCardWithNotice(player, "Получавате карта за изход от затвора");
            }
            case 10 -> {
                printn("Разкрити са измами на вашите конкуренти. Получавате от всеки друг по 150 лв");
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
            int tax = (rnd.nextInt(10)+1)*25; //25 до 150лв
            simplePayToInstitution(player, (int) tax);

            System.out.println("Заплатихте такса за лечение " + tax + " лв");
        }
        else
        {
            System.out.println("Използвахте картата си за безплатно лечение.");
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
            int tax = (rnd.nextInt(6)+1)*25; //25 до 150лв
            simplePayToInstitution(player, (int) tax);

            System.out.println("Заплатихте такса за допълнително обучение " + tax + " лв");
        }
        else
        {
            System.out.println("Използвахте картата си за безплатно обучение.");
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
