package com.asteroid.expression.common.util.node;

import com.asteroid.expression.login.model.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/*
 * 节点工具类
 * @auther YuSai
 */
public class NodeUtil {

    private static List<Menu> returnList = new ArrayList<Menu>();

    /**
     * 根据父节点的ID获取所有子节点
     * @param list 分类表
     * @param typeId 传入的父节点ID
     * @return String
     */
    public static List<Menu> getChildNodes(List<Menu> list, Long typeId) {
        returnList = new ArrayList<Menu>();
        if(list == null && typeId == null) return new ArrayList<Menu>();
        for (Iterator<Menu> iterator = list.iterator(); iterator.hasNext();) {
            Menu node = (Menu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getpId()==0 && typeId==node.getId()) {
                recursionFn(list, node);
            }
            // 二、遍历所有的父节点下的所有子节点
            if (node.getpId()==0) {
                recursionFn(list, node);
            }
        }
        // 对顶层菜单按照MenuOrder从大到小进行进行排序
        Collections.sort(returnList);
        return returnList;
    }

    private static void recursionFn(List<Menu> list, Menu node) {
        List<Menu> childList = getChildList(list, node);// 得到子节点列表
        if (hasChild(list, node)) {// 判断是否有子节点
            Iterator<Menu> it = childList.iterator();
            while (it.hasNext()) {
                Menu n = (Menu) it.next();
                if(hasChild(list,n)){// 判断子节点是否还有相应的子节点，若有则再次递归遍历
                    recursionFn(list, n);
                }
            }
            node.setChild(childList);
            returnList.add(node);
        }
    }

    // 得到子节点列表
    private static List<Menu> getChildList(List<Menu> list, Menu node) {
        List<Menu> nodeList = new ArrayList<Menu>();
        Iterator<Menu> it = list.iterator();
        while (it.hasNext()) {
            Menu n = (Menu) it.next();
            if (n.getpId() == node.getId()) {
                nodeList.add(n);
            }
        }
        Collections.sort(nodeList);
        return nodeList;
    }

    // 判断是否有子节点
    private static boolean hasChild(List<Menu> list, Menu node) {
        return getChildList(list, node).size() > 0 ? true : false;
    }

}
