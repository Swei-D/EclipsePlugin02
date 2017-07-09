package com.test.subject.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.test.subject.model.Attribute;
import com.test.subject.model.Subject;

/**
 * @author Swei
 *
 */
public class CreateAttributeCommand extends Command {

    private Subject subject;

    private Attribute attribute;
    
    private Point location;

    //Setters
    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
    

    //------------------------------------------------------------------------
    // Overridden from Command
    public void execute() {
        attribute.setName("Attribute"+(subject.getAttributes().size()+1));
        attribute.setLocation(this.location);
        subject.addAttribute(attribute);
    }

    public String getLabel() {
        return "Create a new Attribute";
    }

    public void redo() {
        execute();
    }

    public void undo() {
        subject.removeAttribute(attribute);
    }
}