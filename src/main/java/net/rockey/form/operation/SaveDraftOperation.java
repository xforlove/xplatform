package net.rockey.form.operation;

import net.rockey.bpm.manager.BpmLeaveApplyLogManager;
import net.rockey.bpm.model.BpmLeaveApplyLog;
import net.rockey.core.spring.ApplicationContextHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.CPublic;
import net.rockey.core.util.LogUtils;
import net.rockey.core.util.ShiroUtils;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.apache.log4j.Logger;

public class SaveDraftOperation extends AbstractOperation<String> {

	private final Logger log = LogUtils.getLogger(SaveDraftOperation.class,
			true);

	@Override
	public String execute(CommandContext commandContext) {
		String taskId = getParamValue(CONSTANTS.OPERATION_TASK_ID);
		String businessType = getParamValue(CONSTANTS.OPERATION_BUSINESS_TYPE);
		String businessKey = getParamValue(CONSTANTS.OPERATION_BUSINESS_KEY);
		
		if (this.notEmpty(taskId)) {
			// TODO - 如果是任务草稿，直接通过processInstanceId获得record，更新数据

		} else if (this.notEmpty(businessKey)) {
			// TODO - 如果是流程草稿，直接通过businessKey获得record，更新数据

		} else {
			// 如果是第一次保存草稿，肯定是流程草稿，先初始化record，再保存数据
			if (CONSTANTS.BPM_BUSINESS_TYPE_VOCATION_REQUEST
					.equals(businessType)) {
				// 构建业务模型
				BpmLeaveApplyLog applyLog = new BpmLeaveApplyLog();
				applyLog.setType(getParamValue("pp_type"));
				applyLog.setCreator(Long.parseLong((String) ShiroUtils
						.getAttribute("user_id")));
				applyLog.setCreateTime(CPublic.getDateAndTime());
				applyLog.setStatFlag(CONSTANTS.BUSINESS_APPLY_LOG_STAT_FLAG_INIT);

				this.getBpmLeaveApplyLogManager().save(applyLog);

				// 获得businessKey
				businessKey = String.valueOf(applyLog.getId());
			} else {
				log.error("businessType is unknown.");
			}

		}

		log.info(businessKey);

		return businessKey;
	}

	public boolean notEmpty(String str) {
		return (str != null) && (!"".equals(str));
	}

	public BpmLeaveApplyLogManager getBpmLeaveApplyLogManager() {
		return ApplicationContextHelper
				.getBean(BpmLeaveApplyLogManager.class);
	}
}
