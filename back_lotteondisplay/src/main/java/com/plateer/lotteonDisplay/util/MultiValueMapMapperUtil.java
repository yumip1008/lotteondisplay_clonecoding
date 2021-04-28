package com.plateer.lotteonDisplay.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class MultiValueMapMapperUtil {

    public static MultiValueMap<String, String> convertFromObject(Object object){
        ObjectMapper mapper = new ObjectMapper();

        Map<String, String> map = mapper.convertValue(object, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String,String> multiValueMap = new LinkedMultiValueMap<>();

        map.entrySet().forEach(e -> multiValueMap.add(e.getKey(), e.getValue()));
        return multiValueMap;
    }
}
