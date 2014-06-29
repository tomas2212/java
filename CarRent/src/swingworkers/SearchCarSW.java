/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package swingworkers;

import cz.muni.fi.pv168.carrent.Car;
import cz.muni.fi.pv168.carrent.CarManager;
import cz.muni.fi.pv168.carrent.CarManagerImpl;
import cz.muni.fi.pv168.carrent.jframes.AppJFrame;
import java.util.Collection;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Klodye
 */
public class SearchCarSW extends SwingWorker<DefaultTableModel, Void>{

    AppJFrame frame;
    JTable table;
    String str;

    public SearchCarSW(AppJFrame frame, JTable table, String str) {
        this.frame = frame;
        this.table = table;
        this.str = str;
    }

    @Override
    protected DefaultTableModel doInBackground() throws Exception {
        CarManager cm = new CarManagerImpl();
        Collection<Car> cars = cm.findCarsByString(str);
        DefaultTableModel model = frame.clearTable(table);
        for (Car car : cars) {
            model.insertRow(0, new Object[]{car.getCarId(), car.getSpz(), car.getPriceForDayRent(), car.isReserved(), car.getType()});
        }
        return model;
    }

}
