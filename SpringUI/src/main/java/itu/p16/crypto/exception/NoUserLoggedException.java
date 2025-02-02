package itu.p16.crypto.exception;

public class NoUserLoggedException extends Exception {
    public NoUserLoggedException() {
        super("You must log in first");
    }
}
