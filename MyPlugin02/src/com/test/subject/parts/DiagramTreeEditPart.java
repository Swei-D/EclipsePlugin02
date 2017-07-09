package com.test.subject.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.test.subject.model.Diagram;

/**
 * @author Swei
 *
 */
public class DiagramTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener{
    public DiagramTreeEditPart(Object model) {
        super(model);
     }

    public void propertyChange(PropertyChangeEvent evt) {

    }
    public void activate() {
        super.activate();
        ((Diagram) getModel()).addPropertyChangeListener(this);
    }
    public void deactivate() {
        super.deactivate();
        ((Diagram) getModel()).removePropertyChangeListener(this);
    }
    protected List getModelChildren() {
        return ((Diagram) getModel()).getSubjects();
    }
}
