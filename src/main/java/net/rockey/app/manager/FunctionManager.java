package net.rockey.app.manager;

import org.springframework.stereotype.Service;

import net.rockey.app.model.AppFunction;
import net.rockey.core.hibernate.HibernateEntityDao;

@Service
public class FunctionManager extends HibernateEntityDao<AppFunction>{

}
