package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jl.yao
 * @className SumOfTwoNumbers
 * @description 两数之和
 * @date 2021/3/5 9:45
 **/
@Slf4j
public class SumOfTwoNumbers {


    /**
     * 题目描述：给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     * 你可以按任意顺序返回答案。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 示例 2：
     *
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * 示例 3：
     *
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     * 提示：
     *
     * 2 <= nums.length <= 103
     * -109 <= nums[i] <= 109
     * -109 <= target <= 109
     * 只会存在一个有效答案

     */

    @Test
    public void test(){
        int[] nums = {2,3,5,7};
        int target = 9;
//        int[] ints = method01(nums, target);
        int[] ints = method02(nums, target);
        log.info("下标：{}",ints);

    }

    /**
     * 方法一：暴力枚举
     * 思路及算法
     *
     * 最容易想到的方法是枚举数组中的每一个数 x，寻找数组中是否存在 target - x。
     *
     * 当我们使用遍历整个数组的方式寻找 target - x 时，需要注意到每一个位于 x 之前的元素都已经和 x 匹配过，因此不需要再进行匹配。而每一个元素不能被使用两次，所以我们只需要在 x 后面的元素中寻找 target - x。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] method01(int[] nums,int target ){
        for(int i=0;i<nums.length;i++){
            for (int j = i+1; j < nums.length; j++) {
                if(target == nums[i]+nums[j]){
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }


    /**
     * 方法二：哈希表
     * 思路及算法
     *
     * 注意到方法一的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。因此，我们需要一种更优秀的方法，能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。
     *
     * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N)O(N) 降低到 O(1)O(1)。
     *
     * 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配。

     * @param nums
     * @param target
     * @return
     */
    public int[] method02(int[] nums,int target ){
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
