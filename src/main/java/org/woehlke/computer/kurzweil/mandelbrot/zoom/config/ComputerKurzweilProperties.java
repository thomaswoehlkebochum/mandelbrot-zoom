package org.woehlke.computer.kurzweil.mandelbrot.zoom.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.woehlke.computer.kurzweil.mandelbrot.zoom.model.common.Point;


import java.io.InputStream;
import java.io.Serializable;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * &copy; 2006 - 2008 Thomas Woehlke.
 * @author Thomas Woehlke
 *
 * @see <a href="https://thomas-woehlke.blogspot.com/2016/01/mandelbrot-set-drawn-by-turing-machine.html">Blog Article</a>
 * @see <a href="https://github.com/Computer-Kurzweil/simulated-evolution">Github Repository</a>
 * @see <a href="https://java.woehlke.org/simulated-evolution/">Maven Project Repository</a>
 */
@Log4j2
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Getter
@Setter
public class ComputerKurzweilProperties implements Serializable {


    public Allinone allinone = new Allinone();


    public MandelbrotJulia mandelbrotJulia = new MandelbrotJulia();


    public MandelbrotZoom mandelbrotZoom = new MandelbrotZoom();


    public SimulatedEvolution simulatedevolution = new SimulatedEvolution();


    public Cca cca = new Cca();


    public WienerProcess randomwalk = new WienerProcess();


    public Dla dla = new Dla();


    public Kochsnowflake kochsnowflake = new Kochsnowflake();


    public Samegame samegame = new Samegame();


    public Sierpinskitriangle sierpinskitriangle = new Sierpinskitriangle();


    public Tetris tetris = new Tetris();


    public Turmite turmite = new Turmite();


    public Wator wator = new Wator();


    public Gameoflive gameoflive = new Gameoflive();

    @ToString
    @Getter
    @Setter
    public static class Allinone {


        public Lattice lattice = new Lattice();


        public View view = new View();

        @Getter
        @Setter
        @ToString
        public static class Lattice {


            private Integer width;


            private Integer height;
        }

        @ToString
        @Getter
        @Setter
        public static class View {


            private String title;


            private String subtitle;


            private String copyright;


            private Integer borderPaddingX;


            private Integer borderPaddingY;


            private Integer titleHeight;


            private String startStopp;


            private String start;


            private String stop;


            private String info;
        }
    }

    @ToString
    @Getter
    @Setter
    public static class MandelbrotJulia {


        public View view = new View();


        public Control control = new Control();

        @ToString
        @Getter
        @Setter
        public static class View {


            private String title;


            private String subtitle;


            private String copyright;


            private Integer width;


            private Integer height;


            private Integer scale;
        }

        @ToString
        @Getter
        @Setter
        public static class Control {


            private Integer threadSleepTime;
        }
    }

    @ToString
    @Getter
    @Setter
    public static class MandelbrotZoom {
        public View view = new View();
        public Control control = new Control();
        public Model model = new Model();

        @ToString
        @Getter
        @Setter
        public static class View {
            private String title;
            private String subtitle;
            private String copyright;
            private String buttonsZoomOut;
            private String buttonsZoomLabel;
            private Integer width;
            private Integer height;
            private Integer scale;
        }

        @ToString
        @Getter
        @Setter
        public static class Model {
            public Complex complexWorldDimension = new Complex();
            public Complex complexCenterForMandelbrot = new Complex();
            public Complex complexCenterForJulia = new Complex();

            @ToString
            @Getter
            @Setter
            public static class Complex {
                private Double realX;
                private Double imgY;
            }
        }

        @ToString
        @Getter
        @Setter
        public static class Control {
            private Integer threadSleepTime;
        }
    }

    @ToString
    @Getter
    @Setter
    public static class SimulatedEvolution {
        public View view = new View();
        public Control control = new Control();
        public CellConf cellConf = new CellConf();
        public Population population = new Population();
        public Food food = new Food();
        public GardenOfEden gardenOfEden = new GardenOfEden();

        @ToString
        @Getter
        @Setter
        public static class View {
            private String title;
            private String subtitle;
            private String copyright;
            private Integer width;
            private Integer height;
            private Integer scale;
        }

