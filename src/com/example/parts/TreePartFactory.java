package com.example.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.example.model.FStepModel;
import com.example.model.FSubTransModel;
import com.example.model.FTransModel;
import com.example.model.TreeItemModel;


/**
 * @author Swei
 *
 */
public class TreePartFactory implements EditPartFactory{

    public EditPart createEditPart(EditPart context, Object model) {
        if(model instanceof FStepModel){
        	System.out.println("TreePartFactory  :  " + "FStepModel");
        	return new StepTreeEditPart(model);
        }
        else if (model instanceof FSubTransModel){
        	System.out.println("TreePartFactory  :  " + "FSubTransModel");
        	return new SubTransModelTreeEditPart(model);
        } else if (model instanceof FTransModel) {
        	System.out.println("TreePartFactory  :  " + "FTransModel");
			return new TransModelTreeEditPart(model);
		} else if (model instanceof TreeItemModel) {
			System.out.println("TreePartFactory  :  " + "TreeItemModel");
			return new TreeItemTreeEditPart(model);
		}
    	
    	return null;
//    	if (model instanceof Diagram) {
//            return new DiagramTreeEditPart(model);
//         }
//         else if (model instanceof Node) {
//            return new NodeTreeEditPart(model);
//         }
//         else {
//            return null;
//         }
    }
}
