package com.github.jzonthemtn.multinerd;

import com.github.jzonthemtn.Converter;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Converter for the MultiNERD dataset at https://huggingface.co/datasets/Babelscape/multinerd
 * Converts to Apache OpenNLP TokenNameFinder format.
 */
public class MultiNERD implements Converter {

    private final Gson gson;

    public MultiNERD() {
        this.gson = new Gson();
    }

    @Override
    public File convert(final String filename, final String outputFileName) throws IOException {

        final File file = new File(filename);

        final List<String> lines = FileUtils.readLines(file, Charset.defaultCharset());

        final File outputFile = new File(outputFileName);
        outputFile.delete();

        for(final String line : lines) {

            final MultiNERDLine multiNERDLine = gson.fromJson(line, MultiNERDLine.class);

            final StringBuilder sb = new StringBuilder();

            int index = 0;
            boolean insidePerson = false;

            for(final String token : multiNERDLine.getTokens()) {

                final int classification = multiNERDLine.getNerTags().get(index);

                if(classification == 1) {
                    // This is a start of a person.

                    if(insidePerson == false) {
                        sb.append(" <START:person> " + token);
                    } else {
                        sb.append(" <END> <START:person> " + token);
                    }

                    insidePerson = true;

                } else if(classification == 2) {
                    // This is a continuation or end of a person.
                    insidePerson = true;
                    sb.append(" " + token);

                } else {
                    // If we were inside a person, now we are not, so end the person.
                    if(insidePerson == true) {
                        sb.append(" <END> " + token);
                    } else {
                        if(index > 0) {
                            sb.append(" " + token);
                        } else {
                            sb.append(token);
                        }
                    }
                    insidePerson = false;
                }

                index++;

            }

            sb.append("\n");

            System.out.print(sb.toString());
            FileUtils.writeStringToFile(outputFile, sb.toString(), Charset.defaultCharset(), true);

        }

    return outputFile;

    }

}
