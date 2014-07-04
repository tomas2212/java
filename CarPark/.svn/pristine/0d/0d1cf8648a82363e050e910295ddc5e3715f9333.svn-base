package cz.muni.fi.pa165.vozovypark.web;

import cz.muni.fi.pa165.vozovypark.web.menu.Menu;
import cz.muni.fi.pa165.vozovypark.web.menu.MenuItem;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author andrej
 */
public abstract class LayoutPage implements ActionBean {
    
   @SpringBean(value="mainMenu")
   protected Menu mainMenu;
   
    public Menu getMainMenu(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();         
      
        Menu authorisedMenu = new Menu();
        for(MenuItem item : mainMenu.getMenuItems()){
            if(item.getUrl().equals("/carPark")){
                for(GrantedAuthority auth : authentication.getAuthorities()){
                    if(auth.getAuthority().equals("carAdmin")){
                        authorisedMenu.addMenuItem(item);
                    }
                }
            }
            else if(item.getUrl().equals("/company")){
                for(GrantedAuthority auth : authentication.getAuthorities()){
                    if(auth.getAuthority().equals("manager") || auth.getAuthority().equals("sysAdmin")){
                        authorisedMenu.addMenuItem(item);
                        break;
                    }
                }                
            }
            else{
                authorisedMenu.addMenuItem(item);
            }
        }
        return authorisedMenu;
        
    }
    
    public void setMainMenu(Menu mainMenu){
        this.mainMenu = mainMenu;
    };
    
    public abstract Menu getSubMenu();
    
    public abstract void setSubMenu(Menu subMenu);  
}