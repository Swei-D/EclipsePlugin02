package com.test.subject.parts;

import java.beans.PropertyChangeEvent;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;

import com.test.subject.figures.AttributeFigure;
import com.test.subject.model.Attribute;
import com.test.subject.model.Subject;

/**
 * @author Swei
 *
 */
public class AttributePart extends NodePart {

    protected IFigure createFigure() {
        return new AttributeFigure();
    }

    protected void createEditPolicies() {
        super.createEditPolicies();
    }

    protected void refreshVisuals() {
        super.refreshVisuals();
        Attribute attribute = (Attribute) getModel();
        AttributeFigure figure = (AttributeFigure) getFigure();
        figure.setText(attribute.getName());
    }

public void activate() {
    super.activate();
    ((Attribute) getModel()).addPropertyChangeListener(this);
    ((Subject) getParent().getModel()).addPropertyChangeListener(this);
}

public void deactivate() {
    super.deactivate();
    ((Attribute) getModel()).removePropertyChangeListener(this);
    ((Subject) getParent().getModel()).removePropertyChangeListener(this);
}

public void propertyChange(PropertyChangeEvent evt) {
    if (evt.getPropertyName().equals(Subject.PROP_COLLAPSED)) {
        refreshSourceConnections();
        refreshTargetConnections();
    }
    super.propertyChange(evt);
}

    protected List getModelSourceConnections() {
        Attribute attribute = (Attribute) getModel();
        Subject subject = (Subject) ((SubjectPart) getParent()).getModel();
        if (!subject.isCollapsed()) {
            return attribute.getOutgoingConnections();
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    protected List getModelTargetConnections() {
        Attribute attribute = (Attribute) getModel();
        Subject subject = (Subject) ((SubjectPart) getParent()).getModel();
        if (!subject.isCollapsed()) {
            return attribute.getIncomingConnections();
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}