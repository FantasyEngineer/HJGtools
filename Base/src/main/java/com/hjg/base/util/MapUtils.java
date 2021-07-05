package com.hjg.base.util;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 在添加元素时，如果出现相同的键，那么后添加的值会覆盖原有键对应的值（value），
 */
public class MapUtils {

    /**
     * 创建一个hashmap（常用的map，根据key的hashcode值来存储数据，根据key可以获取value，具有很快的访问速度，只允许有一个key值为NUll的记录，多条会被覆盖）
     *
     * @return
     */
    public static HashMap newHashMap() {
        return new HashMap();
    }


    /**
     * 能够把它保存的记录根据key排序,默认是按升序排序，也可以指定排序的比较器，当用Iterator 遍历TreeMap时，得到的记录是排过序的。TreeMap不允许key的值为null。非同步的。
     *
     * @return
     */
    public static TreeMap newTreeMap() {
        return new TreeMap();
    }


    /**
     * 降序排列的TreeMap（可以根据需要修改其方法）
     *
     * @return
     */
    public static TreeMap newTreeMap1() {
        return new TreeMap<Integer, String>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
    }


    public static Collection getKey(Map map) {
        return map.keySet();
    }


    /**
     * 获取所有的value
     *
     * @param map
     * @return
     */
    public static Collection getValue(Map map) {
        return map.values();
    }

    /**
     * 根据value获取到key
     *
     * @param map
     * @param value
     * @return
     */
    public static List<Object> getKey(Map map, Object value) {
        List<Object> keyList = new ArrayList<>();
        for (Object key : map.keySet()) {
            if (map.get(key).equals(value)) {
                keyList.add(key);
            }
        }
        return keyList;
    }

    /**
     *遍历map
     *     Iterator iterable = classMap.entrySet().iterator();
     *         while (iterable.hasNext()) {
     *             Map.Entry entry = (Map.Entry) iterable.next();
     *             dataList.add(entry.getKey());
     *         }
     */


}
