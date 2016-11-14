package com.mockCommon.util.freeMarker.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.mockCommon.constant.Config;
import com.mockCommon.constant.ContextConstant;
import com.mockCommon.constant.LogConstant;
import com.mockCommon.util.NullOrEmpty;
import com.mockCommon.util.freeMarker.DataTemplate;
import com.mockCommon.util.freeMarker.IDataMaker;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class BaseFreeMarkerDataMaker<T> implements IDataMaker<T> {
	protected static String templateFolderPath;
	private static String outputFolderPath;
	private String templateName;
	private String outputName;
	private Configuration freeMarkerCfg;
	private Template template;

	static {
		LogConstant.debugLog.info("===================================");
		LogConstant.debugLog.info("初始化输入输出文件路径.......");
		String os = System.getProperty("os.name");
		if (os.contains("Windows")) {
			try {
				templateFolderPath = Config
						.getConfig("data.template.folder.windows");
				outputFolderPath = Config
						.getConfig("data.output.folder.windows");
			} catch (IOException e) {
				e.printStackTrace();
				templateFolderPath = ContextConstant.DEFAULT_TEMPLATE_FOLDER_WINDOWS;
				outputFolderPath = ContextConstant.DEFAULT_OUTPUT_FOLDER_WINDOWS;
			}
		} else {
			try {
				templateFolderPath = Config
						.getConfig("data.template.folder.linux");
				outputFolderPath = Config.getConfig("data.output.folder.linux");
			} catch (IOException e) {
				e.printStackTrace();
				templateFolderPath = ContextConstant.DEFAULT_TEMPLATE_FOLDER_LINUX;
				outputFolderPath = ContextConstant.DEFAULT_OUTPUT_FOLDER_LINUX;
			}
		}

		LogConstant.debugLog.info("初始化输入输出文件名.......");
		LogConstant.debugLog.info("templateFolderPath : " + templateFolderPath);
		LogConstant.debugLog.info("outputFolderPath : " + outputFolderPath);
		LogConstant.debugLog.info("===================================");
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	private void init() {
		Class<T> entityClass = null;
		Class<?> c = getClass();
		Type type = c.getGenericSuperclass();

		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			entityClass = (Class<T>) parameterizedType[0];
		}

		// 进行扫注解，确定模板的输入输出文件名
		DataTemplate dataTemplate = entityClass
				.getAnnotation(DataTemplate.class);
		if (null != dataTemplate) {
			this.outputName = dataTemplate.output();
			if (NullOrEmpty.isNullOrEmpty(dataTemplate.template())) {
				this.templateName = entityClass.getName();
			} else {
				this.templateName = dataTemplate.template();
			}
		} else {
			this.outputName = entityClass.getName();
			this.templateName = entityClass.getName();
		}

		this.freeMarkerCfg = new Configuration();
		freeMarkerCfg.setObjectWrapper(new DefaultObjectWrapper());
		freeMarkerCfg.setDefaultEncoding(Charset.forName("UTF-8").name());

	}

	public BaseFreeMarkerDataMaker() {
		init();
	}

	//返回文件
	@Override
	public String generateData(String prefix, T role) {
		OutputStreamWriter writer = null;

		try {

			LogConstant.runLog.info("-------------------templatePath: "
					+ templateFolderPath + "/" + this.templateName + prefix);
			LogConstant.runLog.info("-------------------outputPath: "
					+ outputFolderPath + outputName + prefix);

			String result = generateData2output(prefix, role);

			writer = new OutputStreamWriter(new FileOutputStream(
					outputFolderPath + outputName + prefix),
					Charset.forName("UTF-8"));
			writer.append(result);
			writer.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(writer);
		}

		return outputFolderPath;
	}

	//返回流
	@Override
	public String generateData2output(String prefix, T role) {

		String result = "error";

		OutputStreamWriter writer = null;

		try {

			LogConstant.runLog.info("-----------------templatePath: "
					+ templateFolderPath + "/" + this.templateName + prefix);
			LogConstant.runLog.info("-----------------outputPath: "
					+ outputFolderPath + outputName + prefix);

			freeMarkerCfg.setDirectoryForTemplateLoading(new File(
					templateFolderPath));
			template = freeMarkerCfg.getTemplate(this.templateName + prefix);
			Map<String, T> parameters = new HashMap<String, T>();
			parameters.put("data", role);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			writer = new OutputStreamWriter(baos);
			template.process(parameters, writer);
			writer.flush();

			result = baos.toString();

		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(writer);
		}

		return result;
	}
}