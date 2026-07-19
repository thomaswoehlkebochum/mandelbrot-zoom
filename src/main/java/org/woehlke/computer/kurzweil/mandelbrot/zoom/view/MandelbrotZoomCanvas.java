package org.woehlke.computer.kurzweil.mandelbrot.zoom.view;


import org.woehlke.computer.kurzweil.mandelbrot.zoom.model.ApplicationModel;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;


/**
 * Mandelbrot Set Zoom in by Mouse Click and Zoom out by Button.
 * (C) 2006 - 2026 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/thomaswoehlkebochum/mandelbrot-zoom">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-zoom/">Maven Project Repository</a>
 *
 * @see ApplicationModel
 * @see Dimension
 *
 * @see JComponent
 * @see Graphics
 *
 * Date: 05.02.2006
 * Time: 00:51:51
 */
public class MandelbrotZoomCanvas extends JComponent {

    @Serial
    private final static long serialVersionUID = 242L;

    private volatile ApplicationModel model;
    private volatile Dimension preferredSize;

    public MandelbrotZoomCanvas(ApplicationModel model) {
        this.model = model;
        int width = this.model.getWorldDimensions().getWidth();
        int height = this.model.getWorldDimensions().getHeight();
        this.preferredSize = new Dimension(width, height);
        this.setSize(this.preferredSize);
        this.setPreferredSize(preferredSize);
    }

    public void paint(Graphics g) {
        this.setSize(this.preferredSize);
        this.setPreferredSize(preferredSize);
        super.paintComponent(g);
        for(int y = 0; y < model.getWorldDimensions().getY(); y++){
            for(int x = 0; x < model.getWorldDimensions().getX(); x++){
                Color stateColor = getColorForCellStatus(model.getCellStatusFor(x,y));
                g.setColor(stateColor);
                g.fillRect(x,y,1,1);
            }
        }
    }

    private Color getColorForCellStatus(int cellStatus){
        int red = 0; //cellStatus % 256;
        int green = 0;
        int blue = cellStatus * 3 + 16;
        blue = Math.min(blue,255);
        Color stateColor = new Color(red, green, blue);
        return stateColor;
    }

    public void update(Graphics g) {
        paint(g);
    }

}
