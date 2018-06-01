package com.example.record;

public class DeleteRExtension {

    public boolean noDeleteRclass = true
    public boolean noDeleteRclassDebug = true
    public int logLevel = 2


    @Override
    public String toString() {
        String str =
                """
                noDeleteRclass: ${noDeleteRclass}
                noDeleteRclassDebug: ${noDeleteRclassDebug}
                logLevel: ${logLevel}
                """
        return str
    }
}
