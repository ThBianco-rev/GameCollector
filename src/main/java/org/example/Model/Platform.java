package org.example.Model;
public enum Platform{
    MOBILE("Mobile"),
    SWITCH("Nintendo Switch"),
    PC("PC"),
    PS4("PlayStation 4"),
    PS5("PlayStation 5"),
    XBONE("XBOX ONE"),
    UNK("Unknown");

    private String value;
    Platform(String s) {
        this.value = s;
    }
    @Override
    public String toString() {
        return value;
    }
}

