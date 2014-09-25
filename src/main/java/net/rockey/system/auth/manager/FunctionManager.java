package net.rockey.system.auth.manager;

import org.springframework.stereotype.Service;

import net.rockey.core.hibernate.HibernateEntityDao;
import net.rockey.system.auth.model.AppFunction;

@Service
public class FunctionManager extends HibernateEntityDao<AppFunction>{

}
