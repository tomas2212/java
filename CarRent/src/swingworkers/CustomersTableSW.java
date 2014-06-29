/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package swingworkers;

import cz.muni.fi.pv168.carrent.Customer;
import cz.muni.fi.pv168.carrent.CustomerManager;
import cz.muni.fi.pv168.carrent.CustomerManagerImpl;
import cz.muni.fi.pv168.carrent.jframes.AppJFrame;
import java.util.Collection;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Klodye
 */
public class CustomersTableSW extends SwingWorker<DefaultTableModel, Void>{

    AppJFrame frame;
    JTable table;

    public CustomersTableSW(AppJFrame frame, JTable table) {
        this.frame = frame;
        this.table = table;
    }

    @Override
    protected DefaultTableModel doInBackground() throws Exception {
        CustomerManager cstm = new CustomerManagerImpl();
        Collection<Customer> customers = cstm.findAllCustomers();
        DefaultTableModel model = frame.clearTable(table);
        for (Customer customer : customers) {
            model.insertRow(0, new Object[]{customer.getPersonalId(), customer.getFirstName(), customer.getSurname(), customer.getStreet(), customer.getHouseNo(), customer.getPsc(), customer.getCity(), customer.getCountry(), customer.getPhoneNo()});
        }
        return model;
    }

}
