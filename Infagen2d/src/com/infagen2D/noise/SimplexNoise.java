package com.infagen2D.noise;

import java.util.Random;

import com.infagen2D.components.Ref;

public class SimplexNoise {

	SimplexNoiseOctave[] octaves;
    double[] frequencies;
    double[] amplitudes;

    public SimplexNoise(int numberOfOctaves, double persistence, int seed){
        Random random = new Random(seed);

        octaves = new SimplexNoiseOctave[numberOfOctaves];
        frequencies = new double[numberOfOctaves];
        amplitudes = new double[numberOfOctaves];

        for(int i = 0; i < numberOfOctaves; i++){
            octaves[i] = new SimplexNoiseOctave(random.nextInt());

            frequencies[i] = Math.pow(2, i);
            amplitudes[i] = Math.pow(persistence, octaves.length-i);
        }
    }

    public double getNoise(int x, int y){
       double result = 0;

        for(int i = 0; i < octaves.length; i++){
            result += octaves[i].noise(x/frequencies[i], y/frequencies[i])*amplitudes[i];
        }

        return result;
    }

}
