package se.kingharvest.infrastructure.data.columns;

import java.lang.reflect.Field;

import se.kingharvest.infrastructure.entity.IEntity;

public class EntityReflect {

	public static <E extends IEntity> ColumnCollection<E> createColumnsFromEntityType(Class<E> entityType)
	{
		// Iterate all public fields of an entity and create a column collection from
		// all public fields of the entity.
		Field[] fields = entityType.getFields();
		Column[] columns = new Column[fields.length];
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			columns[i] = new Column(f.getName(), f.getType(), i);
		}
		
		ColumnCollection<E> columnCollection = new ColumnCollection<E>(columns);
		return columnCollection;
	}


}
