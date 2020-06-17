package com.example.managetime.Presenter;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.managetime.App;
import com.example.managetime.Model.dto.Task;
import com.example.managetime.R;
import com.example.managetime.Views.AddTaskActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdapterListTasks extends RecyclerView.Adapter<AdapterListTasks.TaskViewHolder> {

    private SortedList<Task> sortedListTasks;

    public AdapterListTasks() {
        sortedListTasks = new SortedList<>(Task.class, new SortedList.Callback<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1.isDone && !o2.isDone) {
                    return 1;
                }
                if (!o1.isDone && o2.isDone) {
                    return -1;
                }
                return (int) (o1.startTime - o2.startTime);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Task oldItem, Task newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Task item1, Task item2) {
                return item1.id == item2.id;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(sortedListTasks.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedListTasks.size();
    }

    public void setItems(List<Task> tasks) {
        sortedListTasks.replaceAll(tasks);
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView taskTitle;
        CheckBox taskIsDoneCheckBox;
        View deleteButtonImage;

        Task task;
        boolean silentUpdateTaskChecked;

        public TaskViewHolder(@NonNull final View itemView) {
            super(itemView);

            taskTitle = itemView.findViewById(R.id.task_title);
            taskIsDoneCheckBox = itemView.findViewById(R.id.taskIsDoneCheckBox);
            deleteButtonImage = itemView.findViewById(R.id.deleteButtonImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddTaskActivity.start((Activity) itemView.getContext(), task, 0);
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

            SimpleDateFormat formatter = new SimpleDateFormat("H:mm");
            Date date = new Date(task.startTime);
            if (date.getHours() == 0 && date.getMinutes() == 0) {
                taskTitle.setText(task.title);
            } else {
                if (task.duration != 0) {
                    Date endDate = new Date(task.startTime + task.duration);
                    String startDateAndTimeString = formatter.format(date);
                    String endDateAndTimeString = formatter.format(endDate);
                    taskTitle.setText(startDateAndTimeString + "-" + endDateAndTimeString + " " + task.title);
                } else {
                    String dateAndTimeString = formatter.format(date);
                    taskTitle.setText(dateAndTimeString + " " + task.title);
                }
            }

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
