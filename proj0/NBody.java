public class NBody {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please type in 3 arguments");
            return;
        }
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String fileName = args[2];
        double Radius = readRadius(fileName);
        Planet[] planet = readPlanets(fileName);

        StdDraw.setXscale(-Radius, Radius);
        StdDraw.setYscale(-Radius, Radius);
        StdDraw.enableDoubleBuffering();
        int PlanetNumbers = planet.length;
        for (int t = 0; t < T; t += dt) {
            double[] xForce = new double[PlanetNumbers];
            double[] yForce = new double[PlanetNumbers];
            for (int i = 0; i < PlanetNumbers; i++) {
                xForce[i] = planet[i].calcNetForceExertedByX(planet);
                yForce[i] = planet[i].calcNetForceExertedByY(planet);
                planet[i].update(dt, xForce[i], yForce[i]);
            }
            
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planet) {
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        
        In in = new In(fileName);
        StdOut.println(in.readInt());
        StdOut.println(Radius);
        for (Planet p : planet) {
            StdOut.printf("%-14.4e", p.xxPos);
            StdOut.printf("%-14.4e", p.yyPos);
            StdOut.printf("%-14.4e", p.xxVel);
            StdOut.printf("%-14.4e", p.yyVel);
            StdOut.printf("%-14.4e", p.mass);
            StdOut.printf("%-14s", p.imgFileName);
            StdOut.println();
        }
    }

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numbersOfPlanet = in.readInt();
        double Radius = in.readDouble();
        Planet[] p = new Planet[numbersOfPlanet];
        for (int i = 0; i < numbersOfPlanet; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();

            p[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return p;
        /*  
         *  p[i].xxPos = in.readDouble();
         *  p[i].yyPos = in.readDouble();
         *  p[i].xxVel = in.readDouble();
         *  p[i].yyVel = in.readDouble();
         *  p[i].mass = in.readDouble();
         *  p[i].imgFileName = in.readString();
         *  Can't do this, since the array was not assigned, and p[i]. points to NULL
         */ 
    }
}