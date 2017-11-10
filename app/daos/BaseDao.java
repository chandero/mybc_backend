package daos;

import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;

public class BaseDao<T extends Model> {
	    private final Class<T> clazz;

	    protected BaseDao(
	            Class<T> clazz) {
	        this.clazz = clazz;
	    }

	    public List<T> getAll() {
	    	//TODO
	        return null;
	    }

	    public T put(T object) {
	    	Ebean.save(object);
	        return object;
	    }

	    public T get(Long id) {
	        return Ebean.find(clazz, id);
	    }
	    
		public Boolean exists(Long id) {
	        return get(id) != null;
	    }

	    public List<T> getSubset(List<Long> ids) {
	        return null;
	    }

	    public Map<Long, T> getSubsetMap(List<Long> ids) {
	        return null;
	    }

	    public void delete(T object) {
	        Ebean.delete(object);
	    }
	
	    public void delete(Long id) {
	    	T object = Ebean.find(clazz, id);
	    	if (object != null){
	    		Ebean.delete(object);
	    	}
	    }

	    public void delete(List<T> objects) {
	        //TODO
	    }

	    public void deleteAll() {
	       //TODO
	    }


	    public int countAll() {
	       //TODO
	    	return -1;
	    }

	    public List<T> getSome(Integer offset, Integer limit) {
	       //TODO
	    	return null;
	    }

	}
