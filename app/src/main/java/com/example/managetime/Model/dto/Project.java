package com.example.managetime.Model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.Objects;

@Entity
public class Project implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "tasks")
    public List<Task> tasks;

    @ColumnInfo(name = "patterns")
    public List<Pattern> patterns;

    public Project() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return id == project.id &&
                name.equals(project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(tasks, project.tasks) &&
                Objects.equals(patterns, project.patterns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, tasks, patterns);
    }

    protected Project(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        tasks = in.createTypedArrayList(Task.CREATOR);
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
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
        dest.writeString(description);
        dest.writeTypedList(tasks);
    }
}
