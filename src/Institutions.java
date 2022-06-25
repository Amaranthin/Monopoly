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
                printn("Разкрити финансови престъпления. Отивате в затвора!"); goPrison(player);
            }
            case 2 -> {
                printn("Замесен сте във фирмени измами. Отивате в затвора!"); goPrison(player);
            }
            case 3 -> {
                printn("Претърпявате ПТП. Отивате в болницата!"); goHospital(player);
            }
            case 4 -> {
                printn("Уличен сте в корупционни схеми! Глоба 200 лв + затвор");
                simplePayToInstitution(player, 200);
                goPrison(player);
            }
            case 5 -> {
                printn("Глобен сте за улично хулиганство 100 лв!"); simplePayToInstitution(player, 100);
            }
            case 6 -> {
                printn("Глоба за превишена скорост на пътя 50 лв!");
                if (Monopoly.hadPrisonCard[player]) {
                    Monopoly.hadPrisonCard[player] = false;
                    printn("Отнета ви е картата за излизане от затвора");
                    refreshPlayerCards();

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
                    refreshPlayerCards();
                }

            }
            case 8 -> {
                printn("Всичко е точно! Вие сте образец за примерен гражданин.");
                getPrisonExitCardWithNotice(player, "Полицията ви дарява карта за изход от затвора");
                refreshPlayerCards();
            }
            case 9 -> {
                print("В игра на голф се запознавате с шефа на полицията.");
                getPrisonExitCardWithNotice(player, "Получавате карта за изход от затвора");
                refreshPlayerCards();
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

    public static void goBank(int player)
    {
        //If we come here from other field, not from dices (not use right now, but for every case)
        Monopoly.plWhere[player]=9;

        Random rnd = new Random();
        int situation = rnd.nextInt(10)+1;

        switch (situation) {
            case 1 -> {
                //Increase Global Inflation
                double up = rnd.nextDouble() * 0.08 + 0.02; //2-10%
                Monopoly.infGlobal = Monopoly.infGlobal * (1 + up);
                System.out.println("Базовата инфлация се покачва до "
                        + Monopoly.convertInflationToPercent(Monopoly.infGlobal));
            }
            case 2 -> {
                //Increase Energy Inflation
                double up = rnd.nextDouble() * 0.15 + 0.05; //5-20%
                Monopoly.infEnergy = Monopoly.infEnergy * (1 + up);
                System.out.println("Енергията поскъпва до "
                        + Monopoly.convertInflationToPercent(Monopoly.infEnergy));
            }
            case 3 -> {
                //Increase Water Inflation
                double up = rnd.nextDouble() * 0.15 + 0.05; //5-20%
                Monopoly.infWater = Monopoly.infWater * (1 + up);
                System.out.println("Водата поскъпва до "
                        + Monopoly.convertInflationToPercent(Monopoly.infWater));
            }
            case 4 -> {
                //Increase Food Inflation
                double up = rnd.nextDouble() * 0.15 + 0.05; //5-20%
                Monopoly.infFood = Monopoly.infFood * (1 + up);
                System.out.println("Храната поскъпва до "
                        + Monopoly.convertInflationToPercent(Monopoly.infFood));
            }
            case 5 -> {
                //Toto Milionare
                int sum = (rnd.nextInt(20) +1)*100; //100-2000
                Monopoly.refreshPlayerMoney(player, sum);
                System.out.println("Печелите от тотото " + sum);
            }
            case 6 -> {
                //Inheritance
                int sum = (rnd.nextInt(10) +1)*100; //100-1000
                Monopoly.refreshPlayerMoney(player, sum);
                System.out.println("Получавате наследство " + sum);
            }
            case 7 -> {
                //newCountryStandart
                int newStandart = rnd.nextInt(3)+1;
                Monopoly.crossStartBonus += newStandart*50;
                System.out.println("Покачване на стандарта на живот. Всяко пресичане ще носи +" + Monopoly.crossStartBonus);
                MapArray.mapText[2][2] = "+" + Monopoly.crossStartBonus;
            }
            case 8 -> {
                //Bank mistake
                Monopoly.plMoney[player] -= 100;
                System.out.println("Банкова грешка. Губите 100");
                Monopoly.refreshPlayerMoney(player, -100);
            }

            case 9 -> {
                //Build taxes
                double taxPerBuild = 15*Monopoly.infGlobal;
                int n = Monopoly.plOwnerList[player][0]; int finalsum =  n* (int) taxPerBuild;
                System.out.println("За всяка от вашите сгради заплащате по " + taxPerBuild);
                System.out.println("Обща сума на данък сгради " + n + "x" + (int) taxPerBuild + "=" + finalsum);
                Monopoly.refreshPlayerMoney(player, - finalsum);
            }
            case 10 -> {
                //Build insurances
                double insPerBuild = 25*Monopoly.infGlobal;
                int n = Monopoly.plOwnerList[player][0]; int finalsum =  (int) (insPerBuild*n);
                System.out.println("Затраховате всяка от вашите сгради.");
                System.out.println("Обща сума на застраховката " + n + "x" + insPerBuild + "=" + finalsum);
                Monopoly.refreshPlayerMoney(player, - finalsum);
            }
        }

        //Refresh inflation
        Monopoly.setInflationField();
    }

    public static void  refreshPlayerCards()
    {
        for (int x=1; x<=3; x++)
        {
            String txt="";
            if (Monopoly.hadPrisonCard[x]) txt +="P";
            if (Monopoly.hadHospitalCard[x]) txt +="H";
            if (Monopoly.hadUniversityCard[x]) txt+="U";
            MapArray.mapText[21][3+x] = "#" + txt;
        }
    }

    public static void getPrisonExitCardWithNotice(int player, String notice)
    {
        if (!Monopoly.hadPrisonCard[player]) {
            printn(notice);
            Monopoly.hadPrisonCard[player] = true;
            refreshPlayerCards();
        }
    }

    public static void goPrison(int player)
    {
        //Because we come here only from other fields
        Monopoly.cntPair=0;
        Monopoly.plWhere[player]=18;
        Monopoly.movePlayersToFields();
    }

    public static void goHospital(int player)
    {
        //If we come here from other field, not from dices
        Monopoly.plWhere[player]=17;
        Monopoly.movePlayersToFields();

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
            refreshPlayerCards();
        }

    }

    public static void goUniversity(int player)
    {
        //If we come here from other field, not from dices
        Monopoly.plWhere[player]=3;
        Monopoly.movePlayersToFields();

        if (!Monopoly.hadUniversityCard[player]){
            Random rnd = new Random();
            int tax = (rnd.nextInt(6)+1)*25; //25 до 150лв
            simplePayToInstitution(player, (int) tax);

            System.out.println("Заплатихте такса за допълнително обучение " + tax + " лв");
        }
        else
        {
            System.out.println("Използвахте картата си за безплатно обучение.");
            Monopoly.hadUniversityCard[player] = false;
            refreshPlayerCards();
        }
    }

    public static void goBorGarden(int player)
    {
        Monopoly.plWhere[player]=12;
        Monopoly.movePlayersToFields();

        Random rnd = new Random();
        int tax = (rnd.nextInt(3)+1)*50; //50 до 150лв
        simplePayToInstitution(player, - tax); //Increase player money

        System.out.println("Семеен пикник. Получавате тонус за работа, което ви носи +" + tax + " лв");
    }

    public static void goChance(int player){
        Monopoly.plWhere[player]=16;
        Monopoly.movePlayersToFields();

        Random rnd = new Random();
        int x = rnd.nextInt(10)+1;
        if (x<3) {Monopoly.hadHospitalCard[player] = true;  Monopoly.hadUniversityCard[player] = true;}
        if (x>5) {Monopoly.hadHospitalCard[player] = true;}
        if (x==4||x==5) {Monopoly.hadUniversityCard[player] = true;}
        Monopoly.hadPrisonCard[player] = true;
        Institutions.refreshPlayerCards();
    }

    public static void goAirport(int player)
    {
        Monopoly.plWhere[player]=15;
        Monopoly.movePlayersToFields();

        Random rnd = new Random();
        int sum = 25*(rnd.nextInt(5)+1);
        Monopoly.refreshPlayerMoney(player, - sum); //25-125
        Monopoly.refreshPlayerMoney(Monopoly.bOwner[15], sum);
        System.out.println("Заплатихте транспортни разходи в размер на " + sum +
                " към играч " + Monopoly.plName[Monopoly.bOwner[15]]);
    }

    public static void goWaterCompany(int player)
    {
        Monopoly.plWhere[player]=19;
        Monopoly.movePlayersToFields();

        int sum = (int) (30 * Monopoly.infWater * Monopoly.infGlobal);
        Monopoly.refreshPlayerMoney(player, - sum);
        Monopoly.refreshPlayerMoney(Monopoly.bOwner[19], sum);
        System.out.println("Заплатихте разходи за вода към играч " + Monopoly.plName[Monopoly.bOwner[19]] +
                " в размер на " + sum);
    }

    public static void goEnergyCompany(int player)
    {
        Monopoly.plWhere[player]=23;
        Monopoly.movePlayersToFields();

        int sum = (int) (50 * Monopoly.infEnergy * Monopoly.infGlobal);
        Monopoly.refreshPlayerMoney(player, - sum);
        Monopoly.refreshPlayerMoney(Monopoly.bOwner[23], sum);
        System.out.println("Заплатихте енергийни разходи към играч " + Monopoly.plName[Monopoly.bOwner[23]] +
                " в размер на " + sum);
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
