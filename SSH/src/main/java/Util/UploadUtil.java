package Util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import Model.UploadFile;

public class UploadUtil {
	/// (saveFilePath==null||"".equals(saveFilePath))?(SAVE_FILEPATH+File.separator+saveFileName):(saveFilePath+File.separator+saveFileName)
	public static void uploadFile(UploadFile uploadFile) {
		try {
			FileUtils.copyFile(uploadFile.getUpload(),
					new File(uploadFile.getSaveFilePath(), uploadFile.getSaveFileName()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			uploadFile.getUpload().delete();
		}
	}
}
