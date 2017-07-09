package com.test.subject.model.commands;

import org.eclipse.gef.commands.Command;

import com.test.subject.model.Attribute;
import com.test.subject.model.Diagram;
import com.test.subject.model.Node;
import com.test.subject.model.Subject;

/**
 * @author Swei
 *
 */
public class DeleteNodeCommand extends Command {
    private Object parent;

    private Node node;

    private int index;

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    //------------------------------------------------------------------------
    // Overridden from Command

    public void execute() {
        if (parent instanceof Diagram) {
            Diagram diagram = (Diagram) parent;
            index = diagram.getSubjects().indexOf(node);
            diagram.removeSubject((Subject)node);
        } else if (parent instanceof Subject) {
            Subject subject = (Subject) parent;
            index = subject.getAttributes().indexOf(node);
            subject.removeAttribute((Attribute) node);
        }
    }

    public String getLabel() {
        return "Delete Node";
    }

    public void redo() {
        execute();
    }

    public void undo() {
        if (parent instanceof Diagram) {
            Diagram diagram = (Diagram) parent;
            diagram.addSubject((Subject)node);
        } else if (parent instanceof Subject) {
            Subject subject = (Subject) parent;
            subject.addAttribute((Attribute) node);
        }
    }
}