        @ToString
        @Getter
        @Setter
        public static class Control {


            private Integer threadSleepTime;


            private Integer exitStatus;


            private Integer queueMaxLength;
        }

        @ToString
        @Getter
        @Setter
        public static class CellConf {


            private Integer fatMax;


            private Integer fatHungerMax;


            private Integer fatMinimumForSex;


            private Integer fatAtBirth;


            private Integer fatPerFood;


            private Integer ageOfAdulthood;


            private Integer ageOld;


            private Integer ageMax;
        }

        @ToString
        @Getter
        @Setter
        public static class Population {


            private Integer initialPopulation;


            private String panelPopulationStatistics;


            private String youngCellsLabel;


            private String youngAndFatCellsLabel;


            private String fullAgeCellsLabel;


            private String hungryCellsLabel;


            private String oldCellsLabel;


            private String populationLabel;


            private String generationOldestLabel;


            private String generationYoungestLabel;
        }

        @ToString
        @Getter
        @Setter
        public static class Food {


            private Integer foodPerDay;


            private Integer foodPerDayFieldColumns;


            private String foodPerDayLabel;


            private String foodPerDayBorderLabel;


            private String buttonFoodPerDayIncrease;


            private String buttonFoodPerDayDecrease;


            private String panelFood;
        }

        @ToString
        @Getter
        @Setter
        public static class GardenOfEden {


            private String panelGardenOfEden;


            private Boolean gardenOfEdenEnabled;


            private String gardenOfEdenEnabledString;


            private String gardenOfEdenEnabledToggleButton;


            private Integer foodPerDay;


            private Integer gardenOfEdenLatticeDivisor;


            private Integer gardenOfEdenLatticeDivisorPadding;
        }
    }

    @ToString
    @Getter
    @Setter
    public static class Cca {


        public View view = new View();


        public Control control = new Control();

        @ToString
        @Getter
        @Setter
        public static class View {


            private String title;


            private String subtitle;


            public Neighborhood neighborhood = new Neighborhood();

            @ToString
            @Getter
            @Setter
            public static class Neighborhood {


                private String title;


                private String typeVonNeumann;


                private String typeMoore;


                private String typeWoehlke;
            }
        }

        @ToString
        @Getter
        @Setter
        public static class Control {


            private Integer threadSleepTime;
        }
    }

    @ToString
    @Getter
    @Setter
    public static class WienerProcess {


        public View view = new View();


        public Control control = new Control();

        @ToString
        @Getter
        @Setter
        public static class View {


            private String title;


            private String subtitle;
        }

        @ToString
        @Getter
        @Setter
        public static class Control {


            private Integer threadSleepTime;
        }
    }

    @ToString
    @Getter
    @Setter
    public static class Dla {


        public View view = new View();


        public Control control = new Control();

        @ToString
        @Getter
        @Setter
        public static class View {


            private String title;


            private String subtitle;
        }

        @ToString
        @Getter
        @Setter
        public static class Control {


            private Integer threadSleepTime;


            private Integer numberOfParticles;
        }
    }

    @ToString
    @Getter
    @Setter
    public static class Kochsnowflake {


        public View view = new View();


        public Control control = new Control();

        @ToString
        @Getter
        @Setter
        public static class View {


            private String title;


            private String subtitle;


            public Neighborhood neighborhood = new Neighborhood();

            @ToString
            @Getter
            @Setter
            public static class Neighborhood {


                private String title;


                private String typeVonNeumann;


                private String typeMoore;


                private String typeWoehlke;
            }
        }

        @ToString
        @Getter
        @Setter
        public static class Control {


            private Integer threadSleepTime;


            private Integer numberOfParticles;
        }
    }

    @ToString
    @Getter
    @Setter
    public static class Samegame {


        public View view = new View();


        public Control control = new Control();

        @ToString
        @Getter
        @Setter
        public static class View {


            private String title;


            private String subtitle;


            public Neighborhood neighborhood = new Neighborhood();

            @ToString
            @Getter
            @Setter
            public static class Neighborhood {


                private String title;


                private String typeVonNeumann;


                private String typeMoore;


