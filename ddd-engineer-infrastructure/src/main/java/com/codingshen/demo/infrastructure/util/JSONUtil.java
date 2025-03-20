package com.codingshen.demo.infrastructure.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.google.common.base.Strings;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JSONUtil {
    private static ObjectMapper objectMapper;
    private static ObjectMapper nonNullObjectMapper;

    static {
        objectMapper = JsonMapper.builder().enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER).enable(
                MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES).disable(new SerializationFeature[]{SerializationFeature.FAIL_ON_EMPTY_BEANS}).build();
        nonNullObjectMapper = JsonMapper.builder().enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER).disable(
                new SerializationFeature[]{SerializationFeature.FAIL_ON_EMPTY_BEANS}).serializationInclusion(JsonInclude.Include.NON_NULL).build();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        objectMapper.registerModule(javaTimeModule);
        objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

        nonNullObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        nonNullObjectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        nonNullObjectMapper.registerModule(javaTimeModule);
        nonNullObjectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
    }

    public static <T> String toJSONString(T object) {
        try {
            if (object == null) {
                return null;
            }
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw buildRuntimeException(e);
        }
    }

    public static <T> String toNonNullJSONString(T object) {
        try {
            if (object == null) {
                return null;
            }
            return nonNullObjectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw buildRuntimeException(e);
        }
    }

    public static <T> T parseObject(String json, Class<T> clazz) {
        if (json != null && json.length() != 0) {
            try {
                return objectMapper.readValue(json, clazz);
            } catch (Exception e) {
                throw buildRuntimeException(e);
            }
        } else {
            return null;
        }
    }

    public static <T> T parseObject(String json, TypeReference<T> typeReference) {
        if (json != null && json.length() != 0) {
            try {
                return objectMapper.readValue(json, typeReference);
            } catch (Exception e) {
                throw buildRuntimeException(e);
            }
        } else {
            return null;
        }
    }

    public static <T> T parseObject(String json, Type type) {
        if (json != null && json.length() != 0) {
            try {
                return objectMapper.readValue(json, objectMapper.getTypeFactory().constructType(type));
            } catch (Exception e) {
                throw buildRuntimeException(e);
            }
        } else {
            return null;
        }
    }

    public static <T> T parseObject(JsonNode json, Class<T> clazz) {
        if (json == null) {
            return null;
        } else {
            try {
                return objectMapper.treeToValue(json, clazz);
            } catch (Exception e) {
                throw buildRuntimeException(e);
            }
        }
    }

    public static Map<String, Object> parseMap(String json) {
        return parseObject(json, new TypeReference<>() {
        });
    }

    public static <T> List<T> parseList(String json, Class<T> clazz) {
        if (Strings.isNullOrEmpty(json)) {
            return new ArrayList<>();
        } else {
            try {
                JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
                return objectMapper.readValue(json, javaType);
            } catch (Exception var3) {
                throw buildRuntimeException(var3);
            }
        }
    }

    public static Map<String, Object> toMap(Object obj) {
        return objectMapper.convertValue(obj, new TypeReference<>() {
        });
    }

    public static Map<String, Object> jsonToMap(String json) {
        return Strings.isNullOrEmpty(json) ? null : parseObject(json, new TypeReference<>() {
        });
    }

    public static JsonNode readTree(String json) {
        if (json != null && json.length() != 0) {
            try {
                return objectMapper.readTree(json);
            } catch (Exception var2) {
                throw buildRuntimeException(var2);
            }
        } else {
            return null;
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <K, V> JsonNode convertValue(Map<K, V> map) {
        return (JsonNode) objectMapper.convertValue(map, JsonNode.class);
    }

    public static JsonNode convertValue(List<?> list) {
        return (JsonNode) objectMapper.convertValue(list, JsonNode.class);
    }

    public static <T> T convertObj(Object obj, TypeReference<T> typeReference) {
        return objectMapper.convertValue(obj, typeReference);
    }

    public static <T> T convertObj(Object obj, Class<T> clazz) {
        return objectMapper.convertValue(obj, clazz);
    }

    public static <T> Object getValueFromJSON(String json, String key, Class<T> clazz) {
        JsonNode value = getValueFromJSON(json, key);
        if (value == null) {
            return null;
        } else {
            try {
                if (clazz.equals(Boolean.class)) {
                    return value.asBoolean();
                }

                if (clazz.equals(Character.class)) {
                    return value.asText().charAt(0);
                }

                if (clazz.equals(Byte.class)) {
                    return Byte.valueOf(value.asText());
                }

                if (clazz.equals(Short.class)) {
                    return (short) value.asInt();
                }

                if (clazz.equals(Integer.class)) {
                    return value.asInt();
                }

                if (clazz.equals(Long.class)) {
                    return value.asLong();
                }

                if (clazz.equals(Float.class)) {
                    return (float) value.asDouble();
                }

                if (clazz.equals(Double.class)) {
                    return value.asDouble();
                }

                if (clazz.equals(Void.class)) {
                    return null;
                }

                if (clazz.equals(String.class)) {
                    return value.asText();
                }

                if (Map.class.isAssignableFrom(clazz) || Collection.class.isAssignableFrom(clazz)) {
                    throw new RuntimeException("incompatible type");
                }
            } catch (Exception var5) {
                throw buildRuntimeException(var5);
            }

            return parseObject(value.toString(), clazz);
        }
    }

    public static <T> T getValueFromJSON(String json, String key, TypeReference<T> typeReference) {
        JsonNode value = getValueFromJSON(json, key);
        return value == null ? null : parseObject(value.toString(), typeReference);
    }

    public static <T> T treeToValue(TreeNode node, Class<T> clazz) {
        try {
            return objectMapper.treeToValue(node, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonNode objectToJsonNode(Object o) {
        return objectMapper.valueToTree(o);
    }

    private static JsonNode getValueFromJSON(String json, String key) {
        if (json != null && json.length() != 0) {
            JsonNode jsonNode = readTree(json);
            if (jsonNode == null) {
                return null;
            } else {
                return jsonNode.findValue(key);
            }
        } else {
            return null;
        }
    }

    private static RuntimeException buildRuntimeException(Exception e) {
        return new RuntimeException(e.getMessage(), e.getCause());
    }
}
