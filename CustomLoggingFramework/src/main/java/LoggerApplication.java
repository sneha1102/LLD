public class LoggerApplication {
    public static void main(String[] args) {
        Logger logger = Logger.getLoggerInstance();

        logger.info("This is info");
        logger.error("This is error");
        logger.debug("This is debug");
    }
}
