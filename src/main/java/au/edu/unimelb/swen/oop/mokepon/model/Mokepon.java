package au.edu.unimelb.swen.oop.mokepon.model;


import java.util.ArrayList;


public class Mokepon {

    private String name;
    private String imgURL;
    private int maxHP;
    private int maxEnergy;
    private int baseStrength;
    private int baseAccuracy;
    private int baseDefence;
    private int baseDodge;
    private ArrayList<String> skillList;
    private MokeponType mokeponType;

    private int currentHP;
    private int currentEnergy;
    private int currentStrength;
    private int currentAccuracy;
    private int currentDefence;
    private int currentDodge;


    public Mokepon(
            String name, String imgURL, int maxHP, int maxEnergy,
            int baseStrength, int baseAccuracy, int baseDefence, int baseDodge,
            ArrayList<String> skillList, MokeponType mokeponType) {
        this.name = name;
        this.imgURL = imgURL;
        this.maxHP = maxHP;
        this.maxEnergy = maxEnergy;
        this.baseStrength = baseStrength;
        this.baseAccuracy = baseAccuracy;
        this.baseDefence = baseDefence;
        this.baseDodge = baseDodge;
        this.skillList = skillList;
        this.mokeponType = mokeponType;
    }

    public String toString() {
        String skills = String.join(", ", skillList);
        return "name : " + name +
                "\n" + "imgURL : " + imgURL +
                "\n" + "maxHP : " + maxHP +
                "\n" + "maxEnergy : " + maxEnergy +
                "\n" + "baseStrength : " + baseStrength +
                "\n" + "baseAccuracy : " + baseAccuracy +
                "\n" + "baseDefence : " + baseDefence +
                "\n" + "baseDodge : " + baseDodge +
                "\n" + "skills : " + skills;
    }

    public boolean equals(Mokepon other) {
        boolean skillsAreSame = true;
        for (int i = 0; i < this.skillList.size(); i++) {
            if (!this.skillList.get(i).equals(other.skillList.get(i))) {
                skillsAreSame = false;
                break;
            }
        }
        return this.name.equals(other.getName()) &&
                this.imgURL.equals(other.getImgURL()) &&
                skillsAreSame;
    }

    public String getName() {
        return this.name;
    }

    public String getImgURL() {
        return this.imgURL;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public int getMaxEnergy() {
        return this.maxEnergy;
    }

    public int getBaseStrength() {
        return this.baseStrength;
    }

    public int getBaseAccuracy() {
        return this.baseAccuracy;
    }

    public int getBaseDefence() {
        return this.baseDefence;
    }

    public int getBaseDodge() {
        return this.baseDodge;
    }

    public ArrayList<String> getSkills() {
        return this.skillList;
    }

    public MokeponType getMokeponType() {
        return mokeponType;
    }


    public int getCurrentHP() {
        return this.currentHP;
    }

    public int getCurrentEnergy() {
        return this.currentEnergy;
    }

    public int getCurrentStrength() {
        return this.currentStrength;
    }

    public int getCurrentAccuracy() {
        return this.currentAccuracy;
    }

    public int getCurrentDefence() {
        return this.currentDefence;
    }

    public int getCurrentDodge() {
        return currentDodge;
    }


    public void setCurrentHP(int hp) {
        if (hp < 0) hp = 0;
        this.currentHP = hp;
    }

    public void setCurrentEnergy(int energy) {
        if (energy < 0) energy = 0;
        this.currentEnergy = energy;
    }

    public void setCurrentDefence(int defence) {
        if (defence < 0) defence = 0;
        else if (defence > 100) defence = 100;
        this.currentDefence = defence;
    }


    public void takeDamage(int damage) {
        if (this.currentHP <= damage) this.currentHP = 0;
        else this.currentHP -= damage;
    }

    public void healToMax() {
        this.currentHP = this.maxHP;
    }

    public void restoreEnergyToMax() {
        this.currentEnergy = this.maxEnergy;
    }

    public void resetBattleStats() {
        healToMax();
        restoreEnergyToMax();
        this.setCurrentDefence(this.baseDefence);
    }


    public Mokepon copy() {
        return new Mokepon(
                this.name, this.imgURL, this.maxHP, this.maxEnergy,
                this.baseStrength, this.baseAccuracy, this.baseDefence, this.baseDodge,
                new ArrayList<>(this.skillList), this.mokeponType
        );
    }

}
