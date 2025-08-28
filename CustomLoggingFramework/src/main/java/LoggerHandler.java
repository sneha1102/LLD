public abstract class LoggerHandler {
    private LoggerHandler nextLoggerHandler;
    public LogLevel logLevel;

    public void setNextLoggerHandler(LoggerHandler nextLoggerHandler) {
        this.nextLoggerHandler = nextLoggerHandler;
    }

    public void log(LogLevel level, String message, LogSinkSubject logSinkSubject) {
        if (this.logLevel.getLevel() == level.getLevel()) {
            publishLog(message, logSinkSubject);
        }

        if (nextLoggerHandler != null) {
            nextLoggerHandler.log(level, message, logSinkSubject);
        }
    }

    public abstract void publishLog(String message, LogSinkSubject logSinkSubject);
}
