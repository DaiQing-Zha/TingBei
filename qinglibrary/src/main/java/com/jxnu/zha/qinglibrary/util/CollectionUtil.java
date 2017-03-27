package com.jxnu.zha.qinglibrary.util;

import java.util.Collection;
import java.util.List;

/**
 * Created by CaiBingZhang on 15/8/28.
 */
public class CollectionUtil {

    /**
     * @Description: 判断List集合是否为空
     * @param list
     * @return
     */
    public static boolean listIsNull(Collection<? extends Object> list){
        if(null ==list || list.size()==0){
            return true;
        }
        return false;
    }

    /**
     * @Description: 判断List集合是否为空
     * @param list
     * @return
     */
    public static boolean listIsNotNull(Collection<? extends Object> list){
        if(null !=list && list.size()>0){
            return true;
        }
        return false;
    }

    /**
     * 清除List中所有元素
     * @param list
     */
    public static void clearList(List<?> list){
        if(list!=null){
            list.clear();
            list=null;
        }
    }
}
