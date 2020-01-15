package com.huaxin.cloud.tms.tray.printer;

import gnu.io.RXTXVersion;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class RXTXLoader {

    private static final Logger LOG = LoggerFactory.getLogger(RXTXLoader.class);

    public static void load() throws IOException {
        RXTXLoader.load(OperatingSystem.get(), Architecture.get());
    }

    public static void load(final OperatingSystem os, final Architecture arch) throws IOException {
        final File tempDir = createTempDirectory();
        final InputStream source = openResource(os, arch);
        if (source == null) {
            throw new IllegalStateException(String.format("Unable to find resource for %s %s", arch, os));
        }

        try {
            final File target = new File(tempDir, os.getLibPath());
            LOG.debug("Loading RXTX library for {} {}", arch, os);

            FileUtils.copyInputStreamToFile(source, target);
            SystemLoadPath.getInstance().add(tempDir.getAbsolutePath());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } finally {
            source.close();
        }

        final String version = RXTXVersion.nativeGetVersion();
        LOG.info("Loaded RXTX native library {} for {} {}", version, arch, os);
    }

    protected static InputStream openResource(final OperatingSystem os, final Architecture arch) throws IOException {
        final String path = String.format("%s/%s/%s", os.getName(), arch.getName(), os.getLibPath());
        LOG.trace("Loading native library from {}", path);
        System.out.println(RXTXLoader.class.getResourceAsStream("/").toString());
        return RXTXLoader.class.getResourceAsStream(path);
    }

    protected static File createTempDirectory() {
        final File tempDir = new File(FileUtils.getTempDirectory(), "rxtx-loader");
        if (!tempDir.exists()) {
            tempDir.mkdir();
        }

        return tempDir;
    }
}
