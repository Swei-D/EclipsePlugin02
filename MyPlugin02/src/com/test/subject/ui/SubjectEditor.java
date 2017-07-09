package com.test.subject.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.test.subject.dnd.DiagramTemplateTransferDropTargetListener;
import com.test.subject.model.Diagram;
import com.test.subject.parts.PartFactory;
import com.test.subject.parts.TreePartFactory;

/**
 * @author Swei
 *
 */
public class SubjectEditor extends GraphicalEditorWithPalette {

    private Diagram diagram = new Diagram();

    private PaletteRoot paletteRoot;

    public Diagram getDiagram() {
        return this.diagram;
    }

    public SubjectEditor() {
        setEditDomain(new DefaultEditDomain(this));
    }

    protected void configureGraphicalViewer() {
        super.configureGraphicalViewer();
        getGraphicalViewer().setRootEditPart(new ScalableFreeformRootEditPart());
        getGraphicalViewer().setEditPartFactory(new PartFactory());
    }

    protected void initializeGraphicalViewer() {
        getGraphicalViewer().setContents(this.diagram);
        getGraphicalViewer().addDropTargetListener(new DiagramTemplateTransferDropTargetListener(getGraphicalViewer()));
    }

    public void doSave(IProgressMonitor monitor) {
    }

    public void doSaveAs() {
    }

    public boolean isDirty() {
        return getCommandStack().isDirty();
    }

    public boolean isSaveAsAllowed() {
        return true;
    }

    protected void setInput(IEditorInput input) {
        super.setInput(input);

//        IFile file = ((IFileEditorInput) input).getFile();
//        try { // attempt to read from a file
//            InputStream istream = file.getContents(false);
//            diagram = Diagram.makeFromStream(istream);
//        } catch (Exception e) { // but if there's an error, create a new diagram
//            e.printStackTrace();
//            diagram = new Diagram();
//        }
        diagram = new Diagram();
    }

    public Object getAdapter(Class type) {
        if (type == IContentOutlinePage.class)
            return new OutlinePage();
        return super.getAdapter(type);
    }

    class OutlinePage extends ContentOutlinePage {
        private PageBook pageBook;

        private Control outline;

        public OutlinePage() {
            super(new TreeViewer());
        }

        public void createControl(Composite parent) {
            pageBook = new PageBook(parent, SWT.NONE);
            outline = getViewer().createControl(pageBook);
            pageBook.showPage(outline);
            getSelectionSynchronizer().addViewer(getViewer());
            getViewer().setEditDomain(getEditDomain());
            //            getViewer().setRootEditPart()
            getViewer().setEditPartFactory(new TreePartFactory());
            getViewer().setContents(getDiagram());
            super.createControl(parent);
        }

        public Control getControl() {
            return super.getControl();
        }

        public void dispose() {
            getSelectionSynchronizer().removeViewer(getViewer());
            super.dispose();
        }
    }

    protected PaletteRoot getPaletteRoot() {
        if (this.paletteRoot == null) {
            this.paletteRoot = PaletteFactory.createPalette();
        }
        return this.paletteRoot;
    }

    protected void initializePaletteViewer() {
        super.initializePaletteViewer();
        getPaletteViewer().addDragSourceListener(new TemplateTransferDragSourceListener(getPaletteViewer()));
    }
}

