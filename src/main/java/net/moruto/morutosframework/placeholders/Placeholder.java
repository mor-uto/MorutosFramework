package net.moruto.morutosframework.placeholders;

import java.util.HashMap;

public interface Placeholder {
    HashMap<String, Object> placeholder = new HashMap<>();
    String placeholderName = null;

    default void setPlaceholder(String placeholderName, Object object) {
        this.placeholder.put(placeholderName, object);
    }

    default String getName() {
        return placeholderName;
    }

    default Object getValue() {
        return placeholder.get(placeholderName);
    }
}
