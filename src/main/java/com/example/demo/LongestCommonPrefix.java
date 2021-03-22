package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Queue;

/**
 * @author jl.yao
 * @className longestCommonPrefix
 * @description
 * @date 2021/3/10 10:01
 **/
@Slf4j
public class LongestCommonPrefix {


    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     *
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     *  
     *
     * 提示：
     *
     * 0 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] 仅由小写英文字母组成
     *
     */
    @Test
    public void test(){
        String prefix = longestCommonPrefix03(new String[]{"flower", "flow", "flight","fleght","fldght"});
        log.info("最小公共前缀：{}",prefix);
    }


    /**
     * 方法一：横向扫描

     *
     * 基于该结论，可以得到一种查找字符串数组中的最长公共前缀的简单方法。依次遍历字符串数组中的每个字符串，对于每个遍历到的字符串，
     * 更新最长公共前缀，当遍历完所有的字符串以后，即可得到字符串数组中的最长公共前缀。
     *如果在尚未遍历完所有的字符串时，最长公共前缀已经是空串，则最长公共前缀一定是空串，
     * 因此不需要继续遍历剩下的字符串，直接返回空串即可
     *
     * 复杂度分析
     *
     * 时间复杂度：O(mn)，其中 mm 是字符串数组中的字符串的平均长度，nn 是字符串的数量。最坏情况下，字符串数组中的每个字符串的每个字符都会被比较一次。
     *
     * 空间复杂度：O(1)。使用的额外空间复杂度为常数。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
       //参数校验
        if (strs == null || strs.length == 0){
            return "";
        }
        //因为取得是最长公共前缀，所以可先取出首位作为比较值
        String prefix = strs[0];
        for (int i = 1; i <strs.length ; i++) {
            //当前i索引处值和前一位比较
            prefix = longestCommonPrefix(prefix,strs[i]);
            //如果第一次比较就不相同即表明没有公共前缀，跳出循环
            if (prefix.length() == 0){
                break;
            }
        }
        return prefix;
    }
    private String longestCommonPrefix(String prefix, String str) {
       //确定循环次数
        int min = Math.min(prefix.length(), str.length());
        //从首位开始比较 即所以为0开始
        int index = 0;
        //因为不确定循环次数 故用while判断 知道不满足条件
        while (index < min && prefix.charAt(index) == str.charAt(index)){
            index++;
        }
        //截取公共前缀
        return prefix.substring(0,index);
    }


    /**
     * 方法二：纵向扫描
     * 方法一是横向扫描，依次遍历每个字符串，更新最长公共前缀。另一种方法是纵向扫描。纵向扫描时，从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。
     *

     * @param strs
     * @return
     */
    public String longestCommonPrefix01(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        //取出第一位作为参考值,遍历第一位字符串
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length ; i++) {
            char c = strs[0].charAt(i);
            //遍历数组中剩余的字符串
            for (int j = 1; j < count ; j++) {
                //比较每个字符传中对应索引位置的字符是否相同
                if (i == strs[j].length() || c != strs[j].charAt(i)){
                    return strs[0].substring(0,i);
                }
            }
        }
        return strs[0];

    }


    /**
     * 分治算法
     * 复杂度分析
     *
     * 时间复杂度：O(mn)O(mn)，其中 mm 是字符串数组中的字符串的平均长度，nn 是字符串的数量。时间复杂度的递推式是 T(n)=2 \cdot T(\frac{n}{2})+O(m)T(n)=2⋅T(
     * 2
     * n
     * ​
     *  )+O(m)，通过计算可得 T(n)=O(mn)T(n)=O(mn)。
     *
     * 空间复杂度：O(m \log n)O(mlogn)，其中 mm 是字符串数组中的字符串的平均长度，nn 是字符串的数量。空间复杂度主要取决于递归调用的层数，层数最大为 \log nlogn，每层需要 mm 的空间存储返回结果。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix03(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else{
            return longestCommonPrefix(strs, 0, strs.length - 1);
        }
    }

    public String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end){
            return strs[start];
        }
        //中间值
        int mid = (end-start)/2+start;
        //递归
        String lcpLeft = longestCommonPrefix(strs, start, mid);
        String lcpRight = longestCommonPrefix(strs,mid+1,end);
        return commonPrefix(lcpLeft,lcpRight);

    }

    public String commonPrefix(String lcpLeft, String lcpRight) {
        //比较两个数组
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);


    }


}
