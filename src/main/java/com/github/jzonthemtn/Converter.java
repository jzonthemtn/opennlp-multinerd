package com.github.jzonthemtn;

import java.io.File;
import java.io.IOException;

public interface Converter {

    File convert(String filename, String outputFileName) throws IOException;

}
