package kz.javalab.va.service.admin;

import kz.javalab.va.dao.impl.OrderDetailsDao;
import kz.javalab.va.entity.OrderDetails;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan
public class AdminOrderDetService extends AdminService<OrderDetails> {
    private final static Logger logger = Logger.getRootLogger();
    @Autowired
    private OrderDetailsDao detailsDao;

    public AdminOrderDetService(OrderDetailsDao detailsDao) {
        this.detailsDao = detailsDao;
    }

    public AdminOrderDetService() {
    }

    public List<OrderDetails> getAllDetails() {
        return getList(detailsDao);
    }
}
