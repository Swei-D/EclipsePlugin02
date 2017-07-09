package com.test.subject.model.commands;

import org.eclipse.gef.commands.Command;

import com.test.subject.model.Node;

/**
 * @author Swei
 *
 */
public class RenameNodeCommand extends Command {

    private Node node;

    private String newName;

    private String oldName;

    public void setName(String name) {
        this.newName = name;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public void execute() {
        oldName = this.node.getName();
        this.node.setName(newName);
    }

    public void redo() {
        node.setName(newName);
    }

    public void undo() {
        node.setName(oldName);
    }

    public String getLabel() {
        return "Rename Node";
    }
}