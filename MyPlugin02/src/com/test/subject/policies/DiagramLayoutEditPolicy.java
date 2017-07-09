package com.test.subject.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.test.subject.model.Diagram;
import com.test.subject.model.Node;
import com.test.subject.model.Subject;
import com.test.subject.model.commands.CreateSubjectCommand;
import com.test.subject.model.commands.ChangeNodeConstraintCommand;
import com.test.subject.parts.NodePart;

/**
 * @author Swei
 *
 */
public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy {

    protected Command createAddCommand(EditPart child, Object constraint) {
        return null;
    }

    protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
        if (!(child instanceof NodePart))
            return null;
        if (!(constraint instanceof Rectangle))
            return null;

        ChangeNodeConstraintCommand cmd = new ChangeNodeConstraintCommand();
        cmd.setNode((Node) child.getModel());
        cmd.setLocation(((Rectangle) constraint).getLocation());
        cmd.setDimension(((Rectangle) constraint).getSize());
        return cmd;

    }

    protected Command getCreateCommand(CreateRequest request) {
        if (!(request.getNewObject() instanceof Node))
            return null;
        if (!(request.getNewObject() instanceof Subject))
            return null;
        CreateSubjectCommand cmd = new CreateSubjectCommand();
        cmd.setDiagram((Diagram) getHost().getModel());
        cmd.setSubject((Subject) request.getNewObject());
        Rectangle constraint = (Rectangle) getConstraintFor(request);
        cmd.setLocation(constraint.getLocation());
        return cmd;
    }

    protected Command getDeleteDependantCommand(Request request) {
        return null;
    }
}