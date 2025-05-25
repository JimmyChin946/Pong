package org.pong;

import java.awt.*;

public class T7Win {
    private static double[] trophyBaseX = new double[]{-2.5,-2.5,2.5,2.5};
    private static double[] trophyBaseY = new double[]{4.4,3.7,3.7,4.4};

    private static double[] trophyBodyX = new double[]{-1.485,-1.44,-1.19,-0.51,-0.504,-1.08,-1.49,-1.737,-1.737,1.737,1.737,1.49,1.08,0.504,0.504,1.184,1.434,1.479};
    private static double[] trophyBodyY = new double[]{3.7,3.38,3.073,2.918,1.924,1.777,1.398,1.046,-2.56,-2.56,1.046,1.398,1.777,1.924,2.918,3.073,3.38,3.7};

    private static double[] trophyHandleLeftX = new double[]{-1.737,-2.416,-2.973,-3.166,-3.256,-3.086,-2.685,-2.27,-1.737,-1.737,-1.95,-2.31,-2.703,-2.73,-2.546,-2.14,-1.737};
    private static double[] trophyHandleRightX = new double[]{1.737,2.416,2.973,3.166,3.256,3.086,2.685,2.27,1.737,1.737,1.95,2.31,2.703,2.73,2.546,2.14,1.737};
    private static double[] trophyHandleY = new double[]{0.65,0.43,-0.13,-0.62,-1.496,-2.136,-2.47,-2.59,-2.446,-1.7,-2,-2.083,-1.834,-1,-0.42,0.01,0.15};

    public static void draw(Graphics g, double scale, String winner) {
        g.drawString(winner + " wins the game!", (int) (0.75 * scale), (int) (0.5 * scale));

        g.setColor(Color.DARK_GRAY);
        g.fillPolygon(pointsToPolygon(trophyBaseX, trophyBaseY, scale));

        g.setColor(Color.YELLOW);
        g.fillPolygon(pointsToPolygon(trophyBodyX, trophyBodyY, scale));
        g.fillPolygon(pointsToPolygon(trophyHandleLeftX, trophyHandleY, scale));
        g.fillPolygon(pointsToPolygon(trophyHandleRightX, trophyHandleY, scale));
    }

    private static Polygon pointsToPolygon(double[] x, double[] y, double scale) {
        Polygon polygon = new Polygon();

        for (int i = 0; i < x.length; i++) {
            polygon.addPoint((int) ((1.15 + x[i] / 20) * scale), (int) ((0.75 + y[i] / 20) * scale));
        }

        return polygon;
    }
}
