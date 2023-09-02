package com.github.jzonthemtn.opennlp;

import opennlp.tools.namefind.TokenNameFinderModel;

import java.io.File;
import java.io.IOException;

public interface ModelTrainer {

    TokenNameFinderModel train(final File convertedTrainingFile) throws IOException;

    void evaluate(File modelFile, File convertedEvaluationFile) throws IOException;

}
