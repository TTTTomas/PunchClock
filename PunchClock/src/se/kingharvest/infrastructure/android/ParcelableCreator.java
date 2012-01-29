package se.kingharvest.infrastructure.android;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;

import se.kingharvest.infrastructure.diagnostics.Logger;
import se.kingharvest.infrastructure.reflection.Reflect;
import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableCreator<T> {

	protected static final String LOG_TAG = ParcelableCreator.class.getSimpleName();
	Class<T> _type;
	
	public ParcelableCreator(Class<T> type)
	{
		_type = type;
	}
	
	public Parcelable.Creator<T> getInstancingCreator()
	{
		Parcelable.Creator<T> creator = new Parcelable.Creator<T>() {

			public T createFromParcel(Parcel in) {
		    	Logger.write(LOG_TAG, "Creating new instance of " + _type.getSimpleName() + ".");
				Constructor<T> constructor = Reflect.getConstructor(_type, Parcelable.class);
				return Reflect.newInstance(constructor, in);
			}
	
			@SuppressWarnings("unchecked")
			public T[] newArray(int size) {
		    	Logger.write(LOG_TAG, "Creating array of " + _type.getSimpleName() + ".");
				return (T[]) Array.newInstance(_type, size);
			}
		};

		return creator;
	}

	public Parcelable.Creator<T> getSerializingCreator()
	{
		Parcelable.Creator<T> creator = new Parcelable.Creator<T>() {

			@SuppressWarnings("unchecked")
			public T createFromParcel(Parcel in) {
		    	Logger.write(LOG_TAG, "Deserializing " + _type.getSimpleName() + " from Parcel.");
				T instance = ((T)in.readSerializable());
				return instance;
			}
	
			@SuppressWarnings("unchecked")
			public T[] newArray(int size) {
		    	Logger.write(LOG_TAG, "Creating array of " + _type.getSimpleName() + ".");
				return (T[]) Array.newInstance(_type, size);
			}
		};

		return creator;
	}
}
