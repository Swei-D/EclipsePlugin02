package com.test.subject.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Swei
 *
 */
public class Diagram extends Element {
    private static final long serialVersionUID = 3257847701181051954L;

    public static String PROP_STRUCTURE = "SUBJECT";

    protected List subjects = new ArrayList();

    public void addSubject(Subject subject) {
        subjects.add(subject);
        fireStructureChange(PROP_STRUCTURE, subjects);
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
        fireStructureChange(PROP_STRUCTURE, subjects);
    }

    public List getSubjects() {
        return subjects;
    }

    public void setSubjects(List subjects) {
        this.subjects = subjects;
    }

    public InputStream getAsStream() throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(this);
        out.close();
        InputStream istream = new ByteArrayInputStream(os.toByteArray());
        os.close();
        return istream;
    }

    public static Diagram makeFromStream(InputStream istream) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(istream);
        Diagram diagram = (Diagram) ois.readObject();
        ois.close();
        return diagram;
    }

}