                private String typeWoehlke;
            }
        }

        @ToString
        @Getter
        @Setter
        public static class Control {


            private Integer threadSleepTime;


            private Integer numberOfParticles;
        }
    }

    @ToString
    @Getter
    @Setter
    public static class Sierpinskitriangle {


        public View view = new View();


        public Control control = new Control();

        @ToString
        @Getter
        @Setter
        public static class View {


            private String title;


            private String subtitle;


            public Neighborhood neighborhood = new Neighborhood();

            @Getter
            @Setter
            @ToString
            public static class Neighborhood {


                private String title;


                private String typeVonNeumann;


                private String typeMoore;


                private String typeWoehlke;
            }
        }

        @ToString
        @Getter
        @Setter
        public static class Control {


            private Integer threadSleepTime;


            private Integer numberOfParticles;
        }
    }


    @Getter
    @Setter
    @ToString
    public static class Tetris {


        public View view = new View();


        public Control control = new Control();

        @Getter
        @Setter
        @ToString
        public static class View {


            private String title;


            private String subtitle;


            public Neighborhood neighborhood = new Neighborhood();

            @Getter
            @Setter
            @ToString
            public static class Neighborhood {

                private String title;


                private String typeVonNeumann;


                private String typeMoore;


                private String typeWoehlke;
            }
        }

        @Getter
        @Setter
        @ToString
        public static class Control {


            private Integer threadSleepTime;


            private Integer numberOfParticles;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Turmite {


        public View view = new View();


        public Control control = new Control();

        @Getter
        @Setter
        @ToString
        public static class View {


            private String title;


            private String subtitle;


            public Neighborhood neighborhood = new Neighborhood();

            @ToString
            @Getter
            @Setter
            public static class Neighborhood {


                private String title;


                private String typeVonNeumann;


                private String typeMoore;


                private String typeWoehlke;
            }
        }

        @Getter
        @Setter
        @ToString
        public static class Control {


            private Integer threadSleepTime;


            private Integer numberOfParticles;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Wator {


        public View view = new View();


        public Control control = new Control();

        @Getter
        @Setter
        @ToString
        public static class View {


            private String title;


            private String subtitle;


            public Neighborhood neighborhood = new Neighborhood();

            @Getter
            @Setter
            @ToString
            public static class Neighborhood {

                private String title;


                private String typeVonNeumann;


                private String typeMoore;


                private String typeWoehlke;
            }
        }

        @Getter
        @Setter
        @ToString
        public static class Control {


            private Integer threadSleepTime;


            private Integer numberOfParticles;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class Gameoflive{
        public View view = new View();
        public Control control = new Control();

        @Getter
        @Setter
        @ToString
        public static class View {
            private String title;
            private String subtitle;
            public Neighborhood neighborhood = new Neighborhood();

            @Getter
            @Setter
            @ToString
            public static class Neighborhood {
                private String title;
                private String typeVonNeumann;
                private String typeMoore;
                private String typeWoehlke;
            }
        }

        @Getter
        @Setter
        @ToString
        public static class Control {
            private Integer threadSleepTime;
            private Integer numberOfParticles;
        }
    }

    public Point getWorldDimensions() {
        int scale = this.getMandelbrotZoom().getView().getScale();
        int width = scale * this.getMandelbrotZoom().getView().getWidth();
        int height = scale * this.getMandelbrotZoom().getView().getHeight();
        return new Point(width, height);
    }

    public static ComputerKurzweilProperties propertiesFactory(String conf, String jarPath){
        log.info("propertiesFactory");
        log.info("propertiesFactory conf: "+conf);
        log.info("propertiesFactory jar:  "+jarPath);
        ComputerKurzweilProperties properties;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            @SuppressWarnings("resource") JarFile jarFile = new JarFile(jarPath);
            JarEntry entry = jarFile.getJarEntry(conf);
            InputStream input = jarFile.getInputStream(entry);
            properties = mapper.readValue(input, ComputerKurzweilProperties.class);
            log.info(properties.toString());
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
            properties = new ComputerKurzweilProperties();
        }
        log.info("propertiesFactory done");
        return properties;
    }
}
