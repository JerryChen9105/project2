package project1.Backend;

import static project1.Backend.RandomMethod.*;

public class Mokepon {

    protected String name;
    protected String imgURL;
    protected int hp;
    protected int energy;
    protected int strength;
    protected int accuracy;
    protected int defence;
    protected int dodge;
    protected String[] skills;

    protected final static String[] logTextArr = {
            "They get a little more bored.",
            "They wonder if this is a good strategy.",
            "They think about what they will have for dinner."
    };

    protected final static int maxDefence = 100;


    // Mokepon initializer
    public Mokepon() {
        this.name = null;
        this.imgURL = null;
        this.hp = -1;
        this.energy = -1;
        this.strength = -1;
        this.accuracy = -1;
        this.defence = -1;
        this.dodge = -1;
        this.skills = null;
    }

    public Mokepon(String name, String imgURL, int hp, int energy, int strength,
                   int accuracy, int defence, int dodge, String skillStr) {
        if (name == null || imgURL == null) {
            this.name = null;
            this.imgURL = null;
            this.hp = -1;
            this.energy = -1;
            this.strength = -1;
            this.accuracy = -1;
            this.defence = -1;
            this.dodge = -1;
            this.skills = null;
            return;
            }
        this.name = name;
        this.imgURL = imgURL;
        this.hp = hp;
        this.energy = energy;
        this.strength = strength;
        this.accuracy = accuracy;
        this.defence = defence;
        this.dodge = dodge;
        this.skills = skillStr.split("\\|");
    }

    public void attrCopy(Mokepon mokepon) {
        this.name = mokepon.getName();
        this.imgURL = mokepon.getImgURL();
        this.hp = mokepon.getHP();
        this.energy = mokepon.getEnergy();
        this.strength = mokepon.getStrength();
        this.accuracy = mokepon.getAccuracy();
        this.defence = mokepon.getDefence();
        this.dodge = mokepon.getDodge();
        this.skills = mokepon.getSkills();
    }

    public boolean isEmpty() {
        return this.name == null || this.imgURL == null || this.skills == null
                || (this.hp == -1 && this.energy == -1 && this.strength == -1
                && this.accuracy == -1 && this.defence == -1 && this.dodge == -1);
    }

    // getter method
    public String getName() {
        return this.name;
    }

    public String getImgURL() {
        return this.imgURL;
    }

    public int getHP() {
        return this.hp;
    }

    public int getEnergy() {
        return this.energy;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public int getDefence() {
        return this.defence;
    }

    public int getDodge() {
        return this.dodge;
    }

    public String[] getSkills() {
        return this.skills;
    }

    // setter method
    public void setHP(int newHP) {
        this.hp = newHP;
    }

    public void setEnergy(int newEnergy) {
        this.energy = newEnergy;
    }

    public void setDefence(int newDefence) {
        this.defence = newDefence;
    }


    // attribute interaction method
    public int bite(Mokepon enemy) {
        if (this.energy < 1) return -1;

        double hitProb = Math.pow(this.accuracy, 2) / (Math.pow(this.accuracy, 2) + Math.pow(enemy.getDodge(), 2));

        int damage = (this.strength * 50) / enemy.getDefence();

        this.energy -= 1;
        if (hitProb > randomDouble(0.0, 1.0)) {
            enemy.setHP(enemy.getHP() - damage);
            return damage;
        } else {
            return 0;
        }
    }

    public int tackle(Mokepon enemy) {
        if (this.energy < 5) return -1;

        double hitProb = Math.pow(this.accuracy, 2) / (Math.pow(this.accuracy, 2) + Math.pow(enemy.getDodge(), 2));
        int heavyDamage = (2 * this.strength * 50) / enemy.getDefence();

        this.energy -= 5;
        if (hitProb > randomDouble(0.0, 1.0)) {
            enemy.setHP(enemy.getHP() - heavyDamage);
            return heavyDamage;
        } else {
            return 0;
        }
    }

    public int bunker() {
        if (this.energy < 3) return -1;


        if (this.defence >= maxDefence) {
            this.defence = maxDefence;
            return 0;
        }

        int defenceIncrement = randomInt(0, 10);
        int diff = maxDefence - this.defence;

        this.energy -= 3;
        if (diff > defenceIncrement) {
            this.defence += defenceIncrement;
            return defenceIncrement;
        } else {
            this.defence += diff;
            return diff;
        }
    }

    public String waitEnergy(Mokepon origin) {
        if (this.energy >= origin.getEnergy()) return "";

        int randomIndex = randomInt(0, 3);
        if (this.energy < origin.getEnergy()) {
            this.energy += 2;
        }
        if (this.energy > origin.getEnergy()) this.energy = origin.getEnergy();

        return logTextArr[randomIndex];
    }

}
