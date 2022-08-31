package krisapps.customdeathmessages.logging;

import jdk.internal.net.http.common.Log;
import krisapps.customdeathmessages.CustomDeathMessages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    CustomDeathMessages main;
    File logFile;

    public Logger(CustomDeathMessages main){
        this.main = main;
        logFile = new File(main.getDataFolder(), "cdmlog.log");
    }

    public void log(String logMessage, LoggingLevel loggingLevel){
        switch (loggingLevel){
            case INFO:
                main.getLogger().info(logMessage);
                try {
                    appendToLogFile(logMessage, loggingLevel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case WARNING:
            case ERROR:
                main.getLogger().warning(logMessage);
                try {
                    appendToLogFile(logMessage, loggingLevel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case FATAL:
                main.getLogger().severe(logMessage);
                try {
                    appendToLogFile(logMessage, loggingLevel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    void appendToLogFile(String record, LoggingLevel level) throws IOException {
        if (main.config.getBoolean("configuration.logging.log")) {
            if (!logFile.getParentFile().exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    main.getLogger().warning("Warning! Logging is enabled, but the plugin failed to create the log file. The stack trace for the error is as follows:\n");
                    e.printStackTrace();
                }
            } else if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    main.getLogger().warning("Warning! Logging is enabled, but the plugin failed to create the log file. The stack trace for the error is as follows:\n");
                    e.printStackTrace();
                }
            }
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            FileWriter fw = new FileWriter(logFile, true);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(String.format("[(%s) CustomDeathMessages/%s]: " + record, format.format(now), level.name()));
            pw.flush();
            pw.close();
        }
    }

}
