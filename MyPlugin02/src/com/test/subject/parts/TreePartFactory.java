package com.test.subject.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.test.subject.model.Diagram;
import com.test.subject.model.Node;


/**
 * @author Swei
 *
 */
public class TreePartFactory implements EditPartFactory{

    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof Diagram) {
            return new DiagramTreeEditPart(model);
         }
         else if (model instanceof Node) {
            return new NodeTreeEditPart(model);
         }
         else {
            return null;
         }
    }
}
