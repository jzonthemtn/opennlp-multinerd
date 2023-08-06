package com.github.jzonthemtn;

import com.github.jzonthemtn.multinerd.MultiNERD;
import com.github.jzonthemtn.opennlp.TrainTokenNameFinder;

import java.io.File;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        //final Converter converter = new MultiNERD();
        //final File convertedTrainingFile = converter.convert("./data/multinerd/val_en.jsonl", "./data/multinerd/converted/val_en.opennlp");

        System.out.println("Beginning model training...");
        final File convertedTrainingFile = new File("./data/multinerd/converted/train_en.opennlp");
        final TrainTokenNameFinder trainTokenNameFinder = new TrainTokenNameFinder();
        final File modelFile = trainTokenNameFinder.train(convertedTrainingFile);

    }

}
