package cz.muni.fi.pa165.vozovypark.web.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author andrej
 */
public class Menu {

    List<MenuItem> menuItems;

    public Menu() {
        menuItems = new ArrayList<MenuItem>();
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setActiveItemByUrl(String url) {
        for (MenuItem item : menuItems) {
            if (item.getUrl().equals(url)) {
                item.setActive(true);
            } else {
                item.setActive(false);
            }
        }
    }

    public void setActiveItemByName(String name) {
        for (MenuItem item : menuItems) {
            if (item.getName().equals(name)) {
                item.setActive(true);
            } else {
                item.setActive(false);
            }
        }
    }
}