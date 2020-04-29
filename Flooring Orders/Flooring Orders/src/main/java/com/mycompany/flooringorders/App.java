/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringorders;

import com.mycompany.flooringorders.controller.FlooringController;
import com.mycompany.flooringorders.dao.OrderDaoFile;
import com.mycompany.flooringorders.dto.Order;
import com.mycompany.flooringorders.service.FlooringService;
import com.mycompany.flooringorders.ui.ConsoleIo;
import com.mycompany.flooringorders.ui.UserIo;
import com.mycompany.flooringorders.ui.FlooringView;
import com.mycompany.flooringorders.dao.OrderDao;
import com.mycompany.flooringorders.dao.OrderDaoException;
import com.mycompany.flooringorders.dao.ProductsDao;
import com.mycompany.flooringorders.dao.ProductsDaoFile;
import com.mycompany.flooringorders.dao.TaxesDao;
import com.mycompany.flooringorders.dao.TaxesDaoFile;
import java.io.IOException;


/**
 *
 * @author ursul
 */
public class App {
    public static void main(String[] args) throws OrderDaoException, IOException {
        
        OrderDao dao = new OrderDaoFile("Folders/Orders");
        ProductsDao dao1 = new ProductsDaoFile("Folders/Data/Products.txt");
        TaxesDao dao2 = new TaxesDaoFile("Folders/Data/Taxes.txt");
        
        FlooringService service = new FlooringService(dao, dao1, dao2);

        UserIo io = new ConsoleIo();
        FlooringView view = new FlooringView(io);
        FlooringController toRun = new FlooringController(service, view);
        toRun.run();
        
    }
}
