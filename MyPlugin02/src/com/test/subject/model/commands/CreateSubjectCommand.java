package com.test.subject.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.test.subject.model.Diagram;
import com.test.subject.model.Subject;

/**
 * @author Swei
 *
 */
public class CreateSubjectCommand extends Command {

    private Diagram diagram;

    private Subject subject;

    private Point location;

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void execute() {
        if (this.location != null) {
            this.subject.setLocation(this.location);
        }
        subject.setName("Subject" + (diagram.getSubjects().size() + 1));
        diagram.addSubject(subject);
    }

    public String getLabel() {
        return "Create Subject";
    }

    public void redo() {
        execute();
    }

    public void undo() {
        diagram.removeSubject(subject);
    }
}