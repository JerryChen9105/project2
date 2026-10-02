package au.edu.unimelb.swen.oop.mokepon.SelectScreen_Test.DynamicUI;

import au.edu.unimelb.swen.oop.mokepon.SelectScreen_Test.SelectScreenElement;

import java.util.HashMap;

public interface SelectScreenDynamicElement<T, V> extends SelectScreenElement {

    public default T buildElement(HashMap<String, Object> dict) {
        throw new RuntimeException("Not implemented");
    }

    public default V getInnerElement() {
        throw new RuntimeException("Not implemented");
    }

}
