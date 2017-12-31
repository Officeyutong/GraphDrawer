/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package top.lifemc.graphdrawer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Ytong
 */
public class Utils {

    //获取以x y为圆心 r为半径的园 转过deg度所对应的点的坐标
    public static Point getPointOnCircle(int x, int y, int r, double deg) {
        return new Point((int) Math.floor(x + r * Math.cos(deg)), (int) Math.floor(y + r * Math.sin(deg)));
    }

    public static void drawCircleAt(Graphics graphics, int x, int y, int r, String str) {
        System.out.printf("drawing circle at %d %d r=%d\n", x, y, r);

        graphics.drawOval(x - r, y - r, r * 2, r * 2);
        graphics.drawString(str, x - r / 2, y + r / 2);
    }

    //在x y出画一个箭头 箭头指向deg角 deg是箭头的顶点与x轴的夹角
    public static void drawTarget(Graphics graphic, int x, int y) {
        Color color = graphic.getColor();
        graphic.setColor(Color.RED);
        graphic.fillRect(x - 2, y - 2, 4, 4);
        graphic.setColor(color);
    }

    //返回p1 p2所构成的直线的斜率
    public static double getSlope(Point p1, Point p2) {
        return Math.atan2(p2.y - p1.y, p2.x - p1.x);
    }

    public static int test(int x, int y, int r, Point p) {
        return (p.x - x) * (p.x - x) + (p.y - y) * (p.y - y) - r * r;
    }
}
