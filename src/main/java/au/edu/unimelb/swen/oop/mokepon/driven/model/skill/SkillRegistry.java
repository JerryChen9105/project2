package au.edu.unimelb.swen.oop.mokepon.driven.model.skill;

import java.util.Map;

public class SkillRegistry {

    private static final Map<String, Skill> skillMap = Map.of(
            "Bite", new BiteSkill(),
            "Tackle", new TackleSkill(),
            "Bunker", new BunkerSkill(),
            "Wait", new WaitSkill(),
            "Aim", new AimSkill(),
            "Swole", new SwoleSkill(),
            "Nimble", new NimbleSkill(),
            "Lance", new LanceSkill(),
            "Stun", new StunSkill(),
            "Pocket Sand", new PocketSandSkill()
    );

    public static Skill getSkill(String name) {
        return skillMap.get(name);
    }


}


