package com.im.portfolio.services;

import java.util.LinkedList;
import java.util.List;

/**
 * @author imaltsev
 * @since 03/07/18
 * <p>
 * Node class holds info about nodes state.
 */
public class Node {

    private String name;
    private String parentName;
    private Node parent;
    private List<Node> children = new LinkedList<>();
    private Integer value;
    private Double weightPerRoot;

    public Node() {}

    public Node(String parentName, String name, Integer value) {
        this.parentName = parentName;
        this.name = name;
        this.value = value;
    }

    public Node addChild(Node child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Double getWeightPerRoot() {
        return weightPerRoot;
    }

    public void setWeightPerRoot(Double weightPerRoot) {
        this.weightPerRoot = weightPerRoot;
    }
}
