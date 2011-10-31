package se.kingharvest.infrastructure.model;

import java.io.Serializable;

import se.kingharvest.infrastructure.diagnostics.Logger;
import android.os.Parcel;

public class ViewModelBase<V extends IView<?>, VM extends IViewModel<?>> implements IViewModel<V>, Serializable{

	private static final long serialVersionUID = 6461370234548566685L;
	private final String LOG_TAG = getClass().getSimpleName();

	transient protected V _view;
	
//    @SuppressWarnings("unchecked")
//	protected ViewModelBase(Parcel in) {
//    	this((T)in.readSerializable());
//    }

    public ViewModelBase(V view) {
    	setView(view);
	}
    
    public void setView(V view){
    	_view = view;
    }

//    public ViewModelBase(T other) {
//	}

	public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
    	Logger.write(LOG_TAG, "Serializing " + this.getClass().getSimpleName() + " to Parcel.");
    	out.writeSerializable(this);
    }


//    @SuppressWarnings("unchecked")
//	public static final Parcelable.Creator<ViewModelBase<?>> CREATOR = 
//    		new ParcelableCreator(ViewModelBase.class).getSerializingCreator();
    
//    Parcelable.Creator<ViewModelBase<?>> CREATOR
//    = new Parcelable.Creator<ViewModelBase<?>>() {
//
//    	public static final Parcelable.Creator<ViewModelBase<?>> CREATOR
//            = new Parcelable.Creator<ViewModelBase<?>>() {
//        @SuppressWarnings("rawtypes")
//		public ViewModelBase<?> createFromParcel(Parcel in) {
//            return new ViewModelBase(in);
//        }
//
//        public ViewModelBase<?>[] newArray(int size) {
//            return new ViewModelBase<?>[size];
//        }
//    };

//    public final Parcelable.Creator<ViewModelBase<?>> getCreator()
//    {
//    	return CREATOR;
//    }
}
