package edu.upc.eetac.dsa.apartmentshare.auth;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by mazp on 30/11/15.
 */
public class AuthorizedResource {

    private String path;
    private List<String> methods;
    private Pattern pattern;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        pattern = Pattern.compile(path);
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setMethods(List<String> methods) {
        this.methods = methods;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
