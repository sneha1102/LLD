public class Logger {
    private final static Logger loggerInstance = new Logger();
    private final static LoggerHandler loggerHandler = LogManager.buildLoggerChain();
    private final static LogSinkSubject logSinkSubject = LogManager.buildLogSinkSubject();

    private Logger() {}

    public static Logger getLoggerInstance() {
        return loggerInstance;
    }

    private void logMessage(String message, LogLevel level) {
        loggerHandler.log(level, message, logSinkSubject);
    }

    public void info(String message) {
        logMessage(message, LogLevel.INFO);
    }

    public void error(String message) {
        logMessage(message, LogLevel.ERROR);
    }

    public void debug(String message) {
        logMessage(message, LogLevel.DEBUG);
    }
}
