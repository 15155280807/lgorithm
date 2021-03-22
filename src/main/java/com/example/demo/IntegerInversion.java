package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 * @author jl.yao
 * @className IntegerInversion
 * @description 整数反转
 * @date 2021/3/5 10:02
 **/
@Slf4j
public class IntegerInversion {

    /**
     * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
     *
     * 如果反转后整数超过 32 位的有符号整数的范围 [−2*31,  2*31 − 1] ，就返回 0。
     *
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     *  
     *
     * 示例 1：
     * 输入：x = 123
     * 输出：321
     *
     * 示例 2：
     * 输入：x = -123
     * 输出：-321
     *
     * 示例 3：
     * 输入：x = 120
     * 输出：21
     *
     * 示例 4：
     * 输入：x = 0
     * 输出：0
     *  
     *
     */
    @Test
    public void test(){

        int reverse = reverse(2131231189);
        log.info("整数反转：{}",reverse);
    }


    /**
     * 字符串反转
     * @param x
     * @return
     */
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)){
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

        /**
         * 字符串反转
         * @param x
         * @return
         */
        public int reverse02(int x){
            long target = x;
            //负数标示
            boolean flag=false;
            if (x < 0){
                //负数转成正整数
                target = x == -2147483648?2147483648L:Math.abs(x);
                flag = true;
            }
            //转成字符串反转
            target = Long.parseLong(new StringBuilder(String.valueOf(target)).reverse().toString());
            if (flag){
                target = target * -1;
                if (target < Integer.MIN_VALUE){
                    return 0;
                }
            }
            if (target >Integer.MAX_VALUE){
                return 0;
            }
            return (int)target;
        }


}
