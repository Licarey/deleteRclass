package com.example.record

import com.google.common.base.Joiner

class Utils {

    private static final Joiner PATH_JOINER = Joiner.on(File.separatorChar);

    public static String joinPath(String... paths) {
        PATH_JOINER.join(paths)
    }

    public static File joinFile(File file, String... paths) {
        return new File(file, joinPath(paths))
    }

    public static byte[] toByteArray(final InputStream input) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        final byte[] buffer = new byte[8024];
        int n = 0;
        long count = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return output.toByteArray();
    }

    public static void delFile(File file) {
        if (!file.delete()) {
            throw new RuntimeException("del file ${file} failed ");
        }
    }

    public static void renameFile(File originFile, File targetFile) {
        if (targetFile.exists()) {
            targetFile.delete()
        }
        targetFile.parentFile.mkdirs()
        if (!originFile.renameTo(targetFile)) {
            throw new RuntimeException("${originFile} rename to ${targetFile} failed ");
        }

    }
}
