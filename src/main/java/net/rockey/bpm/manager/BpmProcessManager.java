package net.rockey.bpm.manager;

import net.rockey.bpm.model.BpmProcess;
import net.rockey.core.hibernate.HibernateEntityDao;

import org.springframework.stereotype.Service;

@Service
public class BpmProcessManager extends HibernateEntityDao<BpmProcess> {

}
