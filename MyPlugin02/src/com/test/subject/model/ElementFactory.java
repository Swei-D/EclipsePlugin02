package com.test.subject.model;

import org.eclipse.gef.requests.CreationFactory;

/**
 * @author Swei
 *
 */
public class ElementFactory implements CreationFactory {

    private Object template;

    public ElementFactory(Object template) {
        this.template = template;
    }

    public Object getNewObject() {
        try {
            return ((Class) template).newInstance();
         }
         catch (Exception e) {
            return null;
         }
    }

    public Object getObjectType() {
        return template;
    }
}