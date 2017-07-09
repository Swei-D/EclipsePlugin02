package com.test.subject.model.commands;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.test.subject.model.Node;
import com.test.subject.model.Subject;

/**
 * @author Swei
 *
 */
public class ChangeNodeConstraintCommand extends Command {
    private Node node;

    private Point oldPos;

    private Point newPos;

    private Dimension oldSize;

    private Dimension newSize;

    public void setLocation(Point p) {
        oldPos = this.node.getLocation();
        this.newPos = p;
    }

    public void setDimension(Dimension d) {
        oldSize = this.node.getSize();
        this.newSize = d;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void execute() {
        node.setLocation(newPos);
        if (!(node instanceof Subject && (((Subject) node).isCollapsed())))
            node.setSize(newSize);
    }

    public String getLabel() {
        return "Move/Resize Node";
    }

    public void redo() {
        execute();
    }

    public void undo() {
        this.node.setLocation(oldPos);
        this.node.setSize(newSize);
    }
}