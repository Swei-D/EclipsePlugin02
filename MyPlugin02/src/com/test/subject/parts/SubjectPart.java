package com.test.subject.parts;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.test.subject.IConstants;
import com.test.subject.SubjectPlugin;
import com.test.subject.figures.SubjectFigure;
import com.test.subject.model.Attribute;
import com.test.subject.model.Node;
import com.test.subject.model.Subject;
import com.test.subject.policies.SubjectLayoutEditPolicy;

/**
 * @author Swei
 *
 */
public class SubjectPart extends NodePart {

    public void performRequest(Request req) {
        if (req.getType() == RequestConstants.REQ_OPEN)
            getSubject().setCollapsed(!getSubject().isCollapsed());
    }

    public IFigure getContentPane() {
        return ((SubjectFigure) getFigure()).getAttributeFigure();
    }

    protected IFigure createFigure() {
        return new SubjectFigure();
    }

    protected void refreshVisuals() {
        super.refreshVisuals();
        SubjectFigure figure = (SubjectFigure) getFigure();
        figure.setName(((Node) this.getModel()).getName());
        if (!getSubject().isCollapsed()) {
            figure.setIcon(SubjectPlugin.getImage(IConstants.IMG_FILE));
        } else {
            figure.setIcon(SubjectPlugin.getImage(IConstants.IMG_FOLDER));
        }
    }

    protected void createEditPolicies() {
        //        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new NodeGraphicalNodeEditPolicy());
        super.createEditPolicies();
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new SubjectLayoutEditPolicy());
    }

    protected List getModelChildren() {
        return getSubject().getAttributes();
    }

    public void activate() {
        super.activate();
        getSubject().addPropertyChangeListener(this);
    }

    public void deactivate() {
        super.deactivate();
        getSubject().removePropertyChangeListener(this);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (Subject.PROP_COLLAPSED.equals(evt.getPropertyName())) {
            SubjectFigure figure = ((SubjectFigure) getFigure());
            if (!getSubject().isCollapsed()) {
                figure.add(figure.getAttributeFigure());
            } else {
                figure.remove(figure.getAttributeFigure());
            }
            refreshVisuals();
            refreshSourceConnections();
            refreshTargetConnections();
        }
        if (Subject.PROP_STRUCTURE.equals(evt.getPropertyName()))
            refreshChildren();
        super.propertyChange(evt);
    }

    protected Subject getSubject() {
        return (Subject) getModel();
    }

    protected List getModelSourceConnections() {
        if (!getSubject().isCollapsed()) {
            return getSubject().getOutgoingConnections();
        } else {
            List l = new ArrayList();
            l.addAll(getSubject().getOutgoingConnections());
            for (Iterator iter = getSubject().getAttributes().iterator(); iter.hasNext();) {
                Attribute attribute = (Attribute) iter.next();
                l.addAll(attribute.getOutgoingConnections());
            }
            return l;
        }
    }

    protected List getModelTargetConnections() {
        if (!getSubject().isCollapsed()) {
            return getSubject().getIncomingConnections();
        } else {
            List l = new ArrayList();
            l.addAll(getSubject().getIncomingConnections());
            for (Iterator iter = getSubject().getAttributes().iterator(); iter.hasNext();) {
                Attribute attribute = (Attribute) iter.next();
                l.addAll(attribute.getIncomingConnections());
            }
            return l;
        }
    }

}