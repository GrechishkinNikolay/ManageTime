package com.example.managetime.Model.dto;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Task implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "taskDescription")
    public String taskDescription;

    @ColumnInfo(name = "priority")
    public String priority;

    @ColumnInfo(name = "duration")
    public long duration;

    @ColumnInfo(name = "startTime")
    public long startTime;

    @ColumnInfo(name = "endTime")
    public long endTime;

    @ColumnInfo(name = "isDone")
    public boolean isDone;

    @ColumnInfo(name = "parentTask")
    public long parentTask;

    @ColumnInfo(name = "pattern")
    public long pattern;

    @ColumnInfo(name = "project")
    public long project;

    public Task() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                duration == task.duration &&
                startTime == task.startTime &&
                endTime == task.endTime &&
                isDone == task.isDone &&
                parentTask == task.parentTask &&
                pattern == task.pattern &&
                project == task.project &&
                title.equals(task.title) &&
                Objects.equals(taskDescription, task.taskDescription) &&
                Objects.equals(priority, task.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, taskDescription, priority, duration, startTime, endTime, isDone, parentTask, pattern, project);
    }

    protected Task(Parcel in) {
        id = in.readInt();
        title = in.readString();
        taskDescription = in.readString();
        priority = in.readString();
        duration = in.readLong();
        startTime = in.readLong();
        endTime = in.readLong();
        isDone = in.readByte() != 0;
        parentTask = in.readLong();
        pattern = in.readLong();
        project = in.readLong();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(taskDescription);
        dest.writeString(priority);
        dest.writeLong(duration);
        dest.writeLong(startTime);
        dest.writeLong(endTime);
        dest.writeByte((byte) (isDone ? 1 : 0));
        dest.writeLong(parentTask);
        dest.writeLong(pattern);
        dest.writeLong(project);
    }
}
