package com.test.subject.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;

/**
 * @author Swei
 *
 */
public class Subject extends Node {

    private static final long serialVersionUID = 3257847675427893304L;

    public static String PROP_STRUCTURE = "ATTRIBUTE";

    public static String PROP_COLLAPSED = "COLLAPSED";

    protected List attributes = new ArrayList();

    protected boolean collapsed = false;

    protected Dimension collapsedDimension = new Dimension(80, 50);

    public Subject() {
        super();
        setSize(new Dimension(200, 220));
    }

    public Dimension getSize() {
        if (!isCollapsed())
            return super.getSize();
        else
            return collapsedDimension;
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
        fireStructureChange(PROP_STRUCTURE, attribute);
    }

    public void addAttribute(int index, Attribute attribute) {
        this.attributes.add(index, attribute);
        fireStructureChange(PROP_STRUCTURE, attribute);
    }

    public void removeAttribute(Attribute attribute) {
        this.attributes.remove(attribute);
        fireStructureChange(PROP_STRUCTURE, attribute);
    }

    public List getAttributes() {
        return attributes;
    }

    public void setAttributes(List attributes) {
        this.attributes = attributes;
    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;
        fireStructureChange(PROP_COLLAPSED, new Boolean(collapsed));
    }

}