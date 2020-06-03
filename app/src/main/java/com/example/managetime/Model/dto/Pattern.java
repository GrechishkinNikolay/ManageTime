package com.example.managetime.Model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Pattern implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "project")
    public int project;

    public Pattern() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pattern pattern = (Pattern) o;
        return id == pattern.id &&
                project == pattern.project &&
                name.equals(pattern.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, project);
    }

    protected Pattern(Parcel in) {
        id = in.readInt();
        name = in.readString();
        project = in.readInt();
    }

    public static final Creator<Pattern> CREATOR = new Creator<Pattern>() {
        @Override
        public Pattern createFromParcel(Parcel in) {
            return new Pattern(in);
        }

        @Override
        public Pattern[] newArray(int size) {
            return new Pattern[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(project);
    }
}
