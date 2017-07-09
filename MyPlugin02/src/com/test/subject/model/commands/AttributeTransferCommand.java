package com.test.subject.model.commands;

import org.eclipse.gef.commands.Command;

import com.test.subject.model.Attribute;
import com.test.subject.model.Subject;

/**
 * @author Swei
 *
 */
public class AttributeTransferCommand extends Command {
    private Attribute attributeToMove;

    private Subject originalSubject;

    private Subject newSubject;

    private int oldIndex, newIndex;

    /**
     * @param attributeToMove
     * @param originalSubject
     * @param newSubject
     * @param oldIndex
     * @param newIndex
     */
    public AttributeTransferCommand(Attribute attributeToMove, Subject originalSubject, Subject newSubject,
            int oldIndex, int newIndex) {
        super();
        this.attributeToMove = attributeToMove;
        this.originalSubject = originalSubject;
        this.newSubject = newSubject;
        this.oldIndex = oldIndex;
        this.newIndex = newIndex;
    }

    public void execute() {
        originalSubject.removeAttribute(attributeToMove);
        newSubject.addAttribute(newIndex, attributeToMove);
    }

    public String getLabel() {
        return "Transfer Attribute";
    }

    public void redo() {
        execute();
    }

    public void undo() {
        newSubject.removeAttribute(attributeToMove);
        originalSubject.addAttribute(oldIndex, attributeToMove);
    }
}