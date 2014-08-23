package net.rockey.app.service;

import java.util.ArrayList;
import java.util.List;

import net.rockey.app.manager.FuncGroupManager;
import net.rockey.app.manager.FunctionManager;
import net.rockey.app.model.AppFuncGroup;
import net.rockey.app.model.AppFunction;
import net.rockey.app.support.AppFuncGroupDTO;
import net.rockey.core.mapper.BeanMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class AppService {

	@Autowired
	public FunctionManager functionManager;

	@Autowired
	public FuncGroupManager funcGroupManager;

	BeanMapper mapper = new BeanMapper();

	public List<AppFuncGroup> findFuncGroups() {
		return funcGroupManager
				.find(" from AppFuncGroup where statFlag = 'Normal' order by id ");
	}

	public List<AppFunction> findFunctions() {
		return functionManager
				.find(" from AppFunction where statFlag = 'Normal' order by code ");
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public List<AppFuncGroupDTO> createFuncGroupListOnSelected(
			AppFunction function) {

		List<AppFuncGroupDTO> result = new ArrayList<AppFuncGroupDTO>();

		for (AppFuncGroup group : findFuncGroups()) {
			AppFuncGroupDTO dest = new AppFuncGroupDTO();
			mapper.copy(group, dest);
			dest.setSelected(function == null ? false : function.getGroup()
					.getId() == group.getId());
			result.add(dest);
		}

		return result;
	}

}
