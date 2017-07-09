package com.test.subject.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * @author Swei
 *
 */
public class Node extends Element implements IPropertySource {
    private static final long serialVersionUID = 3906932274669630514L;

    final public static String PROP_LOCATION = "LOCATION";

    final public static String PROP_SIZE = "SIZE";

    final public static String PROP_NAME = "NAME";

    final public static String PROP_INPUTS = "INPUTS";

    final public static String PROP_OUTPUTS = "OUTPUTS";

    protected Point location = new Point(0, 0);

    protected Dimension size = new Dimension(100, 150);

    protected String name = "Node";

    protected IPropertyDescriptor[] descriptors = new IPropertyDescriptor[] { new TextPropertyDescriptor(PROP_NAME,
            "Name") };

    protected List outputs = new ArrayList(5);

    protected List inputs = new ArrayList(5);

    public void addInput(Connection connection) {
        this.inputs.add(connection);
        fireStructureChange(PROP_INPUTS, connection);
    }

    public void addOutput(Connection connection) {
        this.outputs.add(connection);
        fireStructureChange(PROP_OUTPUTS, connection);
    }

    public List getIncomingConnections() {
        return this.inputs;
    }

    public List getOutgoingConnections() {
        return this.outputs;
    }

    public void removeInput(Connection connection) {
        this.inputs.remove(connection);
        fireStructureChange(PROP_INPUTS, connection);
    }

    public void removeOutput(Connection connection) {
        this.outputs.remove(connection);
        fireStructureChange(PROP_OUTPUTS, connection);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (this.name.equals(name)) {
            return;
        }
        this.name = name;
        firePropertyChange(PROP_NAME, null, name);
    }

    public void setLocation(Point p) {
        if (this.location.equals(p)) {
            return;
        }
        this.location = p;
        firePropertyChange(PROP_LOCATION, null, p);
    }

    public Point getLocation() {
        return location;
    }

    public void setSize(Dimension d) {
        if (this.size.equals(d)) {
            return;
        }
        this.size = d;
        firePropertyChange(PROP_SIZE, null, d);
    }

    public Dimension getSize() {
        return size;
    }

    //------------------------------------------------------------------------
    // Abstract methods from IPropertySource

    public Object getEditableValue() {
        return this;
    }

    public IPropertyDescriptor[] getPropertyDescriptors() {
        return descriptors;
    }

    public Object getPropertyValue(Object id) {
        if (PROP_NAME.equals(id))
            return getName();
        return null;
    }

    public boolean isPropertySet(Object id) {
        return true;
    }

    public void resetPropertyValue(Object id) {

    }

    public void setPropertyValue(Object id, Object value) {
        if (PROP_NAME == id)
            setName((String) value);
    }
}