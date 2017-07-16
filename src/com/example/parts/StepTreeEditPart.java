package com.example.parts;

import java.beans.PropertyChangeEvent;

import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.example.model.FStepModel;


public class StepTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener{
    public StepTreeEditPart(Object model) {
        super(model);
     }

    public void propertyChange(PropertyChangeEvent evt) {
        refreshChildren();
    }
    public void activate() {
        super.activate();
        ((FStepModel) getModel()).addPropertyChangeListener(this);
    }
    public void deactivate() {
        super.deactivate();
        ((FStepModel) getModel()).removePropertyChangeListener(this);
    }
    protected List getModelChildren() {
        return ((FStepModel) getModel()).getChildren();
    }
    protected void refreshVisuals() {
    	System.out.println("StepTreeEditPart -> refreshVisuals : " + ((FStepModel) getModel()).getName());
        setWidgetText(((FStepModel) getModel()).getName());
    }
}

