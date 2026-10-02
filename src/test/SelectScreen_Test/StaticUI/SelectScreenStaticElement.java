package au.edu.unimelb.swen.oop.mokepon.SelectScreen_Test.StaticUI;

import au.edu.unimelb.swen.oop.mokepon.SelectScreen_Test.SelectScreenElement;

import java.util.HashMap;

public interface SelectScreenStaticElement<T> extends SelectScreenElement {

    public default T buildElement(HashMap<String, Object> dict) {
        throw new RuntimeException("Not implemented");
    }

}
