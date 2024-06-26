package ru.gb.family_tree.model.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import ru.gb.family_tree.model.builder.ItemBuilder;
import ru.gb.family_tree.model.item.FamilyTreeItem;
import ru.gb.family_tree.model.item.Gender;
import ru.gb.family_tree.model.item.Human;
import ru.gb.family_tree.model.saving_data.*;
import ru.gb.family_tree.model.tree.FamilyTree;

public class Service {
    private String name;
    private Gender gender;
    private LocalDate birthDate;
    private FamilyTree<Human> tree;
    private ItemBuilder<Human> itemBuilder;
    private FamilyTreeItem<Human> human;
    private String storage;
    private Writable writable;

    public Service() {
        tree = new FamilyTree<>();
        itemBuilder = new ItemBuilder<>();
        human = itemBuilder.build(name, gender, birthDate);
        storage = "src/family_tree.out";
        writable = new FileHandler();
    }

    public void addItem(String name, Gender gender, LocalDate birthDate, LocalDate deathDate, Human father, Human mother) {
        tree.add(itemBuilder.build(name, gender, birthDate, deathDate, father, mother));
    }

    public void sortByName() {
        tree.sortByName();
    }

    public void sortByAge() {
        tree.sortByAge();
    }

    public void sortBySpouse() {
        tree.sortBySpouse();
    }

    public void saveTree() throws FileNotFoundException, IOException {
        writable.write_object(tree, storage);
    }

    @SuppressWarnings("unchecked")
    public FamilyTree<Human> loadTree() throws FileNotFoundException, ClassNotFoundException, IOException {
        tree = (FamilyTree<Human>) writable.read_object(storage);
        return tree;
    }

    public List<Human> getSiblings(long id) { // Поиск братьев и сестёр...
        return tree.getSiblings(id);
    }

    public List<Human> getByName(String name) {
        return tree.getByName(name);
    }

    public boolean setWedding(long itemID1, long itemID2) {
        return tree.setWedding(itemID1, itemID2);
    }

    public boolean setDivorce(long itemID1, long itemID2) {
        return tree.setDivorce(itemID1, itemID2);
    }

    public boolean remove(long id) {
        return tree.remove(id);
    }

    public Human getById(long id) {
        return tree.getById(id);
    }

    public long getId() {
        return human.getId();
    }

    @Override
    public String toString() {
        return tree.getInfo();
    }

}