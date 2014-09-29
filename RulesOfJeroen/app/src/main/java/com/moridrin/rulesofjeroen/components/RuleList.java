package com.moridrin.rulesofjeroen.components;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jeroen on 14/09/28.
 */
public interface RuleList extends List<Rule> {
    public class Array extends ArrayList<Rule> implements RuleList {

    }

    public class Linked extends LinkedList<Rule> implements RuleList {

    }

    public static class RankComparator implements Comparator<Rule> {
        @Override
        public int compare(Rule o1, Rule o2) {
            if (o1.getRank() > o2.getRank()) {
                return 1;
            } else if (o1.getRank() < o2.getRank()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
