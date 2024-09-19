package enums;

import lombok.Getter;

public enum Contexts {
    NATIVE("NATIVE_APP"),
    WEBVIEW("WEBVIEW_com.wdiodemoapp");

    @Getter
    private final String contextName;

    Contexts(String contextName) {
        this.contextName = contextName;
    }
}
