package com.test.subject.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.test.subject.model.Connection;
import com.test.subject.model.Node;
import com.test.subject.model.commands.ConnectionCreateCommand;
import com.test.subject.model.commands.ReconnectSourceCommand;

/**
 * @author Swei
 *
 */
public class NodeGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

    protected Command getConnectionCompleteCommand(CreateConnectionRequest request) {
        ConnectionCreateCommand command = (ConnectionCreateCommand) request.getStartCommand();
        command.setTarget((Node) getHost().getModel());
        return command;
    }

    protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
        ConnectionCreateCommand command = new ConnectionCreateCommand();
        command.setSource((Node) getHost().getModel());
        request.setStartCommand(command);
        return command;
    }

    protected Command getReconnectSourceCommand(ReconnectRequest request) {
		ReconnectSourceCommand cmd = new ReconnectSourceCommand();
		cmd.setConnection((Connection)request.getConnectionEditPart().getModel());
		cmd.setSource((Node)getHost().getModel());
		return cmd;
    }

    protected Command getReconnectTargetCommand(ReconnectRequest request) {
        return null;
    }
}