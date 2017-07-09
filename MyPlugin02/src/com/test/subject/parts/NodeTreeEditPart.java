package com.test.subject.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.test.subject.model.Node;

/**
 * @author Swei
 *
 */
public class NodeTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener{

    public NodeTreeEditPart(Object model) {
        super(model);
     }

    public void propertyChange(PropertyChangeEvent evt) {
        refreshVisuals();
    }
    public void activate() {
        super.activate();
        ((Node)getModel()).addPropertyChangeListener(this);
    }
    public void deactivate() {
        super.deactivate();
        ((Node)getModel()).removePropertyChangeListener(this);
    }
    
    
    protected void refreshVisuals() {
        setWidgetText(((Node)getModel()).getName());
    }
}
