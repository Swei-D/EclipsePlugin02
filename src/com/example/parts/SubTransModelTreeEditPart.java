package com.example.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.example.model.FStepModel;
import com.example.model.FSubTransModel;


public class SubTransModelTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener{
    public SubTransModelTreeEditPart(Object model) {
        super(model);
     }

    public void propertyChange(PropertyChangeEvent evt) {
        refreshChildren();
    }
    public void activate() {
        super.activate();
        ((FSubTransModel) getModel()).addPropertyChangeListener(this);
    }
    public void deactivate() {
        super.deactivate();
        ((FSubTransModel) getModel()).removePropertyChangeListener(this);
    }
    protected List getModelChildren() {
        return ((FSubTransModel) getModel()).getChildren();
    }
    
    protected void refreshVisuals() {
    	System.out.println("SubTransModelTreeEditPart -> refreshVisuals : " + ((FSubTransModel) getModel()).getName());
        
        setWidgetText(((FSubTransModel) getModel()).getName());
    }
}

