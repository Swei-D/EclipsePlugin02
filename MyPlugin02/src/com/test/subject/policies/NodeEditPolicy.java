package com.test.subject.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import com.test.subject.model.Node;
import com.test.subject.model.commands.DeleteNodeCommand;

/**
 * @author Swei
 *
s */
public class NodeEditPolicy extends ComponentEditPolicy {

    protected Command createDeleteCommand(GroupRequest deleteRequest) {
        DeleteNodeCommand deleteCommand = new DeleteNodeCommand();
        deleteCommand.setParent(getHost().getParent().getModel());
        deleteCommand.setNode((Node) getHost().getModel());
        return deleteCommand;
    }
}
