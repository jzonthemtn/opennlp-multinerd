package com.github.jzonthemtn;

import com.github.jzonthemtn.multinerd.MultiNERD;
import com.github.jzonthemtn.opennlp.TrainTokenNameFinder;
import opennlp.tools.namefind.*;
import opennlp.tools.util.InputStreamFactory;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.eval.FMeasure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class App {

    public static void main(String[] args) throws IOException {

        // Convert
        final Converter converter = new MultiNERD();
        final File convertedTrainFile = converter.convert("./data/multinerd/train_en.jsonl", "./data/multinerd/converted/train_en.opennlp");
        final File convertedTestFile = converter.convert("./data/multinerd/test_en.jsonl", "./data/multinerd/converted/test_en.opennlp");
        final File convertedValFile = converter.convert("./data/multinerd/val_en.jsonl", "./data/multinerd/converted/val_en.opennlp");

        // Train
        System.out.println("Beginning model training...");
        //final File convertedTrainFile = new File("./data/multinerd/converted/train_en.opennlp");
        final TrainTokenNameFinder trainTokenNameFinder = new TrainTokenNameFinder();
        final TokenNameFinderModel model = trainTokenNameFinder.train(convertedTrainFile);

        final File output = new File("./models/ner-multinerd.bin");
        final FileOutputStream outputStream = new FileOutputStream(output);
        model.serialize(outputStream);
        System.out.println("Model saved to " + output.getAbsolutePath());

        // Test
        final InputStreamFactory in = new MarkableFileInputStreamFactory(convertedTestFile);
        final ObjectStream<NameSample> sampleStream = new NameSampleDataStream(new PlainTextByLineStream(in, StandardCharsets.UTF_8));
        final TokenNameFinderEvaluator evaluator = new TokenNameFinderEvaluator(new NameFinderME(model));
        evaluator.evaluate(sampleStream);

        final FMeasure result = evaluator.getFMeasure();
        System.out.println(result.toString());


    }

}
