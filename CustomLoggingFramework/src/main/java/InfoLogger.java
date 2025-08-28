public class InfoLogger extends LoggerHandler{

    public InfoLogger(LogLevel level) {
        this.logLevel = level;
    }

    public void publishLog(String message, LogSinkSubject logSinkSubject) {
        String msg = "Info: " + message;
        logSinkSubject.notifyObservers(LogLevel.INFO, msg);
    }
}
