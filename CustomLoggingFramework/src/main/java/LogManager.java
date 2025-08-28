public class LogManager {

    public static LoggerHandler buildLoggerChain() {
        LoggerHandler infoLogger = new InfoLogger(LogLevel.INFO);
        LoggerHandler errorLogger = new ErrorLogger(LogLevel.ERROR);
        LoggerHandler debugLogger = new DebugLogger(LogLevel.DEBUG);

        infoLogger.setNextLoggerHandler(errorLogger);
        errorLogger.setNextLoggerHandler(debugLogger);

        return infoLogger;
    }

    public static LogSinkSubject buildLogSinkSubject() {
        LogSinkSubject logSinkSubject = new LogSinkSubject();
        logSinkSubject.addObserver(LogLevel.INFO, new ConsoleLogger());
        logSinkSubject.addObserver(LogLevel.ERROR, new FileLogger());
        logSinkSubject.addObserver(LogLevel.DEBUG, new DataBaseLogger());

        return logSinkSubject;
    }
}
