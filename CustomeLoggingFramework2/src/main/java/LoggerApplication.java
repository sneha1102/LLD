public class LoggerApplication {
    public static void main(String[] args) {
        Logger logger = Logger.getLoggerInstance();
        logger.addLogSinkSubject(LogLevel.INFO, SinkType.FILE);
        logger.addLogSinkSubject(LogLevel.INFO, SinkType.DATABASE);
        logger.addLogSinkSubject(LogLevel.INFO, SinkType.CONSOLE);
        logger.addLogSinkSubject(LogLevel.DEBUG, SinkType.CONSOLE);
        logger.addLogSinkSubject(LogLevel.DEBUG, SinkType.FILE);
        logger.addLogSinkSubject(LogLevel.ERROR, SinkType.CONSOLE);
        // check that no duplicate observer should be added again
        logger.addLogSinkSubject(LogLevel.INFO, SinkType.FILE);
        logger.addLogSinkSubject(LogLevel.INFO, SinkType.DATABASE);
        logger.addLogSinkSubject(LogLevel.INFO, SinkType.CONSOLE);
        logger.addLogSinkSubject(LogLevel.DEBUG, SinkType.CONSOLE);
        logger.addLogSinkSubject(LogLevel.DEBUG, SinkType.FILE);
        logger.addLogSinkSubject(LogLevel.ERROR, SinkType.CONSOLE);
        logger.info("This is info");
        logger.error("This is error");
        logger.debug("This is debug");
    }
}
