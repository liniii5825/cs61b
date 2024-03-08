/** 
 * @author lin <liniii@foxmail.com>
 * @version 1.2
 * since @version 1.1
 */

import java.lang.management.RuntimeMXBean;

/**
 * Create a class of a Planet containing its propeties
 * @param xxPos Its current x position
 * @param yyPos Its current y position
 * @param xxVel Its current velocity in the x direction
 * @param yyVel Its current velocity in the y direction
 * @param mass  Its mass
 * @param imgFileName The name of the file that corresponds to the image that depicts the planet
 * @param G The gravitational constant
 */

public class Planet {
    public double xxPos; 
    public double yyPos; 
    public double xxVel; 
    public double yyVel; 
    public double mass; 
    public String imgFileName; 

    public double G = 6.67e-11;

    /** The constructor of distinct datas of a Planet object */
    public Planet(double xP, double yP, double xV, 
                    double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /** The constructor of a copy of a identical Planet object */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        double R = Math.pow(dx, 2) + Math.pow(dy, 2);
        return Math.sqrt(R);
    }

    public double calcForceExertedBy(Planet p) {
        return (G * this.mass * p.mass) / Math.pow(calcDistance(p), 2); 
    }


    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double totalForce = 0;
        for (Planet planet : allPlanets) {
            if (this.equals(planet)) {
                continue;
            } else {
                totalForce += calcForceExertedByX(planet);
            }
        }
        return totalForce;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double totalForce = 0;
        for (Planet planet : allPlanets) {
            if (this.equals(planet)) {
                continue;
            } else {
                totalForce += calcForceExertedByY(planet);
            }
        }
        return totalForce;
    }

    public void update(double dt, double Fx, double Fy) {
        double accelerationX = Fx / this.mass;
        double accelerationY = Fy / this.mass;
        this.xxVel = this.xxVel + dt * accelerationX;
        this.yyVel = this.yyVel + dt * accelerationY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }
    
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
} 