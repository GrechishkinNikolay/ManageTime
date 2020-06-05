package com.example.managetime.Presenter;

import android.app.Activity;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.managetime.App;
import com.example.managetime.Model.dto.Task;
import com.example.managetime.R;
import com.example.managetime.Views.AddTaskActivity;

public class AdapterListTasks extends RecyclerView.Adapter<AdapterListTasks.TaskViewHolder> {

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskTitle;
        CheckBox taskIsDoneCheckBox;
        View deleteButtonImage;

        Task task;
        boolean silentUpdateTaskChecked;

        public TaskViewHolder(@NonNull final View itemView) {
            super(itemView);

            taskTitle = itemView.findViewById(R.id.taskTitle);
            taskIsDoneCheckBox = itemView.findViewById(R.id.taskIsDoneCheckBox);
            deleteButtonImage = itemView.findViewById(R.id.deleteButtonImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddTaskActivity.start((Activity) itemView.getContext(), task, null);
                }
            });

            deleteButtonImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    App.getInstance().getTaskDao().deleteTask(task);
                }
            });

            taskIsDoneCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!silentUpdateTaskChecked) {
                        task.isDone = isChecked;
                        App.getInstance().getTaskDao().updateTask(task);
                    }
                    checkAndUpdateIsDone();
                }
            });
        }

        public void bind(Task task) {
            this.task = task;

            taskTitle.setText(task.title);
            checkAndUpdateIsDone();

            silentUpdateTaskChecked = true;
            taskIsDoneCheckBox.setChecked(task.isDone);
            silentUpdateTaskChecked = false;

        }

        private void checkAndUpdateIsDone() {
            if (task.isDone) {
                taskTitle.setPaintFlags(taskTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                taskTitle.setPaintFlags(taskTitle.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }
        }
    }
}
