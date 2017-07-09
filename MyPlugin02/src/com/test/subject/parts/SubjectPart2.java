package com.test.subject.parts;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;

import com.test.subject.figures.CollapsedSubjectFigure;
import com.test.subject.figures.SubjectFigure;
import com.test.subject.model.Attribute;
import com.test.subject.model.Node;
import com.test.subject.model.Subject;
import com.test.subject.policies.SubjectLayoutEditPolicy;

/**
 * @author Swei
 * 这个类是SubjectPart的备份，暂时不使用。
 */
public class SubjectPart2 extends NodePart {

    SubjectFigure expandedFigure = new SubjectFigure();

    CollapsedSubjectFigure collapsedFigure = new CollapsedSubjectFigure();

    public void performRequest(Request req) {
        if (req.getType() == RequestConstants.REQ_OPEN)
            getSubject().setCollapsed(!getSubject().isCollapsed());
    }

    public IFigure getContentPane() {
        if (!getSubject().isCollapsed())
            return expandedFigure.getAttributeFigure();
        else
            return collapsedFigure;
    }

    protected IFigure createFigure() {
        return null;
    }

    public IFigure getFigure() {
        if (!getSubject().isCollapsed())
            return expandedFigure;
        else
            return collapsedFigure;
    }

    protected void refreshVisuals() {
        super.refreshVisuals();
        if (!getSubject().isCollapsed()) {
            expandedFigure.setName(((Node) this.getModel()).getName() + "(" + (getSubject().isCollapsed() ? "C" : "E")
                    + ")");
        } else {
//            collapsedFigure.setPreferredSize(30, 30);
            Rectangle rectangle = new Rectangle(getSubject().getLocation(), new Dimension(50,50));
            ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), rectangle);
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
            if (!getSubject().isCollapsed()) {
                ((DiagramPart) getParent()).getFigure().remove(collapsedFigure);
                ((DiagramPart) getParent()).getFigure().add(expandedFigure);
            } else {
                ((DiagramPart) getParent()).getFigure().remove(expandedFigure);
                ((DiagramPart) getParent()).getFigure().add(collapsedFigure);
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