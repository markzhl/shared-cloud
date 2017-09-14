/**
 * 
 */
package com.github.markzhl.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import lombok.extern.slf4j.Slf4j;

/**
 * 封装Jackson，实现JSON String<->Java Object 的转换工具类
 * 
 * @author mark
 *
 */
@Slf4j
public class JacksonConverter {

  private ObjectMapper objectMapper;
  
  private static ObjectMapper jsonMapper = new ObjectMapper();

  private static JacksonConverter jacksonConverter = null;
  
  static{
    jsonMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jsonMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    jsonMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
  }

  protected JacksonConverter() {
    objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
  }

  public static JacksonConverter getInstance() {

    if (jacksonConverter == null) {
      jacksonConverter = new JacksonConverter();
    }

    return jacksonConverter;

  }
  
  public static ObjectMapper getEnableDefaultTypJsonMapper(){
    return jsonMapper;
  }

  /**
   * 将对象转换为Json
   * 
   * @param obj
   * @return
   */
  public String toJson(Object obj) {
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      log.warn("obj to json failed:" + obj, e);
      throw new RuntimeException("obj to json failed!!!");
    }
  }
  
  public <T> T fromJson(String jsonStr) {

    if (StringUtils.isEmpty(jsonStr)) {
      return null;
    }

    try {
      return objectMapper.readValue(jsonStr, new TypeReference<T>(){});
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 将JSON转换为对象
   * 
   * @param jsonStr
   * @param clazz
   * @return
   */
  public <T> T fromJson(String jsonStr, Class<T> clazz) {

    if (StringUtils.isEmpty(jsonStr)) {
      return null;
    }

    try {
      return objectMapper.readValue(jsonStr, clazz);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 将JSON转换为对象
   *   TypeReference<List<Map<String,List<Task>>>> typeRef = new TypeReference<List<Map<String,List<Task>>>>() {};
   * 
   * @param jsonStr
   * @param type
   * @return
   */
  public <T> T fromJson(String jsonStr, TypeReference<T> type) {
    if (StringUtils.isEmpty(jsonStr)) {
      return null;
    }
    try {
      return objectMapper.readValue(jsonStr, type);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 将JSON转换为对象
   * 
   * @param jsonString
   * @param javaType
   * @return
   */
  public <T> T fromJson(String jsonStr, JavaType javaType) {

    if (StringUtils.isEmpty(jsonStr)) {
      return null;
    }

    try {
      return objectMapper.readValue(jsonStr, javaType);
    } catch (Exception e) {
      log.warn("parse json string error:" + jsonStr, e);
      return null;
    }
  }

  /**
   * 
   * @param jsonStr
   * @return
   */
  @SuppressWarnings("unchecked")
  public <T> Map<String, T> json2Map(String jsonStr) {

    if (StringUtils.isEmpty(jsonStr)) {
      return null;
    }

    try {
      return objectMapper.readValue(jsonStr, HashMap.class);
    } catch (Exception e) {
      log.warn(jsonStr + " not a JSON string!! Exception:" + e.getMessage());
    }
    return null;
  }

  /**
   * 
   * @param jsonStr
   * @param clazz
   * @return
   */
  public <T> Map<String, T> jsonToMap(String jsonStr, Class<T> clazz) {

    if (StringUtils.isEmpty(jsonStr)) {
      return null;
    }

    Map<String, T> map = null;
    try {
      map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
      });
    } catch (Exception e) {
      log.warn(jsonStr + " not a JSON string!! Exception:" + e.getMessage());
    }
    return map;
  }

  public <T> Map<String, T> json2Map(String jsonStr, TypeReference tf) {

    if (StringUtils.isEmpty(jsonStr)) {
      return null;
    }

    Map<String, T> map = null;
    try {
      map = objectMapper.readValue(jsonStr, tf);
    } catch (Exception e) {
      log.warn(jsonStr + " not a JSON string!! Exception:" + e.getMessage());
    }
    return map;
  }

  /**
   * 复杂Map类型转换
   * 
   * @param jsonStr
   * @param mapClass
   * @param keyClass
   * @param valueClass
   * @return
   */
  public <T> Map<String, T> json2Map(String jsonStr, Class< ? extends Map> mapClass, Class< ? > keyClass, Class< ? > valueClass) {
    Map<String, T> map = null;
    JavaType javaType = this.contructMapType(mapClass, keyClass, valueClass);
    try {
      map = this.fromJson(jsonStr, javaType);
    } catch (Exception e) {
      log.warn(jsonStr + " not a JSON string!! Exception:" + e.getMessage());
    }
    return map;
  }

  /**
   * Json字符串转换为List对象（推荐使用）
   * 
   * @param jsonStr
   * @param clazz
   * @return
   */
  public <T> List<T> json2List(String jsonStr, Class<T> clazz) {

    if (StringUtils.isEmpty(jsonStr)) {
      return null;
    }

    CollectionType ct = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
    try {
      List<T> arr = objectMapper.readValue(jsonStr, ct);
      return arr;
    } catch (Exception e) {
      log.error("Json to List error", e);
    }
    return null;
  }

  /**
   * 
   * @param jsonStr
   * @param collectionClass
   * @param elementClass
   * @return
   */
  public <T> List<T> json2List(String jsonStr, Class< ? extends Collection> collectionClass, Class< ? >... elementClass) {

    if (StringUtils.isEmpty(jsonStr)) {
      return null;
    }

    JavaType javaType = this.contructCollectionTypeExt(collectionClass, elementClass);

    try {
      List<T> arr = objectMapper.readValue(jsonStr, javaType);
      return arr;
    } catch (Exception e) {
      log.error("Json to List error", e);
    }
    return null;
  }
  
  public <T> Set<T> toSet(String json, Class<T> c) {
    CollectionType ct = objectMapper.getTypeFactory().constructCollectionType(HashSet.class, c);
    try {
      Set<T> arr = objectMapper.readValue(json, ct);
      return arr;
    } catch (Exception e) {
      log.error("Json to Set error", e);
    }
    return null;
  }

  /**
   * 
   * @param jsonStr
   * @param clazz
   * @return
   */
  public <T> List<T> jsonToList(String jsonStr, Class<T> clazz) {

    if (StringUtils.isEmpty(jsonStr)) {
      return null;
    }

    List<T> result = new ArrayList<T>();
    try {
      List<Map<String, Object>> list = objectMapper.readValue(jsonStr, new TypeReference<List<T>>() {
      });
      for (Map<String, Object> map : list) {
        result.add(map2pojo(map, clazz));
      }
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * 
   * @param map
   * @param clazz
   * @return
   */
  public <T> T map2pojo(Map< ? , ? > map, Class<T> clazz) {
    return objectMapper.convertValue(map, clazz);
  }

  /**
   * 构造泛型的Collection Type
   * 
   * @param collectionClass
   * @param elementClass
   * @return
   */
  public JavaType contructCollectionType(Class< ? extends Collection> collectionClass, Class< ? > elementClass) {
    return objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
  }

  /**
   * 构造泛型的Collection Type
   * 
   * @param collectionClass
   * @param elementClass
   * @return
   */
  public JavaType contructCollectionTypeExt(Class< ? extends Collection> collectionClass, Class< ? >... elementClass) {
    return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
  }

  /**
   * 构造Map类型
   * 
   * @param mapClass
   * @param keyClass
   * @param valueClass
   * @return
   */
  public JavaType contructMapType(Class< ? extends Map> mapClass, Class< ? > keyClass, Class< ? > valueClass) {
    return objectMapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
  }

  public ObjectMapper getMapper() {
    return objectMapper;
  }
}
