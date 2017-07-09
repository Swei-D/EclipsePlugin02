package com.test.subject.model.commands;

import org.eclipse.gef.commands.Command;

import com.test.subject.model.Connection;
import com.test.subject.model.Node;

/**
 * @author Swei
 *
 */
public class ReconnectSourceCommand extends Command {
    private Connection connection;

    private Node newSource;

    private Node oldSource;

    private Node target;

    //setters
    public void setConnection(Connection connection) {
        this.connection = connection;
        this.target=this.connection.getTarget();
        this.oldSource=this.connection.getSource();
    }

    public void setSource(Node source) {
        this.newSource = source;
    }

    public void execute() {
        oldSource.removeOutput(connection);
        newSource.addOutput(connection);
        connection.setSource(newSource);
    }

    public String getLabel() {
        return "Reconnect Source";
    }

    public void redo() {
        execute();
    }

    public void undo() {
        newSource.removeOutput(connection);
        oldSource.addOutput(connection);
        connection.setSource(oldSource);
    }
}