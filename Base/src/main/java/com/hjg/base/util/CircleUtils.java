package com.hjg.base.util;

/**
 * 圆的工具类
 */
public class CircleUtils {
    /**
     * 根据圆心坐标, 半径, 以及角度，获取圆上坐标
     *
     * @param centerX 中心点x坐标
     * @param centerY 中心点y坐标
     * @param radius  半径
     * @param angle   角度
     * @return
     * @tip 角度为0的时候为x轴的正半轴水平位置
     */
    public static float[] getXYinCircle(float centerX, float centerY, float radius, int angle) {
        float x = (float) (centerX + radius * Math.cos(angle * 3.14 / 180));
        float y = (float) (centerY + radius * Math.sin(angle * 3.14 / 180));
        float[] coord = {x, y};
        return coord;
    }
}
