public class ErrorLogger extends LoggerHandler{
    public ErrorLogger(LogLevel level) {
        this.logLevel = level;
    }

    public void publishLog(String message, LogSinkSubject logSinkSubject) {
        String msg = "Error: " + message;
        logSinkSubject.notifyObservers(LogLevel.ERROR, msg);
    }
}
