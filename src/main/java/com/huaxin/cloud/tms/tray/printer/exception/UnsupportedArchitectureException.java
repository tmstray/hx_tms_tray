package com.huaxin.cloud.tms.tray.printer.exception;

public class UnsupportedArchitectureException extends RuntimeException {

    private final String architecture;

    public UnsupportedArchitectureException(final String architecture) {
        super("Unsupported architecture: " + architecture);

        this.architecture = architecture;
    }

    public String getArchitecture() {
        return architecture;
    }
}
