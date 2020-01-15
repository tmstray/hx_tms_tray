package com.huaxin.cloud.tms.tray.printer;


import com.huaxin.cloud.tms.tray.printer.exception.UnsupportedOperatingSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum OperatingSystem {
    WINDOWS("Windows", "rxtxSerial.dll"),
    LINUX("Linux", "librxtxSerial.so"),
    MAC_OSX("Mac OS X", "librxtxSerial.jnilib");

    private static final Logger LOG = LoggerFactory.getLogger(OperatingSystem.class);
    public static final String OS_NAME_SYSTEM_PROPERTY = "os.name";

    public static OperatingSystem get() {
        final String name = System.getProperty(OS_NAME_SYSTEM_PROPERTY);
        return fromString(name);
    }

    public static OperatingSystem fromString(final String name) {
        if (name != null) {
            for (final OperatingSystem os : OperatingSystem.values()) {
                if (name.toLowerCase().contains(os.key.toLowerCase())) {
                    LOG.debug("Detected OS as {} ({})", os, name);
                    return os;
                }
            }
        }

        throw new UnsupportedOperatingSystemException(name);
    }

    private final String key;
    private final String libPath;

    private OperatingSystem(final String key, final String libPath) {
        this.key = key;
        this.libPath = libPath;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public String getLibPath() {
        return libPath;
    }
}
