public class Logger {
    private volatile static Logger loggerInstance;
    private volatile static LoggerHandler loggerHandler;
    private volatile static LogSinkSubject logSinkSubject;

    private Logger() {}

    public static Logger getLoggerInstance() {
        if(loggerInstance == null) {
            synchronized (Logger.class) {
                if(loggerInstance == null) {
                    loggerInstance = new Logger();
                    logSinkSubject = new LogSinkSubject();
                    loggerHandler = LogManager.buildLoggerChain();
                }
            }
        }
        return loggerInstance;
    }

    public void addLogSinkSubject(LogLevel logLevel, SinkType sinkType) {
        logSinkSubject = LogManager.buildLogSinkSubject(logLevel, sinkType, logSinkSubject);
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
