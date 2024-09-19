package enums;

public enum ServerType {
    LOCAL,
    REMOTE;

    public static ServerType fromString(String serverType) {
        try {
            return ServerType.valueOf(serverType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown server type: " + serverType, e);
        }
    }
}
