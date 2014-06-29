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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Klodye
 */
public class CarsTableSW extends SwingWorker<DefaultTableModel, Void>{
    
    AppJFrame frame;
    JTable table;

    public CarsTableSW(AppJFrame frame, JTable table) {
        this.frame = frame;
        this.table = table;
    }

    @Override
    protected DefaultTableModel doInBackground() throws Exception {
        CarManager cm = new CarManagerImpl();
        Collection<Car> cars = cm.findAllCars();
        DefaultTableModel model = frame.clearTable(table);
        for(Car car : cars) {
            model.insertRow(0, new Object[]{car.getCarId(), car.getSpz(), car.getPriceForDayRent(), car.isReserved(), car.getType()});
        }
        return model;
    }



}
