package com.example.dw.word;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Note:
 * Date: 2024/10/25 00:36
 * Author: shihy
 */
public class Rule {

    public String evaluate(String recordJson, String rulesJson, Map<String, String> needContainsConfig, List<String> needSeparatedEqualsFields) {
        // 参数校验
        if (StringUtils.isBlank(recordJson) || StringUtils.isBlank(rulesJson) || needContainsConfig == null || needSeparatedEqualsFields == null) {
            return "";
        }
        try {
            JSONObject record = JSON.parseObject(recordJson);
            JSONArray rules = JSON.parseArray(rulesJson);

            for (int i = 0; i < rules.size(); i++) {
                JSONObject rule = rules.getJSONObject(i);
                JSONArray filterConditions = rule.getJSONArray("filter_condition");
                boolean ruleMatched = true;

                for (int j = 0; j < filterConditions.size(); j++) {
                    JSONObject condition = filterConditions.getJSONObject(j);
                    String fieldName = condition.getString("field_name");
                    String requirement = condition.getString("condition");
                    JSONArray fieldValues = condition.getJSONArray("field_value");

                    String recordValue = record.getString(fieldName);
                    if (recordValue == null) {
                        ruleMatched = false;
                        break;
                    }
                    recordValue = StringUtils.trim(recordValue).toLowerCase(); // 去掉前后空格并转为小写

                    // 从配置中获取比较类型，默认是"equals"
                    String comparisonType = needContainsConfig.getOrDefault(fieldName, "equals");

                    // 如果字段需要按逗号分割
                    String[] recordValues;
                    if (needSeparatedEqualsFields.contains(fieldName)) {
                        recordValues = recordValue.split(","); // 先切分
                        // 去掉切分后的每个值的前后空格
                        for (int k = 0; k < recordValues.length; k++) {
                            recordValues[k] = StringUtils.trim(recordValues[k]);
                        }
                    } else {
                        recordValues = new String[]{recordValue};
                    }

                    // 处理条件逻辑
                    boolean valueMatched = false;
                    if (requirement.equals("include")) {
                        valueMatched = handleIncludeCondition(fieldValues, recordValues, comparisonType);
                    } else if (requirement.equals("not_include")) {
                        valueMatched = handleNotIncludeCondition(fieldValues, recordValues, comparisonType);
                    }

                    if (!valueMatched) {
                        ruleMatched = false;
                        break;
                    }
                }

                if (ruleMatched) {
                    return rule.getString("tag_value");
                }
            }
            return "SET_EMPTY"; // 如果没有匹配到任何规则，返回相应字符串
        } catch (Exception e) {
            // System.err.println("barCode replace error:[err:" + e + "]");
            return "";
        }
    }

    private boolean handleIncludeCondition(JSONArray fieldValues, String[] recordValues, String comparisonType) {
        for (int v = 0; v < fieldValues.size(); v++) {
            String value = StringUtils.trim(fieldValues.getString(v)).toLowerCase(); // 去掉前后空格并转为小写

            if (comparisonType.equals("contains")) {
                for (String rv : recordValues) {
                    if (rv.contains(value)) {
                        return true; // 找到包含关系
                    }
                }
            } else { // 默认使用"equals"比较
                for (String rv : recordValues) {
                    if (rv.equals(value)) {
                        return true; // 找到相等关系
                    }
                }
            }
        }
        return false; // 没有找到匹配
    }

    private boolean handleNotIncludeCondition(JSONArray fieldValues, String[] recordValues, String comparisonType) {
        for (int v = 0; v < fieldValues.size(); v++) {
            String value = StringUtils.trim(fieldValues.getString(v)).toLowerCase(); // 去掉前后空格并转为小写

            for (String rv : recordValues) {
                if (comparisonType.equals("contains")) {
                    if (rv.contains(value)) {
                        return false; // 发现包含关系，返回 false
                    }
                } else { // 默认使用"equals"比较
                    if (rv.equals(value)) {
                        return false; // 发现相等关系，返回 false
                    }
                }
            }
        }
        return true; // 所有组合的值都不满足条件，返回 true
    }

