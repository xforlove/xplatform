package net.rockey.app.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.rockey.app.manager.FuncGroupManager;
import net.rockey.app.model.AppFuncGroup;
import net.rockey.app.service.AppService;
import net.rockey.app.support.AppFuncGroupDTO;
import net.rockey.core.mapper.BeanMapper;
import net.rockey.core.spring.MessageHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.LogUtils;
import net.rockey.core.util.Page;
import net.rockey.core.util.ParamUtils;
import net.rockey.core.util.StringUtils;
import net.rockey.core.util.ViewTransfer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("app")
public class FuncGroupController {

	private final Logger log = LogUtils.getLogger(FuncGroupController.class,
			true);

	@Autowired
	private FuncGroupManager funcGroupManager;

	@Autowired
	private AppService appService;

	@Autowired
	private MessageHelper messageHelper;

	private BeanMapper mapper = new BeanMapper();

	@RequestMapping("funcgroup-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {

		String name = ParamUtils.getString(parameterMap, "name");

		List<AppFuncGroupDTO> funcGrpDtos = new ArrayList<AppFuncGroupDTO>();

		List<AppFuncGroup> funcGrps;
		if (StringUtils.isEmpty(name)) {
			funcGrps = (List<AppFuncGroup>) funcGroupManager.getAll();
		} else {
			/* 字符串两端全模糊匹配 */
			funcGrps = (List<AppFuncGroup>) funcGroupManager.findByLike("name",
					"%" + name + "%");
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
			@RequestParam(value = "id", required = false) Long id,
			Model model) {

		if (id != null) {

			AppFuncGroup funcGrp = funcGroupManager.load(id);

			AppFuncGroupDTO dest = new AppFuncGroupDTO();

			mapper.copy(funcGrp, dest);

			dest.setStatFlagCn(ViewTransfer.getPairStatFlagCn(funcGrp
					.getStatFlag()));

			model.addAttribute("id", id);
			model.addAttribute("funcGrp", dest);
		}

		return "app/funcgroup-input";
	}

	@RequestMapping("funcgroup-save")
	public String save(@ModelAttribute AppFuncGroupDTO funcGrpDTO,
			RedirectAttributes redirectAttributes, Model model) {
		Long funcGrpId = funcGrpDTO.getId();
		String funcGrpName = funcGrpDTO.getName();
		String statFlag = funcGrpDTO.getStatFlag();

		statFlag = statFlag == null ? CONSTANTS.STAT_FLAG_CLOSE : statFlag;

		AppFuncGroup funcGrp = (funcGrpId == null) ? (new AppFuncGroup())
				: (funcGroupManager.get(funcGrpId));

		funcGrp.setName(funcGrpName);
		funcGrp.setStatFlag(statFlag);

		funcGroupManager.save(funcGrp);

		messageHelper.addFlashMessage(redirectAttributes, "保存成功");

		return "redirect:funcgroup-list.do";
	}
}
