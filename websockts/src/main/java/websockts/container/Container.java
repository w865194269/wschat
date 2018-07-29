package websockts.container;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.web.socket.WebSocketSession;

public interface Container<T,Id extends Serializable> {

	/**
	 * 添加新的元素
	 * @param t
	 */
	public void add(T bean,Id id) ;
	
	/**
	 * 获取指定id 为pk的元素
	 * @param pk
	 * @return
	 */
	public T get(Id id);
	
	/**
	 * 删除id为pk的
	 * @param pk
	 */
	public void remove(Id id);
	
	/**
	 * 获取全部的 T
	 * @return
	 */
	public Collection<T> get();

	/**
	 * 获取集合中id列表
	 * @return
	 */
	public Collection<Id> getKeys();

	/**
	 * 获取除了自己以外的id列表
	 * @param id
	 * @return
	 */
	public Collection<Id> getOtherKeys(Id id);

}
