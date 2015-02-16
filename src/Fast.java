import java.util.Arrays;

/**
 * This file is created by @Muffin_C on 2/10/15 20:58.
 * This file is part of Project algs4partI.
 */
public class Fast {
    private static double slope(int i, int j, Point[] points) {
        return points[i].slopeTo(points[j]);
    }

    public static void main(String[] args) {
        args = new String[] {"rs1423.txt"};
        Point[] points;
        Point[] pointsCopy;
        int quantity;

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.001);  // make the points a bit larger
        StdDraw.setPenColor(StdDraw.BLUE);

        // read in the input
        In in = new In(args[0]);
        quantity = in.readInt();
        points = new Point[quantity];
        pointsCopy = new Point[quantity];
        for (int i = 0; i < quantity; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            pointsCopy[i] = new Point(x, y);
            points[i].draw();
        }

        for (int i = 0; i < quantity; i++) {
            Arrays.sort(pointsCopy);
            Arrays.sort(pointsCopy, points[i].SLOPE_ORDER);
            double oldSlope = slope(0, 0, pointsCopy);
            int sameLinePoints = 0;

            Point origin = pointsCopy[0];

            for (int j = 1; j < quantity; j++) {
                double newSlope = slope(0, j, pointsCopy);

                if (newSlope == oldSlope) {
                    sameLinePoints++;

                    if (j == quantity - 1 && sameLinePoints >= 3 && pointsCopy[j - sameLinePoints].compareTo(origin) > 0) {
                        pointsCopy[0].drawTo(pointsCopy[j - 1]);
                        System.out.println("drew" + pointsCopy[0] + "to" + pointsCopy[j - 1]);

                        StdOut.print(pointsCopy[0]);
                        for (int shift = 0; shift < sameLinePoints; shift++) {
                            StdOut.print(" -> " + pointsCopy[j - sameLinePoints + shift]);
                        }
                        StdOut.println();
                    }

                } else {
                    if (sameLinePoints >= 3 && pointsCopy[j - sameLinePoints].compareTo(origin) > 0) {
                        pointsCopy[0].drawTo(pointsCopy[j - 1]);
                        System.out.println("drew" + pointsCopy[0] + "to" + pointsCopy[j - 1]);

                        StdOut.print(pointsCopy[0]);
                        for (int shift = 0; shift < sameLinePoints; shift++) {
                            StdOut.print(" -> " + pointsCopy[j - sameLinePoints + shift]);
                        }
                        StdOut.println();
                    }

                    oldSlope = newSlope;
                    sameLinePoints = 1;
                }
            }

//
//            for (int j = 1; j < quantity; j++) {
//                double newSlope = slope(0, j, pointsCopy);
//
//                if (pointsCopy[j].compareTo(origin) > 0 && newSlope == oldSlope) {
//                    linePoints++;
//
//                    if (j == quantity - 1 && linePoints >= 4) {
//                        Arrays.sort(pointsCopy, j - linePoints, j);
//
//                        pointsCopy[0].drawTo(pointsCopy[j - 1]);
//                        System.out.println("drew" + pointsCopy[0] + "to" + pointsCopy[j - 1]);
//
//                        StdOut.print(pointsCopy[0]);
//                        for (int shift = 0; shift < linePoints; shift++) {
//                            StdOut.print(" -> " + pointsCopy[j - linePoints + shift]);
//                        }
//                        StdOut.println();
//
//                    }
//                } else {
//
//                    if (linePoints >= 4) {
//                        Arrays.sort(pointsCopy, j - linePoints, j);
//
//                        pointsCopy[0].drawTo(pointsCopy[j - 1]);
//                        System.out.println("drew" + pointsCopy[0] + "to" + pointsCopy[j - 1]);
//
//                        StdOut.print(pointsCopy[0]);
//                        for (int shift = 0; shift < linePoints; shift++) {
//                            StdOut.print(" -> " + pointsCopy[j - linePoints + shift]);
//                        }
//                        StdOut.println();
//
//                    }
//                    oldSlope = newSlope;
//                    linePoints = 2;
//                }
//            }




//            Arrays.sort(pointsCopy, points[i].SLOPE_ORDER);
//            double oldSlope = slope(0 ,0 , pointsCopy);
//            int sameDirection = 1;
//
//            Point originPoint = pointsCopy[0];
//            Point edgeSmall = originPoint;
//            Point edgeBig = originPoint;
//
//            for (int j = 1; j < quantity; j++) {
//
//                double newSlope = slope(0, j, pointsCopy);
//
//                if (newSlope == oldSlope) {
//                    sameDirection++;
//
//                    if (pointsCopy[j].compareTo(edgeSmall) < 0) {
//                        edgeSmall = pointsCopy[j];
//                    } else if (pointsCopy[j].compareTo(edgeBig) > 0) {
//                        edgeBig = pointsCopy[j];
//                    }
//                } else {
//                    if (sameDirection >= 4) {
////                        if ((pointsCopy[j - sameDirection + 1].compareTo(originPoint) * pointsCopy[j - 1].compareTo(originPoint) > 0) ||
////                                (pointsCopy[j - sameDirection + 1].compareTo(originPoint) == 0 && pointsCopy[j - 1].compareTo(originPoint) == 0)) {
//                        if (edgeBig == originPoint) {
//                            Arrays.sort(pointsCopy, j - sameDirection, j);
//                            edgeSmall.drawTo(edgeBig);
//                            StdDraw.show(0);
//                            System.out.println("Drew" + edgeSmall + edgeBig);
//                            StdOut.print("" + originPoint);
//                            for (int foo = 0; foo < sameDirection - 1; foo++) {
//                                StdOut.print(" -> " + pointsCopy[j - sameDirection + foo]);
//                            }
//                            StdOut.println();
//                        }
//                    }
//                    edgeSmall = originPoint;
//                    edgeBig = originPoint;
//                    oldSlope = newSlope;
//                    sameDirection = 2;
//                }
//            }

            //bug here
            //Fix
//            if (sameDirection >= 4) {
//                Arrays.sort(pointsCopy, quantity - 1 - sameDirection + 1, quantity - 1);
//
////                        if ((pointsCopy[j - sameDirection + 1].compareTo(originPoint) * pointsCopy[j - 1].compareTo(originPoint) > 0) ||
////                                (pointsCopy[j - sameDirection + 1].compareTo(originPoint) == 0 && pointsCopy[j - 1].compareTo(originPoint) == 0)) {
//                if (edgeBig == originPoint || edgeSmall == originPoint) {
//                    edgeSmall.drawTo(edgeBig);
//                    StdDraw.show(0);
////                    System.out.println("Drew" + edgeSmall + edgeBig);
//                    StdOut.print("Draw out" + originPoint);
//                    for (int foo = 0; foo < sameDirection - 1; foo++) {
//                        StdOut.print(" -> " + pointsCopy[quantity - 1 - sameDirection + 1 + foo]);
//                    }
//                    StdOut.println();
//                }
//            }
        }

        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}
