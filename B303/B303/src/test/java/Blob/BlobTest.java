package Blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import org.hibernate.Hibernate;

import Base.BaseClass;

/** 
 * 
 * @author zhang
 * @Date  2016年6月4日 下午4:13:47
 * @doing 
 */
class Student{
	private Blob blob;
	public void setBlob(Blob blob) {
		this.blob = blob;
	}
	public Blob getBlob() {
		return blob;
	}
}
public class BlobTest {
	public static void main(String[] args) throws Exception {
		Student student=new Student();
		File file=new File("C:/");
		InputStream inputStream=new FileInputStream(file);
		Blob image=Hibernate.getLobCreator(BaseClass.getSession()).createBlob(inputStream,inputStream.available());
		student.setBlob(image);
		BaseClass.getSession().save(student);
		//读
		
	}
	
}
