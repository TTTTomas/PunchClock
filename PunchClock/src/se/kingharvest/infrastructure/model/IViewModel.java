package se.kingharvest.infrastructure.model;

import android.os.Parcelable;

public interface IViewModel<V extends IView<?>> extends Parcelable{

    public void setView(V view);

}
