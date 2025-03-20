package com.codingshen.demo.infrastructure.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListUtil {
    public static<M, N> List<N> convert(Collection<M> list, Function<? super M, ? extends N> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().filter(Objects::nonNull).map(mapper)
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static<M, N> Set<N> convert2Set(List<M> list, Function<? super M, ? extends N> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return Sets.newHashSet();
        }
        return list.stream().filter(Objects::nonNull).map(mapper)
                .filter(Objects::nonNull).collect(Collectors.toSet());
    }

    public static<T> List<T> distinct(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

    // 去重依赖对象equals方法
    public static<M, N> List<N> convertWithDistinct(List<M> list, Function<? super M, ? extends N> mapper) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().filter(Objects::nonNull).map(mapper)
                .distinct().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static<T> List<T> filter(List<T> list, Predicate<? super T> filter) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }

        return list.stream().filter(filter).collect(Collectors.toList());
    }

    public static<T> T filterFirst(List<T> list, Predicate<? super T> filter) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<T> filters = list.stream().filter(filter).collect(Collectors.toList());
        if (filters.isEmpty()) {
            return null;
        }
        return filters.get(0);
    }

    public static<M, N> Map<N, M> toMap(Collection<M> list, Function<? super M, ? extends N> keyMapper) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }

        return list.stream().filter(Objects::nonNull).collect(Collectors.toMap(keyMapper, o -> o, (k1, k2) -> k1));
    }

    public static<K, T, U> Map<K, List<U>> convertMapList(Map<K, List<T>> map, Function<? super T, ? extends U> valueMapper) {
        Map<K, List<U>> convertMap = Maps.newHashMap();
        if (map == null) {
            return convertMap;
        }
        map.forEach((k ,valueList) -> {
            convertMap.put(k, convert(valueList, valueMapper));
        });

        return convertMap;
    }

    public static<K, T, U> Map<K, U> convertMap(Map<K, T> map, Function<? super T, ? extends U> valueMapper) {
        Map<K, U> convertMap = Maps.newHashMap();
        if (map == null) {
            return convertMap;
        }
        map.forEach((k ,value) -> {
            convertMap.put(k, valueMapper.apply(value));
        });

        return convertMap;
    }

    public static<T, K, U> Map<K, U> toMap(Collection<T> list, Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }

        return list.stream().filter(Objects::nonNull).collect(Collectors.toMap(keyMapper, valueMapper, (k1, k2) -> k1));
    }

    public static<T, K> Map<K, List<T>> groupBy(List<T> list, Function<? super T, ? extends K> classifier) {
        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }

        return list.stream().collect(Collectors.groupingBy(classifier));
    }

    public static<T> boolean anyMatch(List<T> list, Predicate<? super T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }

        return list.stream().anyMatch(predicate);
    }

    public static<T> boolean noneMatch(List<T> list, Predicate<? super T> predicate) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }

        return list.stream().noneMatch(predicate);
    }

    public static<T> List<T> singletonList(T o){
        List<T> res = Lists.newArrayListWithExpectedSize(1);
        if (o != null){
            res.add(o);
        }
        return res;
    }

    public static boolean hasDuplicate(List<Integer> list){
        if (CollectionUtils.isEmpty(list) || list.size() == 1){
            return false;
        }

        Set<Integer> set = Sets.newHashSet(list);
        return list.size() != set.size();
    }

    public static <T> List<T> flat(Collection<List<T>> lists) {
        List<T> list = Lists.newArrayList();
        for (List<T> ts : lists) {
            list.addAll(ts);
        }
        return list;
    }

    public static <T> List<T> flatWithDistinct(Collection<List<T>> lists) {
        List<T> list = Lists.newArrayList();
        for (List<T> ts : lists) {
            list.addAll(ts);
        }
        return distinct(list);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
