public class LogManager {

    public static LoggerHandler buildLoggerChain() {
        LoggerHandler infoLogger = new InfoLogger(LogLevel.INFO);
        LoggerHandler errorLogger = new ErrorLogger(LogLevel.ERROR);
        LoggerHandler debugLogger = new DebugLogger(LogLevel.DEBUG);

        infoLogger.setNextLoggerHandler(errorLogger);
        errorLogger.setNextLoggerHandler(debugLogger);

        return infoLogger;
    }

    public static LogSinkSubject buildLogSinkSubject(LogLevel logLevel, SinkType type, LogSinkSubject logSinkSubject) {
        switch (type) {
            case SinkType.CONSOLE -> logSinkSubject.addObserver(logLevel, new ConsoleLogger());
            case SinkType.FILE -> logSinkSubject.addObserver(logLevel, new FileLogger());
            case SinkType.DATABASE -> logSinkSubject.addObserver(logLevel, new DataBaseLogger());
            default -> throw new IllegalArgumentException("Invalid LogSinkType");
        }
//        logSinkSubject.addObserver(LogLevel.INFO, new ConsoleLogger());
//        logSinkSubject.addObserver(LogLevel.ERROR, new FileLogger());
//        logSinkSubject.addObserver(LogLevel.DEBUG, new DataBaseLogger());

        return logSinkSubject;
    }
}
