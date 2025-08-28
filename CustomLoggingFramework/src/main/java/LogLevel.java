public enum LogLevel {
    INFO(1), DEBUG(3), WARN(3), ERROR(2);

    private final int level;

    LogLevel (int value) {
        this.level = value;
    }

    public int getLevel() {
        return level;
    }
}

