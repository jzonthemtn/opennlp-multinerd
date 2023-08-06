package com.github.jzonthemtn.opennlp;

import opennlp.tools.namefind.*;
import opennlp.tools.util.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class TrainTokenNameFinder implements ModelTrainer {

    @Override
    public File train(final File convertedTrainingFile) throws IOException {

        final InputStreamFactory in = new MarkableFileInputStreamFactory(convertedTrainingFile);

        final ObjectStream<NameSample> sampleStream = new NameSampleDataStream(new PlainTextByLineStream(in, StandardCharsets.UTF_8));

        final TrainingParameters params = new TrainingParameters();
        params.put(TrainingParameters.ITERATIONS_PARAM, 5);
        params.put(TrainingParameters.CUTOFF_PARAM, 1);

        final TokenNameFinderModel nameFinderModel = NameFinderME.train("en", null, sampleStream,
                params, TokenNameFinderFactory.create(null, null, Collections.emptyMap(), new BioCodec()));

        final File output = new File("/tmp/ner-custom-model.bin");

        final FileOutputStream outputStream = new FileOutputStream(output);
        nameFinderModel.serialize(outputStream);

        return output;

    }

    @Override
    public void evaluate(final File modelFile, final File convertedEvaluationFile) throws IOException {

//        InputStreamFactory in = new MarkableFileInputStreamFactory(convertedEvaluationFile);
//        OutputStream stream = new ByteArrayOutputStream();
//        TokenNameFinderEvaluationMonitor listener = new NameEvaluationErrorListener(stream);
//
//        Span[] pred = createSimpleNameSampleB().getNames();
//        TokenNameFinderEvaluator eval = new TokenNameFinderEvaluator(new DummyNameFinder(pred), listener);
//        eval.evaluateSample(createSimpleNameSampleA());

    }

}
