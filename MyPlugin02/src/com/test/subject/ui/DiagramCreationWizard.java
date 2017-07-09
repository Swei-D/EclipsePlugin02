package com.test.subject.ui;

import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.part.FileEditorInput;

import com.test.subject.model.Diagram;
import com.test.subject.model.Subject;


public class DiagramCreationWizard extends Wizard implements INewWizard {

   private WizardPage wizardPage;
   private IStructuredSelection selection;
   private IWorkbench workbench;


   //------------------------------------------------------------------------
   // Abstract methods from IWorkbenchWizard

   public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
      this.workbench = workbench;
      this.selection = currentSelection;
   }

   //------------------------------------------------------------------------
   // Overridden from Wizard

   public void addPages() {
      // by default the superclass doesn't have any pages - here we add one
      this.wizardPage = new WizardPage(this.workbench, this.selection);
      addPage(this.wizardPage);
   }


  //------------------------------------------------------------------------
  // Abstract methods from Wizard

   public boolean performFinish() {
      return this.wizardPage.finish();
   }
}

class WizardPage extends WizardNewFileCreationPage {

   private IWorkbench workbench;

   public WizardPage(IWorkbench aWorkbench, IStructuredSelection selection) {
      super("riverPage1", selection);
      this.setTitle("Create River Model");
      this.setDescription("Create a new River model");
      this.workbench = aWorkbench;
   }

   private Diagram createSampleDiagram() {
      Diagram diagram = new Diagram();

      Subject a = new Subject();
      Subject b = new Subject();

      a.setLocation(new Point(100, 50));
      b.setLocation(new Point(300, 50));

      diagram.addSubject(a);
      diagram.addSubject(b);

      return diagram;
   }

   public boolean finish() {
      IFile newFile = createNewFile();
      if (newFile == null)
         return false; // ie.- creation was unsuccessful

      // Since the file resource was created fine, open it for editing
      // iff requested by the user
      try {
         IWorkbenchWindow dwindow = workbench.getActiveWorkbenchWindow();
         IWorkbenchPage page = dwindow.getActivePage();
         if (page != null)
            page.openEditor(new FileEditorInput(newFile),"com.test.subject.ui.SubjectEditor");
      }
      catch (org.eclipse.ui.PartInitException e) {
         e.printStackTrace();
         return false;
      }
      return true;
   }


   //------------------------------------------------------------------------
   // Overridden from WizardNewFileCreationPage

   public void createControl(Composite parent) {
      super.createControl(parent);
      this.setFileName("model.subject");
      setPageComplete(validatePage());
   }

   protected InputStream getInitialContents() {
      // by default the superclass returns null for this
      
      Diagram diagram = createSampleDiagram();

      InputStream istream = null;
      try {
         istream = diagram.getAsStream();
      }
      catch (Exception e) {
         e.printStackTrace();
      }
      return istream;
   }
}