package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author jl.yao
 * @className ImplementStrStr
 * @description 实现 strStr()
 * @date 2021/3/18 13:38
 **/
@Slf4j
public class ImplementStrStr {


    /**
     * 实现 strStr() 函数。
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     *
     * 示例 1:
     *
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * 示例 2:
     *
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * 说明:
     *
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     *
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     *
     */
    @Test
    public void test(){
        String haystack = "held3llo";
        String needle = "llo";
        int i = strStr02(haystack, needle);
        log.info("indexOf索引：{}",i);
    }


    /**
     * 方法一  字符串解法
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        for (int start = 0; start < n - L + 1; ++start) {
            if (haystack.substring(start, start + L).equals(needle)) {
                return start;
            }
        }
        return -1;

    }

    public int strStr02(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        if (n == 0) {
            return 0  ;
        };
        for (int i = 0; i <= m - n; i++) {
            for (int j = 0; j < n; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)){
                    break;
                }
                if (j == n - 1){
                    return i;
                }
            }
        }
        return -1;

    }
}
