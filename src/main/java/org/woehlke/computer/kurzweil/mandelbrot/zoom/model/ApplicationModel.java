package org.woehlke.computer.kurzweil.mandelbrot.zoom.model;

import lombok.extern.slf4j.Slf4j;

import org.woehlke.computer.kurzweil.mandelbrot.zoom.config.ComputerKurzweilProperties;
import org.woehlke.computer.kurzweil.mandelbrot.zoom.model.fractal.GaussianNumberPlane;
import org.woehlke.computer.kurzweil.mandelbrot.zoom.model.common.Point;
import org.woehlke.computer.kurzweil.mandelbrot.zoom.model.turing.MandelbrotTuringMachine;
import org.woehlke.computer.kurzweil.mandelbrot.zoom.view.MandelbrotZoomFrame;

/**
 * Mandelbrot Set Zoom in by Mouse Click and Zoom out by Button.
 * (C) 2006 - 2026 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/thomaswoehlkebochum/mandelbrot-zoom">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-zoom/">Maven Project Repository</a>
 *
 * @see GaussianNumberPlane
 * @see MandelbrotTuringMachine
 *
 * @see ComputerKurzweilProperties
 * @see MandelbrotZoomFrame
 *
 * Created by tw on 16.12.2019.
 */
@Slf4j
public class ApplicationModel {

    private volatile GaussianNumberPlane gaussianNumberPlane;
    private volatile MandelbrotTuringMachine mandelbrotTuringMachine;
    //private volatile ApplicationStateMachine applicationStateMachine;

    private volatile ComputerKurzweilProperties config;
    private volatile MandelbrotZoomFrame frame;

    public ApplicationModel(ComputerKurzweilProperties config, MandelbrotZoomFrame frame) {
        this.config = config;
        this.frame = frame;
        this.gaussianNumberPlane = new GaussianNumberPlane(this);
        this.mandelbrotTuringMachine = new MandelbrotTuringMachine(this);
        //this.applicationStateMachine = new ApplicationStateMachine();
    }

    public synchronized boolean click(Point clicked) {
        //applicationStateMachine.click();
        boolean repaint = true;
        this.zoomIn(clicked);
        return repaint;
    }

    public synchronized boolean step() {
        /*
        boolean repaint = false;
        switch (applicationStateMachine.getApplicationState()) {
            case MANDELBROT:
                repaint = mandelbrotTuringMachine.step();
                break;
            case JULIA_SET:
                break;
        }
         */
        return mandelbrotTuringMachine.step();
    }

    public void zoomIn(Point clicked){
        this.gaussianNumberPlane.zoomIn(clicked);
    }

    public void zoomOut(){
        this.gaussianNumberPlane.zoomOut();
    }

    public synchronized int getCellStatusFor(int x, int y) {
        return gaussianNumberPlane.getCellStatusFor(x, y);
    }

    public Point getWorldDimensions() {
        int scale = config.getMandelbrotZoom().getView().getScale();
        int width = scale * config.getMandelbrotZoom().getView().getWidth();
        int height = scale * config.getMandelbrotZoom().getView().getHeight();
        return new Point(width, height);
    }

    public GaussianNumberPlane getGaussianNumberPlane() {
        return gaussianNumberPlane;
    }

}
