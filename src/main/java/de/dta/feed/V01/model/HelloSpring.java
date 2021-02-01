package de.dta.feed.V01.model;

public class HelloSpring {

    private String message;

    public HelloSpring(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("Spring is started [message=%s]", message);
    }
}
