/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package top.lifemc.graphdrawer;

import java.util.regex.Matcher;

/**
 *
 * @author Ytong
 */
public class Path implements Comparable<Path> {

    private int from;
    private int to;
    private int weight = 0;

    public Path(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public Path(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public Path(String str) throws ArrayIndexOutOfBoundsException {
        String splits[] = str.split(Matcher.quoteReplacement(" "));
        from = Integer.parseInt(splits[0].trim());
        to = Integer.parseInt(splits[1].trim());
        if (splits.length > 2) {
            weight = Integer.parseInt(splits[2].trim());
        }
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Path o) {
        if (from != o.from) {
            return from - o.from;

        } else {
            return to - o.to;
        }
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
