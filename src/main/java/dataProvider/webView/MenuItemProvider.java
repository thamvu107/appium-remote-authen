package dataProvider.webView;

import constants.filePaths.jsonFiles.WebViewDataPathConstants;
import entity.webView.MenuItemDataModel;
import org.testng.annotations.DataProvider;
import utils.DataObjectBuilderUtil;

import java.nio.file.Path;
import java.util.Arrays;

public class MenuItemProvider {

    @DataProvider(name = "menuItem")
    public MenuItemDataModel[] menuItem() {

        Path menuItemDataPath = Path.of(WebViewDataPathConstants.MENU_ITEM_JSON);

        return DataObjectBuilderUtil.buildDataObject(menuItemDataPath, MenuItemDataModel[].class);
    }


    @DataProvider(name = "menuItems")
    public MenuItemDataModel[] menuItems() {
        Path menuItemDataPath = Path.of(WebViewDataPathConstants.MENU_ITEM_JSON);

        return DataObjectBuilderUtil.buildDataObject(menuItemDataPath, MenuItemDataModel[].class);
    }


    @DataProvider(name = "menuItemList")
    public Object[][] menuItemList() {
        Path menuItemDataPath = Path.of(WebViewDataPathConstants.MENU_ITEM_JSON);

        MenuItemDataModel[] menuItemArray = DataObjectBuilderUtil.buildDataObject(menuItemDataPath, MenuItemDataModel[].class);

        Object[][] data = new Object[1][1];
        data[0][0] = Arrays.asList(menuItemArray);

        return data;
    }
}
