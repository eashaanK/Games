package com.infagen.noise;

import java.awt.Point;
import java.util.Random;

public class SimplexNoiseOctave {
	
	int numberOfSwaps = 400;
	
	final double F2 = 0.5 * (Math.sqrt(3.0) - 1.0);
	final double G2 = (3.0 - Math.sqrt(3.0)) / 6.0;
	
	private Point[] points = {new Point(1, 1), new Point(-1, 1), new Point(1, -1), new Point(-1, -1)}; //add z coor for 3D noise

	private static short[] p_supply = {28,179,17,202,114,79,61,70,98,85,187,73,217,16,111,90,34,100,249,19,75,
		212,199,82,190,214,155,27,157,20,227,198,48,230,181,89,121,200,216,39,242,229,36,10,251,135,12,49,55,
		223,117,218,138,139,45,136,54,141,134,102,120,195,137,53,118,23,44,24,176,80,211,92,222,69,33,153,
		143,65,221,146,5,188,163,25,172,115,109,26,248,149,159,207,145,239,11,191,203,219,29,94,113,189,166,
		209,183,63,158,196,59,13,96,81,250,125,22,18,31,127,142,177,255,1,57,210,119,106,126,99,131,46,244,
		245,228,235,103,215,62,3,150,151,93,206,64,208,238,152,243,47,21,68,105,175,4,104,147,50,42,169,123,
		233,182,184,231,108,83,0,41,167,154,58,205,171,8,37,140,91,234,162,110,97,236,116,9,197,161,72,186,
		7,101,252,165,168,174,237,194,220,156,180,240,254,124,201,232,148,77,86,15,164,88,52,213,193,253,
		246,67,112,74,144,32,56,35,38,95,76,224,71,160,129,132,2,51,84,14,170,226,128,87,43,60,192,40,241,
		204,133,130,78,122,107,30,225,185,173,6,178,66,247};
	
	private short[] p;
	
	private short[] perm = new short[512];
	private short[] permMod12 = new short[512];

	
	public SimplexNoiseOctave(int seed) {
		p = p_supply.clone();
		
		Random random = new Random(seed);

		for(int i = 0; i < numberOfSwaps; i++){
			int from = random.nextInt(p.length);
			int to = random.nextInt(p.length);
			
			short tempP = p[from];
			p[from] = p[to];
			p[to] = tempP;
		}
		
		// 8 bit system
		// 0000 0101 (5)
		// 0000 1011 (11)
		// 0000 0001 (1) 
		// 5 & 11The & looks for 1 in their other forms and passes it through
		
		for(int i = 0; i < perm.length; i++){
			perm[i] = p[i & 255]; //whenever i is larger than 255, sets back down
			permMod12[i] = (short)(perm[i] % 4);
		}
		
	}
	private int fastFloor(double x){
		int xi = (int)x;
		return x<xi ? xi-1 : xi;
	}
	
	private double dot(Point point, double x, double y){
		return point.x * x + point.y * y;
	}
	
	
	/**2D simplex noise
	 * 
	 * @param xin
	 * @param yin
	 * @return
	 */
	public double noise(double xin, double yin){
        double s = (xin+yin)*F2;
        int i = fastFloor(xin + s);
        int j = fastFloor(yin+s);
        double t = (i+j)*G2;
        double X0 = i-t;
        double Y0 = j-t;
        double x0 = xin-X0;
        double y0 = yin-Y0;

        int i1, j1;
        if(x0>y0){
            i1 = 1;
            j1 = 0;
        }else{
            i1 = 0;
            j1 = 1;
        }
        double x1 = x0 - i1 + G2;
        double y1 = y0 - j1 + G2;
        double x2 = x0 - 1.0 + 2.0*G2;
        double y2 = y0 - 1.0 + 2.0*G2;

        int ii = i & 255;
        int jj = j & 255;

        int gi0 = permMod12[ii+perm[jj]];
        int gi1 = permMod12[ii+i1+perm[jj+j1]];
        int gi2 = permMod12[ii+1+perm[jj+1]];

        double n0, n1, n2;
        double t0 = 0.5 - x0*x0-y0*y0;
        if(t0 < 0)
            n0 = 0;
        else{
            t0 *= t0;
            n0 = t0 * t0 * dot(points[gi0], x0, y0);
        }

        double t1 = 0.5 - x1*x1-y1*y1;
        if(t1 < 0)
            n1 = 0;
        else {
            t1 *= t1;
            n1 = t1 * t1 * dot(points[gi1], x1, y1);
        }

        double t2 = 0.5 - x2*x2-y2*y2;
        if(t2 < 0)
            n2 = 0;
        else {
            t2 *= t2;
            n2 = t2 * t2 * dot(points[gi2], x2, y2);
        }

        return 70*(n0 + n1 + n2);
    }

	
}
