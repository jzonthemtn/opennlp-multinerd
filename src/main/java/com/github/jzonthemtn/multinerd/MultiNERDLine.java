package com.github.jzonthemtn.multinerd;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MultiNERDLine {

    @SerializedName("tokens")
    private List<String> tokens;

    @SerializedName("ner_tags")
    private List<Integer> nerTags;

    @SerializedName("lang")
    private String language;


    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public List<Integer> getNerTags() {
        return nerTags;
    }

    public void setNerTags(List<Integer> nerTags) {
        this.nerTags = nerTags;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
