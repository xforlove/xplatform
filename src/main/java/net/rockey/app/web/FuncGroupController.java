package net.rockey.app.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.rockey.app.manager.FuncGroupManager;
import net.rockey.app.model.AppFuncGroup;
import net.rockey.app.service.AppService;
import net.rockey.app.support.AppFuncGroupDTO;
import net.rockey.core.mapper.BeanMapper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.LogUtils;
import net.rockey.core.util.Page;
import net.rockey.core.util.ParamUtils;
import net.rockey.core.util.SequencePrefix;
import net.rockey.core.util.SequenceUtils;
import net.rockey.core.util.StringUtils;
import net.rockey.core.util.ViewTransfer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("app")
public class FuncGroupController {

	private Logger log = LogUtils.getLogger(FuncGroupController.class, true);

	@Autowired
	private FuncGroupManager funcGroupManager;

	@Autowired
	private AppService appService;

	private BeanMapper mapper = new BeanMapper();

	@RequestMapping("funcgroup-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {

		String funcGrpName = ParamUtils.getString(parameterMap, "fgName");

		List<AppFuncGroupDTO> funcGrpDtos = new ArrayList<AppFuncGroupDTO>();

		List<AppFuncGroup> funcGrps;
		if (StringUtils.isEmpty(funcGrpName)) {
			funcGrps = (List<AppFuncGroup>) funcGroupManager
					.find(" from AppFuncGroup");
		} else {
			/* 字符串两端全模糊匹配 */
			funcGrps = (List<AppFuncGroup>) funcGroupManager.findByLike(
					"funcGrpName", "%" + funcGrpName + "%");
		}

		for (AppFuncGroup funcGrp : funcGrps) {

			AppFuncGroupDTO dest = new AppFuncGroupDTO();
			mapper.copy(funcGrp, dest);
			dest.setStatFlagCn(ViewTransfer.getPairStatFlagCn(funcGrp
					.getStatFlag()));
			funcGrpDtos.add(dest);
		}

		page.setResult(funcGrpDtos);
		model.addAttribute("page", page);

		return "app/funcgroup-list";
	}

	@RequestMapping("funcgroup-input")
	public String input(
			@RequestParam(value = "fgid", required = false) Long fgid,
			Model model) {

		if (fgid != null) {

			AppFuncGroup funcGrp = funcGroupManager.load(fgid);
			
			AppFuncGroupDTO dest = new AppFuncGroupDTO();
			
			mapper.copy(funcGrp, dest);
			
			dest.setStatFlagCn(ViewTransfer.getPairStatFlagCn(funcGrp
					.getStatFlag()));

			model.addAttribute("fgid", fgid);
			model.addAttribute("funcGrp", dest);
		}

		return "app/funcgroup-input";
	}

	@RequestMapping("funcgroup-save")
	public String save(@ModelAttribute AppFuncGroupDTO funcGrpDTO, Model model) {
		Long funcGrpId = funcGrpDTO.getId();
		String funcGrpName = funcGrpDTO.getName();
		String statFlag = funcGrpDTO.getStatFlag();

		statFlag = statFlag == null ? CONSTANTS.STAT_FLAG_CLOSE : statFlag;

		if (funcGrpId == null) {
			// Create an new record.

			AppFuncGroup funcGrp = new AppFuncGroup();

			funcGrp.setId(SequenceUtils
					.getSequence(SequencePrefix.SEQ_FISRT_POSITION_FOR_APP_FUNCGROUP));
			funcGrp.setName(funcGrpName);
			funcGrp.setStatFlag(statFlag);
			funcGroupManager.save(funcGrp);

		} else {
			// Modify an exists record.

			AppFuncGroup funcGrp = funcGroupManager.get(funcGrpId);

			funcGrp.setName(funcGrpName);
			funcGrp.setStatFlag(statFlag);
			funcGroupManager.save(funcGrp);
		}

		return "redirect:funcgroup-list.do";
	}
}
