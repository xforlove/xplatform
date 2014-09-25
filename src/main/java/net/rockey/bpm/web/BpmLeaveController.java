package net.rockey.bpm.web;

import java.util.Map;

import net.rockey.bpm.support.BpmLeaveApplyLog;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.ParamUtils;
import net.rockey.core.util.ShiroUtils;
import net.rockey.form.keyvalue.KeyValue;
import net.rockey.form.keyvalue.Prop;
import net.rockey.form.keyvalue.Record;
import net.rockey.system.auth.manager.UserManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("leave")
public class BpmLeaveController {

	private static final Logger log = LoggerFactory
			.getLogger(BpmLeaveController.class);

	@Autowired
	private UserManager userManager;

	@Autowired
	private KeyValue keyValue;

	@RequestMapping("apply")
	public String apply(@RequestParam Map<String, Object> parameterMap,
			RedirectAttributes redirectAttributes, Model model) {
		String processDefinitionId = ParamUtils.getString(parameterMap,
				CONSTANTS.PROCESS_PARAMETER_BPM_DEFINITION_ID);

		String bpmProcessId = ParamUtils.getString(parameterMap,
				CONSTANTS.PROCESS_PARAMETER_BPM_PROCESS_ID);

		String businessKey = ParamUtils.getString(parameterMap,
				CONSTANTS.PROCESS_PARAMETER_BUSINESS_KEY);

		log.info("processDefinitionId : {}", processDefinitionId);
		log.info("bpmProcessId : {}", bpmProcessId);
		log.info("businessKey : {}", businessKey);

		model.addAttribute("processDefinitionId", processDefinitionId);
		model.addAttribute("bpmProcessId", bpmProcessId);
		model.addAttribute("businessKey", businessKey);

		BpmLeaveApplyLog leaveApply = new BpmLeaveApplyLog();

		// 设置申请人员
		leaveApply.setCreator(Long.parseLong((String) ShiroUtils
				.getAttribute("user_id")));

		model.addAttribute("model", leaveApply);

		return "bpm/leave-apply";
	}

	@RequestMapping("leaderAudit")
	public String leaderAudit(@RequestParam Map<String, Object> parameterMap,
			RedirectAttributes redirectAttributes, Model model) {
		String businessKey = ParamUtils.getString(parameterMap,
				CONSTANTS.PROCESS_PARAMETER_BUSINESS_KEY);

		String taskId = ParamUtils.getString(parameterMap,
				CONSTANTS.PROCESS_PARAMETER_TASK_ID);

		log.info("businessKey : {}", businessKey);
		log.info("taskId : {}", taskId);

		model.addAttribute("businessKey", businessKey);
		model.addAttribute("taskId", taskId);

		Record record = keyValue.findByCode(businessKey);

		BpmLeaveApplyLog leaveApply = new BpmLeaveApplyLog();

		for (Prop prop : record.getProps().values()) {
			String code = prop.getCode();
			String value = prop.getValue();

			if ("type".equals(code)) {
				leaveApply.setType(value);
			} else if ("applyDuration".equals(code)) {
				leaveApply.setApplyDuration(Double.parseDouble(value));
			} else if ("reason".equals(code)) {
				leaveApply.setReason(value);
			} else if ("creator".equals(code)) {
				leaveApply.setCreator(Long.parseLong(value));
				leaveApply.setCreatorName(userManager
						.get(Long.parseLong(value)).getName());
			} else {
				// TODO - 可支持扩展
			}
		}

		// 设置审核人员
		leaveApply.setAuditor(Long.parseLong((String) ShiroUtils
				.getAttribute("user_id")));

		model.addAttribute("model", leaveApply);

		return "bpm/leave-leader-audit";
	}

