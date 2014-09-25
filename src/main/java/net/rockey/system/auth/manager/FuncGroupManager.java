package net.rockey.system.auth.manager;

import org.springframework.stereotype.Service;

import net.rockey.core.hibernate.HibernateEntityDao;
import net.rockey.system.auth.model.AppFuncGroup;

@Service
public class FuncGroupManager extends HibernateEntityDao<AppFuncGroup>{

}
