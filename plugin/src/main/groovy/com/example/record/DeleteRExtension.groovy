package com.example.record;

public class DeleteRExtension {

    public boolean deleteRclass = true
    public boolean deleteRclassDebug = true
    public int logLevel = 2


    @Override
    public String toString() {
        String str =
                """
                deleteRclass: ${deleteRclass}
                deleteRclassDebug: ${deleteRclassDebug}
                logLevel: ${logLevel}
                """
        return str
    }
}
