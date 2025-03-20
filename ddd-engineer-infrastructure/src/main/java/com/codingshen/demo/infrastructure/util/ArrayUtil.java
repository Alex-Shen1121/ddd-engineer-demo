package com.codingshen.demo.infrastructure.util;

import java.math.BigDecimal;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ArrayUtil {

	public static <T, D> Set<D> getValueSet(Collection<T> list, Function<T, D> valueGetter) {
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptySet();
		}
		Set<D> values = new HashSet<>();
		for (T t : list) {
			D value = t == null ? null : valueGetter.apply(t);
			if (value != null) {
				values.add(value);
			}
		}
		return values;
	}

	public static <T> Set<Long> getIdSet(Collection<T> list, Function<T, Long> idGetter) {
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptySet();
		}
		Set<Long> values = new HashSet<>();
		for (T t : list) {
			Long value = t == null ? null : idGetter.apply(t);
			if (value != null && value > 0) {
				values.add(value);
			}
		}
		return values;
	}

	public static <T> Set<Long> getIdSet(Collection<T> list, Function<T, Long> idGetter1, Function<T, Long> idGetter2) {
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptySet();
		}
		Set<Long> values = new HashSet<>();
		for (T t : list) {
			if (t == null) {
				continue;
			}
			Long value1 = idGetter1.apply(t);
			Long value2 = idGetter2.apply(t);
			if (value1 != null && value1 > 0) {
				values.add(value1);
			}
			if (value2 != null && value2 > 0) {
				values.add(value2);
			}
		}
		return values;
	}

	public static <T> long getMaxId(Collection<T> list, Function<T, Long> idGetter) {
		if (CollectionUtils.isEmpty(list)) {
			return 0;
		}
		long maxId = 0;
		for (T t : list) {
			Long value = t == null ? null : idGetter.apply(t);
			if (value != null && value > maxId) {
				maxId = value;
			}
		}
		return maxId;
	}

	public static <T, K> Map<K, T> toMap(Collection<T> list, Function<T, K> keyGetter) {
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyMap();
		}
		Map<K, T> map = new HashMap<>();
		for (T t : list) {
			if (t != null) {
				map.put(keyGetter.apply(t), t);
			}
		}
		return map;
	}

	public static <T, K, V> Map<K, T> toMap(Map<K, V> map, Function<V, T> valueGetter) {
		if (map == null || map.isEmpty()) {
			return Collections.emptyMap();
		}
		Map<K, T> dstMap = new HashMap<>();
		for (Map.Entry<K, V> entry : map.entrySet()) {
			dstMap.put(entry.getKey(), valueGetter.apply(entry.getValue()));
		}
		return dstMap;
	}

	public static <T> BigDecimal sum(Collection<T> list, Function<T, BigDecimal> valueGetter) {
		if (CollectionUtils.isEmpty(list)) {
			return BigDecimal.ZERO;
		}
		BigDecimal count = BigDecimal.ZERO;
		for (T t : list) {
			if (t != null) {
				BigDecimal v = valueGetter.apply(t);
				if (v != null) {
					count = count.add(v);
				}
			}
		}
		return count;
	}

	@SafeVarargs
	public static <T, K> Map<K, T> toMap(Function<T, K> keyGetter, Collection<T>... lists) {
		if (lists == null || lists.length < 1) {
			return Collections.emptyMap();
		}
		Map<K, T> map = new HashMap<>();
		for (Collection<T> list : lists) {
			if (CollectionUtils.isEmpty(list)) {
				continue;
			}
			for (T t : list) {
				if (t != null) {
					map.put(keyGetter.apply(t), t);
				}
			}
		}
		return map;
	}

	public static <T, K> Map<K, List<T>> toListMap(Collection<T> list, Function<T, K> keyGetter) {
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyMap();
		}
		Map<K, List<T>> map = new HashMap<>();
		for (T t : list) {
			if (t == null) {
				continue;
			}
			K key = keyGetter.apply(t);
			List<T> values = map.computeIfAbsent(key, k -> new ArrayList<>());
			values.add(t);
		}
		return map;
	}

	public static <T, K> Map<K, List<T>> toListMap(Collection<T> list,
			Predicate<? super T> keepCondition,
			Function<T, K> keyGetter) {
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyMap();
		}
		Map<K, List<T>> map = new HashMap<>();
		for (T t : list) {
			if (t == null || !keepCondition.test(t)) {
				continue;
			}
			K key = keyGetter.apply(t);
			List<T> values = map.computeIfAbsent(key, k -> new ArrayList<>());
			values.add(t);
		}
		return map;
	}

	public static <T, K, V> Map<K, List<V>> toListMap(Collection<T> list, Function<T, K> keyGetter,
			Function<T, V> valueGetter) {
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyMap();
		}
		Map<K, List<V>> map = new HashMap<>();
		for (T t : list) {
			if (t == null) {
				continue;
			}
			K key = keyGetter.apply(t);
			V value = valueGetter.apply(t);
			if (value != null) {
				List<V> values = map.computeIfAbsent(key, k -> new ArrayList<>());
				values.add(value);
			}
		}
		return map;
	}

	public static <T, K, V> Map<K, V> toMap(Collection<T> list, Function<T, K> keyGetter, Function<T, V> valueGetter) {
		if (CollectionUtils.isEmpty(list)) {
			return Collections.emptyMap();
		}
		Map<K, V> map = new HashMap<>();
		for (T t : list) {
			if (t != null) {
				map.put(keyGetter.apply(t), valueGetter.apply(t));
			}
		}
		return map;
	}

	/**
	 * 把容器转换为List，并过滤掉null数据
	 */
	public static <T, R> List<R> toList(Collection<T> collection, Function<T, R> valueGetter) {
		if (CollectionUtils.isEmpty(collection)) {
			return Collections.emptyList();
		}
		List<R> list = new ArrayList<>(collection.size());
		for (T t : collection) {
			if (t != null) {
				R r = valueGetter.apply(t);
				if (r != null) {
					list.add(r);
				}
			}
		}
		return list;
	}

	/**
	 * 把容器转换为List，并保留null数据
	 */
	public static <T, R> List<R> toListKeepNull(Collection<T> collection, Function<T, R> valueGetter) {
		if (CollectionUtils.isEmpty(collection)) {
			return Collections.emptyList();
		}
		List<R> list = new ArrayList<>(collection.size());
		for (T t : collection) {
			list.add(t == null ? null : valueGetter.apply(t));
		}
		return list;
	}

	/**
	 * 把容器转换为List，并保留null数据
	 */
	public static <T, R> List<R> toListKeepNull(Iterable<T> iterable, Function<T, R> valueGetter) {
		if (iterable == null || valueGetter == null) {
			return new ArrayList<>();
		}
		List<R> list = new ArrayList<>();
		for (T t : iterable) {
			list.add(t == null ? null : valueGetter.apply(t));
		}
		return list;
	}

	/**
	 * 把容器转换为List，并过滤掉null数据
	 */
	public static <T, R> List<R> toList(Iterable<T> iterable, Function<T, R> valueGetter) {
		if (iterable == null || valueGetter == null) {
			return new ArrayList<>();
		}
		List<R> list = new ArrayList<>();
		for (T t : iterable) {
			if (t != null) {
				R r = valueGetter.apply(t);
				if (r != null) {
					list.add(r);
				}
			}
		}
		return list;
	}

	public static <T> List<T> toList(Collection<? extends T> collection) {
		return toList(collection, t -> t);
	}

	/**
	 * 把容器转换为List，并过滤掉null数据
	 */
	public static <T, R> List<R> toList(T[] array, Function<T, R> valueGetter) {
		if (array == null) {
			return Collections.emptyList();
		}
		List<R> list = new ArrayList<>(array.length);
		for (T t : array) {
			if (t != null) {
				R r = valueGetter.apply(t);
				if (r != null) {
					list.add(r);
				}
			}
		}
		return list;
	}

	/**
	 * 数据过滤
	 */
	public static <T> List<T> filterList(Collection<T> collection, Predicate<? super T> keepCondition) {
		if (CollectionUtils.isEmpty(collection)) {
			return Collections.emptyList();
		}
		List<T> list = new ArrayList<>(collection.size());
		for (T t : collection) {
			if (t != null && keepCondition.test(t)) {
				list.add(t);
			}
		}
		return list;
	}

	public static <T> void filter(Iterable<T> iterable, Predicate<? super T> keepCondition) {
		Iterator<T> it = iterable.iterator();
		while (it.hasNext()) {
			T t = it.next();
			boolean keep = keepCondition.test(t);
			if (!keep) {
				it.remove();
			}
		}
	}

	/**
	 * 按源数据顺序排序目标数据
	 *
	 * @param targetList 目标数据
	 * @param sourceList 源数据
	 */
	public static <T, S, K> List<T> sortList(Collection<T> targetList, Collection<S> sourceList,
			Function<T, K> targetKeyGetter, Function<S, K> sourceKeyGetter) {
		if (CollectionUtils.isEmpty(targetList)) {
			return Collections.emptyList();
		}
		Map<K, T> dataMap = toMap(targetList, targetKeyGetter);
		return toList(sourceList, s -> dataMap.get(sourceKeyGetter.apply(s)));
	}

	@SafeVarargs
	public static <T> List<T> toListDistinct(List<T>... lists) {
		Set<T> set = new HashSet<>();
		List<T> ret = new ArrayList<>();
		for (List<T> list : lists) {
			if (CollectionUtils.isEmpty(list)) {
				continue;
			}
			for (T t : list) {
				if (t != null && set.add(t)) {
					ret.add(t);
				}
			}
		}
		return ret;
	}

	public static <T> String joinToString(Collection<T> collection, Function<T, Object> valueGetter, String split) {
		if (CollectionUtils.isEmpty(collection)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (T t : collection) {
			if (t == null) {
				continue;
			}
			Object v = valueGetter.apply(t);
			if (v != null) {
				if (sb.length() > 0) {
					sb.append(split);
				}
				sb.append(v);
			}
		}
		return sb.toString();
	}

	/**
	 * 获取符合条件的非null元素个数
	 */
	public static <T> int countNotNull(Collection<T> collection, Predicate<? super T> predicate) {
		if (CollectionUtils.isEmpty(collection)) {
			return 0;
		}
		int num = 0;
		for (T t : collection) {
			if (t != null && predicate.test(t)) {
				num++;
			}
		}
		return num;
	}

	/**
	 * 批量处理数据
	 */
	public static <T> int batchProcess(List<T> dataList, int batchSize, BiConsumer<List<T>, Integer> processor) {
		if (CollectionUtils.isEmpty(dataList) || processor == null || batchSize < 1) {
			return 0;
		}
		if (dataList.size() <= batchSize) {
			processor.accept(dataList, 0);
			return 1;
		}
		int off = 0;
		int processCount = 0;
		while (off < dataList.size()) {
			int processSize = Math.min(batchSize, dataList.size() - off);
			List<T> sub = dataList.subList(off, off + processSize);
			processor.accept(sub, off);
			off += processSize;
			processCount++;
		}
		return processCount;
	}

	public static <T> Function<T, String> nullToEmpty(Function<T, String> valueGetter) {
		return t -> {
			if (t == null) {
				return "";
			}
			String str = valueGetter.apply(t);
			return str == null ? "" : str;
		};
	}

	public static String nullToEmpty(String str) {
		return str == null ? "" : str;
	}
}
