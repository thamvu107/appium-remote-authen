package entity.webView;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Getter
@RequiredArgsConstructor
public class MenuItemDataModel {
    private final String name;
    private final String href;
    private final String ariaLabel;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MenuItemDataModel that = (MenuItemDataModel) obj;
        return Objects.equals(name, that.name) && Objects.equals(href, that.href) && Objects.equals(ariaLabel, that.ariaLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, href, ariaLabel);
    }

    @Override
    public String toString() {
        return "MenuItemDataModel{" +
                "name='" + name + '\'' +
                ", href='" + href + '\'' +
                ", ariaLabel='" + ariaLabel + '\'' +
                '}';
    }
}


