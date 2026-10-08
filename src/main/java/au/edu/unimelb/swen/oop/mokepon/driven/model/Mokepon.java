package au.edu.unimelb.swen.oop.mokepon.driven.model;


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

    private MokeponStatus mokeponStatus;


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
        this.mokeponStatus = null;
    }

    public boolean equals(Mokepon other) {
        boolean skillsAreSame = true;
        for (int i = 0; i < skillList.size(); i++) {
            if (!skillList.get(i).equals(other.skillList.get(i))) {
                skillsAreSame = false;
                break;
            }
        }
        return this.name.equals(other.getName()) &&
                imgURL.equals(other.getImgURL()) &&
                skillsAreSame;
    }

    public String getName() {
        return name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public int getBaseAccuracy() {
        return baseAccuracy;
    }

    public int getBaseDefence() {
        return baseDefence;
    }

    public int getBaseDodge() {
        return baseDodge;
    }

    public ArrayList<String> getSkills() {
        return skillList;
    }

    public MokeponType getMokeponType() {
        return mokeponType;
    }

    public MokeponStatus getMokeponStatus() {
        return mokeponStatus;
    }



    public void setMokeponStatus() {
        mokeponStatus = new MokeponStatus(maxHP, maxEnergy,
                baseStrength, baseAccuracy, baseDefence, baseDodge,
                skillList);
    }

    public void clearMokeponStatus() {
        mokeponStatus = null;
    }

    public void resetBattleStatus() {
        clearMokeponStatus();
        setMokeponStatus();
    }

    public Mokepon copy() {
        return new Mokepon(name, imgURL, maxHP, maxEnergy,
                baseStrength, baseAccuracy, baseDefence, baseDodge,
                new ArrayList<>(skillList), mokeponType);
    }

}
