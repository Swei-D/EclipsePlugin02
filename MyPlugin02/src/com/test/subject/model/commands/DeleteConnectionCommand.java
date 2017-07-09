package com.test.subject.model.commands;

import org.eclipse.gef.commands.Command;

import com.test.subject.model.Connection;
import com.test.subject.model.Node;

/**
 * @author Swei
 *
 */
public class DeleteConnectionCommand extends Command {

    Node source;

    Node target;

    Connection connection;

    //Setters
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setSource(Node source) {
        this.source = source;
    }

    public void setTarget(Node target) {
        this.target = target;
    }

    public void execute() {
        source.removeOutput(connection);
        target.removeInput(connection);
        connection.setSource(null);
        connection.setTarget(null);
    }

    public String getLabel() {
        return "Delete Connection";
    }

    public void redo() {
        execute();
    }

    public void undo() {
        connection.setSource(source);
        connection.setTarget(target);
        source.addOutput(connection);
        target.addInput(connection);
    }
}