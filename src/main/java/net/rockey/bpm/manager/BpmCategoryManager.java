package net.rockey.bpm.manager;

import org.springframework.stereotype.Service;

import net.rockey.bpm.model.BpmCategory;
import net.rockey.core.hibernate.HibernateEntityDao;

@Service
public class BpmCategoryManager extends HibernateEntityDao<BpmCategory> {

}
