package com.test.subject.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.DirectEditManager;

import com.test.subject.model.Node;
import com.test.subject.policies.NodeEditPolicy;
import com.test.subject.policies.NodeGraphicalNodeEditPolicy;

/**
 * @author Swei
 *
 */
public abstract class NodePart extends AbstractGraphicalEditPart implements PropertyChangeListener, NodeEditPart {

//    protected DirectEditManager manager;

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Node.PROP_LOCATION))
            refreshVisuals();
        else if (evt.getPropertyName().equals(Node.PROP_SIZE))
            refreshVisuals();
        else if (evt.getPropertyName().equals(Node.PROP_INPUTS))
            refreshTargetConnections();
        else if (evt.getPropertyName().equals(Node.PROP_OUTPUTS))
            refreshSourceConnections();
    }

    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new NodeEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeEditPolicy());
    }

    public void activate() {
        if (isActive()) {
            return;
        }
        super.activate();
    }

    public void deactivate() {
        if (!isActive()) {
            return;
        }
        super.deactivate();
    }

    protected void refreshVisuals() {
        Node node = (Node) getModel();
        Point loc = node.getLocation();
        Dimension size = new Dimension(node.getSize());
        Rectangle rectangle = new Rectangle(loc, size);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
    }

    //------------------------------------------------------------------------
    // Abstract methods from NodeEditPart

    public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart connection) {
        return new NodeConnectionAnchor(getFigure());
    }

    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new NodeConnectionAnchor(getFigure());
    }

    public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart connection) {
        return new NodeConnectionAnchor(getFigure());
    }

    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new NodeConnectionAnchor(getFigure());
    }

    protected List getModelSourceConnections() {
        return ((Node) this.getModel()).getOutgoingConnections();
    }

    protected List getModelTargetConnections() {
        return ((Node) this.getModel()).getIncomingConnections();
    }

}