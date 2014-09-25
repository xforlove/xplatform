package net.rockey.doc.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.rockey.core.mapper.BeanMapper;
import net.rockey.core.spring.MessageHelper;
import net.rockey.core.util.CPublic;
import net.rockey.core.util.Page;
import net.rockey.core.util.ParamUtils;
import net.rockey.core.util.PropertyUtils;
import net.rockey.core.util.ShiroUtils;
import net.rockey.doc.manager.DocInfoManager;
import net.rockey.doc.model.DocInfo;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("doc")
public class DocInfoController {

	private final static Logger log = LoggerFactory
			.getLogger(DocInfoController.class);

	@Autowired
	private DocInfoManager docInfoManager;

	@Autowired
	private MessageHelper messageHelper;

	private BeanMapper beanMapper = new BeanMapper();

	@RequestMapping("doc-info-list")
	public String list(@ModelAttribute Page page,
			@RequestParam Map<String, Object> parameterMap, Model model) {

		log.debug("parameterMap : {}", parameterMap);

		String name = ParamUtils.getString(parameterMap, "name");

		String userId = (String) ShiroUtils.getAttribute("user_id");

		List<DocInfo> docs;
		if (StringUtils.isEmpty(name)) {
			docs = docInfoManager.find(" from DocInfo where userId = ?",
					Long.parseLong(userId));
		} else {
			/* 字符串两端全模糊匹配 */
			docs = docInfoManager.find(DocInfo.class,
					Restrictions.like("name", "%" + name + "%"),
					Restrictions.eq("userId", Long.parseLong(userId)));
		}

		model.addAttribute("docs", docs);

		return "doc/doc-info-list";
	}

	@RequestMapping("doc-info-input")
	public String input(@RequestParam(value = "id", required = false) Long id,
			Model model) {
		if (id != null) {
			DocInfo docInfo = docInfoManager.get(id);
			model.addAttribute("model", docInfo);
		}

		return "doc/doc-info-input";
	}

	@RequestMapping("doc-info-save")
	public String save(@ModelAttribute DocInfo docInfo,
			@RequestParam Map<String, Object> parameterMap,
			RedirectAttributes redirectAttributes) throws Exception {
		DocInfo dest = null;
		Long id = docInfo.getId();

		if (id != null) {
			dest = docInfoManager.get(id);
			beanMapper.copy(docInfo, dest);
		} else {
			dest = docInfo;
			dest.setCreateTime(CPublic.getDateAndTime());

			String userId = (String) ShiroUtils.getAttribute("user_id");
			dest.setUserId(Long.parseLong(userId));
		}

		docInfoManager.save(dest);

		messageHelper.addFlashMessage(redirectAttributes, "保存成功");

		return "redirect:/doc/doc-info-list.do";
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param response
	 * @return 文件路径，在多附件情况下，采用"|"分隔
	 */
	@RequestMapping("doc-info-upload")
	public String upload(HttpServletRequest request,
			HttpServletResponse response) {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		// 获取前台传值
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

		String localFolder = PropertyUtils.getInstance().getValue(
				"file.load.path")
				+ File.separator + CPublic.getDate() + File.separator;

		File f = new File(localFolder);
		if (!f.exists()) {
			f.mkdirs();
		}

		String fileName = null;
		String path = null;

		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();

			// 加密字符串,返回String的密文
			String uuid = UUID.randomUUID().toString().replaceAll("\\-", "");// 返回一个随机UUID。
			String suffix = fileName.indexOf(".") != -1 ? fileName.substring(
					fileName.lastIndexOf("."), fileName.length()) : null;

			String destName = CPublic.getDate() + uuid
					+ (suffix != null ? suffix : "");// 构成新文件名。

			File dest = new File(localFolder + File.separator + destName);

			try {
				FileCopyUtils.copy(mf.getBytes(), dest);
				path = localFolder + File.separator + destName + "|";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (path != null && StringUtils.isNotEmpty(path)) {
			path = path.substring(0, path.indexOf("|"));
		}

		return path;
	}

}
