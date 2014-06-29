/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package swingworkers;

import cz.muni.fi.pv168.carrent.CarRentManager;
import cz.muni.fi.pv168.carrent.CarRentManagerImpl;
import cz.muni.fi.pv168.carrent.Rent;
import cz.muni.fi.pv168.carrent.jframes.AppJFrame;
import java.util.Collection;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Klodye
 */
public class SearchRentsSW extends SwingWorker<DefaultTableModel, Void>{

    AppJFrame frame;
    JTable table;
    String str;

    public SearchRentsSW(AppJFrame frame, JTable table, String str) {
        this.frame = frame;
        this.table = table;
        this.str = str;
    }

    @Override
    protected DefaultTableModel doInBackground() throws Exception {
        CarRentManager crm = new CarRentManagerImpl();
        Collection<Rent> rents = crm.findRentsByString(str);
        DefaultTableModel model = frame.clearTable(table);
        for (Rent rent : rents) {
            model.insertRow(0, new Object[]{rent.getRentId(), rent.getCarId(), rent.getPersonalId(), rent.getDate(), rent.getForDays(), rent.getPrice()});
        }
        return model;
    }

}
