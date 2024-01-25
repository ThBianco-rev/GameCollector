package org.example.Model;

public enum CompletionStatus{
    Complete("Complete"),
    Incomplete("Not Played"),
    Playing("Playing");
    private String value;
    CompletionStatus(String s) {
        this.value = s;
    }
    @Override
    public String toString() {
        return value;
    }
}
