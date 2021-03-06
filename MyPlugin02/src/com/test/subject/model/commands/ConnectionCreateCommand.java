package com.test.subject.model.commands;

import java.util.List;

import org.eclipse.gef.commands.Command;

import com.test.subject.model.Connection;
import com.test.subject.model.Node;

/**
 * @author Swei
 *
 */
public class ConnectionCreateCommand extends Command {

    protected Connection connection;

    protected Node source;

    protected Node target;

    public void setSource(Node source) {
        this.source = source;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    //------------------------------------------------------------------------
    // Overridden from Command

    public void execute() {
        connection = new Connection(source, target);
    }

    public String getLabel() {
        return "Create Connection";
    }

    public void redo() {
        this.source.addOutput(this.connection);
        this.target.addInput(this.connection);
    }

    public void undo() {
        this.source.removeOutput(this.connection);
        this.target.removeInput(this.connection);
    }

    public boolean canExecute() {
        if (source.equals(target))
            return false;
        // Check for existence of connection already
        List connections = this.source.getOutgoingConnections();
        for (int i = 0; i < connections.size(); i++) {
            if (((Connection) connections.get(i)).getTarget().equals(target))
                return false;
        }
        return true;
    }
}