package com.github.jzonthemtn.opennlp;

import java.io.File;
import java.io.IOException;

public interface ModelTrainer {

    File train(final File convertedTrainingFile) throws IOException;

    void evaluate(File modelFile, File convertedEvaluationFile) throws IOException;

}
