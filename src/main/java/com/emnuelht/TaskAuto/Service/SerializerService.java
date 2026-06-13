package com.emnuelht.TaskAuto.Service;

public class SerializerService {
    public static String promptSerialize(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Text not empty");
        }

        return text
                .replaceAll("(?i)ignore\\s+(todas\\s+as\\s+)?instru", "")
                .replaceAll("(?i)esqueça\\s+(tudo|as instru)", "")
                .trim();
    }
}
