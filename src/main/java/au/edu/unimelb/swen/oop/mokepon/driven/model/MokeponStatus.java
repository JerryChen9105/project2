package au.edu.unimelb.swen.oop.mokepon.driven.model;

import au.edu.unimelb.swen.oop.mokepon.driven.battle.BattleContext;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.SkillRegistry;
import au.edu.unimelb.swen.oop.mokepon.driven.model.effect.Effect;
import au.edu.unimelb.swen.oop.mokepon.driven.model.skill.Skill;

import java.util.ArrayList;
import java.util.Iterator;

public class MokeponStatus {

    private String playerID = "player";
    private int currentHP;
    private int currentEnergy;
    private int currentStrength;
    private int currentAccuracy;
    private int currentDefence;
    private int currentDodge;
    private ArrayList<Skill> skillList;
    private ArrayList<Effect> effectList;


    public MokeponStatus(int currentHP, int currentEnergy,
                         int currentStrength, int currentAccuracy,
                         int currentDefence, int currentDodge,
                         ArrayList<String> skillList) {
        this.currentHP = currentHP;
        this.currentEnergy = currentEnergy;
        this.currentStrength = currentStrength;
        this.currentAccuracy = currentAccuracy;
        this.currentDefence = currentDefence;
        this.currentDodge = currentDodge;
        this.skillList = new ArrayList<>();
        skillList.forEach(skillStr -> {
            this.skillList.add(SkillRegistry.getSkill(skillStr));
        });
        this.effectList = new ArrayList<>();
    }


    public int getCurrentHP() {
        return currentHP;
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public int getCurrentStrength() {
        return currentStrength;
    }

    public int getCurrentAccuracy() {
        return currentAccuracy;
    }

    public int getCurrentDefence() {
        return currentDefence;
    }

    public int getCurrentDodge() {
        return currentDodge;
    }

    public ArrayList<Skill> getSkillList() {
        return skillList;
    }

    public ArrayList<Effect> getEffectList() {
        return effectList;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setCurrentHP(int hp) {
        currentHP = hp;
    }

    public void setCurrentEnergy(int energy) {
        currentEnergy = energy;
    }

    public void setCurrentStrength(int strength) {
        currentStrength = strength;
    }

    public void setCurrentAccuracy(int accuracy) {
        currentAccuracy = accuracy;
    }

    public void setCurrentDefence(int defence) {
        currentDefence = defence;
    }

    public void setCurrentDodge(int dodge) {
        currentDodge = dodge;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void takeDamage(int damage) {
        if (currentHP <= damage) currentHP = 0;
        else currentHP -= damage;
    }

    public void addEffect(Effect effect) {
        effectList.add(effect);
    }

    public boolean hasEffect(String effectName) {
        for (Effect effect : effectList) {
            if (effect.getName().equals(effectName) && !effect.isExpired()) return true;
        }
        return false;
    }

    public void updateEffect(Mokepon owner, BattleContext battleContext) {

        ArrayList<Effect> expiredEffects = new ArrayList<>();
        effectList.forEach(effect -> {
            effect.onTurnEnd(owner.getMokeponStatus(), battleContext);
            if (effect.isExpired()) expiredEffects.add(effect);
        });
        expiredEffects.forEach(effect -> {
            effectList.remove(effect);
        });

    }

}


