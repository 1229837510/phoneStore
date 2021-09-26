package com.bigdata.store.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneUtil {
    public static List<Map<String,String>> createTag(String tag){
        String[] tags = tag.split("&");
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        for (String s : tags) {
            map.put("name",s);
            list.add(map);
        }
        return list;
    }
}
