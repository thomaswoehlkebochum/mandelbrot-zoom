package org.woehlke.computer.kurzweil.mandelbrot.zoom.model.turing;

/**
 * Mandelbrot Set Zoom in by Mouse Click and Zoom out by Button.
 * (C) 2006 - 2026 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/thomaswoehlkebochum/mandelbrot-zoom">Github Repository</a>
 * @see <a href="https://java.woehlke.org/mandelbrot-zoom/">Maven Project Repository</a>
 *
 * Created by tw on 18.08.15.
 */
public enum TuringPhase {
    SEARCH_THE_SET,
    WALK_AROUND_THE_SET,
    FILL_THE_OUTSIDE_WITH_COLOR,
    FINISHED
}
