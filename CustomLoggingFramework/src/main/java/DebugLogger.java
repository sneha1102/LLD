public class DebugLogger extends LoggerHandler{
    public DebugLogger(LogLevel level) {
        this.logLevel = level;
    }

    public void publishLog(String message, LogSinkSubject logSinkSubject) {
        String msg = "Debug: " + message;
        logSinkSubject.notifyObservers(LogLevel.DEBUG, msg);
    }
}
