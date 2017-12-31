/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package top.lifemc.graphdrawer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;

/**
 *
 * @author Ytong
 */
public class Drawer {
    
    private Graphics graphic;
    private int x;
    private int y;
    private int r;
    private int R;
    private ConfigSetter setter;
    private TreeSet<Integer> vertexes = new TreeSet<>();
    private TreeSet<Path> edges = new TreeSet<>();
    
    public Drawer(Graphics graphic, ConfigSetter setter, int r, int R, int x, int y) {
        this.graphic = graphic;
        this.x = x;
        this.y = y;
        this.r = r;
        this.setter = setter;
        this.R = R;
        parse();
        
    }
    
    private void parse() throws ArrayIndexOutOfBoundsException {
        String splits[] = (setter.getDefaultSetting() + " ").split(Matcher.quoteReplacement("\n"));
        for (String x : splits) {
            String trimed = x.trim();
            if (trimed.equals("")) {
                continue;
            }
            if (trimed.trim().startsWith("//")) {
                continue;
            }
            System.out.println(trimed);
            Path path = new Path(trimed);
            edges.add(path);
            vertexes.add(path.getFrom());
            vertexes.add(path.getTo());
        }
    }
    TreeMap<Integer, Point> points = new TreeMap<>();
    
    public void draw() {
        
        double deg = -Math.PI * 2 / vertexes.size();
        int i = 0;
        for (int v : vertexes) {
            double currDeg = deg * (i--);
            Point p = Utils.getPointOnCircle(x, y, R, currDeg);
            //System.out.println(p);
            Utils.drawCircleAt(graphic, p.x, p.y, r, String.valueOf(v));
            System.out.println(Utils.test(x, y, R, p));
            points.put(v, p);
        }
        for (Path p : edges) {
            Point p1 = (Point) points.get(p.getFrom()).clone();
            Point p2 = (Point) points.get(p.getTo()).clone();
            System.out.printf("from %s [%s] to %s [%s] \n", p.getFrom(), p1, p.getTo(), p2);
            
            System.out.printf("%d %d \n", Utils.test(x, y, R, p1), Utils.test(x, y, R, p2));
            double slope = Utils.getSlope(p1, p2);
            {
                if (slope < 0) {
                    slope += Math.PI;
                }
                if (slope > Math.PI) {
                    slope -= Math.PI;
                }
                System.out.println(slope / Math.PI * 180);
                if (slope > Math.PI / 2) {
                    slope = Math.PI - slope;
                }
                if (p1.x < p2.x) {
                    p1.x += Math.floor(r * Math.cos(slope));
                    p2.x -= Math.floor(r * Math.cos(slope));
                }
                if (p1.x > p2.x) {
                    p1.x -= Math.floor(r * Math.cos(slope));
                    p2.x += Math.floor(r * Math.cos(slope));
                }
                if (p1.y < p2.y) {
                    p1.y += Math.floor(r * Math.sin(slope));
                    p2.y -= Math.floor(r * Math.sin(slope));
                }
                if (p1.y > p2.y) {
                    p1.y -= Math.floor(r * Math.sin(slope));
                    p2.y += Math.floor(r * Math.sin(slope));
                }
            }
            graphic.drawLine(p1.x, p1.y, p2.x, p2.y);
            
            if (setter.isWeighted()) {
                //  Color x = graphic.getColor();
                //graphic.setColor(Color.WHITE);
                graphic.clearRect((p1.x + p2.x) / 2, (p1.y + p2.y) / 2 - r, r * 2, r);

                // graphic.setColor(x);
                graphic.drawString(String.valueOf(p.getWeight()), (p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
            }
            if (setter.isDirected()) {
                Utils.drawTarget(graphic, p2.x, p2.y);
            }
            System.out.println();
        }
    }
    
}
