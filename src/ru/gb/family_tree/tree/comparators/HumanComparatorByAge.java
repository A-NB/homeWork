package ru.gb.family_tree.tree.comparators;

import java.util.Comparator;

import ru.gb.family_tree.human.Human;

public class HumanComparatorByAge implements Comparator<Human> {
    @Override
    public int compare(Human o1, Human o2) {
        return Integer.compare(o1.getAge(), o2.getAge());
        // return o1.getAge() - o2.getAge(); - тоже будет работать
    }
}