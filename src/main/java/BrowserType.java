public enum BrowserType {
    CHROME,
    FIREFOX,
    EDGE;

    public static BrowserType fromString(String name) {
        if (name == null) {
            return null;
        }
        try {
            return BrowserType.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}

