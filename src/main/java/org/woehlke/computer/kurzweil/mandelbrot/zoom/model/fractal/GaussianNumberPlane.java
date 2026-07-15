package org.woehlke.computer.kurzweil.mandelbrot.zoom.model.fractal;

import lombok.Getter;
import lombok.Setter;
import org.woehlke.computer.kurzweil.mandelbrot.zoom.model.ApplicationModel;
import org.woehlke.computer.kurzweil.mandelbrot.zoom.model.common.Point;

import java.util.Stack;


/**
 * Mandelbrot Set drawn by a Turing Machine.
 * (C) 2006 - 2022 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/mandelbrot-julia">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-julia/">Maven Project Repository</a>
 *
 * @see ComplexNumber
 * @see Point
 *
 * Created by tw on 16.12.2019.
 */
public class GaussianNumberPlane {

    private volatile int[][] lattice;

    private final Point worldDimensions;

    public final static int YET_UNCOMPUTED = -1;

    private final static double complexWorldDimensionRealX = 3.2d;
    private final static double complexWorldDimensionImgY = 2.34d;
    private final static double complexCenterForMandelbrotRealX = -2.2d;
    private final static double complexCenterForMandelbrotImgY = -1.17d;

    private volatile ComplexNumber complexWorldDimensions;
    private volatile ComplexNumber complexCenterForMandelbrot;

    public GaussianNumberPlane(ApplicationModel model) {
        this.worldDimensions = model.getWorldDimensions();
        this.lattice = new int[worldDimensions.getWidth()][worldDimensions.getHeight()];
        this.complexWorldDimensions = new ComplexNumber(
            complexWorldDimensionRealX,
            complexWorldDimensionImgY
        );
        this.complexCenterForMandelbrot = new ComplexNumber(
            complexCenterForMandelbrotRealX,
            complexCenterForMandelbrotImgY
        );
        start();
    }

    public synchronized void start() {
        for (int y = 0; y < this.worldDimensions.getY(); y++) {
            for (int x = 0; x < worldDimensions.getX(); x++) {
                lattice[x][y] = YET_UNCOMPUTED;
            }
        }
    }

    public synchronized int getCellStatusFor(int x, int y) {
        return (lattice[x][y]) < 0 ? 0 : lattice[x][y];
    }

    private synchronized ComplexNumber getComplexNumberFromLatticeCoordsForMandelbrot(Point turingPosition) {
        double realX = (
            complexCenterForMandelbrot.getReal()
                + (complexWorldDimensions.getReal() * turingPosition.getX())
                / worldDimensions.getX()
        );
        double imgY = (
            complexCenterForMandelbrot.getImg()
                + (complexWorldDimensions.getImg() * turingPosition.getY())
                / worldDimensions.getY()
        );
        return new ComplexNumber(realX, imgY);
    }

    /*
    private synchronized ComplexNumber getComplexNumberFromLatticeCoordsForMandelbrot(
        Point turingPosition, ComplexNumber myComplexWorldDimensions, WorldDataItemForMandelbrot oldItem
    ) {
        double realX = (
            oldItem.getOldCenter().getReal()
                + (myComplexWorldDimensions.getReal() * turingPosition.getX())
                / worldDimensions.getX()
        );
        double imgY = (
            oldItem.getOldCenter().getImg()
                + (myComplexWorldDimensions.getImg() * turingPosition.getY())
                / worldDimensions.getY()
        );
        return new ComplexNumber(realX, imgY);
    }
    */

    public synchronized boolean isInMandelbrotSet(Point turingPosition) {
        ComplexNumber position = this.getComplexNumberFromLatticeCoordsForMandelbrot(turingPosition);
        lattice[turingPosition.getX()][turingPosition.getY()] = position.computeMandelbrotSet();
        return position.isInMandelbrotSet();
    }

    public synchronized void fillTheOutsideWithColors() {
        for (int y = 0; y < worldDimensions.getY(); y++) {
            for (int x = 0; x < worldDimensions.getX(); x++) {
                if (lattice[x][y] == YET_UNCOMPUTED) {
                    this.isInMandelbrotSet(new Point(x, y));
                }
            }
        }
    }

