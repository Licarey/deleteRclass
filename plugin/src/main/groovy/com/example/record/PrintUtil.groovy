package com.example.record

/**
 * Created by wangzhi on 16/4/6.
 */
class PrintUtil {
    /*
        1 <-> only warn
        2 <-> ${1} and info
        3 <-> ${2} and verbose

     */
    public static int logLevel = 2
    static IPrintCore printCore = new IPrintCore() {

        @Override
        void info(Object arg) {
            println(arg)
        }

        @Override
        void warn(Object arg) {
            System.err.println(arg)
        }
    }
    public interface IPrintCore {
        public void info(Object arg)

        public void warn(Object arg)
    }

    public static void warn(Object arg) {
        if (logLevel >= 1) {
            printCore.warn(arg)
        }
    }

    public static void info(Object arg) {
        if (logLevel >= 2) {
            printCore.info(arg)
        }
    }

    public static void verbose(Object arg) {
        if (logLevel >= 3) {
            printCore.info(arg)
        }
    }
}
