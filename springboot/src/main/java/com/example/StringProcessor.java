package com.example;

/**
 * Note:
 * Date: 2024/4/13 23:46
 * Author: shihy
 */
public class StringProcessor {
    public static String process(String input) {
        System.out.println("feature3");
        // 找到最后一个 "-" 的位置
        int lastDashIndex = input.lastIndexOf("-");

        // 如果最后一个 "-" 后面全是数字,则去除它及其后面的部分
        if (lastDashIndex != -1 && isAllDigits(input.substring(lastDashIndex + 1))) {
            input = input.substring(0, lastDashIndex);
        }

        // 将 "-" 替换为空格
        input = input.replace("-", " ");

        // 将每个单词的首字母大写
        String[] words = input.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1))
                        .append(" ");
            }
        }

        return result.toString().trim();
    }

    private static boolean isAllDigits(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] inputs = {
                "new-balance-4",
                "nike-144",
                "424-1001u834",
                "s-max-mara-1001305",
                "kent-curwen-1008629",
                "0-croworld-1012440",
                "1017-alyx-9sm-1000301",
                "medicos-entertainment-co-ltd-1000842"
        };

        for (String input : inputs) {
            System.out.println(process(input));
        }
    }
}