    public synchronized void computeTheMandelbrotSetFor(WorldDataItemForMandelbrot item) {
        double realX, imgY;
        for (int y = 0; y < worldDimensions.getY(); y++) {
            for (int x = 0; x < worldDimensions.getX(); x++) {
                //ComplexNumber z = this.getComplexNumberFromLatticeCoordsForMandelbrot(zPoint);
                realX = (
                    item.getUpperLeftCorner().getReal()
                        + ((item.getDelta().getReal() * x) / worldDimensions.getX())
                );
                imgY = (
                    item.getUpperLeftCorner().getImg()
                        + ((item.getDelta().getImg() * y) / worldDimensions.getY())
                );
                //return new ComplexNumber(realX, imgY);
                ComplexNumber z = new ComplexNumber(realX, imgY);
                lattice[x][y] = z.computeMandelbrotSet();
            }
        }
    }

    @Getter
    @Setter
    private class WorldDataItemForMandelbrot {
        private Point clicked;
        private ComplexNumber delta;
        private ComplexNumber center;
        private ComplexNumber upperLeftCorner;
    }

    private Stack<WorldDataItemForMandelbrot> worldataForMandelbrot = new Stack<>();

    public void zoomIn(Point clicked){
        WorldDataItemForMandelbrot item = new WorldDataItemForMandelbrot();
        item.setClicked(clicked);
        if(worldataForMandelbrot.empty()) {
            WorldDataItemForMandelbrot firstItem = new WorldDataItemForMandelbrot();
            firstItem.setDelta(complexWorldDimensions);
            firstItem.setCenter(complexCenterForMandelbrot);
            double myFirstStartRealX = complexCenterForMandelbrotRealX; // TODO
            double myFirstStartImgY = complexCenterForMandelbrotImgY; // TODO
            firstItem.setUpperLeftCorner(new ComplexNumber(myFirstStartRealX,myFirstStartImgY));
            worldataForMandelbrot.push(firstItem);
            //ComplexNumber center = getComplexNumberFromLatticeCoordsForMandelbrot(clicked);
            double realX = (
                complexCenterForMandelbrot.getReal()
                    + (complexWorldDimensions.getReal() * clicked.getX())
                    / worldDimensions.getX()
            );
            double imgY = (
                complexCenterForMandelbrot.getImg()
                    + (complexWorldDimensions.getImg() * clicked.getY())
                    / worldDimensions.getY()
            );
            ComplexNumber oldCenter = new ComplexNumber(realX, imgY);
            item.setCenter(oldCenter);
            double myDeltaRealX = (complexWorldDimensionRealX * 2.0d) / 3.0d;
            double myDeltaImgY = (complexWorldDimensionImgY * 2.0d) / 3.0d;
            ComplexNumber delta = new ComplexNumber(
                myDeltaRealX,myDeltaImgY
            );
            item.setDelta(delta);
            double myStartRealX = oldCenter.getReal()-(myDeltaRealX / 2.0d);
            double myStartImgY = oldCenter.getImg()-(myDeltaImgY / 2.0d);
            ComplexNumber start = new ComplexNumber(
                myStartRealX, myStartImgY
            );
            item.setUpperLeftCorner(start);
        } else {
            WorldDataItemForMandelbrot oldItem = worldataForMandelbrot.peek();
            double realX = (
                oldItem.getUpperLeftCorner().getReal()
                    + (oldItem.getDelta().getReal() * clicked.getX())
                    / worldDimensions.getX()
            );
            double imgY = (
                oldItem.getUpperLeftCorner().getImg()
                    + (oldItem.getDelta().getImg() * clicked.getY())
                    / worldDimensions.getY()
            );
            ComplexNumber oldCenter = new ComplexNumber(realX, imgY);
            item.setCenter(oldCenter);
            double myDeltaRealX = (oldItem.getDelta().getReal() * 2.0d) / 3.0d;
            double myDeltaImgY = (oldItem.getDelta().getImg() * 2.0d) / 3.0d;
            ComplexNumber delta = new ComplexNumber(
                myDeltaRealX,myDeltaImgY
            );
            item.setDelta(delta);
            double myStartRealX = oldCenter.getReal()-(myDeltaRealX / 2.0d);
            double myStartImgY = oldCenter.getImg()-(myDeltaImgY / 2.0d);
            ComplexNumber start = new ComplexNumber(
                myStartRealX, myStartImgY
            );
            item.setUpperLeftCorner(start);
        }
        computeTheMandelbrotSetFor(item);
        worldataForMandelbrot.push(item);
        System.out.println("zoomIn " + clicked.getX()+" : "+clicked.getY());
    }

    public void zoomOut(){
        if(!worldataForMandelbrot.empty()) {
            WorldDataItemForMandelbrot oldItem = worldataForMandelbrot.pop();
            computeTheMandelbrotSetFor(oldItem);
            System.out.println("zoomOut: "+oldItem.getDelta().getReal()+" "+oldItem.getUpperLeftCorner().getReal());
        }
    }
}
