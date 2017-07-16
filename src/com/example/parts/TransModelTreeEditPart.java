package com.example.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.example.model.FStepModel;
import com.example.model.FSubTransModel;
import com.example.model.FTransModel;


public class TransModelTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener{
    public TransModelTreeEditPart(Object model) {
        super(model);
     }

    public void propertyChange(PropertyChangeEvent evt) {
        refreshChildren();
    }
    public void activate() {
        super.activate();
        ((FTransModel) getModel()).addPropertyChangeListener(this);
    }
    public void deactivate() {
        super.deactivate();
        ((FTransModel) getModel()).removePropertyChangeListener(this);
    }
    protected List getModelChildren() {
        return ((FTransModel) getModel()).getChildren();
    }
    protected void refreshVisuals() {
    	System.out.println("TransModelTreeEditPart -> refreshVisuals : " + ((FTransModel) getModel()).getName());
        
        setWidgetText(((FTransModel) getModel()).getName());
    }
}

