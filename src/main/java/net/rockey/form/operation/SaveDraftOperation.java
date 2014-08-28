package net.rockey.form.operation;

import net.rockey.bpm.manager.BpmVocationApplyLogManager;
import net.rockey.bpm.model.BpmVocationApplyLog;
import net.rockey.bpm.model.BpmVocationProcDtl;
import net.rockey.core.spring.ApplicationContextHelper;
import net.rockey.core.util.CONSTANTS;
import net.rockey.core.util.CPublic;
import net.rockey.core.util.LogUtils;
import net.rockey.form.manager.RecordManager;
import net.rockey.form.model.Record;
import net.rockey.form.support.RecordBuilder;

import org.activiti.engine.impl.interceptor.CommandContext;
import org.apache.log4j.Logger;

public class SaveDraftOperation extends AbstractOperation<String> {

	private final Logger log = LogUtils.getLogger(SaveDraftOperation.class,
			true);

	public static final String OPERATION_BUSINESS_TYPE = "businessType";
	public static final String OPERATION_BUSINESS_KEY = "businessKey";
	public static final String OPERATION_TASK_ID = "taskId";
	public static final String OPERATION_BPM_DEFINITION_ID = "processDefinitionId";
	public static final String OPERATION_BPM_PROCESS_ID = "bpmProcessId";
	public static final String STATUS_DRAFT_PROCESS = "DRAFT_PROCESS";
	public static final String STATUS_TASK_PROCESS = "DRAFT_TASK";

	@Override
	public String execute(CommandContext commandContext) {
		String taskId = getParamValue(OPERATION_TASK_ID);
		String businessType = getParamValue(OPERATION_BUSINESS_TYPE);
		String businessKey = getParamValue(OPERATION_BUSINESS_KEY);
		String bpmProcessId = getParamValue(OPERATION_BPM_PROCESS_ID);
		Long userId = this.getCurrentUserId();

		if (this.notEmpty(taskId)) {
			// TODO - 如果是任务草稿，直接通过processInstanceId获得record，更新数据

		} else if (this.notEmpty(businessKey)) {
			// TODO - 如果是流程草稿，直接通过businessKey获得record，更新数据

		} else {
			// 如果是第一次保存草稿，肯定是流程草稿，先初始化record，再保存数据

			Long applySeqId = null;

			if (CONSTANTS.BPM_BUSINESS_TYPE_VOCATION_REQUEST
					.equals(businessType)) {
				BpmVocationApplyLog applyLog = new BpmVocationApplyLog();
				applyLog.setApplyTime(CPublic.getDateAndTime());
				applyLog.setType(getParamValue("type"));
				applyLog.setDuration(Double
						.parseDouble((String) getParamValue("duration")));

				BpmVocationProcDtl procDtl = new BpmVocationProcDtl();
				procDtl.setApplyLog(applyLog);
				procDtl.setProcDate(CPublic.getDate());
				procDtl.setProcTime(CPublic.getTime());
				procDtl.setUserId(userId);
				procDtl.setDescn("发起流程");

				applyLog.getProcDtls().add(procDtl);

				this.getBpmVocationApplyLogManager().save(applyLog);

				applySeqId = applyLog.getId();
			} else {
				log.error("businessType is unknown.");
			}

			log.info(applySeqId);

			Record record = new RecordBuilder().build(bpmProcessId,
					STATUS_DRAFT_PROCESS, getParameters(), userId);
			record.setApplySeqId(applySeqId);
			this.getRecordManager().save(record);

			businessKey = record.getId().toString();
		}

		return businessKey;
	}

	public boolean notEmpty(String str) {
		return (str != null) && (!"".equals(str));
	}

	public RecordManager getRecordManager() {
		return ApplicationContextHelper.getBean(RecordManager.class);
	}

	public BpmVocationApplyLogManager getBpmVocationApplyLogManager() {
		return ApplicationContextHelper
				.getBean(BpmVocationApplyLogManager.class);
	}
}