	@RequestMapping("modifyApply")
	public String modifyApply(@RequestParam Map<String, Object> parameterMap,
			RedirectAttributes redirectAttributes, Model model) {
		String businessKey = ParamUtils.getString(parameterMap,
				CONSTANTS.PROCESS_PARAMETER_BUSINESS_KEY);

		String taskId = ParamUtils.getString(parameterMap,
				CONSTANTS.PROCESS_PARAMETER_TASK_ID);

		log.info("businessKey : {}", businessKey);
		log.info("taskId : {}", taskId);

		model.addAttribute("businessKey", businessKey);
		model.addAttribute("taskId", taskId);

		Record record = keyValue.findByCode(businessKey);

		BpmLeaveApplyLog leaveApply = new BpmLeaveApplyLog();

		for (Prop prop : record.getProps().values()) {
			String code = prop.getCode();
			String value = prop.getValue();

			if ("type".equals(code)) {
				leaveApply.setType(value);
			} else if ("applyDuration".equals(code)) {
				leaveApply.setApplyDuration(Double.parseDouble(value));
			} else if ("reason".equals(code)) {
				leaveApply.setReason(value);
			} else if ("leaderAuditPass".equals(code)) {
				leaveApply.setLeaderAuditPass(Integer.parseInt(value));
			} else if ("rejectReason".equals(code)) {
				leaveApply.setRejectReason(value);
			} else if ("creator".equals(code)) {
				leaveApply.setCreator(Long.parseLong(value));
			} else if ("auditor".equals(code)) {
				leaveApply.setAuditor(Long.parseLong(value));
				leaveApply.setAuditorName(userManager
						.get(Long.parseLong(value)).getName());
			} else {
				// TODO - 可支持扩展
			}
		}

		// 设置申请人员
		leaveApply.setCreator(Long.parseLong((String) ShiroUtils
				.getAttribute("user_id")));

		model.addAttribute("model", leaveApply);

		return "bpm/leave-modify-apply";
	}

	@RequestMapping("hrRecord")
	public String hrRecord(@RequestParam Map<String, Object> parameterMap,
			RedirectAttributes redirectAttributes, Model model) {
		String businessKey = ParamUtils.getString(parameterMap,
				CONSTANTS.PROCESS_PARAMETER_BUSINESS_KEY);

		String taskId = ParamUtils.getString(parameterMap,
				CONSTANTS.PROCESS_PARAMETER_TASK_ID);

		log.info("businessKey : {}", businessKey);
		log.info("taskId : {}", taskId);

		model.addAttribute("businessKey", businessKey);
		model.addAttribute("taskId", taskId);

		Record record = keyValue.findByCode(businessKey);

		BpmLeaveApplyLog leaveApply = new BpmLeaveApplyLog();

		for (Prop prop : record.getProps().values()) {
			String code = prop.getCode();
			String value = prop.getValue();

			if ("type".equals(code)) {
				leaveApply.setType(value);
			} else if ("applyDuration".equals(code)) {
				leaveApply.setApplyDuration(Double.parseDouble(value));
			} else if ("reason".equals(code)) {
				leaveApply.setReason(value);
			} else if ("leaderAuditPass".equals(code)) {
				leaveApply.setLeaderAuditPass(Integer.parseInt(value));
			} else if ("rejectReason".equals(code)) {
				leaveApply.setRejectReason(value);
			} else if ("creator".equals(code)) {
				leaveApply.setCreator(Long.parseLong(value));
				leaveApply.setCreatorName(userManager
						.get(Long.parseLong(value)).getName());
			} else if ("auditor".equals(code)) {
				leaveApply.setAuditor(Long.parseLong(value));
				leaveApply.setAuditorName(userManager
						.get(Long.parseLong(value)).getName());
			} else {
				// TODO - 可支持扩展
			}
		}

		// 设置记录人员
		leaveApply.setRecorder(Long.parseLong((String) ShiroUtils
				.getAttribute("user_id")));

		model.addAttribute("model", leaveApply);

		return "bpm/leave-hr-record";
	}

}
