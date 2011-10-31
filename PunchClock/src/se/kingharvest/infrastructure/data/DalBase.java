package se.kingharvest.infrastructure.data;

import se.kingharvest.infrastructure.entity.EntityBase;

public class DalBase<E extends EntityBase> implements IDal<E>{

	public E select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public E[] selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(E entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(E entity) {
		// TODO Auto-generated method stub
		return 0;
	}

}
