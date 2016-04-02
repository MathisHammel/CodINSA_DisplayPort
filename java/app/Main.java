package app;

import io.SocketManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        new SocketManager("127.0.0.1", 8080);
    }
}