    public static void main(String[] args) {
        Rule udf = new Rule();

//        String recordJson1 = "{\n" +
//                "    \"spu_id\": 629153397,\n" +
//                "    \"status\": 1,\n" +
//                "    \"title\": \"Adidas Originals Beanie Unisex\",\n" +
//                "    \"brand_name\": \"Adidas Originals\",\n" +
//                "    \"regions\": \"China-inner,China,China-outter\"\n" +
//                "}";
//
//        String recordJson2 = "{\n" +
//                "    \"spu_id\": 629153398,\n" +
//                "    \"status\": 3,\n" +
//                "    \"title\": \"Reebok Classic Shoes\",\n" +
//                "    \"brand_name\": \"Reebok\",\n" +
//                "    \"regions\": \"USA,Canada\"\n" +
//                "}";
//
//        String recordJson3 = "{\n" +
//                "    \"spu_id\": 629153399,\n" +
//                "    \"status\": 2,\n" +
//                "    \"title\": \"Nike Sports Cap\",\n" +
//                "    \"brand_name\": \"Nike\",\n" +
//                "    \"regions\": \"Europe,Asia\"\n" +
//                "}";
//
//        String recordJson4 = "{\n" +
//                "    \"spu_id\": 629153400,\n" +
//                "    \"status\": 4,\n" +
//                "    \"title\": \"Winter Shoes\",\n" +
//                "    \"brand_name\": \"Generic Brand\",\n" +
//                "    \"regions\": \"Antarctica,South America\"\n" +
//                "}";
        // 示例 JSON 记录
        String recordJson = "{\n" +
                "    \"spu_id\": 629153400,\n" +
                "    \"status\": 1,\n" +
                "    \"title\": \"Winter Shoes\",\n" +
                "    \"brand_name\": \"Adidas\",\n" +
                "    \"regions\": \"China,South America,Antarctica\"\n" +
                "}";

        // 示例规则
        String rulesJson = "[\n" +
                "    {\n" +
                "        \"filter_condition\": [\n" +
                "            {\n" +
                "                \"field_name\": \"brand_name\",\n" +
                "                \"condition\": \"include\",\n" +
                "                \"field_value\": [\n" +
                "                    \"Adidas\",\n" +
                "                    \"Nike\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"field_name\": \"status\",\n" +
                "                \"condition\": \"include\",\n" +
                "                \"field_value\": [\n" +
                "                    1,\n" +
                "                    2\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"field_name\": \"regions\",\n" +
                "                \"condition\": \"include\",\n" +
                "                \"field_value\": [\n" +
                "                    \"China\",\n" +
                "                    \"South America\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"field_name\": \"title\",\n" +
                "                \"condition\": \"include\",\n" +
                "                \"field_value\": [\n" +
                "                    \"Beanie\",\n" +
                "                    \"Cap\"\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"tag_value\": \"A\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"filter_condition\": [\n" +
                "            {\n" +
                "                \"field_name\": \"brand_name\",\n" +
                "                \"condition\": \"include\",\n" +
                "                \"field_value\": [\n" +
                "                    \"Puma\",\n" +
                "                    \"Reebok\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"field_name\": \"status\",\n" +
                "                \"condition\": \"include\",\n" +
                "                \"field_value\": [\n" +
                "                    3\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"field_name\": \"regions\",\n" +
                "                \"condition\": \"include\",\n" +
                "                \"field_value\": [\n" +
                "                    \"USA\",\n" +
                "                    \"Canada\"\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"tag_value\": \"B\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"filter_condition\": [\n" +
                "            {\n" +
                "                \"field_name\": \"title\",\n" +
                "                \"condition\": \"include\",\n" +
                "                \"field_value\": [\n" +
                "                    \"Shoes\"\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"field_name\": \"regions\",\n" +
                "                \"condition\": \"not_include\",\n" +
                "                \"field_value\": [\n" +
                "                    \"Antarctica\"\n" +
                "                ]\n" +
                "            }\n" +
                "        ],\n" +
                "        \"tag_value\": \"C\"\n" +
                "    }\n" +
                "]";

        // 比较类型的配置
        Map<String, String> comparisonConfig = new HashMap<>();
        comparisonConfig.put("brand_name", "contains");
        comparisonConfig.put("title", "contains");
        comparisonConfig.put("regions", "contains");

        // 需要按逗号分割的字段
        List<String> commaSeparatedFields = new ArrayList<>();
        commaSeparatedFields.add("regions");

        System.out.println(udf.evaluate(recordJson, rulesJson, comparisonConfig, commaSeparatedFields));
    }
}
