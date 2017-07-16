package com.example.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import com.example.model.FStepModel;
import com.example.model.FSubTransModel;
import com.example.model.FTransModel;
import com.example.model.TreeItemModel;


public class TreeItemTreeEditPart extends AbstractTreeEditPart implements PropertyChangeListener{
    public TreeItemTreeEditPart(Object model) {
        super(model);
     }

    public void propertyChange(PropertyChangeEvent evt) {
        refreshChildren();
    }
    public void activate() {
        super.activate();
        ((TreeItemModel) getModel()).addPropertyChangeListener(this);
    }
    public void deactivate() {
        super.deactivate();
        ((TreeItemModel) getModel()).removePropertyChangeListener(this);
    }
    protected List getModelChildren() {
        return ((TreeItemModel) getModel()).getChildren();
    }
    protected void refreshVisuals() {
    	System.out.println("TransModelTreeEditPart -> refreshVisuals : " + ((TreeItemModel) getModel()).getName());
        
        setWidgetText(((TreeItemModel) getModel()).getName());
    }
}

