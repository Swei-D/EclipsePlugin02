package com.test.subject.figures;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * @author Swei
 *
 */
public class AttributeFigure extends Figure {
    public static Color color = new Color(null, 230, 230, 230);

    private String text;

    private Label label;

    public AttributeFigure() {
        this.label = new Label();
        this.add(label);
        setOpaque(true);
        setBorder(new LineBorder(1));
        setBackgroundColor(color);
    }

    public String getText() {
        return this.label.getText();
    }

    public Rectangle getTextBounds() {
        return this.label.getTextBounds();
    }

    public void setText(String name) {
        this.text = name;
        this.label.setText(name);
        this.repaint();
    }

    //------------------------------------------------------------------------
    // Overridden methods from Figure

    public void setBounds(Rectangle rect) {
        super.setBounds(rect);
        this.label.setBounds(rect);
    }
}