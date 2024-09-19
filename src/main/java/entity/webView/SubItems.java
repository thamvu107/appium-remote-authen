package entity.webView;

import java.util.ArrayList;
import java.util.List;

public class SubItems {
    private final String menuItem;
    private final List<MenuItemDataModel> subMenuItems = new ArrayList<>();

    public SubItems(String menuItem) {
        this.menuItem = menuItem;
    }
}
