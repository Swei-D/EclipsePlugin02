package com.test.subject.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.FlowLayoutEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.test.subject.model.Attribute;
import com.test.subject.model.Node;
import com.test.subject.model.Subject;
import com.test.subject.model.commands.AttributeTransferCommand;
import com.test.subject.model.commands.CreateAttributeCommand;
import com.test.subject.model.commands.ChangeNodeConstraintCommand;
import com.test.subject.parts.AttributePart;
import com.test.subject.parts.NodePart;

/**
 * @author Swei
 *
 */
public class SubjectLayoutEditPolicy extends XYLayoutEditPolicy {

    protected boolean isHorizontal() {
        return false;
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

    protected Command createAddCommand(EditPart child, Object constraint) {
//        if (!(child instanceof AttributePart))
//            return null;
//
//        Attribute attributeToMove = (Attribute) child.getModel();
//        Attribute attributeAfter;
//        Subject originalSubject;
//        Subject newSubject=(Subject) getHost().getModel();
//        int newIndex;
//        int oldIndex;
//        if (after != null) {
//            if (!(after instanceof AttributePart))
//                return null;
//            attributeAfter = (Attribute) after.getModel();
//            originalSubject = (Subject) child.getParent().getModel();
////            newSubject = (Subject) after.getParent().getModel();
//            newIndex = originalSubject.getAttributes().indexOf(attributeToMove);
//            oldIndex = newSubject.getAttributes().indexOf(attributeAfter);
//        } else {
//            originalSubject = (Subject) child.getParent().getModel();
////            newSubject = (Subject) getHost().getModel();
//            oldIndex = originalSubject.getAttributes().indexOf(attributeToMove);
//            newIndex = 0;
//        }
//        AttributeTransferCommand cmd = new AttributeTransferCommand(attributeToMove, originalSubject, newSubject,
//                oldIndex, newIndex);
//        return cmd;
        return null;
    }

    protected Command getCreateCommand(CreateRequest request) {
        if (!(request.getNewObject() instanceof Attribute))
            return null;
        CreateAttributeCommand cmd = new CreateAttributeCommand();
        cmd.setSubject((Subject) getHost().getModel());
        cmd.setAttribute((Attribute) request.getNewObject());
        Rectangle constraint = (Rectangle) getConstraintFor(request);
        cmd.setLocation(constraint.getLocation());
        return cmd;
    }

    protected Command getDeleteDependantCommand(Request request) {
        // TODO Auto-generated method stub
        return null;
    }
}