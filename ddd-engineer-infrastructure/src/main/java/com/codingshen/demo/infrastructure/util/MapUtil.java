package com.codingshen.demo.infrastructure.util;

import java.util.Map;

public class MapUtil {

	public static <K, V> V getFromMap(K key, Map<K, V> value) {
		if (key == null || value == null) {
			return null;
		}

		return value.get(key);
	}

	public static <K1, K2, V> V getFromMap(K1 key1, K2 key2, Map<K1, Map<K2, V>> value) {
		if (key1 == null || key2 == null || value == null) {
			return null;
		}
		return getFromMap(key2, getFromMap(key1, value));
	}
}
