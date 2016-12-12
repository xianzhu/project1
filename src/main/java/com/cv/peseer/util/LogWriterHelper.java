package com.cv.peseer.util;

import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.ibatis.logging.LogException;

import com.cv.peseer.conf.ConfigurationHelper;
import com.cv.peseer.model.RequestConext;


public class LogWriterHelper implements Runnable {
	private final LinkedBlockingQueue<RequestConext> queue = new LinkedBlockingQueue<>();
	private static final LogWriterHelper instance = new LogWriterHelper();
	private final HashMap<String, LogWriter> fileName2Writer = new HashMap<>();

	private LogWriterHelper() {
	}

	public static LogWriterHelper getInstance() {
		return instance;
	}

	public void offer(RequestConext requestConext) {
		queue.offer(requestConext);
	}

	public static void startConsume() {
		Thread thread = new Thread(LogWriterHelper.getInstance());
		thread.start();
	}

	@Override
	public void run() {
		Calendar calendar1 = Calendar.getInstance();
		int last_day = calendar1.get(Calendar.DAY_OF_MONTH);
		while (true) {
			try {
				RequestConext requestConext = queue.poll(1, TimeUnit.SECONDS);

				Calendar calendar = Calendar.getInstance();
				if (requestConext != null) {
					LogWriter logWriter = fileName2Writer.get(requestConext.getLogFileName());
					if (logWriter == null) {
						String dateStr = ThreadSafeSDFUtil.getDateFormat().format(calendar.getTime());
						String filePath = String.format("%s-%s", Paths.get(ConfigurationHelper.LOG_ROOT_FOLDER,
								requestConext.getLogFolderName(), requestConext.getLogFileName()).toString(), dateStr);
						logWriter = new LogWriter(filePath);
						fileName2Writer.put(requestConext.getLogFileName(), logWriter);
					}
					logWriter.log(requestConext.toString());
				}

				//每天备份一次
				if (calendar.get(Calendar.DAY_OF_MONTH) != last_day) {
					for (Entry<String, LogWriter> entry : fileName2Writer.entrySet()) {
						if (entry.getValue() != null) {
							entry.getValue().close();
						}
					}

					fileName2Writer.clear();
					last_day = calendar.get(Calendar.DAY_OF_MONTH);
				}

			} catch (InterruptedException | LogException e) {
				e.printStackTrace();
			}
		}		
	}
}
