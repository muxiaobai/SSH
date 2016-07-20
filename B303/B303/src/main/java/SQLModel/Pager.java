package SQLModel;

import java.io.Serializable;
import java.util.List;

/**
 * 根据entry生成HQL语句
 * @author zhang
 * @date  2016年7月7日 下午10:04:35
 * @doing TODO
 * @param <T>
 */
public class Pager<T> implements Serializable {

	private static final long serialVersionUID = -8741766802354222579L;
	/**
	 * // 每页显示多少条记录
	 */
	private Integer pageSize; 
	/**
	 * //当前第几页数据
	 */
	private Integer currentPage; 
	/**
	 * // 一共多少条记录
	 */
	private Integer totalRecord; 
	/**
	 * // 一共多少页记录
	 */
	private Integer totalPage; 
	/**
	 * 需要判断的对象
	 */
	private T entry; 
	/**
	 * //要显示的数据
	 */
	private List<T> dataList; 
	
	public void setEntry(T entry) {
		this.entry = entry;
	}
	public T getEntry() {
		return entry;
	}
	/**
	 * 
	 * @param currentPage 当前第几页数据
	 * @param pageSize 每页显示多少条记录
	 * @param entry 需要判断的对象
	 * @param sourceList 要显示的数据
	 */
	public Pager(Integer currentPage, Integer pageSize,T entry, List<T> sourceList){
		
		this.entry=entry;
		
		if(sourceList == null || sourceList.isEmpty()){
			return;
		}
		// 总记录条数
		this.totalRecord = sourceList.size();
		
		// 每页显示多少条记录
		this.pageSize = pageSize;
		
		//获取总页数
		this.totalPage = this.totalRecord / this.pageSize;
		if(this.totalRecord % this.pageSize !=0){
			this.totalPage = this.totalPage + 1;
		}
		
		// 当前第几页数据
//		this.currentPage = this.totalPage < pageNum ?  this.totalPage : pageNum;
		this.currentPage=currentPage;	
		// 起始索引
	//	int fromIndex	= this.pageSize * (this.currentPage -1);
		
		// 结束索引
	//	int toIndex  = this.pageSize * this.currentPage > this.totalRecord ? this.totalRecord : this.pageSize * this.currentPage;
				
		this.dataList = sourceList;//.subList(fromIndex, toIndex);
	}
	
	public Pager(){
		
	}
	public Pager(T entry){
		this.entry=entry;
	}
	public Pager(Integer currentPage, Integer pageSize, T entry){
		super();
		this.pageSize = pageSize;
		this.currentPage=currentPage;
		this.entry=entry;
	}
	/**
	 * 
	 * @param pageSize  每页显示多少条记录
	 * @param currentPage 当前第几页数据
	 * @param totalRecord  一共多少条记录
	 * @param totalPage 一共多少页记录
	 * @param entry  需要判断的对象
	 * @param dataList 要显示的数据
	 */
	public Pager(Integer pageSize, Integer currentPage, Integer totalRecord, Integer totalPage,T  entry,
			List<T> dataList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.entry=entry;
		this.totalRecord = totalRecord;
		this.totalPage = totalPage;
		this.dataList = dataList;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	@Override
	public String toString() {
		return "Pager [pageSize=" + pageSize + ", currentPage=" + currentPage + ", totalRecord=" + totalRecord
				+ ", totalPage=" + totalPage +" , entry=" + entry + ", dataList=" + dataList +"]";
		
	}

	

}
