package application;

public class addObjects<T, U> {
	T obj1;
	U obj2;
	
	public addObjects(T obj1, U obj2){
		this.obj1 = obj1;
		this.obj2 = obj2;
	}
	
	public Object add() {
		if(obj1 instanceof Integer && obj2 instanceof Integer) {
			return (((Number) obj1).intValue()+((Number) obj2).intValue());
		} else if (obj1 instanceof Double || obj2 instanceof Double) {
			return (((Number) obj1).doubleValue() + ((Number) obj2).doubleValue());
		} else if (obj1 instanceof Float || obj2 instanceof Float) {
			return (((Number) obj1).doubleValue() + ((Number) obj2).doubleValue());
		} else if (obj1 instanceof String) {
			return (obj1.toString() + obj2.toString());
		} else {
			return null;
		}
		
	}
}
