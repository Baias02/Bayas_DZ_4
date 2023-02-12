import java.util.Random;

public class Main {
    public static int bossHealth = 1400;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {290, 270, 250, 300,280,310,250,400};
    public static int[] heroesDamage = {20, 10, 15,0,20,25,0,10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic","Medic","Berserk", "Thor","Lucky","Golem"};
    public static int roundNumber = 0;
    public static int Hill = 30;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameFinished()) {
            playRound();
        }
    }
    public static void ChoosBossDefens(){
        Random random = new Random();
        int randomIndex =random.nextInt(heroesAttackType.length);
        bossDefence  = heroesAttackType[randomIndex];
        System.out.println("Boss choose " + bossDefence);
    }



    public static void playRound() {
        roundNumber++;
        ChoosBossDefens();
        bossHits();
        heroesHit();
        berserkShoot();
        Thor();
        printStatistics();
        medicHill();
        evasion1();
        golemDefens();
    }
    private static void medicHill() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesAttackType[i].equals("Medic")) {
                continue;
            } else if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                heroesHealth[i] += Hill;
                System.out.println(" Medic healed " + heroesAttackType[i]);
                break;
            }

        }
    }

    public static void golemDefens(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[7]>0 && heroesHealth[i]>0 && heroesHealth[7] != heroesHealth[i]){
                heroesHealth[i] += bossDamage/5;
                heroesHealth[7] -= bossDamage/5;
            }

        }
    }
    public static void evasion1(){
        Random random = new Random();
        int randomEvasion = random.nextInt(3)+1;
        switch (randomEvasion){
            case 1:
                heroesHealth[6] = heroesHealth[6] +bossDamage;
                System.out.println("Lacky");
            case 2:
            case 3:
            case 4:

        }
    }
    public static void Thor(){
        Random random = new Random();
        boolean stun = random.nextBoolean();
        if (stun){
            bossDamage = 0;
            System.out.println("Boss oglushon");
        }else {
            bossDamage = 50;
        }
    }
    public static void berserkShoot(){
        Random random = new Random();
        int randomDamage = random.nextInt(20) + 1;
        int randomB =random.nextInt(3) +1;
        if (heroesHealth[4]>0 && bossHealth>0){
            switch (randomB){
                case 1:
                    heroesDamage[4]=( heroesDamage[4] +bossDamage) - randomDamage;
                    System.out.println("Берсерк урон критический");
                    System.out.println("Потери при увеличении урон а Берсерка " + randomDamage);
                    break;
                case 2:
                    bossDamage = 50;
                    break;
                case 3:
                    bossDamage =50;
                    break;
            }

        }

    }



    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; //2,3,4,5,6,7,8,9,10
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ------------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " +
                (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);


        }


    }




    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;




    }


}