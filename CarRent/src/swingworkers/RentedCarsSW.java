/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package swingworkers;

import cz.muni.fi.pv168.carrent.Car;
import cz.muni.fi.pv168.carrent.CarRentManager;
import cz.muni.fi.pv168.carrent.CarRentManagerImpl;
import cz.muni.fi.pv168.carrent.jframes.AppJFrame;
import java.util.Collection;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Klodye
 */
public class RentedCarsSW extends SwingWorker<DefaultTableModel, Void> {

    AppJFrame frame;
    JTable table;

    public RentedCarsSW(AppJFrame frame, JTable table) {
        this.frame = frame;
        this.table = table;
    }

    @Override
    protected DefaultTableModel doInBackground() throws Exception {
        CarRentManager crm = new CarRentManagerImpl();
        DefaultTableModel model = frame.clearTable(table);

        Collection<Car> cars = crm.findRentedCars();
        for (Car car : cars) {
            model.insertRow(0, new Object[]{car.getCarId(), car.getSpz(), car.getPriceForDayRent(), car.isReserved(), car.getType()});
        }
        return model;
    }
}
