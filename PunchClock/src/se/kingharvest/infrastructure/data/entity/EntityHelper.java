package se.kingharvest.infrastructure.data.entity;

import se.kingharvest.infrastructure.data.columns.Column;
import se.kingharvest.infrastructure.data.columns.ColumnCollection;
import se.kingharvest.infrastructure.data.columns.ColumnReflect;
import se.kingharvest.infrastructure.entity.IEntity;
import se.kingharvest.infrastructure.system.Reflect;
import android.database.Cursor;

public class EntityHelper {

	public static <E extends IEntity> E createEntityFromCursor(Cursor cursor, ColumnCollection<E> columns, Class<E> entityType)
	{
		E entity = Reflect.newInstance(entityType);
	
		// FIXME: should iterate cursor instead of columns.
		Column[] cols = columns.Columns;
		for (int i = 0; i < cols.length; i++) {
			ColumnReflect.setValue(cursor, cols[i], entity);
		}
		return entity;
	}
}
