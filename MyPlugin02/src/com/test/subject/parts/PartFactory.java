package com.test.subject.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.test.subject.model.Attribute;
import com.test.subject.model.Connection;
import com.test.subject.model.Diagram;
import com.test.subject.model.Subject;

/**
 * @author Swei
 *
 */
public class PartFactory implements EditPartFactory {

    public EditPart createEditPart(EditPart context, Object model) {
        EditPart part = null;
        if (model instanceof Diagram)
            part = new DiagramPart();
        else if (model instanceof Subject)
            part = new SubjectPart();
        else if (model instanceof Attribute)
            part = new AttributePart();
        else if (model instanceof Connection) {
            part = new ConnectionPart();
        }
        part.setModel(model);
        return part;
    }
}