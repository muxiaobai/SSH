package Model;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;
import org.apache.commons.io.FilenameUtils;
/**
 * 文件模型
 * @author zhang
 * @date  2016年4月26日 下午4:19:10
 * @doing TODO
 */
public class UploadFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private File upload;//文件本身
	private String uploadFileName;//文件上传的名字
	private String filePreFix;//文件前缀
	private String fileExtension;//文件后缀
	private String uploadContentType;//文件类型
	private String saveFileName;//文件保存的名字当为空时，保存UUID.randomUUID()
	private String saveFilePath;//文件保存路径
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		setFilePreFix(FilenameUtils.getBaseName(uploadFileName));
		setFileExtension(FilenameUtils.getExtension(uploadFileName));
		setSaveFileName(UUID.randomUUID().toString()+"."+getFileExtension());
		this.uploadFileName = uploadFileName;
	}
	
	public String getFilePreFix() {
		return filePreFix;
	}
	public void setFilePreFix(String filePreFix) {
		this.filePreFix = filePreFix;
	}
	
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName =saveFileName;
	}
	public String getSaveFilePath() {
		return saveFilePath;
	}
	public void setSaveFilePath(String saveFilePath) {
		this.saveFilePath =saveFilePath;
	}
	@Override
	public String toString() {
		return "UploadFile [upload=" + upload + ", uploadFileName=" + uploadFileName + ", filePreFix=" + filePreFix
				+ ", fileExtension=" + fileExtension + ", uploadContentType=" + uploadContentType + ", saveFileName="
				+ saveFileName + ", saveFilePath=" + saveFilePath + "]";
	}

	
	
}
