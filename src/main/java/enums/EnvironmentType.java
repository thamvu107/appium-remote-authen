package enums;

public enum EnvironmentType {
    DEV,
    QA,
    PROD;

    public static EnvironmentType fromString(String EnvironmentName) {
        try {
            return EnvironmentType.valueOf(EnvironmentName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown Environment: " + EnvironmentName, e);
        }
    }
}
