package net.moruto.morutosframework.placeholders;

import java.util.HashMap;

public interface Placeholder {
    placeholder setPlaceholder();

    default String getName() {
        return setPlaceholder().name();
    }

    default Object getValue() {
        return setPlaceholder().object();
    }

    record placeholder(String name, Object object) {
        @Override
        public String name() {
            return name;
        }
        @Override
        public Object object() {
            return object;
        }
    }
}
