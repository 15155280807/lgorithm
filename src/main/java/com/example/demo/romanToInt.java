package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author jl.yao
 * @className romanToInt
 * @description
 * @date 2021/3/9 16:22
 **/
@Slf4j
public class romanToInt {


    /**
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     *
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: "III"
     * 输出: 3
     * 示例 2:
     *
     * 输入: "IV"
     * 输出: 4
     * 示例 3:
     *
     * 输入: "IX"
     * 输出: 9
     * 示例 4:
     *
     * 输入: "LVIII"
     * 输出: 58
     * 解释: L = 50, V= 5, III = 3.
     * 示例 5:
     *
     * 输入: "MCMXCIV"
     * 输出: 1994
     * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 15
     * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
     * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
     * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
     * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
     * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
     *
     */
    @Test
    public void test(){
        int target = romanToInt01("CMXCIX");
        log.info("罗马数字：{}",target);
    }


    /**
     * 首先定义总和 sum = 0 初始化， 根据罗马数字规则，从左到右，
     * 以当前位为目标位，与连续的右侧即当前位的下一位比较，如果当前位字符数值 < 右侧即下一位字符数值，则当前位为负数，做减法；
     * 如果当前位字符数值 > 右侧即下一位字符数值 ，则当前位数值为正数，做加法；依次循环至倒数第二位（因为循环中使用到了i+1,循环到最后一位会出现数组越界）；
     * 最后处理最后一位字符，以最后一位字符位当前位，因为右侧已不存在字符（可视为0），所以最后一位必然位正数，做加法
     * @param s
     * @return
     */
    public int romanToInt01(String s) {
        int sum = 0;
        for(int i = 0;i < s.length()-1; i ++) {
            //当前为与右边位比较
            int currentNum = getInt(s.charAt(i));
            int nextNum = getInt(s.charAt(i+1));
            //当前位小于右边位 即做减法
            sum = currentNum < nextNum ?  sum - currentNum: sum + currentNum;
        }
        sum += getInt(s.charAt(s.length()-1));
        return sum;

    }


    public int getInt(char c) {
        switch (c){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
