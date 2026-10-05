package au.edu.unimelb.swen.oop.mokepon.driven.model.effect;

import java.util.Map;

public class EffectRegistry {

    private static Map<String, Effect> effectMap = Map.of(
            "Bleed", new BleedEffect(),
            "Blind", new BlindEffect(),
            "Stun", new StunEffect()
    );

    private static Map<String, String> effectEndMap = Map.of(
            "Bleed", "The bleeding stopped.",
            "Blind", "",
            "Stun", ""
    );

    public static Effect getEffect(String str) {
        return effectMap.get(str);
    }

    public static String getEffectEndMessage(String str) {
        return effectEndMap.get(str);
    }

}
