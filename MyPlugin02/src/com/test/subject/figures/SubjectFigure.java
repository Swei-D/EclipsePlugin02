package com.test.subject.figures;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

/**
 * @author Swei
 *
 */
public class SubjectFigure extends Figure {
    public static Color color = new Color(null, 255, 255, 206);
    private Label name = new Label();

    private AttributesFigure attributesFigure = new AttributesFigure();

    public SubjectFigure() {
        ToolbarLayout layout = new ToolbarLayout();
        layout.setVertical(true);
        layout.setStretchMinorAxis(true);
        setLayoutManager(layout);
        setBorder(new LineBorder());
        setBackgroundColor(color);
        setOpaque(true);
        
        add(name);
        add(attributesFigure);
    }

    public AttributesFigure getAttributeFigure() {
        return attributesFigure;
    }

    public void setName(String name) {
        this.name.setText(name);
    }
    
    public void setIcon(Image icon){
        name.setIcon(icon);
    }
    
    class AttributesFigure extends Figure {
        public AttributesFigure() {
            XYLayout layout = new XYLayout();
            setLayoutManager(layout);
            setBorder(new AttributesFigureBorder());
            setOpaque(true);
        }

    }

    class AttributesFigureBorder extends AbstractBorder {

        public Insets getInsets(IFigure figure) {
            return new Insets(1, 0, 0, 0);
        }

        public void paint(IFigure figure, Graphics graphics, Insets insets) {
            graphics.drawLine(getPaintRectangle(figure, insets).getTopLeft(), getPaintRectangle(figure, insets)
                    .getTopRight());
        }
    }}