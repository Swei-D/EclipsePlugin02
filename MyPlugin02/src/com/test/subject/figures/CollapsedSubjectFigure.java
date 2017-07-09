package com.test.subject.figures;

import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;

public class CollapsedSubjectFigure extends RectangleFigure {

    public CollapsedSubjectFigure() {
        setOpaque(true);
        setBackgroundColor(SubjectFigure.color);
        setBorder(new LineBorder());
    }

}
