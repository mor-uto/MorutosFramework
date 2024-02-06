package net.moruto.morutosframework.utils;

import net.moruto.morutosframework.annotations.Incomplete;

import java.lang.reflect.Field;

@Incomplete
public interface JSONSerializable {
    default String serialize() {
        StringBuilder json = new StringBuilder("{");

        for (Field field : this.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                json.append("\"").append(field.getName()).append("\":");

                if (field.getType().isPrimitive() || field.getType() == String.class) {
                    json.append("\"").append(field.get(this)).append("\",");
                } else {
                    JSONSerializable subObject = (JSONSerializable) field.get(this);
                    json.append(subObject.serialize()).append(",");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if (json.charAt(json.length() - 1) == ',') {
            json.setLength(json.length() - 1);
        }

        json.append("}");

        return json.toString();
    }
}
